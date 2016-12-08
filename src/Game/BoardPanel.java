package Game;

/**
 * Creates board panel that draws all the Dominoes on the board
 */

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel{
	
	/**
	 * Contains all the Dominoes on the board
	 */
	private Board board;	
	
	/**
	 * coordinates of the active dominoes;
	 */
	private ArrayList<ActiveLocation> actLocs;

	/**
	 * Constructs a new BoardPanel
	 * @param model The model of the Dominoes game
	 */
	public BoardPanel(GameModel model){
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		actLocs = new ArrayList<ActiveLocation>();
		updateBoard(model);
	}
	
	/**
	 * Updates the Board.
	 * @param model The Game model
	 */
	public void updateBoard(GameModel model){
		this.board = model.getBoard();
		repaint();
		actLocs = getActiveLocs();
	}
	
	/**
	 * Draws the Dominoes on the BoardPanel.
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		this.setBackground(Color.black);
		Color bc = Color.WHITE;
		Color pc = Color.BLACK;
		DrawBoard drawBoard = new DrawBoard(g, board, getWidth(), getHeight(), bc, pc);
		actLocs = drawBoard.getActiveLocations();
	}
	
	/**
	 * The coordinates of the Dominoes that can be played off of.
	 * @return An ArrayList of ActiveLocation objects
	 */
	public ArrayList<ActiveLocation> getActiveLocs(){
		return actLocs;
	}
}
