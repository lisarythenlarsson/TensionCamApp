package com.example.tensioncamapp.test;

import android.hardware.Camera;
import android.test.AndroidTestCase;

import com.example.tensioncamapp.ctr.*;

public class TensionCameraTest extends AndroidTestCase {

	/**
	 * Checks that the method getCameraInstance returns an object of the type Camera 
	 * Requires an SD-card
	 */
	public void testGetCameraInstance() {
		assertTrue(TensionCamera.getCameraInstance().getClass() == Camera.class); 
	}
	
}