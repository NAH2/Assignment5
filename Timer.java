/**
 * \\file - Timer.java 
 * \author Thomas Letheby 659204, Jake Plumley
 * \date 26th March 14
 * 
 * \see GameWindow.java
 * 
 * \brief Timer class, This class implements the count up timer to be used in
 * both game windows
 * 
 * The class initialises a timer to be used for both games
 */
public class Timer extends Thread {

	/**
	 * Accessor method to set the hours of the timer 
	 * \param h an integer for the hours
	 */
	public void setHours(int h) {
		boolean test = false;
		if (test || m_test) {
            System.out.println("Timer :: setHours() BEGIN");
            }
		m_hours = h;
		
		if (test || m_test) {
			System.out.println("Timer :: setHours() END");
		}
		
	}

	/**
	 * Accessor method to set the minutes of the timer 
	 * \param m an integer for the minutes
	 */
	public void setMinutes(int m) {
		boolean test = false;
		if (test || m_test) {
            System.out.println("Timer :: setMinutes() BEGIN");
            }
		m_minutes = m;
		
		if (test || m_test) {
			System.out.println("Timer :: setMinutes() END");
		}
	}

	/**
	 * Accessor method to set the seconds of the timer 
	 * \param s an integer for the seconds
	 */
	public void setSeconds(int s) {
		boolean test = false;
		if (test || m_test) {
            System.out.println("Timer :: setSeconds() BEGIN");
            }
		m_seconds = s;
		
		if (test || m_test) {
			System.out.println("Timer :: setSeconds() END");
		}
	}

	/**
	 * Accessor method to get the hours 
	 * \return m_hours a integer of the current hours
	 */
	public int getHours() {
		boolean test = false;
		if (test || m_test) {
            System.out.println("Timer :: setHours() BEGIN");
            }
		if (test || m_test) {
			System.out.println("Timer :: setHours() END");
		}
		return m_hours;
		
		
	}

	/**
	 * Accessor method to get the minutes 
	 * \return m_minutes a integer of the current minutes
	 */
	public int getMinutes() {
		boolean test = false;
		if (test || m_test) {
            System.out.println("Timer :: getMinutes() BEGIN");
            }
		
		if (test || m_test) {
			System.out.println("Timer :: getMinutes() END");
		}
		return m_minutes;
	}

	/**
	 * Accessor method to get the seconds 
	 * \return m_seconds a integer of the current seconds
	 */
	public int getSeconds() {
		boolean test = false;
		if (test || m_test) {
            System.out.println("Timer :: getSeconds() BEGIN");
            }
		
		if (test || m_test) {
			System.out.println("Timer :: getSeconds() END");
		}
		return m_seconds;
	}

	/**
	 * method to set the timer to be running, returns true
	 */
	public boolean setRunning() {
		boolean test = false;
		if (test || m_test) {
            System.out.println("Timer :: setRunning() BEGIN");
            }
		m_running = false;
		
		if (test || m_test) {
			System.out.println("Timer :: setRunning() END");
		}

		return true;
	}

	/**
	 * Constructor of Timer, receives the game and sets running to true 
	 * \param g the current game
	 */
	public Timer(Game g) {
		boolean test = false;
		if (test || m_test) {
            System.out.println("Timer :: Timer() BEGIN");
            }
		m_game = g;
		m_running = true;
		start();
		
		if (test || m_test) {
			System.out.println("Timer :: Timer() END");
		}
	}

	/**
	 * method to set the interval of the timer and catch any exception
	 */
	public void secondInterval() {
		boolean test = false;
		if (test || m_test) {
            System.out.println("Timer :: secondInterval() BEGIN");
            }
		try {
			Thread.sleep(SECOND);
		} catch (Exception e) {
			System.out.println("hey");
		}
		if (test || m_test) {
			System.out.println("Timer :: secondInterva() END");
		}
	}

	/**
	 * method used to run the timer and display in the correct format
	 */
	public void run() {
		boolean test = false;
		if (test || m_test) {
            System.out.println("Timer :: run() BEGIN");
            }
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
			} catch (NullPointerException e) {
				setRunning();
			}
		}
		if (test || m_test) {
			System.out.println("Timer ::run() END");
		}
	}

	/**
	 * method that is called when saving
	 */
	public String toString() {
		boolean test = false;
		if (test || m_test) {
            System.out.println("Timer :: toString() BEGIN");
            }
		String timeString = getHours() + "," + getMinutes() + ","
				+ getSeconds() + ",";
		
		if (test || m_test) {
			System.out.println("Timer :: toString() END");
		}
		return timeString;
	}
	
	/**
	 * Main method for all the tests that are required
	 * \param args -> Not used
	 */

	public static void main(String args[]) {

		Game g = new Othello();
		Timer t = new Timer(g);
		Timer t2 = new Timer(null);
		t.setHours(t.m_hour_test);
		if (t.getHours() == t.m_hour_test) {
			System.out.println("Hour Set Success " + t.getHours());
		}
		t.setMinutes(t.m_minute_test);
		if (t.getHours() == t.m_minute_test) {
			System.out.println("Hour Set Success " + t.getHours());
		}
		t.setSeconds(t.m_second_test);
		if (t.getHours() == t.m_second_test) {
			System.out.println("Hour Set Success " + t.getHours());
		}
		if (t.setRunning() == true) {
			System.out.println("Set Running Success");
		}
		t.secondInterval();
		if (t.getSeconds() == (t.m_second_test + 1)) {
			System.out.println("Added Second Successfully");
		}
		t.run();
		System.out.println(t.toString());

	}

	/** the hours integer used throughout the class, initialising m_hours to 0 */
	private int m_hours = 0;
	/**
	 * the minutes integer used throughout the class, initialising m_minutes to
	 * 0
	 */
	private int m_minutes = 0;
	/**
	 * the seconds integer used throughout the class, initialising m_seconds to
	 * 0
	 */
	private int m_seconds = 0;
	/** the value for seconds */
	private final int SECOND = 1000;
	/** the value for minutes */
	private final int MINUTE = 60;
	/** the value for hours */
	private final int HOUR = 60;
	/** used to receive the game */
	private Game m_game;
	/** a string that is used in displaying the timer format */
	private String m_display;
	/** used in running the timer */
	private boolean m_running;
	/** Used for testing purposes (Hour) */
	private int m_hour_test = 10;
	/** Used for testing purposes (Minutes) */
	private int m_minute_test = 10;
	/** Used for testing purposes (Seconds) */
	private int m_second_test = 10;
	/**used for turning testing on or off*/
	private boolean m_test = true;

}
