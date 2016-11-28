package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;

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
	
	private JPanel startPanel;
	
	private boolean screen;
	
	private enum Actions{
		START,
		RULES,
		OPTIONS,
		EXIT
	};
	
	/**
	 * Constructs a Dominoes View which contains all the GUI
	 * elements on the game.
	 * @param model The Game Model from which the GUI pulls the game information
	 */
	public DominoesView(){
		super("Dominoes");
		this.getContentPane().setBackground(Color.BLACK);
		
		
		//add icon
		ImageIcon dominoIcon = new ImageIcon("Images/Domino-icon.png");
		setIconImage(dominoIcon.getImage());
		
		//menu
		this.menuPanel = new MenuBar();
		this.model = new GameModel();		
		startScreen();
	}
	
	public void startScreen(){
		screen = true;	
		model.clearGame();
		this.getContentPane().removeAll();
		this.getContentPane().remove(menuPanel);
		this.setJMenuBar(null);
		startPanel = new JPanel(new GridBagLayout());
		startPanel.setOpaque(false);
	
		//Buttons layout
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;

		
		
		//Start button
		JButton start = new JButton("Start Game");
		start.setActionCommand(Actions.START.name());
		
		//Rules button
		JButton rules = new JButton("Rules");
		rules.setActionCommand(Actions.RULES.name());
		
		//Options button
		JButton options = new JButton("Options");
		options.setActionCommand(Actions.OPTIONS.name());
		
		//Exit button
		JButton exit = new JButton("Exit");
		exit.setActionCommand(Actions.EXIT.name());
		
		
		//add buttons
		startPanel.add(start, gbc);
		startPanel.add(rules, gbc);
		startPanel.add(options, gbc);
		startPanel.add(exit, gbc);
		
		//add button panel
		this.add(startPanel);
		this.validate();
		repaint();
	}
	
	public void gameScreen(GameModel model){
		screen = false;
		this.getContentPane().removeAll();
		//this.setBackground(Color.BLACK);
		setLayout(new BorderLayout(2, 2));
		this.model = model;
		
		//board
		Board board = new Board(new Domino(6,6)); //for testing board
		board.addToSpoke(0, new Domino(6,5));
		board.addToSpoke(0, new Domino(5,4));
		board.addToSpoke(0, new Domino(4,4));
		//board.addToSpoke(0, new Domino(4,3));
		board.addToSpoke(1, new Domino(6,3));
		board.addToSpoke(1, new Domino(3,3));
		board.addToSpoke(1, new Domino(3,5));
		board.addToSpoke(1, new Domino(5,2));
		board.addToSpoke(2, new Domino(6,4));
		board.addToSpoke(2, new Domino(4,1));
		board.addToSpoke(3, new Domino(6,0));
		board.addToSpoke(3, new Domino(0,0));
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
		this.revalidate();
		this.repaint();
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
	
	/**
	 * Registers the controllers on the title screen.
	 * @param controller
	 */
	public void registerListeners(ActionListener controller){
		Component[] menu = startPanel.getComponents();
		
		for(Component c: menu){
			if(c instanceof JButton){
				((JButton) c).addActionListener(controller);
			}
		}
	}
		
	/**
	 * Paints graphics components
	 */
	public void paint(Graphics g){
		super.paint(g);
		if(screen == true){
			title(g);
		}
	}
	
	/**
	 * Paints Title of Game on Title Screen
	 * @param g
	 */
	public void title(Graphics g){
		Font fnt = new Font("arial", Font.BOLD, 100);
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("DOMINOES", 220, 200);
	}
	
	public void update(){
		handView.update();
		scorePanel.updatePanel(model);
	}
}
