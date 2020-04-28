package lesson200428;

import utils.Utils;

public class DeadLockExample {
	
	public static void main(String[] args) {
		
		Object a = new Object();
		Object b = new Object();
		
		new Thread(() -> {
			synchronized (a) {
				Utils.pause(10);
				synchronized (b) {
					System.out.println(Thread.currentThread());
				}
			}
		} ) .start();
		
		new Thread(() -> {
			synchronized (b) {
				synchronized (a) {
					System.out.println(Thread.currentThread());
				}
			}
		} ) .start();
		
		// solution:  use the same order of blocking in each thread
	}

}
