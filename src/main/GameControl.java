package main;

public class GameControl {
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    public GameControl(){
        gamePanel=new GamePanel();
        gameWindow=new GameWindow(400,400, gamePanel);
    }
}
