package entityes;

import java.awt.*;

public abstract class Entity {
    protected float worldX, worldY;
    protected int width, height;
    protected Rectangle hitboxRight, hitboxLeft,  hitboxFeet, hitboxHead;
    protected int modRightHitx, modRightHity, modLeftHitx, modLeftHity,modFeetHitx,modFeetHity, modHeadHitx,modHeadHity;
    public Entity(float worldX, float worldY, int width, int height){
        this.worldX = worldX;
        this.worldY = worldY;
        this.width=width;
        this.height=height;
        initHitboxes();
    }

    private void initHitboxes() {
        modLeftHitx=0;
        modLeftHity=5;
        modRightHitx=width-5;
        modRightHity=5;
        modHeadHitx=3;
        modHeadHity=0;
        modFeetHitx=3;
        modFeetHity=height-5;

        hitboxLeft=new Rectangle((int) worldX +modLeftHitx,(int) worldY +modLeftHity,5,height-10);
        hitboxRight=new Rectangle((int) worldX +modRightHitx,(int) worldY +modRightHity,5,height-10);
        hitboxHead=new Rectangle((int) worldX +modHeadHitx,(int) worldY +modHeadHity,width-6,5);
        hitboxFeet=new Rectangle((int) worldX +modFeetHitx,(int) worldY +modFeetHity,width-6,5);
    }
    protected void updateHitbox(){
        hitboxRight.x=(int) worldX +modRightHitx;
        hitboxRight.y=(int) worldY +modRightHity;

        hitboxLeft.x=(int) worldX +modLeftHitx;
        hitboxLeft.y=(int) worldY +modLeftHity;

        hitboxHead.x=(int) worldX +modHeadHitx;
        hitboxHead.y=(int) worldY +modHeadHity;

        hitboxFeet.x=(int) worldX +modFeetHitx;
        hitboxFeet.y=(int) worldY +modFeetHity;
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

    public float getWorldX() {
        return worldX;
    }

    public void setWorldX(float worldX) {
        this.worldX = worldX;
    }

    public float getWorldY() {
        return worldY;
    }

    public void setWorldY(float worldY) {
        this.worldY = worldY;
    }
}
