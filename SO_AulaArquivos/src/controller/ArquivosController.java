package controller;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class ArquivosController implements IArquivosController {

	@Override
	public void escreveArq(String caminho, String nome) throws IOException {

		boolean existe = false;
		File dir = new File(caminho);
		// dir.Directory retorna true se for diretorio, falso se for arquivo
		if (dir.exists() && dir.isDirectory()) {
			// se o arquivo nao existe, o file ja o cria sem precisar de tratamento if.
			File arq = new File(dir, nome);
			if (arq.exists()) {
				existe = true;
			}
			String texto = criaTexto();
			FileWriter abre = new FileWriter(arq, existe);
			PrintWriter escreve = new PrintWriter(abre);
			escreve.write(texto);
			//flush corrige qualquer apontamento de memória que esteja errado. Não é obrigatorio usar.
			escreve.flush();
			escreve.close();
			abre.close();

		} else {
			throw new IOException("Dir. inválido!");
		}

	}

	private String criaTexto() {
		StringBuffer buffer = new StringBuffer();
		String linha = JOptionPane.showInputDialog(null, "Digite");
		while(!linha.equalsIgnoreCase("fim")) {
			if(linha.equalsIgnoreCase("zé")) {
				buffer.append("estevão");
				buffer.append("\r\n");
				linha = JOptionPane.showInputDialog(null, "Digite");
			}else {
			buffer.append(linha);
			buffer.append("\r\n");
			linha = JOptionPane.showInputDialog(null, "Digite");
			}
		}
		return buffer.toString();
	}

	@Override
	public void leArq(String caminho, String nome) throws IOException {
		
		File arq = new File(caminho, nome);
		if(arq.exists() && arq.isFile()) {
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while(linha != null) {
				System.out.println(linha);
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		}else {
			throw new IOException("Arquivo inválido!");
		}

	}

	@Override
	public void leDir(String caminho) throws IOException {
		File dir = new File(caminho);
		if(dir.exists() && dir.isDirectory()) {
			File[] vetFile = dir.listFiles();
			for(File f : vetFile) {
				if(f.isDirectory()) {
					System.out.println("["+f.getName()+"]");
				}
			}
			for(File f : vetFile) {
				if(f.isFile()) {
					System.out.println(f.getName()+"\t\t"+f.getAbsolutePath());
				}
			}
		}else {
			throw new IOException("Diretório invalido");
		}

	}

	@Override
	public void abreArq(String caminho, String nome) throws IOException {
		Desktop desktop = Desktop.getDesktop();
		File arq = new File(caminho, nome);
		if(arq.exists() && arq.isFile()) {
			desktop.open(arq);
		}else {
			throw new IOException("Arquivo invalido");
		}
		
	}

}
