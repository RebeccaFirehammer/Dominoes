package Game;

import java.util.*;

public class PlayerAI extends Player{
	
	/*
	 * The PlayerAI utilizes HashMaps to associate a particular domino from
	 * its hand with a list of spokes that it can play the domino on.
	 * 
	 * In the case of scoring, the PlayerAI will reference the scorevalues HashMap
	 * with respect to the scorable HashMap in order to determine which spoke, when played on,
	 * will lead to the highest scoring play.
	 */
	
	/**
	 * A HashMap containing dominoes which can be played on the current board
	 * and a list of spokes which the domino can be played on
	 */
	private HashMap<Domino, List<Integer>> playable;
	
	/**
	 * A HashMap containing dominoes which can be score on the current board
	 * and a list of spokes which the domino will score on
	 */
	private HashMap<Domino, List<Integer>> scorable;
	
	/**
	 * A HashMap containing dominoes which can be score on the current board
	 * and a list of scores which the domino can make
	 */
	private HashMap<Domino, List<Integer>> scorevalues;
	
	/**
	 * The difficulty level of the AIPlayer
	 * Currently 1 or 2
	 */
	private int level;
	
	/**
	 * The highest scoring value possible
	 * Used to determine which scorable domino to play
	 */
	private int highValue;
	
	/**
	 * Flag to determine whether or not PlayerAI is able
	 * to make a valid play with current hand
	 * False if play possible
	 * True if no play is possible
	 */
	private boolean noPlay;
	
