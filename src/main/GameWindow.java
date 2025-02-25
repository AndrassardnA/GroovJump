package main;

import javax.swing.*;

public class GameWindow {
    private JFrame jFrame;
    public GameWindow(GamePanel gamePanel){
        jFrame=new JFrame();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.add(gamePanel);
        jFrame.setLocationRelativeTo(null);//kozepre helyezi az ablakot (ahhoz kepest viszonyitja helyezi el)
        jFrame.pack(); // a jpanelhez képest nagyítja az ablakot
        jFrame.setResizable(false);
        jFrame.setVisible(true);
    }
}
