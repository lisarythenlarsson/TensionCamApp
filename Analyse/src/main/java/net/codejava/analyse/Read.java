package net.codejava.analyse;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Read {
	/**
	 * Method that reads from a file and converts it into a String
	 */
	public String reader(String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(path));
		String everything = "null";
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			everything = sb.toString();
			System.out.println(everything);
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		finally {
			br.close();
		}
		return everything;
	}
	}
