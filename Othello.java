import java.util.ArrayList;

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
	
	/**
	 * Construtor for a game of Othello
	 */
	public Othello() {
		// If the game class can initialse a grid of this size, that would be
		// great :-)
		super(GAME_WIDTH, GAME_HEIGHT);
		resetGame();
	}
	
	/**
	 * Resets the starting pieces.
	 */
	public void resetGame() {
		Grid grid = super.getGrid();
		
		// Initialise starting peices
		grid.setCoordinate(new Coordinate(3, 3, Game.PlayerTurn.PLAYER1));
		grid.setCoordinate(new Coordinate(4, 3, Game.PlayerTurn.PLAYER2));
		grid.setCoordinate(new Coordinate(3, 4, Game.PlayerTurn.PLAYER2));
		grid.setCoordinate(new Coordinate(4, 4, Game.PlayerTurn.PLAYER1));
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
		if (getGrid().getCoordinate(xy.getX(), xy.getY()).getValue() !=
			Game.PlayerTurn.NONE) { return false; }
		
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
		capture.add(new Coordinate(xy.getX(), xy.getY(), getPlayerTurn()));
		
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
	public int moveScore(Player player, Coordinate xy) {
		// Hint to whoever implements the Hard AI - just iterate over the
		// grid, then pick the best moveScore result. Your AI doesn't need to
		// know how to play Othello, it just needs to have a strategy that uses
		// Game/Othello.validMove and Game/Othello.moveScore. Simple!
		
		if (!isValidMove(xy)) { return 0; }
		
		ArrayList <Coordinate> captured = takeMove(xy);
		return captured.size();
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
	
	private boolean m_Trace = true;
}
