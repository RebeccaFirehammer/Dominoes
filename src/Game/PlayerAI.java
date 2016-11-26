package Game;

import java.util.*;

public class PlayerAI extends Player{
	
	private HashMap<Domino, List<Integer>> playable;
	
	private HashMap<Domino, List<Integer>> scorable;
	
	private HashMap<Domino, List<Integer>> scorevalues;
	
	private int level;
	
	public PlayerAI(String name, int level){
		super(name);
		this.level=level;
		playable = new HashMap<Domino, List<Integer>>();
		scorable = new HashMap<Domino, List<Integer>>();
		scorevalues = new HashMap<Domino, List<Integer>>();
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
		//findScore(board);	
		chooseMove(board);
		reset();
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
			for(int i = 0; i < board.getSpokes().size(); i++){
				if(d.getEndA() == board.getSpokes().get(i).getOpenValue()){
					spokes.add(i);
				}
				else if(d.getEndB() == board.getSpokes().get(i).getOpenValue()){
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
	
	private void findScore(Board board){
		int value = board.getBoardValue();
		/*
		 * cmpval and cmppip are two variables used for comparison
		 * cmppip is the open value of the current spoke being checked for scoring plays
		 * cmpval is equivalent to the current board value minus cmppip plus the value of the domino to be played
		 */
		int cmpval, cmppip;
		for(Domino d : playable.keySet()){
			List<Integer> spokes = new ArrayList<Integer>();
			List<Integer> values = new ArrayList<Integer>();
			for(Integer index : playable.get(d)){
				cmppip = board.getSpokes().get(index).getOpenValue();
				//Only the spinner is on the board
				if(board.getSpokes().get(0).size() == 0 && board.getSpokes().get(1).size() == 0){
					System.out.printf("Only the spinner is on the table. Pip value = %d\tBoard value = %d\n", cmppip, value);
					if(d.getEndA() == cmppip){
						cmpval = value + d.getEndB();
					}
					else{
						cmpval = value + d.getEndA();
					}
				}
				/*
				 * One side of the spinner is open; checking for plays on the spinner
				 * cmppip is doubled to account for the spinner being open
				 */
				else if((board.getSpokes().get(0).size() == 0 && board.getSpokes().get(1).size() > 0 && board.getSpokes().get(index).size() == 0)
						|| (board.getSpokes().get(1).size() == 0 && board.getSpokes().get(0).size() > 0 && board.getSpokes().get(index).size() == 0)){
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
				if(/*(d.getEndA() == cmppip || d.getEndB() == cmppip) &&*/ cmpval % 5 == 0){
					spokes.add(index);
					values.add(cmpval);
				}
			}
			if(!spokes.isEmpty()){
				scorable.put(d, spokes);
				scorevalues.put(d, values);
			}
		}
	}
	
	/**
	 * Selects a domino to play from the existing playable and scorable dominoes	
	 * @param board the game board
	 * @param playable an ArrayList of dominoes that can be legally played
	 * @param scorable an ArrayList of playable dominoes that can also score points
	 */
	private void chooseMove(Board board){
		Domino play;
		Random random = new Random();
		int scoreval = 0, playIndex;
		List<Domino> playableDominoes = new ArrayList<Domino>(playable.keySet());
		//Level 1 AI selects move at random, prioritizing scoring moves
		if(level == 1){
			//Unable to score, just select a playable domino at random
			if(scorable.isEmpty()){
				play = playableDominoes.get(random.nextInt(playableDominoes.size()));
				playIndex = playable.get(play).get(random.nextInt(playable.get(play).size()));
				System.out.printf("Playing domino %s on spoke %d\n", play, playIndex);
				board.addToSpoke(playIndex, playDomino(getHand().indexOf(play)));
			}
			//Score is possible, select highest scoring domino
			else {
				for(Domino d : scorable.keySet()){
					for(Integer value : scorevalues.get(d)){
						if(value > scoreval){
							scoreval = value;
						}
					}
				}
			}
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
				else if(d.getEndB() == board.getSpokes().get(i).getOpenValue()){
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
							"\nPlayable dominos: " + playable.keySet() + "\nPlayable spokes: " + playable.values()
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
		while(!player.isHandEmpty()){
			player.takeTurn(board, btest);
			System.out.println(player.toString());
			System.out.println(board.toString());
		}
	}
}
