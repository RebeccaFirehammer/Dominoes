package Game;

import javax.swing.JFrame;

public class Dominoes {
		
	public static void main(String args[]){
			GameModel model = new GameModel();
			DominoesView view = new DominoesView();
			view.startScreen();
			StartPanelController controller = new StartPanelController(model, view);
			
			view.registerListeners(controller);
			view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			view.setSize(1000, 800);
			view.setVisible(true);
	}
}
