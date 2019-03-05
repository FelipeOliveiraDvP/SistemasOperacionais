package view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.TaskManager;
import model.Service;

public class View extends JFrame{
	
	/* Array de teste */
	private String[] header = {"Nome do processo", "PID", "Uso de Memoria"};
	private Object[][] data = {
			{"processo 1.exe", "3333", "88.541k"},
			{"processo 2.exe", "3334", "88.541k"},
			{"processo 3.exe", "3335", "88.541k"}
	};
	
	private JButton btnAtualizar, btnSair;
	private JTable table;
	private JScrollPane scroll;
	private DefaultTableModel model;
	
	public View() {
		super("Task Manager - Exercicio 2");
		Container screen = getContentPane();
		/* Elementos */
		btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setBounds(15, 15, 85, 20);
		
		btnSair = new JButton("Sair");
		btnSair.setBounds(115, 15, 85, 20);
		
		model = new DefaultTableModel();
		model.addColumn("Nome do processo");
		model.addColumn("PID");
		model.addColumn("Uso de Memória");
				
		table = new JTable(model);		
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		
		scroll = new JScrollPane(table);
		scroll.setBounds(15, 50, 360, 400);
		/* Action Listeners */
		btnAtualizar.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				loadTasks(model);				
			}
		});
		
		btnSair.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
							
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// Clique com botão direito do mouse
				if(e.getClickCount() == 1 &&
				   e.getButton() == MouseEvent.BUTTON3) {
					int selectedRow = -1;
					selectedRow = table.getSelectedRow();
					if(selectedRow >= 0) {
						int op = -1;
						Object obj = null;
						// Processo por nome
						if(table.getSelectedColumn() == 0) {
							obj = table.getValueAt(selectedRow, 0);
							op = JOptionPane.showConfirmDialog(null, 
				                      							"Deseja Matar o seguinte processo\n"
				                      							+ "Nome: "+obj+"?",
				                      							"Encerrar processo", JOptionPane.YES_NO_CANCEL_OPTION);
						// Processo por PID
						}else if(table.getSelectedColumn() == 1) {
							obj = table.getValueAt(selectedRow, 1);
							op = JOptionPane.showConfirmDialog(null, 
									                      "Deseja Matar o seguinte processo\n"
									                      + "PID: "+obj+"?",
									                      "Encerrar processo", JOptionPane.YES_NO_CANCEL_OPTION);
						}					
						
						if(op == JOptionPane.YES_OPTION) {
							killTask(obj);
						}
					}
				}	
				
			}
		});
		/* Add elements */
		screen.add(btnAtualizar);
		screen.add(btnSair);
		screen.add(scroll);
		/* Carrega os processo que estao sendo executados */
		loadTasks(model);
		/* Configurações */
		setSize(400, 500);
		setLayout(null);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private static void loadTasks(DefaultTableModel model) {
		// Limpa a tabela
		model.setNumRows(0);
		// Carrega as tarefas retornadas pelo tasklist e preenche a tabela
		TaskManager tm = new TaskManager();
		
		//model.addRow(new Object[] {"processo1.exe", "3322", "18.545k"});
		for(Service obj:tm.list()) {
			model.addRow(new Object[] {obj.getName(), obj.getPid(), obj.getMemory()});
		}
		
	}
	
	private static void killTask(Object obj) {
		TaskManager tm = new TaskManager();
		
		tm.kill(obj);
	}
}
