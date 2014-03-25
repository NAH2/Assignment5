import java.awt.Color;
import java.util.*;
/*
 *  @file	-Othello.Java
 * 	@author	-B. Golightly
 * 	@date	-12/02/2014
 * 	
 * 	/brief	Extends Game with the rules of Othello as specified. Methods are
 * 			provided to check if a move is valid, query the score of a potential
 * 			move so that the AI can know how good it is, and provides a way to
 * 			take a move, return a list of changes after a move has been made.
 * 			In the case of Othello this means returning a list of captured
 * 			peices; other classes that also extend game may only return a list
 * 			of one item i.e. only the peice that is the result of the move.
 */

public class Othello extends Game
{
	private final static int GAME_WIDTH  = 8;
	private final static int GAME_HEIGHT = 8;

	/* 
	* Return the winning piece coordinates
	* @return the set which stores the winning piece coordinates
	*/
	public Set<Coordinate> getWin(){// should be GetWin()
		//System.out.println("win is empty?"+m_win.isEmpty());
		return m_win;
	}
	
	public boolean[][] Getavailablemov(){
		return m_availableMov;
	}
	
	public void Setavailablemov (boolean[][] availableMov){
		m_availableMov = availableMov;
	}
	/* 
	* Empty the set which stores the winning piece coordinates
	*/
	protected void emptyWin(){
		m_win.clear();
	}
	
	/**
	 * Construtor for a game of Othello
	 */
	public Othello() {
		// If the game class can initialse a grid of this size, that would be
		// great :-)
		super(GAME_WIDTH, GAME_HEIGHT);
		//resetGame();
	}
	
	public void start() throws InterruptedException {
		boolean m_Trace = false;
		Grid grid = super.getGrid();
		
		if(m_Trace) { System.out.println("Game::Start() - Game has started");}
		setWindow(new GameWindow(this));
		resetGame();
		
		availableMove();
	}
	
	
	/**
	 * Resets the starting pieces.
	 * @throws InterruptedException 
	 */
	public void resetGame() throws InterruptedException {
		Grid grid = super.getGrid();
		
		// Initialise starting peices
		if (super.getPlayer1().getPlayerColour().equals(Color.BLACK))
		{
		    super.setPlayerTurn(Game.PlayerTurn.PLAYER1);
		    grid.setCoordinate(new Coordinate(3, 3, Game.PlayerTurn.PLAYER1));
		    grid.setCoordinate(new Coordinate(4, 3, Game.PlayerTurn.PLAYER2));
		    grid.setCoordinate(new Coordinate(3, 4, Game.PlayerTurn.PLAYER2));
		    grid.setCoordinate(new Coordinate(4, 4, Game.PlayerTurn.PLAYER1));
		    getPlayer1().isYourMove();
		    getWindow().displayPlayerTurn(Game.PlayerTurn.PLAYER1);
			if 	(getPlayer1() instanceof OthelloAI){
				Thread.sleep(500);
				getPlayer1().sendMove();
			}
		}
		else 
		{
			super.setPlayerTurn(Game.PlayerTurn.PLAYER2);
			grid.setCoordinate(new Coordinate(3, 3, Game.PlayerTurn.PLAYER2));
			grid.setCoordinate(new Coordinate(4, 3, Game.PlayerTurn.PLAYER1));
			grid.setCoordinate(new Coordinate(3, 4, Game.PlayerTurn.PLAYER1));
			grid.setCoordinate(new Coordinate(4, 4, Game.PlayerTurn.PLAYER2));
			getPlayer2().isYourMove();
			getWindow().displayPlayerTurn(Game.PlayerTurn.PLAYER2);
			if 	(getPlayer2() instanceof OthelloAI){
				Thread.sleep(500);
				getPlayer2().sendMove();
			}
		}
	    startTimer();
		availableMove();
	}

