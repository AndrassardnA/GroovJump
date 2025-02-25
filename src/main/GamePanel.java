package main;

import Inputs.KeyboardInputs;
import Inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.Directions.*;

public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private float xPos =0, yPos =0;
    private float xSpeed=10, ySpeed=10;
    private BufferedImage img;
    private BufferedImage[][] animations;
    private int animTimer=0; //incrases by frame;
    private int animIndex, animSpeed=120/6; //means 120/(120/x) means x frames per second
    private int playerAction=RUN;
    private int playerDirection=-1;
    private boolean moving=false;
    private int playerDrawDir=1; //1 if facing right -1 if facing left
    private int playerDrawDirPositionCorrection=0; //correct the position while mirroring
    public GamePanel(){
        setPanelSize();
        importImg();
        loadAnimation();
        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void loadAnimation() {
        animations=new BufferedImage[4][8];
        for (int i=0; i< animations.length;i++){
            for (int j=0; j<animations[i].length;j++){
                animations[i][j]=img.getSubimage(j*16,i*16,16,16);
            }
        }
    }

    void importImg(){
        InputStream is = getClass().getResourceAsStream("/sprites/test_Player_sprite2.png");
        try {
            img= ImageIO.read(is);
        }catch(IOException e){
            e.printStackTrace();
        }

    }
    private void setPanelSize() {
        Dimension size = new Dimension(1280,680);
        setPreferredSize(size);
    }
    public void setDirection(int direction){
        this.playerDirection=direction;
        moving=true;
    }
    public void isMoving(boolean moving){
        this.moving=moving;
    }
    public void updateGame(){
        updateAnimLoop();
        setAnimation();
        updatePos();
    }

    private void updateAnimLoop() {
        animTimer++;

        if(animTimer>=animSpeed){
            animTimer=0;
            animIndex++;
            if(animIndex>=getSpritesAmount(playerAction)){
                animIndex=0;
            }
        }
    }
    private void setAnimation(){
        if(moving){
            playerAction=RUN;
        }
        else {
            playerAction=IDLE;
        }
    }
    private void updatePos(){
        if(moving){
            switch (playerDirection){
                case UP:
                    yPos-=1;
                    break;
                case DOWN:
                    yPos+=1;
                    break;
                case RIGHT:
                    xPos+=1;
                    playerDrawDir=1;
                    playerDrawDirPositionCorrection=0;
                    break;
                case LEFT:
                    xPos-=1;
                    playerDrawDir=-1;
                    playerDrawDirPositionCorrection=128;
                    break;
            }
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(animations[playerAction][animIndex],(int)xPos+playerDrawDirPositionCorrection,(int)yPos,128*playerDrawDir,128,null);
    }
}
