package Inputs;

import UI.MainMenu;
import UI.Menu;
import UI.PauseMenu;
import UI.WinScreen;
import entityes.Player;
import levels.LevelManager;
import main.GamePanel;
import utilz.GameState;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static main.GameControl.gamestate;

public class KeyboardInputs implements KeyListener {
    private final Player player;
    private final MainMenu mainMenu;
    private final PauseMenu pauseMenu;
    private final WinScreen winScreen;
    private final GamePanel gamePanel;
    public KeyboardInputs(Player p, MainMenu mm, PauseMenu pm, WinScreen ws, GamePanel gp){
        this.player =p;
        this.mainMenu=mm;
        this.pauseMenu=pm;
        this.winScreen=ws;
        this.gamePanel=gp;
    }
    private boolean shiftHolded=false;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(gamestate== GameState.PLAYING) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W:
                    player.setUp(true);
                    break;
                case KeyEvent.VK_D:
                    player.setRight(true);
                    break;
                case KeyEvent.VK_S:
                    player.setDown(true);
                    break;
                case KeyEvent.VK_A:
                    player.setLeft(true);
                    break;
                case KeyEvent.VK_SPACE:
                    if (!player.isJumpBeingHeld()) {
                        player.setJump(true);
                        player.setPrejumpIntent(true);
                    }
                    break;
                case KeyEvent.VK_SHIFT:
                    if (!shiftHolded) {
                        LevelManager.nextLevel();
                        player.levelChanged();
                        shiftHolded = true;
                    }
                    break;
                case KeyEvent.VK_ESCAPE:
                    gamestate=GameState.PAUSE;
            }
        }
        else if(gamestate==GameState.MENU){
            switch (e.getKeyCode()){
                case KeyEvent.VK_UP:
                    mainMenu.previousSelected();
                    break;
                case KeyEvent.VK_DOWN:
                    mainMenu.nextSelected();
                    break;
                case KeyEvent.VK_ENTER:
                    mainMenu.executeButton();
                    player.levelChanged();
                    break;
            }
        }
        else if(gamestate==GameState.PAUSE){
            switch (e.getKeyCode()){
                case KeyEvent.VK_UP:
                    pauseMenu.previousSelected();
                    break;
                case KeyEvent.VK_DOWN:
                    pauseMenu.nextSelected();
                    break;
                case KeyEvent.VK_ENTER:
                    pauseMenu.executeButton();
                    break;
            }
        }
        else{
            switch (e.getKeyCode()){
                case KeyEvent.VK_UP:
                    winScreen.previousSelected();
                    break;
                case KeyEvent.VK_DOWN:
                    winScreen.nextSelected();
                    break;
                case KeyEvent.VK_ENTER:
                    winScreen.executeButton();
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W:
                player.setUp(false);
                break;
            case KeyEvent.VK_D:
                player.setRight(false);
                break;
            case KeyEvent.VK_S:
                player.setDown(false);
                break;
            case KeyEvent.VK_A:
                player.setLeft(false);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(false);
                player.setJumpBeingHeld(false);
                break;
            case KeyEvent.VK_SHIFT:
                shiftHolded=false;
                break;
        }
    }
}
