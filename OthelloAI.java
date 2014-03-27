import java.awt.Color;
import java.util.*;

/**
 *  \\file	OthelloAI.Java
 * 	\author	Mathew Lloyd 711293
 * 	\date	24/03/2014
 * 	
 * \brief The AI for Othello to make a move
 * 
 * The following class sets the AI move for the Othello game. Also there 
 * are controls of threads in this class for delaying the computer response
 * to add the feeling that the computer is 'thinking' about it's turn.
 * 
 */
public class OthelloAI extends Player {

	
	
	
	/**
	 * The method sets the response time for the AI to respond to the opponents
	 * move.
	 * 
	 * \param responseTime an integer value of which to set the response time to
	 * \return true if the method completes
	 */
	public boolean SetTime(int responseTime){
		boolean test = false;
		if (test || m_test) {
            System.out.println("OthelloAI :: SetTime() BEGIN");
            }
		m_time = responseTime;
		
		if (test || m_test) {
            System.out.println("ConnectFourAI :: SetTime() END");
            }
		
		return true;
	}
	
	/**
	 * The method returns the response time for the AI.
	 * \return m_time The response time for the AI
	 */
	public int getTime(){
		boolean test = false;
		if (test || m_test) {
            System.out.println("OthelloAI :: getTime() BEGIN");
            }
		if (test || m_test) {
            System.out.println("OthelloAI :: getTime() END");
            }
		return m_time;
		
	}
	
	/**
	 * The method sets when the thread is to be running, used for thread control
	 * \param run true when game is running, false when not.
	 * \return true if the method completes
	 */
	public boolean SetRun(boolean run){
		boolean test = false;
		if (test || m_test) {
            System.out.println("OthelloAI :: SetRun() BEGIN");
            }
		m_running = run;
		if (test || m_test) {
            System.out.println("OthelloAI :: SetRun() END");
            }
		
		return true;
	}
	
	/**
	 * The method is getting whether the thread is supposed to be running or not
	 * \return m_running, true when game is running, false when not.
	 */
	public boolean getRun(){
		boolean test = false;
		if (test || m_test) {
            System.out.println("OthelloAI :: getRun() BEGIN");
            }
		if (test || m_test) {
            System.out.println("OthellAI :: getRUN() END");
            }
		return m_running;
	}
	
	/**
	 * The first constructor for setting a type of player of OthelloAI,
	 * all data is sent to the super class of Player. Sets the running state of
	 * the thread to be true.
	 * \param game the type of game (Othello only due to PlayerSettings)
	 * \see PlayerSettings.java
	 * \param name the name of the player	 
	 * \param color the piece color of the player
	 */
	public OthelloAI(Game game, String name, Color color) {
		super(game, name, color);
		boolean test = false;
		if (test || m_test) {
            System.out.println("OthelloAI :: OthelloAI() BEGIN");
            }
		m_running = true;
		
		if (test || m_test) {
            System.out.println("OthelloAI :: OthellOAI() END");
            }

	}

	/**
	 * The second constructor for setting a type of player of OthelloAI,
	 * all data is sent to the super class of Player. Sets the running state of
	 * the thread to be true.
	 * \param game the type of game (Othello only due to PlayerSettings)
	 * \see PlayerSettings.java
	 */
	public OthelloAI(Game game) {
		super(game);
		boolean test = false;
		if (test || m_test) {
            System.out.println("OthelloAI :: OthelloAI() BEGIN");
            }
		m_running = true;
		
		if (test || m_test) {
            System.out.println("OthelloAI :: OthelloAI() END");
            }
	}

	/**
	 * Loops through each coordinate on the board to check if the coordinate has
	 * a piece located there in the grid or not. The number of coordinates in 
	 * the list will decrease as the number of available moves decreases, 
	 * will initially have every board position that does not contain a piece.
	 * \return a list of available coordinates.
	 */
	private ArrayList<Coordinate> getAvailableMoves() {
		boolean test = false;
		if (test || m_test) {
            System.out.println("OthelloAI :: getAvailableMoves() BEGIN");
            }
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
		if (test || m_test) {
            System.out.println("OthelloAI :: getAvailableMoves() END");
            }
		return list;
	}

