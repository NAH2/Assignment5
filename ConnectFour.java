import java.util.ArrayList;

/**
 *	 @file   -ConnectFour.java
 *	 @author -G. Howard
 *	 @date   -14/02/2014
 * 
 *	 @brief	 Class that extends Game by using the rules of ConnectFour.
 *			 Specifies the size of the board to be used. 
 *           Sets the ways of winning ConnectFour
 *			 and what happens once happens once the game has ended. 
 *           Doesn't allow the user to click outside the grid.
 */


public class ConnectFour extends Game {

	private final static int GAME_WIDTH = 10;
	private final static int GAME_HEIGHT = 7;
	private Game.PlayerTurn m_Winner ;	

	/**
	 * Returns the winner of the game.
	 * @return The winner of the game as a enumerator Game.PlayerTurn.
	 */
	public Game.PlayerTurn getWinner() {
		return m_Winner;
	}
	
	/**
	 * Sets the winner of the game.
	 * @param player The winner of the game as an enumerator Game.PlayerTurn.
	 */
	public void setWinner(Game.PlayerTurn player) {
		m_Winner = player;
	}
	
	public ConnectFour() {
		super(GAME_WIDTH, GAME_HEIGHT);
	}
	
	/**
	 * Resets the starting pieces.
	 */
	public void resetGame() {
		setWinner(Game.PlayerTurn.NONE);
		
	}
	
