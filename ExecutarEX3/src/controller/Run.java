package controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Run {
	
	public Run() {
		super();
	}
	
	public void execute(String path)throws IllegalArgumentException
	{
		try {
			Runtime.getRuntime().exec(path);
		} catch (IOException e) {
			Desktop desktop = Desktop.getDesktop();
			try {
				desktop.open(new File(path));
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, 
						 "O caminho para o executavel não é válido",
						 "Executavel não encontrado",
						 JOptionPane.ERROR_MESSAGE);
			}
			
		} catch (IllegalArgumentException e1) {
			throw new IllegalArgumentException();
		}
	}
}
