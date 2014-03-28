import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
/**
 *  \\file	PlayerSettings.Java
 * 	\author	Callum Hazelton 709161, Gavin Bailey 711036
 * 	\date	14/02/2014
 * 	
 * \brief Allows you to change settings of player 1 and player 2
 * 
 * Change the player names, player colours, whether players are human/ai and 
 * difficulty of ai. 
 * Changes to the class involved separating the action listeners to comply with 
 * code conventions. Other changes include adding the substance to the resume
 * game button to allow it load the file. 
 */
public class PlayerSettings extends JFrame {

	/**
	 * Sets the game class with which player settings is to send its player data
	 * 
	 * \param the instance of the game class
	 */
	public void setGame(Game game) {
		boolean test = false;
		if (test || m_test) {
            System.out.println("PlayerSettings :: setGame() BEGIN");
            }
		m_game = game;
		
		if (test || m_test) {
			System.out.println("PlayerSettings :: setGame() END");
		}
	}

	/**
	 * Returns the game class which player settings is to send its player data
	 * 
	 * \return pointer to game class
	 */
	public Game getGame() {
		boolean test = false;
		if (test || m_test) {
            System.out.println("PlayerSettings :: getGame() BEGIN");
            }
		if (test || m_test) {
			System.out.println("PlayerSettings :: getGame() END");
		}
		return m_game;
	}

	/**
	 * Runs methods inside
	 * 
	 * \param game instance of game class
	 * \param isOthelloboolean indicating whether or not to limit player colour
	 *            choices in othello.
	 */
	public PlayerSettings(Game game, boolean isOthello) {
		boolean test = false;
		if (test || m_test) {
            System.out.println("PlayerSettings :: PlayerSettings() BEGIN");
            }
		ISOTHELLO = isOthello;
		if (isOthello) {
			PLAYERCOLOUR_A1 = new JRadioButton("Black");
			PLAYERCOLOUR_B1 = new JRadioButton("White");
			PLAYERCOLOUR_A2 = new JRadioButton("Black");
			PLAYERCOLOUR_B2 = new JRadioButton("White");
		} else {
			PLAYERCOLOUR_A1 = new JRadioButton("Red");
			PLAYERCOLOUR_B1 = new JRadioButton("Yellow");
			PLAYERCOLOUR_A2 = new JRadioButton("Red");
			PLAYERCOLOUR_B2 = new JRadioButton("Yellow");
		}
		m_game = game;
		windowInitialise();
		buttonGroups();
		listeners();
		listenersPlayers();
		listenersControls();
		PLAYERCOLOUR_A1.doClick();
		HUMAN.doClick();
		HUMAN2.doClick();
		setTitle("Player settings");
		pack();
		setLocationRelativeTo(null);
		
		if (test || m_test) {
			System.out.println("PlayerSettings :: PlayerSettings() END");
		}
	}

	/**
	 * Adds listeners to react when buttons are pressed for the Colour options
	 */
	private void listeners() {
		boolean test = false;
		if (test || m_test) {
            System.out.println("PlayerSettings :: listeners() BEGIN");
            }
		ButtonListener buttonListener = new ButtonListener();
		PLAYERCOLOUR_A1.addActionListener(buttonListener);
		PLAYERCOLOUR_B1.addActionListener(buttonListener);
		PLAYERCOLOUR_A2.addActionListener(buttonListener);
		PLAYERCOLOUR_B2.addActionListener(buttonListener);
		if (test || m_test) {
			System.out.println("PlayerSettings :: listeners() END");
		}
	}
	
