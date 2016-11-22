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
		//draw spinner
		final Dimension center = new Dimension((this.getWidth()/2), (this.getHeight()/2));
		final int dOffset = this.getWidth() / 200;
		int SpinnerPips = this.board.getSpinner().getEndA();
		final int dSize = this.getWidth() / 15; //Domino Size
		DominoImage domino = new DominoImage();
		domino.setValue(SpinnerPips, SpinnerPips); 
		domino.invert();
		final Dimension sLoc = new Dimension(center.width - (dSize/2), center.height - (dSize));
		Dimension draw = new Dimension(sLoc);
		domino.paintDomino(g, dSize, draw.width, draw.height); //testing draw domino
		
		//draw spokes
		int nextX = draw.width + domino.getWidth() + dOffset;
		int nextY = draw.height + draw.height / 10;
		draw.setSize(nextX, nextY);
		domino.invert();
		int index = 0;
		for(Spoke s: board.getSpokes()){
			for(int i = 0; i < s.getSpoke().size(); i++){
				Domino d = s.getSpoke().get(i);
				if(d.isAOpen()){
					domino.setValue(d.getEndB(), d.getEndA());
				}else{
					domino.setValue(d.getEndA(), d.getEndB());
				}
				switch(index){
				case 0:	//east spoke
					domino.paintDomino(g, dSize, nextX, nextY);
					nextX = draw.width + (domino.getWidth() * 2) + dOffset;
					if(i == (s.getSpoke().size() - 1)){ //set location for west
						nextX = sLoc.width - (domino.getWidth() * 2) - dOffset;
					}
					break;
				case 1: //west spoke
					domino.flip();
					domino.paintDomino(g, dSize, nextX, nextY);
					nextX = draw.width - (domino.getWidth() * 2) - dOffset;
					if(i == (s.getSpoke().size() - 1)){ //set location for north
						domino.invert();
						nextX = sLoc.width;
						nextY = sLoc.height - (domino.getWidth() * 2) - dOffset;
					}
					break;
				case 2: //north spoke
					domino.flip();
					domino.paintDomino(g, dSize, nextX, nextY);
					nextY = draw.height - (domino.getWidth() * 2) - dOffset;
					if(i == (s.getSpoke().size() - 1)){ //set location for north
						nextX = sLoc.width;
						nextY = sLoc.height + (domino.getWidth() * 2) + dOffset;
					}
					break;
				case 3: //south spoke
					domino.paintDomino(g, dSize, nextX, nextY);
					nextY = draw.height + (domino.getWidth() * 2) + dOffset;
					break;
				}
				draw.setSize(nextX, nextY);
			}
			index++;
		}
		
	}
}
