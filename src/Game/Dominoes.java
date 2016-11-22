package Game;

import javax.swing.JFrame;

public class Dominoes {
	
	public static enum STATE{
			MENU,
			GAME
	};
	public static STATE State = STATE.MENU;	
	
	public static void main(String args[]){
		if (State == STATE.MENU){
			StartPanel panel = new StartPanel();
			StartFrame view = new StartFrame();
			view.add(panel);
			view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			view.setSize(1000, 800);
			view.setVisible(true);
		}//else if(State == STATE.GAME){
			//GameModel model = new GameModel();
			//DominoesView view = new DominoesView(model);
			//GameMenuController menuController = new GameMenuController(model, view);
			//view.registerListeners(menuController);
			//view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//view.setSize(1000, 800);
			//view.setVisible(true);
		//} 
	}

}
