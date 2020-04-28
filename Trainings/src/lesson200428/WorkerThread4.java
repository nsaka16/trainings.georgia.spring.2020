package lesson200428;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class WorkerThread4 implements Runnable {

	MyBlockingQueue<Runnable> tasks = new MyBlockingQueue<Runnable>();

	@Override
	public void run() {
		System.out.println("starting " + this);
		long c = 0;
		while (true) {
			Runnable task = null;
			try {
				task = tasks.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (task != null)
				task.run();
		}
	}

	public void execute(Runnable task) {
		tasks.put(task);
	}

}
