package com.example.tensioncamapp_project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.FrameLayout;

public class CameraActivity extends Activity {
	private Button captureButton;
	private Camera mCamera;
    private CameraPreview mPreview;
    private CameraPictureCallback mCallback;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        // Create an instance of Camera.  
        this.mCamera = getCameraInstance();
        //add the capture button
        addListenerOnButton();
        // Create our Preview view and set it as the content of our activity.
        this.mPreview = new CameraPreview(this, this.mCamera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(this.mPreview); 
        this.mCallback = new CameraPictureCallback();
       
    }
    
    private void addListenerOnButton() {
		this.captureButton = (Button) findViewById(R.id.button_capture);
		//add an onCickListener
	}

	/** A safe way to get an instance of the Camera object. Code collected from elsewhere */
    public static Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open(); // attempt to get a Camera instance
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }

    
	
}
