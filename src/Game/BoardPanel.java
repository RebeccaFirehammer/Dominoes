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
		final int offset = this.getWidth() / 200; //space between Dominoes
		final int dSize = this.getWidth() / 16; //Domino Size
		final Dimension sLoc = new Dimension((this.getWidth()/2) - (dSize/2), (this.getHeight()/2) - (dSize)); //spinner location
		int nextX = sLoc.width;  //next X coordinate for drawing Domino
		int nextY = sLoc.height; //next Y coordinate for drawing Domino
		
		DominoImage domino = new DominoImage();
		domino.setValue(this.board.getSpinner()); 
		domino.invert();
		
		//draw spinner
		domino.paintDomino(g, dSize, nextX, nextY); 
		
		//offsets for drawing spokes
		nextX += domino.getWidth() + offset;
		nextY += (nextY / 10);
		domino.invert();
		
		//draws spokes
		int index = 0;
		for(Spoke s: board.getSpokes()){
			for(int i = 0; i < s.getSpoke().size(); i++){
				Domino d = s.getSpoke().get(i);
				if(d.isAOpen()){
					domino.setValue(d);
				}else{
					domino.setValue(d);
				}
				switch(index){
				case 0:	//east spoke
					if(!d.isDouble()){ 
						domino.paintDomino(g, dSize, nextX, nextY);
						nextX += (domino.getWidth() * 2) + offset;
					}else{//draw double
						domino.invert();
						domino.paintDomino(g, dSize, nextX, (nextY - (domino.getWidth() / 2)));
						domino.invert();
						nextX += domino.getWidth() + offset;
					}
					if(i == (s.getSpoke().size() - 1)){ //set location for west
						nextX = sLoc.width - (domino.getWidth() * 2) - offset;
					}
					break;
				case 1: //west spoke
					domino.flip();
					if(!d.isDouble()){
						domino.paintDomino(g, dSize, nextX, nextY);
						nextX -= (domino.getWidth() * 2) + offset;
					}else{ //draw double
						domino.invert();
						nextX += domino.getWidth() - (offset / 2);
						domino.paintDomino(g, dSize, nextX, (nextY - (domino.getWidth() / 2)));
						domino.invert();
						nextX -= (domino.getWidth() * 2) + offset;
					}
					
					if(i == (s.getSpoke().size() - 1)){ //set location for north
						domino.invert();
						nextX = sLoc.width;
						nextY = sLoc.height - (domino.getWidth() * 2) - offset;
					}
					break;
				case 2: //north spoke
					domino.flip();
					if(!d.isDouble()){
						domino.paintDomino(g, dSize, nextX, nextY);
						nextY -= (domino.getWidth() * 2) - offset;
					}else{  //draw double
						domino.invert();
						nextY += domino.getWidth() - (offset * 2);
						domino.paintDomino(g, dSize, (nextX - (domino.getWidth() / 2)), nextY);
						domino.invert();
						nextY -= (domino.getWidth() * 2) + (offset);
					}
					if(i == (s.getSpoke().size() - 1)){ //set location for north
						nextX = sLoc.width;
						nextY = sLoc.height + (domino.getWidth() * 2) + offset;
					}
					break;
				case 3: //south spoke
					if(!d.isDouble()){
						domino.paintDomino(g, dSize, nextX, nextY);
						nextY += (domino.getWidth() * 2) + offset;
					}else{ //draw double
						domino.invert();
						domino.paintDomino(g, dSize, (nextX - (domino.getWidth() / 2)), nextY);
						domino.invert();
						nextY += domino.getWidth() + offset;
					}
					break;
				}
			}
			index++;
		}	
	}
}
