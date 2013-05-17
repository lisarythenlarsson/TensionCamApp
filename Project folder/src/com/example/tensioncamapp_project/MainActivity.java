package com.example.tensioncamapp_project;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {
	
	Button mainbutton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		addListenerOnButton();
	}

	private void addListenerOnButton() {
		mainbutton = (Button) findViewById(R.id.start_button);
		 
		mainbutton.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View mainbutton) {
				Intent openCamActivity = new Intent(MainActivity.this, CameraActivity.class);
					startActivity(openCamActivity);
			}
				
 
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

	
}