	/**
	 * Adds listeners to react when buttons are pressed for the opponent options
	 */
	private void listenersPlayers(){
		boolean test = false;
		if (test || m_test) {
            System.out.println("PlayerSettings :: listenersPlayers() BEGIN");
            }
		
		PlayersButtonListener playersButtonListener = 
				new PlayersButtonListener();
		EASY.addActionListener(playersButtonListener);
		EASY2.addActionListener(playersButtonListener);
		HARD.addActionListener(playersButtonListener);
		HARD2.addActionListener(playersButtonListener);
		HUMAN.addActionListener(playersButtonListener);
		HUMAN2.addActionListener(playersButtonListener);
		AI.addActionListener(playersButtonListener);
		AI2.addActionListener(playersButtonListener);
		
		if (test || m_test) {
			System.out.println("PlayerSettings :: listenersPlayers() END");
		}
	}
	
	/**
	 * Adds listeners to react when buttons are pressed for the control options
	 */
	private void listenersControls(){
		boolean test = false;
		if (test || m_test) {
            System.out.println("PlayerSettings :: listenersControls() BEGIN");
            }
		
		ControlButtonListener controlButtonListener = 
				new ControlButtonListener();
		STARTBUTTON.addActionListener(controlButtonListener);
		RESUME_BUTTON.addActionListener(controlButtonListener);

		if (test || m_test) {
			System.out.println("PlayerSettings :: listenersControls() END");
		}
	}

	/**
	 * Sets out the main window and where the panels are placed on it
	 */
	private void windowInitialise() {
		boolean test = false;
		if (test || m_test) {
            System.out.println("PlayerSettings :: windowInitialise() BEGIN");
            }
		
		GridBagConstraints a = new GridBagConstraints();
		int padx = 10;
		int pady = 10;
		player = getContentPane();
		player.setLayout(new GridBagLayout());

		a.ipadx = padx;
		a.ipady = pady;
		a.gridx = XLINE1;
		a.gridy = YLINE1;
		player.add(player1Panel(), a);

		a.gridx = XLINE2;
		a.gridy = YLINE1;
		player.add(player2Panel(), a);

		a.gridx = XLINE1;
		a.gridy = YLINE2;
		a.fill = GridBagConstraints.HORIZONTAL;

		player.add(STARTBUTTON, a);
		a.gridx = XLINE2;
		a.gridy = YLINE2;
		player.add(RESUME_BUTTON, a);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		
		if (test || m_test) {
			System.out.println("PlayerSettings :: windowInitialise() END");
		}
	}

	/**
	 * Sets out everything on this panel using grid bag layout
	 * 
	 * \return the data on this panel
	 */
	private JPanel player1Panel() {
		boolean test = false;
		if (test || m_test) {
            System.out.println("PlayerSettings :: player1Panel() BEGIN");
            }
		
		JPanel player1Panel = new JPanel(new GridBagLayout());
		GridBagConstraints b = new GridBagConstraints();
		player1Panel.setBorder(BorderFactory.createEtchedBorder());

		b.gridx = XLINE2;
		b.gridy = YLINE1;
		JLabel p1 = new JLabel("Player 1");
		player1Panel.add(p1, b);

		b.gridx = XLINE1;
		b.gridy = YLINE2;
		JLabel pName1 = new JLabel("Player Name");
		player1Panel.add(pName1, b);

		PLAYERNAME1.setPreferredSize(new Dimension(100, 20));
		b.gridx = XLINE2;
		b.gridy = YLINE2;
		player1Panel.add(PLAYERNAME1, b);

		b.gridx = XLINE1;
		b.gridy = YLINE3;
		JLabel pColour1 = new JLabel("Player Colour");
		player1Panel.add(pColour1, b);

		b.gridx = XLINE2;
		b.gridy = YLINE3;

		player1Panel.add(PLAYERCOLOUR_A1, b);
		b.gridx = XLINE3;
		b.gridy = YLINE3;

		player1Panel.add(PLAYERCOLOUR_B1, b);

		b.gridx = XLINE2;
		b.gridy = YLINE4;
		player1Panel.add(optionsPlayer1(), b);
		
		if (test || m_test) {
			System.out.println("PlayerSettings :: player1Panel() END");
		}
		return player1Panel;
		
		
	}

