
public class OthelloSaver extends Saver{
    private final String OTHELLO_PATH = "saveData/othello/";
    
    public OthelloSaver(Game g) {
        setGame(g);
        setPath(OTHELLO_PATH);
        
        createDirs();
    }
}
