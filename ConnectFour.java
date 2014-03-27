import java.util.*;

/**
 * \file -ConnectFour.java
 * \author -G. Howard
 * \date -14/02/2014
 * 
 * \brief Class that extends Game by using the rules of ConnectFour. 
 * 
 * 		Specifies the size of the board to be used. Sets the ways of winning ConnectFour
 *      and what happens once happens once the game has ended. Doesn't allow
 *      the user to click outside the grid.
 */

public class ConnectFour extends Game {

	/**
	 * Sets the winner of the game.
	 * 
	 * \param player
	 *            The winner of the game as an enumerator Game.PlayerTurn.
	 */
	public void setWinner(Game.PlayerTurn player) {
		m_Winner = player;
	}
	
	/**
	 * Return the winning piece coordinates
	 * 
	 * \return the set which stores the winning piece coordinates
	 */
	public Set<Coordinate> getWin() {// should be GetWin()
		return m_win;
	}
	
	/**
	 * Returns the winner of the game.
	 * 
	 * \return The winner of the game as a enumerator Game.PlayerTurn.
	 */
	public Game.PlayerTurn getWinner() {
		return m_Winner;
	}
	
	/**
	 * Empty the set which stores the winning piece coordinates
	 */
	protected void emptyWin() {
		m_win.clear();
	}

	public ConnectFour() {
		super(GAME_WIDTH, GAME_HEIGHT);
	}

	public void start() throws InterruptedException {
		boolean m_Trace = false;

		if (m_Trace) {
			System.out.println("Game::Start() - Game has started");
		}
		setWindow(new GameWindow(this));
		getPlayer1().isYourMove();
		getWindow().displayPlayerTurn(Game.PlayerTurn.PLAYER1);
		if 	((getPlayer1() instanceof ConnectFourAI||
				getPlayer1() instanceof AIEasy) && (getPlayer2() instanceof Human)){
			System.out.println("com first ");
			getPlayer1().sendMove();
		}
		startTimer();
	}

	/**
	 * Resets the starting pieces.
	 * \throws InterruptedException 
	 */
	public void resetGame() throws InterruptedException {
		setWinner(Game.PlayerTurn.NONE);
		setPlayerTurn(PlayerTurn.PLAYER1);
		getPlayer1().isYourMove();
		getWindow().displayPlayerTurn(Game.PlayerTurn.PLAYER1);
		if 	((getPlayer1() instanceof ConnectFourAI||
				getPlayer1() instanceof AIEasy) && (getPlayer2() instanceof Human)){
			getPlayer1().sendMove();
		}
	}

	/**
	 * If the board is full there are no more valid moves
	 * 
	 * \return true if there is a valid move
	 */
	private boolean validMove() {
		boolean m_Trace = true;

		if (getTurnCount() == GAME_WIDTH * GAME_HEIGHT) {
			if (m_Trace)
				System.out
					.println("ConnectFour::ValidMove() - No more valid moves");
			return false;
		} else {
			return true;
		}
	}

	/**
	 * ConnectFour does not allow a move if the mouse is clicked outside the
	 * grid
	 * 
	 * \return true if there is a move available
	 */
	protected boolean isValidMove(Coordinate xy) {
		boolean m_Trace = false;

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

		if (grid.getCoordinate(xy.getX(), 0).getValue() == Game.PlayerTurn.NONE) {
			if (m_Trace)
				System.out
					.println("ConnectFour::isValidMove() - move is valid");
			return true;
		} else {
			if (m_Trace)
				System.out
					.println("ConnectFour::isValidMove() - move is not valid");
			return false;
		}
	}

