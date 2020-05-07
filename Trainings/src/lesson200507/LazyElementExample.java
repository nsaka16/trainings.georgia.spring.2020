package lesson200507;

public class LazyElementExample {

}

class E {
	void process(W w) {
//		L l = new L();
//		l.m(w);
		w.z();
	}
}

//class L {
//	
//	void m(W w) {
//		w.z();
//	}
//	
//	
//}

class W {
	void m1() {	}
	void m2() {	}
	void m3() {	}
	void z() {
		m1();
		m2();
		m3();
	}
}