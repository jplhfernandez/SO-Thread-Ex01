package controller;

import java.util.concurrent.Semaphore;

public class ThreadController extends Thread {

	private int tid;
	private String sentido;
	private Semaphore semaforo;

	public ThreadController(Semaphore semaforo) {
		super();
		this.tid = (int) threadId();
		if (tid % 4 == 1) {
			this.sentido = "BAIXO";
		} else if (tid % 4 == 2) {
			this.sentido = "a ESQUERDA";
		} else if (tid % 4 == 3) {
			this.sentido = "CIMA";
		} else if (tid % 4 == 0) {
			this.sentido = "a DIRETIA";
		}
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		int distPercorrida = 0;
		do {
			System.out.println("O carro " + tid + " percorreu " + distPercorrida + "m \n");
			distPercorrida = distPercorrida + 100;

			try {
				sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} while (distPercorrida < 1000);

		System.out.println("O carro " + tid + " chegou no cruzamento \n");

		try {
			sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			semaforo.acquire();
			System.out.println("O carro " + tid + " está cruzando para " + sentido);
			try {
				sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("O carro " + tid + " cruzou \n");
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			semaforo.release();
		}
	}

}