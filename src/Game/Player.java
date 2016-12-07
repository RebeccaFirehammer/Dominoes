package Game;

import java.util.ArrayList;

public class Player {

	/** The player's name */
	private String name;
	
	/** The Player's score for the current round */
	private int roundScore;
	
	/** The Player's score */
	private int totalScore;
	
	/** The player's hand */
	private ArrayList<Domino> hand;
	
	private boolean noPlay;
	
	/**
	 * Constructs a player consisting of a name, an initial round score of 0,
	 * an initial score of 0 and a empty hand of Dominoes.
	 * @param name A string literal specifying the players name
	 */
	public Player(String name){
		this.name = name;
		this.roundScore = 0;
		this.totalScore = 0;
		this.hand = new ArrayList<Domino>();
	}
	
	/** Constructs a player with a default name */
	public Player(){
		this("Default");
	}
	
	/**
	 * Changes this player's name.
	 * @param name A string literal
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * Returns the name of the player.
	 * @return A string literal specifying the players name
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Adds points to this player's score.
	 * @param points An integer value
	 */
	public void addPoints(int points){
		this.roundScore += points;
		this.totalScore += points;
	}
	
	/**
	 * Returns the player's score for the current round.
	 * @return An integer value representing the player's current round score
	 */
	public int getRoundScore(){
		return this.roundScore;
	}
	
	/**
	 * Resets the player's round score to 0.
	 */
	public void clearRound(){
		this.roundScore = 0;
	}
	
	/**
	 * Returns the player's score.
	 * @return An integer value
	 */
	public int getTotalScore(){
		return this.totalScore;
	}
	
	/**
	 * Sets this player's score to zero.
	 */
	public void clearTotalScore(){
		this.totalScore = 0;
	}
	
	/**
	 * Adds a Domino to this player's hand
	 * @param d A domino object
	 */
	public void addToHand(Domino d){
		this.hand.add(d);
	}
	
	/**
	 * Adds an ArrayList of Dominoes to the player's hand.
	 * @param dominoes An ArrayList of Domino objects
	 */
	public void addToHand(ArrayList<Domino> dominoes){
		this.hand.addAll(dominoes);
	}
	
	/**
	 * Returns the a Domino peice from the player's hand
	 * @return A Domino object
	 */
	public Domino getDomino(int d){
		return (new Domino(this.hand.get(d)));
	}
	
	public ArrayList<Domino> getHand(){
		return (new ArrayList<Domino>(this.hand));
	}
	
	/**
	 * Removes a Domino from this player's hand.
	 * Returns A Domino from this player's hand
	 * @param d An integer value
	 * @return A domino object
	 */
	public Domino playDomino(int d){
		return this.hand.remove(d);
	}
	
	/**
	 * Removes all Dominoes from the current players hand 
	 * and returns them as an ArrayList of Dominoes.
	 * @return An ArrayList of Domino objects
	 */
	public ArrayList<Domino> clearHand(){
		ArrayList<Domino> temp = this.hand;
		this.hand.clear();
		return temp;
	}
	
	/**
	 * Returns the number of Dominoes in the player's hand
	 * @return An integer value representing the number of Dominoes
	 * 		   in the player's hand
	 */
	public int handSize(){
		return this.hand.size();
	}
	
	/**
	 * Returns true if the hand is empty otherwise false.
	 * @return A boolean value specifying if the player's hand is empty
	 */
	public boolean isHandEmpty(){
		return this.hand.size() == 0;
	}
	
	public boolean noPlay(){
		return noPlay;
	}
	
	public void checkPlay(Board b){
		int openSpokes = 2;
		if(b.getSpokes().get(0).size() > 0 && b.getSpokes().get(1).size() > 0){
			openSpokes = 4;
		}
		for(Domino d : getHand()){
			for(int i = 0; i < openSpokes; i++){
				if(d.getEndA() == b.getSpokes().get(i).getOpenValue() ||
						d.getEndB() == b.getSpokes().get(i).getOpenValue()){
					noPlay = false;
					return;
				}
			}
		}
		noPlay = true;
	}

	/**
	 * A string literal representation of this player.
	 */
	public String toString(){
		return getName() + "\nScore: " + getTotalScore() + "\nHand: " + this.hand;
	}		
}
