package movement;

import entityes.Entity;
import levels.HazardBlock;
import levels.Level;
import levels.Platform;
import utilz.Constants;

public class Physic {
    private boolean feetCollision, headCollision, rightCollision, leftCollision, grounded, hazardCollision, finishCollision, headbutt;
    private float yVel=0;
    private Entity entity;
    public Physic(Entity entity){
        this.entity=entity;
    }

    //Moving
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
    //Gravity
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
    //Collision
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
            if(entity.getHitboxFeet().intersects(p.getBounds())){
                feetCollision=true;
                grounded=true;
            }
            if(entity.getHitboxHead().intersects(p.getBounds())){
                headCollision=true;
            }
            if(entity.getHitboxLeft().intersects(p.getBounds())){
                leftCollision=true;
            }
            if(entity.getHitboxRight().intersects(p.getBounds())){
                rightCollision=true;
            }
        }
        for (HazardBlock h: level.getHazards()){
            if(entity.getHitboxFeet().intersects(h.getBounds())||entity.getHitboxHead().intersects(h.getBounds())||entity.getHitboxLeft().intersects(h.getBounds())||entity.getHitboxFeet().intersects(h.getBounds())||entity.getHitboxRight().intersects(h.getBounds())){
                hazardCollision=true;
            }
        }
        if(entity.getHitboxFeet().intersects(level.getFinish().getBounds())||entity.getHitboxHead().intersects(level.getFinish().getBounds())||entity.getHitboxLeft().intersects(level.getFinish().getBounds())||entity.getHitboxFeet().intersects(level.getFinish().getBounds())||entity.getHitboxRight().intersects(level.getFinish().getBounds())){
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

    public float getyVel() {
        return yVel;
    }

    public void setyVel(float yVel) {
        this.yVel = yVel;
    }
    public boolean isHeadButt(){
        return headbutt;
    }
}
