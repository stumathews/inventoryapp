package com.stuartmathews.inventoryapp.Activities.DAO;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;

import com.stuartmathews.inventoryapp.BusinessObjects.Category;
import com.stuartmathews.inventoryapp.Database.DatabaseManager;

public class CategoryDAO extends DAO implements IDAO <Category>
{
		
	private static final String KEY_NAME = "category_name";
	protected String[] ENTITY_SELECT_COLUMNS;
		
	public CategoryDAO (DatabaseManager databaseManager)
	{
		super(databaseManager,"category_id","Category");		
		
		ENTITY_SELECT_COLUMNS = new String[] {KEY_ROWID, KEY_NAME};
	}
	@Override
	public List<Category> getAll() throws Exception 
	{		
		List<Category> categories = new ArrayList<Category>();
		Cursor cursor = databaseManager.getDatabase().query(KEY_TABLE, ENTITY_SELECT_COLUMNS , null, null, null, null, null);		
		if( cursor != null && cursor.moveToFirst()){
			do{
				Category category = new Category(cursor.getString(1));
				category.setRowId(cursor.getLong(0));
				categories.add(category);
			}while( cursor.moveToNext());
		}
		return categories;
	}
	@Override
	public long insert(Category category) throws Exception {					
		ContentValues values = new ContentValues();		
		values.put(KEY_NAME, category.getName());		
		return databaseManager.getDatabase().insert(KEY_TABLE, null, values);
	}
	@Override
	public Category findByID(long id) throws Exception {
		Cursor results = databaseManager.getDatabase().query(KEY_TABLE,ENTITY_SELECT_COLUMNS,KEY_ROWID+"= ?",new String[]{Long.toString(id)},null,null,null);
		if( results == null || !results.moveToFirst())
			return null;
		Category category = new Category(results.getString(1));
		category.setRowId(results.getLong(0));
		return category;
	}
	@Override
	public Category findByName(String categoryName) throws Exception {
		Cursor results = databaseManager.getDatabase().query(KEY_TABLE, ENTITY_SELECT_COLUMNS, KEY_NAME+"=?", new String[]{categoryName}, null, null, null);
		if( results == null || !results.moveToFirst())
			return null;
		Category category = new Category(results.getString(1));
		category.setRowId(results.getLong(0));
		return category;
	}
	@Override
	public Category update(long old_entity_id, Category new_category) throws Exception {
		ContentValues values = new ContentValues();					
		Category old_category = findByID(old_entity_id);		
		if(!old_category.getName().equals(new_category.getName()))
			values.put(KEY_NAME, new_category.getName());		
		databaseManager.getDatabase().update(KEY_TABLE, values,KEY_ROWID + "= ?", new String[]{Long.toString(old_entity_id)});
		
		return null;
	}


	
}
