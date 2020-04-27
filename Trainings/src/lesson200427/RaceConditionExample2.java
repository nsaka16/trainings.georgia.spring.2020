package lesson200427;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import utils.Utils;

public class RaceConditionExample2 {
	
	static int counter = 0;
	
	
	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(2);
		pool.execute(new Tourniket());
		pool.execute(new Tourniket());
	}

}


class Tourniket2 implements Runnable {
	static Lock mutex = new ReentrantLock();

	@Override
	public void run() {
		while (true) {
			Utils.pause(3000);
			
			// critical section, provides atomicity
			mutex.lock();
			try {
				increase();
			} finally {
				mutex.unlock();
			}
		}
	}

	private void increase() {
		int tmp = RaceConditionExample2.counter;
		tmp += 1;
		RaceConditionExample2.counter = tmp;
		System.out.println(RaceConditionExample2.counter);
	}
	
	
}