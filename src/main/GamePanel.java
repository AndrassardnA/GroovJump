package main;

import Inputs.KeyboardInputs;
import Inputs.MouseInputs;
import entityes.Player;
import levels.LevelManager;
import utilz.Constants;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private final MouseInputs mouseInputs;
    private final Player player;
    private final LevelManager levelManager;

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
        utilz.Drawer.drawPlayer(g,player.getAnimator().getCurrentFrame(player.getAction()+(player.isFacingRight() ? 0 : 1)), player.getX(), player.getY());
        //draw hitbox
        utilz.Drawer.drawEntityHitbox(g,player.getPhysic().getHitboxLeft(), player.getPhysic().getHitboxRight(),player.getPhysic().getHitboxHead(),player.getPhysic().getHitboxFeet());
        //draw level
        utilz.Drawer.drawLevel(g,LevelManager.getCurrentLevel(),levelManager.getPlatformSprite());
        //draw deathUI
        utilz.Drawer.renderDeathUI(g,player.getDeaths());
    }
}
