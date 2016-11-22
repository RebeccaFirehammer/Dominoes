package Game;

import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

@SuppressWarnings("serial")
public class HandView extends JPanel{

	private GameModel model;
	private String owner;	
	private ArrayList<Domino> hand;	
	private JLabel playerLabel;	
	private HandPanel handPanel;	
	private int width = 50;
	
	
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
		
		handPanel = new HandPanel(width, hand);
		this.add(handPanel, BorderLayout.CENTER);
	}
	
	public HandView(String owner){
		this(owner, new ArrayList<Domino>());
		
	}
	
	public HandView(GameModel model){
		this.model = model;
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setLayout(new BorderLayout());
		
		playerLabel = new JLabel(model.getPlayerName(0));
		playerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(playerLabel, BorderLayout.NORTH);
		
		handPanel = new HandPanel(width, model.getPlayers().get(0).getHand());
		this.add(handPanel, BorderLayout.CENTER);
		
	}
	
	public HandView(){
		this("Player Hand");			
		}
	
	public void setHeight(int h){
		this.width = h;
	}
	
	public void setHand(ArrayList<Domino> hand){
		this.hand = hand;
	}
	
	public void update(){
		int player = 0;
		this.owner = model.getPlayerName(player);
		this.hand = model.getPlayers().get(player).getHand();		
		playerLabel.setText(owner);
		handPanel.updateHand(hand);
		validate();
		repaint();
	}
	
	public Domino getDomino(DominoImage d){
		return handPanel.getDomino(d);
	}
}