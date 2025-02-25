package main;

import Inputs.KeyboardInputs;
import Inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private float xPos =0, yPos =0;
    private float xSpeed=10, ySpeed=10;
    private BufferedImage img;
    private BufferedImage[] idleAnim;
    private int animTimer=0; //incrases by frame;
    private int animIndex, animSpeed=120/6; //means 120/(120/6) means 6 frames per secound
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
        idleAnim=new BufferedImage[8];
        for (int i=0; i< idleAnim.length;i++){
            idleAnim[i]=img.getSubimage(i*16,0*16,16,16);
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
    public float getXSpeed(){
        return xSpeed;
    }
    public float getYSpeed(){
        return ySpeed;
    }
    public void setXPos(float value){
        xPos +=value;
    }
    public void setYPos(float value){
        yPos +=value;
    }
    public void updateGame(){
        updateAnim();
    }

    private void updateAnim() {
        animTimer++;

        if(animTimer>=animSpeed){
            animTimer=0;
            animIndex++;
            if(animIndex>=idleAnim.length){
                animIndex=0;
            }
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(idleAnim[animIndex],(int)xPos,(int)yPos,128,128,null);
    }
}
