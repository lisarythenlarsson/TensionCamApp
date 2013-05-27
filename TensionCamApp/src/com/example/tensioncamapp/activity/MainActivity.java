package com.example.tensioncamapp.activity;


import com.example.tensioncamapp.activity.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;

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

public class MainActivity extends Activity {


	protected static final String TAG = "MainActivity";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Thread timer = new Thread (){
			//Sets an timer for the activity
			public void run(){
				try {
					sleep(6000);
				}catch (InterruptedException e){
					Log.d(TAG, "failed to sleep" + e.getMessage());
				}finally {
					Intent openCamActivity = new Intent(MainActivity.this, CameraActivity.class);
					startActivity(openCamActivity);
				}
			}
		};		
		timer.start();
	}
	
	
	@Override /**removes activity from stack when paused*/
	protected void onPause() {
		super.onPause();
		finish();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

	
}
