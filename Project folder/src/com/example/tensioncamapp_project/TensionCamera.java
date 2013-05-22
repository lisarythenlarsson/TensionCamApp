package com.example.tensioncamapp_project;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.util.Log;

public class TensionCamera {
	private static final String TAG = "TensionCamera";
	private static Camera mCamera;
	
	TensionCamera() {
		//empty constructor
		
	}


	/** A safe way to get an instance of the Camera object. Code collected from elsewhere */
   public static Camera getCameraInstance(){
        Camera c = null;
        try {
        	// attempt to get a Camera instance
            c = Camera.open(); 
            //getting current parameters
            Camera.Parameters params = c.getParameters(); 
            //setting new parameters with flash
            params.setFlashMode(Parameters.FLASH_MODE_TORCH);
            c.setParameters(params); 
        }
        catch (Exception e){
           Log.e(TAG, "camera not available" + e.getMessage()); // (in use or does not exist)
        }
        // returns null if camera is unavailable
        return c; 
    }
   
   /**Help method to release the camera */
  	public static void releaseCamera(){
  		//Checks if there is a camera object active
  		if (mCamera != null){
  			//Releases the camera
  	        mCamera.release();
  	        //Restore the camera object to its initial state
  	        mCamera = null;
  	    }
  	}
}
