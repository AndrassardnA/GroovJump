package graphics;

import UI.MainMenu;
import UI.PauseMenu;
import UI.WinScreen;
import entityes.Player;
import levels.LevelManager;

import java.awt.*;

import static utilz.Constants.Sizes.*;

public class Render {
    private final Player player;
    private final MainMenu mainMenu;
    private final PauseMenu pauseMenu;
    private final WinScreen winScreen;

    public Render(Player player, MainMenu mm, PauseMenu pm, WinScreen ws) {
        this.player = player;
        this.mainMenu = mm;
        this.pauseMenu = pm;
        this.winScreen = ws;
    }

    public void renderPlaying(Graphics g) {
        Background.drawBackGround(g);
        utilz.Drawer.drawPlayer(g, player.getAnimator().getCurrentFrame(player.getAction() + (player.isFacingRight() ? 0 : 1)), player.getX(), player.getY());
        utilz.Drawer.drawLevel(g, LevelManager.getCurrentLevel(), LevelManager.getPlatformSprite());
        Background.drawFront_Ground(g);
        utilz.Drawer.drawDeathUI(g);
    }

    public void renderMenu(Graphics g) {
        utilz.Drawer.drawMenu(g, mainMenu);
        utilz.Drawer.drawMainMenuAnimFrame(g, mainMenu);
    }

    public void renderPause(Graphics g) {
        //---------------------------------------
        renderPlaying(g);
        g.setColor(new Color(0, 0, 0, 50));
        g.fillRect(0, 0, WINDOW_WIDTH * SCALE, WINDOW_HEIGHT * SCALE);
        //---------------------------------------------

        utilz.Drawer.drawMenu(g, pauseMenu);
    }

    public void renderWin(Graphics g) {
        utilz.Drawer.drawMenu(g, winScreen);
        utilz.Drawer.drawWinScreenAnimFrame(g, winScreen);
        utilz.Drawer.drawWinScreenScore(g);
    }

}
