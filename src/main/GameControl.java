package main;

import UI.Button;
import entityes.Player;
import levels.LevelManager;
import utilz.Constants;
import utilz.GameState;

public class GameControl implements Runnable{
    private final GameWindow gameWindow;
    private final GamePanel gamePanel;
    private Thread gameLoop;
    private final int FPS_SET=Constants.Game.FPS;
    private final int UPS_SET=Constants.Game.UPS;
    private GameState gamestate = GameState.PLAYING;

    private LevelManager levelManager;
    Player player;
    private void startGameLoop(){
        gameLoop= new Thread(this);
        gameLoop.start();
    }
    private void update(){
        player.update();
        levelManager.xMod=(int)player.getWorldX();
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
        player=new Player(Constants.Sizes.TILE_DEFAULT_SIZE*1,Constants.Sizes.TILE_DEFAULT_SIZE*4);
    }
    public Player getPlayer(){
        return player;
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
