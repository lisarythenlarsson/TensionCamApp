package net.codejava.analyse;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class ReadWrite {
	//The reader method
	public String reader() throws IOException {
		//Creates a BufferedReader with a path from where it should read
		BufferedReader br = new BufferedReader(
				new FileReader("C:\\Users\\Martin\\Documents\\workspace-sts-3.2.0.RELEASE\\HelloSpringMVC\\file.txt"));
		String everything;
		try {
			StringBuilder sb = new StringBuilder();//Creates a stringbuilder
			String line = br.readLine();//Creates a string which takes in a line at a time

			while (line != null) {//Iterates through all lines of the document
				sb.append(line);//Writes the lines to the Stringbuilder
				sb.append("\n");
				line = br.readLine();//Reads the next line
			}
			everything = sb.toString();//Turns the stringbuilder into a string
			System.out.println(everything);
		} finally {
			br.close();//Closes the bufferedreader
		}
		return everything;//Returns the string that contains all the text
	}

	public void writer(String s) {
		PrintWriter writer;//Creates a PrintWriter
		try {
			//Decides where the printwriter should write to, if the file doesn't exists, it is created.
			writer = new PrintWriter(
					"C:\\Users\\Martin\\Documents\\workspace-sts-3.2.0.RELEASE\\HelloSpringMVC\\resultat.txt",
					"UTF-8");
			System.out.println(s);
			writer.println(s);//Writes the received string to the file
			writer.close();//Closes the PrinWriter
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