	private void availableMove(){
		for (int y = 0; y < GAME_HEIGHT; y++) {
			for (int x = 0; x < GAME_WIDTH; x++) {
				
				//reset
				if (getGrid().getCoordinate(x, y).getValue() == Game.PlayerTurn.PLAYER1_AM ||
					getGrid().getCoordinate(x, y).getValue() == Game.PlayerTurn.PLAYER2_AM)
				{
					getGrid().setCoordinate(new Coordinate(x, y, Game.PlayerTurn.NONE));
				}
				
				if (getPlayerTurn().equals(Game.PlayerTurn.PLAYER1)){
					Coordinate c1 = new Coordinate(x, y, Game.PlayerTurn.PLAYER1);
				 if (isValidMove(c1)) {
					 Coordinate P1avaiableMov = new Coordinate(x, y, Game.PlayerTurn.PLAYER1_AM);
					 getGrid().setCoordinate(P1avaiableMov);
				 }
				}
				else if (getPlayerTurn().equals(Game.PlayerTurn.PLAYER2)) {
					System.out.println("P2 turn");
				 Coordinate c2 = new Coordinate(x, y, Game.PlayerTurn.PLAYER2);
				 if (isValidMove(c2)) { 
					 Coordinate P2avaiableMov = new Coordinate(x, y, Game.PlayerTurn.PLAYER2_AM);
					 getGrid().setCoordinate(P2avaiableMov);
				 }
				}
			}
		}
	}
	
	private boolean checkPassTurn(){
		for (int y = 0; y < GAME_HEIGHT; y++) {
			for (int x = 0; x < GAME_WIDTH; x++) {
				if (getGrid().getCoordinate(x, y).getValue() == Game.PlayerTurn.PLAYER1_AM &&
						getPlayerTurn().equals(Game.PlayerTurn.PLAYER1)){
					return false;
				}
				if (getGrid().getCoordinate(x, y).getValue() == Game.PlayerTurn.PLAYER2_AM &&
						getPlayerTurn().equals(Game.PlayerTurn.PLAYER2)){
					return false;
				}
			}
			}
		
		setPlayerTurn(nextPlayer());

		return true;
	}
	
	/**
	 * (PRIVATE) Queries the game to see if there are any valid moves remaining
	 * for either player.
	 * @return	True if a move can be made, false otherwise.
	 */
	private boolean isAnyValidMove() {
		Grid grid = getGrid();
		// The game may end when the board is completely filled
		if (super.getTurnCount() == GAME_WIDTH * GAME_HEIGHT) {
			debug("IsAnyValidMove()", "board filled");
			return false;
		}
		
		// Or when no valid moves remain (which may happen BEFORE the board
		// is fully filled (e.g. see Vlasakova 1 - 63 Schotte, European Grand Prix Prague 2011)
		m_Trace = false;
		for (int y = 0; y < GAME_HEIGHT; y++) {
			for (int x = 0; x < GAME_WIDTH; x++) {
				 Coordinate c1 = new Coordinate(x, y, Game.PlayerTurn.PLAYER1);
				 Coordinate c2 = new Coordinate(x, y, Game.PlayerTurn.PLAYER2);
				 
				 if (isValidMove(c1)) { return true; }
				 if (isValidMove(c2)) { return true; }
			}
		}
		m_Trace = true;
		
		// Otherwise...
		return false;
	}
	/**
	 * Checks if a given player selecting a given coordinate is a valid move.
	 * @param player A valid player object used by the current game.
	 * @param xy A coordinate specifying x and y values into the grid.
	 * @return	True if the specified move can be made, false otherwise.
	 */
	protected boolean isValidMove(Coordinate xy) {
		debug("isValidMove", "" + xy, "");
		
		// Check that the move lies on the board somewhere vaugely sensible
		if (xy.getX() < 0) { throw new IllegalArgumentException("bad x value"); }
		if (xy.getY() < 0) { throw new IllegalArgumentException("bad y value"); }
		if (xy.getX() >= GAME_WIDTH)  { throw new IllegalArgumentException("bad x value"); }
		if (xy.getY() >= GAME_HEIGHT) { throw new IllegalArgumentException("bad y value"); }
		if (xy.getValue() == Game.PlayerTurn.NONE) {
			throw new IllegalArgumentException("bad turn value"); }
		
		// Check that the space is empty. If not, the move cannot be made.
		if (getGrid().getCoordinate(xy.getX(), xy.getY()).getValue() ==
			Game.PlayerTurn.PLAYER1 || 
			getGrid().getCoordinate(xy.getX(), xy.getY()).getValue() ==
			Game.PlayerTurn.PLAYER2) { return false; }
		
		// A move is valid if it "traps" the other player's peices between
		// itself and another of the same player's peice, along diagonals or
		// horizontally or vertically. This means checking in eight directions.
		if (checkBound(xy, +1,  0)) { return true; } // right
		if (checkBound(xy, -1,  0)) { return true; } // left
		if (checkBound(xy,  0, +1)) { return true; } // down
		if (checkBound(xy,  0, -1)) { return true; } // up
		if (checkBound(xy, +1, +1)) { return true; } // bottom-right
		if (checkBound(xy, -1, +1)) { return true; } // bottom-left
		if (checkBound(xy, +1, -1)) { return true; } // top-right
		if (checkBound(xy, -1, -1)) { return true; } // top-left
		
		// otherrwise the move is invalid
		debug("isValidMove", "" + xy, "no bound found");

		return false;
	}
	
