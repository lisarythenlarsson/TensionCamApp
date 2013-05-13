package com.example.tensioncamapp_project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
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
    				Log.d(TAG, "Error creating media file, check storage permissions: ");
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

			private File getOutputMediaFile(int mediaTypeImage) {
				// TODO Auto-generated method stub
				return null;
			}
    	};  
    }
}