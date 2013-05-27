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

import com.example.tensioncamapp.activity.R;
import com.example.tensioncamapp.activity.ViewPicActivity;


/**Test class for ViewPicActivity
 * TestID: TC07 */
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

	/**Checks that the discard button opens up the camera 
	 * TestID: TC08*/
	public void testTakePictureButton() {
			TouchUtils.clickView(this, cam.findViewById(R.id.discard_button));//Presses the button take new picture
			assertTrue(cam.getCurrentFocus() == cam.findViewById(R.layout.activity_camera));//Checks that the application is in camera mode
	} 
}