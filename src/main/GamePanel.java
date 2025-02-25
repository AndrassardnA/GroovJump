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
    private float xPos =150, yPos =150;
    private float xSpeed=10, ySpeed=10;
    private BufferedImage img;
    public GamePanel(){
        setPanelSize();
        importImg();
        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }
    void importImg(){
        InputStream is = getClass().getResourceAsStream("/sprites/test_Player_sprite.png");
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

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(img,(int)xPos,(int)yPos,128,128,null);
    }
}
