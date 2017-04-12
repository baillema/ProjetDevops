package time;

import java.util.Date;
import java.util.TimerTask;

public class myTimerTask extends TimerTask {

	int val;
	String name;
	
	public myTimerTask(String name, int val) {
		this.name = name;
		this.val = val;
	}

	@Override
	public void run() {
		System.out.println("Debut execution tache " + new Date());
	    try {
	      Thread.sleep(val*1000);
	    } catch (InterruptedException e) {
	      e.printStackTrace();
	    }
	    System.out.println("Fin execution tache " + new Date());
	}

}
