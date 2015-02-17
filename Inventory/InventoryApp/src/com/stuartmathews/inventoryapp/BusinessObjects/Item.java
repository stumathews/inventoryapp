package com.stuartmathews.inventoryapp.BusinessObjects;

/*
 * + "item_id 					 INTEGER PRIMARY KEY AUTOINCREMENT,"
														+ "item_name 				 TEXT NOT NULL," 
														+ "item_picture 			 TEXT,"
														+ "item_location_id 		 INTEGER REFERENCES Location(location_id),"
														+ "item_category_id 		 INTEGER REFERENCES Category(category_id),"
														+ "item_create_date 		 TEXT NOT NULL,"
														+ "item_last_modifified_date TEXT NOT NULL,"
														+ "item_type_id 			 INTEGER REFERENCES ItemType(item_type_id)"
 */
public class Item 
{
	private String name;
	private String picture;
	private String location;
	private int categoryId;
	private String createDate;
	private String lastModifiedDate;
	private int itemType;
	private int rowId;
	
	public Item( String name,String picture,String location,int categoryId,String createDate,String lastModifiedDate,int itemType)
	{
		 this.name = name;
		 this.picture = picture;
		 this.location = location;
		 this.categoryId = categoryId;
		 this.createDate = createDate;
		 this.lastModifiedDate = lastModifiedDate;
		 this.itemType = itemType;
	}
	
	public Item( int rowID, String name,String picture,String location,int categoryId,String createDate,String lastModifiedDate,int itemType)
	{
		 this.rowId = rowID;
		 this.name = name;
		 this.picture = picture;
		 this.location = location;
		 this.categoryId = categoryId;
		 this.createDate = createDate;
		 this.lastModifiedDate = lastModifiedDate;
		 this.itemType = itemType;
	}
	
	public Item() {
		
	}
	
	@Override
	public String toString()
	{
		return getName();
	}

	public int getRowId(){
		return rowId;
	}
	public void setRowId(int id ){
		rowId = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public int getItemType() {
		return itemType;
	}
	public void setItemType(int itemType) {
		this.itemType = itemType;
	}
	
}
