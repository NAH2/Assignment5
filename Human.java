/**
 *	\\file    -Human.java
 *	\author  -G.Howard
 *	\date    -20/02/2014
 *
 *  \brief   sets which player's move it is then sends this to
 *           the game class
 */
 
import java.awt.Color;

public class Human extends Player {
	
	/**
	 * initialises player with a game
	 * \param game a game to be initialised
	 */
	public Human(Game game) {
		super(game);
		boolean test = false;
        if (test || m_test) {
            System.out.println("Human :: Human() BEGIN");
        }
        if (test || m_test) {
            System.out.println("Human :: Human() END");
        }
	}

	/**
	 * initialises player with a game, name and a colour
	 * \Param game, name, color a game, name and color to be initialised
	 */
	public Human(Game game, String name, Color color) {
		super(game, name, color);
		boolean test = false;
        if (test || m_test) {
            System.out.println("Human :: Human() BEGIN");
        }
        if (test || m_test) {
            System.out.println("Human :: Human() END");
        }
	}

	/**
	 *  Sets which player's move it is
	 */
	public void isYourMove() {
		boolean test = false;
        if (test || m_test) {
            System.out.println("Human :: isYourMove() BEGIN");
        }
		boolean m_Trace = false;
		
		setYourTurn(true);
		if (m_Trace) System.out.println
			("Human::IsYourMove() - player's move");
		
		if (test || m_test) {
            System.out.println("Human :: isYourMove() END");
        }
	}
	
	/**
	 *  Sends the move to the game class
	 * \param move a move to make
	 */
	public void sendMove(Coordinate move) {
		boolean test = false;
        if (test || m_test) {
            System.out.println("Human :: sendMove() BEGIN");
        }
        
		if(getYourTurn()) {
			setYourTurn(false);
			try {
				getGame().moveMade(move);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (test || m_test) {
            System.out.println("Human :: sendMove() END");
        }
	}
	
	/**
	 * Empty method is required for Othello and Connect Four AI
	 */
	public void sendMove(){
		boolean test = false;
        if (test || m_test) {
            System.out.println("Human :: sendMove() BEGIN");
        }
        if (test || m_test) {
            System.out.println("Human :: sendMove() END");
        }
	}
	
	 /** test variable */
    private boolean m_test = false;
}

