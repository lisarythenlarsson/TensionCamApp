package com.example.tensioncamapp.utils;

 /**
 * @author Lisa RythŽn Larsson, Fredrik Johansson
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


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Environment;
import android.util.Log;

public class FileHandler {
	private static final String TAG = "File"; 
	protected static final String filename = "IMG"+ "1" + ".jpg";
	private static final int MEDIA_TYPE_IMAGE = 1;
		

	
	/** Create a File for saving an image and returning file name */
    public static File getOutputMediaFile(int mediaTypeImage) {
		if (!isExternalStorageWritable()){ //checking if the external storage is accessible
			Log.d(TAG,"Can't access the external storage");
		return null;
		}//creating a file for storage directory
		File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
	    Environment.DIRECTORY_PICTURES), "TensionCamApp");
		    	
		// Creating the storage directory if it does not exist
		if (! mediaStorageDir.exists()){
			if (! mediaStorageDir.mkdirs()){
				Log.d("TensionCamApp", "failed to create directory");
		    return null;
		    }
		}

		// Creating a media file name
		File mediaFile;
		if (mediaTypeImage == MEDIA_TYPE_IMAGE){
			mediaFile = new File(pathToString());
		} else {
			return null;
		}
	   	return mediaFile;
	}

     /**Creating a name of file in the right directory. Nämn också att filnamnet är hårdkodat*/

    public static String pathToString(){
    	String path;
    	File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
	    		Environment.DIRECTORY_PICTURES), "TensionCamApp");
    	path = mediaStorageDir.getPath() + File.separator + filename;
    	return path;
    }

    public static void writeToFile(byte[] data, File f){
    	try {
			// Writes the image to the disc
			FileOutputStream fos = new FileOutputStream(f);
			fos.write(data);
			fos.close();
		} catch (FileNotFoundException e) {
			Log.d(TAG, "File not found: " + e.getMessage());
		} catch (IOException e) {
			Log.d(TAG, "Error accessing file: " + e.getMessage());
		}
    }
   
    /**checking if external storage is read and writable */
	private static boolean isExternalStorageWritable() {
	    String state = Environment.getExternalStorageState();
	    if (Environment.MEDIA_MOUNTED.equals(state)) {
	        return true;
	    }
	    return false;
	}

	/**deleting file if it exists in directory*/
	public static void deleteFromExternalStorage () {
	    String mediaStorage = new String (pathToString());
		try { // if file exists, it should be deleted
	        File file = new File(mediaStorage);

	        if(file.exists())
	            file.delete();
	    }
	    catch (Exception e)
	    {
	        Log.e("App", "Exception while deleting file " + e.getMessage());
	    }
	}
	
}
