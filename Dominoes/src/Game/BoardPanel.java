package Game;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BoardView extends JPanel{

	public BoardView(){
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
}
