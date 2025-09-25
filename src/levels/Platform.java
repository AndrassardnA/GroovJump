package levels;

import main.GameControl;
import utilz.Constants;

import java.awt.*;

public class Platform extends Block{
    private int[][] body;


    public Platform(int posX, int posY, int bodyWidth, int bodyHeight) {
        super(posX,posY,bodyWidth*Constants.Sizes.TILE_DEFAULT_SIZE,bodyHeight*Constants.Sizes.TILE_DEFAULT_SIZE);
        fillPlatformBody(bodyWidth, bodyHeight);
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

    public int getHeight() {
        return body.length;
    }

    public int getWidth() {
        return body[0].length;
    }
    public int[][] getBody() {
        return body;
    }
}
