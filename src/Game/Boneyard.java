package Game;

/**
 * Creates a Bone-yard consisting of Dominos.
 * 
 */

import java.util.ArrayList;
import java.util.Collections;

public class Boneyard {
	
	/** An ArrayList of Dominos representing the bone-yard */
	private ArrayList<Domino> boneyard;
	
	/**
	 * Constructs a bone-yard of Dominos.
	 * @param sideSize An integer value specifying 
	 *        the highest size a Domino side can reach
	 */
	public Boneyard(int sideSize){
		boneyard = new ArrayList<Domino>();
		int currentSide = sideSize;
		
		for(int i = 0; i <= sideSize; i++){
			for(int j = currentSide; j >= 0; j--){
				boneyard.add(new Domino(currentSide, j));
			}
			currentSide--;
		}
	}
	
	/**
	 * Constructs boneyard with 6 as the highest side size
	 */
	public Boneyard(){
		this(6);
	}
	
	/**
	 * Shuffles the bone-yard
	 */
	public void shuffle(){
		Collections.shuffle(boneyard);
	}
	
	/**
	 * Returns a Domino from the bone-yard if the boneyard
	 * contains at least 1 Domino, otherwise returns null.
	 * @return A Domino object.
	 */
	public Domino draw(){
		if(boneyard.size() > 0){
			return boneyard.remove(0);
		}else{
			return null;
		}
		
	}
	
	/**
	 * Returns a specified number of Dominos 
	 * from the boneyard as an ArrayList.
	 * @param handSize An integer value specifying the
	 * 	      amount of Dominos to be returned
	 * @return An ArrayList of Domino objects
	 */
	public ArrayList<Domino> drawHand(int handSize){
		ArrayList<Domino> hand = new ArrayList<Domino>();
		if(boneyard.size() < handSize || handSize < 0){
			handSize = boneyard.size();
		}
		for(int i = 0; i < handSize; i++){
			hand.add(draw());
		}
		return hand;
	}
	
	/**
	 * Adds a domino to this boneyard.
	 * @param d A domino object
	 */
	public void add(Domino d){
		boneyard.add(d);
	}
	
	/** 
	 * Adds an ArrayList of dominos to this boneyard
	 * @param dominos An ArrayList of dominos
	 */
	public void add(ArrayList<Domino> dominos){
		for(Domino d: dominos){
			add(d);
		}
	}
	
	/**
	 * Returns a string representing of this bone-yard.
	 * @return A string literal
	 */
	public String toString(){
		//Collections.sort(boneyard); 
		String result = "BONEYARD\n";
		for(int i = 0; i < boneyard.size(); i++){
			result += boneyard.get(i).toString() + "\n\n";
		}
		return result;
	}
}