	/**
	 * Sets out everything on this panel using grid bag layout
	 * 
	 * \return the data on this panel
	 */
	private JPanel player2Panel() {
		boolean test = false;
		if (test || m_test) {
            System.out.println("PlayerSettings :: player2Panel() BEGIN");
            }
		
		int txtBoxWidth = 100;
		int txtBoxHeight = 20;
		JPanel player2Panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		player2Panel.setBorder(BorderFactory.createEtchedBorder());

		c.gridx = XLINE2;
		c.gridy = YLINE1;
		JLabel p2 = new JLabel("Player 2");
		player2Panel.add(p2, c);

		c.gridx = XLINE1;
		c.gridy = YLINE2;
		JLabel pName2 = new JLabel("Player Name");
		player2Panel.add(pName2, c);

		PLAYERNAME2.setPreferredSize(new Dimension(txtBoxWidth,txtBoxHeight));
		c.gridx = XLINE2;
		c.gridy = YLINE2;
		player2Panel.add(PLAYERNAME2, c);

		c.gridx = XLINE1;
		c.gridy = YLINE3;
		JLabel pColour2 = new JLabel("Player Colour");
		player2Panel.add(pColour2, c);

		c.gridx = XLINE2;
		c.gridy = YLINE3;

		player2Panel.add(PLAYERCOLOUR_A2, c);
		c.gridx = XLINE3;
		c.gridy = YLINE3;

		player2Panel.add(PLAYERCOLOUR_B2, c);

		c.gridx = XLINE2;
		c.gridy = YLINE4;
		player2Panel.add(optionsPlayer2(), c);

		if (test || m_test) {
			System.out.println("PlayerSettings :: player2Panel() END");
		}
		
		return player2Panel;
	}

	/**
	 * Sets out everything on this panel using grid bag layout
	 * 
	 * \return the data on this panel
	 */
	private JPanel optionsPlayer1() {
		boolean test = false;
		if (test || m_test) {
            System.out.println("PlayerSettings :: optionsPlayer1() BEGIN");
            }
		
		int btnWidth = 100;
		int btnHeight = 20;
		JPanel options1 = new JPanel(new GridBagLayout());
		GridBagConstraints d = new GridBagConstraints();

		HUMAN.setPreferredSize(new Dimension(btnWidth,btnHeight));
		d.gridx = XLINE1;
		d.gridy = YLINE1;
		options1.add(HUMAN, d);
		d.gridx = XLINE2;
		d.gridy = YLINE1;

		AI.setPreferredSize(new Dimension(btnWidth,btnHeight));
		options1.add(AI, d);
		d.gridx = XLINE1;
		d.gridy = YLINE2;

		EASY.setPreferredSize(new Dimension(btnWidth,btnHeight));
		options1.add(EASY, d);
		d.gridx = XLINE2;
		d.gridy = YLINE2;

		HARD.setPreferredSize(new Dimension(btnWidth,btnHeight));
		options1.add(HARD, d);

		HARD.setVisible(false);
		EASY.setVisible(false);
		
		if (test || m_test) {
			System.out.println("PlayerSettings :: optionsPlayer1() END");
		}

		return options1;
	}

