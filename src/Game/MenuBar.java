package Game;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar {

	/**
	 * The game menu
	 */
	private JMenu gameMenu;
	
	/**
	 * The help menu
	 */
	private JMenu helpMenu;
	
	/**
	 * Creates a new MenuPanel for the game
	 */
	public MenuBar(){
		//game menu
		gameMenu = new JMenu("Game");
		gameMenu.setMnemonic('G');
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.setMnemonic('E');
		gameMenu.add(exitItem);
		
		//help menu
		helpMenu = new JMenu("Help");
		helpMenu.setMnemonic('H');
		JMenuItem rulesItem = new JMenuItem("Rules");
		rulesItem.setMnemonic('R');
		helpMenu.add(rulesItem);
		

		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.add(gameMenu);
		this.add(helpMenu);
	}
}
