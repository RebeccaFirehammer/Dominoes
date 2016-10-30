package Game;

import javax.swing.JFrame;

public class Dominoes {

	public static void main(String args[]){
		DominoesModel model = new DominoesModel();
		DominoesView view = new DominoesView(model);
		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		view.setSize(1000, 800);
		view.setVisible(true);
	}
}
