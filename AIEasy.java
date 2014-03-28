import java.util.*;
import java.awt.Color;
/**
 * \\file -AIEasy.java 
 * \author -Tyrone Bramwell
 * \date -26th March 14
 * 
 * \brief This class contains methods to create and run a AI 
 * 
 * creates AI for othello and connectfour which randomly picks a valid move and 
 * performs the move.
 */
public class AIEasy extends Player {
    /**
    * Sets the variable m_running which is used to decide if the AIEasy runs 
    * or not
    * 
    * \param run a boolean which selects if the AI should run
    * \return true if the method completes
    */
    public boolean SetRun(boolean run){
        if (m_test){
            System.out.println("AIEasy.SetRun- begin");
        }
        
        m_running = run;
        
        if (m_test){
            System.out.println("AIEasy.SetRun- end");
        }
        
        return true;
    }
    
    /**
    * Sets the the wait time before which the ai performs a move
    * 
    * \param responseTime a integer for selecting the wait time
    * \return true if the method completes
    */
    public boolean SetTime(int responseTime){
        if (m_test){
            System.out.println("AIEasy.SetTime- begin");
        }
        
        m_time = responseTime;
        
        if (m_test){
            System.out.println("AIEasy.SetTime- end");
        }
        
        return true;
    }
    
    /**
    * Sets the variable m_running which is used to decide if the AIEasy runs 
    * or not
    * \return returns variable m_time which is used for waiting
    */
    public int getTime(){
        if (m_test){
            System.out.println("AIEasy.getTime- begin");
        }
        if (m_test){
            System.out.println("AIEasy.getTime- end");
        }
        
        return m_time ;
    }
    
    /**
    * Sets the the wait time befor which the ai performs a move
    * \return returns variable m_running which is used for see if a turn is 
    * performed
    */
    public boolean getRun(){
        if (m_test){
            System.out.println("AIEasy.getRun- begin");
        }
        if (m_test){
            System.out.println("AIEasy.getRun- end");
        }
        
        return m_running;
    	
    }
    
    /**
    * Constructor to crate AIEasy with parameters for game type
    * \param game a type of game for selecting the game
    * \param name a string for the player name
    * \param color a type colour for selecting colour
    */
    public AIEasy(Game game, String name, Color color) {
        super(game, name, color);
        if (m_test){
            System.out.println("AIEasy.AIEasy( game,  name,  color)- begin");
        }
        
        m_running = true;
        
        if (m_test){
            System.out.println("AIEasy.AIEasy( game,  name,  color)- end");
        }
    }
    
    /**
    * Constructor to crate AIEasy with parameters for game type
    * \param game a type of game for selecting the game
    */
    public AIEasy(Game game) {
        super(game);
        if (m_test){
            System.out.println("AIEasy.AIEasy(game)- begin");
        }
        
        m_running = true;
        
        if (m_test){
            System.out.println("AIEasy.AIEasy(game)- end");
        }
    }
    
    /**
    * getAvailableMoves creates an array list of valid possiable moves for the 
    * game
    * \return arraylist of all possiable valid moves
    */
    public ArrayList<Coordinate> getAvailableMoves (){
        if (m_test){
            System.out.println("AIEasy.getAvailableMoves- begin");
        }
        
        ArrayList<Coordinate> a = new ArrayList <Coordinate> ();
        for (int x=0; x<getGame().getGrid().getGridWidth();x++){
            for (int y=0; y<getGame().getGrid().getGridHeight();y++){
                Coordinate c1 = new Coordinate (x, y,getGame().getPlayerTurn());
                					
                if (getGame().isValidMove(c1)==true){
                    a.add(c1);
                }
            }
        }
        
        if (m_test){
            System.out.println("AIEasy.getAvailableMoves- end");
        }
        
        return a;
    }
    
    /**
    * setAIMove picks a random move from the list and returns a coordinate 
    * \return coordinate which is then placed into the grid
    */
    public Coordinate setAIMove () throws IndexOutOfBoundsException {
        if (m_test){
            System.out.println("AIEasy.setAIMove- begin");
        }
        
        Random rnd = new Random();
         
        int x = 0;
        ArrayList<Coordinate> a = new ArrayList <Coordinate> ();
        a=getAvailableMoves();
        
        if (a.size() == 0){
            return null;
        }
        
        x = rnd.nextInt(a.size());
        
        if (m_test){
            System.out.println("AIEasy.setAIMove- end");
        }
        
        return a.get(x);
    }
    
