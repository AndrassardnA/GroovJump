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
    private final float speed=2;
   // private float yVel=0;
    private final float jumpPower=7;
    private final float fallingSpeed=0.1f;
   // private boolean onGround;
    private boolean up,down,right,left,jump; // jump: ha lenyomom a space-t true, ha felengedem false. Ha nyomvatartás közben bármi falsra állítja a nyomvatartás nem állítja vissza, újra fel kell engeni és le kell nyomni
    private boolean jumpBeingHeld; // figyeli, hogy nyomvatartás közben a jump ne álljon automatikusan true-ra ha egyszer false lett. Player ugráskor lesz true és input felengedéskor false
    private final float coyotJump=0.15f;
    private float coyotJumpTimer=0;
    private boolean jumpAlradyPressed=false; //ha lenyomták az ugrást true, ha földreért false
    private final float prejump=0.15f;
    private float prejumpTimer=prejump;
    private boolean prejumpTimerStarted=false;
    private boolean prejumpIntent=false;
    //RENDER
    private int turningMod =1; //1 if facing right -1 if facing left
    private int turningPositionCorrection =0; //correct the position while mirroring
    private static final int PLAYER_DEFAULT_HEIGHT=16;
    private static final int PLAYER_DEFAULT_WIDTH=16;
    private float height, width;


    //CONSTRUCTOR
    public Player(float x, float y, Level level) {
        super(x, y,(int)(PLAYER_DEFAULT_WIDTH* Constants.Sizes.SCALE), (int)(PLAYER_DEFAULT_HEIGHT* Constants.Sizes.SCALE));
        height=PLAYER_DEFAULT_HEIGHT* Constants.Sizes.SCALE;
        width=PLAYER_DEFAULT_WIDTH* Constants.Sizes.SCALE;
        loadAnimation();
        setHitboxes();
        physic=new Physic(this);
    }

    private void setHitboxes() {
        modLeftHitx=(int)width/8;
        modLeftHity=(int)height/2+2;
        modRightHitx=(int)width-(int)width/8-5;
        modRightHity=(int)height/2+2;
        modHeadHitx=(int)width/8+3;
        modHeadHity=(int)height/2-1;
        modFeetHitx=(int)width/8+3;
        modFeetHity=(int)height+1;

        hitboxLeft=new Rectangle((int)x+modLeftHitx,(int)y+modLeftHity,1,(int)height/2-3);
        hitboxRight=new Rectangle((int)x+modRightHitx,(int)y+modRightHity,1,(int)height/2-3);
        hitboxHead=new Rectangle((int)x+modHeadHitx,(int)y+modHeadHity,(int)width-2*((int)width/8)-9,1);
        hitboxFeet=new Rectangle((int)x+modFeetHitx,(int)y+modFeetHity,(int)width-2*((int)width/8)-9,1);
    }

    //UPDATE AND RENDER
    public void update(){
        physic.detectCollision(LevelManager.getCurrentLevel());
        physic.calculateGravity(fallingSpeed);
        jump();
        updatePos();
        physic.applyGravity();
        physic.dontStuck();
        updateHitbox();
        setAnimation();
        updateAnimLoop();
    }
    public void render(Graphics g){
        g.drawImage(animations[action][animIndex],(int)x+(int)(width*turningPositionCorrection),(int)y,(int)(width*turningMod),(int)(height),null);
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
    //OTHER
    public void stopMoving(){
        up=false;
        down=false;
        left=false;
        right=false;
        jump=false;
    }
    public void levelChanged(){
        x=1* Constants.Sizes.TILE_SIZE;
        y=4* Constants.Sizes.TILE_SIZE;
        physic.setyVel(0);
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

    //BOOLEAN GETTERS
    public boolean isJumpBeingHeld() {
        return jumpBeingHeld;
    }
}
