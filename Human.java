/**
 *	@file    -Human.java
 *	@author  -G.Howard
 *	@date    -20/02/2014
 *
 *  @brief   sets which player's move it is then sends this to
 *           the game class
 */
 
import java.awt.Color;

public class Human extends Player {
	
/**
 * initialises player with a game
 *
 * @param game a game to be initialised
 */
	public Human(Game game) {
		super(game);
	}
/**
 * initialises player with a game, name and a colour
 *
 * @Param game, name, color a game, name and color to be initialised
 */
	public Human(Game game, String name, Color color) {
		super(game, name, color);
	}

/**
 *  Sets which player's move it is
 */
	public void isYourMove() {
		boolean m_Trace = false;
		
		setYourTurn(true);
		if (m_Trace) System.out.println
			("Human::IsYourMove() - player's move");
	}
	
/**
 *  Sends the move to the game class
 * 
 * @param move a move to make
 */
	public void sendMove(Coordinate move) {
		if(getYourTurn()) {
			setYourTurn(false);
			getGame().moveMade(move);
		}
	}
}

