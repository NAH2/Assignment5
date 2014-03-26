import java.io.PrintWriter;

/**
 * \\file -Saver.java 
 * \author - ADDME
 * \date -26th March 14
 * 
 * \see FileManager.java
 * 
 * \brief Class to handle saving data to files
 * 
 * This Class extends the FileManager class to implement the saving of data to 
 * files.
 */
public class Saver extends FileManager{
    
    /**
     * Method only used in testing.
     * Accessor method for m_saveTest.
     * 
     * @return String m_saveTest to test save methods
     */
    public String getSaveTestString() {
        return m_saveTest;
    }
    
    /**
     * Method to save the current state of the grid
     * 
     * \param String data - created by Grid.java toString() method 
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
     * \param String data - created by Player.java toString() method 
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
     * \param String data - created by Player.java toString() method 
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
     * \param String data - created by Timer.java toString() method 
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
     * \param String data - sent from save methods
     * \param String dataFile - the file address of where to save data 
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
        System.out.println(getClass().getSimpleName() + " :: saveGrid: " 
                                               + saveGrid(getSaveTestString()));
        System.out.println(getClass().getSimpleName() + " :: savePlayer1: " 
                                            + savePlayer1(getSaveTestString()));
        System.out.println(getClass().getSimpleName() + " :: savePlayer2: " 
                                            + savePlayer2(getSaveTestString()));
        System.out.println(getClass().getSimpleName() + " :: saveTimer: " 
                                              + saveTimer(getSaveTestString()));
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
    
    /** String to save in tests */
    private final String m_saveTest = "a,b,c,d,e,f,g,e,f,g,";
    /** test variable */
    private boolean m_test = false;

}