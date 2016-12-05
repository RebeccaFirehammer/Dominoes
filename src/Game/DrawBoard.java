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
		drawNorth(board.getSpokes().get(0));
		drawSouth(board.getSpokes().get(1));
		drawEast(board.getSpokes().get(2));
		drawWest(board.getSpokes().get(3));
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
			if(!bone.isAOpen()){ boneImages.invert(bone); } 
			
			if(nextY > 0 && back == false){  //drawing up
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
				}else if((nextX - sideS) > 0){ //set up to draw down
					boneImages.invert(bone);
					nextX -= boneImages.getWidth(bone) + offset;
					cImg = boneImages.getBufferedImage(bone);
					g.drawImage(cImg, nextX, nextY, null);
					down = true;
				}else{ //set up to draw down
					boneImages.invert(bone);
					nextX = 0;
					nextY += boneImages.getWidth(bone) + offset;
					cImg = boneImages.getBufferedImage(bone);
					g.drawImage(cImg, nextX, nextY, null);
					nextY = boneImages.getHeight(last) + offset;
					down = true;
				}
			}else if(down == true){
				boneImages.invert(bone);
				nextX = 0;
				nextY += boneImages.getHeight(last) + offset;
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
		int yReq = 0;
		boolean across = false;
		
		for(int i = 0; i < spoke.getSpoke().size(); i++){
			Domino bone = spoke.getSpoke().get(i);
			Domino last = (i > 0) ? spoke.getSpoke().get(i - 1) : spoke.getSpoke().get(0);
			
			//determine which end pips are drawn
			if(bone.isAOpen()){ boneImages.invert(bone); } 
			yReq = nextY + boneImages.getHeight(bone);
			
			//check for doubles
			if(yReq < height && across == false){
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
			}else{
				
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
		int nextX = x - sideL - sideS/2 - offset;
		
		//used to determine bounds
		int xReq = 0;
		int yReq = 0;
		boolean down = false;
		
		//draw spoke
		for(int i = 0; i < spoke.getSpoke().size(); i++){
			Domino bone = spoke.getSpoke().get(i);
			Domino last =  (i > 0) ?  last = spoke.getSpoke().get(i - 1) : spoke.getSpoke().get(i);
			
			
			if(!bone.isAOpen()){
				boneImages.invert(bone);
			}
			//draw horizontally to the left
			if(nextX >= 0 && down == false){
				if(!bone.isDouble()){ //check for double
					boneImages.flip(bone);
				}else{
					nextX += sideS;
					nextY = y - (sideL/2);
				}
				cImg = boneImages.getBufferedImage(bone);
				g.drawImage(cImg, nextX, nextY, null);	
				nextY = y - sideL / 4 ;
				nextX -= sideL + offset;
			}else if(nextX + sideS >= 0 && !last.isDouble() && down == false){ // position for drawing down
				boneImages.invert(bone);
				nextX += sideS + offset/2;
				nextY = y - sideS/2 - offset/2;
				cImg = boneImages.getBufferedImage(bone);
				g.drawImage(cImg, nextX, nextY, null);	
				down = true;
			}else if(nextX + sideS >= 0 && last.isDouble() && down == false){   //position for drawing down
				boneImages.invert(bone);
				nextX += boneImages.getWidth(last);
				nextY = y;
				cImg = boneImages.getBufferedImage(bone);
				g.drawImage(cImg, nextX, nextY, null);	
				down = true;
			}else if(!last.isDouble() && down == false){ //position for drawing down
				nextX = 0;
				nextY += boneImages.getHeight(last);
				cImg = boneImages.getBufferedImage(bone);
				g.drawImage(cImg, nextX, nextY, null);	
				down = true;
			}else if(down == true){  //position for drawing down
				boneImages.invert(bone);
				nextX = 0;
				nextY += boneImages.getHeight(last) + offset;
				cImg = boneImages.getBufferedImage(bone);
				g.drawImage(cImg, nextX, nextY, null);	
			}
		}
	}	
	
}