	public void moveMade(Coordinate move) throws InterruptedException {
		boolean m_Trace = false;

		if (m_Trace)
			System.out.println("Game::MoveMade() - Called");
		if (validateMove(move)) {
			if (m_Trace)
				System.out.println("Game::MoveMade() - Move is valid");
			ArrayList<Coordinate> changes = takeMove(move);
			for (int i = 0; i < changes.size(); i++) {
				getGrid().setCoordinate(changes.get(i));
			}
			getWindow().displayGrid(getGrid());
			// **********************
			System.out.println("con4----");
			getWindow().SetAnimation("fall", changes);

			// **********************
			setPlayer1Score(0);
			setPlayer2Score(0);
			for (int i = 0; i < getGrid().getGridWidth(); i++) {
				for (int j = 0; j < getGrid().getGridHeight(); j++) {
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
			setTurnCount(getTurnCount() + 1);
		}

		if (isOver()) {
			if (m_Trace)
				System.out.println("Game::MoveMade() - Game is finished");
			new EndDisplay(this);
			emptyWin();
		} else {
			if (getPlayerTurn() == PlayerTurn.PLAYER1) {
				if (m_Trace)
					System.out.println("Game::MoveMade() - Player1 next");
				getPlayer1().isYourMove();
			} else if (getPlayerTurn() == PlayerTurn.PLAYER2) {
				if (m_Trace)
					System.out.println("Game::MoveMade() - Player2 next");
				getPlayer2().isYourMove();
			}
		}
		// System.out.println("Grid:\n" + getGrid().toString() + "\n");
	}

	/**
	 * Checks if there is a valid move in a column. If there is, the counter is
	 * placed in the lowest available place e.g. on the bottom or on top of
	 * another counter
	 * 
	 * \return a list containing the taken move
	 */

	protected ArrayList<Coordinate> takeMove(Coordinate xy) {
		Grid grid = getGrid();
		ArrayList<Coordinate> result = new ArrayList<Coordinate>();

		for (int y = GAME_HEIGHT - 1; y >= 0; y--) {
			if (grid.getCoordinate(xy.getX(), y).getValue() == Game.PlayerTurn.NONE) {
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
	 * \return true if the game is over
	 */
	public boolean isOver() {
		boolean m_Trace = true;

		checkWinner();
		Iterator<Coordinate> iterator = m_win.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		if (getWinner() == Game.PlayerTurn.PLAYER1
				|| getWinner() == Game.PlayerTurn.PLAYER2) {
			if (m_Trace)
				System.out.println("ConnectFour::IsOver() - A Player has won");
			getTimer().setRunning();
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

				checkRight(x, y);
				checkDown(x, y);
				checkDiagonalUp(x, y);
				checkDiagonalDown(x, y);
			}

		}
	}

	/**
	 * Checks right from the last counter that has been placed to check if there
	 * are 4 counter of the same colour in a row
	 * 
	 * \param x
	 *            the x value to check
	 * \param y
	 *            the y value to check
	 */
	private void checkRight(int x, int y) {
		Grid grid = getGrid();

		// TODO checks the none playerturn. which means a blank grid will return
		// winner.
		Game.PlayerTurn Player = grid.getCoordinate(x, y).getValue();
		if (Player != Game.PlayerTurn.NONE) {
			if (x < GAME_WIDTH - COUNT3) {
				if ((grid.getCoordinate(x + 1, y).getValue() == Player)
					&& (grid.getCoordinate(x + COUNT2, y).getValue() == Player)
					&& (grid.getCoordinate(x + COUNT3, y).getValue() == Player)) {
					m_win.add(new Coordinate(x, y));
					m_win.add(new Coordinate(x + 1, y));
					m_win.add(new Coordinate(x + COUNT2, y));
					m_win.add(new Coordinate(x + COUNT3, y));
					setWinner(Player);
				}
			}
		}
	}

	private int countRight(Coordinate xy, Game.PlayerTurn Player) {
		int x = xy.getX();
		int y = xy.getY();
		int count = 0;
		int loopCount = 0;
		Grid grid = getGrid();
		if (Player != Game.PlayerTurn.NONE) {
			if (x < GAME_WIDTH - 1) {
				loopCount = 1;
			}
			if (x < GAME_WIDTH - COUNT2) {
				loopCount = COUNT2;
			}
			if (x < GAME_WIDTH - COUNT3) {
				loopCount = COUNT3;
			}
			for (int z = 1; z <= loopCount; z++) {
				Game.PlayerTurn Player2 = grid.getCoordinate((x + z), y)
					.getValue();
				if (Player2.equals(Player)) {
					count++;
				} else {
					return count;
				}
			}
		}
		return count;
	}

	private int countLeft(Coordinate xy, Game.PlayerTurn Player) {
		int x = xy.getX();
		int y = xy.getY();
		int count = 0;
		int loopCount = 0;
		Grid grid = getGrid();
		if (Player != Game.PlayerTurn.NONE) {
			if (x > 0) {
				loopCount = 1;
			}
			if (x > 1) {
				loopCount = COUNT2;
			}
			if (x > COUNT2) {
				loopCount = COUNT3;
			}
			for (int z = 1; z <= loopCount; z++) {
				Game.PlayerTurn Player2 = grid.getCoordinate((x - z), y)
					.getValue();
				if (Player2.equals(Player)) {
					count++;
				} else {
					return count;
				}
			}
		}
		return count;
	}

	/**
	 * Checks downwards from the last counter that has been placed to see if
	 * there are 4 counter of the same colour in a row
	 * 
	 * \param x
	 *            the x value to check
	 * \param y
	 *            the y value to check
	 */
	private void checkDown(int x, int y) {
		Grid grid = getGrid();

		Game.PlayerTurn Player = grid.getCoordinate(x, y).getValue();
		if (Player != Game.PlayerTurn.NONE) {
			if (y < GAME_HEIGHT - COUNT3) {
				if ((grid.getCoordinate(x, y + 1).getValue() == Player)
					&& (grid.getCoordinate(x, y + COUNT2).getValue() == Player)
					&& (grid.getCoordinate(x, y + COUNT3).getValue() == Player)) {
					m_win.add(new Coordinate(x, y));
					m_win.add(new Coordinate(x, y + 1));
					m_win.add(new Coordinate(x, y + COUNT2));
					m_win.add(new Coordinate(x, y + COUNT3));
					setWinner(Player);
				}
			}
		}
	}

	private int countDown(Coordinate xy, Game.PlayerTurn Player) {
		int x = xy.getX();
		int y = xy.getY();
		int count = 0;
		int loopCount = 0;
		Grid grid = getGrid();
		if (Player != Game.PlayerTurn.NONE) {
			if (y < GAME_HEIGHT - 1) {
				loopCount = 1;
			}
			if (y < GAME_HEIGHT - COUNT2) {
				loopCount = COUNT2;
			}
			if (y < GAME_HEIGHT - COUNT3) {
				loopCount = COUNT3;
			}
			for (int z = 1; z <= loopCount; z++) {
				Game.PlayerTurn Player2 = grid.getCoordinate(x, (y + z))
					.getValue();
				if (Player2.equals(Player)) {
					count++;
				} else {
					return count;
				}
			}
		}
		return count;
	}

	public int moveScore(Coordinate xy) {
		int count = 0;
		int countDown = countDown(xy, getPlayerTurn());
		int countDiagDown = countDiagonalDown(xy, getPlayerTurn());
		int countDiagUp = countDiagonalUp(xy, getPlayerTurn());
		int countLeft = countLeft(xy, getPlayerTurn());
		int countRight = countRight(xy, getPlayerTurn());

		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(countDown);
		list.add(countDiagDown);
		list.add(countDiagUp);
		list.add(countLeft);
		list.add(countRight);
		
		int equalsCounter = 0;
		for (Integer listIter : list) {
			if (listIter == count) {
				equalsCounter++;
			} else if (listIter > count) {
				count = listIter;
			}
		}

		if (equalsCounter == list.size()) {
			count = 0;
		}

		return count;

	}
	
	public int blockOpponentChecker(Coordinate xy){
		Game.PlayerTurn Player;
		
		if(getPlayerTurn() == Game.PlayerTurn.PLAYER1){
			Player = Game.PlayerTurn.PLAYER2;
		}else{ Player = Game.PlayerTurn.PLAYER1;}
		

				Coordinate c1 = new Coordinate(xy.getX(),xy.getY());
				int count = 0;
				int countDown = countDown(c1,Player);
				int countDiagDown = countDiagonalDown(c1,Player);
				int countDiagUp = countDiagonalUp(c1,Player);
				int countLeft = countLeft(c1,Player);
				int countRight = countRight(c1,Player);

				ArrayList<Integer> list = new ArrayList<Integer>();
				list.add(countDown);
				list.add(countDiagDown);
				list.add(countDiagUp);
				list.add(countLeft);
				list.add(countRight);
				
				for (Integer listIter : list) {
					if (listIter == COUNT3){
					return COUNT3;
					}
				}
				return count;
	}
	

	private int countDiagonalDown(Coordinate xy, Game.PlayerTurn Player) {
		int x = xy.getX();
		int y = xy.getY();
		int count = 0;
		int loopCount = 0;
		Grid grid = getGrid();
		if (Player != Game.PlayerTurn.NONE) {
			if (y < GAME_HEIGHT - 1 && x < GAME_WIDTH - 1) {
				loopCount = 1;
			}
			if (y < GAME_HEIGHT - COUNT2 && x < GAME_WIDTH - COUNT2) {
				loopCount = COUNT2;
			}
			if (y < GAME_HEIGHT - COUNT3 && x < GAME_WIDTH - COUNT3) {
				loopCount = COUNT3;
			}
			for (int z = 1; z <= loopCount; z++) {
				Game.PlayerTurn Player2 = grid.getCoordinate((x + z), (y + z))
					.getValue();
				if (Player2.equals(Player)) {
					count++;
				} else {
					return count;
				}
			}
		}
		return count;
	}

	/**
	 * Checks downwards in a diagonal direction from the last counter that has
	 * been placed to check if there are 4 counter of the same colour in a row
	 * 
	 * \param x
	 *            the x value to check
	 * \param y
	 *            the y value to check
	 */
	private void checkDiagonalDown(int x, int y) {
		Grid grid = getGrid();

		Game.PlayerTurn Player = grid.getCoordinate(x, y).getValue();
		if (Player != Game.PlayerTurn.NONE) {
			if (y < GAME_HEIGHT - COUNT3 && x < GAME_WIDTH - COUNT3) {
				if ((grid.getCoordinate(x + 1, y + 1).getValue() == Player)
					&& (grid.getCoordinate(x + COUNT2, y + COUNT2).getValue() == Player)
					&& (grid.getCoordinate(x + COUNT3, y + COUNT3).getValue() == Player)) {
					m_win.add(new Coordinate(x, y));
					m_win.add(new Coordinate(x + 1, y + 1));
					m_win.add(new Coordinate(x + COUNT2, y + COUNT2));
					m_win.add(new Coordinate(x + COUNT3, y + COUNT3));
					setWinner(Player);
				}
			}
		}
	}

	/**
	 * Checks upwards in a diagonal direction from the last counter that has
	 * been placed to check if there are 4 counters of the same colour in a row
	 * 
	 * \param x
	 *            the x value to check
	 * \param y
	 *            the y value to check
	 */
	private void checkDiagonalUp(int x, int y) {
		Grid grid = getGrid();

		Game.PlayerTurn Player = grid.getCoordinate(x, y).getValue();
		if (Player != Game.PlayerTurn.NONE) {
			if (y > COUNT2 && x < GAME_WIDTH - COUNT3) {
				if ((grid.getCoordinate(x + 1, y - 1).getValue() == Player)
					&& (grid.getCoordinate(x + COUNT2, y - COUNT2).getValue() == Player)
					&& (grid.getCoordinate(x + COUNT3, y - COUNT3).getValue() == Player)) {
					m_win.add(new Coordinate(x, y));
					m_win.add(new Coordinate(x + 1, y - 1));
					m_win.add(new Coordinate(x + COUNT2, y - COUNT2));
					m_win.add(new Coordinate(x + COUNT3, y - COUNT3));
					setWinner(Player);
				}
			}
		}
	}

	private int countDiagonalUp(Coordinate xy, Game.PlayerTurn Player) {
		int x = xy.getX();
		int y = xy.getY();
		int count = 0;
		int loopCount = 0;
		Grid grid = getGrid();
		if (Player != Game.PlayerTurn.NONE) {
			if (y > 1 && x < GAME_WIDTH - 1) {
				loopCount = 1;
			}
			if (y > COUNT2 && x < GAME_WIDTH - COUNT2) {
				loopCount = COUNT2;
			}
			if (y > COUNT3 && x < GAME_WIDTH - COUNT3) {
				loopCount = COUNT3;
			}
			for (int z = 1; z <= loopCount; z++) {
				Game.PlayerTurn Player2 = grid.getCoordinate((x + z), (y - z))
					.getValue();
				if (Player2.equals(Player)) {
					count++;
				} else {
					return count;
				}
			}
		}
		return count;
	}

	@Override
	protected PlayerTurn nextPlayer() {
		if (getPlayerTurn() == Game.PlayerTurn.PLAYER1) {
			return Game.PlayerTurn.PLAYER2;
		} else {
			return Game.PlayerTurn.PLAYER1;
		}
	}

	@Override
	public PlayerTurn isWinner() {
		return getWinner();
	}
	
	private final static int GAME_WIDTH = 10;
	private final static int GAME_HEIGHT = 7;
	private Game.PlayerTurn m_Winner;
	private Set<Coordinate> m_win = new HashSet<Coordinate>();
	private final static int COUNT2 = 2;
	private final static int COUNT3 = 3;
	
}
