package lesson200427;

import java.util.concurrent.Semaphore;

public class Tripleton {
	
	private Tripleton() {
		
	}
	
	private static Semaphore sem = new Semaphore(3);
	
	public static Tripleton create() {
		sem.acquireUninterruptibly();
		return new Tripleton();
	}
	
	public static void release(Tripleton t) {
		// free resources of t ...
		sem.release();
	}

}
