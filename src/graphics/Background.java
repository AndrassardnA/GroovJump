package graphics;

import levels.LevelManager;
import utilz.Constants;
import utilz.Drawer;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Background {
    private static  final int scale= Constants.Sizes.SCALE;
    private static final BufferedImage preFrontGroundImg= LoadSave.getSprite(LoadSave.FORE_GROUND_SPRITE);
    private static final BufferedImage front_ground= Drawer.reScale(preFrontGroundImg,preFrontGroundImg.getWidth()*scale, preFrontGroundImg.getHeight()*scale);

private static final BufferedImage preBackGroundImg= LoadSave.getSprite(LoadSave.BACK_GROUND_SPRITE);
    private static final BufferedImage back_ground_img = Drawer.reScale(preBackGroundImg,preBackGroundImg.getWidth()*scale, preBackGroundImg.getHeight()*scale);
    private static BufferedImage[][] back_ground=new BufferedImage[10][40];;


    private static final int frontX=-2*Constants.Sizes.TILE_DEFAULT_SIZE*scale;
    private static final int frontY=Constants.Sizes.WINDOW_HEIGHT*scale-Constants.Sizes.TILE_DEFAULT_SIZE*2*scale;
    public static void drawFront_Ground(Graphics g){
        g.drawImage(front_ground,(int)(frontX- LevelManager.xMod*scale*1.2f),frontY,null);
    }
public static void drawBackGround(Graphics g){
        int starterX=scale*Constants.Sizes.TILE_DEFAULT_SIZE*3;
        for(int y=0;y<back_ground.length;y++){
            for(int x=0;x<back_ground[y].length;x++){
                g.drawImage(back_ground[y][x],(int)(x*Constants.Sizes.TILE_DEFAULT_SIZE*scale-starterX-LevelManager.xMod),y*Constants.Sizes.TILE_DEFAULT_SIZE*scale,null);
            }
        }
}
    public static void sortBackgroundTiles(){
        Random r = new Random();
        BufferedImage img;
        for(int y=0;y<back_ground.length;y++){
            for(int x=0;x<back_ground[y].length;x++){
                switch (r.nextInt(32)){
                    /*blue cristal*/    case 0->img=back_ground_img.getSubimage(0* (Constants.Sizes.TILE_DEFAULT_SIZE*scale),1* (Constants.Sizes.TILE_DEFAULT_SIZE*scale), Constants.Sizes.TILE_DEFAULT_SIZE*scale, Constants.Sizes.TILE_DEFAULT_SIZE*scale);
                    /*red cristal*/     case 1->img=back_ground_img.getSubimage(0* (Constants.Sizes.TILE_DEFAULT_SIZE*scale),2* (Constants.Sizes.TILE_DEFAULT_SIZE*scale), Constants.Sizes.TILE_DEFAULT_SIZE*scale, Constants.Sizes.TILE_DEFAULT_SIZE*scale);
                    /*big stone*/       case 2->img=back_ground_img.getSubimage(1* (Constants.Sizes.TILE_DEFAULT_SIZE*scale),0* (Constants.Sizes.TILE_DEFAULT_SIZE*scale), Constants.Sizes.TILE_DEFAULT_SIZE*scale, Constants.Sizes.TILE_DEFAULT_SIZE*scale);
                    /*few stone*/       case 3->img=back_ground_img.getSubimage(1* (Constants.Sizes.TILE_DEFAULT_SIZE*scale),1* (Constants.Sizes.TILE_DEFAULT_SIZE*scale), Constants.Sizes.TILE_DEFAULT_SIZE*scale, Constants.Sizes.TILE_DEFAULT_SIZE*scale);
                    /*cracks*/          case 4->img=back_ground_img.getSubimage(1* (Constants.Sizes.TILE_DEFAULT_SIZE*scale),2* (Constants.Sizes.TILE_DEFAULT_SIZE*scale), Constants.Sizes.TILE_DEFAULT_SIZE*scale, Constants.Sizes.TILE_DEFAULT_SIZE*scale);
                    /*three stones*/    case 5->img=back_ground_img.getSubimage(2* (Constants.Sizes.TILE_DEFAULT_SIZE*scale),0* (Constants.Sizes.TILE_DEFAULT_SIZE*scale), Constants.Sizes.TILE_DEFAULT_SIZE*scale, Constants.Sizes.TILE_DEFAULT_SIZE*scale);
                    /*lot stones*/      case 6->img=back_ground_img.getSubimage(2* (Constants.Sizes.TILE_DEFAULT_SIZE*scale),1* (Constants.Sizes.TILE_DEFAULT_SIZE*scale), Constants.Sizes.TILE_DEFAULT_SIZE*scale, Constants.Sizes.TILE_DEFAULT_SIZE*scale);
                    /*medium*/          case 7->img=back_ground_img.getSubimage(2* (Constants.Sizes.TILE_DEFAULT_SIZE*scale),2* (Constants.Sizes.TILE_DEFAULT_SIZE*scale), Constants.Sizes.TILE_DEFAULT_SIZE*scale, Constants.Sizes.TILE_DEFAULT_SIZE*scale);
                    /*nothing*/         default->img=back_ground_img.getSubimage(0* (Constants.Sizes.TILE_DEFAULT_SIZE*scale),0* (Constants.Sizes.TILE_DEFAULT_SIZE*scale), Constants.Sizes.TILE_DEFAULT_SIZE*scale, Constants.Sizes.TILE_DEFAULT_SIZE*scale);
                }
                img=Drawer.reScale(img,img.getWidth()+scale,img.getHeight()+scale);
                back_ground[y][x]=img;
            }
        }
    }

}