	/**
	 * (PRIVATE) checkBound checks to see if the opposing player's peices
	 * are bound/trapped in a given direction by the current player's peices.
	 */
	private boolean checkBound(Coordinate start, int xdir, int ydir) {
		Grid grid = getGrid();
		boolean opponentFound = false;
		
		debug("checkBound", "" + start +", " + xdir +", " + ydir, "");
		
		Coordinate current = new Coordinate(start);
		current.move(xdir, ydir);
		
		while (current.within(0, 0, GAME_WIDTH, GAME_HEIGHT)) {
			Coordinate check = grid.getCoordinate(current);
			
			debug("checkBound", "checking " + check);
			
			if (check.isEmpty()) {
				// Finding an empty coordinate means that this isn't a valid
				// trap.
				debug("checkBound", "returns false due to check.isEmpty()");
				
				return false;
			}
			else if (check.notEquals(start.getValue())) {
				// If the peice belongs to the opponent, then set a flag to
				// record this
				opponentFound = true;
			}
			else if (check.equals(start.getValue())) {
				// If the peice belonds to the current player, then it only
				// counts as bounding an opponent's peice if the flag has been
				// set.
				debug("checkBound()", "returns opponentFound==" + opponentFound);
				
				return opponentFound;
			}
			
			current.move(xdir, ydir);
		}
		
		debug("checkBound", "returns false (edge of grid)");
		return false;
	}
	
	/**
	 * Takes a move, returning a list of squares to capture by the player (this
	 * doesn't actually write to the grid itself; this means that you can use
	 * this method to see how good a move is before you commit it to the grid -
	 * which would be helpful for implementing the AI).
	 * @param player A valid player object used by the current game.
	 * @param xy A coordinate specifying x and y values into the grid specifying
	 * 			 a valid move.
	 * @return	A list of captured peices, possibly of length zero,
	 *          as an array of coordinates specifiying x, y, and
	 *          owning player value.
	 */
	protected ArrayList<Coordinate> takeMove(Coordinate xy) {
	    /*
		if (!isValidMove(xy)) {
			throw new IllegalStateException(
				"Given move is invalid but should be valid");
		}
		*/
		// In each direction, capture the opposing player's peices if it is
		// bound by two of this player's peices.
		
		// A list of changed/captured peices to return
		ArrayList<Coordinate> capture = new ArrayList<Coordinate>();
		
		Grid grid = getGrid();
		
		// "Capture" the current peice
		capture.add(xy);
		
		// Capture peices in each direction
		capture.addAll(take(xy, -1,  0)); // left
		capture.addAll(take(xy, +1,  0)); // right
		capture.addAll(take(xy,  0, -1)); // up
		capture.addAll(take(xy,  0, +1)); // down
		capture.addAll(take(xy, -1, -1)); // top-left
		capture.addAll(take(xy, +1, -1)); // top-right
		capture.addAll(take(xy, -1, +1)); // bottom-left
		capture.addAll(take(xy, +1, +1)); // bottom-right
		return capture;
	}
	
