package Game;

public class ActiveLocation {
	
	private int spoke;
	
	private int x;
	
	private int y;
	
	private int width;
	
	private int height;
	
	public ActiveLocation(int spoke, int x, int y, int width, int height){
		setSpoke(spoke);
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
	}
	
	public void setSpoke(int spoke){
		this.spoke = spoke;
	}
	
	public int getSpoke(){
		return spoke;
	}

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
