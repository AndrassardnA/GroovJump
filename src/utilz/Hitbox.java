package utilz;

import java.awt.*;

public class Hitbox {
    public static Rectangle[] entityHitboxInit(int x, int y, int width, int height){
        Rectangle hitboxLeft= new Rectangle(x,y+2,1,height-2);
        Rectangle hitboxRight= new Rectangle(x+width-1,y+2,1,height-2);
        Rectangle hitboxHead= new Rectangle(x+2,y,width-4,1);
        Rectangle hitboxFeet= new Rectangle(x+2,y+height,width-4,1);

        return new Rectangle[]{hitboxLeft,hitboxRight,hitboxHead,hitboxFeet};
    }
    public static int[] entityHitboxUpdate(float x, float y, float width, float height){
        int leftX, leftY, rightX, rightY, headX, headY, feetX, feetY;
        leftX=(int)(x);
        leftY=(int)(y+2);
        rightX=(int)(x+width-1);
        rightY=(int)(y+2);
        headX=(int)(x+2);
        headY=(int)(y);
        feetX=(int)(x+2);
        feetY=(int)(y+height);
        return new int[]{leftX, leftY, rightX, rightY, headX, headY, feetX, feetY};
    }

}
