package Game;

import java.util.ArrayList;

public class GameModel {
	
	/**
	 * The players of this game.
	 */
	private ArrayList<Player> players;
	
	/**
	 * The Score require to win this game.
	 */
	private int winningScore;
	
	/**
	 * Constructs the model of the game
	 * @param players An ArrayList of Player objects representing the players 
	 * 		  of the game
	 * @param winningScore An integer value representing the score required
	 * 		  to win this game
	 */
	public GameModel(ArrayList<Player> players, int winningScore){
		this.players = players;
		this.winningScore = (winningScore <= 5 && winningScore % 5 == 0 ) ? winningScore : 250;
	}
	
	/**
	 * Constructs the model of the game
	 * @param playersAn ArrayList of Player objects representing the players 
	 * 		  of the game
	 */
	public GameModel(ArrayList<Player> players){
		this(players, 250);
	}
	
	/**
	 * Constructs the model of the game
	 * @param winningScore An integer value representing the score required
	 * 		  to win this game
	 */
	public GameModel(int winningScore){
		this(new ArrayList<Player>(), winningScore);
		makePlayers(4);
	}
	
	/**
	 * Constructs a model of the game with default parameters.
	 */
	public GameModel(){
		this(250);
	}
	
	/**
	 * Creates a default set of specified players.
	 * @param playerNum The number of players to be created
	 */
	private void makePlayers(int playerNum){
		for(int i = 1; i <= playerNum; i++){
			this.players.add(new Player("Player" + i));
		}
	}
	
	public ArrayList<Player> getPlayers(){
		return (new ArrayList<Player>(players));
	}
	
	/**
	 * Returns the score required to win the game.
	 * @return An integer value representing the winning score.
	 */
	public int getWinningScore(){
		return this.winningScore;
	}
	
	/**
	 * Adds points to the specified players score.
	 * @param playerNum An integer value representing the specified player
	 * @param points An integer value representing the points to be added.
	 */
	public void addPoints(int playerNum, int points){
		if(validPlayer(playerNum) && validPoints(points)){
			this.players.get(playerNum).addPoints(points);
		}
	}
	
	/**
	 * Returns the specified players current score.
	 * @param playerNum An integer value representing the specified player
	 * @return An integer value representing the players score
	 */
	public int getPlayerRoundScore(int playerNum){
		if(validPlayer(playerNum)){
			return this.players.get(playerNum).getRoundScore();
		}
		return 0;
	}
	
	
	/**
	 * Returns the specified players current score.
	 * @param playerNum An integer value representing the specified player
	 * @return An integer value representing the players score
	 */
	public int getPlayerTotalScore(int playerNum){
		if(validPlayer(playerNum)){
			return this.players.get(playerNum).getTotalScore();
		}
		return 0;
	}
	
	/**
	 * Returns the number of players in this game.
	 * @return An integer value representing the number of players
	 */
	public int playerSize(){
		return this.players.size();
	}
	
	/**
	 * Returns true if the player number exist and false otherwise
	 * @param playerNum An integer value representing the player number
	 * @return A boolean value
	 */
	private boolean validPlayer(int playerNum){
		return (playerNum < this.players.size() && playerNum >= 0 ) ? true : false;
	}
	
	/**
	 * Returns true if the points are a valid addition for this game and
	 * 			false if otherwise.
	 * @param points An integer value representing the points
	 * @return A boolean value
	 */
	private boolean validPoints(int points){
		return (points % 5 == 0) ? true: false;
	}
}
