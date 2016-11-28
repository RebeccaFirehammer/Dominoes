package Game;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class HandPanel extends JPanel implements ActionListener{
	private int side;
	private ArrayList<Domino> dominoes;
	private ArrayList<DominoImage> images;
	private GameModel model;
	
	
	public HandPanel(){
		this.dominoes = new ArrayList<>();
		this.images = new ArrayList<>();
	}
	
	public HandPanel(int width) {
		this.dominoes = new ArrayList<>();
		this.images = new ArrayList<>();
		this.side = width;
	}
	
	public HandPanel(int width, GameModel model){
		this.model = model;
		this.dominoes = model.getPlayers().get(0).getHand();
		this.side = width;
		this.images = new ArrayList<DominoImage>();
		storeImages(dominoes);	
		
	}	

	private void storeImages(ArrayList<Domino> d) {
		int i = 0;
		images.clear();
		for(Domino domino: d){
			DominoImage image = new DominoImage();
			image.setValue(domino);
			image.invert();
			images.add(i, image);
			i++;
		}		
	}

	public ArrayList<Domino> getHand() {
		return dominoes;
	}

	public void  updateHand(int player) {
		this.dominoes.clear();
		this.dominoes = model.getPlayers().get(player).getHand();
		storeImages(dominoes);
		addButtons();
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
	
	private void addButtons(){
		for(DominoImage i : images){
			JButton button = new JButton(i);
			button.setPreferredSize(new Dimension(i.getIconWidth(), i.getIconHeight()));
			button.addActionListener(this);
			this.add(button);
		}
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		this.setBackground(Color.CYAN);		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		DominoImage icon = (DominoImage) button.getIcon();
		model.setActive(icon.getDomino());		
	}
}