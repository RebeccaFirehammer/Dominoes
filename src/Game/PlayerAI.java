package Game;

import java.util.*;

public class PlayerAI extends Player{
	
	private HashMap<Domino, List<Integer>> playable;
	
	private HashMap<Domino, List<Integer>> scorable;
	
	private int level;
	
	public PlayerAI(String name, int level){
		super(name);
		this.level=level;
		playable = new HashMap<Domino, List<Integer>>();
		scorable = new HashMap<Domino, List<Integer>>();
	}
	
	/**
	 * Plays a turn for the round by finding legal moves and making
	 * a play decision based on various factors
	 * @param board the game board
	 */
	public void takeTurn(Board board, Boneyard b){
		findMove(board);
		if(playable.isEmpty()){
			playFromBoneyard(board, b);
		}
		findScore(board, playable);	
		//chooseMove(board, playable, scorable);
		//play(board, playable, scorable);
		//reset();
	}
	
	/**
	 * Scans current open values and compares the value of the dominoes in its
	 * hand to that of the open values to find playable moves
	 * @param board the game board
	 */
	private void findMove(Board board){
		for(Domino d : getHand()){
			List<Integer> spokes = new ArrayList<Integer>();
			/*
			 * Check sides A and B of current Domino D against open spoke values. If a match is found,
			 * add the index to a list of playable spokes which will be associated with Domino D. 
			 */
			for(int i=0; i<board.getSpokes().size(); i++){
				if(d.getEndA() == board.getSpokes().get(i).getOpenValue()){
					spokes.add(i);
				}
				else if(d.getEndB()==board.getSpokes().get(i).getOpenValue()){
					spokes.add(i);
				}
			}
			if(!spokes.isEmpty()){
				playable.put(d, spokes);
			}
		}
	}
	
	/**
	 * Calculates among those dominoes that are playable which ones can
	 * score points when played
	 * @param board the game board
	 * @param playable an ArrayList of dominoes that can be legally played
	 */
	
	private void findScore(Board board, HashMap<Domino, List<Integer>> playable){
		int value = board.getBoardValue();
		/*
		 * cmpval and cmppip are two variables used for comparison
		 * cmppip is the open value of the current spoke being checked for scoring plays
		 * cmpval is equivalent to the current board value minus cmppip plus the value of the domino to be played
		 */
		int cmpval, cmppip;
		for(Domino d : playable.keySet()){
			List<Integer> spokes = new ArrayList<Integer>();
			for(Integer index : playable.get(d)){
				cmppip = board.getSpokes().get(index).getOpenValue();
				//Only the spinner is on the board
				if(board.getSpokes().get(0).size()==0 && board.getSpokes().get(1).size()==0){
					if(d.getEndA() == cmppip){
						cmpval = value + d.getEndB();
					}
					else{
						cmpval = value + d.getEndA();
					}
				}
				//One side of the spinner is open; checking for plays on open spoke
				else if((board.getSpokes().get(0).size()==0 && board.getSpokes().get(1).size() > 0 && board.getSpokes().get(index).size() == 0)
						|| (board.getSpokes().get(1).size()==0 && board.getSpokes().get(0).size() > 0 && board.getSpokes().get(index).size() == 0)){
					if(d.getEndA() == cmppip){
						cmpval = (value - cmppip*2) + d.getEndB();
					}
					else{
						cmpval = (value - cmppip*2) + d.getEndA();
					}
				}
				else{
					if(d.getEndA() == cmppip){
						cmpval = (value - cmppip) + d.getEndB();
					}
					else{
						cmpval = (value - cmppip) + d.getEndA();
					}
				}
				
				System.out.printf("Domino %s: cmpval = %d\n", d, cmpval);
				if(d.getEndA() == cmppip && cmpval % 5 == 0){
					spokes.add(index);
				}
				else if(d.getEndB() == cmppip && cmpval % 5 == 0){
					spokes.add(index);
				}
			}
			if(!spokes.isEmpty()){
				scorable.put(d, spokes);
			}
		}
	}
	
	/**
	 * Selects a domino to play from the existing playable and scorable dominoes	
	 * @param board the game board
	 * @param playable an ArrayList of dominoes that can be legally played
	 * @param scorable an ArrayList of playable dominoes that can also score points
	 */
	private void chooseMove(Board board, ArrayList<Domino> playable, ArrayList<Domino> scorable){
		if(level == 1){
			
		}
	}
	
	
	/**
	 * Draws from the boneyard until a valid play is found
	 * @param board the game board
	 * @param b the boneyard
	 */
	private void playFromBoneyard(Board board, Boneyard b){
		Domino d;
		do{
			d = b.draw();
			addToHand(d);
			List<Integer> spokes = new ArrayList<Integer>();
			/*
			 * Check sides A and B of current Domino D against open spoke values. If a match is found,
			 * the domino is played 
			 */
			for(int i=0; i < board.getSpokes().size(); i++){
				if(d.getEndA() == board.getSpokes().get(i).getOpenValue()){
					spokes.add(i);
				}
				else if(d.getEndB()==board.getSpokes().get(i).getOpenValue()){
					spokes.add(i);
				}
			}
			if(!spokes.isEmpty()){
				playable.put(d, spokes);
			}
		}while(b.getBoneyard().size() > 0 && playable.isEmpty());
	}
	
	/**
	 * Resets the playable and scorable lists
	 */
	
	public void reset(){
		playable.clear();
		scorable.clear();
	}

	public String toString(){
		return super.toString() + "\nLevel: " + level +
							"\nPlayable dominos: " + playable.keySet() + "Playable spokes: " + playable.values()
							+ "\nScorable dominoes: " + scorable.keySet() + "\nScorable spokes: " + scorable.values();
	}
	
	/*
	 * This main method is used to test the 
	 * PlayerAI, generating a board with a random spinner
	 * to start play
	 */
	public static void main(String args[]){
		Boneyard btest = new Boneyard(6);
		btest.shuffle();
		Domino spin = new Domino(btest.draw());
		while(!(spin.isDouble())){
			btest.add(spin);
			btest.shuffle();
			spin = btest.draw();
		}
		Board board = new Board(spin);
		PlayerAI player = new PlayerAI("AI", 1);
		player.addToHand(btest.drawHand(7));
		player.takeTurn(board, btest);
		System.out.println(player.toString());
		System.out.println(board.toString());
	    //ArrayList<Spoke> spokes = board.getSpokes();
		System.out.println(board.getSpokes().get(3).getOpenValue());
	}
}
