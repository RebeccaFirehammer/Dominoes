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
		this.boneSize = (height + width) / 45;
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
		offset = (this.boneSize / 12 < 4) ? 2 : this.boneSize / 12;
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
		boolean back = false;
		boolean down = false;
		
		
		for(int i = 0; i < spoke.getSpoke().size(); i++){
			Domino bone = spoke.getSpoke().get(i);
			Domino last = (i > 0) ? spoke.getSpoke().get(i - 1) : spoke.getSpoke().get(0);
			if(i > 0){
				last = spoke.getSpoke().get(i - 1);
			}
			//determines which end pips are drawn
			
			if(nextY > 0 && back == false){  //drawing up
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
			}else if(back == false){ //set up for drawing across
				boneImages.invert(bone);
				boneImages.flip(bone);
				if(nextY + sideS > 0 && !last.isDouble()){
					nextX -= sideS;
					nextY += sideS;
				}else if(nextY + sideS > 0 && last.isDouble()){
					nextX -= sideS + sideS/2;
					nextY += sideS;
				}else if(last.isDouble()){
					nextY = 0;
					nextX -= boneImages.getWidth(bone) - sideS/2;
				}else{
					nextY = 0;
					nextX -= boneImages.getWidth(bone) + offset;
				}
				cImg = boneImages.getBufferedImage(bone);
				g.drawImage(cImg, nextX, nextY, null);
				nextY = 0;
				back = true;
			}else if(back == true && down == false){ //draw across going back
				if((nextX - sideL) >= 0){
					if(!bone.isDouble()){
						boneImages.flip(bone);
					}
					nextX -= boneImages.getWidth(bone) + offset;
					cImg = boneImages.getBufferedImage(bone);
					g.drawImage(cImg, nextX, nextY, null);
				}else if((nextX - sideS) > 0){
					nextX -= boneImages.getWidth(bone) + offset;
					cImg = boneImages.getBufferedImage(bone);
					g.drawImage(cImg, nextX, nextY, null);
					down = true;
				}else{
					nextX = 0;
					nextY += boneImages.getWidth(bone);
					cImg = boneImages.getBufferedImage(bone);
					g.drawImage(cImg, nextX, nextY, null);
					down = true;
				}
			}else if(down == true){
				nextX = 0;
				nextY = boneImages.getHeight(last) + offset;
				if(bone.isDouble()){
					boneImages.flip(bone);
				}
				cImg = boneImages.getBufferedImage(bone);
				g.drawImage(cImg, nextX, nextY, null);
				down = true;
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
	
	private void drawEast(Spoke spoke){
		//coordinates for drawing next Domino
		int nextY = y - sideL / 4;
		int nextX = x + sideS * 1/2 + offset + offset/3;
		
		//used to determine bounds
		int xReq = 0;
		boolean up = false;
		boolean back = false;
		boolean down = false;
		
		//draw spoke
		for(int i = 0; i < spoke.getSpoke().size(); i++){
			Domino bone = spoke.getSpoke().get(i);
			Domino last =  (i > 0) ?  last = spoke.getSpoke().get(i - 1) : spoke.getSpoke().get(i);
			if(bone.isAOpen()){
				boneImages.invert(bone);
			}
			//draw horizontally to the right
			if(up == false){
				if(!bone.isDouble()){
					nextY = y - sideS / 2;
					boneImages.flip(bone);
				}else{
					nextY = y - sideL / 2;
				}	
				xReq = nextX + boneImages.getWidth(bone); 
				if(xReq <= width){ //check if domino can fit 
					cImg = boneImages.getBufferedImage(bone);
					g.drawImage(cImg, nextX, nextY, null);
					nextX += boneImages.getWidth(bone) + offset;
					up = false;
				}else{ //position for drawing upwards
					up = true;
					boneImages.invert(bone);
					if(!bone.isDouble()){
						boneImages.flip(bone);
					}
					if(nextX + sideS <= width){
						nextY -= boneImages.getHeight(bone)/2 + offset;
						if(last.isDouble()){
							nextY -= boneImages.getHeight(bone)/4;
						}
						cImg = boneImages.getBufferedImage(bone);
						g.drawImage(cImg, nextX, nextY, null);
					}else if(width - nextX > 0){
						nextX = width - boneImages.getWidth(bone);
						if(!last.isDouble()){
							nextY -= sideL + offset;
						}else{
							nextY -= boneImages.getHeight(last) + sideS/2 + offset;
						}
						cImg = boneImages.getBufferedImage(bone);
						g.drawImage(cImg, nextX, nextY, null);
					}else{
						if(!last.isDouble()){ //check if the last Domino placed is a double
							nextX -= sideS + offset;
							nextY = y - sideL - sideL/4 - offset;
							cImg = boneImages.getBufferedImage(bone);
							g.drawImage(cImg, nextX, nextY, null);
						}else{
							nextX -= sideS + offset + offset/3;
							nextY = y - sideL - sideL/2 - offset + offset/3;
							cImg = boneImages.getBufferedImage(bone);
							g.drawImage(cImg, nextX, nextY, null);
						}
					}
					nextY -= cImg.getHeight() + offset;
				}
			}else if(up == true){ //drawing upwards
				if(back == false){
					boneImages.invert(bone);
					if(nextY >= 0){ //check bounds
						if(!bone.isDouble()){ //check for double
							cImg = boneImages.getBufferedImage(bone);
							g.drawImage(cImg, nextX, nextY, null);	
						}else{ //double going up
							boneImages.flip(bone);
							nextY += boneImages.getHeight(bone) + offset/2;
							nextX -= boneImages.getWidth(bone)/4;
							xReq = nextX + boneImages.getWidth(bone);
							if(width > xReq){
								cImg = boneImages.getBufferedImage(bone);
								//g.drawImage(cImg, nextX, nextY, null);
								g.drawImage(cImg, nextX, nextY, null);
							}else{
								cImg = boneImages.getBufferedImage(bone);
								g.drawImage(cImg, width - boneImages.getWidth(bone), nextY, null);
							}
							nextX += boneImages.getWidth(bone)/4;
							nextY -= boneImages.getHeight(bone) + offset/2;
						}
						back = false;
						nextY -= boneImages.getHeight(bone) + offset;
					}else{  //position for drawing back
						back = true;
						boneImages.flip(bone);
						cImg = boneImages.getBufferedImage(bone);
						if(nextY + sideS > 0){
							nextY += sideS;
							nextX = width - boneImages.getWidth(bone);
							if(last.isDouble()){
								nextX += boneImages.getWidth(last)/4;
							}
						}else{
							nextX -= (!last.isDouble()) ? boneImages.getWidth(last) * 2 + offset 
									: boneImages.getWidth(last);
							//nextY += (!last.isDouble()) ? cImg.getWidth() + offset : cImg.getWidth();
							nextY = 0;
						}
						g.drawImage(cImg, nextX, nextY, null);	
					}
				}else if(back == true){ //drawing back
					if(!bone.isDouble()){
						boneImages.flip(bone);	
					}else{
						
					}
					nextX -= boneImages.getWidth(bone) + offset;
					cImg = boneImages.getBufferedImage(bone);
					g.drawImage(cImg, nextX, 0, null);	
				}

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
			Domino last =  (i > 0) ?  last = spoke.getSpoke().get(i - 1) : spoke.getSpoke().get(i);
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
						nextX -= (boneImages.getHeight(bone) * 2) + offset + offset/3;
					}else{ //check for double
						nextY -= sideS/2;
						nextX += sideS - offset/3;
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
	

}
