package entityes;

import main.GameControl;
import utilz.LoadSave;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utilz.Constants.Directions.*;
import static utilz.Constants.Directions.LEFT;
import static utilz.Constants.PlayerConstants.*;

public class Player extends  Entity{
    private BufferedImage[][] animations;
    private int animTimer=0; //incrases by frame;
    private int animIndex, animSpeed=120/6; //means 120/(120/x) means x frames per second
    private int action =RUN;
    private float speed=2;
    private float normalspeed=speed;
    private float speedSqrRootTwo=speed/1.4142f;
    private int direction =-1;
    private boolean moving=false;
    private int turningMod =1; //1 if facing right -1 if facing left
    private int turningPositionCorrection =0; //correct the position while mirroring
    private boolean up,down,right,left,jump;
    private static final int PLAYER_DEFAULT_HEIGHT=16;
    private static final int PLAYER_DEFAULT_WIDTH=16;
    private float height, width;

    //CONSTRUCTOR
    public Player(float x, float y) {
        super(x, y,(int)(PLAYER_DEFAULT_WIDTH*GameControl.SCALE), (int)(PLAYER_DEFAULT_HEIGHT*GameControl.SCALE));
        height=PLAYER_DEFAULT_HEIGHT*GameControl.SCALE;
        width=PLAYER_DEFAULT_WIDTH*GameControl.SCALE;
        loadAnimation();
    }

    //UPDATE AND RENDER
    public void update(){
        updatePos();
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
        if(jump){
        action=JUMP;
        }
    }
    private void updatePos(){
        if(left && !right){
            x-=speed;
            turningMod=-1;
            turningPositionCorrection=1;
        } else if (right && !left) {
            x+=speed;
            turningMod=1;
            turningPositionCorrection=0;
        }

        if (up && !down){
            y-=speed;
        }
        else if(down && !up){
            y+=speed;
        }

        if((up||down)&&(left||right)){
            speed=speedSqrRootTwo;
        }else{
            speed=normalspeed;
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
    private void detectCollision(){

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
        //set anim index zero to start jump
        if(!this.jump && jump){
            animIndex=0;
        }
        this.jump=jump;
    }
}
