/**
 *  @file	PlayerSettings.Java
 * 	@author	Callum Hazelton
 * 	@date	14/02/2014
 * 	@brief Allows you to change settings of player 1 and player 2
 * 
 * Change the player names, player colours, whether players are human/ai and difficulty of ai
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class PlayerSettings  extends JFrame{
	
	
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
	private final int XLINE1 =0;
	private final int XLINE2 =1;
	private final int XLINE3 =2;
	private final int XLINE4 =3;
	private final int YLINE1 =0;
	private final int YLINE2 =1;
	private final int YLINE3 =2;
	private final int YLINE4 =3;
	private final boolean ISOTHELLO;
	private Container player;

	/*
	 * Sets the game class with which player settings is to send its player data
	 * @param the instance of the game class
	 */
	public void setGame(Game game){
		m_game = game;
	}
	/**
	 * Returns the game class which player settings is to send its player data
	 * @return pointer to game class
	 */
	public Game getGame(){
		return m_game;
	}
	/**
	 * Runs methods inside
	 * @param game instance of game class
	 * @param isOthello boolean indicating whether or not to limit player colour
	 * choices in othello.
	 */
	public PlayerSettings(Game game, boolean isOthello){
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
		PLAYERCOLOUR_A1.doClick();
		HUMAN.doClick();
		HUMAN2.doClick();
		
		pack();
		setLocationRelativeTo(null);
	}
	/**
	 * Adds listeners to react when buttons are pressed
	 */
	private void listeners() {
		ButtonListener buttonListener = new ButtonListener();
		EASY.addActionListener(buttonListener);
		EASY2.addActionListener(buttonListener);
		HARD.addActionListener(buttonListener);
		HARD2.addActionListener(buttonListener);
		HUMAN.addActionListener(buttonListener);
		HUMAN2.addActionListener(buttonListener);
		AI.addActionListener(buttonListener);
		AI2.addActionListener(buttonListener);
		PLAYERCOLOUR_A1.addActionListener(buttonListener);
		PLAYERCOLOUR_B1.addActionListener(buttonListener);
		PLAYERCOLOUR_A2.addActionListener(buttonListener);
		PLAYERCOLOUR_B2.addActionListener(buttonListener);
		STARTBUTTON.addActionListener(buttonListener);
	    RESUME_BUTTON.addActionListener(buttonListener);

	}
