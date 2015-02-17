package com.example.inventoryapp.tests;

import java.util.List;

import android.test.AndroidTestCase;

import com.stuartmathews.inventoryapp.Activities.DAO.ItemDAO;
import com.stuartmathews.inventoryapp.Activities.DAO.ItemTypeDAO;
import com.stuartmathews.inventoryapp.Activities.DAO.PlaceDAO;
import com.stuartmathews.inventoryapp.BusinessObjects.Item;
import com.stuartmathews.inventoryapp.Database.DataManager;

public class PlaceDAOTests extends AndroidTestCase {
	
	private PlaceDAO dao;
	private DataManager dataManager;
	private ItemDAO itemDAO;
	private ItemTypeDAO itemTypeDAO;
	String TEST = "test";
	int InventoryTypeID;
	int PlaceTypeID;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		dataManager = new DataManager( getContext() );
		dao = dataManager.getPlaceDAO();
		itemDAO = dataManager.getItemDAO();
		itemTypeDAO = dataManager.getItemTypeDAO();
		dao.truncate();
		itemDAO.truncate();
		
		InventoryTypeID = itemTypeDAO.getItemTypeID();
		PlaceTypeID = itemTypeDAO.getPlaceTypeID();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		dao.truncate();
		itemDAO.truncate();
		dataManager.close();
	}
	
	
	public void test_getAll() throws Exception
	{
		
		for( int i = 0; i < 10; i++ )
		{
			itemDAO.insert(new Item(TEST+i,TEST,TEST,0,TEST,TEST,InventoryTypeID));
		}
		itemDAO.insert(new Item(TEST,TEST,TEST,0,TEST,TEST,PlaceTypeID));
		
		List<Item> places = dao.getAll();
		assertEquals( 1, places.size() );
		assertEquals( places.get(0).getItemType(), PlaceTypeID);
		
	}

}
