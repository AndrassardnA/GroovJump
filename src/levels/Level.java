package levels;

import utilz.Animator;
import utilz.Constants;
import utilz.LoadSave;

import java.awt.image.BufferedImage;

public class Level {
    private final Platform[] platforms;
    private final HazardBlock[] hazards;
    private final FinishBlock finish;
    private Animator hazard_Animator, finish_Animator;

    public Level(Platform[] platforms, HazardBlock[] hazards, FinishBlock finish) {
        this.platforms = platforms;
        this.hazards=hazards;
        this.finish=finish;
        loadAnimators();
    }

    private void loadAnimators() {
        BufferedImage hazardImage= LoadSave.getSprite(LoadSave.HAZARD_SPRITE);
        hazard_Animator=new Animator(hazardImage,1, Constants.LevelConstants.getSpritesAmount(Constants.LevelConstants.HAZARD));
        BufferedImage finishImage= LoadSave.getSprite(LoadSave.FINISH_SPRITE);
        finish_Animator=new Animator(finishImage,1,Constants.LevelConstants.getSpritesAmount(Constants.LevelConstants.FINISH));
    }

    public void updateHazardFrame(){
        hazard_Animator.updateAnimLoop(0);
    }
    public BufferedImage getHazardFrame(){
        return hazard_Animator.getCurrentFrame(0);
    }
    public void updateFinishFrame(){
        finish_Animator.updateAnimLoop(Constants.LevelConstants.FINISH);
    }
    public BufferedImage getFinishFrame(){
        return finish_Animator.getCurrentFrame(Constants.LevelConstants.FINISH);
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
