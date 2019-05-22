package controller;

import java.io.IOException;

public interface IArquivosController {
	
	public void escreveArq(String caminho, String nome) throws IOException;
	
	public void leArq(String caminho, String nome) throws IOException;
	
	public void leDir(String caminho) throws IOException;
	
	public void abreArq(String caminho, String nome) throws IOException;

}
