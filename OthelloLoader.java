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
public class OthelloLoader extends Loader{

    /**
	 * Accessor 
	 * \param 
	 */
    public OthelloLoader(Game g) {
        setGame(g);
        setPath(OTHELLO_PATH);
        
        loadAll();
    }
    
    /**
	 * Accessor 
	 * \param 
	 */
    public OthelloLoader(Game g, String s) {
        setGame(g);
        setPath(OTHELLO_TEST_PATH);
        
    }
	/**the  */
    private final String OTHELLO_PATH = "saveData/othello/";
	/**the  */
    private final String OTHELLO_TEST_PATH = "saveDataTest/othello/";
}
