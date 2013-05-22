package com.example.tensioncamapp_project;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;

//import com.example.tensioncamapp_project.Send.SendTask;

import android.os.AsyncTask;
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
	
	private static String result;

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

	/** Added switch-clauses to enable functionality for two buttons */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.discard_button:
			Intent openCamActivity = new Intent(ViewPicActivity.this,
					CameraActivity.class);
			startActivity(openCamActivity);
			break;
		case R.id.analyze_button:
			System.out.println("test1");
			String path = FileHandler.pathToString();
			System.out.println("test2");
			try {
				new SendTask().execute(path);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			break;
		}
	}

	/**
	 * Retrieves picture from external storage, decodes it to .bmp and displays
	 * it in layout
	 */
	private void displayImage() {
		try {
			File imageFile = new File(FileHandler.pathToString());

			ImageView jpgView = (ImageView) findViewById(R.id.imageView);
			Bitmap bitmap = BitmapFactory.decodeFile(
					imageFile.getAbsolutePath(), resize());
			jpgView.setImageBitmap(bitmap);
		} catch (NullPointerException e) {
			Log.d(TAG, "No image to retrieve" + e.getMessage());
		}
	}

	private static Options resize() {
		BitmapFactory.Options resample = new BitmapFactory.Options();
		resample.inJustDecodeBounds = true;
		resample.inSampleSize = 2;
		resample.inJustDecodeBounds = false;
		return resample;
	}

	private class SendTask extends AsyncTask<String, String, String> {
		private String answer;

		@Override
		protected String doInBackground(String... params) {
			String filePath = params[0];
			String responseAnswer = "3";

			System.out.println("test3");

			HttpClient httpclient = new DefaultHttpClient();
			try {
				HttpPost httppost = new HttpPost("http://192.168.43.79:8080/Analyse/upload"); 
				System.out.println("test4");
				FileBody pic = new FileBody(new File(filePath)); 
				System.out.println("test5");
				MultipartEntity requestEntity = new MultipartEntity(); 
				requestEntity.addPart("file", pic);

				httppost.setEntity(requestEntity);
				System.out.println("executing request "
						+ httppost.getRequestLine());
				HttpResponse response = httpclient.execute(httppost);
				HttpEntity responseEntity = response.getEntity();

				System.out.println("----------------------------------------");
				System.out.println(response.getStatusLine());
				/*
				 * if (responseEntity != null) {
				 * System.out.println("Response content length: " +
				 * responseEntity.getContentLength()); }
				 */

				ByteArrayOutputStream outstream = new ByteArrayOutputStream();
				response.getEntity().writeTo(outstream);
				byte[] responseBody = outstream.toByteArray();

				responseAnswer = new String(responseBody);
				System.out.println(responseAnswer);

			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					httpclient.getConnectionManager().shutdown();
				} catch (Exception ignore) {
				}
			}
			return responseAnswer;
		}

		@Override
		protected void onPostExecute(String result) {
			answer = result;
			System.out.println("Hej");
			System.out.println(answer);
			set(answer);
			Intent openResultActivity = new Intent(ViewPicActivity.this, ResultActivity.class);
			startActivity(openResultActivity);
		}

	}
	
	private void set(String s){
		result = s;
	}
	
	public static String get(){
		return result;
	}
	

}
