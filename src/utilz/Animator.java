package utilz;

import java.awt.image.BufferedImage;

import static utilz.Constants.PlayerConstants.*;

public class Animator {
    private final BufferedImage[][] animations;
    private final BufferedImage animSprite;
    private int animTimer=0; //incrases by frame;
    private int animIndex=0;
    private static final int animSpeed=Constants.Game.FPS/19; //means FPS/(FPS/x) means x frames per second

    public Animator(BufferedImage sprite, int animTypeCount, int animFrameCount){
        this.animations=new BufferedImage[animTypeCount][animFrameCount];
        this.animSprite=sprite;
        loadAnimation();
    }

    private void loadAnimation() {
        int frameWidth=animSprite.getWidth()/animations[0].length;
        int frameHeight=animSprite.getHeight()/animations.length;
        for (int i=0; i< animations.length;i++){
            for (int j=0; j<animations[i].length;j++){
                animations[i][j]=animSprite.getSubimage(j*frameWidth,i*frameHeight,frameWidth,frameHeight);
            }
        }
    }

    public void updateAnimLoop(int action) {
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
