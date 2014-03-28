import java.util.*;

/**
 * \\file Game.Java
 * \author G. Tsang, Chun Kit So
 * \date 11/02/2014
 * 
 * \brief Main control class which processes the overall game logic.
 * 
 * This class contains the main control logic which allows this class to receive
 * player interactions, process them before sending them to the corresponding 
 * classes. Also initialises the subControl classes.
 * This class is tested through other classes
 */
public abstract class Game {
    /**
     * \brief enum to be stored in the grid object as players moves onto 
     * the board
     */
	public enum PlayerTurn {
		PLAYER1, PLAYER2, NONE, PLAYER1_AM, PLAYER2_AM
	}

	/**
	 * Retrieves the window class containing the program's GUI.
	 * 
	 * \return A pointer indicating the current instance of the window in which 
	 * this class is receiving and processing input to.
	 */
	public GameWindow getWindow() {
	    boolean test = false;
        if (test || m_test) {
            System.out.println("Game :: getWindow() BEGIN");
        }
        if (test || m_test) {
            System.out.println("Game :: getWindow() END");
        }
        
		return m_gameWindow;
	}

	/**
	 * Set's the window class in which this class will control and process.
	 * 
	 * \param window - The GameWindow instance which this class is to be linked 
	 * to.
	 * \return true on success
	 */
	public boolean setWindow(GameWindow window) {
	    boolean test = false;
        if (test || m_test) {
            System.out.println("Game :: setWindow() BEGIN");
        }
        
		m_gameWindow = window;
		
        if (test || m_test) {
            System.out.println("Game :: setWindow() END");
        }
        
        return true;
	}

	/**
	 * Retrieves a pointer to the class containing the data of the gameboard.
	 * 
	 * \return The grid class at it's current state.
	 */
	public Grid getGrid() {
	    boolean test = false;
        if (test || m_test) {
            System.out.println("Game :: getGrid() BEGIN");
        }
        if (test || m_test) {
            System.out.println("Game :: getGrid() END");
        }
        
		return m_grid;
	}

	/**
	 * Set's the grid class which will contain the gameboard's data.
	 * 
	 * \param grid - A pointer to the Grid class indicating which instance this 
	 * class should take data from about the gameboard.
	 * \return true on success
	 */
	public boolean setGrid(Grid grid) {
	    boolean test = false;
        if (test || m_test) {
            System.out.println("Game :: setGrid() BEGIN");
        }
	    
		m_grid = grid;
		
        if (test || m_test) {
            System.out.println("Game :: setGrid() END");
        }
        
        return true;
	}

	/**
	 * Retrieves the Player class of player 1 but throws NullPointerException if
	 * empty.
	 * 
	 * \return The Player class of player 1.
	 */
	public Player getPlayer1() {
	    boolean test = false;
        if (test || m_test) {
            System.out.println("Game :: getPlayer1() BEGIN");
        }
        
		if (m_player1 == null) {
			System.out.println("Game::GetPlayer1 - "
			                                + "m_player1 has not yet been set");
			throw new NullPointerException();
		} else {
		    if (test || m_test) {
	            System.out.println("Game :: getPlayer1() END");
	        }
		    
			return m_player1;
		}
		
        
	}

	/**
	 * Retrieves the Player class of player 2 but throws NullPointerException if
	 * empty.
	 * 
	 * \return The Player class of player 2.
	 */
	public Player getPlayer2() {
	    boolean test = false;
        if (test || m_test) {
            System.out.println("Game :: getPlayer2() BEGIN");
        }
	    
		if (m_player2 == null) {
			System.out.println("Game::GetPlayer2 - "
			                                + "m_player2 has not yet been set");
			throw new NullPointerException();
		} else {
	        if (test || m_test) {
	            System.out.println("Game :: getPlayer2() END");
	        }
	        
			return m_player2;
		}
	}

