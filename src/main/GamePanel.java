package main;

import Inputs.KeyboardInputs;
import UI.MainMenu;
import UI.PauseMenu;
import UI.WinScreen;
import entityes.Player;
import graphics.Render;

import javax.swing.*;

import java.awt.*;

import static main.GameControl.gamestate;
import static utilz.Constants.Sizes.*;


public class GamePanel extends JPanel {
    private final Render render;

    public GamePanel(Player player, MainMenu mainMenu, PauseMenu pauseMenu, WinScreen winScreen) {
        setPanelSize();
        this.render = new Render(player, mainMenu, pauseMenu, winScreen);
        addKeyListener(new KeyboardInputs(player, mainMenu, pauseMenu, winScreen));
        setBackground(new Color(91, 151, 235));
    }

    private void setPanelSize() {
        Dimension size = new Dimension(WINDOW_WIDTH * SCALE, WINDOW_HEIGHT * SCALE);
        setPreferredSize(size);
    }

    public void updateBackground() {
        switch (gamestate) {
            case PLAYING -> setBackground(new Color(91, 151, 235));
            case MENU -> setBackground(new Color(91, 151, 235));
            case PAUSE -> setBackground(new Color(91, 151, 235));
            case WIN -> setBackground(new Color(91, 151, 235));
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        switch (gamestate) {
            case PLAYING -> render.renderPlaying(g);
            case MENU -> render.renderMenu(g);
            case PAUSE -> render.renderPause(g);
            case WIN -> render.renderWin(g);
        }


    }
}
