package com.example.tensioncamapp_project;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends Activity implements View.OnClickListener {
	
	private TextView Result;
	private Button newPic;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		newPic = (Button) findViewById(R.id.new_picture_button);
	
		Result = (TextView) findViewById(R.id.text_result);
		Result.setText("The result is: " + ViewPicActivity.get() + " blobs.");
		addListenerOnButton();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.result, menu);
		return true;
	}
	
	private void addListenerOnButton() {
		newPic = (Button) findViewById(R.id.new_picture_button);
		newPic.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v){
		Intent openTakeNewPic = new Intent(ResultActivity.this, CameraActivity.class);
		startActivity(openTakeNewPic);
	}
	
	/*@Override
	public void onClick(View v) {
		switch (v.getId()) {
		Intent openTakeNewPic = new Intent(ResultActivity.this, CameraActivity.class);
		startActivity(openTakeNewPic);
		}
	}*/

}
