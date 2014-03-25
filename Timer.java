
public class Timer extends Thread {
	
	private int hours = 0;
	private int minutes = 0;
	private int seconds = 0;
	public final int SECOND = 1000;
	public final int MINUTE = 60;
	public final int HOUR = 60;
	
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
			String s = String.format("%02d:%02d:%02d", hours, minutes, seconds);
			System.out.println(s);
		}
	}
	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.start();
	}
	
}
