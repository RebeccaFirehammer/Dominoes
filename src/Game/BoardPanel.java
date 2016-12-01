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

	public BoardPanel(GameModel model){
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		updateBoard(model);
	}
	
	public void updateBoard(GameModel model){
		this.board = model.getBoard();
		repaint();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		this.setBackground(Color.black);
		Color bc = Color.RED;
		Color pc = Color.BLUE;
		final int boneSize = getHeight() / 15;
		final int offset = boneSize / 15;
		DominoImages boneImages = new DominoImages(boneSize, bc, pc);
		BufferedImage image = boneImages.getBufferedImage(board.getSpinner());
		final Dimension loc = new Dimension((getWidth() / 2) - (image.getWidth()/2), 
											(getHeight() / 2) - (image.getHeight()/2));
		int nextX = loc.width;
		int nextY = loc.height;
		
		//draw spinner
		g.drawImage(image, nextX, nextY, null);
		
		nextX += image.getWidth() + offset;
		nextY = (getHeight() / 2) - (image.getHeight()/4);
		
//		boneImages.flip(board.getSpokes().get(0).getSpoke().get(0));
//		image = boneImages.getBufferedImage(board.getSpokes().get(0).getSpoke().get(0));
//		g.drawImage(image, nextX, nextY, null);
//		System.out.println(image.getWidth());
		
		int xReq = 0;
		int yReq = 0;
		for(int index = 0; index < board.getSpokes().size(); index++){
			Spoke s = board.getSpokes().get(index);
			for(int i = 0; i < s.getSpoke().size(); i++){
				Domino bone = s.getSpoke().get(i);
				if(bone.isAOpen()){
					boneImages.invert(bone);
				}
				switch(index){
				case 0:
					if(!bone.isDouble()){
						nextY = (getHeight() / 2) - (boneImages.getHeight(bone)/4);
						boneImages.flip(bone);
					}else{
						nextY = (getHeight() / 2) - (boneImages.getHeight(bone)/2);
					}
					xReq = nextX + boneImages.getWidth(bone) + offset;
					if(xReq < getWidth()){
						image = boneImages.getBufferedImage(bone);
						g.drawImage(image, nextX, nextY, null);
						nextX += boneImages.getWidth(bone) + offset;
					}else{
						nextX += image.getWidth()/2;
						
					}
					if(i == (s.getSpoke().size() - 1)){ //set location for west
						nextX = loc.width - boneImages.getWidth(bone) - offset;
					}
					break;
				}
				
			}
		}
		
/*		final int offset = (this.getWidth() / 200)/2; //space between Dominoes
		final int dSize = (this.getWidth() / 16)/2; //Domino Size
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
		for(int index = 0; index < board.getSpokes().size(); index++){
			Spoke s = board.getSpokes().get(index);
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
		}	*/
	}
}
