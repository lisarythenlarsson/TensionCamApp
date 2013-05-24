package com.example.tensioncamapp.activity;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;

import com.example.tensioncamapp.activity.R;
import com.example.tensioncamapp.utils.Displayer;
import com.example.tensioncamapp.utils.FileHandler;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class ViewPicActivity extends Activity implements View.OnClickListener {

	private Button discard;
	private Button analyze;
	private ProgressBar progressBar;
	
	private static String result;

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

	/** Added switch-clauses to enable functionality for two buttons */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.discard_button:
			this.discard.setEnabled(false);
			Intent openCamActivity = new Intent(ViewPicActivity.this,
					CameraActivity.class);
			startActivity(openCamActivity);
			break;
		case R.id.analyze_button:
			this.analyze.setEnabled(false);
			this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
			this.progressBar.setVisibility(0);
			String path = FileHandler.pathToString();
			try {
				new SendTask().execute(path);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
	}

	private class SendTask extends AsyncTask<String, String, String> {
		private String answer;

		@Override
		protected String doInBackground(String... params) {
				
			String filePath = params[0];
			String responseAnswer = "Could not connect to server";


			HttpClient httpclient = new DefaultHttpClient();
			try {
				HttpPost httppost = new HttpPost("http://192.168.43.79:8080/Analyse/upload"); 
				FileBody pic = new FileBody(new File(filePath)); 
				MultipartEntity requestEntity = new MultipartEntity(); 
				requestEntity.addPart("file", pic);

				httppost.setEntity(requestEntity);
				System.out.println("executing request " + httppost.getRequestLine());
				HttpResponse response = httpclient.execute(httppost);

				System.out.println("-------------------------------------------------------");
				System.out.println(response.getStatusLine());

				ByteArrayOutputStream outstream = new ByteArrayOutputStream();
				response.getEntity().writeTo(outstream);
				byte[] responseBody = outstream.toByteArray();

				responseAnswer = new String(responseBody);
				System.out.println(responseAnswer);

			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
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
			this.answer = result;
			set(this.answer);
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

	@Override
	protected void onResume() {
		super.onResume();
		this.discard.setEnabled(true);
		this.analyze.setEnabled(true);
	}
	

}
