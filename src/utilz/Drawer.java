package utilz;

import UI.MainMenu;
import UI.Button;
import UI.Menu;
import UI.WinScreen;
import entityes.Player;
import levels.*;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilz.Constants.Sizes.*;

public class Drawer {
    private static final int scale = Constants.Sizes.SCALE;

    public static void drawPlatform(Graphics g, Platform p, BufferedImage[] platformSprite) {
        int windowsWidth = WINDOW_WIDTH * scale;
        int windowsHeight = Constants.Sizes.WINDOW_HEIGHT * scale;
        int tileSize = Constants.Sizes.TILE_DEFAULT_SIZE * scale;

        for (int i = 0; i < p.getHeight(); i++) {
            for (int j = 0; j < p.getWidth(); j++) {
                int x = p.getPosX() * scale + j * (tileSize);
                int y = p.getPosY() * scale + i * (tileSize);

                if (x > -tileSize && x < windowsWidth + tileSize && y > -tileSize && y < windowsHeight + tileSize) {
                    g.drawImage(platformSprite[p.getBody()[i][j]], x, y, null);
                }
            }
        }
    }

    public static void drawLevel(Graphics g, Level level, BufferedImage[] platformSprite) {
        for (int i = 0; i < level.getPlatforms().length; i++) {
            drawPlatform(g, level.getPlatforms()[i], platformSprite);

        }
        drawFinish(g, level.getFinish());
        drawHazard(g, level.getHazards());
    }

    public static void drawHazard(Graphics g, HazardBlock[] hazard_arr) {
        int tileSize = Constants.Sizes.TILE_DEFAULT_SIZE * scale;
        int windowsWidth = WINDOW_WIDTH * scale;

        for (HazardBlock h : hazard_arr) {
            int x = h.getPosX() * scale;
            int y = h.getPosY() * scale;

            if (x > -tileSize && x < windowsWidth + tileSize && y >= -16 * scale && y <= 11 * 16 * scale) {
                g.drawImage(LevelManager.getCurrentLevel().getHazardFrame(), x, y, null);
            }
        }
    }

    public static void drawFinish(Graphics g, FinishBlock finish) {
        int x = finish.getPosX() * scale;
        int y = finish.getPosY() * scale;
        int tileSize = Constants.Sizes.TILE_DEFAULT_SIZE * scale;
        int windowsWidth = WINDOW_WIDTH * scale;
        if (x > -tileSize && x < windowsWidth + tileSize) {
            g.drawImage(LevelManager.getCurrentLevel().getFinishFrame(), x, y, null);
        }
    }

    public static void drawPlayer(Graphics g, BufferedImage image, float x, float y) {
        g.drawImage(image, (int) (x * scale), (int) (y * scale), null);
    }

    public static void drawEntityHitbox(Graphics g, Rectangle hitboxLeft, Rectangle hitboxRight, Rectangle hitboxHead, Rectangle hitboxFeet) {
        //for debug;
        g.setColor(Color.RED);
        g.drawRect(hitboxLeft.x * scale, hitboxLeft.y * scale, hitboxLeft.width * scale, hitboxLeft.height * scale);
        g.drawRect(hitboxRight.x * scale, hitboxRight.y * scale, hitboxRight.width * scale, hitboxRight.height * scale);
        g.setColor(Color.BLUE);
        g.drawRect(hitboxHead.x * scale, hitboxHead.y * scale, hitboxHead.width * scale, hitboxHead.height * scale);

        g.drawRect(hitboxFeet.x * scale, hitboxFeet.y * scale, hitboxFeet.width * scale, hitboxFeet.height * scale);
    }

    public static void drawDeathUI(Graphics g) {
        int y=scale*5;
        g.drawImage(Player.deathTextImg,WINDOW_WIDTH*scale-Player.deathTextImg.getWidth()-30*scale,y,null);
        g.drawImage(Player.deathNumImg,WINDOW_WIDTH*scale-30*scale,y,null);
    }
    public static void drawButton(Graphics g, Button b){
        g.drawImage(b.getImg(),b.getX(),b.getY(),null);
    }

    public static void drawMenu(Graphics g, Menu m){
        g.drawImage(m.getTitle(), m.getTitleX(), m.getTitleY(),null);
        for(Button b : m.getButtons()){
            drawButton(g,b);
        }
    }
    public static void drawWinScreenAnimFrame(Graphics g, WinScreen ws){
        Animator[] anims=ws.getAnimators();
        for(int i=0; i<anims.length;i++){
            BufferedImage frame=anims[i].getCurrentFrame(0);
            int x =(i%2==0 ? 0 : WINDOW_WIDTH*scale-frame.getWidth());
            int y= (i-(i%2==0 ? 0 : 1))*frame.getHeight();
            g.drawImage(frame,x,y,null);
        }
    }
    public static void drawMainMenuAnimFrame(Graphics g, MainMenu m){
        BufferedImage frame=m.getAnimator().getCurrentFrame(0);
        int x = WINDOW_WIDTH/2*scale-50*scale;
        int y = WINDOW_HEIGHT/2*scale-13*scale;
        g.drawImage(frame,x,y,null);
    }
    public static void drawWinScreenScore(Graphics g){
        g.drawImage(Player.deathNumImg,WINDOW_WIDTH*scale/2-Player.deathNumImg.getWidth()/2-scale,WINDOW_HEIGHT*scale/2-scale*32,null);
    }

    public static BufferedImage reScale(BufferedImage img, int reWidth, int reHeight) {
        BufferedImage resized = new BufferedImage(reWidth, reHeight, (img.getColorModel().hasAlpha() ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB));

        Graphics2D g = resized.createGraphics();

        g.drawImage(img, 0, 0, reWidth, reHeight, null);
        g.dispose();

        return resized;
    }
}
