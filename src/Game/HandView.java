package Game;

import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;

@SuppressWarnings("serial")
public class HandView extends JPanel{

	private GameModel model;
	private String owner;	
	private ArrayList<Domino> hand;	
	private JLabel playerLabel;	
	private HandPanel handPanel;	
	private int width = 50;
	private HandPanelController handControl;
	JLabel statusBar;
	
	
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
		
		handPanel = new HandPanel(width, model, handControl);
		this.add(handPanel, BorderLayout.CENTER);
	}
	
	public HandView(String owner){
		this(owner, new ArrayList<Domino>());
		
	}
	
	public HandView(GameModel model, HandPanelController control){
		this.model = model;
		handControl = control;
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setLayout(new BorderLayout());
		
		playerLabel = new JLabel(model.getPlayerName(0));
		playerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(playerLabel, BorderLayout.NORTH);
		
		handPanel = new HandPanel(width, model, control);
		this.add(handPanel, BorderLayout.CENTER);
		
		statusBar = new JLabel("Pip Total: " + model.getBoard().openPipsTotal());
		this.add(statusBar, BorderLayout.SOUTH);
		
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
	
	public void setStatus(String s){
		statusBar.setText(s);
	}
	
	public void update(){
		int player = 0;
		this.owner = model.getPlayerName(player);
		this.hand = model.getPlayers().get(player).getHand();		
		playerLabel.setText(owner);
		handPanel.updateHand(player);
	}
	
	public Domino getDomino(DominoImage d){
		return handPanel.getDomino(d);
	}
}