import java.util.Random;
import java.util.ArrayList;

class AIEasy {
	
	public ArrayList getMoves (ArrayList a){
		
		Othello b = new Othello ();
		
		for (int x=0; x<8;x++){
			for (int y=0; y<8;y++){
				Coordinate  c1 =	new Coordinate (x, y, Game.PlayerTurn.PLAYER1);
				a.add(c1);
			}
		}
		return a;
	}

	public void checkMove (){
		Random rnd = new Random();
		ArrayList a = new ArrayList();
		for (int i=0; i<8*8; i++){
			int x = rnd.nextInt(a.size());
			System.out.println("num in list "+a.get(x) + " numb gen " + x);
			a.remove(x);
		}
		
	}
	
	public static void main(String[] args) {

	}

}