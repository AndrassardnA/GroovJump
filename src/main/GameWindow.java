package main;

import javax.swing.*;

public class GameWindow {
    private JFrame jFrame;
    public GameWindow(int windowWidth, int windowHeight, GamePanel gamePanel){
        jFrame=new JFrame();
        jFrame.setSize(windowWidth,windowHeight);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.add(gamePanel);
        jFrame.setVisible(true);
    }
}
