package view;

import java.util.concurrent.Semaphore;

import controller.ThreadCarro;

public class Park {

	public static void main(String[] args) {
		int permissoes = 3;
		Semaphore semaforo = new Semaphore(permissoes);
		
		for(int id = 0;id < 10;id++) {
			Thread tCarro = new ThreadCarro(id, semaforo);
			tCarro.start();
		}
	}

}
