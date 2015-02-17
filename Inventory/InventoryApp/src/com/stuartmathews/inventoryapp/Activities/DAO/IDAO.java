package com.stuartmathews.inventoryapp.Activities.DAO;

import java.util.List;

public interface IDAO <T> 
{
	public long 	insert(T entity) throws Exception;
	public T 		findByID(long id) throws Exception;
	public T 		findByName(String name) throws Exception;
	public List<T> 	getAll() throws Exception;
	public T 		update(long old_entity_id, T new_entity) throws Exception;	
}
