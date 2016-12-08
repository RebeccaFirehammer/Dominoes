package Game;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Dominoes {
	public static void main(String args[]){
			GameModel model = new GameModel();
			DominoesView view = new DominoesView();
			StartPanelController controller = new StartPanelController(model, view);
			
			view.registerListeners(controller);
			view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			view.setMinimumSize(new Dimension(1000,800));
			view.setResizable(false);
			view.setVisible(true);
	}
}
