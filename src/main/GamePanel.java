package main;

import Inputs.KeyboardInputs;
import Inputs.MouseInputs;
import entityes.Player;
import levels.LevelManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private Player player;
    private LevelManager levelManager;

    public GamePanel(){
        setPanelSize();
        initClasses();
        mouseInputs = new MouseInputs(player);
        addKeyListener(new KeyboardInputs(player));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void initClasses() {
        levelManager=new LevelManager(this);
        player=new Player(500,500, levelManager.getCurrentLevel());
    }

    private void setPanelSize() {
        Dimension size = new Dimension(GameControl.TILE_SIZE*16,GameControl.TILE_SIZE*10);
        setPreferredSize(size);
    }

    public void updateGame(){
        player.update();
        levelManager.update();
    }
    public Player getPlayer(){
        return player;
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        player.render(g);
        levelManager.draw(g);
    }
}
