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
	private static CameraActivity cam;
	private String txt;
	
	public void getOutputMediaFileTest(int mediaTypeImage){
		final File file = FileHandler.getOutputMediaFile(this.mediaTypeImage);
		assertTrue(file.getClass() == File.class);
	}
	
	public void deleteFromExternalStorageTest1() throws IOException {
		File file = null;
		txt = FileHandler.pathToString();
		txt.replace(".jpg", ".txt");
		
		file = new File(txt);
		FileWriter writer = new FileWriter(file);
		writer.write(1);
		
		FileHandler.deleteFromExternalStorage();
		
		assertFalse(file.exists());
	}
	/** Verify that ....  */
	public void deleteFromExternalStorageTest2() throws IOException {
		File file = new File(FileHandler.pathToString());
        if(file.exists()){
            file.delete();
        }
        
        assertFalse(file.exists());
        FileHandler.deleteFromExternalStorage();
        assertFalse(file.exists());
		
	}
}
