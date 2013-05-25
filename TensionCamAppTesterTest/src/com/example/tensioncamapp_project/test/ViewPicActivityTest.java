package com.example.tensioncamapp_project.test;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;

import com.example.tensioncamapp.activity.R;
import com.example.tensioncamapp.activity.ViewPicActivity;


/**Test class for ViewPicActivity */
public class ViewPicActivityTest extends
	ActivityInstrumentationTestCase2<ViewPicActivity> {

	private ViewPicActivity cam;
	
	public ViewPicActivityTest() {
		super(ViewPicActivity.class);
	}

	protected void setUp() throws Exception {
			super.setUp();

			setActivityInitialTouchMode(false);

			cam = getActivity();
	}

	/**Checks that the discard button opens up the camera */
	public void testTakePictureButton() {
			TouchUtils.clickView(this, cam.findViewById(com.example.tensioncamapp.activity.R.id.discard_button));//Presses the button take new picture
			assertTrue(cam.getCurrentFocus() == cam.findViewById(R.layout.activity_camera));//Checks that the application is in camera mode
	} 
}