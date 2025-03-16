package utilz;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

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
}

