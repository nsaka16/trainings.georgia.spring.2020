package lesson200506;


interface SomeData {
	int get();
	void set(int i);
}


public class GlobalDataExample {
	static int Size = 10;
	
	public static void main(String[] args) {
		
		
		SomeData data = new SomeData() {
			@Override
			public void set(int i) {
				Size = i;
			}
			
			@Override
			public int get() {
				return Size;
			}
		};
		
		A a = new A();
		a.m(data);
		
		
		
	}
	
}


class A {
	void m(SomeData data) {
//		GlobalDataExample.Size++;
		int tmp = data.get();
		data.set(tmp+1);
		
		
	}
}
