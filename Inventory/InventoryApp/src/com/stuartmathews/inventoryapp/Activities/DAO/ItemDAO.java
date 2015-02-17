package com.stuartmathews.inventoryapp.Activities.DAO;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;

import com.stuartmathews.inventoryapp.BusinessObjects.Item;
import com.stuartmathews.inventoryapp.Database.DatabaseManager;

public class ItemDAO extends DAO implements IDAO <Item>
{
	
	private final String KEY_NAME = "item_name";
	private final String KEY_PICTURE = "item_picture";
	private final String KEY_LOCATION_ID = "item_location_id";
	private final String KEY_CREATE_DATE = "item_create_date";
	private final String KEY_LAST_MODIFIED_DATE = "item_last_modifified_date";
	private final String KEY_TYPE_ID = "item_type_id";
	protected String[] ENTITY_SELECT_COLUMNS;
	
	public ItemDAO(DatabaseManager databaseManager) {
		super(databaseManager,"item_id","Item");		
		
		ENTITY_SELECT_COLUMNS = new String[]{
				KEY_ROWID,
				KEY_NAME,
				KEY_PICTURE,
				KEY_LOCATION_ID,
				KEY_CREATE_DATE,
				KEY_LAST_MODIFIED_DATE,
				KEY_TYPE_ID
				};
		
	}
	
	@Override
	public  Item update( long old_item_id, Item new_item ) throws Exception
	{		
		Item old_item = findByID(old_item_id);
		
		ContentValues values = new ContentValues();
		
		if( old_item.getCategoryId() != new_item.getCategoryId() )
			;//no key yet
		if( !old_item.getCreateDate().equals(new_item.getCreateDate()) )
			values.put( KEY_CREATE_DATE, new_item.getCreateDate());
		if( old_item.getItemType() != new_item.getItemType())
			values.put(KEY_TYPE_ID, new_item.getItemType());
		if( !old_item.getLastModifiedDate().equals(new_item.getLastModifiedDate()))
			values.put(KEY_LAST_MODIFIED_DATE, new_item.getLastModifiedDate());
		if( !old_item.getLocation().equals(new_item.getLocation())); //BUG: This alwasys gets added.
			values.put(KEY_LOCATION_ID, new_item.getLocation());
		if( !old_item.getName().equals(new_item.getName()))
			values.put(KEY_NAME, new_item.getName());
		if( !old_item.getPicture().equals(new_item.getPicture()))
			values.put(KEY_PICTURE, new_item.getPicture());
		
		databaseManager.getDatabase().update(KEY_TABLE, values, KEY_ROWID +"= ?", new String[]{Long.toString(old_item.getRowId())});
		return findByID(old_item.getCategoryId());
		
	}
	
	public long add( Item item ) throws Exception
	{
		return insert( item );
	}
	
	
	@Override
	public List<Item> getAll() throws Exception
	{
		ArrayList<Item> items = new ArrayList<Item>();
		Cursor result = databaseManager.getDatabase().query(KEY_TABLE, ENTITY_SELECT_COLUMNS, null, null, null, null, null);
		if( result != null )
		{
			if( result.moveToFirst()){
				do{
					Item item = getItemFromEntitySelect(result);
					items.add(item);
				}while( result.moveToNext());
			}
		}
		return items;
	}

	private Item getItemFromEntitySelect(Cursor result) {
		Item item = new Item( result.getInt(0), result.getString(1),result.getString(2),result.getString(3),1,result.getString(4),result.getString(5),result.getShort(6));
		return item;
	}
	
	
	@Override
	public Item findByName(String testItemName) throws Exception {		
		Cursor results = databaseManager.getDatabase().query(KEY_TABLE, ENTITY_SELECT_COLUMNS, KEY_NAME+"=?", new String[]{testItemName}, null, null, null);
		if( results == null || !results.moveToFirst() )
			return null;
		if( results.getCount() != 1 )
			throw new Exception("More than one result found!");	
		return getItemFromEntitySelect(results);		
	}
	
	
	@Override
	public Item findByID(long id) throws Exception {
		Cursor results = databaseManager.getDatabase().query(KEY_TABLE, ENTITY_SELECT_COLUMNS, KEY_ROWID +"=?", new String[]{Long.toString(id)}, null, null, null);
		if( results == null || !results.moveToFirst() )
			return null;
		if( results.getCount() != 1 )
			return null;		
		return getItemFromEntitySelect(results);
	}
	
	@Override
	public  long insert(Item item ) throws Exception 
	{
				
		ContentValues values = new ContentValues();
		
		values.put(KEY_NAME, item.getName() );
		values.put(KEY_PICTURE, item.getPicture() );
		values.put(KEY_LOCATION_ID, item.getLocation());		
		values.put(KEY_CREATE_DATE, item.getCreateDate());
		values.put(KEY_LAST_MODIFIED_DATE, item.getLastModifiedDate() );
		values.put(KEY_TYPE_ID, item.getItemType() );
		
		return databaseManager.getDatabase().insert(KEY_TABLE, null, values);
	}
	
	


}
