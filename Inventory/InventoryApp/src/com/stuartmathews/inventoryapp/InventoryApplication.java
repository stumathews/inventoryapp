package com.stuartmathews.inventoryapp;

import android.app.Application;

import com.stuartmathews.inventoryapp.Database.DataManager;


public class InventoryApplication extends Application 
{
	private DataManager dataManager;
	
	@Override
	public void onCreate() {
		super.onCreate();
		try {
			setDataManager(new DataManager(getApplicationContext()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public DataManager getDataManager() {
		return dataManager;
	}

	public void setDataManager(DataManager dataManager) {	this.dataManager = dataManager;
	}

}
