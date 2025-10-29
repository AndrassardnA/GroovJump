package main;

import graphics.Draw;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class GameWindow {
    private final JFrame jFrame;
    public GameWindow(GamePanel gamePanel, GameControl gameControl){
        jFrame=new JFrame();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.add(gamePanel);
        jFrame.setLocation(0,0);//a bal felső sarokba helyezi az ablakot
        jFrame.pack(); // a jpanelhez képest nagyítja az ablakot
        jFrame.setResizable(false);
        jFrame.setVisible(true);
        jFrame.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                gamePanel.requestFocusInWindow();
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                gameControl.getPlayer().stopMoving();
            }
        });
    }
}
