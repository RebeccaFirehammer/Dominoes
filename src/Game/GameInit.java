package Game;

import java.awt.Dimension;
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
		Boolean gameOver;
		int play;
		int spoke;
		ArrayList<Player> players = new ArrayList<Player>();
		Scanner scan = new Scanner(System.in);
		
		//create 1 user player & 3 AI players of each difficulty
		Player one = new Player("Player");
		Player two = new PlayerAI("CPU 1", 1);
		Player three = new PlayerAI("CPU 2", 2);
		Player four = new PlayerAI("CPU 3", 3);
		
		//add players to game
		players.add(one);
		players.add(two);
		players.add(three);
		players.add(four);
		
		//winning score set to 250
		//initialize model, view, & controller
		GameModel model = new GameModel(players, 250);
		DominoesView view = new DominoesView();
		StartPanelController controller = new StartPanelController(model, view);
		//run GUI
		view.registerListeners(controller);
		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		view.setMinimumSize(new Dimension(600,500));
		view.setVisible(true);
		//set starting points to 0		
		model.addPoints(0, 0);
		model.addPoints(1, 0);
		model.addPoints(2, 0);
		model.addPoints(3, 0);
		//add names to score panel
		model.setPlayerName(0, one.getName());
		model.setPlayerName(1, two.getName());
		model.setPlayerName(2, three.getName());
		model.setPlayerName(3, four.getName());
		
		//while(!gameOver){
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
			model.setBoard(board);
			//deal dominoes to players
			one.addToHand(yard.drawHand(7));
			two.addToHand(yard.drawHand(7));
			three.addToHand(yard.drawHand(7));
			four.addToHand(yard.drawHand(7));
			//play game here
			while(!(one.isHandEmpty() || two.isHandEmpty() || three.isHandEmpty() || four.isHandEmpty())){	
				System.out.printf("----------Round %d Turn %d----------\n", round, turn);
				System.out.println("Current board state");
				System.out.println(board.toString());
				System.out.println("Player 1's turn");
				System.out.println("Your hand: " + one.getHand());
				//player one takes turn here
				System.out.println("Choose Domino (1-7): ");
				System.out.println("1. " + one.getDomino(0) + " 2. " + one.getDomino(1) + " 3. " + one.getDomino(2) 
				+ " 4. " + one.getDomino(3) + " 5. " + one.getDomino(4) + " 6." + one.getDomino(5) + " 7. " +one.getDomino(6));
				play = scan.nextInt();
				System.out.println("Selected " + one.getDomino(play-1));
				System.out.println("1. Spoke South 2. Spoke North 3. Spoke East 4. Spoke West");
				spoke = scan.nextInt();
				System.out.println("Selected spoke # " + spoke);
				board.addToSpoke(spoke, one.getDomino(play-1));
				System.out.println(board.toString());
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
				//System.out.println(two.getHand());
				//two.takeTurn(board, yard);
				/*
				 * Check if Player 2 has scored and if their score
				 * is enough to win the game.
				 */
				//if(board.getBoardValue() > 0 && board.getBoardValue() % 5 == 0){
				//	two.addPoints(board.getBoardValue());
				//	System.out.printf("Player 2's score is round: %d\ttotal: %d\n", two.getRoundScore(), two.getTotalScore());
				//	if(two.getTotalScore() >= 250){
				//		gameOver = true;
				//		break;
				//	}
				//}
				/*
				 * Check to see if Players 1-4 are unable to make
				 * valid plays. 
				 */
				//if(one.noPlay() && two.noPlay() && three.noPlay() && four.noPlay()){
				//	System.out.println("No player is able to make a move, end of round.");
				//	break;
				//}
				//turn++;
			//}
			//if(gameOver){
			//	break;
			//}
			//}
			}
		}
	}

