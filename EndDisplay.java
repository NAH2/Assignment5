import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * \\file -EndDisplay.java 
 * \author - Lewis Edwards
 * \date -26th March 14
 * 
 * \brief Displays this window when the game ends
 * 
 * This Class displaye the score when the game ends
 * This class is tested by running a game
 */
public class EndDisplay extends JFrame implements ActionListener {
    /**
     * accessor method to set m_game
     * 
     * \param Game game - reference to game object
     */
	public void setGame(Game game){
	    boolean test = false;
        if (test || m_test) {
            System.out.println("EndDisplay :: setGame() BEGIN");
        }
        
		m_game = game;
		
		if (test || m_test) {
            System.out.println("EndDisplay :: setGame() END");
        }
	}
	
	/**
	 * Accessor method to get m_game
	 * 
	 * \return Game m_game - reference to game object
	 */
	public Game getGame(){
	    boolean test = false;
        if (test || m_test) {
            System.out.println("EndDisplay :: getGame() BEGIN");
        }
        if (test || m_test) {
            System.out.println("EndDisplay :: getGame() END");
        }
        
		return m_game;
	}
	
	/**
	 * This method creates a new panel which hold the information on player 1.
	 * This includes the name, score, turns taken and the colour of piece they
	 * used.
	 * 
	 * \param game This is an instance of the Game class.
	 * \return Returns the frame for the statistics of player 1.
	 */
	private JPanel statsPlayer1(Game game) {
	    boolean test = false;
        if (test || m_test) {
            System.out.println("EndDisplay :: statsPlayer1() BEGIN");
        }
        
		final int P1SCOREGRIDY = 1;
		final int P2SCOREGRIDY = 2;
		final int OVALSIZE = 60;
		
		JPanel stats = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		stats.setLayout(layout);
		
		JLabel player1Name = new JLabel(game.getPlayer1().getPlayerName());
		layout.setConstraints(player1Name, c);
		stats.add(player1Name);

		c.gridy = P1SCOREGRIDY;
		JLabel player1Score = new JLabel("" + game.getPlayer1Score());
		layout.setConstraints(player1Score, c);
		stats.add(player1Score);
		
		c.gridy = P2SCOREGRIDY;
		JLabel player1Piece = new JLabel();
		BufferedImage piece = new BufferedImage
				(OVALSIZE, OVALSIZE, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g2 = piece.createGraphics();
		if(game.getPlayer1().getPlayerColour().equals(Color.BLACK)){
			g2.setColor(Color.WHITE);
			g2.fillRect(0, 0, OVALSIZE, OVALSIZE);
		}
		g2.setColor(game.getPlayer1().getPlayerColour());
		g2.fillOval(0, 0, OVALSIZE, OVALSIZE);
		player1Piece.setIcon(new ImageIcon(piece));
		layout.setConstraints(player1Piece, c);
		stats.add(player1Piece);
		
        if (test || m_test) {
            System.out.println("EndDisplay :: statsPlayer1() END");
        }
        
		return stats;
	}
	
	/**
	 * This method creates a new panel which hold the information on player 2.
	 * This includes the name, score, turns taken and the colour of piece they
	 * used
	 * 
	 * \param game
	 * \return Returns the frame for the statistics of player 1.
	 */
	private JPanel statsPlayer2(Game game) {
	    boolean test = false;
        if (test || m_test) {
            System.out.println("EndDisplay :: statsPlayer2() BEGIN");
        }
        
		final int OVALSIZE = 60;
		final int SCOREPOSITION = 1;
		final int PIECEPOSITION = 2;
		
		JPanel stats = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		stats.setLayout(layout);
		
		JLabel player2Name = new JLabel(game.getPlayer2().getPlayerName());
		layout.setConstraints(player2Name, c);
		stats.add(player2Name);
		
		c.gridy = SCOREPOSITION;
		JLabel player2Score = new JLabel("" + game.getPlayer2Score());
		layout.setConstraints(player2Score, c);
		stats.add(player2Score);
		
		c.gridy = PIECEPOSITION;
		JLabel player2Piece = new JLabel();
		BufferedImage piece2 = new BufferedImage
				(OVALSIZE, OVALSIZE, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g2 = piece2.createGraphics();
		if(game.getPlayer2().getPlayerColour().equals(Color.BLACK)){
			g2.setColor(Color.WHITE);
			g2.fillRect(0, 0, OVALSIZE, OVALSIZE);
		}
		g2.setColor(game.getPlayer2().getPlayerColour());
		g2.fillOval(0, 0, OVALSIZE, OVALSIZE);
		player2Piece.setIcon(new ImageIcon(piece2));
		layout.setConstraints(player2Piece, c);
		stats.add(player2Piece);
		
        if (test || m_test) {
            System.out.println("EndDisplay :: statsPlayer2() END");
        }
		
		return stats;
	}

	/**
	 * actionPerformed closes the window 
	 */
	public void actionPerformed(ActionEvent evt) {
	    Object src = evt.getSource();
	    if (src == OK_BUTTON) {
	    	dispose();
	    }
	}
	
	/**
     * This method shows the panel at the end of a game, showing all the
     * information on the game
     * 
     * \param game This is an instance of the game class.
     */
    public EndDisplay(Game game) {
        boolean test = false;
        if (test || m_test) {
            System.out.println("EndDisplay :: EndDisplay() BEGIN");
        }
        
        final int YESGRIDX = 0;
        final int YESGRIDY = 0;
        final int YESPADY = 0;
        final double STATP1WEIGHTX = 0.5;
        final int STATP1WIDTH = 1;
        final int STATP1GRIDY = 1;
        final int STATP2GRIDX = 1;
        final int ENDLABELGRIDX = 0;
        final int ENDLABELGRIDY =   2;
        final int BUTTONPANELGRIDY = 3;
        final int BUTTONSIZEX = 100;
        final int BUTTONSIZEY = 30;
        final int FRAME_WIDTH = 250;
        final int FRAME_HEIGHT = 150;
        final String WINS = " Wins!";
        final String DRAW = "The game was a draw";
        
        setGame(game);
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        getContentPane().setPreferredSize(new Dimension(FRAME_WIDTH,FRAME_HEIGHT));
        
        JLabel winner = new JLabel();
        
        if (game.isWinner() == Game.PlayerTurn.PLAYER1) {
            winner.setText(game.getPlayer1().getPlayerName() + WINS);
        } else if (game.isWinner() == Game.PlayerTurn.PLAYER2) {
            winner.setText(game.getPlayer2().getPlayerName() + WINS);
        } else {
            winner.setText(DRAW);
        }
        
        OK_BUTTON.setPreferredSize(new Dimension(BUTTONSIZEX,BUTTONSIZEY));
        getContentPane().setLayout(layout);
        
        c.gridy = YESGRIDY;
        c.gridx = YESGRIDX;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.ipady = YESPADY;
        layout.setConstraints(winner, c);
        getContentPane().add(winner);
        
        JPanel stats1 = statsPlayer1(game);
        c.weightx = STATP1WEIGHTX;
        c.gridwidth = STATP1WIDTH;
        c.gridy = STATP1GRIDY;
        layout.setConstraints(stats1, c);
        getContentPane().add(stats1);
        
        JPanel stats2 = statsPlayer2(game);
        c.gridx = STATP2GRIDX;
        layout.setConstraints(stats2, c);
        getContentPane().add(stats2);

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.gridx = ENDLABELGRIDX;
        c.gridy = ENDLABELGRIDY;
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(OK_BUTTON);
        
        c.gridy = BUTTONPANELGRIDY;
        layout.setConstraints(buttonPanel, c);
        getContentPane().add(buttonPanel);
        
        OK_BUTTON.addActionListener(this);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        
        if (test || m_test) {
            System.out.println("EndDisplay :: EndDisplay() END");
        }
    }
    
    /** Store the ok button */
	private final JButton OK_BUTTON = new JButton("Ok");
	/** reference to the current game */
    private Game m_game;
    /** test variable */
    private boolean m_test = true;
}