import java.awt.Color;
import java.util.Scanner;
/**
 * \\file -Loader.java 
 * \author - ADDME
 * \date -26th March 14
 * 
 * \see FileManager.java
 * \see OthelloLoader.java
 * \see ConnectFourLoader.java
 * 
 * \brief Class to handle loading data from files
 * 
 * This Class extends the FileManager class to implement the loading of data 
 * from files.
 */
public class Loader extends FileManager{
    /**
     * This method handles the loading of the grid file into the grid array in
     * FileManager.java
     * 
     * \return True on success
     */
    public boolean loadGrid() {
        boolean test = false;
        if (test || m_test) {
            System.out.println("Loader :: loadGrid() BEGIN");
        }
        
        m_hashString = "";
        initialiseGrid();
        m_hash = 0;
        m_valid = false;
        try {
            setFile(getGridFile());
            m_in = new Scanner(this.getFile()); // may throw exception
            m_in.useDelimiter(",");
            //try reading file
            try {
                //read the file
                for (int y = 0; y < getGridObj().getGridHeight(); ++y) {
                    for (int x = 0; x < getGridObj().getGridWidth(); ++x) {                        
                        if (m_in.hasNext()) {
                            String player = m_in.next();
                            
                            m_hashString += player + ",";
                            
                            if (player.equals(PLAYER_ONE)) {                                
                                setGridArray(x,y,Game.PlayerTurn.PLAYER1);
                            } else if (player.equals(PLAYER_TWO)) {                                
                                setGridArray(x,y,Game.PlayerTurn.PLAYER2);
                            } else if (player.equals(NONE)) {                                
                                setGridArray(x,y,Game.PlayerTurn.NONE);
                            } else if (player.equals(PLAYER1_AM)) {                                
                                setGridArray(x,y,Game.PlayerTurn.PLAYER1_AM);
                            } else if (player.equals(PLAYER2_AM)) {                                
                                setGridArray(x,y,Game.PlayerTurn.PLAYER2_AM);
                            } else {
                                m_allValid = false;
                            }
                            
                        }
                    }
                }
                
                m_hash = m_hashString.hashCode();
                m_in.reset();
                
                if (m_in.hasNext()) {
                    String b = m_in.next();
                    int readHash = 0;
                    readHash = Integer.parseInt(b.substring(1));
                    m_valid = (m_hash == readHash);
                }
            }finally {
                m_in.close();
            }                 
        } catch (Exception exc) {
            System.err.println("Error Reading File");
        }
        
        // if the file is valid then return true
        // if not set flag that file file is invalid, so it will not load
        if (m_valid) {
            if (test || m_test) {
                System.out.println("Loader :: loadGrid() END");
            }
            
            return true;
        } else {
            System.err.println("File Corrupt");
            m_allValid = false;
            
            if (test || m_test) {
                System.out.println("Loader :: loadGrid() END");
            }
            
            return false;
        }
    }
    
    /**
     * method to load the information of player1
     * 
     * \return True on success
     */
    public Boolean loadPlayer1() {
        boolean test = false;
        if (test || m_test) {
            System.out.println("Loader :: loadPlayer1() BEGIN");
        }
        
        if (loadPlayer(getPlayer1File())) {
            setPlayer1(createPlayerType(m_playerInfo[PLAYER_TYPE_INDEX]));
            
            getPlayer1().setPlayerName(m_playerInfo[PLAYER_NAME_INDEX]);
            getPlayer1().setPlayerColour(new Color(Integer.parseInt(
                                            m_playerInfo[PLAYER_COLOR_INDEX])));
            getPlayer1().setYourTurn(Boolean.parseBoolean(
                                              m_playerInfo[PLAYER_TURN_INDEX]));

            
            if (Boolean.parseBoolean(m_playerInfo[PLAYER_TURN_INDEX])) {
                getGame().setPlayerTurn(Game.PlayerTurn.PLAYER1);
            }
        } else {
            m_allValid = false;
            System.err.println("Error with player 1");
        }
        
        if (test || m_test) {
            System.out.println("Loader :: loadPlayer1() END");
        }
        
        return true;
    }
    
