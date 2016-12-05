package Game;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;

/**
 * This class replicates the steps of a new game, from start to finish,
 *  with a user Player and 3 AI Players
 */
public class GameInit{
	private boolean noPlay;
	
	public boolean noPlay(){
		return noPlay;
	}
	public static void main(String args[]){
		int turn = 1, round = 1;
		Boolean gameOver = false;
		String player;
		ArrayList<Player> players = new ArrayList<Player>();
		
		//eventually need to replace with GUI text box
		Scanner scan = new Scanner(System.in);
		System.out.println("Name - Player 1: ");
		player = scan.nextLine();
		
		//create 1 user player & 3 AI players of each difficulty
		Player one = new Player(player);
		Player two = new PlayerAI("CPU 1", 1);
		Player three = new PlayerAI("CPU 2", 2);
		Player four = new PlayerAI("CPU 3", 3);
		
		//add players to game
		players.add(one);
		players.add(two);
		players.add(three);
		players.add(four);
		
		//winning score set to 250
		GameModel model = new GameModel(players, 250);
		DominoesView view = new DominoesView();
		StartPanelController controller = new StartPanelController(model, view);
		
		
		while(!gameOver){
			//initialize boneyard of double-6 dominoes
			Boneyard yard = new Boneyard(6);
			yard.shuffle();
			//finds spinner by drawing from boneyard
			Domino spin = new Domino(yard.draw());
			while(!(spin.isDouble())){
				yard.add(spin);
				yard.shuffle();
				spin = yard.draw();
			}	
			//add spinner to board
			Board board = new Board(spin);
			//deal dominoes to players
			one.addToHand(yard.drawHand(7));
			two.addToHand(yard.drawHand(7));
			three.addToHand(yard.drawHand(7));
			four.addToHand(yard.drawHand(7));
			while(!(one.isHandEmpty() || two.isHandEmpty() || three.isHandEmpty() || four.isHandEmpty())){
				//play game here
				System.out.printf("----------Round %d Turn %d----------\n", round, turn);
				System.out.println("Current board state");
				System.out.println(board.toString());
				System.out.println("Player 1's turn");
				System.out.println(one.getHand());
				/*
				 * Check if Player 1 has scored and if their score
				 * is enough to win the game.
				 */
				if(board.getBoardValue() > 0 && board.getBoardValue() % 5 ==0){
					one.addPoints(board.getBoardValue());
					System.out.printf("Player 1's score is round: %d\ttotal: %d\n", one.getRoundScore(), one.getTotalScore());
					if(one.getTotalScore() >= 250){
						gameOver = true;
						break;
					}
				}
				System.out.println("Current board state");
				System.out.println(board.toString());
				if(one.getHand().isEmpty()){
					break;
				}
				System.out.println("Player 2's turn");
				System.out.println(two.getHand());
				two.takeTurn(board, yard);
				/*
				 * Check if Player 2 has scored and if their score
				 * is enough to win the game.
				 */
				if(board.getBoardValue() > 0 && board.getBoardValue() % 5 == 0){
					two.addPoints(board.getBoardValue());
					System.out.printf("Player 2's score is round: %d\ttotal: %d\n", two.getRoundScore(), two.getTotalScore());
					if(two.getTotalScore() >= 250){
						gameOver = true;
						break;
					}
				}
				/*
				 * Check to see if both Player 1 and Player 2 are unable to make
				 * valid plays. 
				 */
				if(one.noPlay() && two.noPlay()){
					System.out.println("Neither player is able to make a move, end of round.");
					break;
				}
				turn++;
			}
			if(gameOver){
				break;
			}
			}
		}

		//run GUI
		//view.registerListeners(controller);
		//view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//view.setSize(1000, 800);
		//view.setVisible(true);
		
	}
}
