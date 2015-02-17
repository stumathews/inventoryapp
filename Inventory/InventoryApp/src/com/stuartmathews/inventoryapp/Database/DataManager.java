package com.stuartmathews.inventoryapp.Database;

import android.content.Context;

import com.stuartmathews.inventoryapp.Activities.DAO.CategoryDAO;
import com.stuartmathews.inventoryapp.Activities.DAO.ItemDAO;
import com.stuartmathews.inventoryapp.Activities.DAO.ItemTypeDAO;
import com.stuartmathews.inventoryapp.Activities.DAO.PlaceDAO;

public class DataManager
{
	private final DatabaseManager databaseManager;
	private CategoryDAO categoryDAO = null;
	private ItemDAO itemDAO = null;
	private PlaceDAO placeDAO = null;
	private ItemTypeDAO itemTypeDAO = null;
	
	public DataManager( Context context) throws Exception
	{
		this.databaseManager = new DatabaseManager(context);
				
		categoryDAO = new CategoryDAO( databaseManager );
		itemDAO = new ItemDAO( databaseManager );
		placeDAO = new PlaceDAO( databaseManager); 
		itemTypeDAO = new ItemTypeDAO(databaseManager);
	}
	

	public ItemTypeDAO getItemTypeDAO()
	{
		return itemTypeDAO;
	}
	public ItemDAO getItemDAO() {
		return itemDAO;
	}

	public PlaceDAO getPlaceDAO() {
		return placeDAO;
	}
	
	public CategoryDAO getCategoryDAO() {
		return categoryDAO;
	}
		
	public void close()
	{
		databaseManager.close();
	}


	public void open() {
		databaseManager.open();
		
	}
}
