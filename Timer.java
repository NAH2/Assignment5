
public class Timer extends Thread {
	
	private int hours = 0;
	private int minutes = 0;
	private int seconds = 0;
	private final int SECOND = 1000;
	private final int MINUTE = 60;
	private final int HOUR = 60;
	private Game game;
	String s;
	
	public Timer(Game g) {
	    game = g;
	}
	
	public void secondInterval() {
		try {
			Thread.sleep(SECOND);
		} catch (Exception e) {
			System.out.println("hey");
		}	
	}
	
	public void run() {
		while (true) {
			secondInterval();
			++seconds;
			if (seconds >= MINUTE) {
				++minutes;
				seconds = 0;
			}
			if (minutes >= HOUR) {
				++hours;
				minutes = 0;
			}
			s = String.format("%02d:%02d:%02d", hours, minutes, seconds);
			game.getWindow().setTimerDisplay(s);
			//System.out.println(s);
		}
	}
	public static void main(String[] args) {
		//Timer timer = new Timer();
		//timer.start();
	}
	
}
