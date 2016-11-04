package dominoImage;

import java.awt.*;

public class DotImage {

	/**
	 * width of a side of the domino based on the width of the panel
	 */
	private int width;

	/**
	 * height of a side of a domino; same as width
	 */
	private int height;
	
	/**
	 * horizontal location of top-left corner of domino
	 */
	private int x;

	/**
	 * verticle location of top-left corner of domino
	 */
	private int y;
	
	/**
	 * Size of each pip; oval inside square
	 */
	private int size = 0;
	
	/**
	 * Create a DotImage to draw on domino. Sets size requirements for each pip and the 3x3 grid of pips.
	 * @param g Graphics class
	 * @param height height of the domino
	 * @param width width of half a domino
	 */
	public DotImage(Graphics g, int height, int width){
		this.height = height;
		this.width = width;
		this.size = height/9;
	}
	 /**
	  * Determines number of pips on one side of the domino. Calls drawing methods to draw pips.
	  * @param g Graphics class
	  * @param x Horizontal location of top-left corner of domino
	  * @param y Vertical location of top-left corner of domino
	  * @param value number of pips to be drawn
	  */
	public void drawSide(Graphics g, int x, int y, int value){
		this.x = x;
		this.y = y;
		
		if(value == 1){
			drawOne(g);
		}else if(value == 2){
			drawTwo(g);
		}else if(value == 3){
			drawThree(g);
		}else if (value == 4){
			drawFour(g);
		}else if(value == 5){
			drawFive(g);
		}else if(value == 6){
			drawSix(g);
		}else if(value == 7){
			drawSeven(g);
		}else if(value == 8){
			drawEight(g);
		}else if(value == 9){
			drawNine(g);
		}
		
		
	}
	 /**
	  * Draws one pip in the center of the 3x3 grid.
	  * @param g Graphics class
	  */
	public void drawOne(Graphics g){
		int tmpX = x-size/2 + width/2;
		int tmpY = y-size/2 + height/2;
		g.fillOval(tmpX, tmpY, size, size);
	}
	
	/**
	 * Draws a pip in the top-left and bottom right of a 3x3 grid.
	 * @param g Graphics class
	 */
	public void drawTwo(Graphics g){
		int tmpX = x-size/2 + width/4;
		int tmpY = y-size/2 + height/4;
		g.fillOval(tmpX, tmpY, size, size);
		tmpX = x-size/2 + width*3/4;
		tmpY = y-size/2 + height*3/4;
		g.fillOval(tmpX, tmpY, size, size);
	}
	
	/**
	 * Draws a pip in the top-right and bottom-left of a 3x3 grid.
	 * @param g Graphics class
	 */
	public void drawCross(Graphics g){
		int tmpX = x-size/2 + width*3/4;
		int tmpY = y-size/2 + height/4;
		g.fillOval(tmpX, tmpY, size, size);
		tmpX = x-size/2 + width/4;
		tmpY = y-size/2 + height*3/4;
		g.fillOval(tmpX, tmpY, size, size);
	}
	
	/**
	 * Draws a pip at the center-top and center-bottom positions of a 3x3 grid.
	 * @param g Graphics Class
	 */
	public void drawMid(Graphics g){
		int tmpX = x-size/2 + width/2;
		int tmpY = y-size/2 + height/4;
		g.fillOval(tmpX, tmpY, size, size);
		tmpX = x-size/2 + width/2;
		tmpY = y-size/2 + height*3/4;
		g.fillOval(tmpX, tmpY, size, size);
	}
	
	/**
	 * Draws a pip at the center-left and center-right positions of a 3x3 grid.
	 * @param g Graphics Class
	 */
	public void drawEnd(Graphics g){
		int tmpX = x-size/2 + width/4;
		int tmpY = y-size/2 + height/2;
		g.fillOval(tmpX, tmpY, size, size);
		tmpX = x-size/2 + width*3/4;
		tmpY = y-size/2 + height/2;
		g.fillOval(tmpX, tmpY, size, size);
	}
	
	/**
	 * Draws the pip-layout to represent 3.
	 * @param g
	 */
	public void drawThree(Graphics g){
		drawOne(g);
		drawTwo(g);
	}
	
	/**
	 * Draws the pip-layout to represent 4.
	 * @param g
	 */
	public void drawFour(Graphics g){
		drawTwo(g);
		drawCross(g);
	}
	
	/**
	 * Draws the pip-layout to represent 5.
	 * @param g
	 */
	public void drawFive(Graphics g){
		drawFour(g);
		drawOne(g);
	}
	
	/**
	 * Draws the pip-layout to represent 6.
	 * @param g
	 */
	public void drawSix(Graphics g){
		drawFour(g);
		drawMid(g);
	}
	
	/**
	 * Draws the pip-layout to represent 7.
	 * @param g
	 */
	public void drawSeven(Graphics g){
		drawSix(g);
		drawOne(g);
	}
	
	/**
	 * Draws the pip-layout to represent 8.
	 * @param g
	 */
	public void drawEight(Graphics g){
		drawSix(g);
		drawEnd(g);
	}
	
	/**
	 * Draws the pip-layout to represent 9.
	 * @param g
	 */
	public void drawNine (Graphics g){
		drawOne(g);
		drawEight(g);
	}

}