	public PlayerAI(String name, int level){
		super(name);
		this.level=level;
		highValue = 0;
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
		System.out.printf("\nPlayable dominos: " + playable.keySet() + "\nPlayable spokes: " + playable.values());
		findScore(board);	
		System.out.printf("\nScorable dominoes: " + scorable.keySet() + "\nScorable spokes: " + scorable.values() + "\n");
		chooseMove(board);
		System.out.printf("Current score: %d\n", getRoundScore());
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
			 * openSpokes is used to prevent the PlayerAI from trying to find
			 * moves on spokes that currently aren't open for play.
			 */
			int openSpokes = 2;
			if(board.getSpokes().get(0).size() > 0 && board.getSpokes().get(1).size() > 0){
				openSpokes = 4;
			}
			/*
			 * Check sides A and B of current Domino D against open spoke values. If a match is found,
			 * add the index to a list of playable spokes which will be associated with Domino D. 
			 */
			for(int i = 0; i < openSpokes; i++){
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
		if(playable.isEmpty()){
			noPlay = true;
			return;
		}
		for(Domino d : playable.keySet()){
			List<Integer> spokes = new ArrayList<Integer>();
			List<Integer> values = new ArrayList<Integer>();
			for(Integer index : playable.get(d)){
				/*
				 * Doesn't count the open pip value for spokes 2/3(E/W) if they are empty
				 */
				if(index > 1 && board.getSpokes().get(index).size() == 0){
					cmppip = 0;
				}
				else{
					cmppip = board.getSpokes().get(index).getOpenValue();
				}
				/*
				 * Only the spinner is on the board
				 */
				if(board.getSpokes().get(0).size() == 0 && board.getSpokes().get(1).size() == 0){
					//System.out.printf("Only the spinner is on the table. Pip value = %d\tBoard value = %d\n", cmppip, value);
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
					if(d.isDouble()){
						cmpval = (value - cmppip) + d.getEndA()*2;
					}
					else{
						if(d.getEndA() == cmppip){
							cmpval = (value - cmppip) + d.getEndB();
						}
						else{
							cmpval = (value - cmppip) + d.getEndA();
						}
					}
				}
				
				//System.out.printf("\nDomino %s: cmpval = %d\n", d, cmpval);
				if(cmpval > 0 && cmpval % 5 == 0){
					spokes.add(index);
					values.add(cmpval);
					if(cmpval > highValue){
						highValue = cmpval;
					}
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
		int playIndex;
		List<Domino> playableDominoes = new ArrayList<Domino>(playable.keySet());
		List<Domino> scorableDominoes = new ArrayList<Domino>(scorable.keySet());
		List<Domino> highValueDominoes = new ArrayList<Domino>();
		/*
		 * Level 1 AI selects move at random, prioritizing scoring moves
		 */
		if(level == 1){
			/*
			 * Unable to score, just select a playable domino at random
			 */
			if(scorable.isEmpty() && !(playable.isEmpty())){
				play = playableDominoes.get(random.nextInt(playableDominoes.size()));
				playIndex = playable.get(play).get(random.nextInt(playable.get(play).size()));
			}
			/*
			 * Score is possible, select a scoring domino at random
			 */
			else if(!(scorable.isEmpty())){
				/*for(Domino d : scorable.keySet()){
					for(Integer value : scorevalues.get(d)){
						if(value == highValue){
							highValueDominoes.add(d);
						}
					}
				}
				play = highValueDominoes.get(random.nextInt(highValueDominoes.size()));*/
				play = scorableDominoes.get(random.nextInt(scorableDominoes.size()));
				playIndex = scorable.get(play).get(random.nextInt(scorable.get(play).size()));
				/*(System.out.printf("%s\t%s\n", scorevalues.get(play), scorevalues.get(play).indexOf(highValue));
				playIndex = scorable.get(play).get(scorevalues.get(play).indexOf(highValue));*/
			}
			else{
				//System.out.println("No moves possible");
				return;
			}
			System.out.printf("Playing domino %s on spoke %d\n", play, playIndex);
			board.addToSpoke(playIndex, playDomino(getHand().indexOf(play)));
		}
		/*
		 * Level 2 AI plays the highest value domino it has if it can't score
		 * and plays the highest scoring domino if it can score, choosing a
		 * domino at random in the event of a tie
		 */
		else if(level == 2){
			/*
			 * Unable to score, get rid of most valuable domino, still plays on random spoke
			 */
			if(scorable.isEmpty() && !(playable.isEmpty())){
				int maxValue = 0;
				play = playableDominoes.get(0);
				for(Domino d : playableDominoes){
					if(d.value() > maxValue){
						maxValue = d.value();
						play = d;
					}
				}
				playIndex = playable.get(play).get(random.nextInt(playable.get(play).size()));
			}
			/*
			 * Score is possible, select highest scoring domino
			 */
			else if(!(scorable.isEmpty())){
				//int maxValue = 0;
				for(Domino d : scorable.keySet()){
					for(Integer value : scorevalues.get(d)){
						if(value == highValue){
							highValueDominoes.add(d);
						}
					}
				}
				play = highValueDominoes.get(random.nextInt(highValueDominoes.size()));
				playIndex = scorable.get(play).get(scorevalues.get(play).indexOf(highValue));
			}
			else{
				//System.out.println("No moves possible");
				return;
			}
			System.out.printf("Playing domino %s on spoke %d\n", play, playIndex);
			board.addToSpoke(playIndex, playDomino(getHand().indexOf(play)));
		}
	}
	
	
	/**
	 * Draws from the boneyard until a valid play is found
	 * @param board the game board
	 * @param b the boneyard
	 */
	private void playFromBoneyard(Board board, Boneyard b){
		Domino d;
		while(b.getBoneyard().size() > 0 && playable.isEmpty()){
			d = b.draw();
			addToHand(d);
			List<Integer> spokes = new ArrayList<Integer>();
			/*
			 * Check sides A and B of current Domino D against open spoke values. If a match is found,
			 * the domino is added to the playable HashMap
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
		}
	}
	
	/**
	 * Resets the playable and scorable lists.
	 */
	private void reset(){
		playable.clear();
		scorable.clear();
		highValue = 0;
	}
	
	public boolean noPlay(){
		return noPlay;
	}
	
	/**
	 * Returns a string representation of this class.
	 */
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
		int turn = 1, round = 1;
		boolean gameOver = false;
		PlayerAI player = new PlayerAI("AI", 1);
		PlayerAI player2 = new PlayerAI("AI 2", 2);
		/*
		 * Two AI players play against each other
		 */
		while(!gameOver){
			Boneyard btest = new Boneyard(6);
			btest.shuffle();
			Domino spin = new Domino(btest.draw());
			/*
			 * Draw from boneyard until a valid double is found, then
			 * create a new board using that double as the spinner.
			 */
			while(!(spin.isDouble())){
				btest.add(spin);
				btest.shuffle();
				spin = btest.draw();
			}
			Board board = new Board(spin);
			player.addToHand(btest.drawHand(7));
			player2.addToHand(btest.drawHand(7));
			/*
			 * Play through a round until either player's hand is empty or no more plays are possible
			 */
			while(!(player.isHandEmpty() || player2.isHandEmpty())){
				System.out.printf("----------Round %d Turn %d----------\n", round, turn);
				System.out.println("Current board state");
				System.out.println(board.toString());
				System.out.println("Player 1's turn");
				System.out.println(player.getHand());
				player.takeTurn(board, btest);
				/*
				 * Check if Player 1 has scored and if their score
				 * is enough to win the game.
				 */
				if(board.getBoardValue() > 0 && board.getBoardValue() % 5 ==0){
					player.addPoints(board.getBoardValue());
					System.out.printf("Player 1's score is round: %d\ttotal: %d\n", player.getRoundScore(), player.getTotalScore());
					if(player.getTotalScore() >= 250){
						gameOver = true;
						break;
					}
				}
				System.out.println("Current board state");
				System.out.println(board.toString());
				if(player.getHand().isEmpty()){
					break;
				}
				System.out.println("Player 2's turn");
				System.out.println(player2.getHand());
				player2.takeTurn(board, btest);
				/*
				 * Check if Player 2 has scored and if their score
				 * is enough to win the game.
				 */
				if(board.getBoardValue() > 0 && board.getBoardValue() % 5 == 0){
					player2.addPoints(board.getBoardValue());
					System.out.printf("Player 2's score is round: %d\ttotal: %d\n", player2.getRoundScore(), player2.getTotalScore());
					if(player2.getTotalScore() >= 250){
						gameOver = true;
						break;
					}
				}
				/*
				 * Check to see if both Player 1 and Player 2 are unable to make
				 * valid plays. 
				 */
				if(player.noPlay() && player2.noPlay()){
					System.out.println("Neither player is able to make a move, end of round.");
					break;
				}
				turn++;
			}
			if(gameOver){
				break;
			}
			/*
			 * If the round has ended due to a player emptying their hand,
			 * the other AI player gives their hand value (rounded down to nearest 5)
			 * to the winning player.
			 */
			int handScore = 0;
			if(player.isHandEmpty()){
				for(Domino d : player2.getHand()){
					handScore += d.value();
				}
				System.out.printf("Player 1 ends the round\nPlayer 2 hand value: %d\tPoints given: %d\n", handScore, (handScore - (handScore%5)));
				player.addPoints(handScore - (handScore%5));
				player2.clearHand();
			}
			else if(player2.isHandEmpty()){
				for(Domino d : player.getHand()){
					handScore += d.value();
				}
				System.out.printf("Player 2 ends the round\nPlayer 1 hand value: %d\tPoints given: %d\n", handScore, (handScore - (handScore%5)));
				player2.addPoints(handScore - (handScore%5));
				player.clearHand();
			}
			System.out.printf("Round Over, round scores: Player 1: %d\tPlayer2: %d\n", player.getRoundScore(), player2.getRoundScore());
			player2.clearRound();
			player.clearRound();
			round++;
			turn = 1;
		}
		System.out.printf("Game Over\nFinal Score: Player 1: %d\tPlayer2: %d\n", player.getTotalScore(), player2.getTotalScore());
	}
}