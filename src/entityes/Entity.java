package entityes;

import movement.Physic;

import java.awt.*;

public abstract class Entity {
    protected float worldX, worldY;
    protected int width, height;
    protected final Physic physic;
    public Entity(float worldX, float worldY, int width, int height){
        this.worldX = worldX;
        this.worldY = worldY;
        this.width=width;
        this.height=height;
        physic=new Physic(this);
        physic.innitEntityHitboxes();
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

    public int getX(){
        return (int)worldX;
    }
    public int getY(){
        return (int)worldY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