	/**Method which sets the AI position. It gets the list of available comments
	 * from the getAvailableMoves() method. Loops through the list and tries
	 * to find the maximum number of pieces that would be changed and then 
	 * returns the maximum turn coordinate as the move to take.
	 * \return Coordinate the Coordinate that would be taken.*/
	public Coordinate setAIMove() throws IndexOutOfBoundsException, 
	InterruptedException {
		boolean test = false;
		if (test || m_test) {
            System.out.println("OthelloAI :: setAIMove() BEGIN");
            }
		ArrayList<Coordinate> listTwo = new ArrayList<Coordinate>();
		listTwo = getAvailableMoves();
		
		int maximum = 0;
		if(!listTwo.isEmpty()){
			Coordinate takeCoord = listTwo.get(0);
			for (Coordinate coord : listTwo) {
				int move = getGame().moveScore(coord);
				System.out.println("Max: " + maximum + "move: " + move);
				if (move >= maximum) {
					takeCoord = coord;
					maximum = move;
				}
			}
			maximum = 0;
			
			if (test || m_test) {
            System.out.println("ConnectFourAI :: setAIMove() END");
            }
			return takeCoord;
		} else {
			if (test || m_test) {
	            System.out.println("ConnectFourAI :: setAIMove() END");
	            }
			return null;
		}
		
	}

	/**
	 * method to make it set the turn of the AI to be it's move, then
	 * calls for the move to be taken
	 * \return true if the method completes
	 */
	public boolean isYourMove() throws InterruptedException {
		boolean test = false;
		if (test || m_test) {
            System.out.println("OthelloAI :: isYourMove() BEGIN");
            }
		setYourTurn(true);
		if (!(getGame().getPlayer1() instanceof Human || 
				getGame().getPlayer2() instanceof Human)){
		sendMove();
		}
		
		if (test || m_test) {
            System.out.println("ConnectFourAI :: isYourMove() END");
            }
		return true;
	}

	/**
	 * method that sends the coordinate to take back to the GameWindow class,
	 * Has a thread running so that it can make it 'feel' like the computer is
	 * deciding which piece to take to make the game feel more realistic.
	 * Calls setAIMove to get the available Coordinate. method was created to 
	 * allow AI to work.
	 * \see GameWindow.java
	 * \return true if the method completes
	 */
	public boolean sendMove() throws InterruptedException {
		new Thread(
				new Runnable() {
					public void run() {
						if(getRun()){
							try {
								Coordinate move ;
								Thread.sleep(getTime());
								move =setAIMove();
								if (getYourTurn()) {

									setYourTurn(false);
									getGame().moveMade(move);
									
								}
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}
		).start();
		return true;
	}
	

	/**
	 * The default sendMove that was in the Player.java class. 
	 * Gets the move that the AI will do from the setAIMove method. Does the
	 * same as the sendMove() method above but is used in case it is called,
	 * above method was created to allow AI to work.
	 * \see Player.java
	 * \return true if the method completes
	 */
	public boolean sendMove(Coordinate move) throws InterruptedException {
		boolean test = false;
		if (test || m_test) {
            System.out.println("OthelloAI :: sendMove() BEGIN");
            }
		class MyThread implements Runnable {
				Coordinate m_move;
			   public MyThread(Coordinate move) {
			       // store parameter for later user
				   m_move = move;
			   }

			   public void run() {
				   if(getRun()){
						try {
							m_move = setAIMove();
						} catch (IndexOutOfBoundsException e) {
								e.printStackTrace();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if (getYourTurn()) {
							
							try {
								getGame().moveMade(m_move);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							setYourTurn(false);
						}
				   }
			   }
			}
		Runnable r = new MyThread(move);
		r.wait(getTime());
		new Thread(r).start();
		if (test || m_test) {
            System.out.println("ConnectFourAI :: sendMove() END");
            }
		return true;
	}
	/**
	 * Main to test the creation of games, tests that pieces are actually set.
	 * @param args
	 */
	public static void main(String args[]){
		Game game = new ConnectFour();
		OthelloAI C4AI;
		OthelloAI C4AI2;

		C4AI2 = new OthelloAI(game, "test1",Color.blue);
		C4AI = new OthelloAI(game,"test2",Color.red);

		if(C4AI.getGame() == game){System.out.println("Set Game Success");}
		if(C4AI.getPlayerName() == "test2")System.out.println("Name Set");
		if(C4AI.getPlayerColour() == Color.red)System.out.println("Color Ok");
		C4AI = new OthelloAI(game, "test3",Color.yellow);
		C4AI = new OthelloAI(game);
		if(C4AI.getGame() == game)System.out.println("Correct Game Set");
		Coordinate cord = new Coordinate(C4AI.GAME_WIDTH,C4AI.GAME_HEIGHT,
				Game.PlayerTurn.PLAYER1);
		C4AI.SetTime(GAME_WIDTH);
		if(C4AI.getTime() == GAME_WIDTH)System.out.println("Time edited");
		C4AI.SetRun(true);
		if(C4AI.getRun() == true)System.out.println("Game Running");

	}
	
	/** The game board size limits*/
	private final static int GAME_WIDTH = 8;
	private final static int GAME_HEIGHT = 8;
	/** The response time value */
	private int m_time = 1500;
	/** The boolean for checking whether a thread should be running*/
	private boolean m_running;
	/** For testing purposes */
	private boolean m_test = true;
}
