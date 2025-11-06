package utilz;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilz.Constants.Sizes.SCALE;

public class MyString {
    private static final BufferedImage numSprite = LoadSave.getSprite("sprites/numbers_sprite.png");
    private static final BufferedImage[] numbers = splitNumSprite();

    private static BufferedImage[] splitNumSprite() {
        BufferedImage[] numSpriteArr = new BufferedImage[10];
        for (int i = 0; i < numSpriteArr.length; i++) {
            BufferedImage img = numSprite.getSubimage(i * 15, 0, 15, 15);
            numSpriteArr[i] = Drawer.reScale(img, (int) (img.getWidth() * SCALE * (2f / 3f)), (int) (img.getHeight() * SCALE * (2f / 3f)));
        }
        return numSpriteArr;
    }

    public static BufferedImage getMyNumImg(int num) {
        char[] num_char_arr = Integer.toString(num).toCharArray();
        BufferedImage[] img_arr = new BufferedImage[num_char_arr.length];

        for (int i = 0; i < num_char_arr.length; i++) {
            char c = num_char_arr[i];
            switch (c) {
                case '0' -> img_arr[i] = numbers[0];
                case '1' -> img_arr[i] = numbers[1];
                case '2' -> img_arr[i] = numbers[2];
                case '3' -> img_arr[i] = numbers[3];
                case '4' -> img_arr[i] = numbers[4];
                case '5' -> img_arr[i] = numbers[5];
                case '6' -> img_arr[i] = numbers[6];
                case '7' -> img_arr[i] = numbers[7];
                case '8' -> img_arr[i] = numbers[8];
                case '9' -> img_arr[i] = numbers[9];
            }
        }
        return concatImgsHorizontally(img_arr);
    }

    private static BufferedImage concatImgsHorizontally(BufferedImage[] img_arr) {
        int width = 0, height = 0;
        for (BufferedImage img : img_arr) {
            width += img.getWidth();
            height = (Math.max(img.getHeight(), height));
        }

        BufferedImage combined = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = combined.getGraphics();

        int curr_x = 0;
        for (BufferedImage img : img_arr) {
            g.drawImage(img, curr_x, 0, null);
            curr_x += img.getWidth();
        }

        g.dispose();
        return combined;
    }
}
