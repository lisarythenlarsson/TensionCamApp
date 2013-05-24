package com.example.tensioncamapp.activity;


import com.example.tensioncamapp.activity.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class MainActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Thread timer = new Thread (){
			public void run(){
				try {
					sleep(4000);
				}catch (InterruptedException e){
					e.printStackTrace();
				}finally {
					Intent openCamActivity = new Intent(MainActivity.this, CameraActivity.class);
					startActivity(openCamActivity);
				}
			}
		};		
		timer.start();
	}
	
	
	@Override
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
