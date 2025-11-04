package main;

import UI.MainMenu;
import UI.PauseMenu;
import UI.WinScreen;
import entityes.Player;
import levels.LevelManager;
import utilz.Constants;
import utilz.GameState;

public class GameControl implements Runnable {
    private final GameWindow gameWindow;
    private final GamePanel gamePanel;
    private Thread gameLoop;
    private final int FPS_SET = Constants.Game.FPS;
    private final int UPS_SET = Constants.Game.UPS;
    public static GameState gamestate = GameState.MENU;

    private LevelManager levelManager;
    private Player player;
    private MainMenu mainMenu;
    private PauseMenu pauseMenu;
    private WinScreen winScreen;

    private void startGameLoop() {
        gameLoop = new Thread(this);
        gameLoop.start();
    }

    private void update() {
        if(gamestate==GameState.PLAYING) {
            player.update();
            levelManager.xMod = (int) player.getWorldX();
            levelManager.update();
            if (Player.levelFinished) {
                levelManager.nextLevel();
                player.levelChanged();
            }
        }
        else if(gamestate==GameState.WIN){
            player.update();
            winScreen.updateAnims();
        }
        else if(gamestate==GameState.MENU){
            mainMenu.updateAnim();
        }
        gamePanel.updateBackground();
    }

    public GameControl() {
        initClasses();
        gamePanel = new GamePanel(player, mainMenu, pauseMenu, winScreen);
        gameWindow = new GameWindow(gamePanel, this);
        gamePanel.setFocusable(true); // engedélyezi, hogy fókuszolható legyen (fogadhasson inputokat)
        gamePanel.requestFocus(true); // az inputok a gampanelbe fognak menni
        startGameLoop();
    }

    private void initClasses() {
        levelManager = new LevelManager();
        player = new Player(Constants.Sizes.TILE_DEFAULT_SIZE * -7, Constants.Sizes.TILE_DEFAULT_SIZE * 3);
        mainMenu = new MainMenu(2);
        pauseMenu = new PauseMenu(2);
        winScreen = new WinScreen(3);
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long currentTime = System.nanoTime();
        long previousTime = System.nanoTime();

        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                update();
                deltaU -= 1;
            }
            if (deltaF >= 1) {
                gamePanel.repaint();
                deltaF -= 1;
            }
        }
    }
}
