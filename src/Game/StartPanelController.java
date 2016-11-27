package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * 
 * Adds GUI Elements to Start Menu
 *
 */
@SuppressWarnings("serial")
public class StartPanelController implements ActionListener {
	
	private enum Actions{
		START,
		RULES,
		OPTIONS,
		EXIT
	};
	
	private DominoesView view;
	
	private GameModel model;
	
	
	public StartPanelController(GameModel model, DominoesView view){
		this.model = model;
		this.view = view;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == Actions.START.name()){
			GameMenuController menuController = new GameMenuController(model, view);
			view.gameScreen(model);
			view.registerListeners(menuController);
		}
		else if(e.getActionCommand() == Actions.RULES.name()){
			String rules = ("Starting the game:"
					+ "\n\nEach player starts with 7 dominoes.The player with the highest double (six-six) places that double on the board to start the game. "
					+ "\nAfter the first round, the person who won the previous round starts first."
					+ "\n\nPlaying the Game:"
					+ "\nThe next player must then place a matching domino next to the first domino. "
					+ "\nFor example, if the first player started the game with the double six, the next player must play a domino that has a six on it. "
					+ "\nThe first double played is called the “spinner”. "
					+ "\nThe spinner may be played off of either end as well as its regular sides. "
					+ "\nThe spinner is the only place where it can be played off of 4 ways."
					+ "\nIf the player doesn’t have a domino of matching value, they will draw a domino from the “boneyard”. "
					+ "\nThey keep recieving dominoes from the “boneyard” until they get a playable domino. "
					+ "\nIf there is no playable domino then the player must “knock” or pass their turn onto the next player."
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
	                JOptionPane.showMessageDialog(null, new JLabel(html1 + "300" + html2 + rules));
	            }
	        };
	        SwingUtilities.invokeLater(r);
		}
		else if(e.getActionCommand() == Actions.OPTIONS.name()){
			//add options later
			
		}else if (e.getActionCommand() == Actions.EXIT.name()){
			System.exit(0);
		}
	}

}
