package utilz;

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
    public static final String PLAYER_SPRITE="sprites/test_Player_sprite2.png";
    public static final String PLATFORM_SPRITE="sprites/ground_test_sprite.png";
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
        InputStream is = LoadSave.class.getResourceAsStream("/level_files/lvl-" + level_num + ".txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        try {
            String line = reader.readLine();
            while (line!=null) {
                String[] datas = line.split(" ");
                Platform platform = new Platform(Integer.parseInt(datas[0]), Integer.parseInt(datas[1]), Integer.parseInt(datas[2]), Integer.parseInt(datas[3]));
                platforms.add(platform);
                line= reader.readLine();
            }
            Platform[] platformsArr= new Platform[platforms.size()];
            int counter =0;
            for(Platform p : platforms){
                platformsArr[counter]=p;
                counter++;
            }
            level = new Level(platformsArr);
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

