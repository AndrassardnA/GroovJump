package levels;

import java.awt.*;

public class Level {
    private int[][] map;
    public Level(int[][] map){
        this.map=map;
    }
    public int[][] getMap(){
        return map;
    }
}
