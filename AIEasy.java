import java.util.Random;
import java.util.ArrayList;
import java.awt.Color;

class AIEasy extends Player {
	
	public AIEasy(Game game, String name, Color color) {
		super(game, name, color);

	}
	
	public AIEasy(Game game) {
		super(game);
	}
	
	public ArrayList<Coordinate> getAvailableMoves (){
		ArrayList<Coordinate> a = new ArrayList <Coordinate> ();
		for (int x=0; x<getGame().getGrid().getGridWidth();x++){
			for (int y=0; y<getGame().getGrid().getGridHeight();y++){
				Coordinate  c1 =	new Coordinate (x, y, getGame().getPlayerTurn());
							
				if (getGame().isValidMove(c1)==true){
					a.add(c1);
				}
			}
		}
		return a;
	}

	public Coordinate setAIMove (){
		Random rnd = new Random();
	 
		int x = 0;
		ArrayList<Coordinate> a = new ArrayList <Coordinate> ();
		a=getAvailableMoves();
		x = rnd.nextInt(a.size());
		return a.get(x);
		
	}
	
	public void isYourMove(){
		setYourTurn(true);
	}

	public void sendMove(Coordinate move){
		move = setAIMove();
		if (getYourTurn()) {
			setYourTurn(false);
			getGame().moveMade(move);
		}
	}
	
	public static void main(String[] args) {

	}

    @Override
    public void sendMove() throws InterruptedException {
        // TODO Auto-generated method stub
        
    }

}