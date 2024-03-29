package Game;

import java.util.*;

import javax.swing.JOptionPane;


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
	 * The game board containing all the Dominoes that have been played.
	 */
	private Board board;
	
	
	/**
	 * The boneyard containing all possible Dominoes that can be played.
	 */
	private Boneyard boneyard;
	
	/**
	 * The player that is currently "playing"
	 */
	private int currentPlayer;
	
	/**
	 * The Active Domino being played
	 */
	private Domino active;
	
	/**
	 * The Active Spoke being played on
	 */
	private int spoke;
	
	private boolean gameOver;
	/**
	 * Constructs the model of the game
	 * @param players An ArrayList of Player objects representing the players 
	 * 		  of the game
	 * @param winningScore An integer value representing the score required
	 * 		  to win this game
	 */
	public GameModel(ArrayList<Player> players, int winningScore){
		boneyard = new Boneyard();
		board = new Board();
		this.players = players;
		this.winningScore = winningScore;
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
	 * Returns an ArrayList of player's in this game.
	 * @return An ArrayList of Player Objects
	 */
	public ArrayList<Player> getPlayers(){
		return (new ArrayList<Player>(players));
	}
	
	public void setBoard(Board board){
		this.board = board;
	}
	
	public Board getBoard(){
		return new Board(this.board);
	}
	
	/**
	 * Changes a specified player's name.
	 * @param playerNum An integer value specifying which player's name to change
	 * @param name A string literal specifying the new name of the player
	 */
	public void setPlayerName(int playerNum, String name){
		if(validPlayer(playerNum)){
			this.players.get(playerNum).setName(name);
		}
	}
	
	/**
	 * Returns the specified player's name.
	 * @param playerNum An integer value specifying which player's name to return
	 * @return A string literal representing the player's name
	 */
	public String getPlayerName(int playerNum){
		return this.players.get(playerNum).getName();
	}
	
	/**
	 * Adds a specified amount of Dominoes to a specified player's hand.
	 * @param player An integer value representing the specified player
	 * @param handSize An integer value representing the specified hand size
	 */
	public void addToPlayerHand(int player, int handSize){
		this.players.get(player).addToHand(this.boneyard.drawHand(handSize));
	}
	
	
	/**
	 * Returns the score required to win the game.
	 * @return An integer value representing the winning score.
	 */
	public int getWinningScore(){
		return winningScore;
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
	 * Changes the current player to a new specified current player,
	 * if the specified player does not exist, nothing changes.
	 * @param currentPlayer An integer value representing the new current player
	 */
	public void setCurrentPlayer(int currentPlayer){
		if(validPlayer(currentPlayer)){
			this.currentPlayer = currentPlayer;
		}
	}
	
	/**
	 * Changes the current player to a new specified player,
	 * if the specified player does not exist, nothing changes.
	 * @param currentPlayer A string literal specifying the new player
	 */
	public void setCurrentPlayer(String currentPlayer){
		for(int i = 0; i < players.size(); i++){
			if(players.get(i).getName().equalsIgnoreCase(currentPlayer)){
				this.currentPlayer = i;
			}
		}
	}
	
	/**
	 * Returns an integer value representing the player current "playing".
	 * @return An integer value
	 */
	public int CurrentPlayer(){
		return currentPlayer;
	}
	
	public void clearGame(){
		for(Player p: this.players){
			boneyard.add(p.clearHand());
			p.clearRound();
			p.clearTotalScore();
		}
		if(board.spokeCount() != 0){
			//boneyard.add(board.clearBoard());
		}
		
	}
	
	/**
	 * Creates a default set of specified players.
	 * @param playerNum The number of players to be created
	 */
	private void makePlayers(int playerNum){
		this.players.add(new Player("Player"));
		for(int i = 2; i <= playerNum; i++){
			players.add(new PlayerAI("AI " + i, (i%2) + 1));
		}
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
	
	/**
	 * sets the active domino to the players choice, passed by handPanel.
	 * @param d
	 */
	public void setActive(Domino d){
		this.active = d;
		System.out.println(d);
	}
	public Domino getActive(){
		return this.active;
	}
	public void setActiveSpoke(int spoke){
		this.spoke = spoke;
	}
	public int getActiveSpoke(){
		return this.spoke;
	}
	
	/**
	 * Ends the game
	 */
	
	public void gameOver(){
		JOptionPane.showMessageDialog(null, "Game Over! Winner is Player " + (currentPlayer + 1) + "." +
						"\nSelect \"New Game\" from the Menu Bar to start a new game.");
		gameOver = true;
	}
	
	/**
	 * Begins a new round
	 */
	public void newRound(){
		//board.clearBoard();
		boneyard = new Boneyard(6);
		boneyard.shuffle();
		Domino spin;
		for(Player p : players){
			p.clearRound();
			p.clearHand();
			p.addToHand(boneyard.drawHand(7));
			for(Domino d : p.getHand()){
				if(d.value() == 12){
					spin = p.playDomino(p.getHand().indexOf(d));
					board = new Board(spin);
				}
			}
		}
		setCurrentPlayer((currentPlayer + 1) % 4);
		takeTurn();
	}
	
	
	/**
	 * Ends the current round, cleaning up the board
	 */
	public void endRound(){
		int value = 0;
		for(int i = 0; i < playerSize(); i++){
			if(i == currentPlayer){
				continue;
			}
			for(Domino d : players.get(i).getHand()){
				value += d.value();
			}
		}
		addPoints(currentPlayer, value - (value%5));
		JOptionPane.showMessageDialog(null, "Round Over! Player " + (currentPlayer + 1) + " won this round and receives " +
					(value - (value%5)) + " points from the other players' hands.\n\nClick OK to start next round.");
		if(getPlayerTotalScore(currentPlayer) >= getWinningScore()){
			gameOver();
		}
		newRound();
	}
	
	/**
	 * Checks the state of the game to see if the round or game is over
	 */
	public void checkState(){
		if(!(players.get(currentPlayer).noPlay()) && validPoints(board.getBoardValue())){
			System.out.printf("Player %d has scored %d points!\n", (currentPlayer + 1), board.getBoardValue());
			addPoints(currentPlayer, board.getBoardValue());
		}
		if(getPlayerTotalScore(currentPlayer) >= winningScore){
			gameOver();
		}
		if(players.get(currentPlayer).getHand().size() == 0 ||
				(players.get(0).noPlay() && players.get(1).noPlay() && players.get(2).noPlay() && players.get(3).noPlay())){
			endRound();
		}
	}
	
	/**
	 * Passes the turn from Player to Player
	 */
	
	public void pass(){
		checkState();
		setCurrentPlayer((currentPlayer + 1) % 4);
		System.out.printf("Play passing to player %d\n", currentPlayer + 1);
		takeTurn();
	}
	
	/**
	 * Allows Players to take their turn
	 */
	public void takeTurn(){
		if(gameOver){
			return;
		}
		Player player = players.get(currentPlayer);
		if(player instanceof PlayerAI){
			System.out.printf("Player %d's turn\n", currentPlayer + 1);
			((PlayerAI)players.get(currentPlayer)).takeTurn(board, boneyard);
			pass();
		}
		else{
			player.checkPlay(board);
			if(player.noPlay()){
				System.out.printf("Player %d has no valid moves\n", currentPlayer + 1);
				pass();
			}
			if((getActive() != null && players.get(currentPlayer).getHand().contains(getActive()) && getActiveSpoke() >= 0)){
				if(board.getSpokes().get(getActiveSpoke()).isValidMove(getActive())){
					board.addToSpoke(getActiveSpoke(), player.playDomino(player.getHand().indexOf(getActive())));
					pass();
				}
				else{
					System.out.println("Not a valid play");
				}
			}
		}
		active = null;
	}
	
	/**
	 * Starts a new game of Dominoes
	 */

	public void newGame() {
		clearGame();
		boneyard = new Boneyard(6);
		boneyard.shuffle();
		Domino spin;
		//Randomly generate starting spinner
		/*Random random = new Random();
		int spinvalue = random.nextInt(7);
		for(Player p : players){
			p.addToHand(boneyard.drawHand(7));
			for(Domino d : p.getHand()){
				if(d.isDouble() && d.getEndA() == spinvalue){
					spin = p.playDomino(p.getHand().indexOf(d));
					board = new Board(spin);
					setCurrentPlayer((players.indexOf(p) + 1) % 4);
					System.out.printf("Player %d has the spinner, Player %d is active player\n", (players.indexOf(p) % 4) + 1, currentPlayer + 1);
				}
			}
		}*/
		//Starting spinner is always 6|6
		for(Player p : players){
			p.addToHand(boneyard.drawHand(7));
			for(Domino d : p.getHand()){
				if(d.value() == 12){
					spin = p.playDomino(p.getHand().indexOf(d));
					board = new Board(spin);
					setCurrentPlayer((players.indexOf(p) + 1) % 4);
					System.out.printf("Player %d has the spinner, Player %d is active player\n", (players.indexOf(p) % 4) + 1, currentPlayer + 1);
				}
			}
		}
		System.out.println(getWinningScore());
		takeTurn();
		//this.players = players;
		//System.out.println(players);
		/*this.boneyard = new Boneyard(6);
		boneyard.shuffle();
		for(int i = 0; i < this.playerSize(); i++){
			addToPlayerHand(i,7);
		}*/		
	}
	
}
