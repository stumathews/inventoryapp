package com.example.inventoryapp.tests;

import java.util.Date;

import android.test.AndroidTestCase;

import com.stuartmathews.inventoryapp.Activities.DAO.ItemDAO;
import com.stuartmathews.inventoryapp.Activities.DAO.ItemTypeDAO;
import com.stuartmathews.inventoryapp.BusinessObjects.Item;
import com.stuartmathews.inventoryapp.Database.DataManager;

public class ItemDAOTests extends AndroidTestCase implements IDAOTests {

	private static final String TEST_ITEM_NAME = "Test Inventory Item";
	ItemDAO dao;
	DataManager dataManager;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		dataManager = new DataManager( getContext() );
		dao = dataManager.getItemDAO();
		dao.truncate();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		dao.truncate();
		dataManager.close();		
	}
	
	
	@Override
	public void test_update() throws Exception
	{
		String NEW_NAME = "new name";
		String NEW_PICTURE = "new picture";
		String NEW_LOCATION = "new location";
		String NEW_CREATE_DATE = new Date().toString();
		int NEW_ITEM_TYPE = dataManager.getItemTypeDAO().getPlaceTypeID();
		String NEW_LAST_MODIFIED_DATE = new Date().toString();
		
		Item item = createTestItem();
		long id = dao.insert(item);
		item.setName(NEW_NAME);
		item.setPicture(NEW_PICTURE);
		item.setCreateDate(NEW_CREATE_DATE);		
		item.setItemType(NEW_ITEM_TYPE);		
		item.setLocation(NEW_LOCATION);		
		item.setLastModifiedDate(NEW_LAST_MODIFIED_DATE);
		
		
		dao.update(id, item);
		
		Item result = dao.findByID(id);
		
		assertEquals( id, result.getRowId());
		assertEquals( result.getName(),NEW_NAME);
		assertEquals( result.getPicture(), NEW_PICTURE);
		assertEquals( result.getCreateDate(),NEW_CREATE_DATE);		
		assertEquals( result.getItemType(),NEW_ITEM_TYPE);		
		assertEquals( result.getLocation(),NEW_LOCATION);		
		assertEquals( result.getLastModifiedDate(),NEW_LAST_MODIFIED_DATE);
		
	}
	
	
	@Override
	public void test_findById() throws Exception
	{
		Item item = createTestItem();
		long id = dao.insert(item);
		Item result = dao.findByID(id);
		assertEquals(result.getRowId(), id);
	}
	
	
	@Override
	public void test_findByName() throws Exception
	{
		Item item = createTestItem();
		dao.insert(item);
		Item result = dao.findByName(item.getName());
		String expected_name = item.getName();
		String actual_name = result.getName();
		
		assertEquals(expected_name, actual_name);
		
		
	}
	
	@Override
	public void test_truncate() throws Exception
	{
		dao.insert(createTestItemWithName("alpha"));
		dao.insert(createTestItemWithName("beta"));
		dao.insert(createTestItemWithName("gamma"));
		
		int expected_count = 3;
		int actual_count = dao.getAll().size();
		assertEquals(expected_count, actual_count);		
		
	}
	
	
	@Override
	public void test_insert() throws Exception
	{
		Item item = createTestItem();
		long rowid = dao.insert(item);
		Item result = dao.findByName(TEST_ITEM_NAME);
		assertNotNull("Could not find inserted item!", result);
		dao.delete(rowid);				
	}
		

	
	@Override
	public void test_getAll() throws Exception
	{
		Item testItem = createTestItem();
		dao.insert(testItem);
		assertNotNull("Could not find inserted object", dao.findByName(testItem.getName()));
		
	}
	
	private Item createTestItem() throws Exception 
	{
		String theDateNow = new Date().toString();
		ItemTypeDAO itemTypeDAO = dataManager.getItemTypeDAO();
		int inventory_type = itemTypeDAO.getItemTypeID();
		return new Item(TEST_ITEM_NAME,"Test Picture","Test location",1,theDateNow,theDateNow, inventory_type);
	}
	
	private Item createTestItemWithName(String name) throws Exception 
	{
		String theDateNow = new Date().toString();
		ItemTypeDAO itemTypeDAO = dataManager.getItemTypeDAO();
		int inventory_type = itemTypeDAO.getItemTypeID();
		return new Item(name,"Test Picture","Test location",1,theDateNow,theDateNow, inventory_type);
	}

	
}
