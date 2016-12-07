package Game;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class HandPanel extends JPanel /*implements ActionListener*/{
	private int side;
	private ArrayList<Domino> dominoes;
	private ArrayList<DominoImage> images;
	private GameModel model;
	public JButton button, play;
	private HandPanelController handController;
	
	public HandPanel(){
		this.dominoes = new ArrayList<>();
		this.images = new ArrayList<>();
	}
	
	public HandPanel(int width) {
		this.dominoes = new ArrayList<>();
		this.images = new ArrayList<>();
		this.side = width;
	}
	
	public HandPanel(int width, GameModel model, HandPanelController control){
		this.model = model;
		this.handController = control;
		this.dominoes = model.getPlayers().get(0).getHand();
		this.side = width;
		this.images = new ArrayList<DominoImage>();
		storeImages(dominoes);
		addButtons();	
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
		this.dominoes = model.getPlayers().get(player).getHand();
		storeImages(dominoes);
		addButtons();
		validate();
		repaint();
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
		this.removeAll();
		for(DominoImage i : images){
			JButton button = new JButton(i);
			button.setPreferredSize(new Dimension(i.getIconWidth(), i.getIconHeight()));
			button.addActionListener(handController);
			this.add(button);
		}
		JButton play = new JButton("Play");
		play.addActionListener(handController);
		add(play);
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		this.setBackground(Color.CYAN);
	}

	/*@Override
	public void actionPerformed(ActionEvent e) {
		button = (JButton) e.getSource();
		String command = button.getActionCommand();
		if(command.equals("Play")){
			System.out.println("Play has been clicked");
			model.takeTurn();
			
		}
		else{
			DominoImage icon = (DominoImage) button.getIcon();
			model.setActive(icon.getDomino());
		}
		
	}*/
}