    /**
    * isYourMove used to run methods which are used to set it as the AI turn
    * \return true if the method completes
    */
    public boolean isYourMove() throws InterruptedException {
        if (m_test){
            System.out.println("AIEasy.isYourMove- begin");
        }
        
        setYourTurn(true);
        if (!(getGame().getPlayer1() instanceof Human || 
                getGame().getPlayer2() instanceof Human)){
            sendMove();
        }
        
        if (m_test){
            System.out.println("AIEasy.isYourMove- end");
        }
        
        return true;
    }
    
    /**
    * sendMove() a method used to send a valid move to a game of othello or 
    * connect four.
    * \return true if the method completes
    */
    public boolean sendMove() throws InterruptedException {
        if (m_test){
            System.out.println("AIEasy.sendMove() - begin");
        
        }
        new Thread(
            new Runnable() {
                public void run() {
                    if(m_running){
                        try {
                            Coordinate move ;
                            Thread.sleep(m_time);
                            move =setAIMove();
                            if (getYourTurn()) {
                                setYourTurn(false);
                                getGame().moveMade(move);							
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        ).start();
        if (m_test){
            System.out.println("AIEasy.sendMove() - end");
        }
        return true;
    }
    
    /**
    * sendMove a method used to send a valid move to a game of othello or 
    * connect four.
    * \param move pass a coordinate object from the grid
    * \return true if the method completes
    */
    public boolean sendMove(Coordinate move) throws InterruptedException {
        if (m_test){
            System.out.println("AIEasy.sendMove(Coordinate move) - begin");
        }
        class MyThread implements Runnable {
            Coordinate m_move;
            public MyThread(Coordinate move) {
                // store parameter for later user
                m_move = move;
            }
                
            public void run() {
                if(m_running){
                    try {
                        m_move = setAIMove();
                    } catch (IndexOutOfBoundsException e) {
                        e.printStackTrace();
                    }
                    if (getYourTurn()) {
                        try {
                            setYourTurn(false);
                            getGame().moveMade(m_move);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        
        Runnable r = new MyThread(move);
        r.wait(m_time);
        new Thread(r).start();
        
        if (m_test){
            System.out.println("AIEasy.sendMove(Coordinate move) - end");
        }
        
        return true;
    }
    
    /**
    * main used to run tests which create coordinates and a game and prints them
    * out from the coordinate method.
    */
    public static void main(String[] args) {
        m_test=true;
        if (m_test){
            System.out.println("AIEasy.main - begin");
        }
        Game game = new ConnectFour();
        AIEasy C4AI;
        AIEasy C4AI2;
        
        C4AI2 = new AIEasy(game, "test1",Color.blue);
        C4AI = new AIEasy(game,"test2",Color.red);
        
        C4AI = new AIEasy(game, "test3",Color.yellow);
        C4AI = new AIEasy(game);
        System.out.println(C4AI.setAIMove());
        Coordinate cord =new Coordinate(C4AI.getGame().getGrid().getGridWidth()
             ,C4AI.getGame().getGrid().getGridHeight(),Game.PlayerTurn.PLAYER1);
        
        Game othello = new ConnectFour();
        AIEasy OAI;
        AIEasy OAI2;
        
        OAI2 = new AIEasy(game, "test1",Color.blue);
        OAI = new AIEasy(game,"test2",Color.red);
        
        OAI = new AIEasy(game, "test3",Color.yellow);
        OAI = new AIEasy(game);
        System.out.println(OAI.setAIMove());
        Coordinate cord2 =new Coordinate(OAI.getGame().getGrid().getGridWidth()
              ,OAI.getGame().getGrid().getGridHeight(),Game.PlayerTurn.PLAYER1);
        AIEasy a = new AIEasy(game,"test2",Color.red);
        
        System.out.println("Test: getRun " + a.getRun() + " getTime: " + 
                                                                   a.getTime());
        a.SetRun(false);
        a.SetTime(0);
        System.out.println("Test: getRun " + a.getRun() + " getTime: " + 
                                                                   a.getTime());
        
        if (m_test){
            System.out.println("AIEasy.main - end");
        }
    }
    
    /** m_time variable is an integer used to hold the wait time. set to 1500 by 
    default if not changed */
    private int m_time = 1500;
    /** m_running variable is an boolean used to decided if the AIEasy should 
    run or not*/
    private boolean m_running;
    /** m_test variable used for testing when methods are entered and left and 
     * prints out into the console*/
    private static boolean m_test = false;
}