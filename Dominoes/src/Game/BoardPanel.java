package Game;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel{

	public BoardPanel(){
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
}
