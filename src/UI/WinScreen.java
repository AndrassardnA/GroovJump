package UI;

import levels.LevelManager;
import utilz.*;

import java.awt.image.BufferedImage;
import java.util.Random;

import static main.GameControl.gamestate;
import static utilz.Constants.Sizes.SCALE;
import static utilz.Constants.Sizes.WINDOW_HEIGHT;
import static utilz.Constants.Sizes.WINDOW_WIDTH;

public class WinScreen extends Menu {
    private final Animator[] animators;

    public WinScreen(int arrSize) {
        super(arrSize);
        animators = new Animator[6];
        for (int i = 0; i < animators.length; i++) {
            Random r = new Random();
            BufferedImage sprite;
            switch (r.nextInt(0, 3)) {
                case 0 -> sprite = LoadSave.getSprite("sprites/win_star_red.png");
                case 1 -> sprite = LoadSave.getSprite("sprites/win_star_white.png");
                default -> sprite = LoadSave.getSprite("sprites/win_star_yellow.png");
            }
            int w_new = (int) (sprite.getWidth() * SCALE * 1.2f);
            int h_new = (int) (sprite.getHeight() * SCALE * 1.2f);
            sprite = Drawer.reScale(sprite, w_new, h_new);
            Animator anim = new Animator(sprite, 1, 10);
            animators[i] = anim;
        }
    }

    @Override
    protected void setTitle() {
        title = LoadSave.getSprite("sprites/win_screen_icon.png");
        titleX = WINDOW_WIDTH * SCALE / 2 - title.getWidth() * SCALE / 2;
        titleY = 6 * SCALE;
    }

    @Override
    protected void loadButtons() {
        BufferedImage[] subImgs = getSubImg(LoadSave.getSprite("sprites/main_menu_button.png"));
        Button button = new Button(WINDOW_WIDTH * SCALE / 2 - subImgs[0].getWidth() / 2, WINDOW_HEIGHT * SCALE / 2, subImgs[0].getWidth(), subImgs[0].getHeight(), subImgs[0], subImgs[1]);
        buttons[0] = button;

        subImgs = getSubImg(LoadSave.getSprite("sprites/rerun_button.png"));
        button = new Button(buttons[0].getX(), (int) (buttons[0].getY() + subImgs[0].getHeight() * 1.5), subImgs[0].getWidth(), subImgs[0].getHeight(), subImgs[0], subImgs[1]);
        buttons[1] = button;

        subImgs = getSubImg(LoadSave.getSprite("sprites/exit_button2.png"));
        button = new Button(buttons[1].getX(), (int) (buttons[1].getY() + subImgs[0].getHeight() * 1.5), subImgs[0].getWidth(), subImgs[0].getHeight(), subImgs[0], subImgs[1]);
        buttons[2] = button;
    }

    public void updateAnims() {
        for (Animator anim : animators) {
            anim.updateAnimLoop(Constants.ActionConstants.STAR);
        }
    }

    public Animator[] getAnimators() {
        return animators;
    }

    @Override
    public void executeButton() {
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].isSelected()) {
                switch (i) {
                    case 0:
                        gamestate = GameState.MENU;
                        break;
                    case 1:
                        LevelManager.resetLevels();
                        gamestate = GameState.PLAYING;
                        break;
                    case 2:
                        System.exit(0);
                }
                return;
            }
        }
    }
}
