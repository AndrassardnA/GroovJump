package levels;

import java.awt.image.BufferedImage;

import utilz.Constants;
import utilz.Drawer;
import utilz.LoadSave;

public class LevelManager {
    private static int currentLevel;
    private BufferedImage[] platformSprite;
    private final static Level[] levels= new Level[5];
    public int xMod=0;

    //CONSTRUCTOR
    public LevelManager(){
        loadOutSideImg();
        loadLevels();
     //   BufferedImage image=LoadSave.getSprite(LoadSave.FINISH_SPRITE);
    }

    //LOADERS
    private void loadLevels(){
        for(int i=1; i<=5;i++){
            levels[i-1]=LoadSave.loadLevelData(i);
        }
        currentLevel=0;
    }
    private void loadOutSideImg() {
        BufferedImage img =LoadSave.getSprite(LoadSave.PLATFORM_SPRITE);
        int scale=Constants.Sizes.SCALE;
        img= Drawer.reScale(img,img.getWidth()*scale+(scale*4), img.getHeight()*scale+(scale*3));
        platformSprite=new BufferedImage[12];
        for(int i=0; i<3;i++){
            for(int j=0; j<4;j++){
                int index=i*4+j;
                    platformSprite[index]=img.getSubimage(j* (Constants.Sizes.TILE_DEFAULT_SIZE*scale+scale),i* (Constants.Sizes.TILE_DEFAULT_SIZE*scale+scale), Constants.Sizes.TILE_DEFAULT_SIZE*scale+scale, Constants.Sizes.TILE_DEFAULT_SIZE*scale+scale);
            }
        }
    }

    //-------------------------------------------------------------------
    private void updateLevelPos(Level level){
        Platform[] platformArr=level.getPlatforms();
        for (Platform currPlatform : platformArr) {
            currPlatform.setPosX(currPlatform.getOriginalPosX() - xMod);
        }
        HazardBlock[] hazardArr=level.getHazards();
        for (HazardBlock currHazard : hazardArr) {
            currHazard.setPosX(currHazard.getOriginalPosX() - xMod);
        }
        FinishBlock currFinish=level.getFinish();
        currFinish.setPosX(currFinish.getOriginalPosX()-xMod);
    }

    public void nextLevel(){
            if (currentLevel < levels.length - 1) {
                currentLevel++;
            } else {
                System.out.println("No more levels to load");
            }
    }

    //UPDATE
    public void update(){
        updateLevelPos(getCurrentLevel());
        getCurrentLevel().updateFinishFrame();
        getCurrentLevel().updateHazardFrame();
    }

    //GETTERS
    public BufferedImage[] getPlatformSprite(){
        return platformSprite;
    }
    public static Level getCurrentLevel(){
        return levels[currentLevel];
    }
}
