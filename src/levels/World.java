package levels;

public class World {
    private int[][] map;
    public World(int height, int width){
        map=new int[height][width];
    }
    public int[][] getMap(){
        return map;
    }
    public void setMapXY(int x, int y, int value ){
        map[y][x]=value;
    }
}
