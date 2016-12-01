package Game;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;

/**
 * This class replicates the steps of a new game, from start to finish,
 *  with a user Player and 3 AI Players
 */
public class GameInit{
	public static void main(String args[]){
		
		Boolean gameOver = false;
		String player;
		ArrayList<Player> players = new ArrayList<Player>();
		
		//eventually need to replace with GUI text box
		Scanner scan = new Scanner(System.in);
		System.out.println("Name - Player 1: ");
		player = scan.nextLine();
		
		//create 1 user player & 3 AI players
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
			//deal dominoes to players
			one.addToHand(yard.drawHand(7));
			two.addToHand(yard.drawHand(7));
			three.addToHand(yard.drawHand(7));
			four.addToHand(yard.drawHand(7));
			System.out.println(players.toString());
			//spinner is determined to be the highest double
			ArrayList<Domino> doubles = new ArrayList<Domino>();
			for(int a = 0; a < one.handSize(); a++){
				if(one.getDomino(a).isDouble()){doubles.add(one.getDomino(a));}
				if(two.getDomino(a).isDouble()){doubles.add(two.getDomino(a));}
				if(three.getDomino(a).isDouble()){doubles.add(three.getDomino(a));}
				if(four.getDomino(a).isDouble()){doubles.add(four.getDomino(a));}
			}
			System.out.println("Doubles: " + doubles.toString());
			Domino spinner;
			for(int b = 0; b < doubles.size(); b ++){
				//compare all double dominoes and assign largest value double to "spinner"
				///spinner = ...;
			}		
			//Board board = new Board(spinner);
		
			while(!(one.isHandEmpty() || two.isHandEmpty() || three.isHandEmpty() || four.isHandEmpty())){
				//play game here
			}
		}
		
		//run GUI
		//view.registerListeners(controller);
		//view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//view.setSize(1000, 800);
		//view.setVisible(true);
		
	}
}
