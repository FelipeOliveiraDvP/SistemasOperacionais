package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

public class OpProc {
	
	public OpProc() {
		super();
	}
	// Retorna o nome do sistema operacional
	public String so() {
		String so = System.getProperty("os.version");
		return so;		
	}
	
	public void chamaProcesso(String processo) {
		try {
			Runtime.getRuntime().exec(processo);
		} catch (IOException e) {
			// Procura o codigo de erro de acesso
			if(e.getMessage().contains("740")) {
				StringBuffer buffer = new StringBuffer();
				buffer.append("cmd /c ");
				buffer.append(processo);
				try {
					Runtime.getRuntime().exec(buffer.toString());
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);	
				}
			}else {
				JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);	
			}
		}
	}
	
	public void leProcesso(String processo) {
		try {
			Process pr = Runtime.getRuntime().exec(processo);
			InputStream fuxo = pr.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fuxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			
			while(linha != null) {
				System.out.println(linha);
				linha = buffer.readLine();
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);	
		}
	}
	
	public void mataProcesso(String info) {
		StringBuffer buffer = new StringBuffer();
		String cmdNome = "TASKKILL /IM ";
		String cmdPid = "TASKKILL /PID ";
		int pid = 0;
		try {
			pid = Integer.parseInt(info);
			buffer.append(cmdPid).append(pid);
		}catch(NumberFormatException e) {
			buffer.append(cmdNome).append(info);
		}finally {
			chamaProcesso(buffer.toString());
		}

	}
}
