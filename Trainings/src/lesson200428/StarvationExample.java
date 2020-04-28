package lesson200428;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import utils.Utils;

public class StarvationExample {
	
	public static void main(String[] args) {
		
		ExecutorService service = Executors.newSingleThreadExecutor();
		
		service.execute(()->{
			
			while (true) {
				service.execute(()->{
					System.out.println("hello");
				});
				Utils.pause(1000);
			}
			
		});
		
	}

}
