package com.example.tensioncamapp_project;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Writer {
	public void writer(String s){
		PrintWriter writer;//Creates a PrintWriter
		try {
			writer = new PrintWriter("C:\\Users\\Martin\\Desktop\\Funkar.txt", "UTF-8");//Puts the path to where the PrintWriter should write		
			System.out.println(s);
			writer.println(s);//Writes to the file
			writer.close();//Closes the PrintWriter
		} catch (FileNotFoundException e) {//Handling exceptions
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}
}

