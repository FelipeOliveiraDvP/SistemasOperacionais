package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

public class RedesController {
	public RedesController() {
		super();
	}
	
	/*
	 * Chama a configuração de IP do Sistema Operacional
	 *
	 * @return Nome e endereço IP dos adaptadores que contem IPV4
	 */
	public String ip(String so) {
		StringBuffer adapters = new StringBuffer();
		
		try {
			Process pr = Runtime.getRuntime().exec("ipconfig");
			InputStream fluxo = pr.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
						
			while(linha != null) {
				// Nome do adaptador
				if(linha.contains("Adaptador")) {
					adapters.append(linha+"\n");
										
				}
				// Endereço ipv4
				if(linha.contains("IPv4")){
					adapters.append("IPv4: ");
					adapters.append(linha.substring(linha.indexOf(":") + 2, linha.length()) + "\n");
				}
				
				linha = buffer.readLine();				
			}
		} catch (IOException e) {
			// Trata o ifconfig do linux
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);	
		}
//		
		return adapters.toString();
	}
	/*
	 * Realiza uma chamada de ping com 10 iterações e mostra o tempo médio em ms
	 * 
	 */
	public String ping(String so) {
		Integer media = null;
		try {
			Process pr = Runtime.getRuntime().exec("ping 8.8.8.8"
					+ "] -n 10");
			InputStream fluxo = pr.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			
			String tempo;
			int count = 0;
			media = 0;
				
			while(linha != null) {
				//System.out.println(linha);
				if(linha.startsWith("Resposta")) {
					tempo = linha.substring(linha.lastIndexOf("tempo"), linha.length());
					tempo = tempo.substring(tempo.indexOf("=") + 1, tempo.lastIndexOf("m"));
					System.out.println(tempo);
					media += Integer.parseInt(tempo);
					count++;
				}
				linha = buffer.readLine();				
			}
			
			media /= count;
			
			return media.toString();
		} catch (IOException e) {
			// Trata o ifconfig do linux
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);	
		}
		return media.toString();
	}
}
