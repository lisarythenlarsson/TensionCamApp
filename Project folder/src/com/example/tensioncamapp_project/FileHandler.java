package com.example.tensioncamapp_project;

import java.io.File;

import android.os.Environment;
import android.util.Log;

public class FileHandler {
	private static final String TAG = "File"; 
	protected static final String filename = "IMG"+ "1" + ".jpg";
	private static final int MEDIA_TYPE_IMAGE = 1;
	
	public FileHandler(){
		//TODO: write constructor
	}
	
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
		    		mediaFile = new File(mediaStorageDir.getPath() + File.separator +
		    				filename);
		    	} else {
		    		return null;
		    	}

		    	return mediaFile;
	}

   
   
    /**checking if external storage is read and writable */
	public static boolean isExternalStorageWritable() {
	    String state = Environment.getExternalStorageState();
	    if (Environment.MEDIA_MOUNTED.equals(state)) {
	        return true;
	    }
	    return false;
	}

	/**deleting file if it exists in directory*/
	public static void deleteFromExternalStorage () {
	    File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
	    		Environment.DIRECTORY_PICTURES), "TensionCamApp");
		try { // if file exists, it should be deleted
	        File file = new File(mediaStorageDir, filename);
	        if(file.exists())
	            file.delete();
	    }
	    catch (Exception e)
	    {
	        Log.e("App", "Exception while deleting file " + e.getMessage());
	    }
	}
}
