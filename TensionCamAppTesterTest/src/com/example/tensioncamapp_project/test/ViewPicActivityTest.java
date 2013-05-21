package com.example.tensioncamapp_project.test;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;

import com.example.tensioncamapp_project.ViewPicActivity;

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

	public void testTakePictureButton() {
			TouchUtils.clickView(this, cam.findViewById(com.example.tensioncamapp_project.R.id.discard_button));
			
	} 
}