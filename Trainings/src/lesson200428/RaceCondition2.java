package lesson200428;

import utils.Utils;

public class RaceCondition2 {
	
	public static void main(String[] args) {
		Account acc = new Account();
		
		new Thread(()->{
			while (true) {
				Utils.pause(1000);
				acc.increase(1000);
				System.out.println(acc.isBalanced());
				System.out.println(acc);
			}
		}).start();
		new Thread(()->{
			while (true) {
				Utils.pause(1000);
				acc.increase(1000);
				System.out.println(acc.isBalanced());
			}
		}).start();
		new Thread(()->{
			while (true) {
				Utils.pause(1000);
				acc.increase(1000);
				System.out.println(acc.isBalanced());
			}
		}).start();
	}

}


class Account {
	int debit;
	int credit;
	
	private Object mutex = new Object();
	
	void increase(int amount) {
		synchronized (mutex) {
			debit += amount;
			Utils.pause(500);
			credit += amount;
			Utils.pause(500);
		}
	}
	
	boolean isBalanced() {
		synchronized (mutex) {
			return debit - credit == 0;
		}
	}
	
	@Override
	public String toString() {
		synchronized (mutex) {
			return debit + ", " + credit;
		}
	}
}
