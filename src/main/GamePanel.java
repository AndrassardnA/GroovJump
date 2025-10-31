package main;

import Inputs.KeyboardInputs;
import Inputs.MouseInputs;
import UI.MainMenu;
import entityes.Player;
import graphics.Render;
import utilz.Constants;

import javax.swing.*;

import java.awt.*;



public class GamePanel extends JPanel{
    private final MouseInputs mouseInputs;
    private final Render render;
    private final Player player;

    public GamePanel(Player player, MainMenu mainMenu){
        setPanelSize();
        this.render =new Render(player, mainMenu);
        this.player=player;
        mouseInputs = new MouseInputs(player);
        addKeyListener(new KeyboardInputs(player,mainMenu,this));
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
            case PLAYING -> render.renderPlaying(g);
            case MENU -> render.renderMenu(g);
            case PAUSE -> render.renderPause(g);
            case WIN -> render.renderWin(g);
        }


    }
}
