package main;

import java.awt.*;

public class GameControl implements Runnable{
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private  Thread gameLoop;
    private final int FPS_SET=120;
    private final int UPS_SET=200;
    public final static int TILE_DEFAULT_SIZE=16;
    public final static float SCALE=8f;
    public final static int TILE_SIZE=(int)(SCALE*TILE_DEFAULT_SIZE);
    private void startGameLoop(){
        gameLoop= new Thread(this);
        gameLoop.run();
    }
    private void update(){
        gamePanel.updateGame();
    }
    public GameControl(){
        gamePanel=new GamePanel();
        gameWindow=new GameWindow(gamePanel);
        gamePanel.setFocusable(true); // engedélyezi, hogy fókuszolható legyen (fogadhasson inputokat)
        gamePanel.requestFocus(true); // az inputok a gampanelbe fognak menni
        startGameLoop();
    }



    @Override
    public void run() {
        double timePerFrame= 1000000000.0/FPS_SET;
        double timePerUpdate= 1000000000.0/UPS_SET;

        long currentTime=System.nanoTime();
        long previousTime=System.nanoTime();

        double deltaU=0;
        double deltaF=0;

        while(true){
            currentTime=System.nanoTime();

            deltaU+=(currentTime-previousTime)/timePerUpdate;
            deltaF+=(currentTime-previousTime)/timePerFrame;
            previousTime=currentTime;

            if(deltaU>=1){
                update();
                deltaU-=1;
            }
            if(deltaF>=1){
                gamePanel.repaint();
                deltaF-=1;
            }
        }
    }
}
