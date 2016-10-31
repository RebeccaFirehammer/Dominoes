package Game;

import java.util.ArrayList;

public class GameModel {
	
	private ArrayList<Player> players;
	
	public GameModel(){
		this.players = new ArrayList<Player>();
		for(int i = 1; i <= 4; i++){
			players.add(new Player("Player" + i));
		}
	}
	
	public ArrayList<Player> getPlayers(){
		return (new ArrayList<Player>(players));
	}
	
}
