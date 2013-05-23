package com.example.tensioncamapp_project;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.util.Log;

public class CameraFeatures {
	private static final String TAG = "CameraFeatures";
	private Camera mCamera;
	
	public CameraFeatures(Camera c){
		this.mCamera =c;
	}

	/**evoking flash for camera*/
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
