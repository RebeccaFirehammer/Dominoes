package Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel{
	
	private Board board;

	public BoardPanel(GameModel model){
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		updateBoard(model);
	}
	
	public void updateBoard(GameModel model){
		this.board = model.getBoard();
		repaint();
	}
	
	
	public void paintComponent(Graphics g){
		int SpinnerPips = this.board.getSpinner().getEndA();
		DominoImage domino = new DominoImage();
		domino.invert();
		final Dimension center = new Dimension((this.getWidth()/2), (this.getHeight()/2));
		domino.setValue(SpinnerPips, SpinnerPips);   //testing
		domino.paintDomino(g, 70, center.width - domino.getX(), center.height - domino.getY()); //testing draw domino
		
	}
}
