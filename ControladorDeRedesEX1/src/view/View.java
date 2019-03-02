package view;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import controller.RedesController;

public class View extends JFrame{
	
	JLabel lblHeader;
	JButton btnIp, btnPing, btnSair;
	
	public View() {
		super("Sistemas Operacionais 1");
		Container screen = getContentPane();
		setLayout(null);
		/* Elementos*/
		lblHeader = new JLabel("Exercicio 1 - Controlador de Redes");
		lblHeader.setBounds(20, 10, 300, 40);
		lblHeader.setFont(new Font("Arial", Font.BOLD, 16));
		
		btnIp = new JButton("Configuração de IP");
		btnIp.setBounds(50, 70, 200, 40);
		
		btnPing = new JButton("Teste de PING");
		btnPing.setBounds(50, 120, 200, 40);
		
		btnSair = new JButton("Sair");
		btnSair.setBounds(50, 170, 200, 40);
		/* Implement Action Listener */
		btnIp.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				RedesController rc = new RedesController();
				String ip = rc.ip(System.getProperty("os.name"));
				JOptionPane.showMessageDialog(null, ip, "Configuração de IP - "+System.getProperty("os.name"), JOptionPane.INFORMATION_MESSAGE);				
			}
		});
		
		btnPing.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				RedesController rc = new RedesController();
				String media = rc.ping(System.getProperty("os.name"));
				JOptionPane.showMessageDialog(null, "Media do PING: "+media+" ms", 
											"Tempo médio de resposta do ping - "+System.getProperty("os.name"),
											JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		btnSair.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);				
			}
		});
		/* Add elements*/
		screen.add(lblHeader);
		screen.add(btnIp);
		screen.add(btnPing);
		screen.add(btnSair);
		/* Configurações*/
		setLocationRelativeTo(null);
		setResizable(false);
		setSize(325, 270);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
