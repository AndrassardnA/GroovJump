package entityes;

import levels.LevelManager;
import movement.Physic;
import utilz.Constants;
import utilz.LoadSave;
import levels.Level;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilz.Constants.PlayerConstants.*;


public class Player extends  Entity{
    Physic physic;
    //ANIMATION
    private BufferedImage[][] animations;
    private int animTimer=0; //incrases by frame;
    private int animIndex, animSpeed=120/6; //means 120/(120/x) means x frames per second
    private int action =RUN;
    //MOVEMENT
    private final float scaleMod=6/Constants.Sizes.SCALE;
    private final float speed=0.65f;
    private final float jumpPower=1.7f;
    private final float fallingSpeed=0.038f;
    private boolean up,down,right,left,jump; // jump: ha lenyomom a space-t true, ha felengedem false. Ha nyomvatartás közben bármi falsra állítja a nyomvatartás nem állítja vissza, újra fel kell engeni és le kell nyomni
    private boolean jumpBeingHeld; // figyeli, hogy nyomvatartás közben a jump ne álljon automatikusan true-ra ha egyszer false lett. Player ugráskor lesz true és input felengedéskor false
    private final float coyotJump=0.13f;
    private float coyotJumpTimer=0;
    private boolean jumpAlradyPressed=false; //ha lenyomták az ugrást true, ha földreért false
    private final float prejump=0.15f;
    private float prejumpTimer=prejump;
    private boolean prejumpTimerStarted=false;
    private boolean prejumpIntent=false;
    //RENDER
    private final float scale=Constants.Sizes.SCALE;

    private final int screenX;
    private int screenY;
    private int turningMod =1; //1 if facing right -1 if facing left
    private int turningPositionCorrection =0; //correct the position while mirroring
    private static final int PLAYER_DEFAULT_HEIGHT=16;
    private static final int PLAYER_DEFAULT_WIDTH=16;
    private float height, width;

    //GAME PLAY
    public int deaths=0;
    private boolean levelFinished=false;
    public boolean levelFinishedBeenLocked=false;

    //CONSTRUCTOR
    public Player(float x, float y, Level level) {
        super(x, y,(int)(PLAYER_DEFAULT_WIDTH/* Constants.Sizes.SCALE*/), (int)(PLAYER_DEFAULT_HEIGHT/* Constants.Sizes.SCALE*/));
        height=PLAYER_DEFAULT_HEIGHT/* Constants.Sizes.SCALE*/;
        width=PLAYER_DEFAULT_WIDTH/* Constants.Sizes.SCALE*/;
        screenX=(int)(Constants.Sizes.WINDOW_WIDTH/2-width/2);
        loadAnimation();
        setHitboxes();
        physic=new Physic(this);
    }

    private void setHitboxes() {
        modLeftHitx=0;
        modLeftHity=2;
        modRightHitx=(int)width;
        modRightHity=2;
        modHeadHitx=2;
        modHeadHity=0;
        modFeetHitx=2;
        modFeetHity=(int)height;

        hitboxLeft=new Rectangle((int) screenX +modLeftHitx,(int) worldY +modLeftHity,1,(int)height-3);
        hitboxRight=new Rectangle((int) screenX +modRightHitx,(int) worldY +modRightHity,1,(int)height-3);
        hitboxHead=new Rectangle((int) screenX +modHeadHitx,(int) worldY +modHeadHity,(int)width-3,1);
        hitboxFeet=new Rectangle((int) screenX +modFeetHitx,(int) worldY +modFeetHity,(int)width-3,1);
    }

    //UPDATE AND RENDER
    public void update(){
        levelFinished=false;
        updatePos();
        physic.detectCollision(LevelManager.getCurrentLevel());
        physic.calculateGravity(fallingSpeed);
        jump();
        physic.applyGravity();
        physic.dontStuck();
        updateHitbox();
        setAnimation();
        updateAnimLoop();
        tryDying();
        if(physic.isFinishCollision()/*&&!levelFinishedBeenLocked*/){
            levelFinished=true;
           // levelFinishedBeenLocked=true;
        }
    }
    public void render(Graphics g){
        BufferedImage currentImage=animations[action][animIndex];
        int X = (int)(screenX + width*turningPositionCorrection);
        int Y = (int)worldY;
        int W = (int)(width*turningMod);
        int H = (int)(height);

        g.drawImage(currentImage,X*(int)scale,Y*(int)scale,W*(int)scale,H*(int)scale,null);
        drawHitbox(g);
    }

