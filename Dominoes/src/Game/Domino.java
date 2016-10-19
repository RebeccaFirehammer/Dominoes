package Game;

/**
 * Basic idea for the domino, subject to change
 */

import dominos.Domino;

public class Domino implements Comparable<Domino> {

	/** The ends of the domino */
	private int endA, endB;
	
	/**
	 * Constructs a Domino consisting of two ends containing integer values.
	 * @param endA An integer value
	 * @param endB An integer value
	 */
	public Domino(int endA, int endB){
		this.endA = endA;
		this.endB = endB;
	}
	
	/**
	 * Returns end A.
	 * @return An integer value
	 */
	public int getEndA(){
		return this.endA;
	}
	
	/**
	 * Returns end B.
	 * @return An integer value
	 */
	public int getEndB(){
		return this.endB;
	}
	
	/**
	 * Switches the values of the domino.
	 */
	public void flip(){
		int temp = this.endA;
		this.endA = this.endB;
		this.endB = temp;
	}

	/**
	 * Returns true if both ends of the domino are equal.
	 * Returns false if the ends are not equal.
	 * @return A boolean value
	 */
	public boolean isDouble(){
		return (this.endA == this.endB);
	}
	
	/**
	 * Compares this Domino to another Domino and...
	 * Returns 1 if this Domino is greater than another Domino.
	 * Returns -1 if this Domino is less than another Domino.
	 * Returns 0 if this Domino is equal to another Domino.
	 * @param other A Domino object
	 * @return An integer value
	 */
	public int compareTo(Domino other) {
		return (this.getEndA() > other.getEndA() && this.getEndA() > other.getEndB()) ? 1 : //returns 1 if end A is greater than both ends of other
				(this.getEndB() > other.getEndA() && this.getEndB() > other.getEndB()) ? 1 : //returns 1 if end B is greater than both ends of other
				(this.getEndA() < other.getEndA() && this.getEndA() < other.getEndB()) ? -1 :	//returns -1 if end A is less than both ends of other
				(this.getEndB() < other.getEndA() && this.getEndB() < other.getEndB()) ?-1 : 0; //returns -1 if end B is less than both ends of other
				//all other conditions return 0
	}
	
	/**
	 * Compares this Domino with another object and...
	 * Returns true if Dominos are equal.
	 * Returns false if Domino are not equal.
	 * @param other A Domino object
	 * @return A boolean value
	 */
	public boolean equals(Domino other){
		return this.getEndA() == other.getEndA() && this.getEndB() == other.getEndB();
	}
	
	/**
	 * Returns a string representation of this Domino object.
	 * @return A string literal
	 */
	public String toString(){
		return "[" + getEndA() + "|" + getEndB() + "]";
	}	
}
