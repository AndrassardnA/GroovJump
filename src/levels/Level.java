package levels;

import main.GameControl;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Level {
    private Platform[] platforms;

    public Level(Platform[] platforms) {
        this.platforms = platforms;
    }
    public Platform[] getPlatforms(){
        return platforms;
    }

}
