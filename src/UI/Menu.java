package UI;

public class Menu {
    protected Button[] buttons;

    protected void loadButtons(){}
    public Button[] getButtons() {
        return buttons;
    }
    public void nextSelected(){
        for(int i=0; i<buttons.length;i++){
            if(buttons[i].isSelected()){
                buttons[i].setSelected(false);
                buttons[(i==buttons.length-1 ? 0 : i+1)].setSelected(true);
                return;
            }
        }
        buttons[0].setSelected(true);
    }
    public void previousSelected(){
        for(int i=buttons.length-1; i>=0;i--){
            if(buttons[i].isSelected()){
                buttons[i].setSelected(false);
                buttons[(i==0 ? buttons.length-1 : i-1)].setSelected(true);
                return;
            }
        }
        buttons[buttons.length-1].setSelected(true);
    }
    public void executeButton(){}
}
