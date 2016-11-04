package dominoImage;

import java.awt.*;

public class DominoImage {

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
	 * track if domino is inverted
	 */
	private boolean invert = false;
	
	/**
	 * Value of Left or Top side of a Domino
	 */
	private int leftValue = 0;
	
	/**
	 * Value of Right or Bottom side of a Domino
	 */
	private int rightValue = 0;
	
	/**
	 * Paints each side of a double blank domino. Calls DotImage to get pips.
	 * @param g Graphics Class
	 * @param width Width of one side of the domino
	 */
	public void paintDomino(Graphics g, int width) {
		this.height = width;
		this.width = width;
		DotImage dot = new DotImage(g, height, width);
		if (invert) {
			drawHalf(g, x, y);
			dot.drawSide(g, x, y, leftValue);
			drawHalf(g, x, y+height);
			dot.drawSide(g, x+width, y, rightValue);			
		} else {
			drawHalf(g, x, y);
			dot.drawSide(g, x, y, leftValue);
			drawHalf(g, x + width, y);
			dot.drawSide(g, x+width, y, rightValue);
		}

	}

	/**
	 * Paints each side of a double blank domino. Calls DotImage to get pips.
	 * @param g Graphics Class
	 * @param width Width of one side of the domino.
	 * @param x Horizontal location of top-left corner of domino.
	 * @param y Verticle location of top-left corner of domino.
	 */
	public void paintDomino(Graphics g, int width, int x, int y) {
		this.height = width;
		this.width = width;
		DotImage dot = new DotImage(g, height, width);
		if (invert) {
			drawHalf(g, x, y);
			dot.drawSide(g, x, y, leftValue);
			drawHalf(g, x, y+height);
			dot.drawSide(g, x+width, y, rightValue);			
		} else {
			drawHalf(g, x, y);
			dot.drawSide(g, x, y, leftValue);
			drawHalf(g, x + width, y);
			dot.drawSide(g, x+width, y, rightValue);
		}
	}

	/**
	 * returns width of a side of the domino.
	 * @return width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * sets width of domino.
	 * @param width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * returns height of the domino.
	 * @return Height.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Sets the height of the domino.
	 * @param height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * returns horizontal location of top-left corner of domino
	 * @return x-value
	 */
	public int getX() {
		return x;
	}

	/**
	 * sets the horizontal location of top-left corner of domino.
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}
 /**
  * returns vertical location of top-left corner of domino
  * @return y-value
  */
	public int getY() {
		return y;
	}

	/**
	 * sets vertical location of top-left corner of domino
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * returns number of pips on left side of domino
	 * @return leftValue
	 */
	public int getleftValue(){
		return leftValue;
	}
	
	/**
	 * returns number of pips on right side of domino
	 * @return rightValue
	 */
	public int getRightValue(){
		return rightValue;
	}
	
	/**
	 * set value of domino
	 * @param leftValue
	 * @param rightValue
	 */
	public void setValue(int leftValue, int rightValue){
		this.leftValue = leftValue;
		this.rightValue = rightValue;
	}

	/**
	 * sets invert value
	 * Domino turns 90 degrees.
	 */
	public void invert() {
		invert = !invert;
	}
	
	/**
	 * Flips number of pips on each side of domino.
	 * Domino turns 180 degrees.
	 */
	public void flip(){
		int tmp = rightValue;
		rightValue = leftValue;
		leftValue = tmp;
	}
	
	/**
	 * Draws half of a Domino.
	 * @param g Graphics Class
	 * @param x Horizontal location of top-left corner
	 * @param y Vertical Location of top-left corner
	 */
	public void drawHalf(Graphics g, int x, int y){
		g.setColor(Color.white);
		g.fill3DRect(x, y, width, height, true);
		g.setColor(Color.black);
		g.draw3DRect(x, y, width, height, true);
	}
}