/**
 * Sets out the main window and where the panels are placed on it
 */
	private void windowInitialise() {
		GridBagConstraints a = new GridBagConstraints();
		
		player = getContentPane();
		player.setLayout(new GridBagLayout());
		
		a.ipadx = 10;
		a.ipady = 10;
		a.gridx=XLINE1;
		a.gridy=YLINE1;
		player.add(player1Panel(),a);
		
		a.gridx=XLINE2;
		a.gridy=YLINE1;
		player.add(player2Panel(),a);
		
		a.gridx = XLINE1;
		a.gridy = YLINE2;
		//a.gridwidth = GridBagConstraints.REMAINDER;
		a.fill = GridBagConstraints.HORIZONTAL;
		
		player.add(STARTBUTTON,a);
		a.gridx = XLINE2;
        a.gridy = YLINE2;
		player.add(RESUME_BUTTON,a);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLocationRelativeTo(null);
		setVisible(true);
	}
	/**
	 * Sets out everything on this panel using grid bag layout
	 * @return the data on this panel
	 */
	private JPanel player1Panel() {
		JPanel player1Panel = new JPanel(new GridBagLayout());
		GridBagConstraints b = new GridBagConstraints();
		player1Panel.setBorder(BorderFactory.createEtchedBorder());
		
		b.gridx=XLINE2;
		b.gridy=YLINE1;
		JLabel p1 = new JLabel("Player 1");
		player1Panel.add(p1,b);
		
		b.gridx=XLINE1;
		b.gridy=YLINE2;
		JLabel pName1 = new JLabel("Player Name");
		player1Panel.add(pName1,b);
		
		PLAYERNAME1.setPreferredSize(new Dimension(100,20));
		b.gridx=XLINE2;
		b.gridy=YLINE2;
		player1Panel.add(PLAYERNAME1,b);
		
		b.gridx = XLINE1;
		b.gridy = YLINE3;
		JLabel pColour1 = new JLabel("Player Colour");
		player1Panel.add(pColour1,b);
		
		b.gridx=XLINE2;
		b.gridy=YLINE3;
		
		player1Panel.add(PLAYERCOLOUR_A1,b);
		b.gridx=XLINE3;
		b.gridy=YLINE3;
		
		player1Panel.add(PLAYERCOLOUR_B1,b);
		
		b.gridx=XLINE2;
		b.gridy=YLINE4;
		player1Panel.add(optionsPlayer1(),b);
		
		return player1Panel;
	}
	/**
	 * Sets out everything on this panel using grid bag layout
	 * @return the data on this panel
	 */
	private JPanel player2Panel() {
		JPanel player2Panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		player2Panel.setBorder(BorderFactory.createEtchedBorder());

		c.gridx=XLINE2;
		c.gridy=YLINE1;
		JLabel p2 = new JLabel("Player 2");
		player2Panel.add(p2,c);
		
		c.gridx = XLINE1;
		c.gridy = YLINE2;
		JLabel pName2 = new JLabel("Player Name");
		player2Panel.add(pName2,c);
		
		PLAYERNAME2.setPreferredSize(new Dimension(100,20));
		c.gridx=XLINE2;
		c.gridy=YLINE2;
		player2Panel.add(PLAYERNAME2,c);
		
		c.gridx=XLINE1;
		c.gridy=YLINE3;
		JLabel pColour2 = new JLabel("Player Colour");
		player2Panel.add(pColour2,c);
		
		c.gridx=XLINE2;
		c.gridy=YLINE3;
		
		player2Panel.add(PLAYERCOLOUR_A2,c);
		c.gridx=XLINE3;
		c.gridy=YLINE3;
		
		player2Panel.add(PLAYERCOLOUR_B2,c);
		
		c.gridx=XLINE2;
		c.gridy=YLINE4;
		player2Panel.add(optionsPlayer2(),c);
		
		return player2Panel;
	}
	/**
	 * Sets out everything on this panel using grid bag layout
	 * @return the data on this panel
	 */
	private JPanel optionsPlayer1(){
		JPanel options1 = new JPanel(new GridBagLayout());
		GridBagConstraints d = new GridBagConstraints();
		
		HUMAN.setPreferredSize(new Dimension(70,20));
		d.gridx=XLINE1;
		d.gridy=YLINE1;
		options1.add(HUMAN,d);
		d.gridx=XLINE2;
		d.gridy=YLINE1;
		
		AI.setPreferredSize(new Dimension(70,20));
		options1.add(AI,d);
		d.gridx=XLINE1;
		d.gridy=YLINE2;
		
		EASY.setPreferredSize(new Dimension(70,20));
		options1.add(EASY,d);
		d.gridx=XLINE2;
		d.gridy=YLINE2;
		
		HARD.setPreferredSize(new Dimension(70,20));
		options1.add(HARD,d);

		HARD.setVisible(false);
		EASY.setVisible(false);
		
		return options1;
	}
	/**
	 * Sets out everything on this panel using grid bag layout
	 * @return the data on this panel
	 */
	private JPanel optionsPlayer2() {
		JPanel options2 = new JPanel(new GridBagLayout());
		GridBagConstraints e = new GridBagConstraints();
		
		
		HUMAN2.setPreferredSize(new Dimension(70,20));
		e.gridx=XLINE1;
		e.gridy=YLINE1;
		options2.add(HUMAN2,e);
		e.gridx=XLINE2;
		e.gridy=YLINE1;
		
		AI2.setPreferredSize(new Dimension(70,20));
		options2.add(AI2,e);
		e.gridx=XLINE1;
		e.gridy=YLINE2;
		
		EASY2.setPreferredSize(new Dimension(70,20));
		options2.add(EASY2,e);
		e.gridx=XLINE2;
		e.gridy=YLINE2;
		
		HARD2.setPreferredSize(new Dimension(70,20));
		options2.add(HARD2,e);
		

		HARD2.setVisible(false);
		EASY2.setVisible(false);
		
		return options2;
	}
	/**
	 * Puts the buttons into groups so only one button in the group can be clicked
	 * e.g doesn't allow player 1 to be human and ai
	 */
	public void buttonGroups(){

		ButtonGroup group1= new ButtonGroup();
		group1.add(PLAYERCOLOUR_A1);
		group1.add(PLAYERCOLOUR_B1);
		
		ButtonGroup group4= new ButtonGroup();
		group4.add(PLAYERCOLOUR_A2);
		group4.add(PLAYERCOLOUR_B2);
		
		ButtonGroup group5= new ButtonGroup();
		group5.add(HUMAN);
		group5.add(AI);
		
		ButtonGroup group6= new ButtonGroup();
		group6.add(HUMAN2);
		group6.add(AI2);
		
		ButtonGroup group7= new ButtonGroup();
		group7.add(HARD);
		group7.add(EASY);
		
		ButtonGroup group8= new ButtonGroup();
		group8.add(HARD2);
		group8.add(EASY2);
	}
	/**
	 * We use this class so that it can reach the buttons throughout the PlayerSettings class
	 * meaning we don't have to use a lot of get and set methods
	 *
	 */
	class ButtonListener implements ActionListener {

		private Player player1;
		private Player player2;
		private Color player1Color = null;
		private Color player2Color = null;
		/**
		 * This performs the actions 1 when certain buttons are clicked.
		 * Makes easy/hard options available/unavailable depending on whether user clicks ai/human
		 * Doesn't allow both player's to be one colour
		 * Start starts the game bringing forward the settings to the next screen
		 */
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == HUMAN) {
				player1 = new Human(m_game);
				EASY.setVisible(false);
				HARD.setVisible(false);
			} else if(e.getSource() == HUMAN2) {
				player2 = new Human(m_game);
				EASY2.setVisible(false);
				HARD2.setVisible(false);
			} else if(e.getSource() == AI) {
				EASY.setVisible(true);
				HARD.setVisible(true);
			} else if(e.getSource() == AI2) {
				EASY2.setVisible(true);
				HARD2.setVisible(true);
			} else if(e.getSource() == PLAYERCOLOUR_A1) {
				if(ISOTHELLO) {
					player1Color = Color.BLACK;
				} else {
					player1Color = Color.RED;
				}
				if(!PLAYERCOLOUR_B2.isSelected()) {
					PLAYERCOLOUR_B2.doClick();
				}
			} else if(e.getSource() == PLAYERCOLOUR_B1) {
				if(ISOTHELLO) {
					player1Color = Color.WHITE;
				} else {
					player1Color = Color.YELLOW;
				}
				if(!PLAYERCOLOUR_A2.isSelected()) {
					PLAYERCOLOUR_A2.doClick();
				}
			} else if(e.getSource() == PLAYERCOLOUR_A2) {
				if(ISOTHELLO) {
					player2Color = Color.BLACK;
				} else {
					player2Color = Color.RED;
				}
				if(!PLAYERCOLOUR_B1.isSelected()) {
				PLAYERCOLOUR_B1.doClick();
				}
			} else if(e.getSource() == PLAYERCOLOUR_B2) {
				if(ISOTHELLO) {
					player2Color = Color.WHITE;
				} else {
					player2Color = Color.YELLOW;
				}
				if(!PLAYERCOLOUR_A1.isSelected()) {
				PLAYERCOLOUR_A1.doClick();
				}
			/*
			} else if(e.getSource() == hard) {
				player1 = new Hard();
			} else if(e.getSource() == hard2) {
				player2 = new Hard();
			} else if(e.getSource() == easy) {
				player1 = new Easy();
			} else if(e.getSource() == easy2) {
				player2 = new Easy();
			*/
			} else if(e.getSource() == STARTBUTTON) {
				player1.setPlayerName(PLAYERNAME1.getText());
				player2.setPlayerName(PLAYERNAME2.getText());
				player1.setPlayerColour(player1Color);
				player2.setPlayerColour(player2Color);
				m_game.setPlayer1(player1);
				System.out.println("PlayerSettings.ButtonListener::ActionPerformed() - Player 1 = " + PLAYERNAME1.getText() + ":" + player1.getPlayerColour());
				m_game.setPlayer2(player2);
				System.out.println("PlayerSettings.ButtonListener::ActionPerformed() - Player 2 = " + PLAYERNAME2.getText() + ":" + player2.getPlayerColour());
				setVisible(false);
				m_game.start();
			}
			
			if (e.getSource() == RESUME_BUTTON) {
			    
                if (ISOTHELLO) {
                    OthelloLoader loader = new OthelloLoader(m_game);
                    loader.loadGrid();
                    player1 = loader.loadPlayer1(player1);
                    player2 = loader.loadPlayer2(player2);
                    checkValid(loader);
                } else {
                    ConnectFourLoader loader = new ConnectFourLoader(m_game);
                    loader.loadGrid();
                    player1 = loader.loadPlayer1(player1);
                    player2 = loader.loadPlayer2(player2);
                    checkValid(loader);
                }
			}
		}
		public void checkValid(Loader l) {
		    if (l.getValid()) {
                getGame().getGrid().setGrid(l.getGridArray());
                m_game.setPlayer1(player1);
                m_game.setPlayer2(player2);
                m_game.setScores();
                setVisible(false);
                m_game.resumeGame();
            }else {
                JOptionPane.showMessageDialog(player, "ERROR Laoding File",
                        "Load ERROR",JOptionPane.ERROR_MESSAGE);
                dispose();
                new ChooseGame();
            }
		}
	}
}
