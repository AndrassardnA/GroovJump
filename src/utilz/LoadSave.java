package utilz;

import levels.FinishBlock;
import levels.HazardBlock;
import levels.Level;
import levels.Platform;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LoadSave {
    public static final String PLAYER_SPRITE="sprites/Player_Sprite2.png";
    public static final String PLATFORM_SPRITE="sprites/ground_sprite.png";
    public static final String FINISH_SPRITE="sprites/finish_flag_sprite.png";
    public static final String HAZARD_SPRITE="sprites/spike_sprite.png";
    public static BufferedImage getSprite(String filePath){
        BufferedImage img=null;
        InputStream is = LoadSave.class.getResourceAsStream("/" + filePath);
        try {
            img= ImageIO.read(is);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally {
            try {
                is.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        return img;
    }
    public static Level loadLevelData(int level_num){
        Level level = null;
        ArrayList<Platform> platforms = new ArrayList<>();
        ArrayList<HazardBlock> hazards = new ArrayList<>();
        FinishBlock finish= new FinishBlock(0,0,1,1);
        InputStream is = LoadSave.class.getResourceAsStream("/level_files/lvl-" + level_num + ".txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        try {
            String line = reader.readLine();
            while (line!=null) {
                String[] datas = line.split(" ");
                if(datas[0].equals("F")){
                    finish= new FinishBlock(Integer.parseInt(datas[1]),Integer.parseInt(datas[2]),Integer.parseInt(datas[3]),Integer.parseInt(datas[4]));
                }else if(datas[0].equals("X")) {
                    HazardBlock hazard= new HazardBlock(Integer.parseInt(datas[1]),Integer.parseInt(datas[2]),Integer.parseInt(datas[3]),Integer.parseInt(datas[4]));
                    hazards.add(hazard);
                }
                else{
                    Platform platform = new Platform(Integer.parseInt(datas[0]), Integer.parseInt(datas[1]), Integer.parseInt(datas[2]), Integer.parseInt(datas[3]));
                    platforms.add(platform);
                }

                line= reader.readLine();
            }
            Platform[] platformsArr= new Platform[platforms.size()];
            HazardBlock[] hazardsArr = new HazardBlock[hazards.size()];
            int counter =0;
            for(Platform p : platforms){
                platformsArr[counter]=p;
                counter++;
            }
            counter=0;
            for(HazardBlock h : hazards){
                hazardsArr[counter]=h;
                counter++;
            }
            level = new Level(platformsArr,hazardsArr,finish);
        }catch (IOException e){
            e.printStackTrace();
        }
        finally {
            try {
                reader.close();
                is.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        return level;
    }
}

