import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;


public class ConnectFourAI extends Player{

	private final static int GAME_WIDTH = 10;
	private final static int GAME_HEIGHT = 7;
	
	public ConnectFourAI(Game game, String name, Color color) {
		super(game, name, color);
		
	}

	public ConnectFourAI(Game game) {
		super(game);
		
	}

	public ArrayList<Coordinate> getAvailableMoves() {
		ArrayList<Coordinate> list = new ArrayList<Coordinate>();
		
		if (getYourTurn()) {
			for(int x = 0;x<GAME_WIDTH;x++){
				if(getGame().getGrid().getCoordinate(x, 0).getValue() == Game.PlayerTurn.NONE){
					for(int y = GAME_HEIGHT - 1;y>-1;y--){
						if(getGame().getGrid().getCoordinate(x,y).getValue() == Game.PlayerTurn.NONE){
							Coordinate c1 = new Coordinate(x,y,getGame().getPlayerTurn());
							list.add(c1);
							y=0;
						}
					}
				//}else{
					//Coordinate c1 = new Coordinate(x,0,getGame().getPlayerTurn());
					//list.add(c1);
				}
			}
		}

		return list;
	}

	public Coordinate setAIMove() {
		ArrayList<Coordinate> listTwo = new ArrayList<Coordinate>();
		listTwo = getAvailableMoves();
		
		int maximum = 0;
		int countCounter = 0;
		Coordinate takeCoord = listTwo.get(0);
		for (Coordinate coord : listTwo) {
			int move = getGame().moveScore(coord);
			if (move == 0){
				countCounter++;
			}
			if(move >= maximum) {
				takeCoord = coord;
				maximum = move;
			}
		}
		for(Coordinate coord2 : listTwo){
			if(getGame().blockOpponentChecker(coord2) == 3 && maximum != 3){
				return takeCoord = coord2;
			}
		}
		
		if(countCounter == listTwo.size()){
			Random rnd = new Random();
			takeCoord = listTwo.get(rnd.nextInt(9));
		
		}
		
		maximum = 0;
		return takeCoord;
	}

	public void isYourMove() {
		setYourTurn(true);

	}

	public void sendMove(Coordinate move) throws InterruptedException {
		move = setAIMove();
		if (getYourTurn()) {
			setYourTurn(false);
			getGame().moveMade(move);
		}
	}

	public String toString() {
		String playerData = "OthelloAI," + getPlayerName() + ","
				+ getPlayerColour().getRGB() + "," + getYourTurn() + ",";

		return playerData;
	}

	@Override
	public void sendMove() throws InterruptedException {
		// TODO Auto-generated method stub
		
	}

}
