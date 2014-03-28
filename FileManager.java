import java.io.File;
/**
 * \\file -FileManager.java 
 * \author -Tyrone Bramwell
 * \date -26th March 14
 * 
 * \brief Class to handle the locations of individual files
 * 
 * This Class controls the paths of the individual save files
 */
public class FileManager {    
    /**
     * Accessor method of TIMER_FILE
     * 
     * \return String TIMER_FILE - loacation of timer save file
     */
    public String getTimerFile() {
    	boolean test = false;
		if (test || m_test) {
			System.out.println("FileManager :: getTimerFile() BEGIN");
		}

		
		if (test || m_test) {
			System.out.println("FileManager :: getTimerFile() END");
		}
        return TIMER_FILE;
    }
    
    /**
     * Accessor method to set individual index of m_gridArray
     * 
     * \param x - x index of m_gridArray
     * \param y - y index of m_gridArray
     * \param pt - to be stored in the m_gridArray
     * \return true on success
     */
    public Boolean setGridArray(int x, int y, Game.PlayerTurn pt) {
    	boolean test = false;
		if (test || m_test) {
			System.out.println("FileManager :: setGridArray() BEGIN");
		}
        m_gridArray[x][y] = pt;
        
        if (test || m_test) {
			System.out.println("FileManager :: setGridArray() END");
		}
        return true;
    }
    
    /**
     * Accessor method to get m_gridArray
     * 
     * \return Game.Playerturn m_gridArray
     */
    public Game.PlayerTurn[][] getGridArray() {
    	boolean test = false;
		if (test || m_test) {
			System.out.println("FileManager :: getGridArray() BEGIN");
		}
		
		if (test || m_test) {
			System.out.println("FileManager :: getGridArray() END");
		}
        return m_gridArray;
    }
    
    /**
     * Accessor method to set m_game
     * 
     * \param g - reference to current game
     * \return true on success
     */
    public Boolean setGame(Game g) {
    	boolean test = false;
		if (test || m_test) {
			System.out.println("FileManager :: setGame() BEGIN");
		}
        m_game = g;
        setGridObj();
        
        if (test || m_test) {
			System.out.println("FileManager :: setGame() END");
		}
        
        return true;
    }
    
    /**
     * Accessor method to get game reference
     * 
     * \return Game m_game - reference to game
     */
    public Game getGame() {
    	boolean test = false;
		if (test || m_test) {
			System.out.println("FileManager :: getGame() BEGIN");
		}
		
		if (test || m_test) {
			System.out.println("FileManager :: getGame() END");
		}
        return m_game;
    }
    
    /**
     * Accessor method to set path
     * 
     * \param p - the location of files to be saved and loaded
     * \return true on success
     */
    public boolean setPath(String p) {
    	boolean test = false;
		if (test || m_test) {
			System.out.println("FileManager :: setPath() BEGIN");
		}
        m_path = p;
        
        if (test || m_test) {
			System.out.println("FileManager :: setPath() END");
		}
        return true;
    }
    
    /**
     * Accessor method to get path
     * 
     * \return String path
     */
    public String getPath() {
    	boolean test = false;
		if (test || m_test) {
			System.out.println("FileManager :: getPath() BEGIN");
		}
		
		if (test || m_test) {
			System.out.println("FileManager :: getPath() END");
		}
        return m_path;
    }
    
    /**
     * Accessor method to set inFile
     * 
     * \param file - current file to work on
     * \return true on success
     */
    public boolean setFile(String file) {
    	boolean test = false;
		if (test || m_test) {
			System.out.println("FileManager :: setFile() BEGIN");
		}
        m_inFile = new File(this.m_path + file);
        
        if (test || m_test) {
			System.out.println("FileManager :: setFile() END");
		}
        return true;
    }
    
    /**
     * Accessor method to get inFile
     * 
     * \return File inFile
     */
    public File getFile() {
    	boolean test = false;
		if (test || m_test) {
			System.out.println("FileManager :: getFile() BEGIN");
		}
		
		if (test || m_test) {
			System.out.println("FileManager :: getFile() END");
		}
        return m_inFile;
    }
    
    /**
     * Accessor method to get grid file location
     * 
     * \return String GRID_FILE - location of grid save
     */
    public String getGridFile() {
    	boolean test = false;
		if (test || m_test) {
			System.out.println("FileManager :: getGridFile() BEGIN");
		}
		
		if (test || m_test) {
			System.out.println("FileManager :: getGridFile() END");
		}
        return GRID_FILE;
    }
    
    /**
     * Accessor method to get player1 file location
     * 
     * \return String PLAYER1_FILE - location of player1 save
     */
    public String getPlayer1File() {
    	boolean test = false;
		if (test || m_test) {
			System.out.println("FileManager :: getPlayer2File() BEGIN");
		}
		
		if (test || m_test) {
			System.out.println("FileManager :: getPlayer2File() END");
		}
        return PLAYER1_FILE;
    }
    
    /**
     * Accessor method to get player2 file location
     * 
     * \return PLAYER2_FILE - location of player2 save
     */
    public String getPlayer2File() {
    	boolean test = false;
		if (test || m_test) {
			System.out.println("FileManager :: getPlayer2File() BEGIN");
		}
		
		if (test || m_test) {
			System.out.println("FileManager :: getPlayer2File() END");
		}
        return PLAYER2_FILE;
    }
    
