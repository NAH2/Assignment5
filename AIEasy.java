/**
 *  \\file -AIEasy.java
 * 	\author - Tyrone Bramwell
 *	\date	-20/03/2014
 *
 *	\brief This class contains methods to create and run a AI for othello and conect four
 *		  which randomly picks a a valid move and performs the move.
 */
import java.util.*;
import java.awt.Color;

class AIEasy extends Player {
	/**
	* Sets the variable m_running which is used to decide if the AIEasy runs or not
	*\param run a boolean which selects if the AI should run
	*/
	public void SetRun(boolean run){
		m_running = run;
	}
	/**
	* Sets the the wait time befor which the ai performs a move
	*\param responseTime a integer for selecting the wait time
	*/
	public void SetTime(int responseTime){
		m_time = responseTime;
	}
	/**
	* Sets the variable m_running which is used to decide if the AIEasy runs or not
	*/
	public int getTime(){
		return m_running;
	}
	/**
	* Sets the the wait time befor which the ai performs a move
	*\return returns variable m_time which is used for waiting
	*/
	public boolean getRun(){
		return m_time;
	}
	/**
	* Constructor to crate AIEasy with parameters for game type
	* \param game a type of game for selecting the game
  	* \param name a string for the player name
	* \param color a type colour for selecting colour
	*/
	public AIEasy(Game game, String name, Color color) {
		super(game, name, color);
		m_running = true;

	}
	/**
	* Constructor to crate AIEasy with parameters for game type
	* \param game a type of game for selecting the game
	*/
	public AIEasy(Game game) {
		super(game);
		m_running = true;
	}
	/**
	* getAvailableMoves creates an array list of valid possiable moves for the game
	*\return arraylist of all possiable valid moves
	*/
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
	/**
	* setAIMove picks a random move from the list and returns a coordinate 
	*\return coordinate which is then placed into the grid
	*/
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
	/**
	* isYourMove used to run methods which are used to set it as the AI turn
	*/
	public void isYourMove() throws InterruptedException {
		setYourTurn(true);
		if (!(getGame().getPlayer1() instanceof Human || 
				getGame().getPlayer2() instanceof Human)){
		sendMove();
		}
	}
	/**
	* sendMove() a method used to send a valid move to a game of othello or connect
	 four.
	*/
	public void sendMove() throws InterruptedException {
		new Thread(
				new Runnable() {
					public void run() {
						if(m_running){
							try {
								Coordinate move ;
								Thread.sleep(m_time);
								move =setAIMove();
								if (getYourTurn()) {
									setYourTurn(false);
									getGame().moveMade(move);							
									
								}
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
		).start();
	}
	/**
	* sendMove a method used to send a valid move to a game of othello or 
	connect four.
	* \param move pass a coordinate object from the grid
	*/
	public void sendMove(Coordinate move) throws InterruptedException {
		class MyThread implements Runnable {
				Coordinate m_move;
			   public MyThread(Coordinate move) {
			       // store parameter for later user
				   m_move = move;
			   }

			   public void run() {
				   if(m_running){
						try {
							m_move = setAIMove();
						} catch (IndexOutOfBoundsException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (getYourTurn()) {
							
							try {
								
								setYourTurn(false);
								getGame().moveMade(m_move);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
				   }
			   }
			}
		Runnable r = new MyThread(move);
		r.wait(m_time);
		new Thread(r).start();

	}
	public static void main(String[] args) {
		
	}
	/** m_time variable is an integer used to hold the wait time. set to 1500 by 
	default if not changed */
	private int m_time = 1500;
	/** m_running variable is an boolean used to decided if the AIEasy should 
	run or not*/
	private boolean m_running;
}