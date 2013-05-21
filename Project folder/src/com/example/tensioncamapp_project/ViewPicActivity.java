package com.example.tensioncamapp_project;

import java.io.File;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class ViewPicActivity extends Activity implements View.OnClickListener {

	private String TAG = "ViewPicActivity";
	private Button discard;
	private Button analyze;
	
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
		analyze = (Button) findViewById(R.id.analyze_button);
		 
		discard.setOnClickListener(this);
		analyze.setOnClickListener(this);
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
		


	
	/** Retrieves picture from external storage, decodes it to .bmp and displays it in layout */
	private void displayImage(){
		try{
		File imageFile = new File(new File(Environment.getExternalStoragePublicDirectory(
	    		Environment.DIRECTORY_PICTURES), "TensionCamApp")
                .getPath() + File.separator + FileHandler.filename);
			
		ImageView jpgView = (ImageView)findViewById(R.id.imageView);
		Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath(), resize());
		jpgView.setImageBitmap(bitmap);
		} catch (NullPointerException e){
			Log.d(TAG, "No image to retrieve" + e.getMessage());
		}
	}

	private static Options resize(){
		BitmapFactory.Options resample = new BitmapFactory.Options();
		resample.inJustDecodeBounds = true;
		resample.inSampleSize = 2;
		resample.inJustDecodeBounds = false;
		return resample;
	}
}

