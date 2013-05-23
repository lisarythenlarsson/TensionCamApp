package com.example.tensioncamapp_project;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ViewPicActivity extends Activity implements View.OnClickListener {

	private Button discard;
	private Button analyze;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_pic);
		ImageView jpgView = (ImageView) findViewById(R.id.imageView);
		Displayer.displayImage(jpgView);
		addListenerOnButton();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_pic, menu);
		return true;
	}
	
	
	
	private void addListenerOnButton() {
		this.discard = (Button) findViewById(R.id.discard_button);
		this.analyze = (Button) findViewById(R.id.analyze_button);
		 
		this.discard.setOnClickListener(this);
		this.analyze.setOnClickListener(this);
	}
		
	/**Added switch-clauses to enable functionality for two buttons */	
 	@Override
	public void onClick(View v) {
		switch(v.getId()) {
			case R.id.discard_button:
				Intent openCamActivity = new Intent(ViewPicActivity.this, CameraActivity.class);
				startActivity(openCamActivity);
				break;
			case R.id.analyze_button:
				
				Intent opentestActivity = new Intent(ViewPicActivity.this, MainActivity.class);
				startActivity(opentestActivity);
			break;			
		}
	}
 	
 	
}

