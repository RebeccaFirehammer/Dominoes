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
		this.spinner = null;
		setSpinner(spinner);
	}
	
	/**
	 * Changes the spinner to a new spinner if the current spinner is currently null
	 * otherwise nothing happens.
	 * @param Spinner A Domino object representing the new spinner
	 */
	public void setSpinner(Domino spinner){
		if(this.spinner == null){
			this.spinner = spinner;
			this.spokes = new ArrayList<Spoke>(Arrays.asList(new Spoke[]{new Spoke(this.spinner.getEndA()),
					new Spoke(this.spinner.getEndA()), new Spoke(this.spinner.getEndA()), 
					new Spoke(this.spinner.getEndA())}));
		}
	}
	
	/**
	 * Returns the spinner Domino that represents the first Domino on the Board.
	 * @return A Domino object representing the spinner
	 */
	public Domino getSpinner(){
		return new Domino(this.spinner);
	}
	
	public int getBoardValue(){
		int value = 0;
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
			System.out.println("Spoke value: " + s.getOpenValue());
			value += s.getOpenValue();
		}
		return value;
	}
	
	/**
	 * Adds a new Domino to the specified spoke if the Domino was a valid move
	 * otherwise nothing happens.
	 * @param An integer value representing the spoke to add a Domino to
	 * @param d A Domino object representing the Domino to be added
	 */
	public void addToSpoke(int index, Domino d){
		switch(spokeCount()){
		case 0:
			this.spokes.get(0).addDomino(d);
			break;
		case 1:
			if(index == 0){
				this.spokes.get(index).addDomino(d);
			}else{
				this.spokes.get(1).addDomino(d);
			}
			break;
		case 2:
			if(index == 0 || index == 1){
				this.spokes.get(index).addDomino(d);
			}else{
				this.spokes.get(2).addDomino(d);
			}
			break;
		case 3:
			if(index == 0 || index == 1 || index == 2){
				this.spokes.get(index).addDomino(d);
			}else{
				this.spokes.get(3).addDomino(d);
			}
			break;
		default:
			if(index >= 0 && index < spokeCount()){
				this.spokes.get(index).addDomino(d);
			}
		}
	}
	
	/**
	 * Returns the spokes on the board if the spinner exist otherwise
	 * a value of null is returned.
	 * @return An ArrayList of spoke objects
	 */
	public ArrayList<Spoke> getSpokes(){
		if(this.spinner != null){
			return new ArrayList<Spoke>(this.spokes);
		}
		return null;
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
		for(Spoke s: getSpokes()){
			count += (s.getSpoke().size() > 0) ? 1 : 0;
		}
		return count;
	}
	
	public String toString(){
		String result = "";
		result += "Spinner: " + getSpinner().toString() + "\n";
		for(int i = 0; i < this.spokes.size(); i++){
			result += "Spoke " + i + ": " + getSpokes().get(i).toString() + "\n";
		}
		result += "Board Value: " + getBoardValue();
		return result;
	}
	
	public static void main(String args[]){
		Board board = new Board(new Domino(6,6));
		System.out.println(board.getSpinner().toString());
		board.addToSpoke(0, new Domino(6,3));
		System.out.println(board.spokeCount());
		System.out.println(board.getBoardValue());
		System.out.println(board.toString());
		board.addToSpoke(0, new Domino(3,4));
		System.out.println(board.toString());
		board.addToSpoke(1, new Domino(6,2));
		System.out.println(board.toString());
		board.addToSpoke(2, new Domino(6,4));
		System.out.println(board.toString());
		board.addToSpoke(3, new Domino(6,5));
		System.out.println(board.toString());
		
	}
}
