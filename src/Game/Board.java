package Game;


/**
 * Creates a Domino board containing a spinner and 4 Domino
 * spokes.
 */

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
		setSpinner(spinner);
	}
	
	/**
	 * Constructs a new Board from a Board object.
	 * @param board A board object
	 */
	public Board(Board board){
		this.spinner = board.getSpinner();
		this.spokes = board.getSpokes();
	}
	
	/**
	 * Constructs an empty board.
	 */
	public Board(){
		this(new Domino(-1, -1));
	}
	
	/**
	 * Locations of the North and South spokes
	 */
	private final int NORTH = 0, SOUTH = 1;
	
	/**
	 * Changes the spinner to a new spinner if the current spinner is currently null
	 * otherwise nothing happens.
	 * @param Spinner A Domino object representing the new spinner
	 */
	public void setSpinner(Domino spinner){
		if(spinner != null){
			this.spinner = spinner;
			this.spokes = new ArrayList<Spoke>(Arrays.asList(new Spoke[]{new Spoke(this.spinner.getEndA()),
					new Spoke(this.spinner.getEndA()), new Spoke(this.spinner.getEndA()), 
					new Spoke(this.spinner.getEndA())}));
		}else{
			this.spokes = new ArrayList<Spoke>();
		}
	}
	
	/**
	 * Returns the spinner Domino that represents the first Domino on the Board.
	 * @return A Domino object representing the spinner
	 */
	public Domino getSpinner(){
		return new Domino(this.spinner);
	}
	
	/**
	 * Returns the total of all the open pips on the board.
	 * @return An integer value representing the total value of the pips
	 * 		   currently open on the board.
	 */
	public int getBoardValue(){
		int value = 0;
		if(this.spokes.size() > 0){
			switch(spokeCount()){
			case 0:
				value = this.spinner.getEndA() * 2;
				break;
			case 1:
				value = (this.spinner.getEndA() * 2) + openPipsTotal();
				break;
			case 2: case 3: case 4:
				value = openPipsTotal();
				break;
			}
		}
		return value;
	}
	
	/**
	 * Returns an integer value representing the total of all the open pips 
	 * 		   on the board.
	 * @return An integer value specifying the total value of the open pips
	 */
	private int openPipsTotal(){
		int value = 0;
		for(Spoke s: this.spokes){
			if((s.size() - 1 >= 0) && s.getSpoke().get(s.size() - 1).isDouble()){
				value += s.getSpoke().get(s.size() - 1).value(); //add total value if double
			}else if(s.size() > 0){
				value += s.getOpenValue();
			}
		}
		return value;
	}
	
	/**
	 * Adds a new Domino to the specified spoke if the Domino was a valid move
	 * otherwise nothing happens.
	 * @param Index An integer value representing the spoke to add a Domino to
	 * @param d A Domino object representing the Domino to be added
	 */
	public void addToSpoke(int index, Domino d){
		switch(spokeCount()){
		case 0:
			if(index == NORTH || index == SOUTH){
				this.spokes.get(index).addDomino(d);
			}else{
				if(spokes.get(NORTH).isValidMove(d)){
					this.spokes.get(NORTH).addDomino(d);
				}else if(spokes.get(SOUTH).isValidMove(d)){
					this.spokes.get(SOUTH).addDomino(d);
				}
			}
			break;
		case 1:
			if(index == NORTH || index == SOUTH){
				this.spokes.get(index).addDomino(d);
			}
			break;
		case 2: case 3: case 4:
			if(index >= 0 && index <= 3){
				this.spokes.get(index).addDomino(d);
			}
			break;
		}
	}
	
	/**
	 * Returns the spokes on the board if the spinner exist otherwise
	 * a value of null is returned.
	 * @return An ArrayList of spoke objects
	 */
	public ArrayList<Spoke> getSpokes(){
		return new ArrayList<Spoke>(this.spokes);
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
		
	/**
	 * Returns the number of spokes currently on the board. 
	 * @return An integer value representing the number of spokes
	 */
	public int spokeCount(){
		int count = 0;
		if(getSpokes().size() > 0){
			for(Spoke s: getSpokes()){
				count += (s.getSpoke().size() > 0) ? 1 : 0;
			}
		}
		return count;
	}
	
	/**
	 * Returns a character representation of the of the direction the specified spoke
	 * is facing, N for north, S for south, E for east and W for west. Returns a ? if
	 * the specified spoke does not exist.
	 * @param index An integer value specifying the spoke direction to return
	 * @return A character value
	 */
	public char spokeDirection(int index){
		if(index >= 0 && index < this.spokes.size()){
			switch(index){
			case 0:
				return 'N';
			case 1:
				return 'S';
			case 2:
				return 'E';
			case 3:
				return 'W';
			}
		}
		return '?';
	}
	
	/**
	 * Returns a string literal representation of this Board object.
	 * @return A string literal
	 */
	public String toString(){
		String result = "";
		if(this.spinner != null){
			result += "Spinner: " + getSpinner().toString() + "\n";
			for(int i = 0; i < this.spokes.size(); i++){
				result += "Spoke " + spokeDirection(i) + ": " + getSpokes().get(i).toString() + "\n";
			}
			result += "Board Value: " + getBoardValue();
		}else{
			result += "Board Empty";
		}
		return result;
	}
}
