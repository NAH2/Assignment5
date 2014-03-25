import java.awt.Color;
import java.util.*;

public class OthelloAI extends Player {

	private final static int GAME_WIDTH = 8;
	private final static int GAME_HEIGHT = 8;
	private int m_time = 1500;
	
	public void SetTime(int responseTime){
		m_time = responseTime;
	}
	
	public OthelloAI(Game game, String name, Color color) {
		super(game, name, color);

	}

	public OthelloAI(Game game) {
		super(game);
	}

	public ArrayList<Coordinate> getAvailableMoves() {
		ArrayList<Coordinate> list = new ArrayList<Coordinate>();
		if (getYourTurn()) {
			for (int y = 0; y < GAME_HEIGHT; y++) {
				for (int x = 0; x < GAME_WIDTH; x++) {
					Coordinate c1 = new Coordinate(x, y, getGame()
							.getPlayerTurn());

					if (getGame().isValidMove(c1)) {
						list.add(c1);
					}
				}
			}
		}

		return list;
	}

	public Coordinate setAIMove() throws IndexOutOfBoundsException, InterruptedException {
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
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (getYourTurn()) {
						
						getGame().moveMade(m_move);
						
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
