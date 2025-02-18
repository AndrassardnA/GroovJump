package main;

public class GameControl {
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    public GameControl(){
        gamePanel=new GamePanel();
        gameWindow=new GameWindow(400,400, gamePanel);
        gamePanel.setFocusable(true); // engedélyezi, hogy fókuszolható legyen (fogadhasson inputokat)
        gamePanel.requestFocus(true); // az inputok a gampanelbe fognak menni
    }
}
