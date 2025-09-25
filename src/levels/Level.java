package levels;

public class Level {
    private Platform[] platforms;
    private HazardBlock[] hazards;
    private FinishBlock finish;

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

}
