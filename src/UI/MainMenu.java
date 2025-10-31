package UI;

import main.GameControl;
import utilz.Constants;
import utilz.GameState;
import utilz.LoadSave;

import java.awt.image.BufferedImage;

import static utilz.Constants.Sizes.*;
import static utilz.GameState.PLAYING;

public class MainMenu extends Menu{
    public MainMenu() {
        buttons = new Button[2];
        loadButtons();
    }

    @Override
    protected void loadButtons(){
        BufferedImage[] subImgs=getSubImg(LoadSave.getSprite("sprites/play_button.png"));
        Button button = new Button(WINDOW_WIDTH*SCALE/2-subImgs[0].getWidth()/2*SCALE,WINDOW_HEIGHT*SCALE/2-subImgs[0].getHeight()*SCALE,subImgs[0].getWidth(),subImgs[0].getHeight(),subImgs[0],subImgs[1]);
        buttons[0]=button;

        subImgs=getSubImg(LoadSave.getSprite("sprites/exit_button.png"));
        button = new Button(buttons[0].getX(),buttons[0].getY()+subImgs[0].getHeight()*SCALE*2,subImgs[0].getWidth(),subImgs[0].getHeight(),subImgs[0],subImgs[1]);
        buttons[1]=button;
    }
    private BufferedImage[] getSubImg(BufferedImage img){
        BufferedImage sImg = img.getSubimage(0,0,img.getWidth()/2,img.getHeight());
        BufferedImage uImg = img.getSubimage(img.getWidth()/2,0,img.getWidth()/2,img.getHeight());
        return new BufferedImage[]{sImg,uImg};
    }
    @Override
    public void executeButton(){
        for(int i=0; i<buttons.length; i++){
            if(buttons[i].isSelected()){
                switch(i){
                    case 0 -> GameControl.gamestate =PLAYING;
                    case 1 -> System.exit(0);
                }
                return;
            }
        }
    }

}
