package com.stuartmathews.inventoryapp.Activities;

import com.stuartmathews.inventoryapp.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ShowPlaceActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_place);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_place, menu);
		return true;
	}

}
