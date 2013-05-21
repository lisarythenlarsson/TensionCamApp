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
    private static final String TAG = "File"; 
    private static final int MEDIA_TYPE_IMAGE = 1;
    private static final int CAMERA_REQUEST = 1888; 
    private ImageView imageView;
    private static final int STD_DELAY = 400;
    protected static final String filename = "IMG_"+ "1" + ".jpg";
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        this.onResume();
        // Create an instance of Camera.  
    }   
    
 
    /** method for adding a listener and connecting the event to next activity.*/
    private void addListenerOnButton() {
		this.captureButton = (ImageButton) findViewById(R.id.button_capture_symbol);
		this.captureButton.setOnClickListener(new View.OnClickListener() {
			 
			@Override
			public void onClick(View capturebutton) {
				mCamera.takePicture(null, null, mPicture);
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
            c = Camera.open(); // attempt to get a Camera instance
            Camera.Parameters params = c.getParameters();//getting parameters 
            params.setFlashMode(Parameters.FLASH_MODE_TORCH);
            c.setParameters(params); //setting new parameters with flash
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }

	/** Create a File for saving an image or video */
    static File getOutputMediaFile(int mediaTypeImage) {
		if (!isExternalStorageWritable()){
			Log.d(TAG,"Can't access the external storage");
			return null;
		}
			File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
	    		Environment.DIRECTORY_PICTURES), "TensionCamApp");
		    	// Creating the storage directory if it does not exist
		    	if (! mediaStorageDir.exists()){
		    		if (! mediaStorageDir.mkdirs()){
		    			Log.d("TensionCamApp", "failed to create directory");
		    			return null;
		    		}
		    	}

		    	// Create a media file name
		    	 
		    	File mediaFile;
		    	if (mediaTypeImage == MEDIA_TYPE_IMAGE){
		    		mediaFile = new File(mediaStorageDir.getPath() + File.separator +
		    				filename);
		    	} else {
		    		return null;
		    	}

		    	return mediaFile;
	}


	/**checking if external storage is read and writable */
	public static boolean isExternalStorageWritable() {
	    String state = Environment.getExternalStorageState();
	    if (Environment.MEDIA_MOUNTED.equals(state)) {
	        return true;
	    }
	    return false;
	}
	
	/**generates delay needed for application to save new picture */
	private void delay(){
		try {
			Thread.sleep(STD_DELAY);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
	
	// release the camera immediately on pause event
	@Override
	protected void onPause() {
	    super.onPause();
	    mCamera.stopPreview();
	    releaseCamera();             
	}


    // release the camera for other applications
	private void releaseCamera(){
		if (this.mCamera != null){
	        this.mCamera.release();        
	        this.mCamera = null;
	    }
	}


	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//if (image exists, delete) 
			this.mCamera = getCameraInstance();
			// Create our Preview view and set it as the content of our activity.
			this.mPreview = new CameraPreview(this, this.mCamera);
        	FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        	preview.addView(this.mPreview); 
        	//add the capture button
        	addListenerOnButton();
        	// In order to receive data in JPEG format
        	this.mPicture = new PictureCallback() {
        		@Override /**creates a file when a image is taken, if the file doesn't already exists*/
        		public void onPictureTaken(byte[] data, Camera mCamera) {

    			File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
    			if (pictureFile == null){
    				Log.d(TAG, "Error creating media file, check storage permissions");
    				return;
    			}

    			try {
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
    }
			
    

}

	
	
  
	

