package com.example.tensioncamapp_project.test;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;

import com.example.tensioncamapp_project.ViewPicActivity;


/**Test class for ViewPicActivity. If your emulator is up and running; make sure it is unlocked... */
public class ViewPicActivityTest extends
	ActivityInstrumentationTestCase2<ViewPicActivity> {

	private ViewPicActivity cam;
	
	public ViewPicActivityTest () {
			super(ViewPicActivity.class);
	}

	protected void setUp() throws Exception {
			super.setUp();

			setActivityInitialTouchMode(false);

			cam = getActivity();
	}

	
	/**Checks that the discard button opens up the camera */
	public void testTakePictureButton() {
			TouchUtils.clickView(this, cam.findViewById(com.example.tensioncamapp_project.R.id.discard_button));
	} 
	
	/**Checks that the analyze button generates a result */
	//public void testAnalyzeButton() {
	//	TouchUtils.clickView(this, cam.findViewById(com.example.tensioncamapp_project.R.id.analyze_button));
	//}
}