	/**
	 * Sets out everything on this panel using grid bag layout
	 * 
	 * \return the data on this panel
	 */
	private JPanel optionsPlayer2() {
		boolean test = false;
		if (test || m_test) {
            System.out.println("PlayerSettings :: optionsPlayer2() BEGIN");
            }
		int btnWidth = 100;
		int btnHeight = 20;
		JPanel options2 = new JPanel(new GridBagLayout());
		GridBagConstraints e = new GridBagConstraints();

		HUMAN2.setPreferredSize(new Dimension(btnWidth,btnHeight));
		e.gridx = XLINE1;
		e.gridy = YLINE1;
		options2.add(HUMAN2, e);
		e.gridx = XLINE2;
		e.gridy = YLINE1;

		AI2.setPreferredSize(new Dimension(btnWidth,btnHeight));
		options2.add(AI2, e);
		e.gridx = XLINE1;
		e.gridy = YLINE2;

		EASY2.setPreferredSize(new Dimension(btnWidth,btnHeight));
		options2.add(EASY2, e);
		e.gridx = XLINE2;
		e.gridy = YLINE2;

		HARD2.setPreferredSize(new Dimension(btnWidth,btnHeight));
		options2.add(HARD2, e);

		HARD2.setVisible(false);
		EASY2.setVisible(false);

		if (test || m_test) {
			System.out.println("PlayerSettings :: optionsPlayer2() END");
		}
		return options2;
	}

	/**
	 * Puts the buttons into groups so only one button in the group can be
	 * clicked e.g doesn't allow player 1 to be human and ai
	 */
	public void buttonGroups() {
		boolean test = false;
		if (test || m_test) {
            System.out.println("PlayerSettings :: buttonGroups() BEGIN");
            }

		ButtonGroup group1 = new ButtonGroup();
		group1.add(PLAYERCOLOUR_A1);
		group1.add(PLAYERCOLOUR_B1);

		ButtonGroup group4 = new ButtonGroup();
		group4.add(PLAYERCOLOUR_A2);
		group4.add(PLAYERCOLOUR_B2);

		ButtonGroup group5 = new ButtonGroup();
		group5.add(HUMAN);
		group5.add(AI);

		ButtonGroup group6 = new ButtonGroup();
		group6.add(HUMAN2);
		group6.add(AI2);

		ButtonGroup group7 = new ButtonGroup();
		group7.add(HARD);
		group7.add(EASY);

		ButtonGroup group8 = new ButtonGroup();
		group8.add(HARD2);
		group8.add(EASY2);
		
		if (test || m_test) {
			System.out.println("PlayerSettings :: buttonGroups() END");
		}
	}
	/** 
	 * Class to test the above methods
	 */
	public static void main(String args[]){
		
		Game game = new Othello();
		PlayerSettings playerS = new PlayerSettings(game, true );
		//Tests both getGame() and setGame
		if(playerS.getGame() == game){
			System.out.println("Test game successfully set");
		}
		
	}

	/**
	 * We use this class so that it can reach the buttons throughout the
	 * PlayerSettings class meaning we don't have to use a lot of get and set
	 * methods
	 * 
	 */
	class PlayersButtonListener implements ActionListener {
		/**
		 * This performs the actions 1 when certain buttons are clicked. Makes
		 * easy/hard options available/unavailable depending on whether user
		 * clicks ai/human Doesn't allow both player's to be one colour Start
		 * starts the game bringing forward the settings to the next screen
		 */
		public void actionPerformed(ActionEvent e) {
			boolean test = false;
			if (test || m_test) {
	            System.out.println("PlayersButtonListener :: "
	            		+ "actionPerformed() BEGIN");
	            }
			
			if (e.getSource() == HUMAN) {
				m_player1 = new Human(m_game);
				EASY.setVisible(false);
				HARD.setVisible(false);
			}
			if (e.getSource() == HUMAN2) {
				m_player2 = new Human(m_game);
				EASY2.setVisible(false);
				HARD2.setVisible(false);
			}
			if (e.getSource() == AI) {
				EASY.setVisible(true);
				HARD.setVisible(true);
				EASY.doClick();
			}
			if (e.getSource() == AI2) {
				EASY2.setVisible(true);
				HARD2.setVisible(true);
				EASY2.doClick();
			}
			if (e.getSource() == HARD) {
				if (ISOTHELLO) {
					m_player1 = new OthelloAI(m_game);
				} else {
					m_player1 = new ConnectFourAI(m_game);
				}
			}
			if (e.getSource() == EASY) {
				m_player1 = new AIEasy(m_game);
			}
			if (e.getSource() == EASY2) {
				m_player2 = new AIEasy(m_game);
			}
			if (e.getSource() == HARD2) {
				if (ISOTHELLO) {
					m_player2 = new OthelloAI(m_game);
				} else {
					m_player2 = new ConnectFourAI(m_game);
				}
			}
			pack();
			
			if (test || m_test) {
			System.out.println("PlayersButtonListener :: "
            		+ "actionPerformed() END");
			}
		}
		
	}

