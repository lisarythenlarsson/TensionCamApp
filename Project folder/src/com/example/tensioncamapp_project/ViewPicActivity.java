package com.example.tensioncamapp_project;

import java.io.File;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.widget.ImageView;

public class ViewPicActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_pic);
		displayImage();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_pic, menu);
		return true;
	}
	
	/** Retrieves picture from external storage, decodes it to .bmp and displays it in layout */
	private void displayImage(){
		File imageFile = new File("/sdcard/Pictures/TensionCamApp/IMG_19700101_010000.jpg");
		ImageView jpgView = (ImageView)findViewById(R.id.imageView);
		Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
		jpgView.setImageBitmap(bitmap);
	}

}
