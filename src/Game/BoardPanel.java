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
	 * The game Model
	 */
	private GameModel model;
	
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
		this.model = model;
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		actLocs = new ArrayList<ActiveLocation>();
		updateBoard(this.model);
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
		drawActive(g);
	}
	
	/**
	 * The coordinates of the Dominoes that can be played off of.
	 * @return An ArrayList of ActiveLocation objects
	 */
	public ArrayList<ActiveLocation> getActiveLocs(){
		return actLocs;
	}
	
	public void drawActive(Graphics g){
		int alpha = 127;
		Color color = Color.YELLOW;
		color.brighter();
		color = new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
		g.setColor(color);
		int x = 0;
		int y = 0;
		int height = 0;
		int width = 0;
		
		try{
			switch(board.spokeCount()){
			case 0:
				//no spokes, highlight spinner
				if(board.getSpokes().get(0).isValidMove(model.getActive()) ||
					board.getSpokes().get(1).isValidMove(model.getActive())){
					x = actLocs.get(0).getX() - actLocs.get(0).getWidth() / 15;
					y = actLocs.get(0).getY() - actLocs.get(0).getHeight() / 15;
					width = actLocs.get(0).getWidth() + (actLocs.get(0).getWidth()/15 * 3);
					height = actLocs.get(0).getHeight() + (actLocs.get(0).getHeight()/15 * 2);
					g.fillRect(x, y, width, height);
				}
				break;
			case 1: 
				//North spoke empty, South started
				if(board.getSpokes().get(0).size() <= 0 && board.getSpokes().get(1).size() > 0){ 
					if(board.getSpokes().get(0).isValidMove(model.getActive())){
						x = actLocs.get(0).getX() - actLocs.get(0).getWidth() / 15;
						y = actLocs.get(0).getY() - actLocs.get(0).getHeight() / 15;
						width = actLocs.get(0).getWidth() + (actLocs.get(0).getWidth()/15 * 3);
						height = actLocs.get(0).getHeight() + (actLocs.get(0).getHeight()/15 * 2);
						g.fillRect(x, y, width, height);
					}
					if(board.getSpokes().get(1).isValidMove(model.getActive())){
						x = actLocs.get(2).getX() - actLocs.get(2).getWidth() / 15;
						y = actLocs.get(2).getY() - actLocs.get(2).getHeight() / 15;
						width = actLocs.get(2).getWidth() + (actLocs.get(2).getWidth()/15 * 3);
						height = actLocs.get(2).getHeight() + (actLocs.get(2).getHeight()/15 * 2);
						g.fillRect(x, y, width, height);
					}
				//south spoke empty, north spoke started
				}else if(board.getSpokes().get(1).size() <= 0 && board.getSpokes().get(0).size() > 0){
					if(board.getSpokes().get(1).isValidMove(model.getActive())){
						x = actLocs.get(0).getX() - actLocs.get(0).getWidth() / 15;
						y = actLocs.get(0).getY() - actLocs.get(0).getHeight() / 15;
						width = actLocs.get(0).getWidth() + (actLocs.get(0).getWidth()/15 * 3);
						height = actLocs.get(0).getHeight() + (actLocs.get(0).getHeight()/15 * 2);
						g.fillRect(x, y, width, height);
					}
					if(board.getSpokes().get(0).isValidMove(model.getActive())){
						x = actLocs.get(1).getX() - actLocs.get(1).getWidth() / 15;
						y = actLocs.get(1).getY() - actLocs.get(1).getHeight() / 15;
						width = actLocs.get(1).getWidth() + (actLocs.get(1).getWidth()/15 * 3);
						height = actLocs.get(1).getHeight() + (actLocs.get(1).getHeight()/15 * 2);
						g.fillRect(x, y, width, height);
					}
				}
				break;
			case 2:
				if(board.getSpokes().get(0).isValidMove(model.getActive())){
					x = actLocs.get(1).getX() - actLocs.get(1).getWidth() / 15;
					y = actLocs.get(1).getY() - actLocs.get(1).getHeight() / 15;
					width = actLocs.get(1).getWidth() + (actLocs.get(1).getWidth()/15 * 3);
					height = actLocs.get(1).getHeight() + (actLocs.get(1).getHeight()/15 * 2);
					g.fillRect(x, y, width, height);
				}
				if(board.getSpokes().get(1).isValidMove(model.getActive())){
					x = actLocs.get(2).getX() - actLocs.get(2).getWidth() / 15;
					y = actLocs.get(2).getY() - actLocs.get(2).getHeight() / 15;
					width = actLocs.get(2).getWidth() + (actLocs.get(2).getWidth()/15 * 3);
					height = actLocs.get(2).getHeight() + (actLocs.get(2).getHeight()/15 * 2);
					g.fillRect(x, y, width, height);
				}
				if(board.getSpokes().get(2).isValidMove(model.getActive())){
					x = actLocs.get(0).getX() - actLocs.get(0).getWidth() / 15;
					y = actLocs.get(0).getY() - actLocs.get(0).getHeight() / 15;
					width = actLocs.get(0).getWidth() + (actLocs.get(0).getWidth()/15 * 3);
					height = actLocs.get(0).getHeight() + (actLocs.get(0).getHeight()/15 * 2);
					g.fillRect(x, y, width, height);
				}
				break;
			case 3:
				if(board.getSpokes().get(0).isValidMove(model.getActive())){
					x = actLocs.get(1).getX() - actLocs.get(1).getWidth() / 15;
					y = actLocs.get(1).getY() - actLocs.get(1).getHeight() / 15;
					width = actLocs.get(1).getWidth() + (actLocs.get(1).getWidth()/15 * 3);
					height = actLocs.get(1).getHeight() + (actLocs.get(1).getHeight()/15 * 2);
					g.fillRect(x, y, width, height);
				}
				if(board.getSpokes().get(1).isValidMove(model.getActive())){
					x = actLocs.get(2).getX() - actLocs.get(2).getWidth() / 15;
					y = actLocs.get(2).getY() - actLocs.get(2).getHeight() / 15;
					width = actLocs.get(2).getWidth() + (actLocs.get(2).getWidth()/15 * 3);
					height = actLocs.get(2).getHeight() + (actLocs.get(2).getHeight()/15 * 2);
					g.fillRect(x, y, width, height);
				}
				if(board.getSpokes().get(2).isValidMove(model.getActive())){
					x = actLocs.get(3).getX() - actLocs.get(3).getWidth() / 15;
					y = actLocs.get(3).getY() - actLocs.get(3).getHeight() / 15;
					width = actLocs.get(3).getWidth() + (actLocs.get(3).getWidth()/15 * 3);
					height = actLocs.get(3).getHeight() + (actLocs.get(3).getHeight()/15 * 2);
					g.fillRect(x, y, width, height);
				}
				if(board.getSpokes().get(3).isValidMove(model.getActive())){
					x = actLocs.get(0).getX() - actLocs.get(0).getWidth() / 15;
					y = actLocs.get(0).getY() - actLocs.get(0).getHeight() / 15;
					width = actLocs.get(0).getWidth() + (actLocs.get(0).getWidth()/15 * 3);
					height = actLocs.get(0).getHeight() + (actLocs.get(0).getHeight()/15 * 2);
					g.fillRect(x, y, width, height);
				}
				break;
			case 4:
				if(board.getSpokes().get(0).isValidMove(model.getActive())){
					x = actLocs.get(1).getX() - actLocs.get(1).getWidth() / 15;
					y = actLocs.get(1).getY() - actLocs.get(1).getHeight() / 15;
					width = actLocs.get(1).getWidth() + (actLocs.get(1).getWidth()/15 * 3);
					height = actLocs.get(1).getHeight() + (actLocs.get(1).getHeight()/15 * 2);
					g.fillRect(x, y, width, height);
				}
				if(board.getSpokes().get(1).isValidMove(model.getActive())){
					x = actLocs.get(2).getX() - actLocs.get(2).getWidth() / 15;
					y = actLocs.get(2).getY() - actLocs.get(2).getHeight() / 15;
					width = actLocs.get(2).getWidth() + (actLocs.get(2).getWidth()/15 * 3);
					height = actLocs.get(2).getHeight() + (actLocs.get(2).getHeight()/15 * 2);
					g.fillRect(x, y, width, height);
				}
				if(board.getSpokes().get(2).isValidMove(model.getActive())){
					x = actLocs.get(3).getX() - actLocs.get(3).getWidth() / 15;
					y = actLocs.get(3).getY() - actLocs.get(3).getHeight() / 15;
					width = actLocs.get(3).getWidth() + (actLocs.get(3).getWidth()/15 * 3);
					height = actLocs.get(3).getHeight() + (actLocs.get(3).getHeight()/15 * 2);
					g.fillRect(x, y, width, height);
				}
				if(board.getSpokes().get(3).isValidMove(model.getActive())){
					x = actLocs.get(4).getX() - actLocs.get(4).getWidth() / 15;
					y = actLocs.get(4).getY() - actLocs.get(4).getHeight() / 15;
					width = actLocs.get(4).getWidth() + (actLocs.get(4).getWidth()/15 * 3);
					height = actLocs.get(4).getHeight() + (actLocs.get(4).getHeight()/15 * 2);
					g.fillRect(x, y, width, height);
				}
			}
				
		} catch(NullPointerException e){
			//do nothing
		}
	}
}
