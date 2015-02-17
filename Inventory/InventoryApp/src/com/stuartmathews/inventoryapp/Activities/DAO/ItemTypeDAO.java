package com.stuartmathews.inventoryapp.Activities.DAO;

import android.database.Cursor;

import com.stuartmathews.inventoryapp.Database.DatabaseManager;

public class ItemTypeDAO extends DAO // This is not a real DAO in the sense that it implements IDAO (dont need to)
{
	protected String[] ENTITY_SELECT_COLUMNS;
	
	// These hold values that dont change once they are set.
	int cache_inventory_type_id = Integer.MAX_VALUE;
	int cache_place_type_id = Integer.MAX_VALUE;

	public ItemTypeDAO(DatabaseManager databaseManager) {
		super(databaseManager,"item_type_id","ItemType");		
	}
	public int getItemTypeID() throws Exception
	{
			if( cache_inventory_type_id != Integer.MAX_VALUE )
				return cache_inventory_type_id;
			
			Cursor result = databaseManager.getDatabase().query(true, KEY_TABLE, new String[] {KEY_ROWID}, "type = " + "'Inventory'", null, null, null, null,null);
			if( result != null )
			{
				result.moveToFirst();
				cache_inventory_type_id = result.getInt(0);
				return cache_inventory_type_id;
			}
			throw new Exception("Could not get Item type id");
		}
	
	public int getPlaceTypeID() throws Exception
	{
		if( cache_place_type_id != Integer.MAX_VALUE)
			return cache_place_type_id;
		
		Cursor result = databaseManager.getDatabase().query(true, KEY_TABLE, new String[] {KEY_ROWID}, "type = " + "'Place'", null, null, null, null,null);
		if( result != null )
		{
			result.moveToFirst();
			cache_place_type_id = result.getInt(0);
			return cache_place_type_id;
		}
		throw new Exception("Could not get Item type id");
		
	}

}
