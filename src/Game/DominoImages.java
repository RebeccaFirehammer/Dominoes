package Game;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class DominoImages {
	
	private ArrayList<DrawDomino> drawDominos;
	
	public DominoImages(int size, Color boneColor, Color pipColor, Color barColor){
		Boneyard boneyard = new Boneyard();
		drawDominos = new ArrayList<DrawDomino>();
		
		for(Domino d: boneyard.getBoneyard()){
			DrawDomino draw = new DrawDomino(d, size, boneColor, pipColor, barColor);
			this.drawDominos.add(draw);
		}
	}
	
	public DominoImages(int size, Color boneColor, Color pipColor){
		this(size, boneColor, pipColor, pipColor);
	}
	
	public DominoImages(int size, Color color){
		this(size, Color.WHITE, color);
	}
	
	public DominoImages(int size){
		this(size, Color.BLACK);
	}
	
	public void flip(Domino d){
		for(DrawDomino draw: drawDominos){
			if(draw.getDomino().equals(d)){
				draw.rotate();
			}
		}
	}
	
	public void invert(Domino d){
		for(DrawDomino draw: drawDominos){
			if(draw.getDomino().equals(d)){
				draw.invert();
			}
		}
	}
	
	public ImageIcon getImage(Domino d){
		for(DrawDomino draw: drawDominos){
			if(draw.getDomino().equals(d)){
				return draw.getImageIcon();
			}
		}
		return null;
	}
	
	public BufferedImage getBufferedImage(Domino d){
		for(DrawDomino draw: drawDominos){
			if(draw.getDomino().equals(d)){
				return draw.getBufferedImage();
			}
		}
		return null;
	}
	
	public int getWidth(Domino d){
		for(DrawDomino draw: drawDominos){
			if(draw.getDomino().equals(d)){
				return draw.getImageIcon().getIconWidth();
			}
		}
		return 0;
	}
	
	public int getHeight(Domino d){
		for(DrawDomino draw: drawDominos){
			if(draw.getDomino().equals(d)){
				return draw.getImageIcon().getIconHeight();
			}
		}
		return 0;
	}
}
