package com.example.inventoryapp.tests;

import java.util.List;

import android.test.AndroidTestCase;

import com.stuartmathews.inventoryapp.Activities.DAO.CategoryDAO;
import com.stuartmathews.inventoryapp.BusinessObjects.Category;
import com.stuartmathews.inventoryapp.Database.DataManager;

public class CategoryDAOTests extends AndroidTestCase implements IDAOTests {

	private static final String DUMMY_CATEGORY = "Sports";
	private static final String NEW_NAME = "Fashion";
	DataManager dataManager;
	CategoryDAO dao;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();		
		dataManager = new DataManager(getContext());
		dao = dataManager.getCategoryDAO();
		dao.truncate();
		
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		dao.truncate();
		dataManager.close();
	}
	
	@Override
	public void test_getAll() throws Exception
	{
		
		dao.insert(new Category(DUMMY_CATEGORY));		
		List<Category> categories = dao.getAll();
		if( categories.size() == 0 )		
			throw new Exception("No results");
	}
	
	@Override
	public void test_insert() throws Exception
	{
		boolean found_inserted_row = false;
		long rowid = dao.insert(new Category(DUMMY_CATEGORY));		
		
		List<Category> catagories = dao.getAll();
		for( Category c : catagories )
		{
			if( c.getName().equals(DUMMY_CATEGORY) ){
				found_inserted_row = true;
				break;
			}				
		}
		
		assertEquals(true, found_inserted_row);
		
		dao.delete(rowid);
		
		
	}
	
	@Override
	public void test_update() throws Exception
	{						
		Category category = new Category(DUMMY_CATEGORY);
		long id = dao.insert(category);	
		category.setName(NEW_NAME);
		dao.update(id, category);
		
		Category result = dao.findByID(id);
		
		assertEquals( id, result.getRowId());
		assertEquals( result.getName(),NEW_NAME);
	}
	
	@Override
	public void test_findById() throws Exception
	{
		Category category = new Category(DUMMY_CATEGORY);
		long id = dao.insert(category);
		Category result = dao.findByID(id);
		assertEquals(result.getRowId(), id);
	}
	
	@Override
	public void test_findByName() throws Exception
	{
		Category category = new Category(DUMMY_CATEGORY);
		dao.insert(category);
		Category result = dao.findByName(category.getName());
		String expected_name = category.getName();
		String actual_name = result.getName();
		
		assertEquals(expected_name, actual_name);
		
		
	}

	@Override
	public void test_truncate() throws Exception 
	{
		dao.insert(new Category("alpha"));
		dao.insert(new Category("beta"));
		dao.insert(new Category("gamma"));
		
		int expected_count = 3;
		int actual_count = dao.getAll().size();
		assertEquals(expected_count, actual_count);			
	}

}
