package main;

import Inputs.KeyboardInputs;
import Inputs.MouseInputs;
import entityes.Player;
import levels.LevelManager;
import utilz.Constants;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private Player player;
    private LevelManager levelManager;

    public GamePanel(Player player, LevelManager levelManager){
        setPanelSize();
        this.player=player;
        this.levelManager=levelManager;
        mouseInputs = new MouseInputs(player);
        addKeyListener(new KeyboardInputs(player,this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }
    public LevelManager getLevelManager(){
        return levelManager;
    }
    private void setPanelSize() {
        Dimension size = new Dimension(Constants.Sizes.WINDOW_WIDTH, Constants.Sizes.WINDOW_HEIGHT);
        setPreferredSize(size);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        player.render(g);
        levelManager.draw(g);
    }
}
