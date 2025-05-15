package levels;
public class Level {
    private Platform[] platforms;
    private HazardBlock[] hazards;

    public Level(Platform[] platforms, HazardBlock[] hazards) {
        this.platforms = platforms;
        this.hazards=hazards;
    }
    public Platform[] getPlatforms(){
        return platforms;
    }

    public HazardBlock[] getHazards() {
        return hazards;
    }
}
