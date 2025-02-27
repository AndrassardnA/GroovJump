package main;

import Inputs.KeyboardInputs;
import Inputs.MouseInputs;
import entityes.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.Directions.*;

public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private Player player;

    public GamePanel(){
        setPanelSize();
        player=new Player(100,100);
        mouseInputs = new MouseInputs(player);
        addKeyListener(new KeyboardInputs(player));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }
    private void setPanelSize() {
        Dimension size = new Dimension(1280,680);
        setPreferredSize(size);
    }

    public void updateGame(){
        player.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        player.render(g);
    }
}
