package Game;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.Graphics;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class HandPanel extends JPanel{
	private int side;
	private ArrayList<Domino> dominoes;
	private ArrayList<DominoImage> images;
	
	
	public HandPanel(){
		this.dominoes = new ArrayList<>();
		this.images = new ArrayList<>();
	}
	
	public HandPanel(int width) {
		this.dominoes = new ArrayList<>();
		this.images = new ArrayList<>();
		this.side = width;
	}
	
	public HandPanel(int width, ArrayList<Domino> d){
		this.dominoes = d;
		this.side = width;
		this.images = new ArrayList<DominoImage>();
		storeImages(d);	
		
	}	

	private void storeImages(ArrayList<Domino> d) {
		int i = 0;
		for(Domino domino: d){
			DominoImage image = new DominoImage();
			image.setValue(domino.getEndA(), domino.getEndB());
			image.invert();
			images.add(i, image);
			i++;
		}		
	}

	public ArrayList<Domino> getHand() {
		return dominoes;
	}

	public void  updateHand(ArrayList<Domino> dominoes) {
		this.dominoes = dominoes;
		storeImages(dominoes);
	}

	public ArrayList<DominoImage> getImages() {
		return images;
	}

	public void setImages(ArrayList<DominoImage> images) {
		this.images = images;
	}

	public int getSide() {
		return side;
	}
	public void setSide(int width){
		this.side = width;
	}	
	
	public Domino getDomino(DominoImage d){
		return this.dominoes.get(images.indexOf(d));
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		this.setBackground(Color.CYAN);
		int x = (int) ((getWidth()/2)- ((images.size()/2) * side));
		for(DominoImage i : images){
			i.paintDomino(g, side, x, 20);			
			x += side+10;
		}		
	}
}