    //LOADERS
    private void loadAnimation() {
            BufferedImage img= LoadSave.getSprite(LoadSave.PLAYER_SPRITE);
            animations=new BufferedImage[4][8];
            for (int i=0; i< animations.length;i++){
                for (int j=0; j<animations[i].length;j++){
                    animations[i][j]=img.getSubimage(j*16,i*16,16,16);
                }
            }
    }
    //TO UPDATE
    private void updateAnimLoop() {
        animTimer++;

        if(animTimer>=animSpeed){
            animTimer=0;
            animIndex++;
            if(animIndex>=getSpritesAmount(action)){
                animIndex=0;
                //jump off
                if(action==JUMP){
                    jump=false;
                }
            }
        }
    }
    private void setAnimation(){
        if((right||left)&&!(left&&right)){
            action =RUN;
        }
        else {
            action =IDLE;
        }
    }
    private void updatePos(){
        if(left && !right && !physic.isLeftCollision()){
            physic.moveLeft(speed);
            turningMod=-1;
            turningPositionCorrection=1;
        } else if (right && !left && !physic.isRightCollision()) {
            physic.moveRight(speed);
            turningMod=1;
            turningPositionCorrection=0;
        }
    }

    @Override protected void updateHitbox(){
        hitboxRight.x= screenX +modRightHitx;
        hitboxRight.y=(int) worldY +modRightHity;

        hitboxLeft.x= screenX +modLeftHitx;
        hitboxLeft.y=(int) worldY +modLeftHity;

        hitboxHead.x= screenX +modHeadHitx;
        hitboxHead.y=(int) worldY +modHeadHity;

        hitboxFeet.x= screenX +modFeetHitx;
        hitboxFeet.y=(int) worldY +modFeetHity;
    }
    //OTHER
    public void stopMoving(){
        up=false;
        down=false;
        left=false;
        right=false;
        jump=false;
    }
    public void levelChanged(){
        worldX =1* Constants.Sizes.TILE_SIZE;
        worldY =4* Constants.Sizes.TILE_SIZE;
        physic.setyVel(0);
        levelFinished=false;
        levelFinishedBeenLocked=false;
    }
    private void jump(){

        if(physic.isGrounded()){
            jumpAlradyPressed=false;
        }
        setCoyotJumpTimer();
        setPrejumpTimer();
        boolean canJump=(physic.isGrounded() || coyotJumpTimer<=coyotJump) && !jumpBeingHeld && !jumpAlradyPressed;
        boolean shouldJump= prejumpTimer>0 && physic.isGrounded();
        if((jump && canJump) || (prejumpIntent && shouldJump)){
            physic.setyVel(jumpPower);
            jumpBeingHeld=true;
            prejumpTimer=-1;
            prejumpIntent=false;
        }
        else if(!jump && !jumpBeingHeld && !physic.isGrounded() && physic.getyVel()>0){ //small jump
            physic.setyVel(physic.getyVel()/1.1f);
        }
        if(jump) {
            jumpAlradyPressed=true;
            jumpBeingHeld=true;
        }
        if(prejumpTimer<0){
            jump=false;
        }
        if(physic.isHeadButt()){
            physic.setyVel(0);
        }
    }
    private void setPrejumpTimer(){
        if(!physic.isGrounded()&&prejumpIntent&&!prejumpTimerStarted&&!jumpBeingHeld){
            prejumpTimer=prejump;
            prejumpTimerStarted=true;
        } else if (prejumpTimerStarted) {
            prejumpTimer-=1f/200f;
        }
        if(physic.getyVel()>0){
            prejumpTimerStarted=false;
            prejumpTimer=-1;
        }
    }
    private void setCoyotJumpTimer(){
        if(physic.isGrounded()){
            coyotJumpTimer=0;
        }
        else{
            coyotJumpTimer+=1f/200f;
        }
    }

    //BOOLEAN SETTERS
    public void setUp(boolean up) {
        this.up = up;
    }
    public void setDown(boolean down) {
        this.down = down;
    }
    public void setRight(boolean right) {
        this.right = right;
    }
    public void setLeft(boolean left) {
        this.left = left;
    }
    public void setJump(boolean jump){
        this.jump=jump;
    }
    public void setJumpBeingHeld(boolean jumpBeingHeld) {
        this.jumpBeingHeld = jumpBeingHeld;
    }
    public void setPrejumpIntent(boolean prejumpIntent){this.prejumpIntent=prejumpIntent;}

    public void setLevelFinished(boolean levelFinished) {
        this.levelFinished = levelFinished;
    }

    //BOOLEAN GETTERS
    public boolean isJumpBeingHeld() {
        return jumpBeingHeld;
    }
    public boolean isLevelFinished() {
        return levelFinished;
    }

    //GAME OVER
    private void die(){
            deaths++;
            System.out.println("You Died! Deaths: " + deaths);
            levelChanged();
    }
    public void tryDying(){
        if(physic.isHazardCollision()){
            die();
        }
    }
    public void renderDeathUI(Graphics g){
        String kiir="Deaths: " + deaths;
        g.setFont(new Font("Arial",Font.BOLD,13*(int)scale));
        g.setColor(Color.black);
        g.drawString(kiir,11*Constants.Sizes.TILE_SIZE*(int)scale,1*Constants.Sizes.TILE_SIZE*(int)scale);
    }
}
