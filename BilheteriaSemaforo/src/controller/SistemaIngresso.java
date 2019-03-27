package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class SistemaIngresso extends Thread{
	
	private int ingressosRestantes = 100;
	private int id;
	private Semaphore semaforo;
	
	public SistemaIngresso(int id, Semaphore semaforo) {
		this.id = id;
		this.semaforo = semaforo;
	}
	
	public void run() {
		if(login()) {
			if(comprarIngresso()) {
				// Falta finalizar comprar
			}
		}
	}
	//int ingressosPorPessoa = new Random().nextInt(4) + 1;
	public boolean login() {		
		int tempoDeLogin = new Random().nextInt(1951) + 50;
		
		if(tempoDeLogin > 1000) {
			System.out.println("O usuario #"+this.id+" excedeu o tempo de login");				
			return false;
		}else {
			System.out.println("O usuario #"+this.id+" entrou no sistema");			
		}
		
		try {
			Thread.sleep(tempoDeLogin);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean comprarIngresso() {
		int tempoDeCompra = new Random().nextInt(1000) + 2000;
		
		if(tempoDeCompra > 2500) {
			System.out.println("O usuario #"+this.id+" excedeu o tempo de compra");
			return false;
		}else {
			System.out.println("O usuario #"+this.id+" vai para a finalização da compra");			
		}
		
		try {
			Thread.sleep(tempoDeCompra);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	
	public void validarCompra() {
		
	}
	
}
