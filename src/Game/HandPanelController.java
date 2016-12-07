package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class HandPanelController implements ActionListener {
	private GameModel model;
	private DominoesView view;
	
	public HandPanelController(DominoesView view, GameModel model){
		this.view = view;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals("Play")) {
			System.out.println("Play has been clicked");
			model.takeTurn();

		} else {
			JButton button = (JButton) e.getSource();
			DominoImage icon = (DominoImage) button.getIcon();
			model.setActive(icon.getDomino());

		}
		view.update();

	}
}