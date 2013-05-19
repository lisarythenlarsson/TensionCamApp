package net.codejava.analyse;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CommandExecution {
	public CommandExecution(String commandline) {
		try {
			String line;
			Process p = Runtime.getRuntime().exec(commandline);//Creates a process that executes a commandline, in this case a .bat
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));//Reads the response?
			while ((line = input.readLine()) != null) {
				System.out.println(line);//Prints the response
			}
			input.close();//Closes the bufferedreader
		} catch (Exception err) {
			err.printStackTrace();
		}
	}
	public static void main(String argv[]) {
	   new CommandExecution("C:\\Users\\Martin\\hw.bat");//Executes the .bat file
	}
}
