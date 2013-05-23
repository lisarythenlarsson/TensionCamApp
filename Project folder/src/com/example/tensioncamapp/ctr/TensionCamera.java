package com.example.tensioncamapp.ctr;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.util.Log;

public class TensionCamera {
	private static final String TAG = "CameraFeatures";
	private Camera mCamera;
	
	public TensionCamera(Camera c){
		this.mCamera =c;
	}


	/** A safe way to get an instance of the Camera object.*/
   public static Camera getCameraInstance(){
        Camera c = null;
        try {
        	// attempt to get a Camera instance
            c = Camera.open();  
        }
        catch (Exception e){
           Log.e(TAG, "camera not available" + e.getMessage()); // (in use or does not exist)
        }
        // returns null if camera is unavailable
        System.out.println("printar c" + c);
        return c; 
    }
	
	/**activating flash for camera*/
	public void activateFlash(){
		try{
		//getting current parameters
        Camera.Parameters params = this.mCamera.getParameters(); 
        //setting new parameters with flash
        params.setFlashMode(Parameters.FLASH_MODE_TORCH);
        this.mCamera.setParameters(params);
		} catch (Exception e){
			Log.d(TAG, "Failed to activate flash" + e.getMessage());
		}
	}
	
	public void disactivateFlash(){
		try{
			//getting current parameters
	        Camera.Parameters params = this.mCamera.getParameters(); 
	        //setting new parameters with flash
	        params.setFlashMode(Parameters.FLASH_MODE_OFF);
	        this.mCamera.setParameters(params);
			} catch (Exception e){
				Log.d(TAG, "Failed to disactivate flash" + e.getMessage());
			}
	}
	
}
