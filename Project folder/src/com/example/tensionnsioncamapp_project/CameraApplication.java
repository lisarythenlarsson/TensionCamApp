package com.example.tensionnsioncamapp_project;
import android.hardware.Camera;

public class CameraApplication extends Camera{

	CameraApplication() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/** A safe way to get an instance of the Camera object. */
	public static Camera getCameraInstance(){
	    Camera c = null;
	    try {
	        c = Camera.open(); // attempt to get a Camera instance
	    }
	    catch (Exception e){
	    	e.printStackTrace();
	        // Camera is not available (in use or does not exist)
	    }
	    return c; // returns null if camera is unavailable
	}
	

}
