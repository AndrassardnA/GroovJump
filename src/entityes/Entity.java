package entityes;

import java.awt.*;

public abstract class Entity {
    protected float x,y;
    protected int width, height;
    protected Rectangle hitboxRight, hitboxLeft,  hitboxFeet, hitboxHead;
    protected int modRightHitx, modRightHity, modLeftHitx, modLeftHity,modFeetHitx,modFeetHity, modHeadHitx,modHeadHity;
    public Entity(float x, float y,int width,int height){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        initHitbox();
    }

    private void initHitbox() {
        modLeftHitx=0;
        modLeftHity=5;
        modRightHitx=width-5;
        modRightHity=5;
        modHeadHitx=3;
        modHeadHity=0;
        modFeetHitx=3;
        modFeetHity=height-5;

        hitboxLeft=new Rectangle((int)x+modLeftHitx,(int)y+modLeftHity,5,height-10);
        hitboxRight=new Rectangle((int)x+modRightHitx,(int)y+modRightHity,5,height-10);
        hitboxHead=new Rectangle((int)x+modHeadHitx,(int)y+modHeadHity,width-6,5);
        hitboxFeet=new Rectangle((int)x+modFeetHitx,(int)y+modFeetHity,width-6,5);
    }
    protected void updateHitbox(){
        hitboxRight.x=(int)x+modRightHitx;
        hitboxRight.y=(int)y+modRightHity;

        hitboxLeft.x=(int)x+modLeftHitx;
        hitboxLeft.y=(int)y+modLeftHity;

        hitboxHead.x=(int)x+modHeadHitx;
        hitboxHead.y=(int)y+modHeadHity;

        hitboxFeet.x=(int)x+modFeetHitx;
        hitboxFeet.y=(int)y+modFeetHity;
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
