package main;

import Inputs.KeyboardInputs;
import Inputs.MouseInputs;
import entityes.Player;
import graphics.Draw;
import utilz.Constants;

import javax.swing.*;

import java.awt.*;



public class GamePanel extends JPanel{
    private final MouseInputs mouseInputs;
    private final Draw draw;
    private final Player player;

    public GamePanel(Player player){
        setPanelSize();
        this.draw=new Draw(player);
        this.player=player;
        mouseInputs = new MouseInputs(player);
        addKeyListener(new KeyboardInputs(player,this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        setBackground(new Color(91, 151, 235));
    }
    private void setPanelSize() {
        Dimension size = new Dimension(Constants.Sizes.WINDOW_WIDTH*Constants.Sizes.SCALE, Constants.Sizes.WINDOW_HEIGHT*Constants.Sizes.SCALE);
        setPreferredSize(size);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        switch (GameControl.gamestate){
            case PLAYING -> draw.renderPlaying(g);
            case MENU -> draw.renderMenu(g);
            case PAUSE -> draw.renderPause(g);
            case WIN -> draw.renderWin(g);
        }


    }
}
