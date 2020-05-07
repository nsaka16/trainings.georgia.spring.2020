package lesson200507;

public class FeatureEnvyExample {
	public static void main(String[] args) {
		A a = new A();
	}

}

class B {
	void x(A a) {
		a.m();
	}
}

class A {
	void m1() {
	}

	void m2() {
	}

	void m3() {
	}

	void m() {
		m1();
		m2();
		m3();
	}
}
