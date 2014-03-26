/**
 * \\file - Timer.java
 * \author Thomas Letheby
 * \date 26th March 14
 * 
 * \see GameWindow.java
 * 
 * \brief Timer class, This class implements the count up timer to be used in both game windows
 *
 * The class initialises a timer to be used for both games
 */
public class Timer extends Thread {
	
	/**
	 * Accessor method to set the hours of the timer
	 * @param h an integer for the hours
	 */
	public void setHours(int h) {
		m_hours = h;
	}
	
	/**
	 * Accessor method to set the minutes of the timer
	 * @param m an integer for the minutes
	 */
	public void setMinutes(int m) {
		m_minutes = m;
	}
	
	/**
	 * Accessor method to set the seconds of the timer
	 * @param s an integer for the seconds
	 */
	public void setSeconds(int s) {
		m_seconds = s;
	}
	
	/**
	 * Accessor method to get the hours 
	 * @return m_hours a integer of the current hours
	 */
	public int getHours() {
		return m_hours;
	}
	/**
	 * Accessor method to get the minutes 
	 * @return m_minutes a integer of the current minutes
	 */
	public int getMinutes() {
		return m_minutes;
	}
	/**
	 * Accessor method to get the seconds
	 * @return m_seconds a integer of the current seconds
	 */
	public int getSeconds() {
		return m_seconds;
	}
	
	/**
	 * method to set the timer to be running, returns true
	 */
	public boolean setRunning(){
		m_running = false;
		
		return true;
	}
	
	/**
	 * Constructor of Timer, receives the game and sets running to true
 	 * @param g the current game
	 */
	public Timer(Game g) {
	    m_game = g;
	    m_running = true;
	    start();
	}
	
	/**
	 * method to set the interval of the timer and catch any exception
	 */
	public void secondInterval() {
		try {
			Thread.sleep(SECOND);
		} catch (Exception e) {
			System.out.println("hey");
		}	
	}
	
	/**
	 * method used to run the timer and display in the correct format
	 */
	public void run() {
		while (m_running) {
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
			m_display = String.format("%02d:%02d:%02d", m_hours, m_minutes,
			                                                         m_seconds);
			try {
		         m_game.getWindow().getDrawing().setTimerDisplay(m_display);
			} catch (NullPointerException e){
			    setRunning();
			}
		}
	}
	/**
	 * method that is called when saving
	 */
	public String toString() {
		String timeString = getHours() + "," + getMinutes() 
		                                             + "," + getSeconds() + ",";
		return timeString;
	}
	/**the hours integer used throughout the class, initialising m_hours to 0 */
	private int m_hours = 0;
	/**the minutes integer used throughout the class, initialising m_minutes to 0 */
	private int m_minutes = 0;
	/**the seconds integer used throughout the class, initialising m_seconds to 0 */
	private int m_seconds = 0;
	/**the value for seconds*/
	private final int SECOND = 1000;
	/**the value for minutes */
	private final int MINUTE = 60;
	/**the value for hours*/
	private final int HOUR = 60;
	/**used to receive the game */
	private Game m_game;
	/**a string that is used in displaying the timer format*/
	private String m_display;
	/**used in running the timer*/
	private boolean m_running;
	
}
