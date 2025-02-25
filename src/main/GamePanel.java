package main;

import Inputs.KeyboardInputs;
import Inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private float xPos =150, yPos =150;
    private float xSpeed=10, ySpeed=10;
    public GamePanel(){
        setPanelSize();
        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
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
    public void setRectPos(float x,float y){
        xPos =x;
        yPos =y;

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.fillRect((int)xPos, (int)yPos,100,100);
    }
}
