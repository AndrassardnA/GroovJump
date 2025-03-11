package entityes;

import java.awt.*;

public abstract class Entity {
    protected float x,y;
    protected int width, height;
    protected Rectangle hitboxRight, hitboxLeft,  hitboxFeet, hitboxHead;
    public Entity(float x, float y,int width,int height){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        initHitbox();
    }

    private void initHitbox() {
        hitboxLeft=new Rectangle((int)x,(int)y+5,5,height-10);
        hitboxRight=new Rectangle((int)x+width-5,(int)y+5,5,height-10);
        hitboxHead=new Rectangle((int)x+3,(int)y,width-6,5);
        hitboxFeet=new Rectangle((int)x+3,(int)y+height-5,width-6,5);
    }
    protected void updateHitbox(){
        hitboxRight.x=(int)x+width-5;
        hitboxRight.y=(int)y+5;

        hitboxLeft.x=(int)x;
        hitboxLeft.y=(int)y+5;

        hitboxHead.x=(int)x+3;
        hitboxHead.y=(int)y;

        hitboxFeet.x=(int)x+3;
        hitboxFeet.y=(int)y+height-5;
    }
    public Rectangle getHitboxLeft(){
        return hitboxLeft;
    }
    public Rectangle getHitboxRight(){
        return hitboxRight;
    }
    public Rectangle getHitboxHead(){
        return hitboxHead;
    }
    public Rectangle getHitboxFeet(){
        return hitboxFeet;
    }
    protected void drawHitbox(Graphics g){
        //for debug;
        g.setColor(Color.RED);
        g.drawRect(hitboxLeft.x, hitboxLeft.y, hitboxLeft.width, hitboxLeft.height);
        g.drawRect(hitboxRight.x, hitboxRight.y, hitboxRight.width, hitboxRight.height);
        g.setColor(Color.BLUE);
        g.drawRect(hitboxHead.x,hitboxHead.y, hitboxHead.width,hitboxHead.height);

        g.drawRect(hitboxFeet.x, hitboxFeet.y, hitboxFeet.width, hitboxFeet.height);
    }
}
