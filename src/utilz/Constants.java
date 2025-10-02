package utilz;

public class Constants {

    public static class PlayerConstants{
        public static final int IDLE=0;
        public static final int JUMP=2;
        public static final int RUN=1;
        public static final int ATTACK=3;
        public static int getSpritesAmount(int playerAction){
            return switch (playerAction) {
                case IDLE -> 15;
                case ATTACK, JUMP -> 12;
                case RUN -> 14;
                default -> 1;
            };
        }

    }
    public static class LevelConstants {
        public static final int FINISH = 0;
        public static final int HAZARD = 1;

        public static int getSpritesAmount(int blockType) {
            return switch (blockType) {
                case FINISH, HAZARD -> 15;
                default -> 1;
            };
        }
    }
    public static class Sizes{
        public final static int TILE_DEFAULT_SIZE=16;
        public final static float SCALE=4f;
        public final static int WINDOW_WIDTH=TILE_DEFAULT_SIZE*16;
        public final static int WINDOW_HEIGHT=TILE_DEFAULT_SIZE*10;
    }
    public static class Game{
        public static final int FPS=240;
        public static final int UPS=200;
    }

}
