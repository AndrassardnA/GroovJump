package main;

import Inputs.KeyboardInputs;
import Inputs.MouseInputs;
import entityes.Player;
import levels.LevelManager;

import java.awt.*;

//import static com.sun.java.accessibility.util.AWTEventMonitor.*;

public class GameControl implements Runnable{
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private  Thread gameLoop;
    private final int FPS_SET=120;
    private final int UPS_SET=200;
    public final static int TILE_DEFAULT_SIZE=16;
    public final static float SCALE=6f;
    public final static int TILE_SIZE=(int)(SCALE*TILE_DEFAULT_SIZE);
    private LevelManager levelManager;
    Player player;
    private void startGameLoop(){
        gameLoop= new Thread(this);
        gameLoop.run();
    }
    private void update(){
        //gamePanel.updateGame();
        player.update();
        levelManager.update();
    }
    public GameControl(){
        initClasses();
        gamePanel=new GamePanel(player,levelManager);
        gameWindow=new GameWindow(gamePanel,this);
        gamePanel.setFocusable(true); // engedélyezi, hogy fókuszolható legyen (fogadhasson inputokat)
        gamePanel.requestFocus(true); // az inputok a gampanelbe fognak menni
        startGameLoop();
    }
    private void initClasses() {
        levelManager=new LevelManager();
        player=new Player(500,500, levelManager.getCurrentLevel());
    }
    public Player getPlayer(){
        return player;
    }
    public LevelManager getLevelManager(){
        return levelManager;
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
