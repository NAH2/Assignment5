import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;


public class ConnectFourAI extends Player{

	private final static int GAME_WIDTH = 10;
	private final static int GAME_HEIGHT = 7;
	private int m_time = 1500;
	
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
		Coordinate takeCoord = null ;
		int maximum = 0;
		int countCounter = 0;
		if (!listTwo.isEmpty()){
		 takeCoord = listTwo.get(0);
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
		}
		return takeCoord;
	}

	public void isYourMove() throws InterruptedException {
		setYourTurn(true);
		if (!(getGame().getPlayer1() instanceof Human || 
				getGame().getPlayer2() instanceof Human)){
		sendMove();
		}
	}

	public void sendMove() throws InterruptedException {
		new Thread(
				new Runnable() {
					public void run() {
						try {
							Coordinate move ;
							Thread.sleep(m_time);
							move =setAIMove();
							if (getYourTurn()) {
								
								getGame().moveMade(move);
								
								setYourTurn(false);
								
							}
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
		).start();
	}
	
	public void sendMove(Coordinate move) throws InterruptedException {
		class MyThread implements Runnable {
				Coordinate m_move;
			   public MyThread(Coordinate move) {
			       // store parameter for later user
				   m_move = move;
			   }

			   public void run() {
					try {
						m_move = setAIMove();
					} catch (IndexOutOfBoundsException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (getYourTurn()) {
						
						try {
							getGame().moveMade(m_move);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						setYourTurn(false);
						
					}
			   }
			}
		Runnable r = new MyThread(move);
		r.wait(m_time);
		new Thread(r).start();

	}
	public String toString() {
		String playerData = "OthelloAI," + getPlayerName() + ","
				+ getPlayerColour().getRGB() + "," + getYourTurn() + ",";

		return playerData;
	}

}

