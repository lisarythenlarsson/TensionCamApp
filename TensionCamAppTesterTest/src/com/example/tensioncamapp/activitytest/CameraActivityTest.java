package com.example.tensioncamapp.activitytest;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;

import com.example.tensioncamapp.activity.CameraActivity;
import com.example.tensioncamapp.utils.FileHandler;
import com.example.tensioncamapp.ctr.TensionCamera;

public class CameraActivityTest extends ActivityInstrumentationTestCase2<CameraActivity> {

	private static CameraActivity cam;
	
	public CameraActivityTest () {
		super(CameraActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();

		setActivityInitialTouchMode(false);

		cam = getActivity();
	}
	
	/**Checks that a picture is being saved once the capture button is pressed*/
	public void testCaptureButton() {
		
		FileHandler.deleteFromExternalStorage(); //wipes the directory
		TouchUtils.clickView(this, cam.findViewById(com.example.tensioncamapp.activity.R.id.button_capture_symbol)); //captures a picture
		assertTrue(!FileHandler.pathToString().isEmpty()); //checks that there is a file stores in the directory
	
	}	
	
}
