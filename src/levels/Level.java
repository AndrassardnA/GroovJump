package levels;
public class Level {
    private Platform[] platforms;
    private World world;

    public Level(Platform[] platforms, int width, int height) {
        this.platforms = platforms;
        world=new World(height,width);
        fillWorldWithPlatforms();
    }
    public Platform[] getPlatforms(){
        return platforms;
    }
    public World getWorld(){
        return world;
    }
    private void fillWorldWithPlatforms(){
        for (int i=0; i< platforms.length; i++){ //végigmenni a platform listán (i egy platform)
            Platform currentPlatform=platforms[i];
            for (int j=0; j<currentPlatform.getBody().length;j++){ // végigmenni az adott platform sorain (j egy sor)
                for (int h=0; h<currentPlatform.getBody()[j].length;h++){ //végigmenni az adott sor elemein (h egy sor elem)
                    int x= currentPlatform.getPosX();
                    int y= currentPlatform.getPosY();
                    world.getMap()[y][x]=currentPlatform.getBody()[j][h];
                }
            }
        }
    }
}
