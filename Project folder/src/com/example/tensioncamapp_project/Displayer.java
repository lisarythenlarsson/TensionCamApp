package com.example.tensioncamapp_project;

import java.io.File;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.Log;
import android.widget.ImageView;

public class Displayer {
	private static final String TAG = "Displayer";
	private static BitmapFactory.Options resample = new BitmapFactory.Options();

	/** Retrieves picture from external storage, decodes it to .bmp and displays it in layout */
	public static void displayImage(ImageView jpgView){
		try{
		File imageFile = new File(FileHandler.pathToString());
		Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath(), resize());
		jpgView.setImageBitmap(bitmap);
		} catch (NullPointerException e){
			Log.d(TAG, "No image to retrieve" + e.getMessage());
		}
	}
	/** Changes the size of the image to fit the device*/
	private static Options resize(){
		//allowing the caller to query the bitmap without having to allocate the memory for its pixels
		resample.inJustDecodeBounds = true;
		//returns an image that is 1/2 the width/height of the original, and 1/4 the number of pixels
		resample.inSampleSize = 2; 
		resample.inJustDecodeBounds = false;
		return resample;
	}
}
