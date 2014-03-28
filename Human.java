import java.awt.Color;
/**
 *	\\file    -Human.java
 *	\author  -G.Howard
 *	\date    -20/02/2014
 *
 *  \brief   sets which player's move it is then sends this to
 *           the game class
 */
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
	 * \param game - game to be initialised 
	 * \param name - name to be initialised 
	 * \param color - color to be initialised 
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
	 *  \return true if the method completes
	 */
	public boolean isYourMove() {
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
		return true;
	}
	
	/**
	 *  Sends the move to the game class
	 * \param move a move to make
	 * \return true if the method completes
	 */
	public boolean sendMove(Coordinate move) {
		boolean test = false;
        if (test || m_test) {
            System.out.println("Human :: sendMove() BEGIN");
        }
        
		if(getYourTurn()) {
			setYourTurn(false);
			try {
				getGame().moveMade(move);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (test || m_test) {
            System.out.println("Human :: sendMove() END");
        }
		return true;
	}
	
	/**
	 * Empty method is required for Othello and Connect Four AI
	 * \return true if the method completes
	 */
	public boolean sendMove(){
		boolean test = false;
        if (test || m_test) {
            System.out.println("Human :: sendMove() BEGIN");
        }
        if (test || m_test) {
            System.out.println("Human :: sendMove() END");
        }
        return true;
	}
	
	 /** test variable */
    private boolean m_test = false;
}

