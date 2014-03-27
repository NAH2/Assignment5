import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 *  \\file	ConnectFourAI.Java
 * 	\author	Mathew Lloyd 711293
 * 	\date	24/03/2014
 * 	
 * \brief The AI for ConnectFour to make a move
 * 
 * The following class sets the AI move for the ConnectFour game. Also there 
 * are controls of threads in this class for delaying the computer response
 * to add the feeling that the computer is 'thinking' about it's turn.
 * 
 */

public class ConnectFourAI extends Player{

	
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
            System.out.println("ConnectFourAI :: SetTime() BEGIN");
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
            System.out.println("ConnectFourAI :: getTime() BEGIN");
            }
		if (test || m_test) {
            System.out.println("ConnectFourAI :: getTime() END");
            }
		return m_time;
	}
	
	/**
	 * The method sets when the thread is to be running, used for thread control
	 * \param run true when game is running, false when not.
	 * \return true if the method completes
	 */
	public Boolean SetRun(boolean run){
		boolean test = false;
		if (test || m_test) {
            System.out.println("ConnectFourAI :: SetRun() BEGIN");
            }
		m_running = run;
		if (test || m_test) {
			System.out.println("ConnectFourAI :: SetRun() END");
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
            System.out.println("ConnectFourAI :: getRun() BEGIN");
            }
		if (test || m_test) {
			System.out.println("ConnectFourAI :: getRun() END");
		}
		return m_running;
	}
	
	/**
	 * The first constructor for setting a type of player of ConnectFourAI,
	 * all data is sent to the super class of Player. Sets the running state of
	 * the thread to be true.
	 * \param game the type of game (ConnectFour only due to PlayerSettings)
	 * \see PlayerSettings.java
	 * \param name the name of the player	 
	 * \param color the piece color of the player
	 */
	public ConnectFourAI(Game game, String name, Color color) {
		super(game, name, color);
		boolean test = false;
		if (test || m_test) {
            System.out.println("ConnectFourAI :: ConnectFourAI() BEGIN");
            }
		m_running = true;
		if (test || m_test) {
			System.out.println("ConnectFourAI :: ConnectFourAI() END");
		}
	}

	/**
	 * The second constructor for setting a type of player of ConnectFourAI,
	 * all data is sent to the super class of Player. Sets the running state of
	 * the thread to be true.
	 * \param game the type of game (ConnectFour only due to PlayerSettings)
	 * \see PlayerSettings.java
	 */
	public ConnectFourAI(Game game) {
		super(game);
		boolean test = false;
		if (test || m_test) {
            System.out.println("ConnectFourAI :: ConnectFourAI() BEGIN");
            }
		
		m_running = true;
		if (test || m_test) {
			System.out.println("ConnectFourAI :: ConnectFourAI() END");
		}
	}

	/**
	 * Loops through each coordinate on the board to check if the coordinate has
	 * a piece located there in the grid or not. Goes from the bottom of the 
	 * grid to be more efficient to find the first available coordinate in
	 * each column
	 * \return a list of available coordinates.
	 */
	private ArrayList<Coordinate> getAvailableMoves() {
		boolean test = false;
		if (test || m_test) {
            System.out.println("ConnectFourAI :: getAvailableMoves() BEGIN");
            }
		ArrayList<Coordinate> list = new ArrayList<Coordinate>();
		if (getYourTurn()) {
			for(int x = 0;x<GAME_WIDTH;x++){
				for(int y = GAME_HEIGHT - 1;y>NEG_ONE;y--){
					if(getGame().getGrid().getCoordinate(x,y).getValue() == 
							Game.PlayerTurn.NONE){
						Coordinate c1 = 
								new Coordinate(x,y,getGame().getPlayerTurn());
						list.add(c1);
						y=0;
						}
					}
				}
			}
		if (test || m_test) {
			System.out.println("ConnectFourAI :: getAvailableMoves() END");
		}
		return list;
	}

	/**
	 * method that sets the move that the AI will do. The method calls the
	 * getAvailableMoves() method above to get a list of available coordinates
	 * then looks for the move that has the biggest chain of pieces. 
	 * If all positions come out with a zero chain (Initial start of game) then
	 * the position will be picked to a random point.
	 * The game will also check if the opponent has a chain of 3 pieces, if so
	 * it will attempt to block the opponent unless it can win itself.
	 * \return Coordinate to take
	 */
	public Coordinate setAIMove() {
		boolean test = false;
		if (test || m_test) {
            System.out.println("ConnectFourAI :: setAIMove() BEGIN");
            }
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
			if(getGame().blockOpponentChecker(coord2) == 
					WIN_CHAIN && maximum != WIN_CHAIN){
				return takeCoord = coord2;
			}
		}
		
		if(countCounter == listTwo.size()){
			Random rnd = new Random();
			takeCoord = listTwo.get(rnd.nextInt(listTwo.size()));
		
		}
		
		maximum = 0;
		}
		if (test || m_test) {
			System.out.println("ConnectFourAI :: setAIMove() END");
		}
		return takeCoord;
	}

	/**
	 * method to make it set the turn of the AI to be it's move, then
	 * calls for the move to be taken
	 * \return true if the method completes
	 */
	public boolean isYourMove() throws InterruptedException {
		boolean test = false;
		if (test || m_test) {
            System.out.println("ConnectFourAI :: isYourMove() BEGIN");
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
		boolean test = false;
		if (test || m_test) {
            System.out.println("ConnectFourAI :: sendMove() BEGIN");
            }
		new Thread(
				new Runnable() {
					public void run() {
						if(getRun()){
							try {
								Coordinate move ;
								Thread.sleep(getTime());
								move =setAIMove();
								if (getYourTurn()) {
									
									getGame().moveMade(move);
									
									setYourTurn(false);
									
								}
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}
		).start();
		if (test || m_test) {
			System.out.println("ConnectFourAI :: sendMove() END");
		}
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
		return true;
		}
	
	public static void main(String args[]){
		Game game = new ConnectFour();
		ConnectFourAI C4AI;
		ConnectFourAI C4AI2;

		C4AI2 = new ConnectFourAI(game, "test1",Color.blue);
		C4AI = new ConnectFourAI(game,"test2",Color.red);
		if(C4AI.getGame() == game){System.out.println("Set Game Success");}
		if(C4AI.getPlayerName() == "test2")System.out.println("Name Set");
		if(C4AI.getPlayerColour() == Color.red)System.out.println("Color Ok");
		C4AI = new ConnectFourAI(game, "test3",Color.yellow);
		C4AI = new ConnectFourAI(game);
		System.out.println(C4AI.setAIMove());
		Coordinate cord = new Coordinate(C4AI.GAME_WIDTH,C4AI.GAME_HEIGHT,
				Game.PlayerTurn.PLAYER1);
		C4AI.SetTime(C4AI.GAME_WIDTH);
		if(C4AI.getTime() == C4AI.GAME_WIDTH)System.out.println("Time edited");
		C4AI.SetRun(true);
		if(C4AI.getRun() == true)System.out.println("Game Running");
	}
	
	

	/**The Game width and height of ConnectFour*/
	private final int GAME_WIDTH = 10;
	private final int GAME_HEIGHT = 7;
	/**To be able to use the value of -1 for the coding conventions*/
	private final int NEG_ONE = -1;
	/**The number of pieces in a row before the win*/
	private final int WIN_CHAIN = 3;
	/**Thread sleep time*/
	private int m_time = 1500;
	/**If the thread should be running or not*/
	private boolean m_running;
	/**For testing purposes*/
	private boolean m_test = true;
}

