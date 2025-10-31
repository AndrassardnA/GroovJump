package Inputs;

import UI.MainMenu;
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
    private final GamePanel gamePanel;
    public KeyboardInputs(Player p, MainMenu m, GamePanel gp){
        this.player =p;
        this.mainMenu=m;
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
