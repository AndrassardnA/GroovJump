package levels;

import main.GameControl;
import utilz.Constants;

import java.awt.*;

public class Platform {
    private int posX;
    private int posY;
    private int[][] body;
    private final int originalPosX;
    private final int originalPosY;


    public Platform(int posX, int posY, int bodyWidth, int bodyHeight) {
        this.posX = posX * Constants.Sizes.TILE_SIZE;
        this.posY = posY * Constants.Sizes.TILE_SIZE;
        fillPlatformBody(bodyWidth, bodyHeight);
        originalPosX=this.posX;
        originalPosY=this.posY;
    }

    private void fillPlatformBody(int bodyWidth, int bodyHeight) {
        body = new int[bodyHeight][bodyWidth];
        for (int i = 0; i < bodyHeight; i++) {
            for (int j = 0; j < bodyWidth; j++) {
                //1*1-es
                if(bodyWidth==1 && bodyHeight==1){
                    body[i][j]=8;
                }
                //1-es szélesség teteje alja
                else if(bodyWidth==1 && i==0){
                    body[i][j]=3;
                }
                else if(bodyWidth==1 && i==bodyHeight-1){
                    body[i][j]=5;
                }
                //Teteje
                else if (i == 0 && !(j == 0 || j == bodyWidth - 1)) {
                    body[i][j] = 0;
                }
                //Teteje sarkok
                else if (i == 0 && j == 0) {
                    body[i][j] = 2;
                }
                else if (i == 0 && j == bodyWidth - 1) {
                    body[i][j] = 9;
                }
                //Oldalak
                else if (i > 0 && i < bodyHeight - 1 && j == 0) {
                    body[i][j] = 4;
                }
                else if (i > 0 && i < bodyHeight - 1 && j == bodyWidth - 1) {
                    body[i][j] = 10;
                }
                //Alja sarkok
                else if (i == bodyHeight - 1 && j == 0) {
                    body[i][j] = 6;
                }
                else if (i == bodyHeight - 1 && j == bodyWidth - 1) {
                    body[i][j] = 11;
                }
                //Alja
                else if (i == bodyHeight - 1 && j>0 && j<bodyWidth-1) {
                    body[i][j] = 7;
                }
                //Belseje
                else {
                    body[i][j] = 1;
                }
            }
        }
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

    public int getHeight() {
        return body.length;
    }

    public int getWidth() {
        return body[0].length;
    }
    public int[][] getBody() {
        return body;
    }
    public Rectangle getBounds(){
        return new Rectangle(posX,posY,body[0].length* Constants.Sizes.TILE_SIZE,body.length* Constants.Sizes.TILE_SIZE);
    }

    public int getOriginalPosX() {
        return originalPosX;
    }

    public int getOriginalPosY() {
        return originalPosY;
    }
}
