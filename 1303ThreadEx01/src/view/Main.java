package view;

import java.util.concurrent.Semaphore;
import controller.ThreadController;

public class Main {

	public static void main(String[] args) {
		Semaphore semaforo = new Semaphore(1);
		
		for(int i = 0; i < 4; i++) {
			Thread t = new ThreadController(semaforo);
			t.start();
		}

	}

}
