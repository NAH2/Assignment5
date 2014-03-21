import java.util.Random;
import java.util.ArrayList;

class AIEasy {
	
	public void moves (ArrayList a, Grid b){
		for (int x=0; x<8;x++){
			for (int y=0; y<8;y++){
				a.add(b.getCoordinate(int x, int y));
			}
		}
	}

	
	public static void main(String[] args) {
		int x =0;
		ArrayList m_moves = new ArrayList();
			
		Random m_rnd = new Random();
		Grid b = new Grid();
		AIEasy ai = new AIEasy();
		
		moves(m_moves, b);
		for (int i=0; i<20; i++){
			x = m_rnd.nextInt(m_moves.size());
			System.out.println("num in list "+m_moves.get(x) + " numb gen " + x);
			m_moves.remove(x);
		}
	}

}