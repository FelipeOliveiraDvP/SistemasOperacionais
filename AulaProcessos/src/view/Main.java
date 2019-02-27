package view;

import controller.*;

public class Main {

	public static void main(String[] args) {
		OpProc opProc = new OpProc();
		//String so = opProc.so();
		//System.out.println(so);
		//String processo = "regedit.exe";
		//opProc.chamaProcesso(processo);
		//String processo = "TASKLIST /FO TABLE";
		//opProc.leProcesso(processo);
		opProc.mataProcesso("notepad.exe");
	}

}
