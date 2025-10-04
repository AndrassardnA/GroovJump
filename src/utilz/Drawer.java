package utilz;

import levels.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Drawer {
    private static final int scale = Constants.Sizes.SCALE;

    public static void drawPlatform(Graphics g, Platform p, BufferedImage[] platformSprite) {
        for (int i = 0; i < p.getHeight(); i++) {
            for (int j = 0; j < p.getWidth(); j++) {
                int tileSize=Constants.Sizes.TILE_DEFAULT_SIZE * scale;
                int x= p.getPosX() * scale + j * (tileSize);
                int y = p.getPosY() * scale + i * (tileSize);
                int windowsWidth=Constants.Sizes.WINDOW_WIDTH*scale;
                if(x>-tileSize&&x<windowsWidth+tileSize) {
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
        for (HazardBlock h : hazard_arr) {
            int x=h.getPosX() * scale;
            int y=h.getPosY() * scale;
            int tileSize=Constants.Sizes.TILE_DEFAULT_SIZE * scale;
            int windowsWidth=Constants.Sizes.WINDOW_WIDTH*scale;
            if(x>-tileSize&&x<windowsWidth+tileSize) {
                g.drawImage(LevelManager.getCurrentLevel().getHazardFrame(), x, y, null);
            }
        }
    }

    public static void drawFinish(Graphics g, FinishBlock finish) {
        int x=finish.getPosX() * scale;
        int y=finish.getPosY() * scale;
        int tileSize=Constants.Sizes.TILE_DEFAULT_SIZE * scale;
        int windowsWidth=Constants.Sizes.WINDOW_WIDTH*scale;
        if(x>-tileSize&&x<windowsWidth+tileSize) {
            g.drawImage(LevelManager.getCurrentLevel().getFinishFrame(), x, y, null);
        }
    }

    public static void drawPlayer(Graphics g, BufferedImage image, float x, float y) {
        g.drawImage(image, (int)(x * scale), (int)(y * scale), null);
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

    public static void renderDeathUI(Graphics g, int deaths) {
        String kiir = "Deaths: " + deaths;
        g.setFont(new Font("Arial", Font.BOLD, 13 * scale));
        g.setColor(Color.black);
        g.drawString(kiir, 11 * Constants.Sizes.TILE_DEFAULT_SIZE * scale, 1 * Constants.Sizes.TILE_DEFAULT_SIZE * scale);
    }

    public static BufferedImage reScale(BufferedImage img,int reWidth, int reHeight) {
        //Dimension newDimension= new Dimension(reWidth,reHeight);

        BufferedImage resized = new BufferedImage(reWidth,reHeight,(img.getColorModel().hasAlpha()? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB));

        Graphics2D g = resized.createGraphics();

        g.drawImage(img,0,0,reWidth, reHeight,null);
        g.dispose();

        return resized;
    }
}
