package com.example.tensioncamapp_project;

import java.io.File;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class ViewPicActivity extends Activity implements View.OnClickListener {

	private String TAG = "ViewPicActivity";
	private Button discard;
	private Button send;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_pic);
		displayImage();
		addListenerOnButton();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_pic, menu);
		return true;
	}
	
	
	
	private void addListenerOnButton() {
		discard = (Button) findViewById(R.id.discard_button);
		send = (Button) findViewById(R.id.send_button);
		 
		discard.setOnClickListener(this);
		send.setOnClickListener(this);
	}
		
	/**Added switch-clauses to enable functionality for two buttons */	
 	@Override
	public void onClick(View v) {
		switch(v.getId()) {
			case R.id.discard_button:
				Intent openCamActivity = new Intent(ViewPicActivity.this, CameraActivity.class);
				startActivity(openCamActivity);
				break;
			case R.id.send_button:
				Intent opentestActivity = new Intent(ViewPicActivity.this, MainActivity.class);
				startActivity(opentestActivity);
			break;			
		}
	}
		


	
	/** Retrieves picture from external storage, decodes it to .bmp and displays it in layout */
	private void displayImage(){
		try{
		File imageFile = new File("/sdcard/Pictures/TensionCamApp/IMG_19700101_010000.jpg");
		ImageView jpgView = (ImageView)findViewById(R.id.imageView);
		Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
		jpgView.setImageBitmap(bitmap);
		} catch (NullPointerException e){
			Log.d(TAG, "No image to retrieve" + e.getMessage());
		}
	}

}
