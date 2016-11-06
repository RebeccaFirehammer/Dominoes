package Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RoundPanel extends JPanel {

	private int score;
	
	private int bars;
	
	private int crosses;
	
	public RoundPanel(int score){
		updateScore(score);
	}
	
	public RoundPanel(){
		this(0);
	}
	
	public void updateScore(int score){
		if(score % 5 == 0){
			this.score = score;
			setTicks();
		}
		repaint();
	}
	
	private void setTicks(){
		this.crosses = (this.score / 25 >= 1) ? this.score / 25 : 0;
		this.bars = (this.score - (this.crosses * 25)) / 5;
	}
	
	public void paintComponent(Graphics g)
	{
		this.setBackground(Color.black);
		final int tallySpace = 3;
		int y1 = 5, y2 = 15;
		int x = (this.getWidth() / 6);
		Dimension d = new Dimension(x,y2);
		//draw tally marks with diagonal line through them
		for(int i = 0; i < this.crosses; i++){
			int reqSpace = 0;
			for(int j = 0; j < 5; j++){
			    g.setColor(Color.BLACK);
			    g.drawLine(x, y1, x, y2);
			    x += tallySpace;
			    reqSpace += tallySpace;
			}
		    g.setColor(Color.BLACK);
		    g.drawLine((int)d.getWidth()- 3,(int)d.getHeight() , x, y1);
		    
		    //set up next set up tally marks with diagonal line through them
		    x += tallySpace;
		    reqSpace += tallySpace;
		    
		    if((x + reqSpace) < this.getWidth()){
		        d.setSize(x, y2);
		    }else{
		    	y1 += 20;
		    	y2 += 20;
		    	x = (this.getWidth() / 6);
		    	d = new Dimension(x,y2);
		    }
		}
		
		//draw remaining tally marks
		for(int i = 0; i < this.bars; i++){
			int reqSpace = 0;
		    g.setColor(Color.BLACK);
		    g.drawLine(x, y1, x, y2);
		    x += tallySpace;
		    reqSpace += tallySpace;
		    
		    if((x + reqSpace) < this.getWidth()){
		        d.setSize(x, y2);
		    }else{
		    	y1 += 20;
		    	y2 += 20;
		    	x = (this.getWidth() / 6);
		    }
		}
	}
	
	public String toString(){
		return "Crosses = " + this.crosses +"\nBars = " + this.bars;
	}
}
