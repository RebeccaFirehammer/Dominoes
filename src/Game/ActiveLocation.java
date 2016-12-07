package Game;

public class ActiveLocation {
	/**
	 * Holds spoke # where 0 = North, 1 = South, 2 = East, and 3 = West
	 */
	private int spoke;
	/**
	 * x coordinate of active domino
	 */
	private int x;
	/**
	 * y coordinate of active domino
	 */
	private int y;
	/**
	 * width of active domino
	 */
	private int width;
	/**
	 * height of active domino
	 */
	private int height;
	
	/**
	 * ActiveLocation provides setters and getters for the active domino clicked from the board
	 * @param spoke, an int that represents North, South, East or West
	 * @param x, an int that represents x coordinate of active domino
	 * @param y, an int that represents y coordinate of active domino
	 * @param width, an int that represents width of active domino
	 * @param height, an int that represents height of active domino
	 */
	public ActiveLocation(int spoke, int x, int y, int width, int height){
		setSpoke(spoke);
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
	}
	/**
	 * sets spoke's cardinal direction
	 * @param spoke, an int that represents North, South, East or West
	 */
	public void setSpoke(int spoke){
		this.spoke = spoke;
	}
	/**
	 * gets spoke's cardinal direction
	 * @return
	 */
	public int getSpoke(){
		return spoke;
	}
	/**
	 * sets active domino's x coordinate
	 * @param x
	 */
	public void setX(int x){
		this.x = x;
	}
	
	public int getX(){
		return x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public int getY(){
		return y;
	}
	
	public void setWidth(int width){
		this.width = width;
	}
	
	public int getWidth(){
		return width;
	}
	
	public void setHeight(int height){
		this.height = height;
	}
	
	public int getHeight(){
		return height;
	}
	
	public String toString(){
		return "Spoke: " + spoke +"\nX: " + x + "\nY: " + y + 
				"\nWidth: " + width + "\nHeight: " + height;
	}
}
