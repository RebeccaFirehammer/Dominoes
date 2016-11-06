package Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RoundPanel extends JPanel {

	/**
	 * The score to be displayed in "Tally marks"
	 */
	private int score;
	
	/**
	 * number of vertical lines representing a score
	 */
	private int bars;
	
	/**
	 * The number of lines with a cross through them (5 tally marks)
	 */
	private int crosses;
	
	/**
	 * Constructs a RoundPanel with a specified score.
	 * @param score
	 */
	public RoundPanel(int score){
		updateScore(score);
	}
	
	/**
	 * Constructs a RoundPanel with a default score of zero.
	 */
	public RoundPanel(){
		this(0);
	}
	
	/**
	 * Updates the score.
	 * @param score An integer value specifying the score to update
	 */
	public void updateScore(int score){
		if(score % 5 == 0){ //score most be a multiple of 5
			this.score = score;
			setMarks();
		}
		repaint();
	}
	
	/**
	 * sets the number of tally marks (crosses and bars) that will be displayed.
	 */
	private void setMarks(){
		this.crosses = (this.score / 25 >= 1) ? this.score / 25 : 0; 
		this.bars = (this.score - (this.crosses * 25)) / 5;
	}
	
	/**
	 * Draws the tally marks on the panel.
	 * @param g A Graphics object that will draw tally marks
	 */
	public void paintComponent(Graphics g)
	{
		final int tallySpace = 3;   //space between tally marks
		int y1 = 5, y2 = 15;		//initial tally mark y coordinates
		int x = (this.getWidth() / 6); //initial tally mark x coordinate
		Dimension d = new Dimension(x,y2); //used to draw diagonal tally mark
		
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
		    
		    
		    if((x + reqSpace) < this.getWidth()){ //check if more marks can be drawn on same axis
		        d.setSize(x, y2);
		    }else{  //move marks down to next "line"
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
		    
		    if((x + reqSpace) < this.getWidth()){ //check if more marks can be drawn on same axis
		        d.setSize(x, y2);
		    }else{  //move marks down to next "line"
		    	y1 += 20;
		    	y2 += 20;
		    	x = (this.getWidth() / 6);
		    }
		}
	}
	
	/**
	 * Returns a string representation of this Object.
	 * @return A string literal specifying information about this object
	 */
	public String toString(){
		return "Crosses = " + this.crosses +"\nBars = " + this.bars;
	}
}