    /**
     * method to load the information of player2
     * 
     * \return True on success
     */
    public Boolean loadPlayer2() {
        boolean test = false;
        if (test || m_test) {
            System.out.println("Loader :: loadPlayer2() BEGIN");
        }
        
        if (loadPlayer(getPlayer2File())) {
            setPlayer2(createPlayerType(m_playerInfo[PLAYER_TYPE_INDEX]));
            
            getPlayer2().setPlayerName(m_playerInfo[PLAYER_NAME_INDEX]);
            getPlayer2().setPlayerColour(new Color(Integer.parseInt(
                                            m_playerInfo[PLAYER_COLOR_INDEX])));
            getPlayer2().setYourTurn(Boolean.parseBoolean(
                                              m_playerInfo[PLAYER_TURN_INDEX]));

            
            if (Boolean.parseBoolean(m_playerInfo[PLAYER_TURN_INDEX])) {
                getGame().setPlayerTurn(Game.PlayerTurn.PLAYER2);
            }
        } else {
            m_allValid = false;
            System.err.println("Error with player 2");
        }
        
        if (test || m_test) {
            System.out.println("Loader :: loadPlayer2() END");
        }
        
        return true;
    }
    
    /**
     * method to create the type of player to be loaded
     * 
     * \param String type - which is a string of what player type needs to 
     * be created
     * \return Player - depending on what type has been created
     */
    public Player createPlayerType(String type) {
        boolean test = false;
        if (test || m_test) {
            System.out.println("Loader :: createPlayerType() BEGIN");
        }
        
        switch (m_playerInfo[PLAYER_TYPE_INDEX]) {
            case HUMAN_PLAYER: return new Human(getGame());
            case EASY_AI_PLAYER: return new AIEasy(getGame());
            case HARD_OTHELLO_PLAYER: return new OthelloAI(getGame());
            case HARD_CONNECTFOUR_PLAYER: return new ConnectFourAI(getGame());
        }
        
        if (test || m_test) {
            System.out.println("Loader :: createPlayerType() END");
        }
        
        //needed this to shut up compiler
        return new Human(getGame());
    }
    
    /**
     * Method to load the player information from file into an array
     * 
     * \param String file - The file and its location to retrieve data from 
     * \return True on success
     */
    public Boolean loadPlayer(String file) {
        boolean test = false;
        if (test || m_test) {
            System.out.println("Loader :: loadPlayer() BEGIN");
        }
        
        m_valid = false;
        m_playerInfo = new String[PLAYER_INFO_SIZE];
        m_hashString = "";
        
        try {
            setFile(file);
            m_in = new Scanner(this.getFile()); // may throw exception
            m_in.useDelimiter(",");
            //try reading file
            try {
                //read the file
                for (int x = 0; x < m_playerInfo.length; ++x) {
                    if (m_in.hasNext()) {
                        m_playerInfo[x] = m_in.next();
                        m_hashString += m_playerInfo[x] + ",";
                    }
                }
                m_hash = m_hashString.hashCode();
                m_in.reset();
                
                if (m_in.hasNext()) {
                    String b = m_in.next();
                    int readHash = 0;
                    readHash = Integer.parseInt(b.substring(1));
                    m_valid = (m_hash == readHash);
                }
            }finally {
                m_in.close();
            }                 
        } catch (Exception exc) {
            System.err.println("Error Reading File");
        }
        
        if (test || m_test) {
            System.out.println("Loader :: loadPlayer() END");
        }
        
        return m_valid;
    }
    
    /**
     * Method to load the timer data from file 
     * 
     * \return True on success
     */
    public boolean loadTimer() {
        boolean test = false;
        if (test || m_test) {
            System.out.println("Loader :: loadTimer() BEGIN");
        }
        
        m_valid = false;
        m_hashString = "";
        m_timerInfo = new int[TIMER_INFO_SIZE];
        
        try {
            setFile(getTimerFile());
            m_in = new Scanner(this.getFile()); // may throw exception
            m_in.useDelimiter(",");
            //try reading file
            try {
                //read the file
                for (int x = 0; x < m_timerInfo.length; ++x) {
                    if (m_in.hasNext()) {
                        m_timerInfo[x] = Integer.parseInt(m_in.next());
                        m_hashString += m_timerInfo[x] + ",";
                    }
                }
                m_hash = m_hashString.hashCode();
                m_in.reset();
                
                if (m_in.hasNext()) {
                    String b = m_in.next();
                    int readHash = 0;
                    readHash = Integer.parseInt(b.substring(1));
                    m_valid = (m_hash == readHash);
                }
            }finally {
                m_in.close();
            }                 
        } catch (Exception exc) {
            System.err.println("Error Reading File");
        }
        if(m_valid) {
            setTimer(new Timer(getGame()));
            getTimer().setHours(m_timerInfo[HOURS]);
            getTimer().setMinutes(m_timerInfo[MINUTES]);
            getTimer().setSeconds(m_timerInfo[SECONDS]);
        } else {
            m_allValid = false;
        }
        
        if (test || m_test) {
            System.out.println("Loader :: loadTimer() END");
        }

        return m_valid;
    }
    
