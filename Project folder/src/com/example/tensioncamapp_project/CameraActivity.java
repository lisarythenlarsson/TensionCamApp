package com.example.tensioncamapp_project;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.widget.FrameLayout;

public class CameraActivity extends Activity {

	private Camera mCamera;
    private CameraPreview mPreview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        // Create an instance of Camera. Call method from class Main Activity
        this.mCamera = getCameraInstance();

        // Create our Preview view and set it as the content of our activity.
        this.mPreview = new CameraPreview(this, this.mCamera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(this.mPreview);
    }
    
	// Getting an instance of the Camera object.
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
