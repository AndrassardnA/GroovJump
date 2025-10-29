package movement;

import entityes.Entity;
import entityes.Player;
import levels.HazardBlock;
import levels.Level;
import levels.Platform;

import java.awt.*;

public class Physic {
    private boolean feetCollision, headCollision, rightCollision, leftCollision, grounded, hazardCollision, finishCollision, headbutt;
    private float yVel=0;
    private final Entity entity;
    private Rectangle hitboxLeft, hitboxRight, hitboxHead, hitboxFeet;


    //CONSTRUCTOR
    public Physic(Entity entity){
        this.entity=entity;
    }
    public void innitEntityHitboxes(){
        Rectangle[] allHitbox=utilz.Hitbox.entityHitboxInit((entity.getX()),entity.getY(),(int)entity.getWidth(),(int)entity.getHeight());

        hitboxLeft=allHitbox[0];
        hitboxRight=allHitbox[1];
        hitboxHead=allHitbox[2];
        hitboxFeet=allHitbox[3];
    }
    public void updateEntityHitboxes(){
        int[] updatedPositions=utilz.Hitbox.entityHitboxUpdate(entity.getX(),entity.getY(),entity.getWidth(),entity.getHeight());
        hitboxLeft.x=updatedPositions[0];
        hitboxLeft.y=updatedPositions[1];

        hitboxRight.x=updatedPositions[2];
        hitboxRight.y=updatedPositions[3];

        hitboxHead.x=updatedPositions[4];
        hitboxHead.y=updatedPositions[5];

        hitboxFeet.x=updatedPositions[6];
        hitboxFeet.y=updatedPositions[7];
    }


    //MOVING
    public void moveUp(float speed){
        entity.setWorldY(entity.getWorldY()-speed);
    }
    public void moveDown(float speed){
        entity.setWorldY(entity.getWorldY()+speed);
    }
    public void moveLeft(float speed){
        entity.setWorldX(entity.getWorldX()-speed);
    }
    public void moveRight(float speed){
        entity.setWorldX(entity.getWorldX()+speed);
    }
    //GRAVITY
    public void calculateGravity(float gravityForce) {
        if(!grounded){
            yVel -= gravityForce;

        }else {
            yVel=0;
        }
    }
    public void applyGravity(){
        entity.setWorldY(entity.getWorldY() - yVel/*(6/Constants.Sizes.SCALE)*/);
    }
    //COLLISION
    public void detectCollision(Level level){
        feetCollision=false;
        headCollision=false;
        rightCollision =false;
        leftCollision =false;
        grounded=false;
        hazardCollision=false;
        finishCollision=false;
        headbutt=false;

        for (Platform p: level.getPlatforms()){
            if(hitboxFeet.intersects(p.getBounds())){
                feetCollision=true;
                grounded=true;
            }
            if(hitboxHead.intersects(p.getBounds())){
                headCollision=true;
            }
            if(hitboxLeft.intersects(p.getBounds())){
                leftCollision=true;
            }
            if(hitboxRight.intersects(p.getBounds())){
                rightCollision=true;
            }
        }
        for (HazardBlock h: level.getHazards()){
            if(hitboxFeet.intersects(h.getBounds())||hitboxHead.intersects(h.getBounds())||hitboxLeft.intersects(h.getBounds())||hitboxFeet.intersects(h.getBounds())||hitboxRight.intersects(h.getBounds())){
                hazardCollision=true;
            }
        }

        if(hitboxFeet.intersects(level.getFinish().getBounds())||hitboxHead.intersects(level.getFinish().getBounds())||hitboxLeft.intersects(level.getFinish().getBounds())||hitboxFeet.intersects(level.getFinish().getBounds())||hitboxRight.intersects(level.getFinish().getBounds())){
            finishCollision=true;
        }
        if(headCollision&&(!feetCollision&&(leftCollision||rightCollision))){ //Headbutting
            headbutt=true;
        }
    }
    public void dontStuck(){
        if(feetCollision&&(rightCollision||leftCollision)){
            moveUp(1);
        }
        if(rightCollision&&(feetCollision&&headCollision)){
            moveLeft(1);
        }
        if(leftCollision&&(feetCollision&&headCollision)){
            moveRight(1);
        }
        if(headbutt){
            moveDown(1);
        }

    }

    //BOOLEAN GETTERS
    public boolean isFeetCollision() {
        return feetCollision;
    }

    public boolean isHeadCollision() {
        return headCollision;
    }

    public boolean isHazardCollision() {
        return hazardCollision;
    }

    public boolean isFinishCollision() {
        return finishCollision;
    }

    public boolean isRightCollision() {
        return rightCollision;
    }

    public boolean isLeftCollision() {
        return leftCollision;
    }

    public boolean isGrounded() {
        return grounded;
    }

    public boolean isHeadButt(){
        return headbutt;
    }

    //VELOCITY SET-GET
    public float getyVel() {
        return yVel;
    }

    public void setyVel(float yVel) {
        this.yVel = yVel;
    }

    //HITBOX GETTERS
    public Rectangle getHitboxLeft() {
        return hitboxLeft;
    }

    public Rectangle getHitboxRight() {
        return hitboxRight;
    }

    public Rectangle getHitboxHead() {
        return hitboxHead;
    }

    public Rectangle getHitboxFeet() {
        return hitboxFeet;
    }
}
