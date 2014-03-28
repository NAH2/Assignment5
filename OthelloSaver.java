/**
 * \\file -OthelloSaver.java 
 * \author -Gavin Bailey 711036
 * \date -26th March 14
 * 
 * \see FileManager.java
 * \see Saver.java
 * 
 * \brief Class to set file paths for the Saving of Othello
 * 
 * This Class extends the Saver class to and controls the path of the save files
 */
public class OthelloSaver extends Saver{
    /**
     * Constructor to initiate the saving of the files
     * 
     * \param g - A reference to the Game to be saved
     */
    public OthelloSaver(Game g) {
        boolean test = false;
        if (test || m_test) {
            System.out.println("OthelloSaver :: OthelloSaver() BEGIN");
        }
        
        setGame(g);
        setPath(OTHELLO_PATH);
        createDirs();
        
        if (test || m_test) {
            System.out.println("OthelloSaver :: OthelloSaver() END");
        }
    }
    
    /**
     * Test constructor
     */
    public OthelloSaver() {
        boolean test = false;
        if (test || m_test) {
            System.out.println("OthelloSaver :: OthelloSaver() BEGIN");
        }
        
        setPath(OTHELLO_TEST_PATH);
        createDirs();
        
        if (test || m_test) {
            System.out.println("OthelloSaver :: OthelloSaver() END");
        }
    }
    
    /** file paths to load files */
    private final String OTHELLO_PATH = "saveData/othello/";
    /** file path to test loading */
    private final String OTHELLO_TEST_PATH = "saveDataTest/othello/";
    /** Test variable */
    private boolean m_test = false;
}
