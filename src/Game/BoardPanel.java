package Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel{
	
	private Board board;	
	/**
	 * coordinates of the active dominoes;
	 */
	private ArrayList<ActiveLocation> actLocs;

	public BoardPanel(GameModel model){
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		actLocs = new ArrayList<ActiveLocation>();
		updateBoard(model);
	}
	
	public void updateBoard(GameModel model){
		this.board = model.getBoard();
		repaint();
		actLocs = getActiveLocs();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		this.setBackground(Color.black);
		Color bc = Color.GREEN;
		Color pc = Color.BLUE;
		DrawBoard drawBoard = new DrawBoard(g, board, getWidth(), getHeight(), bc, pc);
		actLocs = drawBoard.getActiveLocations();
	}
	
	public ArrayList<ActiveLocation> getActiveLocs(){
		return actLocs;
	}
}
