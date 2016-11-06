package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class ScorePanel extends JPanel{
	
	/**
	 * The Dominoes game model
	 */
	private GameModel model;

	
	private ArrayList<JPanel> playerPanels;
	
	public ScorePanel(GameModel model){
		this.model = model;
		playerPanels = new ArrayList<JPanel>();
		this.setLayout(new GridLayout(1, model.getPlayers().size()));
		
		//create a player panel for each player in the game
		for(Player p: model.getPlayers()){
			playerPanels.add(createPlayerPanel(p));
		}
		
		//add each player panel to the ScorePanel
		for(JPanel jp: playerPanels){
			this.add(jp);
		}
	}
	
	/**
	 * Creates the Panel for each player on the score board.
	 * @param player A player object that is used for panel data
	 * @return A JPanel object
	 */
	private JPanel createPlayerPanel(Player player){
		JPanel playerPanel = new JPanel();
		playerPanel.setLayout(new BorderLayout());
		
		//creates player name label
		JLabel playerName = new JLabel(player.getName());
		playerName.setHorizontalAlignment(SwingConstants.CENTER);
		playerName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		//creates "tick" panel
		RoundPanel roundScore = new RoundPanel();
		roundScore.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		//creates total score label
		JLabel totalScore = new JLabel("0");
		totalScore.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, Color.BLACK));
		totalScore.setHorizontalAlignment(SwingConstants.CENTER);
		
		//adds components to the player Panel
		playerPanel.add(playerName, BorderLayout.NORTH);
		playerPanel.add(roundScore, BorderLayout.CENTER);
		playerPanel.add(totalScore, BorderLayout.SOUTH);
		return playerPanel;
	}
	
	public void updatePanel(GameModel model){
		//the component positions
		final int roundScore = 1, totalScore = 2;
		//update model
		this.model = model;
		//the player position
		int player = 0;
		
		if(this.playerPanels.size() == this.model.playerSize()){ //verify panel number matches player number
			for(JPanel p : this.playerPanels){
				//changes the rounds score panel
				if(p.getComponent(roundScore) instanceof RoundPanel){
					((RoundPanel) p.getComponent(roundScore)).updateScore(this.model.getPlayerTotalScore(player));
				}
				//changes the total score panel
				if(p.getComponent(totalScore) instanceof JLabel){
					p.remove(totalScore);
					JLabel newTotalScore = new JLabel(this.model.getPlayerTotalScore(player) + "");
					newTotalScore.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, Color.BLACK));
					newTotalScore.setHorizontalAlignment(SwingConstants.CENTER);
					p.add(newTotalScore, BorderLayout.SOUTH);
				}
				player++;
			}
		}
		validate();
		repaint();
	}
	
	
}