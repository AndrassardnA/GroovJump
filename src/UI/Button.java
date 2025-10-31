package UI;

import java.awt.*;
import java.awt.geom.Dimension2D;
import java.awt.image.BufferedImage;

public class Button {
    private Dimension size;
    private int x,y;
    private BufferedImage sImg,uImg;
    private boolean selected;

    public Button(int x, int y, int width, int height, BufferedImage sImg,BufferedImage uImg){
        this.size= new Dimension(width,height);
        this.x=x;
        this.y=y;
        this.sImg=sImg;
        this.uImg=uImg;
        selected=false;
    }
    public int getX(){
        return x;
    }

    public int getY() {
        return y;
    }

    public Dimension getSize() {
        return size;
    }

    public BufferedImage getImg() {
        return selected ? sImg : uImg;
    }

    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean value){
        this.selected=value;
    }
}
