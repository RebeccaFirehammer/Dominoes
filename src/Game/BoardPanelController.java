package Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BoardPanelController implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		System.out.println("Domino placed at ("+x+", "+y+")");
		if(x>380 && y > 500 && y < 550){
			System.out.println("East Spoke");
		}else if(x<362 && y > 500 && y < 550){
			System.out.println("West Spoke");
		}else if(y<520 && x > 355 && x < 395){
			System.out.println("North Spoke");
		}else if(y > 565 && x > 355 && x < 395){
			System.out.println("South Spoke");
		}else{
			System.out.println("Not on Spoke");
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
