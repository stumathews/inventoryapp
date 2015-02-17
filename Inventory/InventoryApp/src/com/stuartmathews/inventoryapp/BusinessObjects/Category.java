package com.stuartmathews.inventoryapp.BusinessObjects;

public class Category 
{
	private long rowId;
	private String name;
	
	public Category(String name)
	{
	this.name = name;	
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getRowId() {
		return rowId;
	}
	public void setRowId(long rowId) {
		this.rowId = rowId;
	}
}
