
public class OthelloLoader extends Loader{
    private final String OTHELLO_PATH = "saveData/othello/";
    private final String OTHELLO_TEST_PATH = "saveDataTest/othello/";

    public OthelloLoader(Game g) {
        setGame(g);
        setPath(OTHELLO_PATH);
        
        loadAll();
    }
    
    public OthelloLoader(Game g, String s) {
        setGame(g);
        setPath(OTHELLO_TEST_PATH);
        
    }
}