	/**
	 * We use this class so that it can reach the buttons throughout the
	 * PlayerSettings class meaning we don't have to use a lot of get and set
	 * methods
	 * 
	 */
	class ButtonListener implements ActionListener {

		/**
		 * This performs the actions 1 when certain buttons are clicked. Makes
		 * easy/hard options available/unavailable depending on whether user
		 * clicks ai/human Doesn't allow both player's to be one colour Start
		 * starts the game bringing forward the settings to the next screen
		 */
		public void actionPerformed(ActionEvent e) {
			boolean test = false;
			if (test || m_test) {
	            System.out.println("ButtonListener :: actionPerformed() BEGIN");
	            }

			if (e.getSource() == PLAYERCOLOUR_A1) {
				if (ISOTHELLO) {
					m_player1Color = Color.BLACK;
				} else {
					m_player1Color = Color.RED;
				}
				if (!PLAYERCOLOUR_B2.isSelected()) {
					PLAYERCOLOUR_B2.doClick();
				}
			}
			if (e.getSource() == PLAYERCOLOUR_B1) {
				if (ISOTHELLO) {
					m_player1Color = Color.WHITE;
				} else {
					m_player1Color = Color.YELLOW;
				}
				if (!PLAYERCOLOUR_A2.isSelected()) {
					PLAYERCOLOUR_A2.doClick();
				}
			}
			if (e.getSource() == PLAYERCOLOUR_A2) {
				if (ISOTHELLO) {
					m_player2Color = Color.BLACK;
				} else {
					m_player2Color = Color.RED;
				}
				if (!PLAYERCOLOUR_B1.isSelected()) {
					PLAYERCOLOUR_B1.doClick();
				}
			}
			if (e.getSource() == PLAYERCOLOUR_B2) {
				if (ISOTHELLO) {
					m_player2Color = Color.WHITE;
				} else {
					m_player2Color = Color.YELLOW;
				}
				if (!PLAYERCOLOUR_A1.isSelected()) {
					PLAYERCOLOUR_A1.doClick();
					}
				}
			pack();
			if (test || m_test) {
				System.out.println("ButtonListener :: "
	            		+ "actionPerformed() END");
				}
		}
	}
	
