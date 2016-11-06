package Game;

import java.awt.BorderLayout;
import java.awt.Color;
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
		this.getContentPane().setBackground(Color.BLACK);
		setLayout(new BorderLayout(2, 2));
		
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
		score.setPreferredSize(new Dimension(275,200));
		this.add(score, BorderLayout.EAST);
		
		//add hand
		HandView hand = new HandView();
		hand.setPreferredSize(new Dimension(200,150));
		this.add(hand,  BorderLayout.SOUTH);
		
		//test values for updating score board 
		//model.setPlayerName(playerNumber, String) for changing names
		//model.addPoints(playerNumber, points) for adding points
		//score.updatePanel(model) redraws the score panel to reflect changes to model
		model.addPoints(0, 105);
		model.addPoints(1, 80);
		model.addPoints(2, 55);
		model.addPoints(3, 35);
		model.setPlayerName(0, "Shane");
		model.setPlayerName(1, "Marcus");
		model.setPlayerName(2, "Rebecca");
		model.setPlayerName(3, "Steve");
		score.updatePanel(model);
	}
}
