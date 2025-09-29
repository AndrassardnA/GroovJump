package entityes;

import levels.LevelManager;
import movement.Physic;
import utilz.Animator;
import utilz.Constants;
import utilz.LoadSave;

import java.awt.*;

import static utilz.Constants.PlayerConstants.*;


public class Player extends  Entity{
    //ANIMATION
    private int action =IDLE;
    private Animator animator;
    //MOVEMENT
    private final float speed=0.65f;
    private final float jumpPower=1.7f;
    private final float fallingSpeed=0.038f;
    private boolean up,down,right,left,jump; // jump: ha lenyomom a space-t true, ha felengedem false. Ha nyomvatartás közben bármi falsra állítja a nyomvatartás nem állítja vissza, újra fel kell engeni és le kell nyomni
    private boolean jumpBeingHeld; // figyeli, hogy nyomvatartás közben a jump ne álljon automatikusan true-ra ha egyszer false lett. Player ugráskor lesz true és input felengedéskor false
    private final float coyotJump=0.13f;
    private float coyotJumpTimer=0;
    private boolean jumpAlradyPressed=false; //ha lenyomták az ugrást true, ha földreért false
    private final float prejump=0.15f;
    private float prejumpTimer=prejump;
    private boolean prejumpTimerStarted=false;
    private boolean prejumpIntent=false;
    //RENDER
    private final int screenX;
    private boolean facingRight =true; //1 if facing right -1 if facing left
    private static final int PLAYER_DEFAULT_HEIGHT=16;
    private static final int PLAYER_DEFAULT_WIDTH=16;
    //GAME PLAY
    public int deaths=0;
    public static boolean levelFinished=false;
    //CONSTRUCTOR
    public Player(float x, float y) {
        super(x, y,(PLAYER_DEFAULT_WIDTH), (PLAYER_DEFAULT_HEIGHT));
        height=PLAYER_DEFAULT_HEIGHT;
        width=PLAYER_DEFAULT_WIDTH;
        screenX=(int)(Constants.Sizes.WINDOW_WIDTH/2-width/2);
        animator=new Animator(LoadSave.getSprite(LoadSave.PLAYER_SPRITE),3,15);
    }

    //UPDATE
    public void update(){
        levelFinished=false;
        updatePos();
        physic.detectCollision(LevelManager.getCurrentLevel());
        physic.calculateGravity(fallingSpeed);
        jump();
        physic.applyGravity();
        physic.dontStuck();
        physic.updateEntityHitboxes();
        setAnimation();
        Animator.updateAnimLoop(action);
        tryDying();
        if(physic.isFinishCollision()){
            levelFinished=true;
        }
    }

    private void setAnimation(){
        if((right||left)&&!(left&&right)){
            action =RUN;
        }
        else {
            action =IDLE;
        }
    }
    private void updatePos(){
        if(left && !right && !physic.isLeftCollision()){
            physic.moveLeft(speed);
            facingRight =false;
        } else if (right && !left && !physic.isRightCollision()) {
            physic.moveRight(speed);
            facingRight =true;
        }
    }

    //OTHER
    public void stopMoving(){
        up=false;
        down=false;
        left=false;
        right=false;
        jump=false;
    }
    public void levelChanged(){
            worldX = 1 * Constants.Sizes.TILE_DEFAULT_SIZE;
            worldY = 4 * Constants.Sizes.TILE_DEFAULT_SIZE;
            physic.setyVel(0);
            System.out.println("Level changed");
    }
    private void jump(){

        if(physic.isGrounded()){
            jumpAlradyPressed=false;
        }
        setCoyotJumpTimer();
        setPrejumpTimer();
        boolean canJump=(physic.isGrounded() || coyotJumpTimer<=coyotJump) && !jumpBeingHeld && !jumpAlradyPressed;
        boolean shouldJump= prejumpTimer>0 && physic.isGrounded();
        if((jump && canJump) || (prejumpIntent && shouldJump)){
            physic.setyVel(jumpPower);
            jumpBeingHeld=true;
            prejumpTimer=-1;
            prejumpIntent=false;
        }
        else if(!jump && !jumpBeingHeld && !physic.isGrounded() && physic.getyVel()>0){ //small jump
            physic.setyVel(physic.getyVel()/1.1f);
        }
        if(jump) {
            jumpAlradyPressed=true;
            jumpBeingHeld=true;
        }
        if(prejumpTimer<0){
            jump=false;
        }
        if(physic.isHeadButt()){
            physic.setyVel(0);
        }
    }
    private void setPrejumpTimer(){
        if(!physic.isGrounded()&&prejumpIntent&&!prejumpTimerStarted&&!jumpBeingHeld){
            prejumpTimer=prejump;
            prejumpTimerStarted=true;
        } else if (prejumpTimerStarted) {
            prejumpTimer-=1f/200f;
        }
        if(physic.getyVel()>0){
            prejumpTimerStarted=false;
            prejumpTimer=-1;
        }
    }
    private void setCoyotJumpTimer(){
        if(physic.isGrounded()){
            coyotJumpTimer=0;
        }
        else{
            coyotJumpTimer+=1f/200f;
        }
    }

    //BOOLEAN SETTERS
    public void setUp(boolean up) {
        this.up = up;
    }
    public void setDown(boolean down) {
        this.down = down;
    }
    public void setRight(boolean right) {
        this.right = right;
    }
    public void setLeft(boolean left) {
        this.left = left;
    }
    public void setJump(boolean jump){
        this.jump=jump;
    }
    public void setJumpBeingHeld(boolean jumpBeingHeld) {
        this.jumpBeingHeld = jumpBeingHeld;
    }
    public void setPrejumpIntent(boolean prejumpIntent){this.prejumpIntent=prejumpIntent;}

    //BOOLEAN GETTERS
    public boolean isJumpBeingHeld() {
        return jumpBeingHeld;
    }
    public boolean isFacingRight(){
        return facingRight;
    }

    //POSITION GETTERS

    @Override
    public int getX(){
        return screenX;
    }

    //ANIMATION GETTERS

    public Animator getAnimator() {
        return animator;
    }
    public int getAction(){
        return action;
    }

    //for debug
    public Physic getPhysic(){
        return physic;
    }

    //UI GETTERS

    public int getDeaths() {
        return deaths;
    }

    //GAME OVER
    private void die(){
            deaths++;
            System.out.println("You Died! Deaths: " + deaths);
            levelChanged();
    }
    public void tryDying(){
        if(physic.isHazardCollision()){
            die();
        }
    }
}
