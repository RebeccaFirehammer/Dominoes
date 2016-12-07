package Game;

/**
 * A controller for the BoardPanel.
 */

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class BoardPanelController implements MouseListener {
	
	/**
	 * An ArrayList containing the locations of the Active Dominoes
	 */
	private ArrayList<ActiveLocation> actLocs;
	
	/**
	 * The Game Model
	 */
	private GameModel model;
	
	/**
	 * Construcsts a new BoardPanelController
	 * @param actLocs The locations of the active Dominoes.\
	 * @param model The Game Model
	 */
	public BoardPanelController(ArrayList<ActiveLocation> actLocs, GameModel model){
		this.actLocs = actLocs;
		this.model = model;
	}

	/**
	 * Handles a mouse clicking event on the board.
	 */
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
					//if(!(model.getBoard().getSpokes().get(0).size() > 0 && model.getBoard().getSpokes().get(1).size() > 0)){
						if(y >= loc.getY() && y <= loc.getY() + (loc.getHeight()/2)){
							System.out.println("SPINNER NORTH ACTIVE");
							model.setActiveSpoke(NORTH);
							break;
						}
						else if (y >= loc.getY() && y >= loc.getY() + (loc.getHeight()/2)){
							System.out.println("SPINNER SOUTH ACTIVE");
							model.setActiveSpoke(SOUTH);
							break;
						}
					//}
					//Not Working
					//else if(x <= loc.getX()  && x <= loc.getX() + (loc.getWidth())/2){
					//	System.out.println("SPINNER EAST ACTIVE");
					//	break;
					//}
					//else if(x >= loc.getX() && x <= loc.getX() + (loc.getWidth())/2){
					//	System.out.println("SPINNER WEST ACTIVE");
					//	break;
					//}
				case NORTH:
					System.out.println("NORTH ACTIVE");
					model.setActiveSpoke(NORTH);
					break;
				case SOUTH:
					System.out.println("SOUTH ACTIVE");
					model.setActiveSpoke(SOUTH);
					break;
				case EAST:
					System.out.println("EAST ACTIVE");
					model.setActiveSpoke(EAST);
					break;
				case WEST:
					System.out.println("WEST ACTIVE");
					model.setActiveSpoke(WEST);
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
