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
    private Level testLevel;
    private static Level[] levels= new Level[5];
    private Platform platform0=new Platform(0,9,16,1);
    private Platform platform1=new Platform(13,8,5,4);
    private Platform platform2=new Platform(0,0,16,2);
    private  Platform platform3=new Platform(12,6,1,1);
    private Platform[] platforms = {platform0,platform1,platform2,platform3};
    public int xMod=0;

    public LevelManager(){
        loadOutSideImg();
        loadLevels();
    }
    private void loadLevels(){
        testLevel=new Level(platforms);
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
    }

    public static Level getCurrentLevel(){
        return levels[currentLevel];
    }
    public void setCurrentLevel(int num){
        currentLevel=num;
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
            g.setColor(Color.RED);
            g.drawRect(getCurrentLevel().getPlatforms()[i].getBounds().x,getCurrentLevel().getPlatforms()[i].getBounds().y,getCurrentLevel().getPlatforms()[i].getBounds().width,getCurrentLevel().getPlatforms()[i].getBounds().height);
        }
    }

    public void update(){
        updateLevelPos(getCurrentLevel());
    }
}
