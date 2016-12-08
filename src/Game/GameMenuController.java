package Game;

/**
 * Creates a game menu controller
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class GameMenuController extends KeyAdapter implements ActionListener {

	/**
	 * The Game Model
	 */
	private GameModel model;
	
	/**
	 * The Game view
	 */
	private DominoesView view;
	
	/**
	 * A Game Menu Controller
	 * @param model The game Model
	 * @param view The game view
	 */
	public GameMenuController(GameModel model, DominoesView view){
		this.model = model;
		this.view = view;
	}
	
	/**
	 * Handle the menu item that was selected.
	 */
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if (command.equals("Exit")) {
			view.startScreen();
			view.registerListeners(new StartPanelController(model, view));
		} 
		if(command.equals("Rules")){
			String rules = ("Starting the game:"
					+ "\n\nEach player starts with 7 dominoes.The highest double drawn by a player is placed on the board to start the game. "
					+ "\nAfter the first round, the person who won the previous round starts first."
					+ "\n\nPlaying the Game:"
					+ "\nThe next player must then place a matching domino next to the first domino. "
					+ "\nFor example, if the first player started the game with the double six, the next player must play a domino that has a six on it. "
					+ "\nThe first double played is called the “spinner”. "
					+ "\nThe spinner may be played off of either end first. "
					+ "\nThe spinner is the only place where it can be played off of 4 ways after the North & South sides have been played on."
					+ "\nIf the player doesn’t have a domino of matching value, they will draw a domino from the “boneyard”. "
					+ "\nIf there is no playable domino then the player must pass their turn onto the next player."
					+ "\n\nScoring:"
					+ "\n\nPoints may be awarded during the play of the hand by making the exposed ends of the chain total to a multiple of five."
					+ "\nThe winner at the end of each hand also scores points for all the remaining unplayed dominoes in the other player’s hands "
					+ "added and rounded to the nearest multiple of five."
					+ "\n\nEnd:"
					+ "\nThe first player to reach 250 points wins.");
			//allows word wrapping
			final String html1 = "<html><body style='width: ";
	        final String html2 = "px'>";

	        Runnable r = new Runnable(){ 
	        	//adds rules to window
	            public void run() {
	                JOptionPane.showMessageDialog(null, new JLabel(html1 + "200" + html2 + rules));
	                
	            }
	        };
	        SwingUtilities.invokeLater(r);
		}
		if(command.equals("New Game")){
			model.newGame();
			view.getHandView().setStatus("Pip Total: " + model.getBoard().openPipsTotal());
			view.update(model);
		}
	}
	
	/**
	 * Handle the keyboard shortcut.
	 */
	public void keyTyped(KeyEvent event) {
		char c = event.getKeyChar();
		if (c == 'e' || c == 'E') {
			view.startScreen();
			view.registerListeners(new StartPanelController(model, view));
		} 
	}

	
}