	/**
	 * We use this class so that it can reach the buttons throughout the
	 * PlayerSettings class meaning we don't have to use a lot of get and set
	 * methods
	 */
	class ControlButtonListener implements ActionListener{
		/**
		 * starts the game bringing forward the settings to the next screen,
		 * resume loads up the last saved game
		 */
		public void actionPerformed(ActionEvent e) {
			boolean test = false;
			if (test || m_test) {
	            System.out.println("ControlButtonListener :: "
	            		+ "actionPerformed() BEGIN");
	            }
			if (e.getSource() == STARTBUTTON) {
				if (PLAYERNAME1.getText().length()>20){
					PLAYERNAME1.setText(PLAYERNAME1.getText().substring(0, 20));
				}
				if (PLAYERNAME2.getText().length()>20){
					PLAYERNAME2.setText(PLAYERNAME2.getText().substring(0, 20));
				}
				m_player1.setPlayerName(PLAYERNAME1.getText());
				m_player2.setPlayerName(PLAYERNAME2.getText());
				m_player1.setPlayerColour(m_player1Color);
				m_player2.setPlayerColour(m_player2Color);
				m_game.setPlayer1(m_player1);
				System.out.println("PlayerSettings.ButtonListener::"
								+ "ActionPerformed() - Player 1 = "
								+ PLAYERNAME1.getText() + ":"
								+ m_player1.getPlayerColour());
				m_game.setPlayer2(m_player2);
				System.out.println("PlayerSettings.ButtonListener::"
								+ "ActionPerformed() - Player 2 = "
								+ PLAYERNAME2.getText() + ":"
								+ m_player2.getPlayerColour());
				setVisible(false);
				try {
					m_game.start();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}

			if (e.getSource() == RESUME_BUTTON) {
				if (ISOTHELLO) {
					OthelloLoader loader = new OthelloLoader(m_game);
					try {
						checkValid(loader);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				} else {
					ConnectFourLoader loader = new ConnectFourLoader(m_game);
					try {
						checkValid(loader);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			}
			pack();
			if (test || m_test) {
				System.out.println("ControlsButtonListener :: "
	            		+ "actionPerformed() END");
				}
		}

		/**
		 * Author Gavin Bailey 711036
		 * This method checks that the file that is being loaded in is
		 * correct and can be loaded up. 
		 * Outputs an error message if the file cannot be loaded else will 
		 * load up the save file
		 * \param l is the reference to the loader of the file
		 * \throws InterruptedException if file cannot be loaded
		 */
		public void checkValid(Loader l) throws InterruptedException {
			boolean test = false;
			if (test || m_test) {
	            System.out.println("ControlButtonListener :: "
	            		+ "checkValid() BEGIN");
	            }
			
			if (l.getValid()) {
				m_game.getGrid().setGrid(l.getGridArray());
				m_game.setPlayer1(l.getPlayer1());
				m_game.setPlayer2(l.getPlayer2());
				setVisible(false);
				m_game.resumeGame();
				getGame().startTimer(l.getTimer());
			} else {
				JOptionPane.showMessageDialog(player, "ERROR Loading File",
						"Load ERROR", JOptionPane.ERROR_MESSAGE);
				dispose();
				new GameSelecter();
			}
			if (test || m_test) {
				System.out.println("ControlsButtonListener :: "
	            		+ "checkValid() END");
				}
		}		
	}

	private final JRadioButton EASY = new JRadioButton("Easy");
	private final JRadioButton EASY2 = new JRadioButton("Easy");
	private final JRadioButton HARD = new JRadioButton("Hard");
	private final JRadioButton HARD2 = new JRadioButton("Hard");
	private final JRadioButton HUMAN = new JRadioButton("Human");
	private final JRadioButton HUMAN2 = new JRadioButton("Human");
	private final JRadioButton AI = new JRadioButton("AI");
	private final JRadioButton AI2 = new JRadioButton("AI");
	private final JButton STARTBUTTON = new JButton("Start");
	private final JButton RESUME_BUTTON = new JButton("Resume");
	private final TextField PLAYERNAME1 = new TextField("Player One");
	private final TextField PLAYERNAME2 = new TextField("Player Two");
	private final JRadioButton PLAYERCOLOUR_A1;
	private final JRadioButton PLAYERCOLOUR_B1;
	private final JRadioButton PLAYERCOLOUR_A2;
	private final JRadioButton PLAYERCOLOUR_B2;
	private Game m_game;
	private final int XLINE1 = 0;
	private final int XLINE2 = 1;
	private final int XLINE3 = 2;
	private final int XLINE4 = 3;
	private final int YLINE1 = 0;
	private final int YLINE2 = 1;
	private final int YLINE3 = 2;
	private final int YLINE4 = 3;
	private final boolean ISOTHELLO;
	private Container player;
	private Player m_player1;
	private Player m_player2;
	private Timer m_timer;
	private Color m_player1Color = null;
	private Color m_player2Color = null;
	private Boolean m_test = true;
}
