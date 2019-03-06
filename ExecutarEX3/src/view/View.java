package view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.Run;

public class View extends JFrame{
	
	JLabel lblHeader, lblAbrir;
	JTextField txtPath;
	JButton btnRun, btnCancel, btnFile;
	JFileChooser file;
	
	public View() {
		super("Executar - "+System.getProperty("os.name"));
		/* Layout */
		Container screen = getContentPane();
		/* Elementos */
		lblHeader = new JLabel("Digite o caminho do executavel ou clique em procurar");
		lblHeader.setBounds(45, 20, 320, 30);
		
		lblAbrir = new JLabel("Abrir:");
		lblAbrir.setBounds(15, 60, 45, 30);
		
		txtPath = new JTextField();
		txtPath.setBounds(65, 65, 270, 20);
		
		btnRun = new JButton("Run >>");
		btnRun.setBounds(65, 100, 80, 25);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(155, 100, 80, 25);
		
		btnFile = new JButton("Procurar...");
		btnFile.setBounds(245, 100, 100, 25);
		
		file = new JFileChooser();
		/* Listeners */
		btnRun.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Run run = new Run();
				try {
					run.execute(txtPath.getText());
					dispose();
				}catch(IllegalArgumentException e1) {
					JOptionPane.showMessageDialog(null, 
							 "Informe um caminho para o executavel",
							 "Executavel não encontrado",
							 JOptionPane.ERROR_MESSAGE);
				}				
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		
		btnFile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				file.showOpenDialog(null);
				txtPath.setText(file.getSelectedFile().getPath());				
				
			}
		});
		/* Add Elements */
		screen.add(lblHeader);
		screen.add(lblAbrir);
		screen.add(txtPath);
		screen.add(btnRun);
		screen.add(btnCancel);
		screen.add(btnFile);
		/* Configurações */
		setSize(400, 200);
		setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new View();
	}

}
