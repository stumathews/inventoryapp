package com.stuartmathews.inventoryapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseManager  {
	private static final String DATABASE_NAME = "InventoryDB";
	private static final int DATABASE_VERSION = 1;
	private static final String TAG = "DatabaseManager";
	
	DatabaseOpener dbOpener = null;
	final Context context;
	SQLiteDatabase db = null;
	
	public SQLiteDatabase getDatabase() throws Exception 
	{
		if( db != null )
			return db;		
		else {			
			open();
			if( db == null )
				throw new Exception("Datbase not initialised.");
			return db;
		}
		
		
	}

	private static final String CREATE_ITEM_TABLE = "CREATE TABLE Item" 
													+ "("
														+ "item_id 					 INTEGER PRIMARY KEY AUTOINCREMENT,"
														+ "item_name 				 TEXT NOT NULL," 
														+ "item_picture 			 TEXT,"
														+ "item_location_id 		 INTEGER REFERENCES Location(location_id),"
														+ "item_category_id 		 INTEGER REFERENCES Category(category_id),"
														+ "item_create_date 		 TEXT NOT NULL,"
														+ "item_last_modifified_date TEXT NOT NULL,"
														+ "item_type_id 			 INTEGER REFERENCES ItemType(item_type_id)"
												   + ")";
	
	private static final String CREATE_CATEGORY_TABLE = "CREATE TABLE Category" 
													  + "("
															+ "category_id INTEGER PRIMARY KEY AUTOINCREMENT,"
															+ "category_name TEXT NOT NULL" 
													  + ")";
	
	private static final String CREATE_ITEM_TYPE_TABLE = "CREATE TABLE ItemType"
			 										   + "("
															+ "item_type_id INTEGER PRIMARY KEY AUTOINCREMENT,"
															+ "type TEXT NOT NULL" 
													   + ")";

	private static final String CREATE_LOCATION_TABLE = "CREATE TABLE Location" 
													  + "("
															+ "location_id INTEGER PRIMARY KEY AUTOINCREMENT,"
															+ "location_coord TEXT NOT NULL"
													  + ")";
	
	private static final String CREATE_LINK_TABLE = "CREATE TABLE Link"
												  + "("
														+ "link_id INTEGER PRIMARY KEY AUTOINCREMENT,"
														+ "parent_item_id INTEGER REFERENCES Item(item_id),"
														+ "child_item_id INTEGER REFERENCES Item(item_id) " 
												  + ")";
						
	private static final String CREATE_ATTRIBUTE_TABLE = "CREATE TABLE Attribute" 
													   + "("
															+ "attribute_id INTEGER PRIMARY KEY AUTOINCREMENT,"
															+ "attribute_name TEXT NOT NULL" 
													   + ")";
	
	private static final String CREATE_ATTRIBUTE_VALUE_TABLE = "CREATE TABLE AttributeValue" 
															 + "("
																	+ "attribute_value_id INTEGER PRIMARY KEY AUTOINCREMENT,"
																	+ "attribute_id INTEGER REFERENCES Attribute(attribute_id),"
																	+ "item_id INTEGER REFERENCES Item(item_id),"
																	+ "value TEXT NOT NULL" 
															 + ")";
	
	public DatabaseManager( Context context )
	{
		this.context = context;
		dbOpener = new DatabaseOpener(context);		
	}

	private class DatabaseOpener extends SQLiteOpenHelper
	{		
	
		public DatabaseOpener(Context context)
		{
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		
		@Override public void onOpen(SQLiteDatabase db) 
		{
			// Define two types of Items initially Places and Inventory Items...
			ContentValues values = new ContentValues();
			values.put("type", "Inventory");			
			db.insert("ItemType", null, values);
			
			values = new ContentValues();
			values.put("type", "Place");
			db.insert("ItemType", null, values);
						
		};
		
		@Override
		public void onCreate(SQLiteDatabase db) 
		{		
			Log.w(TAG, "Creating the database");
			try {			
				db.execSQL(CREATE_ITEM_TABLE);
				db.execSQL(CREATE_CATEGORY_TABLE);
				db.execSQL(CREATE_ITEM_TYPE_TABLE);
				db.execSQL(CREATE_LOCATION_TABLE);
				db.execSQL(CREATE_LINK_TABLE);
				db.execSQL(CREATE_ATTRIBUTE_TABLE);
				db.execSQL(CREATE_ATTRIBUTE_VALUE_TABLE);
			} catch ( SQLException err )
			{
				err.printStackTrace();
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
		{
			Log.w(TAG, "Upgrading your database from " + oldVersion + " to " + newVersion  );
			
			db.execSQL("DROP TABLE IF EXISTS Item");
			db.execSQL("DROP TABLE IF EXISTS Category");
			db.execSQL("DROP TABLE IF EXISTS ItemType");
			db.execSQL("DROP TABLE IF EXISTS Location");
			db.execSQL("DROP TABLE IF EXISTS Link");
			db.execSQL("DROP TABLE IF EXISTS Attribute");
			db.execSQL("DROP TABLE IF EXISTS AttributeValue");
			
			onCreate(db);
		}
				
	}
	
	public DatabaseManager open(){
		db = dbOpener.getWritableDatabase();
		return this;
	}
	
	public void close(){
		dbOpener.close();
	}
	
	public void AddCategory(String category)
	{
		ContentValues values = new ContentValues();
		values.put("category_name",category);	
	}
	
	
		
}
