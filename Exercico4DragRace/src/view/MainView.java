package view;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MainView extends JFrame{

	private JLabel carro1, carro2, vencedor, perdedor, barra;
	private JButton correr;
	private JTextField txtVencedor, txtPerdedor;
	
	public static void main(String[] args) {
		new MainView();
	}
	
	public MainView() {		
		Container screen = getContentPane();
		// elementos
		carro1 = new JLabel("Carro 1");
		carro1.setBounds(15, 50, 70, 30);
		carro1.setForeground(new Color(0,0,255));
		screen.add(carro1);
		
		barra = new JLabel("--------------------------------------------------------------------------------------------------------------------------------");
		barra.setBounds(15, 90, 700, 30);
		screen.add(barra);
		
		carro2 = new JLabel("Carro 2");
		carro2.setBounds(15, 130, 70, 30);
		carro2.setForeground(new Color(255,0,0));
		screen.add(carro2);
		
		vencedor = new JLabel("Vencedor");
		vencedor.setBounds(200, 200, 70, 30);
		screen.add(vencedor);
		
		txtVencedor = new JTextField();
		txtVencedor.setBounds(280, 200, 100, 30);
		txtVencedor.setEnabled(false);
		screen.add(txtVencedor);
		
		perdedor = new JLabel("Perdedor");
		perdedor.setBounds(200, 240, 70, 30);
		screen.add(perdedor);
		
		txtPerdedor = new JTextField();
		txtPerdedor.setBounds(280, 240, 100, 30);
		txtPerdedor.setEnabled(false);
		screen.add(txtPerdedor);
		
		
		// layout
		setLayout(null);
		setSize(600, 400);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
