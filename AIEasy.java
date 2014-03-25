import java.util.Random;
import java.util.ArrayList;

class AIEasy extends Player {
	
	public ArrayList getMoves (ArrayList a){
		
		for (int x=0; x<8;x++){
			for (int y=0; y<8;y++){
				Coordinate  c1 =	new Coordinate (x, y, Game.PlayerTurn.PLAYER1);
				
				
				if (getGame().isValidMove(c1)==true)
					a.add(c1);
				}
			}
		}
		return a;
	}

	public Coordinate makeMove (){
		Random rnd = new Random();
		ArrayList a = new ArrayList();
		a = getMoves(a);
		int x = 0;
		x = rnd.NextInt(a.size());
		return a.get(x);
		
	}
	
	public void isYourMove(){}

	public void sendMove(Coordinate move){}
	
	public String toString(){}
	
	public static void main(String[] args) {

	}

}