package utilz;

import levels.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Drawer {
    private static final float scale = Constants.Sizes.SCALE;

    public static void drawPlatform(Graphics g, Platform p, BufferedImage[] platformSprite) {
        for (int i = 0; i < p.getHeight(); i++) {
            for (int j = 0; j < p.getWidth(); j++) {
                g.drawImage(platformSprite[p.getBody()[i][j]], (p.getPosX() * (int) scale + j * (int) (Constants.Sizes.TILE_DEFAULT_SIZE * scale)), p.getPosY() * (int) scale + i * (int) (Constants.Sizes.TILE_DEFAULT_SIZE * scale), (int) (Constants.Sizes.TILE_DEFAULT_SIZE * scale) + (int)scale, (int) (Constants.Sizes.TILE_DEFAULT_SIZE * scale) + (int)scale, null);
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
        g.setColor(Color.RED);
        for (HazardBlock h : hazard_arr) {
             g.drawImage(LevelManager.getCurrentLevel().getHazardFrame(), (int) (h.getPosX() * scale), (int) (h.getPosY() * scale), (int) (h.getWidth() * scale), (int) (h.getHeight() * scale), null);
        }
    }

    public static void drawFinish(Graphics g, FinishBlock finish) {
        g.drawImage(LevelManager.getCurrentLevel().getFinishFrame(), (int) (finish.getPosX()*scale), (int) (finish.getPosY() * scale), (int) (finish.getWidth()), (int) (finish.getHeight()),null);
    }

    public static void drawPlayer(Graphics g, BufferedImage image, float x, float y, float width, float height) {
        g.drawImage(image, (int)(x * scale), (int)(y * scale), (int)(width * scale), (int)(height * scale), null);
    }

    public static void drawEntityHitbox(Graphics g, Rectangle hitboxLeft, Rectangle hitboxRight, Rectangle hitboxHead, Rectangle hitboxFeet) {
        //for debug;
        g.setColor(Color.RED);
        g.drawRect(hitboxLeft.x * (int) scale, hitboxLeft.y * (int) scale, hitboxLeft.width * (int) scale, hitboxLeft.height * (int) scale);
        g.drawRect(hitboxRight.x * (int) scale, hitboxRight.y * (int) scale, hitboxRight.width * (int) scale, hitboxRight.height * (int) scale);
        g.setColor(Color.BLUE);
        g.drawRect(hitboxHead.x * (int) scale, hitboxHead.y * (int) scale, hitboxHead.width * (int) scale, hitboxHead.height * (int) scale);

        g.drawRect(hitboxFeet.x * (int) scale, hitboxFeet.y * (int) scale, hitboxFeet.width * (int) scale, hitboxFeet.height * (int) scale);
    }

    public static void renderDeathUI(Graphics g, int deaths) {
        String kiir = "Deaths: " + deaths;
        g.setFont(new Font("Arial", Font.BOLD, 13 * (int) scale));
        g.setColor(Color.black);
        g.drawString(kiir, 11 * Constants.Sizes.TILE_DEFAULT_SIZE * (int) scale, 1 * Constants.Sizes.TILE_DEFAULT_SIZE * (int) scale);
    }
}
