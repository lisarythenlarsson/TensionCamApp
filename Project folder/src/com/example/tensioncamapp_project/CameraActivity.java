package com.example.tensioncamapp_project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import android.content.Intent;
import android.app.Activity;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

public class CameraActivity extends Activity {
	private ImageButton captureButton;
	private Camera mCamera;
    private CameraPreview mPreview;
    private PictureCallback mPicture;
    private ImageView imageView;
    private static final int STD_DELAY = 400;
    private static final int MEDIA_TYPE_IMAGE = 1;
	protected static final String TAG = "CameraActivity";
    
    /**Starts up the camera */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        onResume();
          
    }   
    
 
    /**Connects the capture button on the view to a listener
     *  and redirects the client to a preview of the captures image*/
    private void addListenerOnButton() {
		this.captureButton = (ImageButton) findViewById(R.id.button_capture_symbol);
		this.captureButton.setOnClickListener(new View.OnClickListener() {
			 
			@Override
			public void onClick(View capturebutton) {
				CameraActivity.this.mCamera.takePicture(null, null, CameraActivity.this.mPicture);
				delay();
				Intent viewPic = new Intent(CameraActivity.this, ViewPicActivity.class);
				startActivity(viewPic);
			}
		});
	}
    

	
	/**Generates a delay needed for application to save new pictures */
	private static void delay(){
		try {
			//Makes the program inactive for a specific amout of time
			Thread.sleep(STD_DELAY);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
	
	/**Method for releasing the camera immediately on pause event*/
	@Override
	protected void onPause() {
	    super.onPause();
	    //Shuts down the preview shown on the screen
	    this.mCamera.stopPreview();
	    //Calls an internal help method to restore the camera
	    TensionCamera.releaseCamera();             
	}


	
	/**Activates the camera and makes it appear on the screen */
		protected void onResume() {
		// deleting image from external storage
		FileHandler.deleteFromExternalStorage();
		// Create an instance of Camera.
		if (mCamera == null){
			this.mCamera = TensionCamera.getCameraInstance();}
		// Create our Preview view and set it as the content of our activity.
		this.mPreview = new CameraPreview(this, this.mCamera);
    	FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
    	preview.addView(this.mPreview); 
    	//add the capture button
    	addListenerOnButton();
    	// In order to receive data in JPEG format
    	this.mPicture = new PictureCallback() {
    		
    		/**Creates a file when a image is taken, if the file doesn't already exists*/
    		@Override 
    		public void onPictureTaken(byte[] data, Camera mCamera) {
    		//creating file for storage on external storage
			File pictureFile = FileHandler.getOutputMediaFile(MEDIA_TYPE_IMAGE);
			
			if (pictureFile == null){
				Log.d(TAG, "Error creating media file, check storage permissions");
				return;
			}

			try {
				//Writes the image to the disc
				FileOutputStream fos = new FileOutputStream(pictureFile);
				fos.write(data);
				fos.close();
			} catch (FileNotFoundException e) {
				Log.d(TAG, "File not found: " + e.getMessage());
			} catch (IOException e) {
				Log.d(TAG, "Error accessing file: " + e.getMessage());
			}
		}
	};
    	super.onResume();
    }
			
	

}

	
	
  
	

