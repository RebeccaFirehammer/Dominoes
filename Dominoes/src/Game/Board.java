package Game;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {

	/**
	 * The spokes on the game board
	 */
	private ArrayList<Spoke> spokes;
	
	/**
	 * The spinner on the game board
	 */
	private Domino spinner;
	
	/**
	 * Constructs a Domino game board consisting of a spinner and spokes.
	 * @param spinner A Domino object representing the first Domini played
	 */
	public Board(Domino spinner){
		this.spinner = spinner;
		Spoke[] spoke = {new Spoke(this.spinner.getEndA()), new Spoke(this.spinner.getEndA()),
						new Spoke(this.spinner.getEndA()), new Spoke(this.spinner.getEndA())};
		this.spokes = new ArrayList<Spoke>(Arrays.asList(spoke));
	}
	
	/**
	 * Adds a new Domino to the specified spoke if the Domino was a valid move
	 * otherwise nothing happens.
	 * @param An integer value representing the spoke to add a Domino to
	 * @param d A Domino object representing the Domino to be added
	 */
	public void addDomino(int spokeNum, Domino d){
		if(spokeNum >= 0 && spokeNum < this.spokes.size()){
			this.spokes.get(spokeNum).addDomino(d);
		}
	}
	
	/**
	 * Returns all the Dominoes currently on the board in an ArrayList.
	 * @return An ArrayList of Domino objects representing all the Dominoes on the board
	 */
	public ArrayList<Domino> clearBoard(){
		ArrayList<Domino> temp = new ArrayList<Domino>();
		temp.add(this.spinner);
		this.spinner = null;
		for(Spoke s: spokes){
			temp.addAll(s.clearSpoke());
			this.spokes.remove(s);
		}
		return temp;
	}
}
