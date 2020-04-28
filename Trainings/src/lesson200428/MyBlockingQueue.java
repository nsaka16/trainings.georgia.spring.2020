package lesson200428;

import java.util.LinkedList;
import java.util.Queue;

public class MyBlockingQueue<T> {
	
	private Queue<T> queue = new LinkedList<T>(); 

	public T take() throws InterruptedException {
		synchronized (queue) {
			while (queue.isEmpty()) {
				this.wait();
			}
			return queue.poll();
		}
	}

	public void put(T item) {
		synchronized (queue) {
			queue.offer(item);
			this.notifyAll();
		}
	}

}
