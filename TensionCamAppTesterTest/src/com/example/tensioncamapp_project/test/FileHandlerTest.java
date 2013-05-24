package com.example.tensioncamapp_project.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.example.tensioncamapp_project.CameraActivity;
import com.example.tensioncamapp_project.FileHandler;

import android.test.AndroidTestCase;
import android.test.TouchUtils;

public class FileHandlerTest extends AndroidTestCase {
	
	private int mediaTypeImage = 1; //The int 1 represents an image file

	
	/**Checks that getOutputMediaFileTest dosen't manipulate the type of the file  */
	public void getOutputMediaFileTest(int mediaTypeImage){
		final File mFile = FileHandler.getOutputMediaFile(this.mediaTypeImage);
		assertTrue(mFile.getClass() == File.class); //checks that the mFile still is of the type File
	}
	
	/**Verifies that the deleteFromExternalStorage() method deletes files */
	public void deleteFromExternalStorageTest1() throws IOException {
		File file = new File(FileHandler.pathToString()); //creates a file at the location from the pathToString() method
		assertTrue(file.exists()); //checks that a file is created
		FileHandler.deleteFromExternalStorage(); //deletes the file by using the method deleteFromExternalStorage()
		assertFalse(file.exists()); //checks that the file is deleted
	}
	/** Verifies that the method deleteFromExternalStorage() dosen't crash the program is there are no files to delete */
	public void deleteFromExternalStorageTest2() throws IOException {
		File file = new File(FileHandler.pathToString()); //saves a new file or over an existing one one a given location 
        if(file.exists()){
            file.delete(); //makes sure that the location is empty
        }
        assertFalse(file.exists()); //checks that the location is empty
        FileHandler.deleteFromExternalStorage(); //trying to delete the file from the now empty location
        assertFalse(file.exists()); //checks that the location is still empty and therefore hasn't been modified by deleteFromExternalStorage()
		
	}
}
