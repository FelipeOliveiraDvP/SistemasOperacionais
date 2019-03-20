package controller;

import java.util.concurrent.Semaphore;

public class ThreadCarro extends Thread{
	
	private Semaphore semaforo;
	private int id;
	private static int posChegada;
	private static int posSaida;
	
	public ThreadCarro(int id, Semaphore semaforo) {
		this.id = id;
		this.semaforo = semaforo;
	}
	
	@Override
	public void run() {
		carroAndando();
		try {
			semaforo.acquire();
			carroParado();
			carroSaindo();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
		
	}
	
	private void carroAndando() {
		int distFinal = (int)(Math.random() * 1001) + 1000;
		int distPercorrida = 0;
		int deslocamento = (int)(Math.random() * 60) + 41;
		int tempo = 50;
		
		while(distPercorrida < distFinal) {
			distPercorrida += deslocamento;
			try {
				Thread.sleep(tempo);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Carro #"+id+" andou "+distPercorrida+" metros");			
		}
		posChegada++;
		System.out.println("Carro #"+id+" foi o "+posChegada+"° a chegar");
	}
	
	private void carroParado() {
		System.out.println("Carro #"+id+" Estacionou");
		int tempoParado = (int)(Math.random() * 2001) + 1000;
		try {
			Thread.sleep(tempoParado);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void carroSaindo() {
		posSaida++;
		System.out.println("Carro #"+id+" foi o "+posSaida+"° a sair");
	}
}