	/**
	 * Retrieves the which player's turn it currently is.
	 * 
	 * \return An enumerator "PlayerTurn" which can be either PLAYER1, PLAYER2 
	 * or NONE.
	 */
	public Game.PlayerTurn getPlayerTurn() {
	    boolean test = false;
        if (test || m_test) {
            System.out.println("Game :: getPlayerTurn() BEGIN");
        }
        if (test || m_test) {
            System.out.println("Game :: getPlayerTurn() END");
        }
        
		return m_playerTurn;
	}

	/**
	 * Set's a variable which indicates which player's turn it currently is.
	 * 
	 * \param Turn - An enumerator "PlayerTurn" which indicates which player's 
	 * turn it currently is. Can either be PLAYER1, PLAYER2 or NONE.
	 * 
	 * \return Returns TRUE if successful.
	 */
	public boolean setPlayerTurn(PlayerTurn turn) {
	    boolean test = false;
        if (test || m_test) {
            System.out.println("Game :: setPlayerTurn() BEGIN");
        }
        
		m_playerTurn = turn;
		
        if (test || m_test) {
            System.out.println("Game :: setPlayerTurn() END");
        }
        
		return true;
	}

	/**
	 * Retrieves the amount of turns which have occurred in a single game.
	 * 
	 * \return Returns an integer indicating the amount of turns.
	 */
	public int getTurnCount() {
	    boolean test = false;
        if (test || m_test) {
            System.out.println("Game :: getTurnCount() BEGIN");
        }
        if (test || m_test) {
            System.out.println("Game :: getTurnCount() END");
        }
        
		return m_turnCount;
	}

	/**
	 * Set's the amount of turns which have occurred currently in a game.
	 * 
	 * \param count - An integer indicating the amount of turns.
	 * 
	 * \return Returns TRUE if successful.
	 */
	public boolean setTurnCount(int count) {
	    boolean test = false;
        if (test || m_test) {
            System.out.println("Game :: setTurnCount() BEGIN");
        }
        
		m_turnCount = count;
		
        if (test || m_test) {
            System.out.println("Game :: setTurnCount() END");
        }
        
		return true;
	}

	/**
	 * Stores the Player class for player 1 but throws IllegalArgumentException
	 * if empty
	 * 
	 * \param player - Player class containing data of player 1
	 * 
	 * \return Returns true if successful
	 */
	public boolean setPlayer1(Player player) {
	    boolean test = false;
        if (test || m_test) {
            System.out.println("Game :: setPlayer1() BEGIN");
        }
        
		if (player == null) {
			throw new IllegalArgumentException(
					"Game::SetPlayer1 - Invalid player type(null) for player1");
		}
		m_player1 = player;
		
        if (test || m_test) {
            System.out.println("Game :: setPlayer1() END");
        }
        
		return true;
	}

	/**
	 * Stores the Player class for player 2 but throws IllegalArgumentException
	 * if empty
	 * 
	 * \param player - Player class containing data of player 2
	 * 
	 * \return Returns true if successful
	 */
	public boolean setPlayer2(Player player) {
	    boolean test = false;
        if (test || m_test) {
            System.out.println("Game :: setPlayer2() BEGIN");
        }
        
		if (player == null) {
			throw new IllegalArgumentException(
					"Game::SetPlayer2 - Invalid player type(null) for player2");
		}
		m_player2 = player;
		
        if (test || m_test) {
            System.out.println("Game :: setPlayer2() END");
        }
        
		return true;
	}

	/**
	 * Returns the score for player 1 as an integer.
	 * 
	 * \return Returns an integer indicating the score of player 1.
	 */
	public int getPlayer1Score() {
	    boolean test = false;
        if (test || m_test) {
            System.out.println("Game :: getPlayer1Score() BEGIN");
        }
        if (test || m_test) {
            System.out.println("Game :: getPlayer1Score() END");
        }
        
		return m_player1Score;
	}

	/**
	 * Returns the score for player2 as an integer.
	 * 
	 * \return Returns an integer indicating the score of player 2.
	 */
	public int getPlayer2Score() {
	    boolean test = false;
        if (test || m_test) {
            System.out.println("Game :: getPlayer2Score() BEGIN");
        }
        if (test || m_test) {
            System.out.println("Game :: getPlayer2Score() END");
        }
        
		return m_player2Score;
	}

