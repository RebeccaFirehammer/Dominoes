package Game;

/**
 * Draws a Board containing Dominoes.
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

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
	
	/**
	 * The width of the board
	 */
	private int width;
	
	/**
	 * The height of the board
	 */
	private int height;
	
	/**
	 * An ArrayList of ActiveLocation objects representing the locations
	 * of the Dominoes that can be played off of
	 */
	private ArrayList<ActiveLocation> actLocs;
	
	/**
	 * Constructs a new DrawBoard object.
	 * @param g A graphics object
	 * @param board The game board
	 * @param x The x location of the spinner
	 * @param y The y location of the spinner
	 * @param width The width of the board
	 * @param height The height of the board
	 * @param boneColor The background color of the Domino
	 * @param pipColor The pip color of the Domino
	 * @param barColor The bar color of the Domino
	 */
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
		actLocs = new ArrayList<ActiveLocation>();
		boneImages = new DominoImages(this.boneSize, this.boneColor, this.pipColor, this.barColor);
		cImg = boneImages.getBufferedImage(board.getSpinner());
		sideL = cImg.getHeight();
		sideS = cImg.getWidth();
		offset = (this.boneSize / 12 < 4) ? 2 : this.boneSize / 12;
		draw();
	}
	
	/**
	 * Constructs a new DrawBoard object.
	 * @param g A graphics object
	 * @param board The game board
	 * @param x The x location of the spinner
	 * @param y The y location of the spinner
	 * @param width The width of the board
	 * @param height The height of the board
	 * @param boneColor The background color of the Domino
	 * @param pipColor The pip color of the Domino
	 */
	public DrawBoard(Graphics g, Board board, int x, int y, int width, int height, Color boneColor, Color pipColor){
		this(g, board, x, y, width, height, boneColor, pipColor, pipColor);
	}
	
	/**
	 * Constructs a new DrawBoard object.
	 * @param g A graphics object
	 * @param board The game board
	 * @param x The x location of the spinner
	 * @param y The y location of the spinner
	 * @param width The width of the board
	 * @param height The height of the board
	 * @param boneColor The background color of the Domino
	 */
	public DrawBoard(Graphics g, Board board, int x, int y, int width, int height, Color boneColor){
		this(g, board, x, y, width, height, boneColor, Color.BLACK);
	}
	
	/**
	 * Constructs a new DrawBoard object.
	 * @param g A graphics object
	 * @param board The game board
	 * @param width The width of the board
	 * @param height The height of the board
	 * @param boneColor The background color of the Domino
	 * @param pipColor The pip color of the Domino
	 */
	public DrawBoard(Graphics g, Board board, int width, int height, Color boneColor, Color pipColor){
		this(g, board, width/2, height/2, width, height,  boneColor, pipColor);
	}
	
	/**
	 * Constructs a new DrawBoard object.
	 * @param g A graphics object
	 * @param board The game board
	 * @param width The width of the board
	 * @param height The height of the board
	 * @param boneColor The background color of the Domino
	 */
	public DrawBoard(Graphics g, Board board, int width, int height, Color boneColor){
		this(g, board, width/2, height/2, width, height,  boneColor, Color.BLACK);
	}
	
	/**
	 * Construcs a new DrawDomino object.
	 * @param g A graphics object
	 * @param board The game board
	 * @param x The x location of the spinner
	 * @param y The y location of the spinner
	 * @param width The width of the board
	 * @param height The height of the board
	 */
	public DrawBoard(Graphics g, Board board, int x, int y, int width, int height){
		this(g, board, x, y, width, height, Color.WHITE);
	}

	/**
	 * Constructs a new DrawDomino object
	 * @param g A graphics object
	 * @param board The game board
	 * @param x The x location of the spinner
	 * @param width The width of the board
	 * @param height The height of the board
	 */
	public DrawBoard(Graphics g, Board board, int x, int width, int height){
		this(g, board, x, height/2, width, height);
	}
	
	/**
	 * Constructs a new DrawDomino object
	 * @param g A graphics object
	 * @param board The game board
	 * @param width The width of the board
	 * @param height The height of the board
	 */
	public DrawBoard(Graphics g, Board board, int width, int height){
		this(g, board, width/2, width, height);
	}
	
	/**
	 * Returns a list specifying the location of the playable Dominoes.
	 * @return An ArrayList of ActiveLocation objects
	 */
	public ArrayList<ActiveLocation> getActiveLocations(){
		return actLocs;
	}
	
	/**
	 * Draws the Dominoes on the board.
	 */
	private void draw(){
		//draw spinner
		g.drawImage(cImg, x - (sideS/2), y - (sideL/2), null);
		actLocs.add(new ActiveLocation(-1, x - (sideS/2), y - (sideL/2), 
									cImg.getWidth(), cImg.getHeight()));
		//draw spokes
		drawNorth(board.getSpokes().get(0));
		drawSouth(board.getSpokes().get(1));
		drawEast(board.getSpokes().get(2));
		drawWest(board.getSpokes().get(3));
	}
	
	/***
	 * Draws the north spoke on the board.
	 * @param spoke A spoke object
	 */
	private void drawNorth(Spoke spoke){
		//coordinates for drawing next Domino
		final int NORTH = 0;
		ActiveLocation al = new ActiveLocation(NORTH, 0, 0 ,0, 0);
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
					al = new ActiveLocation(NORTH, nextX, nextY, cImg.getWidth(), cImg.getHeight());
					nextY -= (sideS * 2) + offset;
				}else{ //check for double
					boneImages.flip(bone);
					nextX -= sideS/2;
					nextY += sideS - offset/4;
					cImg = boneImages.getBufferedImage(bone);
					g.drawImage(cImg, nextX, nextY, null);
					al = new ActiveLocation(NORTH, nextX, nextY, cImg.getWidth(), cImg.getHeight());
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
				al = new ActiveLocation(NORTH, nextX, nextY, cImg.getWidth(), cImg.getHeight());
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
					al = new ActiveLocation(NORTH, nextX, nextY, cImg.getWidth(), cImg.getHeight());
				}else if((nextX - sideS) > 0){ //set up to draw down
					boneImages.invert(bone);
					nextX -= boneImages.getWidth(bone) + offset;
					cImg = boneImages.getBufferedImage(bone);
					g.drawImage(cImg, nextX, nextY, null);
					al = new ActiveLocation(NORTH, nextX, nextY, cImg.getWidth(), cImg.getHeight());
					down = true;
				}else{ //set up to draw down
					boneImages.invert(bone);
					nextX = 0;
					nextY += boneImages.getWidth(bone) + offset;
					cImg = boneImages.getBufferedImage(bone);
					g.drawImage(cImg, nextX, nextY, null);
					al = new ActiveLocation(NORTH, nextX, nextY, cImg.getWidth(), cImg.getHeight());
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
				al = new ActiveLocation(NORTH, nextX, nextY, cImg.getWidth(), cImg.getHeight());
				down = true;
			}
		}
		actLocs.add(al);
	}
	
	/**
	 * Draws the south spoke on the board.
	 * @param spoke A spoke object
	 */
	private void drawSouth(Spoke spoke){
		//coordinates for drawing next Domino
		final int SOUTH = 1;
		ActiveLocation al = new ActiveLocation(SOUTH, 0, 0 ,0, 0);
		int nextY = y + sideS + offset;
		int nextX = x - sideS / 2;
		int yReq = 0;
		int xReq = 0;
		boolean across = false;
		boolean up = false;
	
		for(int i = 0; i < spoke.getSpoke().size(); i++){
			Domino bone = spoke.getSpoke().get(i);
			Domino last = (i > 0) ? spoke.getSpoke().get(i - 1) : spoke.getSpoke().get(0);
			
			//determine which end pips are drawn
			if(bone.isAOpen()){ boneImages.invert(bone); } 
			yReq = nextY + sideL;
			//check for doubles
			if(yReq < height && across == false){
				if(bone.isDouble()){ //check for double
					boneImages.flip(bone);
					nextX -= boneImages.getWidth(bone)/4;
				}
				cImg = boneImages.getBufferedImage(bone);
				g.drawImage(cImg, nextX, nextY, null);
				al = new ActiveLocation(SOUTH, nextX, nextY, cImg.getWidth(), cImg.getHeight());
				nextY += boneImages.getHeight(bone) + offset;
				nextX = x - sideS / 2;
			}else if(yReq - sideS < height && !last.isDouble() && across == false){
				boneImages.flip(bone);
				cImg = boneImages.getBufferedImage(bone);
				g.drawImage(cImg, nextX, nextY, null);
				al = new ActiveLocation(SOUTH, nextX, nextY, cImg.getWidth(), cImg.getHeight());
				nextX += cImg.getWidth() + offset;
				nextY = height - sideS;
				across = true;
			}else if(yReq - sideS < height && last.isDouble() && across == false){
				boneImages.flip(bone);
				cImg = boneImages.getBufferedImage(bone);
				nextX = x;
				g.drawImage(cImg, nextX, nextY, null);
				al = new ActiveLocation(SOUTH, nextX, nextY, cImg.getWidth(), cImg.getHeight());
				nextX += cImg.getWidth() + offset;
				nextY = height - sideS;
				across = true;
			}else if(!last.isDouble() && across == false){
				boneImages.flip(bone);
				cImg = boneImages.getBufferedImage(bone);
				nextX += boneImages.getWidth(last) + offset;
				nextY = height - cImg.getHeight();
				g.drawImage(cImg, nextX, nextY, null);
				al = new ActiveLocation(SOUTH, nextX, nextY, cImg.getWidth(), cImg.getHeight());
				nextX += cImg.getWidth() + offset;
				nextY = height - sideS;
				across = true;
			}else if(across == true){
				boneImages.flip(bone);
				xReq = nextX + sideL;
				if(xReq <= width && up == false){
					if(bone.isDouble() && !last.isDouble()){
						boneImages.flip(bone);
						nextY = height - sideL;
					}
					cImg = boneImages.getBufferedImage(bone);
					g.drawImage(cImg, nextX, nextY, null);
					al = new ActiveLocation(SOUTH, nextX, nextY, cImg.getWidth(), cImg.getHeight());
					nextX += cImg.getWidth() + offset;
					nextY = height - sideS;
				}else if(xReq - sideS <= width && !last.isDouble() && up == false){ //set up to draw up
					boneImages.invert(bone);
					boneImages.flip(bone);
					cImg = boneImages.getBufferedImage(bone);
					nextY = height - cImg.getHeight();
					g.drawImage(cImg, nextX, nextY, null);
					al = new ActiveLocation(SOUTH, nextX, nextY, cImg.getWidth(), cImg.getHeight());
					nextX = width - cImg.getWidth();
					nextY -= cImg.getHeight();
					up = true;
				}else if(xReq - sideS <= width && last.isDouble() && up == false){
					boneImages.invert(bone);
					boneImages.flip(bone);
					cImg = boneImages.getBufferedImage(bone);
					nextY = height - sideS - cImg.getHeight();
					g.drawImage(cImg, nextX, nextY, null);
					al = new ActiveLocation(SOUTH, nextX, nextY, cImg.getWidth(), cImg.getHeight());
					nextX = width - cImg.getWidth();
					nextY -= cImg.getHeight() + offset;
					up = true;
				}else if(!last.isDouble() && up == false){
					boneImages.invert(bone);
					boneImages.flip(bone);
					cImg = boneImages.getBufferedImage(bone);
					nextX = width - cImg.getWidth();
					nextY -= cImg.getHeight() + offset;
					g.drawImage(cImg, nextX, nextY, null);
					al = new ActiveLocation(SOUTH, nextX, nextY, cImg.getWidth(), cImg.getHeight());
					nextY -= cImg.getHeight() + offset;
					up = true;
				}else if(up == true && across == true){
					boneImages.invert(bone);
					boneImages.flip(bone);
					if(bone.isDouble() && !last.isDouble()){
						boneImages.flip(bone);
						nextX = width - boneImages.getWidth(bone);
						nextY += sideS + offset/2;
						cImg = boneImages.getBufferedImage(bone);
						g.drawImage(cImg, nextX, nextY, null);
						al = new ActiveLocation(SOUTH, nextX, nextY, cImg.getWidth(), cImg.getHeight());
						nextY -= cImg.getWidth() + offset;;
						nextX = width - sideS;
					}else{
						cImg = boneImages.getBufferedImage(bone);
						g.drawImage(cImg, nextX, nextY, null);
						al = new ActiveLocation(SOUTH, nextX, nextY, cImg.getWidth(), cImg.getHeight());
						nextX = width - sideS;
						nextY -= cImg.getHeight() + offset;
					}
				}
			}
		}
		actLocs.add(al);
	}
	
	/**
	 * Draws the east spoke on the board.
	 * @param spoke A spoke object
	 */
	private void drawEast(Spoke spoke){
		final int EAST = 2;
		ActiveLocation al = new ActiveLocation(EAST, 0, 0 ,0, 0);
		//coordinates for drawing next Domino
		int nextY = y - sideL / 4;
		int nextX = x + sideS * 1/2 + offset + offset/3;
		//used to determine bounds
		int xReq = 0;
		boolean up = false;
		boolean back = false;
		
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
					al = new ActiveLocation(EAST, nextX, nextY, cImg.getWidth(), cImg.getHeight());
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
						al = new ActiveLocation(EAST, nextX, nextY, cImg.getWidth(), cImg.getHeight());
					}else if(width - nextX > 0){
						nextX = width - boneImages.getWidth(bone);
						if(!last.isDouble()){
							nextY -= sideL + offset;
						}else{
							nextY -= boneImages.getHeight(last) + sideS/2 + offset;
						}
						cImg = boneImages.getBufferedImage(bone);
						g.drawImage(cImg, nextX, nextY, null);
						al = new ActiveLocation(EAST, nextX, nextY, cImg.getWidth(), cImg.getHeight());
					}else{
						if(!last.isDouble()){ //check if the last Domino placed is a double
							nextX -= sideS + offset;
							nextY = y - sideL - sideL/4 - offset;
							cImg = boneImages.getBufferedImage(bone);
							g.drawImage(cImg, nextX, nextY, null);
							al = new ActiveLocation(EAST, nextX, nextY, cImg.getWidth(), cImg.getHeight());
						}else{
							nextX -= sideS + offset + offset/3;
							nextY = y - sideL - sideL/2 - offset + offset/3;
							cImg = boneImages.getBufferedImage(bone);
							g.drawImage(cImg, nextX, nextY, null);
							al = new ActiveLocation(EAST, nextX, nextY, cImg.getWidth(), cImg.getHeight());
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
							al = new ActiveLocation(EAST, nextX, nextY, cImg.getWidth(), cImg.getHeight());
						}else{ //double going up
							boneImages.flip(bone);
							nextY += boneImages.getHeight(bone) + offset/2;
							nextX -= boneImages.getWidth(bone)/4;
							xReq = nextX + boneImages.getWidth(bone);
							if(width > xReq){
								cImg = boneImages.getBufferedImage(bone);
								//g.drawImage(cImg, nextX, nextY, null);
								g.drawImage(cImg, nextX, nextY, null);
								al = new ActiveLocation(EAST, nextX, nextY, cImg.getWidth(), cImg.getHeight());
							}else{
								cImg = boneImages.getBufferedImage(bone);
								g.drawImage(cImg, width - boneImages.getWidth(bone), nextY, null);
								al = new ActiveLocation(EAST, width - boneImages.getWidth(bone), nextY, cImg.getWidth(), cImg.getHeight());
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
						al = new ActiveLocation(EAST, nextX, nextY, cImg.getWidth(), cImg.getHeight());
					}
				}else if(back == true){ //drawing back
					if(!bone.isDouble()){
						boneImages.flip(bone);	
					}else{
						
					}
					nextX -= boneImages.getWidth(bone) + offset;
					cImg = boneImages.getBufferedImage(bone);
					g.drawImage(cImg, nextX, 0, null);	
					al = new ActiveLocation(EAST, nextX, 0, cImg.getWidth(), cImg.getHeight());
				}
			}
		}
		actLocs.add(al);
	}
	
	/**
	 * Draws the west spoke on the board.
	 * @param spoke A spoke object
	 */
	private void drawWest(Spoke spoke){
		final int WEST = 3;
		ActiveLocation al = new ActiveLocation(WEST, 0, 0 ,0, 0);
		//coordinates for drawing next Domino
		int nextY = y - sideL / 4 ;
		int nextX = x - sideL - sideS/2 - offset;
		//used to determine bounds
		boolean down = false;
		boolean back = false;
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
				al = new ActiveLocation(WEST, nextX, nextY, cImg.getWidth(), cImg.getHeight());
				nextY = y - sideL / 4 ;
				nextX -= sideL + offset;
			}else if(nextX + sideS >= 0 && !last.isDouble() && down == false){ // position for drawing down
				boneImages.invert(bone);
				nextX += sideS;
				nextY = y - sideL / 4 ;
				cImg = boneImages.getBufferedImage(bone);
				g.drawImage(cImg, nextX, nextY, null);
				al = new ActiveLocation(WEST, nextX, nextY, cImg.getWidth(), cImg.getHeight());
				down = true;
			}else if(nextX + sideS >= 0 && last.isDouble() && down == false){   //position for drawing down
				boneImages.invert(bone);
				nextX += sideS;
				nextY = y - sideL / 4 + sideS/2  ;
				cImg = boneImages.getBufferedImage(bone);
				g.drawImage(cImg, nextX, nextY, null);
				al = new ActiveLocation(WEST, nextX, nextY, cImg.getWidth(), cImg.getHeight());
				down = true;
			}else if(!last.isDouble() && down == false){ //position for drawing down
				boneImages.invert(bone);
				nextY += boneImages.getHeight(last) + offset ;
				nextX = 0;
				cImg = boneImages.getBufferedImage(bone);
				g.drawImage(cImg, nextX, nextY, null);
				al = new ActiveLocation(WEST, nextX, nextY, cImg.getWidth(), cImg.getHeight());
				down = true;
			}else if(last.isDouble() && down == false){
				nextY = y + sideS + offset;
				nextX = 0;
				cImg = boneImages.getBufferedImage(bone);
				g.drawImage(cImg, nextX, nextY, null);
				al = new ActiveLocation(WEST, nextX, nextY, cImg.getWidth(), cImg.getHeight());
				down = true;
			}else if(down == true && back == false){  //position for drawing down
				boneImages.invert(bone);
				nextX = 0;
				nextY += cImg.getHeight() + offset;
				if(nextY + sideL <= height){
					if(bone.isDouble() && !last.isDouble()){
						boneImages.flip(bone);
					}
					cImg = boneImages.getBufferedImage(bone);
					g.drawImage(cImg, nextX, nextY, null);
					al = new ActiveLocation(WEST, nextX, nextY, cImg.getWidth(), cImg.getHeight());
				}else if(nextY + sideS <= height && !last.isDouble()){ //set up for drawing back horizontally
					boneImages.flip(bone);
					nextX = 0;
					nextY += offset;
					cImg = boneImages.getBufferedImage(bone);
					g.drawImage(cImg, nextX, nextY, null);
					al = new ActiveLocation(WEST, nextX, nextY, cImg.getWidth(), cImg.getHeight());
					nextX += cImg.getWidth() + offset;
					back = true;
				}else if(nextY + sideS <= height && last.isDouble()){ //set up for drawing back horizontally
					boneImages.flip(bone);
					nextX = boneImages.getWidth(last)/2;
					cImg = boneImages.getBufferedImage(bone);
					g.drawImage(cImg, nextX, nextY, null);
					al = new ActiveLocation(WEST, nextX, nextY, cImg.getWidth(), cImg.getHeight());
					nextX += cImg.getWidth() + offset;
					back = true;
				}else if(!last.isDouble()){//set up for drawing back horizontally
					boneImages.flip(bone);
					nextX = boneImages.getWidth(last) + offset;
					nextY = height - boneImages.getHeight(bone);
					cImg = boneImages.getBufferedImage(bone);
					g.drawImage(cImg, nextX, nextY, null);
					al = new ActiveLocation(WEST, nextX, nextY, cImg.getWidth(), cImg.getHeight());
					nextX += cImg.getWidth() + offset;
					back = true;
				}
			}else if(down == true && back == true){
				boneImages.invert(bone);
				boneImages.flip(bone);
				if(bone.isDouble() && !last.isDouble()){
					boneImages.flip(bone);
				}
				cImg = boneImages.getBufferedImage(bone);
				nextY = height - cImg.getHeight();
				g.drawImage(cImg, nextX, nextY, null);
				al = new ActiveLocation(WEST, nextX, nextY, cImg.getWidth(), cImg.getHeight());
				nextX += cImg.getWidth() + offset;
			}
		}
		actLocs.add(al);
	}	
}
