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
    private int[][] testMap= {
            {9,9,9,9,9,9,9,9,9,9},
            {9,9,9,9,9,9,9,9,9,9},
            {9,9,9,9,9,9,2,0,0,0},
            {9,9,9,9,9,9,9,9,9,9},
            {0,0,0,0,0,0,0,0,0,0},
            {1,1,1,1,1,1,1,1,1,1}
    };

    public LevelManager(GamePanel game){
        this.gamePanel =game;
        //platformSprite=LoadSave.getSprite(LoadSave.PLATFORM_SPRITE);
        loadOutSideImg();
        loadLevels();
    }
    private void loadLevels(){
        testLevel=new Level(testMap);
    }
    private void loadOutSideImg() {
        BufferedImage img =LoadSave.getSprite(LoadSave.PLATFORM_SPRITE);
        platformSprite=new BufferedImage[9];
        for(int i=0; i<3;i++){
            for(int j=0; j<4;j++){
                int index=i*4+j;
                if(index>=9){
                   // break;
                }else{
                    platformSprite[index]=img.getSubimage(j*GameControl.TILE_DEFAULT_SIZE,i*GameControl.TILE_DEFAULT_SIZE,GameControl.TILE_DEFAULT_SIZE,GameControl.TILE_DEFAULT_SIZE);
                }

            }
        }
    }

    public void draw(Graphics g){
        //g.drawImage(platformSprite[8],0,0, GameControl.TILE_SIZE,GameControl.TILE_SIZE,null);
        for (int i=0;i< testLevel.getMap().length;i++){
            for(int j=0; j<testLevel.getMap()[i].length;j++){
                if(testLevel.getMap()[i][j]<9){
                    g.drawImage(platformSprite[testLevel.getMap()[i][j]],j*GameControl.TILE_SIZE,i*GameControl.TILE_SIZE, GameControl.TILE_SIZE,GameControl.TILE_SIZE,null);
                }
            }
        }
    }
    public void update(){}
}
