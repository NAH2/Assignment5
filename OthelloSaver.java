
public class OthelloSaver extends Saver{
    private final String OTHELLO_PATH = "saveData/othello/";
    private final String OTHELLO_TEST_PATH = "saveDataTest/othello/";
    
    public OthelloSaver(Game g) {
        setGame(g);
        setPath(OTHELLO_PATH);
        
        createDirs();
    }
    
    public OthelloSaver() {
        setPath(OTHELLO_TEST_PATH);
        
        createDirs();
    }
}
