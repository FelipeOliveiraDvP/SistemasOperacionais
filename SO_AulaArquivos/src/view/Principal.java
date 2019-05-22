package view;

import java.io.IOException;

import javax.swing.JOptionPane;

import controller.ArquivosController;
import controller.IArquivosController;

public class Principal {
	
	public static void main(String[] args) {
		
		String caminho = "C:\\TEMP";
		String nome = "teste.txt";
		String path = "C:\\Windows";
		String outroNome = "carro.png";
		
		IArquivosController ac = new ArquivosController();
		
		try {
			//ac.escreveArq(caminho, nome);
			//ac.leArq(caminho, nome);
			//ac.leDir(path);
			ac.abreArq(caminho, outroNome);
		}catch(IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			
		}
		
	}

}