    /**
     * Accessor method to set m_grid
     * 
     * \return true on success
     */
    public boolean setGridObj() {
    	boolean test = false;
		if (test || m_test) {
			System.out.println("FileManager :: setGridObj() BEGIN");
		}
		
		
        m_gridObj = m_game.getGrid();
        
        
        if (test || m_test) {
			System.out.println("FileManager :: setGridObj() END");
		}
        return true;
    }
    
    /**
     * Accessor method to get reference to m_gridObj
     * 
     * \return m_gridObj - reference to the grid object
     */
    public Grid getGridObj() {
    	boolean test = false;
		if (test || m_test) {
			System.out.println("FileManager :: getGridObj() BEGIN");
		}
		
		if (test || m_test) {
			System.out.println("FileManager :: getGridObj() END");
		}
        return m_gridObj;
    }
    
    /**
     * Accessor method to set player1
     * 
     * \param p - player object
     * \return true on success
     */
    public boolean setPlayer1(Player p) {
    	boolean test = false;
		if (test || m_test) {
			System.out.println("FileManager :: setPlayer1() BEGIN");
		}
        m_player1 = p;
        
        
        if (test || m_test) {
			System.out.println("FileManager :: setPlayer1() END");
		}
        return true;
    }
    
    /**
     * Accessor method to set player2
     * 
     * \param p - player object
     * \return true on success
     */
    public boolean setPlayer2(Player p) {
    	boolean test = false;
		if (test || m_test) {
			System.out.println("FileManager :: setPlayer2() BEGIN");
		}
        m_player2 = p;
        
        
        if (test || m_test) {
			System.out.println("FileManager :: setPlayer2() END");
		}
        return true;
    }
    
    /**
     * Accessor method to get player1
     * 
     * \return m_player1 - player1 loaded from file
     */
    public Player getPlayer1() {
    	boolean test = false;
		if (test || m_test) {
			System.out.println("FileManager :: getPlayer1() BEGIN");
		}
		
		if (test || m_test) {
			System.out.println("FileManager :: getPlayer1() END");
		}
        return m_player1;
    }
    
    /**
     * Accessor method to get player2
     * 
     * \return m_player2 - player2 loaded from file
     */
    public Player getPlayer2() {
    	boolean test = false;
		if (test || m_test) {
			System.out.println("FileManager :: getPlayer2() BEGIN");
		}
		
		if (test || m_test) {
			System.out.println("FileManager :: getPlayer2() END");
		}
        return m_player2;
    }
    
    /**
     * Accessor method to set m_timer
     * 
     * \param t - timer loaded from file
     * \return true on success
     */
    public boolean setTimer(Timer t) {
    	boolean test = false;
		if (test || m_test) {
			System.out.println("FileManager :: setTimer() BEGIN");
		}
        m_timer = t;
        
        
        if (test || m_test) {
			System.out.println("FileManager :: setTimer() END");
		}
        return true;
    }
    
    /**
     * Accessor method to get m_timer
     * 
     * \return m_timer - timer loaded from file
     */
    public Timer getTimer() {
    	boolean test = false;
		if (test || m_test) {
			System.out.println("FileManager :: getTimer() BEGIN");
		}
		
		if (test || m_test) {
			System.out.println("FileManager :: getTimer() END");
		}
        return m_timer;
    }
    
    /**
     * method to initialise the grid array to the correct size 
     * 
     * \return true on success
     */
    public Boolean initialiseGrid() {
    	boolean test = false;
		if (test || m_test) {
			System.out.println("FileManager :: initialiseGrid() BEGIN");
		}
        m_gridArray = new Game.PlayerTurn[m_gridObj.getGridWidth()][m_gridObj.getGridHeight()];
        
        
        if (test || m_test) {
			System.out.println("FileManager :: initialiseGrid() END");
		}
        return true;
    }
    
    /**
     * method to create the required directories to save the files
     * 
     * \return true on success
     */
    public boolean createDirs() {
    	boolean test = false;
		if (test || m_test) {
			System.out.println("FileManager :: createDirs() BEGIN");
		}
        new File(getPath()).mkdirs();
        
        
        if (test || m_test) {
			System.out.println("FileManager :: createDirs() END");
		}
        return true;
    }
    
    /** String to hold the path of files */
    private String m_path = "";
    /** Store the current file */
    private File m_inFile;
    /** Store reference to grid object */
    private Grid m_gridObj;
    /** Strings of file names */
    private final String GRID_FILE = "grid.txt";
    private final String PLAYER1_FILE = "player1.txt";
    private final String PLAYER2_FILE = "player2.txt";
    private final String TIMER_FILE = "timer.txt";
    /** Array to be created for grid */
    private Game.PlayerTurn[][] m_gridArray;
    /** player objects to be poaded from file */
    private Player m_player1;
    private Player m_player2;
    /** timer object to be loaded from file */
    private Timer m_timer;
    /** reference to the game object */
    private Game m_game;
    /**Test boolean for testing*/
    private boolean m_test = false;
}
