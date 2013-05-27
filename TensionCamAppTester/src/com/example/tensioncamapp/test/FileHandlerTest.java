package com.example.tensioncamapp.test;

/**
 * @author Max Dubois, Fredrik Johansson
 * @copyright Lisa RythŽn Larsson, Fredrik Johansson, Max Dubois, Martin Falk Danauskis
 * 
 * Copyright 2013 Fredrik Johansson, Lisa Rythén Larsson, Martin Falk Danauskis, Max Dubois
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *  
 *  */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import com.example.tensioncamapp.utils.FileHandler;

import android.test.AndroidTestCase;

public class FileHandlerTest extends AndroidTestCase {
	
	private int GoodInput = 1; //The int 1 represents an image file
	private int BadInput = 2; //This int does NOT represent an image file

	/**Checks that getOutputMediaFile() dosen't manipulate the type of the file
	 * TestID: TC02  */
	public void getOutputMediaFileTest1(){
		final File file = FileHandler.getOutputMediaFile(GoodInput);
		assertTrue(file.getClass().equals(File.class)); //checks that the mFile still is of the type File
	}
	
	/**Checks that a bad input to getOutputMediaFile() does not crash the program
	 * TestID: TC03 */
	
	public void getOutputMediaFileTest2(){
		final File file = FileHandler.getOutputMediaFile(BadInput); //Provides the method() with faulty input
	}
	
	/**Verifies that the deleteFromExternalStorage() method deletes files 
	 * TestID: TC04*/
	public void deleteFromExternalStorageTest1() throws IOException {
		String s = "Hello World!"; 
		byte[] bytes = s.getBytes(); //Converts the string 'Hello World!' to bytes
		File file = new File(FileHandler.pathToString()); //creates a file at the location from the pathToString() method
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(bytes);
		fos.close();
		assertTrue(file.exists()); //checks that a file is created
		FileHandler.deleteFromExternalStorage(); //deletes the file by using the method deleteFromExternalStorage()
		assertFalse(file.exists()); //checks that the file is deleted
	}
	/** Verifies that the method deleteFromExternalStorage() dosen't crash the program is there are no files to delete 
	 * TestID: TC05*/
	public void deleteFromExternalStorageTest2() throws IOException {
		File file = new File(FileHandler.pathToString()); //saves a new file or over an existing one one a given location 
        if(file.exists()){
            file.delete(); //makes sure that the location is empty
        }
        assertFalse(file.exists()); //checks that the location is empty
        FileHandler.deleteFromExternalStorage(); //trying to delete the file from the now empty location
        assertFalse(file.exists()); //checks that the location is still empty and therefore hasn't been modified by deleteFromExternalStorage()	
	}
	
	/**
	 * Verifies that writeToFile actually writes a file to the disc with the given input
	 * Requires an SD-card because the path file is set to a external storage
	 *  TestID: TC06
	 * @throws IOException
	 */
	
	public void writeToFileTest() throws IOException {
		String s = "Hello World!"; 
		byte[] bytes = s.getBytes(); //Converts the string 'Hello World!' to bytes
		String path = FileHandler.pathToString().replace("jpg", "txt"); //Change the type of the file from 'jpg' to 'txt'
		File file = new File(path); //Creates a new 'txt' file with the content 'Hello World!'
		FileHandler.writeToFile(bytes, file); //Writes the files to the disc
		BufferedReader reader = new BufferedReader(new FileReader(file)); //Establish a connection the the file
		String text = reader.readLine(); //Fetches the first line from the file
		reader.close();
		assertEquals(s, text); //Checks that the input actually is on the file
		
	}
}