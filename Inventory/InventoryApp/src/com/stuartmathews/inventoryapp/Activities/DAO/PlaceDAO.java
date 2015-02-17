package com.stuartmathews.inventoryapp.Activities.DAO;

import java.util.ArrayList;
import java.util.List;

import com.stuartmathews.inventoryapp.BusinessObjects.Item;
import com.stuartmathews.inventoryapp.Database.DatabaseManager;


public class PlaceDAO extends DAO implements IDAO <Item> {

	protected String[] ENTITY_SELECT_COLUMNS;
	private final ItemDAO itemDAO;
	private final ItemTypeDAO itemTypeDAO;
	
	public PlaceDAO(DatabaseManager databaseManager) {
		super(databaseManager,"item_id","Item");
		itemDAO = new ItemDAO(databaseManager);
		itemTypeDAO = new ItemTypeDAO(databaseManager);
	}

	@Override
	public  long insert(Item item) throws Exception {
		return itemDAO.insert(item);		
	}

	@Override
	public Item findByID(long id) throws Exception {
		return itemDAO.findByID(id);		 
	}

	@Override
	public Item findByName(String itemname) throws Exception {
		return itemDAO.findByName(itemname);
	}

	
	@Override
	public List<Item> getAll() throws Exception {
		List<Item> all_places = new ArrayList<Item>();
		for( Item item : itemDAO.getAll() ) // might want to specilise this to only get PLACES
		{			
			if( item.getItemType() != itemTypeDAO.getPlaceTypeID() )
				continue;
			all_places.add(item);
		}
		return all_places;
	}

	@Override
	public Item update(long old_entity_id, Item new_item) throws Exception {
		return itemDAO.update( old_entity_id, new_item );
	}

}