	/**
	 * Sets player 1's score to a certain integer.
	 * 
	 * \param score - The new score as an integer for player 1.
	 * 
	 * \return Returns TRUE if successful
	 */
	protected boolean setPlayer1Score(int score) {
	    boolean test = false;
        if (test || m_test) {
            System.out.println("Game :: setPlayer1Score() BEGIN");
        }
        
		if (score < 0) {
			throw new IllegalArgumentException(
					"Game::AddPlayer1Score - Invalid integer(" + score
							+ ") to addition of score");
		}
		m_player1Score = score;
		
        if (test || m_test) {
            System.out.println("Game :: setPlayer1Score() END");
        }
		return true;
	}

	/**
	 * Sets player 1's score to a certain integer.
	 * 
	 * \param score - The new score as an integer for player 1.
	 * 
	 * \return Returns TRUE if successful
	 */
	protected boolean setPlayer2Score(int score) {
	    boolean test = false;
        if (test || m_test) {
            System.out.println("Game :: setPlayer2Score() BEGIN");
        }
        
		if (score < 0) {
			throw new IllegalArgumentException(
					"Game::AddPlayer2Score - Invalid integer(" + score
							+ ") to addition of score");
		}
		m_player2Score = score;
		
        if (test || m_test) {
            System.out.println("Game :: setPlayer2Score() END");
        }
        
		return true;
	}

	/**
	 * Constructor which initialises this class.
	 * 
	 * \param x - The width of the grid for the game as an integer.
	 * 
	 * \param y - The height of the grid for the game as an integer.
	 */
	public Game(int x, int y) {
	    boolean test = false;
        if (test || m_test) {
            System.out.println("Game :: Game() BEGIN");
        }
        
		setGrid(new Grid(x, y));
		
        if (test || m_test) {
            System.out.println("Game :: Game() END");
        }
	}

	/**
	 * method to calculate the scores of both players 
	 * 
	 * \return true on success
	 */
	public boolean setScores() {
	    boolean test = false;
        if (test || m_test) {
            System.out.println("Game :: setScores() BEGIN");
        }
        
		setPlayer1Score(0);
		setPlayer2Score(0);
		for (int i = 0; i < getGrid().getGridWidth(); i++) {
			for (int j = 0; j < getGrid().getGridHeight(); j++) {
				if (getGrid().getCoordinate(i, j).getValue() == 
				                                           PlayerTurn.PLAYER1) {
					setPlayer1Score(getPlayer1Score() + 1);
				} else if (getGrid().getCoordinate(i, j).getValue() == 
				                                           PlayerTurn.PLAYER2) {
					setPlayer2Score(getPlayer2Score() + 1);
				}
			}
		}
		
        if (test || m_test) {
            System.out.println("Game :: setScores() END");
        }
        
		return true;
	}

	/**
	 * Used to check whether the move is valid within set game rules, and if
	 * not, calls the GUI to display the error.
	 * 
	 * \param move - The move, as a Coordinate, which is to be validated under 
	 * the game rules.
	 * 
	 * \return Returns TRUE if it is a valid move, FALSE otherwise.
	 */
	protected boolean validateMove(Coordinate move) {
	    boolean test = false;
        if (test || m_test) {
            System.out.println("Game :: validateMove() BEGIN");
        }
        
		if (isValidMove(move)) {
			getWindow().displayInvalidMove(false);
			
	        if (test || m_test) {
	            System.out.println("Game :: validateMove() END");
	        }
	        
			return true;
		} else {
			getWindow().displayInvalidMove(move);
			
	        if (test || m_test) {
	            System.out.println("Game :: validateMove() END");
	        }
	        
			return false;
		}
	}

