package lesson200428;

public class EvilSynch {
	
	public static void main(String[] args) {
		
		A a = new A();
		a.print("Hello");
		
		new Thread(()-> {
			synchronized (a) {
				while (true);
			}
		}).start();
		
		new Thread(()-> {
			System.out.println(Thread.currentThread());
			a.print("Hello2");
		}).start();
		
		new Thread(()-> {
			System.out.println(Thread.currentThread());
			a.print("Hello3");
		}).start();
		
	}

}

class A {
	
	private Object mutex = new Object();
	
	void print(String message) {
		synchronized (mutex) {
			System.out.println(message);
		}
	}
	
}
