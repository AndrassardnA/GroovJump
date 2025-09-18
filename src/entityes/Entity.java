package entityes;

import utilz.Constants;

import java.awt.*;

public abstract class Entity {
    public float worldX, worldY;
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
        g.drawRect(hitboxLeft.x*(int) Constants.Sizes.SCALE, hitboxLeft.y*(int) Constants.Sizes.SCALE, hitboxLeft.width*(int) Constants.Sizes.SCALE, hitboxLeft.height*(int) Constants.Sizes.SCALE);
        g.drawRect(hitboxRight.x*(int) Constants.Sizes.SCALE, hitboxRight.y*(int) Constants.Sizes.SCALE, hitboxRight.width*(int) Constants.Sizes.SCALE, hitboxRight.height*(int) Constants.Sizes.SCALE);
        g.setColor(Color.BLUE);
        g.drawRect(hitboxHead.x*(int) Constants.Sizes.SCALE,hitboxHead.y*(int) Constants.Sizes.SCALE, hitboxHead.width*(int) Constants.Sizes.SCALE,hitboxHead.height*(int) Constants.Sizes.SCALE);

        g.drawRect(hitboxFeet.x*(int) Constants.Sizes.SCALE, hitboxFeet.y*(int) Constants.Sizes.SCALE, hitboxFeet.width*(int) Constants.Sizes.SCALE, hitboxFeet.height*(int) Constants.Sizes.SCALE);
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
