package Game;

/**
 * Basic idea for the domino, subject to change
 */

public class Domino implements Comparable<Domino> {

	/** The ends of the domino */
	private int[] end;
	
	/**Holds the boolean value that determines if domino is a double*/
	private boolean isDouble;
	
	/** True if he A side of the Domino is facing out */
	private boolean endAOpen;
	
	/**
	 * Constructs a Domino consisting of two ends containing integer values.
	 * @param endA An integer value
	 * @param endB An integer value
	 */
	public Domino(int endA, int endB){
		this.end = new int[]{endA, endB};
		this.endAOpen = false;
	}
	
	public Domino(Domino d){
		this(d.getEndA(), d.getEndB());
	}
	
	/**
	 * Returns end A.
	 * @return An integer value
	 */
	public int getEndA(){
		return this.end[0];
	}
	
	/**
	 * Returns end B.
	 * @return An integer value
	 */
	public int getEndB(){
		return this.end[1];
	}
	
	/**
	 * Returns the sum of both ends
	 * @return An integer value
	 */
	public int value(){
		return getEndA() + getEndB();
	}
	
	/**
	 * Returns a boolean representing whether the domino
	 * is a double or not
	 * @return
	 */
	public boolean isDouble(){
		return getEndA() == getEndB();
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
		return (getEndA() > other.getEndA()) ? 1 
				: (getEndA() < other.getEndA()) ? -1 
				: (getEndB() > other.getEndB()) ? 1
				: (getEndB() < other.getEndB()) ? -1 : 0;
	}
	
	/**
	 * A boolean value representing the "flipping" of the Domino.
	 */
	public void flipEndA(){
		this.endAOpen = (this.endAOpen) ? false : true;
	}
	
	/**
	 * Returns an boolean value specifying if the A end of the Domino
	 * 		   is "Open". 
	 * @return A boolean value
	 */
	public boolean isAOpen(){
		return this.endAOpen;
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
		return (isAOpen()) ? "[" + getEndB() + "|" + getEndA() + "]" 
				: "[" + getEndA() + "|" + getEndB() + "]";
	}	
}