	/**
	 * Calculates the potential score of a move, for use by an AI player who
	 * wants to pick the best move.
	 * @param player A valid player object used by the current game.
	 * @param xy A coordinate specifying x and y values into the grid.
	 * @return	The score of the move.
	 */
	public int moveScore(Coordinate xy) {
		// Hint to whoever implements the Hard AI - just iterate over the
		// grid, then pick the best moveScore result. Your AI doesn't need to
		// know how to play Othello, it just needs to have a strategy that uses
		// Game/Othello.validMove and Game/Othello.moveScore. Simple!
		
		if (!isValidMove(xy)) { return 0; }
		
		ArrayList <Coordinate> captured = takeMove(xy);
		return captured.size();
	}
	
	public void moveMade(Coordinate move) {
		boolean m_Trace = false;
		
		if(m_Trace) System.out.println("Game::MoveMade() - Called");
		if(validateMove(move)) {
			if(m_Trace) System.out.println("Game::MoveMade() - Move is valid");
			ArrayList<Coordinate> changes = takeMove(move);
			for(int i = 0; i < changes.size(); i++) {
				getGrid().setCoordinate(changes.get(i));
			}	
			getWindow().displayGrid(getGrid());
			//**********************
				System.out.println("othello----");
				changes.remove(0);
				getWindow().SetAnimation("flip", changes);
			//**********************
			setPlayer1Score(0);
			setPlayer2Score(0);
			for (int i = 0; i < getGrid().getGridWidth(); i++) {
				for (int j = 0; j< getGrid().getGridHeight(); j++) {
					if (getGrid().getCoordinate(i, j).getValue() == PlayerTurn.PLAYER1) {
						setPlayer1Score(getPlayer1Score() + 1);
					} else if (getGrid().getCoordinate(i, j).getValue() == PlayerTurn.PLAYER2) {
						setPlayer2Score(getPlayer2Score() + 1);
					}
				}
			}
			
			getWindow().updateScore(getPlayer1Score(), getPlayer2Score());
			setPlayerTurn(nextPlayer());
			 getWindow().displayPlayerTurn(getPlayerTurn());
			 availableMove();
			 if(checkPassTurn() && isAnyValidMove())
			 {
				 super.Getpassturnmessage();
				 availableMove();
				 getWindow().displayPlayerTurn(getPlayerTurn());
			 }
			setTurnCount(getTurnCount() + 1);
		}
		
		if(isOver()) {
			if(m_Trace) System.out.println("Game::MoveMade() - Game is finished");
			new EndNewGame(this);
			emptyWin();
		} else {
			if (getPlayerTurn() == PlayerTurn.PLAYER1) {
				if(m_Trace) System.out.println("Game::MoveMade() - Player1 next");
				getPlayer1().isYourMove();
			} else if(getPlayerTurn() == PlayerTurn.PLAYER2){
				if(m_Trace) System.out.println("Game::MoveMade() - Player2 next");
				getPlayer2().isYourMove();
			}
		}
		System.out.println("Grid:\n" + getGrid().toString() + "\n");
	}
	
	
	
	/**
	 * (PRIVATE) Captures peices in a direction.
	 * @param player A valid player object used by the current game.
	 * @param xy A coordinate specifying x and y values into the grid.
	 * @param xdir Horizontal direction value in which to capture.
	 * @param ydir Vertical direction value in which to capture.
	 * @return	A list of captured peices, possibly of length zero,
	 *          as an array of coordinates specifiying x, y, and
	 *          owning player value.
	 */
	private ArrayList<Coordinate> take(Coordinate xy, int xdir, int ydir) {
	
		if (!checkBound(xy, xdir, ydir)) { return new ArrayList<Coordinate>(); }
		
		Grid grid = getGrid();
		Coordinate current = new Coordinate(xy);
		
		ArrayList <Coordinate> capture = new ArrayList<Coordinate>();
		
		current.move(xdir, ydir);
		
		// The move is assumed to be valid at this point, so move over the
		// grid capturing any peices belonging to the opponent
		while (current.within(0, 0, GAME_WIDTH, GAME_HEIGHT)) {
			Coordinate check = grid.getCoordinate(current);
			
			if (check.isEmpty()) { break; }
			
			if (check.notEquals(current.getValue())) {
				// If the peice belongs to the opponent, then capture it
				check.setValue(current.getValue());
				capture.add(check);
			}
			else {
				// If the peice belonds to the current player, then finish.
				break;
			}
			
    		current.move(xdir, ydir);
		}
		return capture;
	}
	
