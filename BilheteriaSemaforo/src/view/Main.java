package view;

import java.util.concurrent.Semaphore;

import controller.SistemaIngresso;

public class Main {
	
	public static Semaphore semaforo;
	
	public static void main(String[] args) {
		int totalCompradores = 300;
		int permissoesDeUsusario = 1;
		semaforo = new Semaphore(permissoesDeUsusario);
		
		for(int i = 0;i < totalCompradores;i++) {
			SistemaIngresso si = new SistemaIngresso(i, semaforo);
			si.start();
		}
	}
}
