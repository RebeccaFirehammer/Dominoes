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
		final int offset = (boneSize / 15 < 2) ? 1 : boneSize / 15;
		DominoImages boneImages = new DominoImages(boneSize, bc, pc);
		BufferedImage image = boneImages.getBufferedImage(board.getSpinner());
		Dimension sLoc = new Dimension(image.getWidth(), image.getHeight());
		int nextX = (getWidth() / 2) - (image.getWidth()/2);
		int nextY = (getHeight() / 2) - (image.getHeight()/2);
		
		//draw spinner
		g.drawImage(image, nextX, nextY, null);
		
		//set location for north spoke
		nextY -= image.getHeight() + offset;
		
		int xReq = 0;
		int yReq = 0;
		boolean up = false;
		boolean back = false;
		for(int index = 0; index < board.getSpokes().size(); index++){
			Spoke s = board.getSpokes().get(index);
			for(int i = 0; i < s.getSpoke().size(); i++){
				Domino bone = s.getSpoke().get(i);
				Domino last = s.getSpoke().get(i);
				if(i > 0){
					last = s.getSpoke().get(i - 1);
				}
				if(bone.isAOpen()){
					boneImages.invert(bone);
				}
				
				//north spoke
				if(index == 0){
					boneImages.invert(bone);
					image = boneImages.getBufferedImage(bone);
					g.drawImage(image, nextX, nextY, null);
					nextY -= boneImages.getHeight(bone) + offset;
					//set location for south
					if(i == (s.getSpoke().size() - 1)){ 
						nextX = (getWidth() / 2) - sLoc.width/2;
						nextY = (getHeight() / 2) + sLoc.height/2 + offset + offset/2;
					}
				}
				//south spoke
				if(index == 1){
					image = boneImages.getBufferedImage(bone);
					g.drawImage(image, nextX, nextY, null);
					nextY += boneImages.getHeight(bone) + offset;
					
					//set location for east
					if(i == (s.getSpoke().size() - 1)){ 
						nextX = (getWidth() / 2) + (sLoc.width/2) + offset + offset/2;
						nextY = (getHeight() / 2) - (sLoc.height/2);
					}
				}
				//east spoke
				if(index == 2){ 
					if(up == false){ //east spoke right horizontal
						//check for double
						if(!bone.isDouble()){
							nextY = (getHeight() / 2) - (boneImages.getHeight(bone)/4);
							boneImages.flip(bone);
						}else{
							nextY = (getHeight() / 2) - (boneImages.getHeight(bone)/2);
						}
						xReq = nextX + boneImages.getWidth(bone); 	//set space required to place horizontally
						if(xReq < getWidth()){
							image = boneImages.getBufferedImage(bone);
							g.drawImage(image, nextX, nextY, null);
							nextX += boneImages.getWidth(bone) + offset;
							up = false;
						}else{
							nextY -= (boneImages.getHeight(bone) * 2) + (offset * 2);
							nextX -= (boneImages.getWidth(bone) / 2) + offset;
							if(last.isDouble()){
								nextY -= boneImages.getHeight(bone)/2 + ((offset/3 < 1) ? 1 : offset/3);
							}
							if(!bone.isDouble()){
								boneImages.flip(bone);
							}else{
								nextY += boneImages.getHeight(bone) + offset * 10;
								xReq = nextX + boneImages.getWidth(bone); 	//set space required to place horizontally
								if(xReq > getWidth()){
									nextX -= xReq - getWidth();
								}
							}
							boneImages.invert(bone);
							
							image = boneImages.getBufferedImage(bone);
							g.drawImage(image, nextX, nextY, null);
							up = true;
						}
						
					}else if(up == true){
						nextY -= boneImages.getHeight(bone) + offset;
						boneImages.invert(bone);
						if(bone.isDouble()){ //check for double
							boneImages.invert(bone);
							boneImages.flip(bone);
							nextY += boneImages.getHeight(bone) + offset;
							nextX -= boneImages.getWidth(bone)/4;
						}
						xReq = nextX + boneImages.getWidth(bone); 	//set space required to place horizontally
						if(xReq > getWidth()){
							nextX -= xReq - getWidth();
						}
						image = boneImages.getBufferedImage(bone);
						g.drawImage(image, nextX, nextY, null);	
						nextX += (bone.isDouble()) ? boneImages.getWidth(bone)/4 : 0;
					}
					//set location for west
					if(i == (s.getSpoke().size() - 1)){ 
						
					}
				}
				//west spoke
				if(index == 3){
					
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
