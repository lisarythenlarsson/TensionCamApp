package com.example.tensioncamapp_project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.content.Intent;
import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

public class CameraActivity extends Activity {
	private ImageButton captureButton;
	private Camera mCamera;
    private CameraPreview mPreview;
    private PictureCallback mPicture;
    private static final int STD_DELAY = 1000;
    private static final int MEDIA_TYPE_IMAGE = 1;
	protected static final String TAG = "CameraActivity";
	private File mFile;
	    
    /**Starts up the camera */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
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
        System.out.println("printar c" + c);
        return c; 
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


    /**Help method to release the camera */
	private void releaseCamera(){
		mPreview.getHolder().removeCallback(mPreview);
		//Checks if there is a camera object active
		if (this.mCamera != null){
			//Releases the camera
	        this.mCamera.release();
	        //Restore the camera object to its initial state
	        this.mCamera = null;
	    }
	}
	
	/**Activates the camera and makes it appear on the screen */
		protected void onResume() {
		// TODO Auto-generated method stub
		// deleting image from external storage
		FileHandler.deleteFromExternalStorage();
		if(this.mCamera == null){
		// Create an instance of Camera.
		this.mCamera = getCameraInstance();
		}
		// Create our Preview view and set it as the content of our activity.
		this.mPreview = new CameraPreview(this, this.mCamera);
		FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
		preview.addView(this.mPreview);
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

				File pictureFile = FileHandler.getOutputMediaFile(MEDIA_TYPE_IMAGE);

				if (pictureFile == null) {
					Log.d(TAG,
							"Error creating media file, check storage permissions");
					return;
				}

				try {
					// Writes the image to the disc
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
		 
	//    @Override /**Resets when activity destroyed*/
		/*protected void onDestroy() {
			super.onDestroy();
			releaseCamera();
		}*/


}

	
	
  
	

