package com.example.tensioncamapp.ctr;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.util.Log;

/**
 * @author Lisa Rythén Larsson
 * @copyright Lisa Rythén Larsson, Fredrik Johansson, Max Dubois, Martin Falk Danauskis
 * 
 * Copyright 2013 Fredrik Johansson, Lisa RythÈn Larsson, Martin Falk Danauskis, Max Dubois
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
