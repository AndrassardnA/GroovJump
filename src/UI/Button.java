package UI;

import java.awt.*;

public class Button {
    private String szoveg;
    private int x, y;
    private Rectangle body;
    private boolean selected;

    public Button(String szoveg, int x, int y, int width, int height){
        this.szoveg=szoveg;
        this.x=x;
        this.y=y;
        this.body=new Rectangle(x,y,width,height);
        selected=false;
    }
    public void drawButton(Graphics g){
        if(selected){
            g.setColor(Color.RED);
        }else{
            g.setColor(Color.BLUE);
        }
        g.fillRect(body.x, body.y, body.width, body.height);
        g.setColor(Color.WHITE);
        //g.setFont(new Font());
        g.drawString(szoveg,x,y);
    }
}
