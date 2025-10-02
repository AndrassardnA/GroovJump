package levels;

import utilz.Constants;

public class FinishBlock extends Block{
    public FinishBlock(int posX, int posY, int width, int height) {
        super(posX,posY,width* Constants.Sizes.TILE_DEFAULT_SIZE,height*Constants.Sizes.TILE_DEFAULT_SIZE);
    }

}