	protected void Getmessage(String msg) {
	    boolean test = false;
        if (test || m_test) {
            System.out.println("Game :: Getmessage() BEGIN");
        }
        
		if(getPlayer1() instanceof AIEasy){
			((AIEasy)getPlayer1()).SetRun(false);
		} else if(getPlayer1() instanceof OthelloAI) {
			((OthelloAI)getPlayer1()).SetRun(false);
		}
		if(getPlayer2() instanceof AIEasy){
			((AIEasy)getPlayer2()).SetRun(false);
		} else if(getPlayer2() instanceof OthelloAI) {
			((OthelloAI)getPlayer2()).SetRun(false);
		}
		getWindow().Displaymessage(msg);
		if(getPlayer1() instanceof AIEasy){
			((AIEasy)getPlayer1()).SetRun(true);
		} else if(getPlayer1() instanceof OthelloAI) {
			((OthelloAI)getPlayer1()).SetRun(true);
		}
		if(getPlayer2() instanceof AIEasy){
			((AIEasy)getPlayer2()).SetRun(true);
		} else if(getPlayer2() instanceof OthelloAI) {
			((OthelloAI)getPlayer2()).SetRun(true);
		}
		
        if (test || m_test) {
            System.out.println("Game :: Getmessage() END");
        }
	}
	
	

	/**
	 * Called whenever a game is to be reset to it's state at turn 0.
	 * 
	 * \throws InterruptedException
	 */
	public void reset() throws InterruptedException {
	    boolean test = false;
        if (test || m_test) {
            System.out.println("Game :: reset() BEGIN");
        }
        
		int x = getGrid().getGridWidth();
		int y = getGrid().getGridHeight();
		setGrid(new Grid(x, y));
		setPlayer1Score(0);
		setPlayer2Score(0);
		setTurnCount(0);
		getWindow().displayGrid(getGrid());
		getWindow().updateScore(getPlayer1Score(), getPlayer2Score());
		getWindow().displayPlayerTurn(getPlayerTurn());
		getWindow().SetOver(false);
		m_timer.setRunning();
		startTimer();
		resetGame();
		
        if (test || m_test) {
            System.out.println("Game :: reset() END");
        }
	}

	/**
	 * method to resume a game that has been loaded from file
	 *  
	 * \throws InterruptedException
	 */
	public void resumeGame() throws InterruptedException {
	    boolean test = false;
        if (test || m_test) {
            System.out.println("Game :: resumeGame() BEGIN");
        }
        
		setWindow(new GameWindow(this));
		setScores();
		getWindow().displayPlayerTurn(m_playerTurn);
		getWindow().updateScore(m_player1Score, m_player2Score);
		setTurnCount(m_player1Score + m_player2Score);
		getWindow().updateScore(m_player1Score, m_player2Score);
		setTurnCount(m_player1Score + m_player2Score);
		if (getPlayerTurn() == PlayerTurn.PLAYER1){
			getPlayer1().isYourMove();
		}
		else 
		{
			getPlayer2().isYourMove();
		}
		startTimer();
		
        if (test || m_test) {
            System.out.println("Game :: resumeGame() END");
        }
	}
	
	/**
	 * method to create and start a timer for the game
	 */
	public void startTimer() {
	    boolean test = false;
        if (test || m_test) {
            System.out.println("Game :: startTimer() BEGIN");
        }
        
		m_timer = new Timer(this);
		
        if (test || m_test) {
            System.out.println("Game :: startTimer() END");
        }
	}
	
	/**
	 * method to load and start a timer from file
	 * 
	 * \param Timer t - timer loaded from file
	 */
	public void startTimer(Timer t) {
	    boolean test = false;
        if (test || m_test) {
            System.out.println("Game :: startTimer() BEGIN");
        }
        
		if (m_timer == null) {
			// DO NOTHING
		} else {
			m_timer.setRunning();
		}
		m_timer = t;
		
        if (test || m_test) {
            System.out.println("Game :: startTimer() END");
        }
	}
	
	/**
	 * Accessor method to get m_timer
	 * 
	 * \return Timer m_timer
	 */
	public Timer getTimer() {
	    boolean test = false;
        if (test || m_test) {
            System.out.println("Game :: getTimer() BEGIN");
        }
        if (test || m_test) {
            System.out.println("Game :: getTimer() END");
        }
        
		return m_timer;
	}
	
