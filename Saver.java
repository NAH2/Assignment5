import java.io.PrintWriter;

/**
 * \\file -Saver.java 
 * \author -Gavin Bailey 711036
 * \date -26th March 14
 * 
 * \see FileManager.java
 * \see OthelloLoader.java
 * \see ConnectFourLoader.java
 * 
 * \brief Class to handle saving data to files
 * 
 * This Class extends the FileManager class to implement the saving of data to 
 * files.
 */
public class Saver extends FileManager{
    
    /**
     * Method to save the current state of the grid
     * 
     * \param data - created by Grid.java toString() method 
     * \return Boolaean true on success
     */
    public boolean saveGrid(String data) {
        boolean test = false;
        if (test || m_test) {
            System.out.println("Saver :: saveGrid() BEGIN");
        }
        
        boolean success = saveData(data, getGridFile());
        
        if (test || m_test) {
            System.out.println("Saver :: saveGrid() END");
        }
        
        return success;
    }
    
    /**
     * Method to save the current state of player 1
     * 
     * \param data - created by Player.java toString() method 
     */
    public boolean savePlayer1(String data) {
        boolean test = false;
        if (test || m_test) {
            System.out.println("Saver :: savePlayer1() BEGIN");
        }
        
        boolean success = saveData(data, getPlayer1File());
        
        if (test || m_test) {
            System.out.println("Saver :: savePlayer1() END");
        }
        
        return success;
    }
    
    /**
     * Method to save the current state of the player 2
     * 
     * \param data - created by Player.java toString() method 
     */
    public boolean savePlayer2(String data) {
        boolean test = false;
        if (test || m_test) {
            System.out.println("Saver :: savePlayer2() BEGIN");
        }
        
        boolean success = saveData(data, getPlayer2File());
        
        if (test || m_test) {
            System.out.println("Saver :: savePlayer2() END");
        }
        
        return success;
    }
    
    /**
     * Method to save the current state of the timer
     * 
     * \param data - created by Timer.java toString() method 
     */
    public boolean saveTimer(String data) {
        boolean test = false;
        if (test || m_test) {
            System.out.println("Saver :: saveTimer() BEGIN");
        }
        
        boolean success = saveData(data, getTimerFile());
        
        if (test || m_test) {
            System.out.println("Saver :: saveTimer() END");
        }
        
        return success;
    }
    
    /**
     * Method to save the data to file
     * 
     * \param data - sent from save methods
     * \param dataFile - the file address of where to save data 
     */
    public boolean saveData(String data,String dataFile) {
        boolean test = false;
        if (test || m_test) {
            System.out.println("Saver :: saveData() BEGIN");
        }
        
        try {
            setFile(dataFile);
            PrintWriter out = new PrintWriter(this.getFile());
            //try reading file
            try {
                out.println(data + data.hashCode());
            }finally {
                out.close();
            }                 
        } catch (Exception exc) {
            System.err.println("Write Error");
            return false;
        }
        
        if (test || m_test) {
            System.out.println("Saver :: saveData() END");
        }
        
        return true;
    }
    
    /**
     * Test for save methods
     */
    public void saveTest() {
        boolean test = false;
        if (test || m_test) {
            System.out.println("Saver :: saveTest() BEGIN");
        }
        
        if (getClass().getSimpleName().equals("OthelloSaver")) {
            System.out.println(getClass().getSimpleName() + " :: saveGridTest: " 
                                              + saveGrid(OTH_GRID_TEST_STRING));
        } else {
            System.out.println(getClass().getSimpleName() + " :: saveGridTest: " 
                                               + saveGrid(C4_GRID_TEST_STRING));
        }
        System.out.println(getClass().getSimpleName() + " :: savePlayer1Test: " 
                                             + savePlayer1(PLAYER_TEST_STRING));
        System.out.println(getClass().getSimpleName() + " :: savePlayer2Test: " 
                                             + savePlayer2(PLAYER_TEST_STRING));
        System.out.println(getClass().getSimpleName() + " :: saveTimerTest: " 
                                                + saveTimer(TIMER_TEST_STRING));
        
        if (test || m_test) {
            System.out.println("Saver :: saveTest() END");
        }
    }
    
    /**
     * Main method to initiate tests
     */
    public static void main(String[] args) {
        ConnectFourSaver c4Saver = new ConnectFourSaver();
        c4Saver.saveTest();
        
        OthelloSaver othelloSaver = new OthelloSaver();
        othelloSaver.saveTest();
    }
    
    /** Strings to save in tests */
    private final String OTH_GRID_TEST_STRING = "NONE,NONE,NONE,NONE,NONE,NONE,"
            + "NONE,NONE,NONE,NONE,PLAYER1,PLAYER2,PLAYER1_AM,PLAYER1,NONE,NONE"
            + ",NONE,PLAYER2,PLAYER1_AM,PLAYER2,NONE,PLAYER1,NONE,NONE,"
            + "PLAYER1_AM,PLAYER2,PLAYER2,PLAYER2,PLAYER1,PLAYER1,NONE,NONE,"
            + "NONE,PLAYER2,PLAYER1_AM,PLAYER2,PLAYER1,NONE,NONE,NONE,NONE,NONE"
            + ",PLAYER1_AM,NONE,NONE,NONE,NONE,NONE,NONE,NONE,NONE,NONE,NONE,"
            + "NONE,NONE,NONE,NONE,NONE,NONE,NONE,NONE,NONE,NONE,NONE,";
    
    private final String C4_GRID_TEST_STRING = "NONE,NONE,NONE,NONE,NONE,NONE,"
            + "NONE,NONE,NONE,NONE,NONE,NONE,NONE,NONE,NONE,NONE,NONE,NONE,NONE"
            + ",NONE,NONE,NONE,NONE,NONE,PLAYER1,NONE,NONE,NONE,NONE,NONE,NONE,"
            + "NONE,NONE,NONE,PLAYER2,NONE,NONE,NONE,NONE,NONE,NONE,NONE,NONE,"
            + "NONE,PLAYER1,NONE,NONE,NONE,NONE,NONE,NONE,NONE,NONE,PLAYER1,"
            + "PLAYER2,PLAYER2,NONE,NONE,NONE,NONE,NONE,PLAYER2,NONE,PLAYER1,"
            + "PLAYER2,PLAYER1,NONE,NONE,NONE,NONE,";
    
    private final String PLAYER_TEST_STRING ="Human,Frank,-256,true,";

    private final String TIMER_TEST_STRING ="0,0,19,";

    /** test variable */
    private boolean m_test = false;

}