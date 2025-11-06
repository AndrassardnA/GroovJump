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

    public static void drawPlatform(Graphics g, Platform p, BufferedImage[] platformSprite) {
        int windowsWidth = WINDOW_WIDTH * SCALE;
        int windowsHeight = WINDOW_HEIGHT * SCALE;
        int tileSize = TILE_DEFAULT_SIZE * SCALE;

        for (int i = 0; i < p.getHeight(); i++) {
            for (int j = 0; j < p.getWidth(); j++) {
                int x = p.getPosX() * SCALE + j * (tileSize);
                int y = p.getPosY() * SCALE + i * (tileSize);

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
        int tileSize = TILE_DEFAULT_SIZE * SCALE;
        int windowsWidth = WINDOW_WIDTH * SCALE;

        for (HazardBlock h : hazard_arr) {
            int x = h.getPosX() * SCALE;
            int y = h.getPosY() * SCALE;

            if (x > -tileSize && x < windowsWidth + tileSize && y >= -16 * SCALE && y <= 11 * 16 * SCALE) {
                g.drawImage(LevelManager.getCurrentLevel().getHazardFrame(), x, y, null);
            }
        }
    }

    public static void drawFinish(Graphics g, FinishBlock finish) {
        int x = finish.getPosX() * SCALE;
        int y = finish.getPosY() * SCALE;
        int tileSize = TILE_DEFAULT_SIZE * SCALE;
        int windowsWidth = WINDOW_WIDTH * SCALE;
        if (x > -tileSize && x < windowsWidth + tileSize) {
            g.drawImage(LevelManager.getCurrentLevel().getFinishFrame(), x, y, null);
        }
    }

    public static void drawPlayer(Graphics g, BufferedImage image, float x, float y) {
        g.drawImage(image, (int) (x * SCALE), (int) (y * SCALE), null);
    }

    public static void drawEntityHitbox(Graphics g, Rectangle hitboxLeft, Rectangle hitboxRight, Rectangle hitboxHead, Rectangle hitboxFeet) {
        //for debug;
        g.setColor(Color.RED);
        g.drawRect(hitboxLeft.x * SCALE, hitboxLeft.y * SCALE, hitboxLeft.width * SCALE, hitboxLeft.height * SCALE);
        g.drawRect(hitboxRight.x * SCALE, hitboxRight.y * SCALE, hitboxRight.width * SCALE, hitboxRight.height * SCALE);
        g.setColor(Color.BLUE);
        g.drawRect(hitboxHead.x * SCALE, hitboxHead.y * SCALE, hitboxHead.width * SCALE, hitboxHead.height * SCALE);

        g.drawRect(hitboxFeet.x * SCALE, hitboxFeet.y * SCALE, hitboxFeet.width * SCALE, hitboxFeet.height * SCALE);
    }

    public static void drawDeathUI(Graphics g) {
        int y = SCALE * 5;
        g.drawImage(Player.deathTextImg, WINDOW_WIDTH * SCALE - Player.deathTextImg.getWidth() - 30 * SCALE, y, null);
        g.drawImage(Player.deathNumImg, WINDOW_WIDTH * SCALE - 30 * SCALE, y, null);
    }

    public static void drawButton(Graphics g, Button b) {
        g.drawImage(b.getImg(), b.getX(), b.getY(), null);
    }

    public static void drawMenu(Graphics g, Menu m) {
        g.drawImage(m.getTitle(), m.getTitleX(), m.getTitleY(), null);
        for (Button b : m.getButtons()) {
            drawButton(g, b);
        }
    }

    public static void drawWinScreenAnimFrame(Graphics g, WinScreen ws) {
        Animator[] anims = ws.getAnimators();
        for (int i = 0; i < anims.length; i++) {
            BufferedImage frame = anims[i].getCurrentFrame(0);
            int x = (i % 2 == 0 ? 0 : WINDOW_WIDTH * SCALE - frame.getWidth());
            int y = (i - (i % 2 == 0 ? 0 : 1)) * frame.getHeight();
            g.drawImage(frame, x, y, null);
        }
    }

    public static void drawMainMenuAnimFrame(Graphics g, MainMenu m) {
        BufferedImage frame = m.getAnimator().getCurrentFrame(0);
        int x = WINDOW_WIDTH / 2 * SCALE - 50 * SCALE;
        int y = WINDOW_HEIGHT / 2 * SCALE - 13 * SCALE;
        g.drawImage(frame, x, y, null);
    }

    public static void drawWinScreenScore(Graphics g) {
        g.drawImage(Player.deathNumImg, WINDOW_WIDTH * SCALE / 2 - Player.deathNumImg.getWidth() / 2 - SCALE, WINDOW_HEIGHT * SCALE / 2 - SCALE * 32, null);
    }

    public static BufferedImage reScale(BufferedImage img, int reWidth, int reHeight) {
        BufferedImage resized = new BufferedImage(reWidth, reHeight, (img.getColorModel().hasAlpha() ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB));

        Graphics2D g = resized.createGraphics();

        g.drawImage(img, 0, 0, reWidth, reHeight, null);
        g.dispose();

        return resized;
    }
}
