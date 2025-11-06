package levels;

import java.awt.image.BufferedImage;

import entityes.Player;
import graphics.Background;
import utilz.*;

import static main.GameControl.gamestate;
import static utilz.Constants.Sizes.SCALE;
import static utilz.Constants.Sizes.TILE_DEFAULT_SIZE;

public class LevelManager {
    private static int currentLevel;
    private static BufferedImage[] platformSprite;
    private final static Level[] levels = new Level[5];
    public static int xMod = 0;

    //CONSTRUCTOR
    public LevelManager() {
        loadOutSideImg();
        loadLevels();
        Background.sortBackgroundTiles();
    }

    //LOADERS
    private void loadLevels() {
        for (int i = 1; i <= 5; i++) {
            levels[i - 1] = LoadSave.loadLevelData(i);
        }
        currentLevel = 0;
    }

    private static void loadOutSideImg() {
        BufferedImage img = LoadSave.getSprite(LoadSave.PLATFORM_SPRITE);
        img = Drawer.reScale(img, img.getWidth() * SCALE + (SCALE * 4), img.getHeight() * SCALE + (SCALE * 3));
        platformSprite = new BufferedImage[12];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                int index = i * 4 + j;
                platformSprite[index] = img.getSubimage(j * (TILE_DEFAULT_SIZE * SCALE + SCALE), i * (TILE_DEFAULT_SIZE * SCALE + SCALE), TILE_DEFAULT_SIZE * SCALE + SCALE, TILE_DEFAULT_SIZE * SCALE + SCALE);
            }
        }
    }

    //-------------------------------------------------------------------
    private void updateLevelPos(Level level) {
        Platform[] platformArr = level.getPlatforms();
        for (Platform currPlatform : platformArr) {
            currPlatform.setPosX(currPlatform.getOriginalPosX() - xMod);
        }
        HazardBlock[] hazardArr = level.getHazards();
        for (HazardBlock currHazard : hazardArr) {
            currHazard.setPosX(currHazard.getOriginalPosX() - xMod);
        }
        FinishBlock currFinish = level.getFinish();
        currFinish.setPosX(currFinish.getOriginalPosX() - xMod);
    }

    public static void nextLevel() {
        if (currentLevel < levels.length - 1) {
            currentLevel++;
        } else {
            gamestate = GameState.WIN;
        }
    }

    //UPDATE
    public void update() {
        updateLevelPos(getCurrentLevel());
        getCurrentLevel().updateFinishFrame();
        getCurrentLevel().updateHazardFrame();
    }

    //GETTERS
    public static BufferedImage[] getPlatformSprite() {
        return platformSprite;
    }

    public static Level getCurrentLevel() {
        return levels[currentLevel];
    }

    public static void resetLevels() {
        currentLevel = 0;
        Player.deaths = 0;
        Player.deathNumImg = MyString.getMyNumImg(Player.deaths);
    }
}
