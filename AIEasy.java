import java.util.Random;
import java.util.ArrayList;

class AIEasy {
	
	public ArrayList moves (ArrayList a, Grid b){
		for (int x=0; x<8;x++){
			for (int y=0; y<8;y++){
				a.add(b.getCoordinate( x, y));
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