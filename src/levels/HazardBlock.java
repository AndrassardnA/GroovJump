package levels;

import utilz.Constants;

import java.awt.*;

public class HazardBlock extends Block{

    public HazardBlock(int posX, int posY, int width, int height) {
        super(posX,posY,width*Constants.Sizes.TILE_SIZE,height*Constants.Sizes.TILE_SIZE);
    }
}
