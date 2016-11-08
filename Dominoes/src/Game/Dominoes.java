package Game;

import javax.swing.JFrame;

public class Dominoes {

	public static void main(String args[]){
		GameModel model = new GameModel();
		DominoesView view = new DominoesView(model);
		GameMenuController menuController = new GameMenuController(model, view);
		view.registerListeners(menuController);
		
		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		view.setSize(1000, 800);
		view.setVisible(true);
	}
}
