package levels;

import utilz.Constants;

import java.awt.*;

public class Level {
    private Platform[] platforms;
    private HazardBlock[] hazards;
    private FinishBlock finish;
    private final int scale= (int)Constants.Sizes.SCALE;

    public Level(Platform[] platforms, HazardBlock[] hazards, FinishBlock finish) {
        this.platforms = platforms;
        this.hazards=hazards;
        this.finish=finish;
    }
    public Platform[] getPlatforms(){
        return platforms;
    }

    public HazardBlock[] getHazards() {
        return hazards;
    }

    public FinishBlock getFinish() {
        return finish;
    }
    public void  drawHazard(Graphics g){
        g.setColor(Color.RED);
        for(int i=0; i<hazards.length;i++){
            HazardBlock h=hazards[i];
            g.fillRect(h.getPosX()*scale,h.getPosY()*scale,h.getWidth()*scale,h.getHeight()*scale);
        }
    }
    public void drawFinish(Graphics g){
        g.setColor(Color.BLUE);
        g.fillRect(finish.getPosX()*scale,finish.getPosY()*scale,finish.getWidth()*scale,finish.getHeight()*scale);
    }
}
