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
