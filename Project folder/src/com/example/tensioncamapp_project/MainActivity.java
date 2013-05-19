package com.example.tensioncamapp_project;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

	
}
