package com.example.tensioncamapp.test;

/**
 * @author Max Dubois, Fredrik Johansson
 * @copyright Lisa RythŽn Larsson, Fredrik Johansson, Max Dubois, Martin Falk Danauskis
 * 
 * Copyright 2013 Fredrik Johansson, Lisa Rythén Larsson, Martin Falk Danauskis, Max Dubois
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

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;

import com.example.tensioncamapp.activity.CameraActivity;
import com.example.tensioncamapp.utils.FileHandler;

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
		assertFalse(FileHandler.pathToString().isEmpty()); //checks that there is a file stores in the directory
	}	

	
}
