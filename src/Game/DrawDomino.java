package Game;

/**
 * Draws a Domino object
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class DrawDomino{
	
	/**
	 * The size of each end of the Domino
	 */
	private int endSize;
	
	/**
	 * The size of the Domino pips
	 */
	private int pipSize;
		
	/**
	 * The Domino that will be drawn
	 */
	private Domino bone;
	
	/**
	 * The size of the bar in the middle of the Domino
	 */
	private int barSize;
	
	/**
	 * A BufferedImage of the Domino
	 */
	private BufferedImage image;
	
	/**
	 * An ArrayList of Dimensions that specify the pip locations
	 */
	private ArrayList<Dimension> loc;
	
	/**
	 * A boolean value specifying if the Domino is flipped 90 degrees
	 */
	private boolean flip = false;
	
	/**
	 * The color of the bar
	 */
	private Color barColor;
	
	/**
	 * The color of the background of the Domino
	 */
	private Color boneColor;
	
	/**
	 * The pip color of the Domino
	 */
	private Color pipColor;
		
	/**
	 * Constructs a new DrawDomino object
	 * @param bone The Domino
	 * @param size The size of the Domino
	 * @param boneColor The background color of the Domino
	 * @param pipColor The color of the pips
	 * @param barColor The color of the bar
	 */
	public DrawDomino(Domino bone, int size, Color boneColor, Color pipColor, Color barColor){
		this.bone = bone;
		this.boneColor = boneColor;
		this.pipColor = pipColor;
		this.barColor = barColor;
		endSize = (size < 12) ? 12 : size;
		barSize = (endSize / 25 > 0) ? (endSize / 25) : 1;
		pipSize = ((this.endSize / 10) < 1) ? 1: this.endSize / 10;
		loc = new ArrayList<Dimension>();
		draw();
	}
	
	/**
	 * Constructs a new DrawDomino object
	 * @param bone The Domino
	 * @param size The size of the Domino
	 * @param boneColor The background color of the Domino
	 * @param pipColor The color of the pipsr
	 */
	public DrawDomino(Domino d, int size, Color boneColor, Color pipColor){
		this(d, size, boneColor, pipColor, pipColor);
	}
	
	/**
	 * Constructs a new DrawDomino object
	 * @param bone The Domino
	 * @param size The size of the Domino
	 * @param color The background color of the Domino
	 */
	public DrawDomino(Domino d, int size, Color color){
		this(d, size, color.WHITE, color, color);
	}
	
	/**
	 * Constructs a new DrawDomino object
	 * @param bone The Domino
	 * @param size The size of the Domino
	 */
	public DrawDomino(Domino d, int size){
		this(d, size, Color.BLACK);
	}
	
	/**
	 * Construcst a new DrawDomin object
	 * @param d The domino
	 */
	public DrawDomino(Domino d){
		this(d, 20);
	}
	
	/**
	 * Constructs a new DrawDomino object
	 */
	public DrawDomino(){
		this(new Domino(0,0));
	}
	
	/**
	 * Draws the domino
	 */
	public void draw(){
		int pipsA = bone.getEndA();
		int pipsB = bone.getEndB();
		if(bone.isAOpen()){
			pipsA = bone.getEndB();
			pipsB = bone.getEndA();
		}
		
		image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB_PRE);
		Graphics2D g = image.createGraphics();
		
		BufferedImage aEnd = new BufferedImage(endSize, endSize, BufferedImage.TYPE_INT_ARGB_PRE);
		Graphics2D g1 = aEnd.createGraphics();
		drawEnd(g1, pipsA, 0, 0, boneColor, pipColor);
		
		BufferedImage bEnd = new BufferedImage(endSize, endSize, BufferedImage.TYPE_INT_ARGB_PRE);
	
		Graphics2D g2 = bEnd.createGraphics();
		drawEnd(g2, pipsB, 0, 0, boneColor, pipColor);
		
		
		g.drawImage(aEnd, 0, 0, null);
		drawBar(g);
		g.drawImage(bEnd, 0, (endSize + barSize), null);
	}
	
	/**
	 * Resizes the Domino
	 * @param size The size of the domino
	 */
	public void resize(int size){
		endSize = (size < 12) ? 12 : size;
		barSize = (endSize / 25 > 0) ? (endSize / 25) : 1;
		pipSize = ((this.endSize / 6) < 1) ? 1: this.endSize / 6;
		draw();
	}
	
	/**
	 * Draws the bar on the Domino
	 * @param g A graphics object
	 */
	private void drawBar(Graphics g){
		g.setColor(boneColor);
		g.fill3DRect(1, endSize, endSize, barSize, false);
		g.setColor(barColor);
		g.fill3DRect(1, endSize, endSize, barSize, false);
	}
		
	/***
	 * Draws a pip
	 * @param g A graphics object
	 * @param x The x location
	 * @param y The y location
	 */
	public void drawPip(Graphics g, int x, int y){
		//g.fillOval(x, y, pipSize, pipSize);
		g.fillArc(x, y, pipSize, pipSize, 0, 360);
		g.setColor(Color.BLACK);
		g.drawArc(x, y, pipSize, pipSize, 0, 360);
		
	}
	
	/**
	 * Draws a pi
	 * @param g A graphics object
	 * @param x The x location
	 * @param y The y location
	 * @param color The color of the pip
	 */
	public void drawPip(Graphics g, int x, int y, Color color){
		g.setColor(color);
		drawPip(g, x, y);
	}
	
	/**
	 * Draws one end of the Domino
	 * @param g A graphics object
	 * @param pips The number of pips
	 * @param x The x location
	 * @param y The y location
	 * @param dColor The color of the Domino
	 * @param pColor The pip color of the  domino
	 */
	public void drawEnd(Graphics g, int pips, int x, int y, Color dColor, Color pColor){
		g.setColor(dColor);
		g.fill3DRect(x, y, endSize, endSize, true);
		g.setColor(Color.black);
		g.draw3DRect(x, y, endSize, endSize, true);
		
		locations(pips, x, y);
		int index[] = new int[]{};
		switch(pips){
		case 1:
			index = new int[]{4};
			break;
		case 2:
			index = new int[]{0,8};
			break;
		case 3:
			index = new int[]{0,4,8};
			break;
		case 4:
			index = new int[]{0,2,6,8};
			break;
		case 5:
			index = new int[]{0,2,4,6,8};
			break;
		case 6:
			index = new int[]{0,2,3,5,6,8};
			break;
		default:
			break;
		}
		for(int i: index){
			drawPip(g, loc.get(i).width, loc.get(i).height, pColor);
		}
	}
	
	/**
	 * Determines where the pips will be drawn.
	 * @param pips The number of pips
	 * @param x The x location
	 * @param y The y location
	 */
	private void locations(int pips, int x, int y){
		loc.clear();
		int nextX = 0;
		int nextY = 0;
		int xCount = 1;
		int yCount = 1;
		int yOffset = endSize/20 + ((pipSize/4 < 1) ? 1 : pipSize/4);
		int xOffset = endSize/20 + ((pipSize/4 < 1) ? 1 : pipSize/4);
		if(pipSize < 3){
			xOffset = 1;
			yOffset = 1;
		}else if(pipSize > 7){
			xOffset -= pipSize/4;
		}
		
		for(int i = 1; i <= 9; i++){
			nextY = (endSize * yCount/4) - yOffset;
			switch(xCount){ //x positions
			case 1:
				nextX = (endSize * xCount/4) - xOffset;
				xCount++;
				break;
			case 2:
				nextX = (endSize * xCount/4) - xOffset;
				xCount++;
				break;
			case 3:
				nextX = (endSize * xCount/4) - xOffset;
				xCount = 1;
				yCount ++;
				break;
			}
			loc.add(new Dimension(nextX, nextY));
		}
	}
		
	/**
	 * Returns a buffered image of this Domino
	 * @return A buffered image
	 */
	public BufferedImage getBufferedImage(){
		return image;
	}
	
	/**
	 * Rotates the Domino by 90 degrees
	 */
	public void rotate(){
		if(!flip){
			BufferedImage temp = new BufferedImage(image.getHeight(), image.getWidth(),
													BufferedImage.TYPE_INT_ARGB_PRE);
			Graphics2D g = temp.createGraphics();
			g.rotate(Math.toRadians(-90), image.getWidth(), image.getHeight() / 2);
			g.translate(image.getHeight()/2, 0);
			g.drawImage(image, 0, 0, null);
			image = temp;
			flip = !flip;
		}else{
			flip = !flip;
			draw();
		}
	}
	
	/**
	 * Inverts the ends of the Domino
	 */
	public void invert(){
		bone.flipEndA();
		draw();
	}
	
	/**
	 * Returns an image icon of this Domino object
	 * @return A image icon
	 */
	public ImageIcon getImageIcon(){
		return new ImageIcon(image);
	}
	
	/**
	 * Returns the width of this Domino.
	 * @return An integer value specifying the width
	 */
	public int getWidth(){
		return (!flip) ? endSize : (this.endSize * 2) + barSize;
	}
	
	/**
	 * Returns the height of this Domino.
	 * @return An integer value specifying the height of the Domino
	 */
	public int getHeight(){
		return (!flip) ? (this.endSize * 2) + barSize : endSize;
	}
		
	/**
	 * Returns A Domino
	 * @return A Domino object
	 */
	public Domino getDomino(){
		return this.bone;
	}	
}