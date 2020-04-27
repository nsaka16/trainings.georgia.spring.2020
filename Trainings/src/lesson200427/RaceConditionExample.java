package lesson200427;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import utils.Utils;

public class RaceConditionExample {
	
	static int counter = 0;
	
	
	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(3);
		pool.execute(new Tourniket());
		pool.execute(new Tourniket());
	}

}


class Tourniket implements Runnable {
	static Object mutex = new Object();

	@Override
	public void run() {
		while (true) {
			Utils.pause(3000);
//			synchronized (CountersExample.class) {
			
			// critical section, provides atomicity  
			synchronized (mutex) { // lock
				increase();
			} // unlock
		}
	}

	private void increase() {
		int tmp = RaceConditionExample.counter;
		tmp += 1;
		RaceConditionExample.counter = tmp;
		System.out.println(RaceConditionExample.counter);
	}
	
	
}