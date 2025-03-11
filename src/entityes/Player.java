package entityes;

import levels.Platform;
import main.GameControl;
import utilz.LoadSave;
import levels.Level;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilz.Constants.PlayerConstants.*;


public class Player extends  Entity{
    //ANIMATION
    private BufferedImage[][] animations;
    private int animTimer=0; //incrases by frame;
    private int animIndex, animSpeed=120/6; //means 120/(120/x) means x frames per second
    private int action =RUN;
    //MOVEMENT
    private float speed=2;
    private float yVel=0;
    private float jumpPower=5;
    private float fallingSpeed=0.08f;
    private boolean onGround;
    private boolean up,down,right,left,jump;
    //RENDER
    private int direction =-1;
    private boolean moving=false;
    private int turningMod =1; //1 if facing right -1 if facing left
    private int turningPositionCorrection =0; //correct the position while mirroring
    private static final int PLAYER_DEFAULT_HEIGHT=16;
    private static final int PLAYER_DEFAULT_WIDTH=16;
    private float height, width;
    //COLLISION
    private Level level;
    private boolean feetCollision, headCollision, rightCollision, leftCollision;


    //CONSTRUCTOR
    public Player(float x, float y, Level level) {
        super(x, y,(int)(PLAYER_DEFAULT_WIDTH*GameControl.SCALE), (int)(PLAYER_DEFAULT_HEIGHT*GameControl.SCALE));
        height=PLAYER_DEFAULT_HEIGHT*GameControl.SCALE;
        width=PLAYER_DEFAULT_WIDTH*GameControl.SCALE;
        this.level=level;
        loadAnimation();
        setHitboxes();
    }

    private void setHitboxes() {
        modLeftHitx=(int)width/8;
        modLeftHity=(int)height/2+5;
        modRightHitx=(int)width-(int)width/8-5;
        modRightHity=(int)height/2+5;
        modHeadHitx=(int)width/8+5;
        modHeadHity=(int)height/2;
        modFeetHitx=(int)width/8+5;
        modFeetHity=(int)height-5;

        hitboxLeft=new Rectangle((int)x+modLeftHitx,(int)y+modLeftHity,5,(int)height/2-10);
        hitboxRight=new Rectangle((int)x+modRightHitx,(int)y+modRightHity,5,(int)height/2-10);
        hitboxHead=new Rectangle((int)x+modHeadHitx,(int)y+modHeadHity,(int)width-2*((int)width/8)-10,5);
        hitboxFeet=new Rectangle((int)x+modFeetHitx,(int)y+modFeetHity,(int)width-2*((int)width/8)-10,5);
    }

    //UPDATE AND RENDER
    public void update(){
        gravity();
        jump();
        updatePos();
        updateHitbox();
        detectCollision();
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
        if(left && !right && !leftCollision){
            x-=speed;
            turningMod=-1;
            turningPositionCorrection=1;
        } else if (right && !left && !rightCollision) {
            x+=speed;
            turningMod=1;
            turningPositionCorrection=0;
        }
        //no need for this functions jet
        if (up && !down && !headCollision){
            //y-=speed;
        }
        else if(down && !up && !feetCollision){
           // y+=speed;
        }

        if((up||down)&&(left||right)){
           // speed=speedSqrRootTwo;
        }else{
           // speed=normalspeed;
        }
        y-=yVel;
    }

    //OTHER
    public void stopMoving(){
        up=false;
        down=false;
        left=false;
        right=false;
        jump=false;
    }
    private void detectCollision(){
        feetCollision=false;
        headCollision=false;
        rightCollision =false;
        leftCollision =false;
        onGround=false;
        for (Platform p: level.getPlatforms()){
            if(hitboxFeet.intersects(p.getBounds())){
                feetCollision=true;
                onGround=true;
            }
            if(hitboxHead.intersects(p.getBounds())){
                headCollision=true;
            }
            if(hitboxLeft.intersects(p.getBounds())){
                leftCollision=true;
            }
            if(hitboxRight.intersects(p.getBounds())){
                rightCollision=true;
            }
            if(hitboxFeet.intersects(p.getBounds())&&(hitboxLeft.intersects(p.getBounds())||hitboxRight.intersects(p.getBounds()))){ //againts stucking in ground
                y-=1;
            }
        }
    }
    //GRAVITY && JUMP

    private void gravity(){
        if(!onGround&&!up){
            yVel-=fallingSpeed;
        }
        else{
            yVel=0;
        }
    }
    private void jump(){
        if(jump && onGround){
            yVel=jumpPower;
        }
        if(yVel<0){
            jump=false;
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
}
