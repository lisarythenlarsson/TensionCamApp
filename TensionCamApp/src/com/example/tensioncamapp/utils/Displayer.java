package com.example.tensioncamapp.utils;

/**
 * @author Lisa Rythén Larsson
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
 *  */

import java.io.File;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.Log;
import android.widget.ImageView;

public class Displayer {
	private static final String TAG = "Displayer";
	private static BitmapFactory.Options resample = new BitmapFactory.Options();
	private static Bitmap bitmap;
	
	/** Retrieves picture from external storage, decodes it to .bmp */
	public static void displayImage(ImageView jpgView){
		try{
		File imageFile = new File(FileHandler.pathToString());
		bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath(), resize());
		} catch (NullPointerException e){
			Log.d(TAG, "No image to retrieve" + e.getMessage());
		}
		jpgView.setImageBitmap(bitmap);
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
