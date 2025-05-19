package main;

import UI.Button;
import entityes.Player;
import levels.LevelManager;
import utilz.Constants;
import utilz.GameState;

public class GameControl implements Runnable{
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private  Thread gameLoop;
    private final int FPS_SET=120;
    private final int UPS_SET=200;
    private GameState gamestate = GameState.PLAYING;

    private LevelManager levelManager;
    Player player;
    private void startGameLoop(){
        gameLoop= new Thread(this);
        gameLoop.start();
    }
    private void update(){
        player.update();
        levelManager.xMod=(int)player.worldX;
        levelManager.update();
        if(player.isLevelFinished()){
            levelManager.nextLevel();
            player.levelChanged();
        }
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
        player=new Player(Constants.Sizes.TILE_SIZE*1,Constants.Sizes.TILE_SIZE*4, levelManager.getCurrentLevel());
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
            switch(gamestate){
                case PLAYING:
                    currentTime=System.nanoTime();

                    deltaU+=(currentTime-previousTime)/timePerUpdate;
                    deltaF+=(currentTime-previousTime)/timePerFrame;
                    previousTime=currentTime;

                    if(deltaU>=1){
                        update();
                        deltaU-=1;
                    }
                    if(deltaF>=1) {
                        gamePanel.repaint();
                        deltaF -= 1;
                    }
                    break;
                case MENU:
                    Button button = new Button("proba szoveg",100,100,200,50);
                    //button.drawButton(g);
                    break;
                case PAUSE:
                    break;
            }
        }
    }
}
