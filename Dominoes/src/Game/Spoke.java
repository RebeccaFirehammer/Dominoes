package Game;

import java.util.ArrayList;

public class Spoke {

	/**
	 * The open pips that can be played off of
	 */
	private int openPips;
	
	/**
	 * The spoke of Dominoes
	 */
	private ArrayList<Domino> spoke;
	
	/**
	 * Contructs a new spoke 
	 * @param openPips An integer value representing the pips that can 
	 * 		  be played off of
	 */
	public Spoke(int openPips){
		this.openPips = openPips;
		this.spoke = new ArrayList<Domino>();
	}
		
	/**
	 * Returns the value of the side of the open Domino that can be played off of.
	 * @return An integer value representing the open pips
	 */
	public int getOpenValue(){
		if(getSpoke().size() > 0){
			return this.openPips;
		}
		return 0;
	}
	
	/**
	 * Adds a Domino to the spoke if the specified Domino
	 * 	is a valid move, otherwise the no action occurs.
	 * @param d A Domino object to be added
	 */
	public void addDomino(Domino d){
		if(isValidMove(d)){
			if(this.openPips == d.getEndB()){
				d.flip();
			}
			this.openPips = d.getEndB();
			this.spoke.add(d);
		}
	}
	
	/**
	 * Returns a spoke containing Domino objects.
	 * @return An ArrayList of Domino objects
	 */
	public ArrayList<Domino> getSpoke(){
		return (new ArrayList<Domino>(this.spoke));
	}
	
	/**
	 * Returns true if the domino has a side that can be added to the spoke and 
	 * 		   false if not.
	 * @param d A domino object
	 * @return A boolean value representing if the move is valid
	 */
	public boolean isValidMove(Domino d){
		return (d.getEndA() == this.openPips || d.getEndB() == this.openPips) ? true : false;
	}
	
	/**
	 * Clears all Dominoes from the spoke and returns them in an ArrayList.
	 * @return An ArrayList of Domino objects
	 */
	public ArrayList<Domino> clearSpoke(){
		ArrayList<Domino> temp = this.spoke;
		this.spoke.clear();
		return temp;
	}
	
	/**
	 * Returns a string representation of this Spoke object.
	 * @return A string literal value specifying information about this object
	 */
	public String toString(){
		String result = "";
		for(Domino d : spoke){
			result += d.toString();
		}
		return result;
	}
}
