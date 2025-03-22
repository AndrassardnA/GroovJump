package levels;

import java.awt.*;
import java.awt.image.BufferedImage;

import main.GameControl;
import main.GamePanel;
import utilz.LoadSave;

public class LevelManager {
    private GamePanel gamePanel;
    private BufferedImage platformSprite[];
    private Level testLevel;
    private Platform platform0=new Platform(0,9,16,1);
    private Platform platform1=new Platform(13,8,5,4);
    private Platform platform2=new Platform(0,0,16,2);
    private  Platform platform3=new Platform(12,6,1,1);
    private Platform[] platforms = {platform0,platform1,platform2,platform3};

    public LevelManager(GamePanel game){
        this.gamePanel =game;
        loadOutSideImg();
        loadLevels();
    }
    private void loadLevels(){
        testLevel=new Level(platforms);
    }
    private void loadOutSideImg() {
        BufferedImage img =LoadSave.getSprite(LoadSave.PLATFORM_SPRITE);
        platformSprite=new BufferedImage[12];
        for(int i=0; i<3;i++){
            for(int j=0; j<4;j++){
                int index=i*4+j;
                    platformSprite[index]=img.getSubimage(j*GameControl.TILE_DEFAULT_SIZE,i*GameControl.TILE_DEFAULT_SIZE,GameControl.TILE_DEFAULT_SIZE,GameControl.TILE_DEFAULT_SIZE);
            }
        }
    }
    public void drawPlatform(Graphics g, Platform p){
        for(int i=0; i<p.getHeight();i++){
            for(int j=0; j<p.getWidth();j++){
                g.drawImage(platformSprite[p.getBody()[i][j]],p.getPosX()+j*GameControl.TILE_SIZE,p.getPosY()+i*GameControl.TILE_SIZE,GameControl.TILE_SIZE,GameControl.TILE_SIZE,null);
            }
        }
    }

    public Level getCurrentLevel(){
        return testLevel;
    }
    public void draw(Graphics g){
        for (int i=0;i<testLevel.getPlatforms().length;i++){
            drawPlatform(g,testLevel.getPlatforms()[i]);
            //for debug
            g.setColor(Color.RED);
            g.drawRect(testLevel.getPlatforms()[i].getBounds().x,testLevel.getPlatforms()[i].getBounds().y,testLevel.getPlatforms()[i].getBounds().width,testLevel.getPlatforms()[i].getBounds().height);
        }
    }

    public void update(){}
}
