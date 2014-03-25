
public class Timer extends Thread {
	
	public void setHours(int h) {
		m_hours = h;
	}
	
	public void setMinutes(int m) {
		m_minutes = m;
	}
	
	public void setSeconds(int s) {
		m_seconds = s;
	}
	
	public int getHours() {
		return m_hours;
	}
	
	public int getMinutes() {
		return m_minutes;
	}
	
	public int getSeconds() {
		return m_seconds;
	}
	
	public Timer(Game g) {
	    m_game = g;
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
			++m_seconds;
			if (m_seconds >= MINUTE) {
				++m_minutes;
				m_seconds = 0;
			}
			if (m_minutes >= HOUR) {
				++m_hours;
				m_minutes = 0;
			}
			m_display = String.format("%02d:%02d:%02d", m_hours, m_minutes, m_seconds);
			//m_game.getWindow().setTimerDisplay(m_display);
			//System.out.println(s);
		}
	}
	
	public String toString() {
		String timeString = getHours() + "," + getMinutes() + "," + getSeconds() + ",";
		return timeString;
	}
	
	public static void main(String[] args) {
		//Timer timer = new Timer();
		//timer.start();
	}
	private int m_hours = 0;
	private int m_minutes = 0;
	private int m_seconds = 0;
	private final int SECOND = 1000;
	private final int MINUTE = 60;
	private final int HOUR = 60;
	private Game m_game;
	private String m_display;
	
}
