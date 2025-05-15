package levels;

import utilz.Constants;

import java.awt.*;

public class HazardBlock {
    private int posX;
    private int posY;
    private final int originalPosX;
    private final int originalPosY;
    private final int size=Constants.Sizes.TILE_SIZE;
    private int width, height;

    public HazardBlock(int posX, int posY, int width, int height) {
        this.posX = posX * Constants.Sizes.TILE_SIZE;
        this.posY = posY * Constants.Sizes.TILE_SIZE;
        originalPosX=this.posX;
        originalPosY=this.posY;
        this.width=width*size;
        this.height=height*size;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
    public Rectangle getBounds(){
        return new Rectangle(posX,posY,width,height);
    }

    public int getSize() {
        return size;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getOriginalPosX() {
        return originalPosX;
    }
    public int getOriginalPosY() {
        return originalPosY;
    }
}
