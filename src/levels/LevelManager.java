package levels;

import java.awt.*;
import java.awt.image.BufferedImage;

import entityes.Player;
import main.GameControl;
import utilz.Constants;
import utilz.LoadSave;

public class LevelManager {
    private static int currentLevel;
    private BufferedImage platformSprite[];
    private static Level[] levels= new Level[5];
    public int xMod=0;

    public LevelManager(){
        loadOutSideImg();
        loadLevels();
    }
    private void loadLevels(){
        for(int i=1; i<=5;i++){
            levels[i-1]=LoadSave.loadLevelData(i);
        }
        currentLevel=0;
    }
    private void loadOutSideImg() {
        BufferedImage img =LoadSave.getSprite(LoadSave.PLATFORM_SPRITE);
        platformSprite=new BufferedImage[12];
        for(int i=0; i<3;i++){
            for(int j=0; j<4;j++){
                int index=i*4+j;
                    platformSprite[index]=img.getSubimage(j* Constants.Sizes.TILE_DEFAULT_SIZE,i* Constants.Sizes.TILE_DEFAULT_SIZE, Constants.Sizes.TILE_DEFAULT_SIZE, Constants.Sizes.TILE_DEFAULT_SIZE);
            }
        }
    }
    public void drawPlatform(Graphics g, Platform p){
        for(int i=0; i<p.getHeight();i++){
            for(int j=0; j<p.getWidth();j++){
                g.drawImage(platformSprite[p.getBody()[i][j]],(p.getPosX()+j* Constants.Sizes.TILE_SIZE),p.getPosY()+i* Constants.Sizes.TILE_SIZE, Constants.Sizes.TILE_SIZE+2, Constants.Sizes.TILE_SIZE+2,null);
            }
        }
    }
    private void updateLevelPos(Level level){
        Platform[] platformArr=level.getPlatforms();
        for(int i=0; i<platformArr.length;i++){
            Platform currPlatform=platformArr[i];
            currPlatform.setPosX(currPlatform.getOriginalPosX()-xMod);
        }
        HazardBlock[] hazardArr=level.getHazards();
        for(int i=0; i<hazardArr.length;i++){
            HazardBlock currHazard=hazardArr[i];
            currHazard.setPosX(currHazard.getOriginalPosX()-xMod);
        }
        FinishBlock currFinish=level.getFinish();
        currFinish.setPosX(currFinish.getOriginalPosX()-xMod);
    }

    public static Level getCurrentLevel(){
        return levels[currentLevel];
    }
    public void nextLevel(){
        if(currentLevel<levels.length-1){
            currentLevel++;
        }
        else{
            System.out.println("No more levels to load");
        }
    }
    public void draw(Graphics g){
        for (int i=0;i<getCurrentLevel().getPlatforms().length;i++){
            drawPlatform(g,getCurrentLevel().getPlatforms()[i]);
            //for debug
           /* g.setColor(Color.RED);
            g.drawRect(getCurrentLevel().getPlatforms()[i].getBounds().x,getCurrentLevel().getPlatforms()[i].getBounds().y,getCurrentLevel().getPlatforms()[i].getBounds().width,getCurrentLevel().getPlatforms()[i].getBounds().height);*/
        }
        getCurrentLevel().drawHazard(g);
        getCurrentLevel().drawFinish(g);
    }

    public void update(){
        updateLevelPos(getCurrentLevel());
    }
}
