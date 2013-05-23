package com.example.tensioncamapp_project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Intent;
import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;

public class CameraActivity extends Activity implements View.OnClickListener {
	private ImageButton captureButton;
	private Button flashButton;
	private Camera mCamera;
    private CameraPreview mPreview;
    private TensionCamera mFeature;
    private PictureCallback mPicture;
    private static final int STD_DELAY = 1000;
    private static final int MEDIA_TYPE_IMAGE = 1;
	protected static final String TAG = "CameraActivity";
	private int flashclicks = 0;
	    
    /**Starts up the camera */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
       }   
    

	/**Connects the capture button on the view to a listener*/
    private void addListenerOnButton() {
		this.captureButton = (ImageButton) findViewById(R.id.button_capture_symbol);
		this.flashButton = (Button) findViewById(R.id.flash_button);
		
		this.captureButton.setOnClickListener(this);
		this.flashButton.setOnClickListener(this);
    }
			 
	@Override /** performing actions depending on which button being pressed*/
	public void onClick(View v) {
		switch(v.getId()) {
			case R.id.button_capture_symbol:
				//taking a picture and switching activity
				CameraActivity.this.mCamera.takePicture(null, null, CameraActivity.this.mPicture);
				this.captureButton.setEnabled(false);
				delay();	
				Intent viewPic = new Intent(CameraActivity.this, ViewPicActivity.class);
				startActivity(viewPic);
				break;
			case R.id.flash_button:
				//activating flash
				if(this.flashclicks == 0){
					this.mFeature.activateFlash();
					this.flashclicks ++;
				//disactivating flash
				}else {
					this.mFeature.disactivateFlash();
					this.flashclicks --;
				}
		}
	}
		
	
	/**Generates a delay needed for application to save new pictures */
	private static void delay(){
		try {
			//Makes the program inactive for a specific amount of time
			Thread.sleep(STD_DELAY);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
	
	/**Method for releasing the camera immediately on pause event*/
	@Override
	protected void onPause() {
	    //Shuts down the preview shown on the screen
	    this.mCamera.stopPreview();
	    //Calls an internal help method to restore the camera
	    try{
	    	releaseCamera();
	    }catch(NullPointerException e){
	    	
	    	Log.e(TAG, e.getMessage());
	    }
	    super.onPause();
	}


    /**Releasing the camera so that other applications can use the camera*/
	private void releaseCamera(){
		this.mPreview.getHolder().removeCallback(this.mPreview);
		//Checks if there is a camera object active
		if (this.mCamera != null){
			//Releases the camera
	        this.mCamera.release();
	        //Restore the camera object to its initial state
	        this.mCamera= null;
	    }
	}
	
	/**Activates the camera and makes it appear on the screen */
		protected void onResume() {
		// TODO Auto-generated method stub
		// deleting image from external storage
		FileHandler.deleteFromExternalStorage();
		//creating an TensionCamera instance
		this.mFeature = new TensionCamera(this.mCamera);
		// Create an instance of Camera.
		this.mCamera = TensionCamera.getCameraInstance();
		// Create our Preview view and set it as the content of our activity.
		this.mPreview = new CameraPreview(this, this.mCamera);
		FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
		preview.addView(this.mPreview);
		//Create Camera Features
		
		// add the capture button
		addListenerOnButton();
		// In order to receive data in JPEG format
		this.mPicture = new PictureCallback() {

			/**
			 * Creates a file when a image is taken, if the file doesn't already
			 * exists
			 */
			@Override
			public void onPictureTaken(byte[] data, Camera mCamera) {
				//creating an file to write the image to
				File pictureFile = FileHandler.getOutputMediaFile(MEDIA_TYPE_IMAGE);

				if (pictureFile == null) {
					Log.d(TAG,
							"Error creating media file, check storage permissions");
					return;
				}
				FileHandler.writeToFile(data, pictureFile);
				/**try {
					// Writes the image to the disc
					FileOutputStream fos = new FileOutputStream(pictureFile);
					fos.write(data);
					fos.close();
				} catch (FileNotFoundException e) {
					Log.d(TAG, "File not found: " + e.getMessage());
				} catch (IOException e) {
					Log.d(TAG, "Error accessing file: " + e.getMessage());
				}*/
			}
		};
		
		super.onResume();
	}
	


}

	
	
  
	

