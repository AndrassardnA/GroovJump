package UI;

import levels.LevelManager;
import main.GameControl;
import utilz.*;

import java.awt.image.BufferedImage;

import static utilz.Constants.Sizes.*;
import static utilz.GameState.PLAYING;

public class MainMenu extends Menu{
    private Animator animator;
    public MainMenu(int arrSize){
        super(arrSize);
        BufferedImage sprite=LoadSave.getSprite(LoadSave.PLAYER_SPRITE);
        sprite=Drawer.reScale(sprite, sprite.getWidth()*SCALE*2, sprite.getHeight()*SCALE*2);
        animator= new Animator(sprite,6,15);
    }
    @Override
    protected void setTitle() {
        title=LoadSave.getSprite("sprites/main_menu_icon.png");
        title= Drawer.reScale(title, (int)(title.getWidth()*1.2f),(int)(title.getHeight()*1.2f));
        titleX=WINDOW_WIDTH*SCALE/2-title.getWidth()*SCALE/2;
        titleY=5*SCALE;
    }
    @Override
    protected void loadButtons(){
        BufferedImage[] subImgs=getSubImg(LoadSave.getSprite("sprites/play_button2.png"));
        Button button = new Button(WINDOW_WIDTH*SCALE/2-subImgs[0].getWidth()/2,WINDOW_HEIGHT*SCALE/2+subImgs[0].getHeight(),subImgs[0].getWidth(),subImgs[0].getHeight(),subImgs[0],subImgs[1]);
        buttons[0]=button;

        subImgs=getSubImg(LoadSave.getSprite("sprites/exit_button2.png"));
        button = new Button(buttons[0].getX(),buttons[0].getY()+subImgs[0].getHeight()+5*SCALE,subImgs[0].getWidth(),subImgs[0].getHeight(),subImgs[0],subImgs[1]);
        buttons[1]=button;
    }
    public void updateAnim(){
        animator.updateAnimLoop(utilz.Constants.ActionConstants.IDLE);
    }
    public Animator getAnimator(){
        return animator;
    }

    @Override
    public void executeButton(){
        for(int i=0; i<buttons.length; i++){
            if(buttons[i].isSelected()){
                switch(i){
                    case 0 :
                        GameControl.gamestate =PLAYING;
                        LevelManager.resetLevels();
                        break;
                    case 1:
                        System.exit(0);
                        break;
                }
                return;
            }
        }
    }


}
