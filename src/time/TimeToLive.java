package time;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimeToLive {

	Timer timer;
	
	public TimeToLive(String name, int val) {
		timer = new Timer(true);
		TimerTask myTask = new TimerTask() {
			@Override
			public void run() {
				System.out.println("Debut execution tache " + new Date());
			    try {
			      Thread.sleep(1000);
			    } catch (InterruptedException e) {
			      e.printStackTrace();
			    }
			    System.out.println("Fin execution tache " + new Date());
			}
		};
		
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