	/**
	 * Checks to see if the game is over.
	 * @return	True if the game is over, false otherwise.
	 */
	public boolean isOver() {
		// this concretely implements from the abstract game class
		if(!isAnyValidMove()){
			for (int i = 0; i < getGrid().getGridWidth(); i++) {
				for (int j = 0; j< getGrid().getGridHeight(); j++) {
					if (getGrid().getCoordinate(i, j).getValue() == PlayerTurn.PLAYER1) {
						m_p1.add(new Coordinate(i, j));
					} else if (getGrid().getCoordinate(i, j).getValue() == PlayerTurn.PLAYER2) {
						m_p2.add(new Coordinate(i, j));
					}
				}
			}
			if(m_p1.size() > m_p2.size()){
				m_win = new HashSet<Coordinate>(m_p1);
			} else if(m_p2.size() > m_p1.size()){
				m_win = new HashSet<Coordinate>(m_p2);
			}
	        getTimer().setRunning();
			m_p1.clear();
			m_p2.clear();
		}
		return (!isAnyValidMove());
	}
	
	/**
	 * Returns the winner (once the game has ended). The winner is the one with
	 * the most captured peices.
	 * @return The winning player, or null in the case of a tie.
	 */
	public Game.PlayerTurn isWinner() {
		if (!isOver())
			{ throw new IllegalStateException("no winner before game is over"); }
	
		Grid grid = getGrid();
		int score1 = getPlayer1Score();
		int score2 = getPlayer2Score();
		
		/*
		int score1 = 0; int score2 = 0;
		
		for (int y = 0; y < GAME_HEIGHT; y++) {
			for (int x = 0; x < GAME_WIDTH; x++) {
				Coordinate c = grid.getCoordinate(x, y);
				
				if (c.equals(Game.PlayerTurn.PLAYER1)) {
					score1++;
				}
				else if (c.equals(Game.PlayerTurn.PLAYER2)) {
    				score2++;
				}
			}
		}*/
		
		if (score1 > score2) { return Game.PlayerTurn.PLAYER1; }
		else if (score2 > score1) { return Game.PlayerTurn.PLAYER2; }
		else { return Game.PlayerTurn.NONE; } // "Tie"
	}

	protected PlayerTurn nextPlayer() {
		if(getPlayerTurn() == Game.PlayerTurn.PLAYER1) {
			return Game.PlayerTurn.PLAYER2;
		} else {
			return Game.PlayerTurn.PLAYER1;
		}
	}
	
	
	/**
	 * Prints a debug message.
	 * @param method The name of the method printing the message.
	 * @param args   A string representation arguments of the method printing
	 * 				 the message.
	 * @param msg    A message to print.
	 */
	private void debug(String method, String args, String msg) {
		if (!m_Trace) { return; }
		
		System.out.println("Othello::"+method+"("+args+") - "+msg);
	}
	
	/**
	 * Prints a debug message.
	 * @param method The name of the method printing the message.
	 * @param msg    A message to print.
	 */
	private void debug(String method, String msg) {
		debug(method, "", msg);
	}
	
	private Set<Coordinate> m_win = new HashSet<Coordinate>();
	private Set<Coordinate> m_p1 = new HashSet<Coordinate>();
	private Set<Coordinate> m_p2 = new HashSet<Coordinate>();
	private boolean[][] m_availableMov; 
	
	private boolean m_Trace = true;
}
