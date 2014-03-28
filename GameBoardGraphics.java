import java.awt.BasicStroke;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ConcurrentModificationException;
import java.lang.NullPointerException;

/**
 * \\file GameBoardGraphics.java 
 * \author Daniel 709547, Chak Yan Lam
 * \date 25/02/2014 
 * 
 * \brief
 * Class which deals with the graphics of the game board
 * 
 * This class deals with all the graphics of the actual game board itself, being
 * called whenever the gameboard is updated or needs to be repainted.
 */
public class GameBoardGraphics extends JComponent implements
		MouseMotionListener {

	/**
	 * Methods to set whether the thread should be be running
	 * 
	 * \param run - the boolean false to stop the thread execution 
	 * \return TRUE if successful.
	 */
	public boolean SetRun(boolean run) {
		boolean test = false;
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: SetRun() BEGIN");
		}
		m_running = run;
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: SetRun() END");
		}
		return true;
	}

	/**
	 * Store the coordinate to be displayed when the move is not valid
	 * 
	 * \param valid - the boolean whether the move is valid or not 
	 * \param xmove - the Coordinate of invalid move 
	 * \return TRUE if successful.
	 */
	public boolean SetValid(boolean valid, Coordinate xmove) {
		boolean test = false;
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: SetValid() BEGIN");
		}
		m_valid = valid;
		m_xmove = xmove;
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: SetValid() END");
		}
		return true;
	}

	/**
	 * Set the boolean to true when the move is valid
	 * 
	 * \param valid - the boolean whether the move is valid or not 
	 * \return TRUE if successful.
	 */
	public boolean SetValid(boolean valid) {
		boolean test = false;
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: SetValid(boolean) BEGIN");
		}
		m_valid = valid;
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: SetValid(boolean) END");
		}
		return true;
	}

	/**
	 * Method which returns if the piece is flipping at the moment 
	 * \return true if the width of the piece is not equal to the original 
	 * piece size, that means it is flipping
	 */
	public boolean GetFlip() {
		boolean test = false;
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: GetFlip() BEGIN");
		}
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: GetFlip() END");
		}
		return m_w != PIECE_SIZE;
	}

	/**
	 * Method responsible for setting AI move 
	 * \param move - coordinate of AI move
	 */
	public void SetAImove(Coordinate move) {
		boolean test = false;
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: SetAImove() BEGIN");
		}
		m_AImove = move;
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: SetAImove() END");
		}
	}

	/**
	 * Method to set the animation speed 
	 * \param speed - an integer represents millisecond time delay per movement
	 */
	public void SetSpeed(int speed) {
		boolean test = false;
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: SetSpeed() BEGIN");
		}
		m_speed = speed;
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: SetSpeed() END");
		}
	}

	/**
	 * Method to set the game board 
	 * \param board name
	 */
	public void SetBoard(String board) {
		boolean test = false;
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: SetBoard() BEGIN");
		}
		repaint();
		m_board = board;
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: SetBoard() END");
		}
	}

	/**
	 * Method to return the player1 object 
	 * \return m_player1
	 */
	public Player GetPlayer1() {
		boolean test = false;
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: GetPlayer1() BEGIN");
		}
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: GetPlayer1() END");
		}
		return m_player1;
	}
	
	/**
     * Method to set the player1 object 
     * \param p1 the player object
     * \return true on success
     */
    public boolean setPlayer1(Player p1) {
        boolean test = false;
        if (test || m_test) {
            System.out.println("GameBoardGraphics :: setPlayer1() BEGIN");
        }
        
        m_player1 = p1;
        
        if (test || m_test) {
            System.out.println("GameBoardGraphics :: setPlayer1() END");
        }
        return true;
    }

	/**
	 * Method to return the player2 object \return the player object
	 */
	public Player GetPlayer2() {
		boolean test = false;
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: GetPlayer2() BEGIN");
		}
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: GetPlayer2() END");
		}
		return m_player2;
	}
	
	/**
     * Method to set the player2 object 
     * \param p2 the player object
     * \return true on success
     */
    public boolean setPlayer2(Player p2) {
        boolean test = false;
        if (test || m_test) {
            System.out.println("GameBoardGraphics :: setPlayer2() BEGIN");
        }
        
        m_player2 = p2;
        
        if (test || m_test) {
            System.out.println("GameBoardGraphics :: setPlayer2() END");
        }
        return true;
    }

	/**
	 * returns the variable m_grid to the caller of the method. \return private
	 * member variable m_grid, a grid object.
	 */

	public Grid getGrid() {
		boolean test = false;
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: getGrid() BEGIN");
		}
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: getGrid() END");
		}
		return m_grid;
	}

	/**
	 * Setter method to set the value of a private member variable grid 
	 * \param grid - object storing data for the game grid
	 */
	public void setGrid(Grid grid) {
		boolean test = false;
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: setGrid() BEGIN");
		}
		m_grid = grid;

		repaint();
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: setGrid() END");
		}
	}

	/**
	 * Method to get the constant SQUARE_WIDTH 
	 * \return SQUARE_WIDTH
	 */

	public int getSquareWidth() {
		boolean test = false;
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: getSquareWidth() BEGIN");
		}
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: getSquareWidth() END");
		}
		return SQUARE_WIDTH;
	}

	/**
	 * Method to get the constant SQUARE_HEIGHT 
	 * \return SQUARE_HEIGHT
	 */

	public int getSquareHeight() {
		boolean test = false;
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: getSquareHeight() BEGIN");
		}
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: getSquareHeight() END");
		}
		return SQUARE_HEIGHT;
	}

	/**
	 * Method to get the constant Y_SQUARES 
	 * \return Y_SQUARES
	 */

	public int getYSquares() {
		boolean test = false;
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: getYSquares() BEGIN");
		}
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: getYSquares() END");
		}
		return Y_SQUARES;
	}

	/**
	 * Method to get the constant X_SQUARES 
	 * \return X_SQUARES
	 */

	public int getXSquares() {
		boolean test = false;
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: getXSquares() BEGIN");
		}
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: getXSquares() END");
		}
		return X_SQUARES;
	}

	/**
	 * Method to set the game is not over after the game restarts 
	 * \param isOver - the boolean true means the game is over
	 */
	public void SetOver(boolean isOver) {
		boolean test = false;
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: SetOver() BEGIN");
		}
		m_isOver = isOver;
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: SetOver() END");
		}
	}

	/**
	 * Method to get the winning piece coordinates for showing the winning
	 * pieces graphically, signal the game is over 
	 * \param win - a set of winning piece coordinates without repetition 
	 * \param over - a boolean, true when the game is over
	 */
	public void setIsOver(boolean over, Set<Coordinate> win) {
		boolean test = false;
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: setIsOver() BEGIN");
		}
		m_isOver = over;
		m_win = new HashSet<Coordinate>(win);
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: setIsOver() END");
		}
	}

	/**
	 * Method for generating animation of pieces in both game 
	 * \param type - type of animation that is either flip or fall 
	 * \param changes - the list stores the pieces which need the animation
	 */
	public void SetAnimation(String type, ArrayList<Coordinate> changes) {
		boolean test = false;
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: SetAnimation() BEGIN");
		}
		if (PLAYER1_COLOUR.equals(Color.BLACK)
				|| PLAYER1_COLOUR.equals(Color.WHITE)) {
			changes.remove(0);
		}
		m_changes = changes;
		m_type = type;
		m_start = true;
		m_lowestY = m_changes.get(0).getY() * getSquareHeight();
		if (m_type.equals("fall")) {
			new Thread(new Runnable() {
				public void run() {
					if (m_running) {
						for (m_y = m_dropPoint; m_y <= m_lowestY; m_y = m_y
								+ m_fallDistance) {
							// System.out.println("drop:"+m_y);
							try {
								repaint();
								Thread.sleep(m_speed);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						m_changes.clear();
					}
				}
			}).start();
		} else {
			new Thread(new Runnable() {
				public void run() {
					if (m_running) {
						try {
							m_x = 0;
							for (m_w = PIECE_SIZE; m_w > 0; m_w = m_w - EVEN) {
								repaint();
								m_x = m_x + 1;
								Thread.sleep(m_speed);
							}
							m_flipSide = true;
							for (m_w = 0; m_w < PIECE_SIZE; m_w = m_w + EVEN) {
								repaint();
								m_x = m_x - 1;
								Thread.sleep(m_speed);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						if (!m_criticalSection) {
							m_changes.clear();
						}
						m_flipSide = false;
					}
				}
			}).start();
		}
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: SetAnimation() END");
		}
	}

	/**
	 * Class Constructor for GameBoardGraphics, initialises all necessary
	 * variables. 
	 * \param grid - object storing data for the game grid 
	 * \param player1 - object containing the data for player 1 
	 * \param player2 - object containing the data for player 2
	 */
	public GameBoardGraphics(Grid grid, Player player1, Player player2)
			throws IOException {
		boolean test = false;
		if (test || m_test) {
			System.out
					.println("GameBoardGraphics :: GameBoardGraphics() BEGIN");
		}
		addMouseMotionListener(this);
		m_player1 = player1;
		m_player2 = player2;
		PLAYER1_COLOUR = m_player1.getPlayerColour();
		PLAYER2_COLOUR = m_player2.getPlayerColour();
		setGrid(grid);
		Y_SQUARES = getGrid().getGridHeight();
		X_SQUARES = getGrid().getGridWidth();
		GRID_WIDTH = (getXSquares() * getSquareWidth());
		GRID_HEIGHT = (getYSquares() * getSquareHeight());
		setPreferredSize(new Dimension(GRID_WIDTH, GRID_HEIGHT));
		m_running = true;
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: GameBoardGraphics() END");
		}
	}

	/**
	 * Method to update all the GUI elements and paint them to the screen.
	 * \param g - graphics object to handle all the data for creating
	 * repainting the gameboard
	 */
	@Override
	public void paintComponent(Graphics g) {
		boolean test = false;
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: paintComponent() BEGIN");
		}
		Graphics2D g2 = (Graphics2D) g;
		for (int i = 0; i < GRID_WIDTH; i += getSquareWidth()) {
			for (int j = 0; j < GRID_HEIGHT; j += getSquareHeight()) {
				paintFlip(g2, i, j);
				paintOthelloBoard(g2, i, j);

				if (m_type.equals("fall") && m_changes.size() > 0) {
					if (i == m_changes.get(0).getX() * getSquareWidth()
						  && j == m_changes.get(0).getY() * getSquareHeight()) {
						continue;
					}
				}
				paintBoardPiece(g2, i, j);
				if (!m_valid
						&& (PLAYER1_COLOUR.equals(Color.BLACK) || PLAYER1_COLOUR
								.equals(Color.WHITE))) {
					g2.drawImage(CROSS2, m_xmove.getX() * getSquareWidth()
							+ MID_POSITION, m_xmove.getY() * getSquareHeight()
							+ MID_POSITION, WINMARK_SIZE, WINMARK_SIZE, null);
					new Thread(new Runnable() {
						public void run() {
							try {
								Thread.sleep(200);
								m_valid = true;
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}).start();
				}
				paintAvailableMove(g2, i, j);
			}
		}
		paintFall(g2);
		paintConnectFourBoard(g2);
		if (!m_isOver) {
			if (PLAYER1_COLOUR.equals(Color.BLACK)
					|| PLAYER1_COLOUR.equals(Color.WHITE)) {
				if (m_AImove != null && !m_changes.isEmpty() && ((m_changes.
				        get(0).getValue() == Game.PlayerTurn.PLAYER1 &&
						(m_player1 instanceof OthelloAI || m_player1 instanceof 
						AIEasy)) || (m_changes.get(0).getValue() == Game.
						PlayerTurn.PLAYER2 && (m_player2 instanceof OthelloAI 
						                    || m_player2 instanceof AIEasy)))) {

					g2.setColor(Color.RED);
					g2.setStroke(new BasicStroke(4));
					g2.drawOval(m_AImove.getX() * getSquareWidth()
							+ MID_POSITION, m_AImove.getY() * getSquareHeight()
							+ MID_POSITION, WINMARK_SIZE, WINMARK_SIZE);
				}
			} else {
				m_d = this.getSize();
				g2.setColor(Color.BLACK);
				g2.drawLine(m_colX - ADJUST_POINT, 0, m_colX - ADJUST_POINT,
						m_d.height);
				g2.setColor(Color.BLACK);
				g2.drawLine(m_nextColX - ADJUST_POINT, 0, m_nextColX
						- ADJUST_POINT, m_d.height);
			}
		} else {
			paintWin(g2);
		}
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: paintComponent() END");
		}
	}

	/**
	 * Method for drawing the Connect four game board 
	 * \param g2 - graphics object to handle all the data for creating 
	 * \return true when the method completes
	 */
	public boolean paintConnectFourBoard(Graphics2D g2) {
		boolean test = false;
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: paintConnectFourBoard() "
			                                                         + "BEGIN");
		}
		if (PLAYER1_COLOUR.equals(Color.YELLOW)
				|| PLAYER1_COLOUR.equals(Color.RED)) {
			if (m_board == "board2") {
				g2.drawImage(CONNECT4BOARD2, 0, 0, GRID_WIDTH, GRID_HEIGHT,
						null);
			} else if (m_board == "board3") {
				g2.drawImage(CONNECT4BOARD3, 0, 0, GRID_WIDTH, GRID_HEIGHT,
						null);
			} else {
				g2.drawImage(CONNECT4BOARD, 0, 0, GRID_WIDTH, GRID_HEIGHT,null);
			}
		}
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: paintConnectFourBoard() "
			                                                           + "END");
		}
		return true;
	}

	/**
	 * Method for drawing the Othello game board 
	 * \param g2 - graphics object to handle all the data for creating 
	 * \param i - point X of the board 
	 * \param j - point Y of the board \return true when the method completes
	 */
	public boolean paintOthelloBoard(Graphics2D g2, int i, int j) {
		boolean test = false;
		if (test || m_test) {
			System.out
					.println("GameBoardGraphics :: paintOthelloBoard() BEGIN");
		}
		if (!m_flippingPiece) {
			if (PLAYER1_COLOUR.equals(Color.BLACK)
					|| PLAYER1_COLOUR.equals(Color.WHITE)) {
				if ((i / getSquareWidth() + j / getSquareWidth()) % EVEN == 0) {
					if (m_board == "board2") {
						g2.drawImage(GRID4, i, j, null);
					} else if (m_board == "board3") {
						g2.drawImage(GRID6, i, j, null);
					} else {
						g2.drawImage(GRID2, i, j, null);
					}
				} else {
					if (m_board == "board2") {
						g2.drawImage(GRID3, i, j, null);
					} else if (m_board == "board3") {
						g2.drawImage(GRID5, i, j, null);
					} else {
						g2.drawImage(GRID1, i, j, null);
					}
				}
			}
		}
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: paintOthelloBoard() END");
		}
		return true;
	}

	/**
	 * Method for drawing the pieces to the game board 
	 * \param g2 - graphics object to handle all the data for creating
	 * \param i - point X of the board 
	 * \param j - point Y of the board 
	 * \return true when the method completes
	 */
	public boolean paintBoardPiece(Graphics2D g2, int i, int j) {
		boolean test = false;
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: paintBoardPiece() BEGIN");
		}
		if (!m_flippingPiece) {
			if (getGrid().getCoordinate(i / getSquareWidth(),
			     j / getSquareHeight()).getValue() == Game.PlayerTurn.PLAYER1) {
				if (PLAYER1_COLOUR.equals(Color.white)) {
					g2.drawImage(WHITE, i + MID_DIFF, j + MID_DIFF, PIECE_SIZE,
							PIECE_SIZE, null);
				} else if (PLAYER1_COLOUR.equals(Color.black)) {
					g2.drawImage(BLACK, i + MID_DIFF, j + MID_DIFF, PIECE_SIZE,
							PIECE_SIZE, null);
				} else {
					g2.setColor(PLAYER1_COLOUR);
					g2.fillOval(i + MID_DIFF, j + MID_DIFF, PIECE_SIZE,
							PIECE_SIZE);
				}
			} else if (getGrid().getCoordinate(i / getSquareWidth(),
				 j / getSquareHeight()).getValue() == Game.PlayerTurn.PLAYER2) {
				if (PLAYER2_COLOUR.equals(Color.white)) {
					g2.drawImage(WHITE, i + MID_DIFF, j + MID_DIFF, PIECE_SIZE,
							PIECE_SIZE, null);
				} else if (PLAYER2_COLOUR.equals(Color.black)) {
					g2.drawImage(BLACK, i + MID_DIFF, j + MID_DIFF, PIECE_SIZE,
							PIECE_SIZE, null);
				} else {
					g2.setColor(PLAYER2_COLOUR);
					g2.fillOval(i + MID_DIFF, j + MID_DIFF, PIECE_SIZE,
							PIECE_SIZE);
				}
			}
		}
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: paintBoardPiece() END");
		}
		return true;
	}

	/**
	 * Method to paint the available moves in othello 
	 * \param g2 - graphics object to handle all the data for creating 
	 * \param i - point X of the board 
	 * \param j - point Y of the board
	 */
	private void paintAvailableMove(Graphics2D g2, int i, int j) {
		boolean test = false;
		if (test || m_test) {
			System.out
					.println("GameBoardGraphics :: paintAvailableMove() BEGIN");
		}
		if (!m_flippingPiece) {
			Coordinate c = getGrid().getCoordinate(i / getSquareWidth(),
					j / getSquareHeight());
			if (c.getValue() == Game.PlayerTurn.PLAYER1_AM
					&& m_player1 instanceof Human) {
				g2.drawImage(CROSS, c.getX() * getSquareWidth() + MID_POSITION,
						c.getY() * getSquareHeight() + MID_POSITION,
						WINMARK_SIZE, WINMARK_SIZE, null);
				if (c.getX() * getSquareWidth() == m_colX
						&& c.getY() * getSquareHeight() == m_colY) {
					if (PLAYER1_COLOUR.equals(Color.white)) {
						g2.drawImage(WHITE, i + MID_DIFF, j + MID_DIFF,
								PIECE_SIZE, PIECE_SIZE, null);
					} else if (PLAYER1_COLOUR.equals(Color.black)) {
						g2.drawImage(BLACK, i + MID_DIFF, j + MID_DIFF,
								PIECE_SIZE, PIECE_SIZE, null);
					}
				}
			} else if (c.getValue() == Game.PlayerTurn.PLAYER2_AM
					&& m_player2 instanceof Human) {
				g2.drawImage(CROSS, c.getX() * getSquareWidth() + MID_POSITION,
						c.getY() * getSquareHeight() + MID_POSITION,
						WINMARK_SIZE, WINMARK_SIZE, null);
				if (c.getX() * getSquareWidth() == m_colX
						&& c.getY() * getSquareHeight() == m_colY) {
					if (PLAYER2_COLOUR.equals(Color.white)) {
						g2.drawImage(WHITE, i + MID_DIFF, j + MID_DIFF,
								PIECE_SIZE, PIECE_SIZE, null);
					} else if (PLAYER2_COLOUR.equals(Color.black)) {
						g2.drawImage(BLACK, i + MID_DIFF, j + MID_DIFF,
								PIECE_SIZE, PIECE_SIZE, null);
					}
				}
			}
		}
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: paintAvailableMove() END");
		}
	}

	/**
	 * Method to paint the animation of the falling connect four piece 
	 * \param g2 - graphics object to handle all the data for creating
	 */
	private void paintFall(Graphics2D g2) {
		boolean test = false;
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: paintFall() BEGIN");
		}
		if (m_type.equals("fall") && m_start && m_changes.size() > 0) {
			int m_x = m_changes.get(0).getX() * getSquareWidth();
			if (m_changes.get(0).getValue() == Game.PlayerTurn.PLAYER1) {
				g2.setColor(PLAYER1_COLOUR);
			} else {
				g2.setColor(PLAYER2_COLOUR);
			}
			g2.fillOval(m_x + MID_DIFF, m_y + MID_DIFF, PIECE_SIZE, PIECE_SIZE);
		}
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: paintFall() END");
		}
	}

	/**
	 * Method to paint the animation of the flipping othello piece 
	 * \param g2 - graphics object to handle all the data for creating 
	 * \param i - point X of the piece to be flipped 
	 * \param j - point Y of the piece to be flipped
	 */
	private void paintFlip(Graphics2D g2, int i, int j)
			throws NullPointerException, ConcurrentModificationException {
		boolean test = false;
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: paintFlip() BEGIN");
		}
		m_flippingPiece = false;
		if (m_type.equals("flip") && m_changes.size() > 0 && m_start) {
			m_criticalSection = true;
			Iterator<Coordinate> s = m_changes.iterator();
			for (s = m_changes.iterator(); s.hasNext();) {
				try {
					Coordinate item = s.next();
					if (item != null && item.getX() * getSquareWidth() == i
							&& item.getY() * getSquareHeight() == j) {
						m_flippingPiece = true;
						if ((i / getSquareWidth() + j / getSquareWidth())
								% EVEN == 0) {
							// g2.setColor(new Color(RED,GREEN,BLUE));
							if (m_board == "board2") {
								g2.drawImage(GRID4, i, j, null);
							} else if (m_board == "board3") {
								g2.drawImage(GRID6, i, j, null);
							} else {
								g2.drawImage(GRID2, i, j, null);
							}
						} else {
							// g2.setColor(new
							// Color(DARK_RED,DARK_GREEN,DARK_BLUE));
							if (m_board == "board2") {
								g2.drawImage(GRID3, i, j, null);
							} else if (m_board == "board3") {
								g2.drawImage(GRID5, i, j, null);
							} else {
								g2.drawImage(GRID1, i, j, null);
							}
						}
						g2.setColor(Color.WHITE);
						g2.setStroke(new BasicStroke(2));
						g2.drawRect(i, j, getSquareWidth(), getSquareHeight());
						if ((item.getValue() == Game.PlayerTurn.PLAYER1
								&& !m_flipSide && PLAYER2_COLOUR
									.equals(Color.white))
								|| (item.getValue() == Game.PlayerTurn.PLAYER1
										&& m_flipSide && PLAYER1_COLOUR
											.equals(Color.white))
								|| (item.getValue() == Game.PlayerTurn.PLAYER2
										&& !m_flipSide && PLAYER1_COLOUR
											.equals(Color.white))
								|| (item.getValue() == Game.PlayerTurn.PLAYER2
										&& m_flipSide && PLAYER2_COLOUR
											.equals(Color.white))) {
							g2.drawImage(WHITE, i + m_x + MID_DIFF, j
									+ MID_DIFF, m_w, PIECE_SIZE, null);
						} else {
							g2.drawImage(BLACK, i + m_x + MID_DIFF, j
									+ MID_DIFF, m_w, PIECE_SIZE, null);
						}
					}
				} catch (NullPointerException e) {
				}
			}
			m_criticalSection = false;
		}
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: paintFlip() END");
		}
	}

	/**
	 * Method to show green dots on the winning pieces when the game ends 
	 * \param g - graphics object to handle all the data for creating
	 */
	private void paintWin(Graphics g) {
		boolean test = false;
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: paintWin() BEGIN");
		}
		Iterator<Coordinate> iterator = m_win.iterator();
		while (iterator.hasNext()) {
			m_next = iterator.next();
			if (PLAYER1_COLOUR.equals(Color.BLACK)
					|| PLAYER1_COLOUR.equals(Color.WHITE)) {
				g.drawImage(WIN_STAR,
						(SQUARE_WIDTH * m_next.getX() + MID_POSITION),
						(SQUARE_HEIGHT * m_next.getY() + MID_POSITION),
						WINMARK_SIZE, WINMARK_SIZE, null);
			} else {
				m_starX = SQUARE_WIDTH * m_next.getX() + MID_POSITION
						- ADJUST_POINT;
				m_starY = SQUARE_HEIGHT * m_next.getY() + MID_POSITION
						- ADJUST_POINT - ADJUST_POINT;
				g.drawImage(WIN_STAR, m_starX, m_starY, WINMARK_SIZE,
						WINMARK_SIZE, null);
			}
		}
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: paintWin() END");
		}
	}

	/**
	 * Method to update the mouse position to draw a piece following the cursor
	 * \param e - An event which indicates that a mouse action occurred in a
	 * component
	 */
	public void mouseMoved(MouseEvent e) {
		boolean test = false;
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: mouseMoved() BEGIN");
		}
		m_colX = (e.getX() / getSquareWidth()) * getSquareWidth();
		m_colY = (e.getY() / getSquareHeight()) * getSquareHeight();
		m_nextColX = (e.getX() / getSquareWidth()) * getSquareWidth()
				+ getSquareWidth();
		repaint();
		if (test || m_test) {
			System.out.println("GameBoardGraphics :: mouseMoved() END");
		}
	}

	/**
	 * Method only for completing the implementation of MouseMotionListener
	 * \param e - An event which indicates that a mouse action occurred in a
	 * component
	 */
	public void mouseDragged(MouseEvent e) {
	}

	/**
	 * Test method.
	 * 
	 * \param args
	 * 
	 * \throws IOException
	 * \throws InterruptedException
	 */
	public static void main(String args[]) throws IOException,
			InterruptedException {
		Othello game = new Othello();
		Player player1 = new OthelloAI(game);
		Player player2 = new AIEasy(game);
		player1.setPlayerName("Gavin");
		player2.setPlayerName("Lucy");
		player1.setPlayerColour(Color.BLACK);
		player2.setPlayerColour(Color.WHITE);
		game.setPlayer1(player1);
		game.setPlayer2(player2);
		game.start();
		// game.getWindow().getDrawing().getGridPanel().SetRun(false);
		int m_maxOthelloSpeed = 500;
		int m_coord = 1;
		int m_squareSize = 8;
		System.out
				.println("Board Width =8:"
						+ (game.getWindow().getDrawing().getGridPanel()
								.getXSquares() == m_squareSize));
		System.out
				.println("Board Length =8:"
						+ (game.getWindow().getDrawing().getGridPanel()
								.getXSquares() == m_squareSize));
		game.getWindow().getDrawing().getGridPanel()
				.SetSpeed(m_maxOthelloSpeed);
		game.getWindow().getDrawing().getGridPanel()
				.SetValid(false, new Coordinate(m_coord, m_coord + m_coord));
		game.getWindow().getDrawing().getGridPanel()
				.SetValid(false, new Coordinate(m_coord + m_coord, m_coord));
		game.getWindow().getDrawing().getGridPanel()
				.SetValid(false, new Coordinate(m_coord, m_coord));
	}

	// private member variables
	private Player m_player1;
	private Player m_player2;
	private Grid m_grid;
	private final int Y_SQUARES;
	private final int X_SQUARES;
	private final Color PLAYER1_COLOUR;
	private final Color PLAYER2_COLOUR;
	private final int SQUARE_WIDTH = 60;
	private final int SQUARE_HEIGHT = 60;
	private final int GRID_WIDTH;
	private final int GRID_HEIGHT;
	private boolean m_isOver = false;
	private Set<Coordinate> m_win;
	private Coordinate m_next;
	private final int MID_POSITION = (SQUARE_WIDTH + SQUARE_HEIGHT) / 6;
	private final int WINMARK_SIZE = (SQUARE_WIDTH + SQUARE_HEIGHT) / 6;
	private int m_x = 0;
	private boolean m_flippingPiece;
	private boolean m_flipSide = false;
	private int m_dropPoint = -30;
	private int m_speed;
	private int m_fallDistance = 30;
	private String m_type = "";
	private ArrayList<Coordinate> m_changes;
	private final int MID_DIFF = 3;
	private final int PIECE_SIZE = (getSquareWidth() + getSquareHeight()) / 2
			- 2 * MID_DIFF;
	private int m_w = PIECE_SIZE;
	private int m_y = 0;
	private boolean m_start = false;
	private int m_lowestY;
	private final int EVEN = 2;
	private int m_nextColX;
	private int m_colX;
	private int m_colY;
	private final int ADJUST_POINT = 1;
	private int m_starX;
	private int m_starY;
	private Dimension m_d;
	private final BufferedImage CONNECT4BOARD = ImageIO.read(getClass()
			.getResource("/resource/connect4board.png"));;
	private final BufferedImage WIN_STAR = ImageIO.read(getClass().getResource(
			"/resource/star.png"));;
	private final BufferedImage GRID1 = ImageIO.read(getClass().getResource(
			"/resource/Oboard1.png"));
	private final BufferedImage GRID2 = ImageIO.read(getClass().getResource(
			"/resource/Oboard2.png"));
	private final BufferedImage GRID3 = ImageIO.read(getClass().getResource(
			"/resource/Oboard3.png"));
	private final BufferedImage GRID4 = ImageIO.read(getClass().getResource(
			"/resource/Oboard4.png"));
	private final BufferedImage GRID5 = ImageIO.read(getClass().getResource(
			"/resource/Oboard5.png"));
	private final BufferedImage GRID6 = ImageIO.read(getClass().getResource(
			"/resource/Oboard6.png"));

	private static final long serialVersionUID = 1L;
	private final BufferedImage CONNECT4BOARD2 = ImageIO.read(getClass()
			.getResource("/resource/connect4board2.png"));
	private final BufferedImage CONNECT4BOARD3 = ImageIO.read(getClass()
			.getResource("/resource/connect4board3.png"));
	private final BufferedImage CROSS = ImageIO.read(getClass().getResource(
			"/resource/cross.png"));
	private final BufferedImage CROSS2 = ImageIO.read(getClass().getResource(
			"/resource/cross2.png"));
	private final BufferedImage WHITE = ImageIO.read(getClass().getResource(
			"/resource/white.png"));
	private final BufferedImage BLACK = ImageIO.read(getClass().getResource(
			"/resource/black.png"));
	private String m_board;
	private Coordinate m_AImove;
	private boolean m_criticalSection;
	private Coordinate m_xmove;
	private boolean m_valid = true;
	private boolean m_running;
	private boolean m_test = false;
}
