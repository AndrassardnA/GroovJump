package utilz;

public class Constants {
    public static class Directions{
        public static final int LEFT=0;
        public static final int UP=1;
        public static final int RIGHT=2;
        public static final int DOWN=3;
    }
    public static class PlayerConstants{
        public static final int IDLE=0;
        public static final int JUMP=1;
        public static final int RUN=2;
        public static final int ATTACK=3;
        public static int getSpritesAmount(int playerAction){
            switch (playerAction){
                case IDLE:
                case JUMP:
                case ATTACK:
                    return 15;
                case RUN:
                    return 14;
                default:
                    return 1;
            }
        }

    }
    public static class Sizes{
        public final static int TILE_DEFAULT_SIZE=16;
        public final static float SCALE=4f;
        public final static int WINDOW_WIDTH=(int)(TILE_DEFAULT_SIZE*16);
        public final static int WINDOW_HEIGHT=(int)(TILE_DEFAULT_SIZE*10);
    }
    public static class Game{
        public static final int FPS=120;
        public static final int UPS=200;
    }

}
