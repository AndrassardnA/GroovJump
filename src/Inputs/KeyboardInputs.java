package Inputs;

import entityes.Player;
import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static utilz.Constants.Directions.*;
public class KeyboardInputs implements KeyListener {
    private Player player;
    public KeyboardInputs(Player p){
        this.player =p;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W:
                player.setDirection(UP);
                break;
            case KeyEvent.VK_D:
                player.setDirection(RIGHT);
                break;
            case KeyEvent.VK_S:
                player.setDirection(DOWN);
                break;
            case KeyEvent.VK_A:
                player.setDirection(LEFT);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W:
            case KeyEvent.VK_D:
            case KeyEvent.VK_S:
            case KeyEvent.VK_A:
                player.isMoving(false);
                break;
        }
    }
}
