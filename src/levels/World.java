package levels;

public class World {
    private int[][] map;
    public World(int height, int width){
        map=new int[height][width];
        fillWorldWithBlanc();
    }
    private void fillWorldWithBlanc(){
        for (int i=0; i<map.length;i++){
            for(int j=0; j<map[0].length;j++){
                map[i][j]=-1;
            }
        }
    }
    public int[][] getMap(){
        return map;
    }
    public void setMapXY(int x, int y, int value ){
        map[y][x]=value;
    }
}