    /**
     * This method calls all load methods to load all of the data from files
     * 
     * \return True on success
     */
    public boolean loadAll() {
        boolean test = false;
        if (test || m_test) {
            System.out.println("Loader :: loadAll() BEGIN");
        }
        
        loadGrid();
        loadPlayer1();
        loadPlayer2();
        loadTimer();
        
        if (test || m_test) {
            System.out.println("Loader :: loadAll() END");
        }
        
        return true;
    }
    
    /**
     * accessor method for m_allValid 
     * 
     * \return Boolean m_allValid - a flag to indicate if all files loaded 
     * are valid
     */
    public Boolean getValid() {
        boolean test = false;
        if (test || m_test) {
            System.out.println("Loader :: loadAll() BEGIN");
        }
        if (test || m_test) {
            System.out.println("Loader :: loadAll() END");
        }
        
        return m_allValid;
    }
    
    /**
     * Test for load methods
     */
    public void loadTest() {
        boolean test = false;
        if (test || m_test) {
            System.out.println("Loader :: loadTest() BEGIN");
        }
        
        System.out.println(getClass().getSimpleName() + " :: loadGridTest: " 
                                                                  + loadGrid());
        System.out.println(getClass().getSimpleName() + " :: loadPlayer1Test: " 
                                                               + loadPlayer1());
        System.out.println(getClass().getSimpleName() + " :: LoadPlayer2Test: " 
                                                               + loadPlayer2());
        System.out.println(getClass().getSimpleName() + " :: saveTimerTest: " 
                                                                 + loadTimer());
        
        if (test || m_test) {
            System.out.println("Loader :: loadTest() END");
        }
    }
    
    /**
     * Main method to initiate tests
     */
    public static void main(String[] args) {
        ConnectFourLoader c4Saver = new ConnectFourLoader(new ConnectFour(),"");
        c4Saver.loadTest();
        
        OthelloLoader othelloSaver = new OthelloLoader(new Othello(), "");
        othelloSaver.loadTest();
    }
    
    /** store the entire string from file to create a hash from */
    private String m_hashString;
    /** store the hash created from m_hashString */
    private int m_hash;
    /** flag of an individual load */
    private boolean m_valid;
    /** flag if all loads are valid */
    private boolean m_allValid = true;
    /** scanner for reading from files */
    private Scanner m_in;
    /** array of player information */
    private String[] m_playerInfo;
    /** array of infor for timer */
    private int[] m_timerInfo;
    /** strings for checking grid spaces */
    private final String PLAYER_ONE = "PLAYER1";
    private final String PLAYER_TWO = "PLAYER2";
    private final String NONE = "NONE";
    private final String PLAYER1_AM = "PLAYER1_AM";
    private final String PLAYER2_AM = "PLAYER2_AM";
    /** indexes of the playerinfo array */
    private final int PLAYER_INFO_SIZE = 4;
    private final int PLAYER_TYPE_INDEX = 0;
    private final int PLAYER_NAME_INDEX = 1;
    private final int PLAYER_COLOR_INDEX = 2;
    private final int PLAYER_TURN_INDEX = 3;
    /** strings for checking player type */
    private final String HUMAN_PLAYER = "Human";
    private final String EASY_AI_PLAYER = "AIEasy";
    private final String HARD_OTHELLO_PLAYER = "OthelloAI";
    private final String HARD_CONNECTFOUR_PLAYER = "ConnectFourAI";
    /** ints to hold timer information */
    private final int TIMER_INFO_SIZE = 3;
    private final int HOURS = 0;
    private final int MINUTES = 1;
    private final int SECONDS = 2;
    /** Test variable */
    private boolean m_test = false;
}
