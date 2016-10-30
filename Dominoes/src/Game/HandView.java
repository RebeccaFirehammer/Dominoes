package Game;

import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

public class HandView extends JPanel{

	private String owner;
	
	private ArrayList<Domino> hand;
	
	private JLabel playerLabel;
	
	private JPanel handPanel;
	
	
	public HandView(String owner, ArrayList<Domino> hand){
		this.owner = owner;
		this.hand = hand;
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setLayout(new BorderLayout());
		
		//add player label
		playerLabel = new JLabel(owner);
		playerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(playerLabel, BorderLayout.NORTH);
		
		//add icon
//		ImageIcon imageIcon = new ImageIcon(new ImageIcon("Images/Domino-icon.png").getImage().getScaledInstance(60, 90, java.awt.Image.SCALE_SMOOTH));
//		JLabel imgLabel = new JLabel(imageIcon);
//		this.add(imgLabel, BorderLayout.LINE_START);
//		handPanel = new JPanel();
		
	}
	
	public HandView(String owner){
		this(owner, new ArrayList<Domino>());
	}
	
	public HandView(){
		this("Player Hand");
	}
	
}
