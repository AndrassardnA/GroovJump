package graphics;

import levels.LevelManager;
import utilz.Constants;
import utilz.Drawer;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import static utilz.Constants.Sizes.SCALE;
import static utilz.Constants.Sizes.TILE_DEFAULT_SIZE;

public class Background {
    private static final BufferedImage preFrontGroundImg = LoadSave.getSprite(LoadSave.FORE_GROUND_SPRITE);
    private static final BufferedImage front_ground = Drawer.reScale(preFrontGroundImg, preFrontGroundImg.getWidth() * SCALE, preFrontGroundImg.getHeight() * SCALE);

    private static final BufferedImage preBackGroundImg = LoadSave.getSprite(LoadSave.BACK_GROUND_SPRITE);
    private static final BufferedImage back_ground_img = Drawer.reScale(preBackGroundImg, preBackGroundImg.getWidth() * SCALE, preBackGroundImg.getHeight() * SCALE);
    private static BufferedImage[][] back_ground = new BufferedImage[10][40];
    ;


    private static final int frontX = -2 * TILE_DEFAULT_SIZE * SCALE;
    private static final int frontY = Constants.Sizes.WINDOW_HEIGHT * SCALE - TILE_DEFAULT_SIZE * 2 * SCALE;

    public static void drawFront_Ground(Graphics g) {
        g.drawImage(front_ground, (int) (frontX - LevelManager.xMod * SCALE * 1.2f), frontY, null);
    }

    public static void drawBackGround(Graphics g) {
        int starterX = SCALE * TILE_DEFAULT_SIZE * 3;
        for (int y = 0; y < back_ground.length; y++) {
            for (int x = 0; x < back_ground[y].length; x++) {
                g.drawImage(back_ground[y][x], x * TILE_DEFAULT_SIZE * SCALE - starterX - LevelManager.xMod, y * TILE_DEFAULT_SIZE * SCALE, null);
            }
        }
    }

    public static void sortBackgroundTiles() {
        Random r = new Random();
        BufferedImage img;
        int scaledTileSize = TILE_DEFAULT_SIZE * SCALE;
        for (int y = 0; y < back_ground.length; y++) {
            for (int x = 0; x < back_ground[y].length; x++) {
                switch (r.nextInt(32)) {
                    case 0 -> img = back_ground_img.getSubimage(0, scaledTileSize, scaledTileSize, scaledTileSize);
                    case 1 -> img = back_ground_img.getSubimage(0, 2 * scaledTileSize, scaledTileSize, scaledTileSize);
                    case 2 -> img = back_ground_img.getSubimage(scaledTileSize, 0, scaledTileSize, scaledTileSize);
                    case 3 ->
                            img = back_ground_img.getSubimage(scaledTileSize, scaledTileSize, scaledTileSize, scaledTileSize);
                    case 4 ->
                            img = back_ground_img.getSubimage(scaledTileSize, 2 * scaledTileSize, scaledTileSize, scaledTileSize);
                    case 5 -> img = back_ground_img.getSubimage(2 * scaledTileSize, 0, scaledTileSize, scaledTileSize);
                    case 6 ->
                            img = back_ground_img.getSubimage(2 * scaledTileSize, scaledTileSize, scaledTileSize, scaledTileSize);
                    case 7 ->
                            img = back_ground_img.getSubimage(2 * scaledTileSize, 2 * scaledTileSize, scaledTileSize, scaledTileSize);
                    default -> img = back_ground_img.getSubimage(0, 0, scaledTileSize, scaledTileSize);
                }
                img = Drawer.reScale(img, img.getWidth() + SCALE, img.getHeight() + SCALE);
                back_ground[y][x] = img;
            }
        }
    }

}
