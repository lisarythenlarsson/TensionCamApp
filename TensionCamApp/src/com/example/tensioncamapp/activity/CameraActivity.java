package com.example.tensioncamapp.activity;

import java.io.File;

import com.example.tensioncamapp.activity.R;
import com.example.tensioncamapp.ctr.TensionCamera;
import com.example.tensioncamapp.utils.FileHandler;
import com.example.tensioncamapp.views.CameraPreview;

import android.content.Intent;
import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;

/**
 * @author Lisa Rythén Larsson, Fredrik Johansson
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

public class CameraActivity extends Activity implements View.OnClickListener {
	private ImageButton captureButton;
	private Button flashButton;
	private Camera mCamera;
    private CameraPreview mPreview;
    private TensionCamera mFeature;
    //Callback interface used to supply image data from a photo capture.
    private PictureCallback mPicture;
    private static final int STD_DELAY = 1000;
    private static final int MEDIA_TYPE_IMAGE = 1;
	protected static final String TAG = "CameraActivity";
	// counter for when flash button has been clicked
	private int flashclicks = 0;
	    
   
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
		
		this.captureButton.setEnabled(true);
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
		if (this.mCamera != null){
			this.mCamera.release();
	        //Restore the camera object to its initial state
	        this.mCamera= null;
	    }
	}
	/**@result [Camera, PictureCallback, CameraPreview, TensionCamera]*/
	/**Activates the camera and makes it appear on the screen */
		protected void onResume() {
		FileHandler.deleteFromExternalStorage();
		// Create an instance of Camera.
		this.mCamera = TensionCamera.getCameraInstance();
		// Create our Preview view and set it as the content of our activity.
		this.mPreview = new CameraPreview(this, this.mCamera);
		FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
		preview.addView(this.mPreview);
		this.mFeature = new TensionCamera(this.mCamera);
		addListenerOnButton();
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
				//saves data on created file
				FileHandler.writeToFile(data, pictureFile);
			}
		};
		
		super.onResume();
	}
	
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			return super.onCreateOptionsMenu(menu);
		}

}

	
	
  
	