	/**
	 * Abstract method to reset the game to the begining
	 * 
	 * \throws InterruptedException
	 */
	protected abstract void resetGame() throws InterruptedException;
	
	/**
     * Abstract method to reset the game to the begining
     * 
     * \param Player p - player whos turn it is
     * \throws InterruptedException
     */
	protected abstract void resetGame(Player p) throws InterruptedException;


	/**
	 * Abstract Method used to test whether or not the game is over.
	 * 
	 * \return Returns a boolean, TRUE if the game is over and FALSE if the game
	 * is still in progress.
	 */
	public abstract boolean isOver();

	/**
	 * Abstract Method used to calculate the player who has won the game.
	 * 
	 * \return Returns the PlayerTurn enumerator who won the game.
	 */
	public abstract Game.PlayerTurn isWinner();

	/**
	 * Abstract method used to change which player's turn it currently is
	 * depending on the game rules.
	 * 
	 * \return Returns an enumerator of type PlayerTurn which indicates which
	 * turn it is, PLAYER1 or PLAYER2.
	 */
	protected abstract PlayerTurn nextPlayer();

	/**
	 * Abstract method used to check whether or not this move is valid in
	 * relation to the game rules.
	 * 
	 * \param move - The class containing the data to the current player's move.
	 * 
	 * \return Returns a boolean indicating if the move is valid, TRUE for valid
	 * and FALSE for not valid
	 */
	protected abstract boolean isValidMove(Coordinate move);

	/**
	 * Absract method which is used to indicate all the pieces in a grid that is
	 * to be changed due to the game rules.
	 * 
	 * \param move -The move which the player has made which will be used to
	 * process the changed pieces.
	 * 
	 * \return Returns an ArrayList<Coordinate> containing all the pieces to be
	 * changed.
	 */
	protected abstract ArrayList<Coordinate> takeMove(Coordinate move);

	/**
	 * Abstract method which is used to empty the set that stores the winning
	 * piece coordinates for opening a new game
	 */
	protected abstract void emptyWin();

	/**
	 * Abstract method which is used to return the set that stores the winning
	 * piece coordinates for showing the winning pieces graphically
	 * 
	 * \return Returns the set containing the winning piece coordinates
	 */
	protected abstract Set<Coordinate> getWin();

	/**
	 * Change by Mathew Lloyd 711293 abstract method to check the total score
	 * for number of positions taken. Used in OthelloAI to calculate the move
	 * that creates the most changes.
	 * 
	 * \param xy - The coordinate of the piece on the grid
	 * \return int of the score from all changes that would be made
	 */
	public abstract int moveScore(Coordinate xy);
	
	/**
     * Main method which is called whenever the game is first started.
     * 
     * \throws InterruptedException
     */
    public abstract void start() throws InterruptedException;

    // **********************
    // private ArrayList<Coordinate> changes;
    // public ArrayList<Coordinate> getMove(){
    // return changes;
    // }
    // **********************
    /**
     * Called whenever a player has made a move. Processes the move and calls
     * the GUI and storage classes to store the processed move's data.
     * 
     * \param move - The move which the player has made as a Coordinate class.
     * \throws InterruptedException
     */
    public abstract void moveMade(Coordinate move) throws InterruptedException;
	
	/**
	 * method os overridden by connectfour
	 * 
	 * \param Coordinate coord2
	 * \return int 0
	 */
	public int blockOpponentChecker(Coordinate coord2) {
		return 0;
	}

	/** reference to gameWindow */
	private GameWindow m_gameWindow;
	/** reference to grid */
	private Grid m_grid;
	/** reference to player1 */
	private Player m_player1;
	/** reference player2 */
	private Player m_player2;
	/** store score for player 1 */
	private int m_player1Score = 0;
	/** store score for player2 */
	private int m_player2Score = 0;
	/** store the turn count */
	private int m_turnCount = 0;
	/** store the current players turn */
	private PlayerTurn m_playerTurn = PlayerTurn.PLAYER1;
	/** reference to timer object */
	private Timer m_timer;
	/** test variable */
	private boolean m_test = false;
}
