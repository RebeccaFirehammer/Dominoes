package Game;

import java.util.ArrayList;
import java.util.Arrays;

public class Player {

	/** The player's name */
	private String name;
	
	/** The [layer's score */
	private int score;
	
	/** The player's hand */
	private ArrayList<Domino> hand;
	
	/**
	 * Constructs a player consisting of a name, 
	 * an initial score of 0 and a empty hand of Dominos.
	 * @param name A string literal
	 */
	public Player(String name){
		this.name = name;
		this.score = 0;
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
	
	public String getName(){
		return this.name;
	}
	
	/**
	 * Adds points to this player's score.
	 * @param points An integer value
	 */
	public void addPoints(int points){
		this.score += points;
	}
	
	/**
	 * Returns the player's score.
	 * @return An integer value
	 */
	public int getScore(){
		return this.score;
	}
	
	/**
	 * Sets this player's score to zero.
	 */
	public void clearScore(){
		this.score = 0;
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
	
	public ArrayList<Domino> clearHand(){
		ArrayList<Domino> temp = this.hand;
		this.hand.clear();
		return temp;
	}
	
	public int handSize(){
		return this.hand.size();
	}
	
	public boolean isHandEmpty(){
		return this.hand.size() == 0;
	}

	public String toString(){
		return getName() + "\nScore: " + getScore() + "\nHand: " + this.hand;
	}		
}
