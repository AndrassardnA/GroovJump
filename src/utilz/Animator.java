package utilz;

import java.awt.image.BufferedImage;

import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.PlayerConstants.IDLE;

public class Animator {
    private final BufferedImage[][] animations;
    private final BufferedImage animSprite;
    private static int animTimer=0; //incrases by frame;
    private static int animIndex;
    private static final int animSpeed=Constants.Game.FPS/6; //means FPS/(FPS/x) means x frames per second

    public Animator(BufferedImage sprite, int animTypeCount, int animFrameCount){
        this.animations=new BufferedImage[animTypeCount][animFrameCount];
        this.animSprite=sprite;
        loadAnimation();
    }

    private void loadAnimation() {
        BufferedImage img= animSprite;
        for (int i=0; i< animations.length;i++){
            for (int j=0; j<animations[i].length;j++){
                animations[i][j]=img.getSubimage(j*16,i*16,16,16);
            }
        }
    }

    public static void updateAnimLoop(int action) {
        animTimer++;

        if(animTimer>=animSpeed){
            animTimer=0;
            animIndex++;
            if(animIndex>=getSpritesAmount(action)){
                animIndex=0;
            }
        }
    }

    public BufferedImage getCurrentFrame(int action){
        return animations[action][animIndex];
    }
}
