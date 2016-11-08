package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameMenuController extends KeyAdapter implements ActionListener {

	private GameModel model;
	
	private DominoesView view;
	
	public GameMenuController(GameModel model, DominoesView view){
		this.model = model;
		this.view = view;
	}
	
	/**
	 * Handle the menu item that was selected.
	 */
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		System.out.println(command);
		if (command.equals("Exit")) {
			view.dispose();
			System.exit(0);
		} 
	}
	
	/**
	 * Handle the keyboard shortcut.
	 */
	public void keyTyped(KeyEvent event) {
		char c = event.getKeyChar();
		System.out.println(c);
		if (c == 'e' || c == 'E') {
			view.dispose();
			System.exit(0);
		} 
	}

	
}
