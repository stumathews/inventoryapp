package com.stuartmathews.inventoryapp.Activities.DAO;

import com.stuartmathews.inventoryapp.Database.DatabaseManager;

public class  DAO
{
	protected final DatabaseManager databaseManager;
	protected String KEY_ROWID;
	protected String KEY_TABLE;
	/***
	 * Blueprint for every Data Access Object
	 * @param databaseManager Objetc that manages the creation/upgrade/access to database
	 * @param rowIdname column name of the id of the table that backs this entity
	 * @param tableName table name of the table that backs this entity
	 */
	public DAO(DatabaseManager databaseManager, String rowIdname, String tableName)
	{
		this.databaseManager = databaseManager;
		this.KEY_ROWID = rowIdname;
		this.KEY_TABLE = tableName;
	}
	
	public boolean delete(long id) throws Exception {
		return databaseManager.getDatabase().delete(KEY_TABLE, KEY_ROWID + "=" + id, null) > 0;		
	}

	public void truncate() throws Exception {
		databaseManager.getDatabase().delete(KEY_TABLE, null, null);
	}
	
	
}
