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
    private float speed=1;
    private int direction =-1;
    private boolean moving=false;
    private int turningMod =1; //1 if facing right -1 if facing left
    private int turningPositionCorrection =0; //correct the position while mirroring
    public Player(float x, float y) {
        super(x, y);
        loadAnimation();
    }
    public void update(){
        updateAnimLoop();
        setAnimation();
        updatePos();
    }
    public void render(Graphics g){
        g.drawImage(animations[action][animIndex],(int)x+turningPositionCorrection,(int)y,128*turningMod,128,null);
    }

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
        if(moving){
            action =RUN;
        }
        else {
            action =IDLE;
        }
    }
    private void updatePos(){
        if(moving){
            switch (direction){
                case UP:
                    y-=speed;
                    break;
                case DOWN:
                    y+=speed;
                    break;
                case RIGHT:
                    x+=speed;
                    turningMod =1;
                    turningPositionCorrection =0;
                    break;
                case LEFT:
                    x-=speed;
                    turningMod =-1;
                    turningPositionCorrection =128;
                    break;
            }
        }
    }
    public void setDirection(int direction){
        this.direction =direction;
        moving=true;
    }
    public void isMoving(boolean moving){
        this.moving=moving;
    }
}
