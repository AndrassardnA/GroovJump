package UI;

import utilz.Drawer;

import java.awt.image.BufferedImage;

import static utilz.Constants.Sizes.SCALE;

public class Menu {
    protected Button[] buttons;
    protected BufferedImage title=new BufferedImage(1,1,1);
    protected int titleX, titleY;

    public Menu(int arrSize){
        buttons=new Button[arrSize];
        loadButtons();
        setTitle();
        title=Drawer.reScale(title,title.getWidth()*SCALE,title.getHeight()*SCALE);
    }

    protected void setTitle(){}
    protected BufferedImage[] getSubImg(BufferedImage img){
        img= Drawer.reScale(img,img.getWidth()*SCALE,img.getHeight()*SCALE);
        BufferedImage sImg = img.getSubimage(0,0,img.getWidth()/2,img.getHeight());
        BufferedImage uImg = img.getSubimage(img.getWidth()/2,0,img.getWidth()/2,img.getHeight());
        return new BufferedImage[]{sImg,uImg};
    }
    protected void loadButtons(){}
    public Button[] getButtons() {
        return buttons;
    }
    public void nextSelected(){
        for(int i=0; i<buttons.length;i++){
            if(buttons[i].isSelected()){
                buttons[i].setSelected(false);
                buttons[(i==buttons.length-1 ? 0 : i+1)].setSelected(true);
                return;
            }
        }
        buttons[0].setSelected(true);
    }
    public void previousSelected(){
        for(int i=buttons.length-1; i>=0;i--){
            if(buttons[i].isSelected()){
                buttons[i].setSelected(false);
                buttons[(i==0 ? buttons.length-1 : i-1)].setSelected(true);
                return;
            }
        }
        buttons[buttons.length-1].setSelected(true);
    }
    public void executeButton(){}
    public BufferedImage getTitle() {
        return title;
    }

    public int getTitleX() {
        return titleX;
    }

    public int getTitleY() {
        return titleY;
    }

}
