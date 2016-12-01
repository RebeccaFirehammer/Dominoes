package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class DrawBoard {
	
	/** also possible Domino images from the boneyard */
	private DominoImages boneImages;
	
	/** Image that will be drawn */
	private BufferedImage cImg;
	
	/** Graphics object used to draw images */
	private Graphics g;
	
	/** The Dominoes on the Board */
	private Board board;
	
	/** The background color of the domino */
	private Color boneColor;
	
	/** The color of the dots on the Domino */
	private Color pipColor;
	
	/** The color of the bar inbetween sides of the Domino */
	private Color barColor;
	
	/** The size of one end of the Domino */
	private int boneSize;
	
	/** The space between Dominoes */
	private int offset;
	
	/** The starting x coordinate */
	private int x;
	
	/** The starting y coordinate */
	private int y;
	
	/** The length of the long side of the Domino */
	private int sideL;
	
	/** The length of the short side of the Domino */
	private int sideS;
	
	private int width;
	
	private int height;
	
	public DrawBoard(Graphics g, Board board, int x, int y, int width, int height, Color boneColor, Color pipColor, Color barColor){
		this.g = g;
		this.board = board;
		this.boneSize = (height / 40) + (width / 40);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.boneColor = boneColor;
		this.pipColor = pipColor;
		this.barColor = barColor;
		boneImages = new DominoImages(this.boneSize, boneColor, pipColor, barColor);
		cImg = boneImages.getBufferedImage(board.getSpinner());
		sideL = cImg.getHeight();
		sideS = cImg.getWidth();
		offset = (this.boneSize / 12 < 4) ? 3 : this.boneSize / 12;
		draw();
	}
	
	public DrawBoard(Graphics g, Board board, int x, int y, int width, int height, Color boneColor, Color pipColor){
		this(g, board, x, y, width, height, boneColor, pipColor, pipColor);
	}
	
	public DrawBoard(Graphics g, Board board, int x, int y, int width, int height, Color boneColor){
		this(g, board, x, y, width, height, boneColor, Color.BLACK);
	}
	
	public DrawBoard(Graphics g, Board board, int width, int height, Color boneColor, Color pipColor){
		this(g, board, width/2, height/2, width, height,  boneColor, pipColor);
	}
	
	public DrawBoard(Graphics g, Board board, int width, int height, Color boneColor){
		this(g, board, width/2, height/2, width, height,  boneColor, Color.BLACK);
	}
	
	public DrawBoard(Graphics g, Board board, int x, int y, int width, int height){
		this(g, board, x, y, width, height, Color.WHITE);
	}

	
	public DrawBoard(Graphics g, Board board, int x, int width, int height){
		this(g, board, x, height/2, width, height);
	}
	
	public DrawBoard(Graphics g, Board board, int width, int height){
		this(g, board, width/2, width, height);
	}
	
	private void draw(){
		//draw spinner
		g.drawImage(cImg, x - (sideS/2), y - (sideL/2), null);
		
		//draw spokes
		for(int i = 0; i < board.spokeCount(); i++){
			switch(board.spokeDirection(i)){
			case 'N':  //draw north
				drawNorth(board.getSpokes().get(i));
				break;
			case 'S': //draw south
				drawSouth(board.getSpokes().get(i));
				break;
			case 'E':  //draw east
				drawEast(board.getSpokes().get(i));
				break;
			case 'W': //draw west
				drawWest(board.getSpokes().get(i));
				break;
			}
		}
	}
	
	private void drawNorth(Spoke spoke){
		//coordinates for drawing next Domino
		int nextY = y - (sideS * 3) - offset;
		int nextX = x - sideS / 2;
		
		
		for(int i = 0; i < spoke.getSpoke().size(); i++){
			Domino bone = spoke.getSpoke().get(i);
			Domino last = (i > 0) ? spoke.getSpoke().get(i - 1) : spoke.getSpoke().get(0);
			if(i > 0){
				last = spoke.getSpoke().get(i - 1);
			}
			//determines which end pips are drawn
			if(!bone.isAOpen()){ boneImages.invert(bone); } 
			//check for doubles
			if(!bone.isDouble()){ //check for double
				cImg = boneImages.getBufferedImage(bone);
				g.drawImage(cImg, nextX, nextY, null);
				nextY -= (sideS * 2) + offset;
			}else{ //check for double
				boneImages.flip(bone);
				nextX -= sideS/2;
				nextY += sideS - offset/4;
				cImg = boneImages.getBufferedImage(bone);
				g.drawImage(cImg, nextX, nextY, null);
				nextX += sideS/2;
				nextY -= sideL + offset;
			}
		}
	}
	
	private void drawSouth(Spoke spoke){
		//coordinates for drawing next Domino
		int nextY = y + sideS + offset;
		int nextX = x - sideS / 2;
		
		for(int i = 0; i < spoke.getSpoke().size(); i++){
			Domino bone = spoke.getSpoke().get(i);
			Domino last = (i > 0) ? spoke.getSpoke().get(i - 1) : spoke.getSpoke().get(0);
			//determine which end pips are drawn
			if(bone.isAOpen()){ boneImages.invert(bone); } 
			//check for doubles
			if(!bone.isDouble()){ //check for double
				cImg = boneImages.getBufferedImage(bone);
				g.drawImage(cImg, nextX, nextY, null);
				nextY += (sideS * 2) + offset;
			}else{ //check for double
				boneImages.flip(bone);
				nextX -= sideS/2;
				nextY += offset/2;
				cImg = boneImages.getBufferedImage(bone);
				g.drawImage(cImg, nextX, nextY, null);
				nextX += sideS/2;
				nextY += sideS + offset/2;
			}
		}
	}
	
	private void drawWest(Spoke spoke){
		//coordinates for drawing next Domino
		int nextY = y - sideL / 4 ;
		int nextX = x - sideS * 5/2 - offset;
		
		//used to determine bounds
		int xReq = 0;
		int yReq = 0;
		boolean up = false;
		
		//draw spoke
		for(int i = 0; i < spoke.getSpoke().size(); i++){
			Domino bone = spoke.getSpoke().get(i);
			Domino last = spoke.getSpoke().get(i);
			if(i > 0){
				last = spoke.getSpoke().get(i - 1);
			}
			if(!bone.isAOpen()){
				boneImages.invert(bone);
			}
			//draw horizontally to the left
			if(up == false){
				//space required to place horizontally right
				xReq = nextX - boneImages.getWidth(bone)/2; 
				if(xReq >= 0){
					if(!bone.isDouble()){ //check for double
						boneImages.flip(bone);
						cImg = boneImages.getBufferedImage(bone);
						g.drawImage(cImg, nextX, nextY, null);
						nextX -= (boneImages.getHeight(bone) * 2) + offset;
					}else{ //check for double
						nextY -= sideS/2;
						nextX += sideS;
						cImg = boneImages.getBufferedImage(bone);
						g.drawImage(cImg, nextX, nextY, null);
						nextX -= sideL + offset;
						nextY += sideS/2;
					}
				}else{ //position for drawing downwards
					up = true;
					boneImages.invert(bone);
					if(!last.isDouble()){ //check if the last Domino placed is a double
						nextX = 0 + nextX + sideS;
						//nextY -= 
						cImg = boneImages.getBufferedImage(bone);
						g.drawImage(cImg, nextX, nextY, null);
						
					}else{
//						nextX = 0 + nextX + sideS;
//						nextY = y - sideL / 4 ;
//						cImg = boneImages.getBufferedImage(bone);
//						g.drawImage(cImg, nextX, nextY, null);
					}
					
				}
			}
		}
	}
	
	private void drawEast(Spoke spoke){
		//coordinates for drawing next Domino
		int nextY = y - sideL / 4;
		int nextX = x + sideS * 1/2 + offset;
		
		//used to determine bounds
		int xReq = 0;
		int yReq = 0;
		boolean up = false;
		
		//draw spoke
		for(int i = 0; i < spoke.getSpoke().size(); i++){
			Domino bone = spoke.getSpoke().get(i);
			Domino last = spoke.getSpoke().get(i);
			if(i > 0){
				last = spoke.getSpoke().get(i - 1);
			}
			if(bone.isAOpen()){
				boneImages.invert(bone);
			}
			//draw horizontally to the right
			if(up == false){
				//space required to place horizontally right
				//xReq = width - (width - sideL);	
				//System.out.println(xReq + " "  + (xReq + nextX) + " " +  width);
				xReq = width - (width - sideL - sideS) + nextX;
				
				if(xReq < width){
					if(!bone.isDouble()){
						boneImages.flip(bone);
						cImg = boneImages.getBufferedImage(bone);
						g.drawImage(cImg, nextX, nextY, null);
						nextX += sideL + offset;
					}else{
						nextY -= sideS/2;
						cImg = boneImages.getBufferedImage(bone);
						g.drawImage(cImg, nextX, nextY, null);
						nextX += sideS + offset;
						nextY += sideS / 2;
					}
				}else{ //position for drawing upwards
					up = true;
					boneImages.invert(bone);
					if(!last.isDouble()){ //check if the last Domino placed is a double
						//nextX += sideS/4;
						nextY -= sideS + offset/2;
						cImg = boneImages.getBufferedImage(bone);
						g.drawImage(cImg, nextX, nextY, null);
						
					}else{
						nextX -= sideS + offset;
						nextY -= sideL + (sideS/2 + offset);
						cImg = boneImages.getBufferedImage(bone);
						g.drawImage(cImg, nextX, nextY, null);
					}

					
//					nextY -= (boneImages.getHeight(bone) * 2) + (offset * 2);
//					nextX -= (boneImages.getWidth(bone) / 2) + offset;
//					if(last.isDouble()){
//						nextY -= boneImages.getHeight(bone)/2 + ((offset/3 < 1) ? 1 : offset/3);
//					}
//					if(!bone.isDouble()){
//						boneImages.flip(bone);
//					}else{
//						nextY += boneImages.getHeight(bone) + offset * 10;
//						xReq = nextX + boneImages.getWidth(bone); 	//space required to place horizontally right
//						if(xReq > getWidth()){
//							nextX -= xReq - getWidth();
//						}
//					}
//					boneImages.invert(bone);
//					
//					image = boneImages.getBufferedImage(bone);
//					g.drawImage(image, nextX, nextY, null);
//					nextY -= boneImages.getHeight(bone) + offset;
//					up = true;
					
				}
			}else if(up == true){
				
			}
		}
	}
}
