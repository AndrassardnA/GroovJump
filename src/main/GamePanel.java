package main;

import Inputs.KeyboardInputs;
import Inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private int xDelta=150, yDelta=150;
    public GamePanel(){
        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }
    public void changeXDelta(int value){
        xDelta+=value;
        repaint();
    }
    public void changeYDelta(int value){
        yDelta+=value;
        repaint();
    }
    public void setRectPos(int x,int y){
        xDelta=x;
        yDelta=y;
        repaint();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.fillRect(xDelta,yDelta,100,100);
    }
}
