/**
 * \\file - OthelloSaver.java
 * \author 
 * \date 26th March 14
 * 
 * \see 
 * 
 * \brief 
 *
 * The class 
 */
public class OthelloSaver extends Saver{
   
	/**
	 * Accessor 
	 * \param 
	 */
    public OthelloSaver(Game g) {
        setGame(g);
        setPath(OTHELLO_PATH);
        
        createDirs();
    }
    
    /**
	 * Accessor 
	 * \param 
	 */
    public OthelloSaver() {
        setPath(OTHELLO_TEST_PATH);
        
        createDirs();
    }
	/**the  */
    private final String OTHELLO_PATH = "saveData/othello/";
	/**the  */
    private final String OTHELLO_TEST_PATH = "saveDataTest/othello/";
}
