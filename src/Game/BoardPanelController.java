package Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class BoardPanelController implements MouseListener {
	
	private ArrayList<ActiveLocation> actLocs;
	
	public BoardPanelController(ArrayList<ActiveLocation> actLocs){
		this.actLocs = actLocs;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		final int SPINNER = -1;
		final int NORTH = 0;
		final int SOUTH = 1;
		final int EAST = 2;
		final int WEST = 3;
		int x = e.getX();
		int y = e.getY();
		for(ActiveLocation loc: actLocs){
			if(x >= loc.getX() && x <= loc.getX() + loc.getWidth() && 
					y >= loc.getY() && y <= loc.getY() + loc.getHeight()){
				switch(loc.getSpoke()){
				case SPINNER:
					System.out.println("SPINNER");
					break;
				case NORTH:
					System.out.println("NORTH ACTIVE");
					break;
				case SOUTH:
					System.out.println("SOUTH ACTIVE");
					break;
				case EAST:
					System.out.println("EAST ACTIVE");
					break;
				case WEST:
					System.out.println("WEST ACTIVE");
					break;
				}
			}
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
