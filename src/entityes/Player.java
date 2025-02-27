package entityes;

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
    private boolean up,down,right,left;

    //CONSTRUCTOR
    public Player(float x, float y) {
        super(x, y);
        loadAnimation();
    }

    //UPDATE AND RENDER
    public void update(){
        updatePos();
        setAnimation();
        updateAnimLoop();
    }
    public void render(Graphics g){
        g.drawImage(animations[action][animIndex],(int)x+turningPositionCorrection,(int)y,128*turningMod,128,null);
    }

    //LOADERS
    private void loadAnimation() {
        InputStream is = getClass().getResourceAsStream("/sprites/test_Player_sprite2.png");
        try {
            BufferedImage img= ImageIO.read(is);
            animations=new BufferedImage[4][8];
            for (int i=0; i< animations.length;i++){
                for (int j=0; j<animations[i].length;j++){
                    animations[i][j]=img.getSubimage(j*16,i*16,16,16);
                }
            }

        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally {
            try {
                is.close();
            }
            catch (IOException e){
                e.printStackTrace();
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
        if(left && !right){
            x-=speed;
            turningMod=-1;
            turningPositionCorrection=128;
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
}
