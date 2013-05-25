package com.example.tensioncamapp.activity;

import com.example.tensioncamapp.activity.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author Lisa Rythén Larsson, Martin Falk Danauskis
 * @copyright Lisa Rythén Larsson, Fredrik Johansson, Max Dubois, Martin Falk Danauskis
 *  
 *  */

public class ResultActivity extends Activity implements View.OnClickListener {
	
	private TextView Result;
	private Button newPic;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		this.newPic = (Button) findViewById(R.id.new_picture_button);
	
		this.Result = (TextView) findViewById(R.id.text_result);
		this.Result.setText("The result is: " + ViewPicActivity.get());
		addListenerOnButton();
	}

	
	private void addListenerOnButton() {
		this.newPic = (Button) findViewById(R.id.new_picture_button);
		this.newPic.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v){
		this.newPic.setEnabled(false);
		Intent openTakeNewPic = new Intent(ResultActivity.this, CameraActivity.class);
		startActivity(openTakeNewPic);
	}

	@Override
	protected void onResume() {
		super.onResume();
		//activate button when the activity is resumed
		this.newPic.setEnabled(true);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.result, menu);
		return true;
	}
}
