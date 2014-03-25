import java.util.Random;
import java.util.ArrayList;
import java.awt.Color;

class AIEasy extends Player {
	
	private final static int GAME_WIDTH = 8;
	private final static int GAME_HEIGHT = 8;
	
	public AIEasy(Game game, String name, Color color) {
		super(game, name, color);

	}
	
	public AIEasy(Game game) {
		super(game);
	}
	
	public ArrayList<Coordinate> getAvailableMoves (){
		ArrayList<Coordinate> a = new ArrayList();
		for (int x=0; x<GAME_HEIGHT;x++){
			for (int y=0; y<GAME_WIDTH;y++){
				Coordinate  c1 =	new Coordinate (x, y, getGame().getPlayerTurn());
							
				if (getGame().isValidMove(c1)==true){
					a.add(c1);
				}
			}
		}
		return a;
	}

	public Coordinate setAIMove (){
		Random rnd = new Random();

	 
		int x = 0;
		x = rnd.nextInt(getAvailableMoves().size());
		return getAvailableMoves().get(x);
		
	}
	
	public void isYourMove(){
		setYourTurn(true);
	}

	public void sendMove(Coordinate move){
		move = setAIMove();
		if (getYourTurn()) {
			setYourTurn(false);
			getGame().moveMade(move);
		}
	}
	
	public String toString(){
		String playerData = "OthelloAI," + getPlayerName() + ","
				+ getPlayerColour().getRGB() + "," + getYourTurn() + ",";

		return playerData;
	}
	
	public static void main(String[] args) {

	}

}