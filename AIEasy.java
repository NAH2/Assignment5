import java.util.*;
import java.awt.Color;

class AIEasy extends Player {
	
	private int m_time = 1500;
	
	public AIEasy(Game game, String name, Color color) {
		super(game, name, color);

	}
	
	public AIEasy(Game game) {
		super(game);
	}
	
	public ArrayList<Coordinate> getAvailableMoves (){
		ArrayList<Coordinate> a = new ArrayList <Coordinate> ();
		for (int x=0; x<getGame().getGrid().getGridWidth();x++){
			for (int y=0; y<getGame().getGrid().getGridHeight();y++){
				Coordinate  c1 =	new Coordinate (x, y, getGame().getPlayerTurn());
							
				if (getGame().isValidMove(c1)==true){
					a.add(c1);
				}
			}
		}
		return a;
	}

	public Coordinate setAIMove () throws IndexOutOfBoundsException {
		Random rnd = new Random();
	 
		int x = 0;
		ArrayList<Coordinate> a = new ArrayList <Coordinate> ();
		a=getAvailableMoves();
		if (a.size() == 0){
			return null;
		}
		x = rnd.nextInt(a.size());
		
		return a.get(x);
		
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

}