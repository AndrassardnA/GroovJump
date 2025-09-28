package utilz;

import java.awt.*;

public class Hitbox {
    public static Rectangle[] entityHitboxInit(int x, int y, int width, int height){
        Rectangle hitboxLeft= new Rectangle(x,y+2,1,height-4);
        Rectangle hitboxRight= new Rectangle(x+width-1,y+2,1,height-4);
        Rectangle hitboxHead= new Rectangle(x+2,y,width-4,1);
        Rectangle hitboxFeet= new Rectangle(x+2,y+height-1,width-4,1);

        return new Rectangle[]{hitboxLeft,hitboxRight,hitboxHead,hitboxFeet};
    }
    public static int[] entityHitboxUpdate(float x, float y, float width, float height){
        int leftX, leftY, rightX, rightY, headX, headY, feetX, feetY;
        float moddedX=x-width/2;
        leftX=(int)(moddedX);
        leftY=(int)(y+2);
        rightX=(int)(moddedX+width-1);
        rightY=(int)(y+2);
        headX=(int)(moddedX+2);
        headY=(int)(y);
        feetX=(int)(moddedX+2);
        feetY=(int)(y+height-1);
        return new int[]{leftX, leftY, rightX, rightY, headX, headY, feetX, feetY};
    }

}
