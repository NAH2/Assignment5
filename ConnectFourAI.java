import java.awt.Color;
import java.util.ArrayList;


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
					for(int y = GAME_HEIGHT;y>0;y--){
						if(getGame().getGrid().getCoordinate(x,y).getValue() == Game.PlayerTurn.NONE){
							Coordinate c1 = new Coordinate(x,y,getGame().getPlayerTurn());
							list.add(c1);
							y=0;
						}
					}
				}else{
					Coordinate c1 = new Coordinate(x,0,getGame().getPlayerTurn());
					list.add(c1);
				}
			}
		}

		return list;
	}

	public Coordinate setAIMove() {
		ArrayList<Coordinate> listTwo = new ArrayList<Coordinate>();
		listTwo = getAvailableMoves();
		for (Coordinate Cs : listTwo) {
			System.out.println(Cs.getX() + ", " + Cs.getY());
		}
		int maximum = 0;
		System.out.println(getGame());
		Coordinate takeCoord = listTwo.get(0);
		for (Coordinate coord : listTwo) {
			System.out.println(coord.getX());
			int move = getGame().moveScore(coord);
			System.out.println("Max: " + maximum + "move: " + move);
			if (move >= maximum) {
				takeCoord = coord;
				maximum = move;
			}
		}
		maximum = 0;
		return takeCoord;
	}

	public void isYourMove() {
		setYourTurn(true);

	}

	public void sendMove(Coordinate move) {
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
