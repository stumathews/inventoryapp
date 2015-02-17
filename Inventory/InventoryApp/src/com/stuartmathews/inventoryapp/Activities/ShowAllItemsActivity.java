package com.stuartmathews.inventoryapp.Activities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.stuartmathews.inventoryapp.InventoryApplication;
import com.stuartmathews.inventoryapp.R;
import com.stuartmathews.inventoryapp.Activities.DAO.CategoryDAO;
import com.stuartmathews.inventoryapp.Activities.DAO.ItemDAO;
import com.stuartmathews.inventoryapp.Activities.DAO.ItemTypeDAO;
import com.stuartmathews.inventoryapp.Activities.DAO.PlaceDAO;
import com.stuartmathews.inventoryapp.BusinessObjects.Item;

public class ShowAllItemsActivity extends ListActivity {
	InventoryApplication app;
	CategoryDAO categoryDAO = null;	
	ItemDAO itemDAO = null;
	PlaceDAO placeDAO = null;
	ItemTypeDAO itemTypeDAO = null;
	List<Item> items = new ArrayList<Item>();
	
	final int ADD_NEW_MENU_ACTION = 0;
	
	
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_all_items);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		setTitle("Your inventory");
		
		app = (InventoryApplication) getApplication();					
		app.getDataManager().open();	
		
		categoryDAO = app.getDataManager().getCategoryDAO();		
		itemDAO = app.getDataManager().getItemDAO();
		placeDAO = app.getDataManager().getPlaceDAO();
		itemTypeDAO = app.getDataManager().getItemTypeDAO();
		
		
		try {	
			items = itemDAO.getAll();
			for( int i = 0 ; i < items.size();i++)
				itemDAO.delete(items.get(i).getRowId());
			for( int j = 0; j < 10;j++)
				itemDAO.insert(createBasicItem("Item "+j));
			
			setListAdapter(new ArrayAdapter<Item>(this, R.layout.itemrow_layout,R.id.txtItemName, items));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

		
	private Item createBasicItem(String string) throws Exception {
		String aDate = new Date().toString();
		return new Item(string,"no picture","no location",0,aDate,aDate, itemTypeDAO.getItemTypeID());
	}


	@Override protected void onListItemClick(ListView l, View v, int position, long id) {
		Toast.makeText(this, "You have selected " + items.get(position), Toast.LENGTH_LONG).show();
	};
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_all_items, menu);
		createMenu(menu);
		return true;
	}
	
	@SuppressLint("NewApi")
	private void createMenu(Menu menu) {
		MenuItem newActivityMenu = menu.add(0,0,0, "Add");
		//newActivityMenu.setIcon(R.drawable.ic_launcher);
		newActivityMenu.setShowAsAction( MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT );
		
		
	}
	
	@Override public boolean onOptionsItemSelected(MenuItem menu)
	{
		return MenuChoice(menu);
	}


	private boolean MenuChoice(MenuItem menu) {
		switch( menu.getItemId() )
		{
		case ADD_NEW_MENU_ACTION :			
			Intent new_item_activity_intent = new Intent( this, AddEditItemActivity.class);			
			startActivity(new_item_activity_intent);
			return true;
		case android.R.id.home :
			Intent i = new Intent(this, ShowAllItemsActivity.class);
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(i);
		default:
			return false;
		}
	}


	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		app.getDataManager().close();
	}

}
