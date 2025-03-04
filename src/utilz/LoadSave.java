package utilz;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {
    public static BufferedImage getPlayerSprite(){
        BufferedImage img=null;
        InputStream is = LoadSave.class.getResourceAsStream("/sprites/test_Player_sprite2.png");
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

