package com.events;

import android.os.Bundle;
import android.view.MenuItem;

import com.events.custom.CustomActivity;
import com.events.ui.MapViewer;

/**
 * The MapViewActivity is the activity class that shows Map fragment. This
 * activity is only created to show Back button on ActionBar.
 */
public class MapViewActivity extends CustomActivity
{
	/* (non-Javadoc)
	 * @see com.food.custom.CustomActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		getActionBar().setTitle("Maps");

		addFragment();
	}

	/**
	 * Attach the appropriate MapViewer fragment with activity.
	 */
	private void addFragment()
	{
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, new MapViewer()).commit();
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		if (item.getItemId() == android.R.id.home)
		{
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
