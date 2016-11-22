package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;

@SuppressWarnings("serial")
public class DominoesView extends JFrame {
	
	/** The Dominoes Game Model */
	private GameModel model;
	
	/**Displays the menu options */
	private MenuBar menuPanel;
	
	/** The board panel that displays the game board */
	private BoardPanel boardPanel;
	
	/** The score panel that displays the score board */
	private ScorePanel scorePanel;
	
	/** The hand panel that displays the hand of the player */
	private HandView handView;
	
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
		
		//menu
		this.menuPanel = new MenuBar();
		
		//board
		Board board = new Board(new Domino(6,6)); //for testing board
		board.addToSpoke(0, new Domino(6,5));
		board.addToSpoke(0, new Domino(5,4));
		board.addToSpoke(0, new Domino(4,3));
		board.addToSpoke(1, new Domino(6,3));
		board.addToSpoke(1, new Domino(3,5));
		board.addToSpoke(1, new Domino(5,2));
		board.addToSpoke(2, new Domino(6,4));
		board.addToSpoke(2, new Domino(4,1));
		board.addToSpoke(3, new Domino(6,0));
		board.addToSpoke(3, new Domino(0,1));
		
		this.model.setBoard(board);
		this.boardPanel = new BoardPanel(this.model);
		boardPanel.setPreferredSize(new Dimension(725,1000));
		
		//scoreboard
		this.scorePanel = new ScorePanel(this.model);
		scorePanel.setPreferredSize(new Dimension(275,0));
		
		//hand
		this.handView = new HandView(this.model);
		handView.setPreferredSize(new Dimension(200,150));
		
		//add components
		this.setJMenuBar(menuPanel);
		this.add(boardPanel, BorderLayout.CENTER);
		this.add(scorePanel, BorderLayout.EAST);
		this.add(handView, BorderLayout.SOUTH);
		
		
		//test values for updating score board 
		//model.setPlayerName(playerNumber, String) for changing names
		//model.addPoints(playerNumber, points) for adding points
		//score.updatePanel(model) redraws the score panel to reflect changes to model
		model.addPoints(0, 310);
		model.addPoints(1, 405);
		model.addPoints(2, 515);
		model.addPoints(3, 645);
		model.setPlayerName(0, "Shane");
		model.setPlayerName(1, "Marcus");
		model.setPlayerName(2, "Rebecca");
		model.setPlayerName(3, "Steve");
		scorePanel.updatePanel(model);
		//test values for updating players hand
		model.addToPlayerHand(0, 7);
		handView.update();
				
		this.setFocusable(true);
		this.requestFocus();
	}
	
	/**
	 * Registers the listener for the Menu. More listeners to be added.
	 * @param menuController The menu listener
	 */
	public void registerListeners(GameMenuController menuController){
	       Component[] menu = menuPanel.getComponents(); //components displayed on menu bar
	       for(Component menuComponent : menu) {
	    	  if(menuComponent instanceof JMenu){
	    		  Component[] itemComponents = ((JMenu) menuComponent).getMenuComponents(); 
	    		  for(Component item: itemComponents){
	    			  AbstractButton button = (AbstractButton) item;
	    			  button.addActionListener(menuController);
	    		  }
	    	  }
	       }
	}
}
