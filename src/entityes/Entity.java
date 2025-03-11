package entityes;

import java.awt.*;

public abstract class Entity {
    protected float x,y;
    protected int width, height;
    protected Rectangle hitboxBody, hitboxFeet, hitboxHead;
    public Entity(float x, float y,int width,int height){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        initHitbox();
    }

    private void initHitbox() {
        hitboxBody=new Rectangle((int)x,(int)y+5,width,height-10);
        hitboxHead=new Rectangle((int)x+3,(int)y,width-6,5);
        hitboxFeet=new Rectangle((int)x+3,(int)y+height-5,width-6,5);
    }
    protected void updateHitbox(){
        hitboxBody.x=(int)x;
        hitboxBody.y=(int)y+5;

        hitboxHead.x=(int)x+3;
        hitboxHead.y=(int)y;

        hitboxFeet.x=(int)x+3;
        hitboxFeet.y=(int)y+height-5;
    }
    public Rectangle getHitboxBody(){
        return hitboxBody;
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
        g.drawRect(hitboxBody.x, hitboxBody.y, hitboxBody.width, hitboxBody.height);
        g.setColor(Color.BLUE);
        g.drawRect(hitboxHead.x,hitboxHead.y, hitboxHead.width,hitboxHead.height);

        g.drawRect(hitboxFeet.x, hitboxFeet.y, hitboxFeet.width, hitboxFeet.height);
    }
}
