package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.Service;

public class TaskManager {
	public TaskManager() {
		super();
	}
	
	public String so() {
		return System.getProperty("os.name");
	}
	
	public List<Service> list(){
		List<Service> services = new ArrayList<Service>();
		
		try {
			Process pr = Runtime.getRuntime().exec("tasklist");
			InputStream flow = pr.getInputStream();
			InputStreamReader reader = new InputStreamReader(flow);
			BufferedReader buffer = new BufferedReader(reader);
			String line = buffer.readLine();
			
			while (line != null) {
				Service s = new Service();				
								
				String[] name = line.split("\\s+");
				
				if(name.length > 5 && name.length < 9) {
					
					if(name[0].endsWith(".exe")) {
						s.setName(name[0]);
					}
					
					boolean isNumber = false;
					
					try {
						int valor = Integer.parseInt(name[1]);
						double mem = Double.parseDouble(name[4]);
						isNumber = true;
					}catch(NumberFormatException e) {
						isNumber = false;
					}
					
					if(isNumber) {						
						s.setPid(name[1]);
						s.setMemory(name[4]+"k");
					}
										
				}

				if(s.getName() != null) {
					services.add(s);
				}
				
				line = buffer.readLine();				
				
			}
			
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return services;		
	}

	public void kill(Object obj) {
		
		String process;
		
		try {
			int pid = Integer.parseInt(obj.toString());
			process = "TASKKILL /PID "+pid;
		}catch(NumberFormatException e1) {
			process = "TASKKILL /IM "+obj;
		}
		
		try {
			Runtime.getRuntime().exec(process);
		}catch(IOException e) {
			if(e.getMessage().contains("740")) {
				StringBuffer buffer = new StringBuffer();
				buffer.append("cmd /c ");
				buffer.append(process);
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
}
