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
        Dimension size = new Dimension((int)(Constants.Sizes.WINDOW_WIDTH*Constants.Sizes.SCALE), (int)(Constants.Sizes.WINDOW_HEIGHT*Constants.Sizes.SCALE));
        setPreferredSize(size);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //draw player
        utilz.Drawer.drawPlayer(g,player.getCurrentFrame(), player.getScreenX(), player.worldY,player.getWidth(), player.getHeight(), player.isFacingRight());
        //draw hitbox
        utilz.Drawer.drawEntityHitbox(g,player.getHitboxLeft(),player.getHitboxRight(),player.getHitboxHead(),player.getHitboxFeet());
        //draw level
        utilz.Drawer.drawLevel(g,LevelManager.getCurrentLevel(),levelManager.getPlatformSprite());
        //draw deathUI
        utilz.Drawer.renderDeathUI(g,player.getDeaths());
    }
}
