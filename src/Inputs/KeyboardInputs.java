package Inputs;

import entityes.Player;
import levels.LevelManager;
import main.GameControl;
import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static utilz.Constants.Directions.*;
public class KeyboardInputs implements KeyListener {
    private Player player;
    private GamePanel gamePanel;
    public KeyboardInputs(Player p, GamePanel gp){
        this.player =p;
        this.gamePanel=gp;
    }
    private boolean shiftHolded=false;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
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
                if(!player.isJumpBeingHeld()){
                    player.setJump(true);
                    player.setPrejumpIntent(true);
                }
                break;
            case KeyEvent.VK_SHIFT:
                if(!shiftHolded){
                    gamePanel.getLevelManager().nextLevel();
                    player.levelChanged();
                    shiftHolded=true;
                }
                break;
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