	/**
	 * If the board is full there are no more valid moves
	 * 
	 * @return true if there is a valid move
	 */
	private boolean validMove() {
		boolean m_Trace = true;
		
		if (getTurnCount() == GAME_WIDTH * GAME_HEIGHT) {
			if(m_Trace) System.out.println
				("ConnectFour::ValidMove() - No more valid moves");
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 *   ConnectFour does not allow a move if 
	 *   the mouse is clicked outside the grid
	 *   
	 *   @return true if there is a move available
	 */
	protected boolean isValidMove(Coordinate xy) {
		boolean m_Trace = true;
	
		Grid grid = getGrid();
		
		if (xy.getX() < 0) { 
			throw new IllegalArgumentException("bad x value");
		}
		if (xy.getX() < 0) { 
			throw new IllegalArgumentException("bad y value"); 
		}
		if (xy.getX() >= GAME_WIDTH) { 
			throw new IllegalArgumentException("bad x value"); 
		}
		if (xy.getY() >= GAME_HEIGHT) { 
			throw new IllegalArgumentException("bad y value"); 
		}
		
		if (grid.getCoordinate(xy.getX(), 0).getValue()
				== Game.PlayerTurn.NONE) {
			if(m_Trace) System.out.println
				("ConnectFour::isValidMove() - move is valid");
				return true;
		} else {
			if(m_Trace) System.out.println
				("ConnectFour::isValidMove() - move is not valid");
				return false; 
		}
	}
	
	/**
	 *  Checks if there is a valid move in a column.
	 *  If there is, the counter is placed in the lowest available place
	 *  e.g. on the bottom or on top of another counter
	 *  
	 *  @return a list containing the taken move
	 */

	protected ArrayList<Coordinate> takeMove(Coordinate xy) {
		Grid grid = getGrid();
		ArrayList<Coordinate> result = new ArrayList<Coordinate>();
		
		for (int y = GAME_HEIGHT - 1; y >= 0; y--) {
			if(grid.getCoordinate(xy.getX(), y).getValue() 
				== Game.PlayerTurn.NONE) {
					result = new ArrayList<Coordinate>();
					result.add(new Coordinate(xy.getX(), y, xy.getValue()));
					break;
			}
		}
		return result;
	}
	
	/**
	 * Sets the ways in which a game can be ended
	 * 
	 * @return true if the game is over
	 */
	public boolean isOver() {
		boolean m_Trace = true;
		
		checkWinner();
		if(getWinner() == Game.PlayerTurn.PLAYER1 || 
				getWinner() == Game.PlayerTurn.PLAYER2) {
			if(m_Trace) System.out.println
				("ConnectFour::IsOver() - A Player has won");
				return true;
		} else {
			return (!validMove());
		}
	}
	
	/**
	 * Sets how the game will check who the winner is
	 */
	private void checkWinner() {
		for (int y = 0; y < GAME_HEIGHT; y++) {
			for (int x = 0; x < GAME_WIDTH; x++) {
			
			checkRight(x,y);
			checkDown(x,y);
			checkDiagonalUp(x,y);
			checkDiagonalDown(x,y);
			}
			
		}
	}
	
	/**
	 * Checks right from the last counter that has been placed to check if
	 * there are 4 counter of the same colour in a row
	 * 
	 * @param x the x value to check
	 * @param y the y value to check
	 */
	private void checkRight(int x, int y) {
		Grid grid = getGrid();
		
		//TODO checks the none playerturn. which means a blank grid will return winner.
		Game.PlayerTurn Player = grid.getCoordinate(x, y).getValue();
		if(Player != Game.PlayerTurn.NONE) {
			if (x < GAME_WIDTH - 3) {
				if ((grid.getCoordinate(x + 1, y).getValue() == Player) 
				&& (grid.getCoordinate(x + 2, y).getValue() == Player)
				&& (grid.getCoordinate(x + 3, y).getValue() == Player)) {
					setWinner(Player);
				}
			}
		}
	}
	
	/**
	 * Checks downwards from the last counter that has been placed to see
	 * if there are 4 counter of the same colour in a row
	 *
	 * @param x the x value to check
	 * @param y the y value to check
	 */
	private void checkDown(int x, int y) {
		Grid grid = getGrid();
		
		Game.PlayerTurn Player = grid.getCoordinate(x, y).getValue();
		if(Player != Game.PlayerTurn.NONE) {
			if (y < GAME_HEIGHT - 3) {
				if ((grid.getCoordinate(x, y + 1).getValue() == Player)
				&& (grid.getCoordinate(x, y + 2).getValue() == Player)
				&& (grid.getCoordinate(x, y + 3).getValue() == Player)) {
					setWinner(Player);
				}
			}
		}
	}
	
	/**
	 * Checks downwards in a diagonal direction from the last counter that
	 * has been placed to check if there are 4 counter of the same colour
	 * in a row
	 * 
	 * @param x the x value to check
	 * @param y the y value to check
	 */
	private void checkDiagonalDown(int x, int y) {
		Grid grid = getGrid();
		
		Game.PlayerTurn Player = grid.getCoordinate(x, y).getValue();
		if(Player != Game.PlayerTurn.NONE) {
			if (y < GAME_HEIGHT - 3 && x < GAME_WIDTH - 3) {
				if ((grid.getCoordinate(x+1, y+1).getValue() == Player)
				&& (grid.getCoordinate(x+2, y+2).getValue() == Player)
				&& (grid.getCoordinate(x+3, y+3).getValue() == Player)) {
					setWinner(Player);
				}
			}
		}
	}
	
	/**
	 * Checks upwards in a diagonal direction from the last counter that 
	 * has been placed to check if there are 4 counters of the 
	 * same colour in a row
	 * 
	 * @param x the x value to check
	 * @param y the y value to check
	 */
	private void checkDiagonalUp(int x, int y) {
		Grid grid = getGrid();
		
		Game.PlayerTurn Player = grid.getCoordinate(x, y).getValue();
		if(Player != Game.PlayerTurn.NONE) {
			if (y > 2 && x < GAME_WIDTH - 3) {
				if ((grid.getCoordinate(x+1, y-1).getValue() == Player)
				&& (grid.getCoordinate(x+2, y-2).getValue() == Player)
				&& (grid.getCoordinate(x+3, y-3).getValue() == Player)) {
					setWinner(Player);
				}
			}
		}
	}



	@Override
	protected PlayerTurn nextPlayer() {
		if(getPlayerTurn() == Game.PlayerTurn.PLAYER1) {
			return Game.PlayerTurn.PLAYER2;
		} else {
			return Game.PlayerTurn.PLAYER1;
		}
	}

	@Override
	public PlayerTurn isWinner() {
		return getWinner();
	}
}
		


	
	
