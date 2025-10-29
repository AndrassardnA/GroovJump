package levels;

import utilz.Constants;

import java.awt.*;

public class Block {
    protected int posX;
    protected int posY;
    private final int originalPosX;
    private final int originalPosY;
    protected final int width, height;

    public Block(int posX, int posY, int width, int height) {
        this.posX = posX * Constants.Sizes.TILE_DEFAULT_SIZE;
        this.posY = posY * Constants.Sizes.TILE_DEFAULT_SIZE;
        originalPosX=this.posX;
        originalPosY=this.posY;
        this.width=width;
        this.height=height;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getOriginalPosX() {
        return originalPosX;
    }

    public int getOriginalPosY() {
        return originalPosY;
    }
    public Rectangle getBounds(){
        return new Rectangle(posX,posY,width,height);
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
