package Game;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class MenuView extends JMenuBar {

	private JMenu gameMenu;
	private JMenu helpMenu;
	
	public MenuView(){
		//game menu
		gameMenu = new JMenu("Game");
		gameMenu.setMnemonic('G');
		//help menu
		helpMenu = new JMenu("Help");
		helpMenu.setMnemonic('H');
		
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.add(gameMenu);
		this.add(helpMenu);
	}
}
