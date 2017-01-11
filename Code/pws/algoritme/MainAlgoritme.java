package pws.algoritme;

import pws.engine.Timegeneration;

import java.awt.event.ActionEvent;
import java.sql.Time;

public class MainAlgoritme implements Runnable{


	/*-----------------------------
	|    VARIABLE DECLARATIONS    |
	-----------------------------*/

	private Object window;
	private Vliegtuig vt;
	boolean running = true;
	private Rij rij;


	/*-------------------
	|    CONSTRUCTOR    |
	-------------------*/

	public MainAlgoritme(Object window){
		this.window = window;
	}


	/*---------------
	|    METHODS    |
	---------------*/

	public void start(){
		System.out.println("Alg start");
		run();
		System.out.println("Passed first run()");
		//try{run();}catch (Exception e){System.out.println(e);}
	}

	public void run(){
		Timegeneration tg = new Timegeneration();
		rij = new Rij();
		double now, lasttime = System.currentTimeMillis();
		System.out.println("Continuing to main loop");



		while(true) {	//Main loop
			if(running) {
				rij.timeLoop();
				now = System.currentTimeMillis();
				if(now-lasttime >= 10) {
					update(now - lasttime, tg);
					lasttime = now;
				}
			}
		}
	}

	private void update(double dtime, Timegeneration tg){
		Vliegtuig[] vtl = rij.getVtl();
		for(int i = 0; i < vtl.length;i++){
			if(vtl[i] != null){
				vtl[i].update(dtime);
			}
		}

		int[] ptl = tg.getPresentTimeLine();
		int ct = rij.getCt();
		if(ptl[ct] != 0){
			Vliegtuig vt = new Vliegtuig(ptl[ct], 180000, Timegeneration.getKlasse(ptl[ct]), rij);
		}

	}


}