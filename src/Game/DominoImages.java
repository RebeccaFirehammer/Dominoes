package Game;

/**
 * Creates a list of Domino images.
 */

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class DominoImages {
	
	/**
	 * An ArrayList of drawDomino objects
	 */
	private ArrayList<DrawDomino> drawDominos;
	
	/**
	 * Construcst a new DominoImages object.
	 * @param size The size of the Dominoes
	 * @param boneColor The background color of the Domino
	 * @param pipColor The pip color
	 * @param barColor The bar color
	 */
	public DominoImages(int size, Color boneColor, Color pipColor, Color barColor){
		Boneyard boneyard = new Boneyard();
		drawDominos = new ArrayList<DrawDomino>();
		
		for(Domino d: boneyard.getBoneyard()){
			DrawDomino draw = new DrawDomino(d, size, boneColor, pipColor, barColor);
			this.drawDominos.add(draw);
		}
	}
	
	/**
	 * Constructs a new DominoImages object.
	 * @param size The size of the Domino
	 * @param boneColor The background color of the Domino
	 * @param pipColor The pip color of the Domino
	 */
	public DominoImages(int size, Color boneColor, Color pipColor){
		this(size, boneColor, pipColor, pipColor);
	}
	
	/**
	 * Construcst a new DominoImages object.
	 * @param size The size of the Domino
	 * @param color The background color of the Domino
	 */
	public DominoImages(int size, Color color){
		this(size, Color.WHITE, color);
	}
	
	/**
	 * Constructs a new DominoImages object.
	 * @param size The size of the Domino
	 */
	public DominoImages(int size){
		this(size, Color.BLACK);
	}
	
	/**
	 * Flips the specified Domino by 90 degrees.
	 * @param d A Domino object
	 */
	public void flip(Domino d){
		for(DrawDomino draw: drawDominos){
			if(draw.getDomino().equals(d)){
				draw.rotate();
			}
		}
	}
	
	/**
	 * Inverts the sides of the specified Domino.
	 * @param d A Domino object
	 */
	public void invert(Domino d){
		for(DrawDomino draw: drawDominos){
			if(draw.getDomino().equals(d)){
				draw.invert();
			}
		}
	}
	
	/**
	 * Return an ImageIcon of the specified Domino.
	 * @param d A Domino object
	 * @return An ImageIcon
	 */
	public ImageIcon getImage(Domino d){
		for(DrawDomino draw: drawDominos){
			if(draw.getDomino().equals(d)){
				return draw.getImageIcon();
			}
		}
		return null;
	}
	
	/**
	 * Returns a BufferedImage of the specified Domino object.
	 * @param d A Domino object
	 * @return A BufferedImage object
	 */
	public BufferedImage getBufferedImage(Domino d){
		for(DrawDomino draw: drawDominos){
			if(draw.getDomino().equals(d)){
				return draw.getBufferedImage();
			}
		}
		return null;
	}
	
	/**
	 * Returns the width of the specified Domino
	 * @param d A Domino object
	 * @return An integer value representing the width of the Domino
	 */
	public int getWidth(Domino d){
		for(DrawDomino draw: drawDominos){
			if(draw.getDomino().equals(d)){
				return draw.getImageIcon().getIconWidth();
			}
		}
		return 0;
	}
	
	/**
	 * Returns the height of hte specified Domino
	 * @param d A domino object
	 * @return An integer value representing the height of the Domino
	 */
	public int getHeight(Domino d){
		for(DrawDomino draw: drawDominos){
			if(draw.getDomino().equals(d)){
				return draw.getImageIcon().getIconHeight();
			}
		}
		return 0;
	}
}
