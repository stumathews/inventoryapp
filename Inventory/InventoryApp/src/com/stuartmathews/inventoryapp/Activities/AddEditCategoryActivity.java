package com.stuartmathews.inventoryapp.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.stuartmathews.inventoryapp.R;

public class AddEditCategoryActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.activity_add_edit_category);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_edit_category, menu);
		return true;
	}

}
