package Inputs;

import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {
    private GamePanel gamePanel;
    public KeyboardInputs(GamePanel gp){
        this.gamePanel=gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_W){
            gamePanel.setYPos(-gamePanel.getYSpeed());
        }
        if(e.getKeyCode()==KeyEvent.VK_A){
            gamePanel.setXPos(-gamePanel.getXSpeed());
        }
        if(e.getKeyCode()==KeyEvent.VK_S){
            gamePanel.setYPos(gamePanel.getYSpeed());
        }
        if(e.getKeyCode()==KeyEvent.VK_D){
            gamePanel.setXPos(gamePanel.getXSpeed());
        }
/*
        if(e.getKeyCode()==KeyEvent.VK_W && e.getKeyCode()==KeyEvent.VK_A){
            gamePanel.setYPos(-gamePanel.getYSpeed()*1.414213f);
            gamePanel.setXPos(-gamePanel.getXSpeed()*1.414213f);
        }
        if(e.getKeyCode()==KeyEvent.VK_W && e.getKeyCode()==KeyEvent.VK_D){
            gamePanel.setYPos(-gamePanel.getYSpeed()*1.414213f);
            gamePanel.setXPos(gamePanel.getXSpeed()*1.414213f);
        }
        if(e.getKeyCode()==KeyEvent.VK_Y && e.getKeyCode()==KeyEvent.VK_A){
            gamePanel.setYPos(gamePanel.getYSpeed()*1.414213f);
            gamePanel.setXPos(-gamePanel.getXSpeed()*1.414213f);
        }
        if(e.getKeyCode()==KeyEvent.VK_Y && e.getKeyCode()==KeyEvent.VK_D){
            gamePanel.setYPos(gamePanel.getYSpeed()*1.414213f);
            gamePanel.setXPos(gamePanel.getXSpeed()*1.414213f);
        }*/
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
