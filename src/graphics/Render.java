package graphics;

import UI.MainMenu;
import entityes.Player;
import levels.LevelManager;

import java.awt.*;

public class Render {
    private final Player player;
    private final MainMenu mainMenu;
    public Render(Player player,MainMenu mm){
        this.player=player;
        this.mainMenu=mm;
    }

    public void renderPlaying(Graphics g){
        Background.drawBackGround(g);
        utilz.Drawer.drawPlayer(g,player.getAnimator().getCurrentFrame(player.getAction()+(player.isFacingRight() ? 0 : 1)), player.getX(), player.getY());
        utilz.Drawer.drawLevel(g, LevelManager.getCurrentLevel(),LevelManager.getPlatformSprite());
        Background.drawFront_Ground(g);
        utilz.Drawer.renderDeathUI(g,player.getDeaths());
    }
    public void renderMenu(Graphics g){
        utilz.Drawer.drawMainMenu(g,mainMenu);
    }
    public void renderPause(Graphics g){}
    public void renderWin(Graphics g){}

}
