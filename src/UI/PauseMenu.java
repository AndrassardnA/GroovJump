package UI;

import main.GameControl;
import utilz.LoadSave;

import java.awt.image.BufferedImage;

import static utilz.Constants.Sizes.*;
import static utilz.Constants.Sizes.SCALE;
import static utilz.GameState.MENU;
import static utilz.GameState.PLAYING;

public class PauseMenu extends Menu{
    public PauseMenu(int arrSize){
        super(arrSize);
    }
    @Override
    protected void setTitle() {
        title=LoadSave.getSprite("sprites/pause_icon.png");
        titleX=WINDOW_WIDTH*SCALE/2-title.getWidth()*SCALE/2;
        titleY=WINDOW_HEIGHT*SCALE/2-title.getHeight()*SCALE*2;
    }
    @Override
    protected void loadButtons(){
        BufferedImage[] subImgs=getSubImg(LoadSave.getSprite("sprites/continue_button.png"));
        Button button = new Button(WINDOW_WIDTH*SCALE/2-subImgs[0].getWidth()/2,WINDOW_HEIGHT*SCALE/2,subImgs[0].getWidth(),subImgs[0].getHeight(),subImgs[0],subImgs[1]);
        buttons[0]=button;

        subImgs=getSubImg(LoadSave.getSprite("sprites/main_menu_button.png"));
        button = new Button(buttons[0].getX(),buttons[0].getY()+subImgs[0].getHeight()*2,subImgs[0].getWidth(),subImgs[0].getHeight(),subImgs[0],subImgs[1]);
        buttons[1]=button;
    }
    @Override
    public void executeButton(){
        for(int i=0; i<buttons.length; i++){
            if(buttons[i].isSelected()){
                switch(i){
                    case 0 -> GameControl.gamestate = PLAYING;
                    case 1 -> GameControl.gamestate = MENU;
                }
                return;
            }
        }
    }
}

