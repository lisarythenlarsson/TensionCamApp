package net.codejava.analyse;

/**
 * Method that executes a commandline
 */
public class CommandExecution {
	public CommandExecution(String commandline) {
		try {
			Runtime.getRuntime().exec(commandline);//Creates a process that executes a commandline, in this case a .bat
		} catch (Exception err) {
			err.printStackTrace();
		}
	}
}
