package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ScoreView extends JPanel{
	
	private GameModel model;
	
	public ScoreView(GameModel model){
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.model = model;
		
		for(Player p: model.getPlayers()){
			this.add(new JLabel(p.getName(), SwingConstants.CENTER));
		}
	}
}
