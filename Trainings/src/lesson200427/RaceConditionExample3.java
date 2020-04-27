package lesson200427;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import utils.Utils;

public class RaceConditionExample3 {
	
	static int counter = 0;
	
	
	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(2);
		pool.execute(new Tourniket());
		pool.execute(new Tourniket());
	}

}


class Tourniket3 implements Runnable {
	static Semaphore mutex = new Semaphore(1);

	@Override
	public void run() {
		while (true) {
			Utils.pause(3000);
			
			// critical section, provides atomicity
			mutex.acquireUninterruptibly();
			try {
				increase();
			} finally {
				mutex.release();
			}
		}
	}

	private void increase() {
		int tmp = RaceConditionExample3.counter;
		tmp += 1;
		RaceConditionExample3.counter = tmp;
		System.out.println(RaceConditionExample3.counter);
	}
	
	
}