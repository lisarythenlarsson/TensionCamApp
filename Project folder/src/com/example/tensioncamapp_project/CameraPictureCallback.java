package com.example.tensioncamapp_project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Environment;
import android.util.Log;

public class CameraPictureCallback {
	private static final String TAG = "File"; 
    private static final int MEDIA_TYPE_IMAGE = 1;
	 // In order to receive data in JPEG format
    
    public CameraPictureCallback() {
    	final PictureCallback mPicture = new PictureCallback() {

    		@Override
    		public void onPictureTaken(byte[] data, Camera camera) {

    			File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
    			if (pictureFile == null){
    				Log.d(TAG, "Error creating media file, check storage permissions");
    				return;
    			}

    			try {
    				FileOutputStream fos = new FileOutputStream(pictureFile);
    				fos.write(data);
    				fos.close();
    			} catch (FileNotFoundException e) {
    				Log.d(TAG, "File not found: " + e.getMessage());
    			} catch (IOException e) {
    				Log.d(TAG, "Error accessing file: " + e.getMessage());
    			}
    		}
    		
    		/** Create a File for saving an image or video */
			private File getOutputMediaFile(int mediaTypeImage) {
				if (!isExternalStorageWritable()){
					Log.d(TAG,"Can't access the extrenal storage");
					return null;
				}
					File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
			    		Environment.DIRECTORY_PICTURES), "TensionCamApp");
				    	// Creating the storage directory if it does not exist
				    	if (! mediaStorageDir.exists()){
				    		if (! mediaStorageDir.mkdirs()){
				    			Log.d("TensionCamApp", "failed to create directory");
				    			return null;
				    		}
				    	}

				    	// Create a media file name, might want to include location
				    	String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date(0));
				    	File mediaFile;
				    	if (mediaTypeImage == MEDIA_TYPE_IMAGE){
				    		mediaFile = new File(mediaStorageDir.getPath() + File.separator +
				    				"IMG_"+ timeStamp + ".jpg");
				    	} else {
				    		return null;
				    	}

				    	return mediaFile;
				}
			}; 
		
    	}
		/**checking if external storage is read and writable */
		public static boolean isExternalStorageWritable() {
    		    String state = Environment.getExternalStorageState();
    		    if (Environment.MEDIA_MOUNTED.equals(state)) {
    		        return true;
    		    }
    		    return false;
    }
}