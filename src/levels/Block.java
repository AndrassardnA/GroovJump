package levels;


import java.awt.*;

import static utilz.Constants.Sizes.TILE_DEFAULT_SIZE;

public class Block {
    protected int posX;
    protected int posY;
    private final int originalPosX;

    protected final int width, height;

    public Block(int posX, int posY, int width, int height) {
        this.posX = posX * TILE_DEFAULT_SIZE;
        this.posY = posY * TILE_DEFAULT_SIZE;
        originalPosX = this.posX;
        this.width = width;
        this.height = height;
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

    public Rectangle getBounds() {
        return new Rectangle(posX, posY, width, height);
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
