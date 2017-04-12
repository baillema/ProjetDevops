package time;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimeToLive {

	Timer timer;
	
	public TimeToLive(String name, int val) {
		timer = new Timer(true);
		myTimerTask myTask = new myTimerTask(name, val);
		
		timer.schedule(myTask, 0);
		System.out.println("Lancement execution");
		
		try {
		  Thread.sleep(20000);
		} catch (InterruptedException e) {
		  e.printStackTrace();
		}
		timer.cancel();
		
		System.out.println("Abandon execution");
		try {
		  Thread.sleep(20000);
		} catch (InterruptedException e) {
		  e.printStackTrace();
		}
	}
		
}

