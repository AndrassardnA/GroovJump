package main;

import Inputs.KeyboardInputs;
import Inputs.MouseInputs;
import UI.MainMenu;
import UI.PauseMenu;
import UI.WinScreen;
import entityes.Player;
import graphics.Render;
import utilz.Constants;

import javax.swing.*;

import java.awt.*;

import static main.GameControl.gamestate;


public class GamePanel extends JPanel{
    private final Render render;

    public GamePanel(Player player, MainMenu mainMenu, PauseMenu pauseMenu, WinScreen winScreen){
        setPanelSize();
        this.render =new Render(player, mainMenu, pauseMenu, winScreen);
        MouseInputs mouseInputs = new MouseInputs(player);
        addKeyListener(new KeyboardInputs(player,mainMenu,pauseMenu,winScreen,this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        setBackground(new Color(91, 151, 235));
    }
    private void setPanelSize() {
        Dimension size = new Dimension(Constants.Sizes.WINDOW_WIDTH*Constants.Sizes.SCALE, Constants.Sizes.WINDOW_HEIGHT*Constants.Sizes.SCALE);
        setPreferredSize(size);
    }
    public void updateBackground(){
        switch(gamestate){
            case PLAYING -> setBackground(new Color(91, 151, 235));
            case MENU -> setBackground(new Color(91, 151, 235));
            case PAUSE -> setBackground(new Color(91, 151, 235));
            case WIN -> setBackground(new Color(91, 151, 235));
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        switch (gamestate){
            case PLAYING -> render.renderPlaying(g);
            case MENU -> render.renderMenu(g);
            case PAUSE -> render.renderPause(g);
            case WIN -> render.renderWin(g);
        }


    }
}
