package Inputs;

import UI.MainMenu;
import UI.Menu;
import UI.PauseMenu;
import UI.WinScreen;
import entityes.Player;
import levels.LevelManager;
import utilz.GameState;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static main.GameControl.gamestate;

public class KeyboardInputs implements KeyListener {
    private final Player player;
    private final MainMenu mainMenu;
    private final PauseMenu pauseMenu;
    private final WinScreen winScreen;

    public KeyboardInputs(Player p, MainMenu mm, PauseMenu pm, WinScreen ws) {
        this.player = p;
        this.mainMenu = mm;
        this.pauseMenu = pm;
        this.winScreen = ws;
    }

    private boolean shiftHolded = false;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (gamestate == GameState.PLAYING) {
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
                    gamestate = GameState.PAUSE;
            }
        } else if (gamestate == GameState.MENU) {
            menuNavigation(mainMenu, e.getKeyCode());
        } else if (gamestate == GameState.PAUSE) {
            menuNavigation(pauseMenu, e.getKeyCode());
        } else {
            menuNavigation(winScreen, e.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
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
                shiftHolded = false;
                break;
        }
    }

    private void menuNavigation(Menu m, int kc) {
        switch (kc) {
            case KeyEvent.VK_UP:
                m.previousSelected();
                break;
            case KeyEvent.VK_DOWN:
                m.nextSelected();
                break;
            case KeyEvent.VK_ENTER:
                m.executeButton();
                break;
        }
    }
}
