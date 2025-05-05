package levels;
public class Level {
    private Platform[] platforms;

    public Level(Platform[] platforms) {
        this.platforms = platforms;
    }
    public Platform[] getPlatforms(){
        return platforms;
    }
}
