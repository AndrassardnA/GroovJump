package graphics;

import entityes.Player;
import levels.LevelManager;

import java.awt.*;

public class Draw{
    private Player player;
    public Draw(Player player){
        this.player=player;
    }

    public void renderPlaying(Graphics g){
        Background.drawBackGround(g);
        utilz.Drawer.drawPlayer(g,player.getAnimator().getCurrentFrame(player.getAction()+(player.isFacingRight() ? 0 : 1)), player.getX(), player.getY());
        utilz.Drawer.drawLevel(g, LevelManager.getCurrentLevel(),LevelManager.getPlatformSprite());
        Background.drawFront_Ground(g);
        utilz.Drawer.renderDeathUI(g,player.getDeaths());
    }
    public void renderMenu(Graphics g){}
    public void renderPause(Graphics g){}
    public void renderWin(Graphics g){}

}
