package levels;

import utilz.Constants;

import java.awt.*;

public class HazardBlock extends Block{
    public HazardBlock(int posX, int posY, int width, int height) {
        super(posX,posY,width*Constants.Sizes.TILE_DEFAULT_SIZE,height*Constants.Sizes.TILE_DEFAULT_SIZE);
    }
    @Override
    public Rectangle getBounds(){
        return new Rectangle(posX+5,posY+5,width-10,height-5);
    }
}
