package Game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class DominoesView extends JFrame {
	
	private GameModel model;
	
	public DominoesView(GameModel model){
		super("Dominoes");
		this.model = model;
		
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
		ScoreView score = new ScoreView(this.model);
		score.setPreferredSize(new Dimension(200,200));
		this.add(score, BorderLayout.EAST);
		
		//add hand
		HandView hand = new HandView();
		hand.setPreferredSize(new Dimension(200,150));
		this.add(hand,  BorderLayout.SOUTH);
	}
}
