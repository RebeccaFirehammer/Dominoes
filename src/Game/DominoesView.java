package Game;



/**
 * 
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
	
	private HandPanelController handController;
	
	private JPanel startPanel;
	
	private boolean screen;
	
	private BoardPanelController boardController;
	
	private ArrayList<ActiveLocation> actLocs;
	
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
		actLocs = new ArrayList<ActiveLocation>();
		
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
		this.getContentPane().setBackground(Color.BLACK);	
		//model.clearGame();
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
		setLayout(new BorderLayout(2, 2));
		this.model = model;

		this.boardPanel = new BoardPanel(model);
		
		boardPanel.setPreferredSize(new Dimension(750,1000));
		
		boardController = new BoardPanelController(actLocs, model);
		boardPanel.addMouseListener(boardController);		
		
		//scoreboard
		this.scorePanel = new ScorePanel(this.model);
		scorePanel.setPreferredSize(new Dimension(215,0));
		
		//hand
		HandPanelController control = new HandPanelController(this, model);
		this.handView = new HandView(this.model, control);
		handView.setPreferredSize(new Dimension(200,150));
		
		//add components
		this.setJMenuBar(menuPanel);
		
		this.add(boardPanel, BorderLayout.CENTER);
		this.add(scorePanel, BorderLayout.EAST);
		this.add(handView, BorderLayout.SOUTH);
		
		//test values for updating score board 
		scorePanel.updatePanel(model);
		//test values for updating players hand
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
		}else{
			actLocs = boardPanel.getActiveLocs();
		}
		try{
			update();
		}catch(NullPointerException e){
			//System.out.println("null");
		}
	}
	
	/**
	 * Paints Title of Game on Title Screen
	 * @param g
	 */
	public void title(Graphics g){
		final int x = this.getWidth() / 4, y = this.getHeight() / 4;
		Font fnt = new Font("arial", Font.BOLD, (x / 3));
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("DOMINOES", x + (x/15), y);
	}
	
	public void update(){
		handView.update();
		scorePanel.updatePanel(model);
		boardPanel.removeMouseListener(boardController);
		boardPanel.updateBoard(model);
		boardController = new BoardPanelController(actLocs, model);
		boardPanel.addMouseListener(boardController);
	}
}
