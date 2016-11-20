package Game;

/**
 * Creates a RoundPanel that displays each player's
 * current round score in the form of "tally" marks.
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RoundPanel extends JPanel {

	/**
	 * The player's current score for the round
	 */
	private int score;
		
	/**
	 * The number of tally marks to draw
	 */
	private int tallyMarks;
	
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
			this.tallyMarks = this.score / 5;
		}
		repaint();
	}
	
	/**
	 * Draws the tally marks on the panel.
	 * @param g A Graphics object that will draw tally marks
	 */
	public void paintComponent(Graphics g){
		final Color MARK_COLOR = Color.BLACK;    //tally mark color
		
		//constants used for resizing purposes
		final int X_OFFSET = this.getWidth() / 18;   //space between tally marks
		final int FIRST_X = X_OFFSET * 2;  		     //starting x position
		final int Y_OFFSET = this.getHeight() / 26;  //space between rows
		
		//used for tally mark positions
		int y1 = this.getHeight() / 99;			//initial tally mark y coordinates
		int y2 = this.getHeight() / 30;		
		int x = FIRST_X; 						//initial tally mark x coordinate
		Dimension d = new Dimension(x , y2); 	//used to draw diagonal tally mark
		
		int marks = 0;
		for(int i = 1 ; i <= this.tallyMarks; i++){
			marks++;
			switch(marks % 5){ //determines if line should be straight or diagonal
			case 0:	//draw diagonal mark
			    g.setColor(MARK_COLOR);
			    g.drawLine((int)d.getWidth()- 3,(int)d.getHeight() , x, y1);
			    x += X_OFFSET;
			    d.setSize(x, y2);
			    marks = 0; 
				break;
			default: //draw straight mark
				if((x + X_OFFSET) <= (this.getWidth() - X_OFFSET)){ //draw on current row
					g.setColor(MARK_COLOR);
					g.drawLine(x, y1, x, y2);
					x += X_OFFSET;
				}else{ //draw on next row
			    	y1 += Y_OFFSET;
			    	y2 += Y_OFFSET;
			    	x = FIRST_X;
					d.setSize(x, y2);
					g.setColor(MARK_COLOR);
					g.drawLine(x, y1, x, y2);
					x += X_OFFSET;
			    	marks = 1;
				}
				break;
			}
		}
	}
		
	/**
	 * Returns a string representation of this Object.
	 * @return A string literal specifying information about this object
	 */
	public String toString(){
		return "Current Round Score = " + this.score + "\nTally Marks =" + this.tallyMarks;
	}
}
