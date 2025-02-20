package main;

import java.awt.*;

public class GameControl implements Runnable{
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private  Thread gameLoop;
    private final int FPS_SET=120;
    private void startGameLoop(){
        gameLoop= new Thread(this);
        gameLoop.run();
    }
    public GameControl(){
        gamePanel=new GamePanel();
        gameWindow=new GameWindow(gamePanel);
        setPanelSize();
        gamePanel.setFocusable(true); // engedélyezi, hogy fókuszolható legyen (fogadhasson inputokat)
        gamePanel.requestFocus(true); // az inputok a gampanelbe fognak menni
        startGameLoop();
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1600,1000);
        gamePanel.setMinimumSize(size);
        gamePanel.setPreferredSize(size);
        gamePanel.setMaximumSize(size);
    }

    @Override
    public void run() {
        double timePerFrame= 1000000000.0/FPS_SET;
        long now=System.nanoTime();
        long lastFrame=now;
        while(true){
            now=System.nanoTime();
            if(now-lastFrame>=timePerFrame){
                gamePanel.repaint();
                lastFrame=now;
            }
        }
    }
}
