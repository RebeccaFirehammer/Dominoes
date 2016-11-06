package Game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class DominoesView extends JFrame {
	
	/**
	 * The Dominoes Game Model
	 */
	private GameModel model;
	
	/**
	 * Constructs a Dominoes View which contains all the GUI
	 * elements on the game.
	 * @param model The Game Model from which the GUI pulls the game information
	 */
	public DominoesView(GameModel model){
		super("Dominoes");
		this.model = model;
		setLayout(new BorderLayout());
		
		//add icon
		ImageIcon dominoIcon = new ImageIcon("Images/Domino-icon.png");
		setIconImage(dominoIcon.getImage());
		
		//add menu
		MenuView menu = new MenuView();
		this.add(menu, BorderLayout.NORTH);	
		
		//add board
		BoardView board = new BoardView();
		this.add(board, BorderLayout.CENTER);
		
		//add scoreboard
		ScorePanel score = new ScorePanel(this.model);
		score.setPreferredSize(new Dimension(200,200));
		this.add(score, BorderLayout.EAST);
		
		//add hand
		HandView hand = new HandView();
		hand.setPreferredSize(new Dimension(200,150));
		this.add(hand,  BorderLayout.SOUTH);
		
		model.addPoints(0, 75);
		model.addPoints(1, 100);
		model.addPoints(2, 200);
		model.addPoints(3, 400);
		score.updatePanel(model);
	}
}
