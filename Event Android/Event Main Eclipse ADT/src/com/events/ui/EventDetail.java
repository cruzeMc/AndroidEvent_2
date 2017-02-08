package com.events.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.events.R;
import com.events.custom.CustomFragment;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * The Class EventDetail is the Fragment class that shows the details about an
 * Event. This Fragment is used inside the EventDetailActivity class. It also
 * show a Map with a marker on map for showing the location of that event. You
 * need to write your own logic for loading actual contents related to Events
 * and also need to show actual location for Event.
 */
public class EventDetail extends CustomFragment
{

	/** The map view. */
	private MapView mMapView;

	/** The Google map. */
	private GoogleMap mMap;

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View v = inflater.inflate(R.layout.event_detail, null);
		setHasOptionsMenu(true);

		setTouchNClick(v.findViewById(R.id.btnDesc));
		setTouchNClick(v.findViewById(R.id.btnReg));

		setupMap(v, savedInstanceState);
		return v;
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onPause()
	 */
	@Override
	public void onPause()
	{
		mMapView.onPause();
		super.onPause();
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onDestroy()
	 */
	@Override
	public void onDestroy()
	{
		mMapView.onDestroy();
		super.onDestroy();
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onResume()
	 */
	@Override
	public void onResume()
	{
		super.onResume();
		mMapView.onResume();

		mMap = mMapView.getMap();
		if (mMap != null)
		{
			mMap.setMyLocationEnabled(true);
			mMap.setInfoWindowAdapter(null);
			setupMarker();
		}
	}

	/**
	 * Setup and initialize the Google map view.
	 * 
	 * @param v
	 *            the root view
	 * @param savedInstanceState
	 *            the saved instance state
	 */
	private void setupMap(View v, Bundle savedInstanceState)
	{
		try
		{
			MapsInitializer.initialize(getActivity());
		} catch (GooglePlayServicesNotAvailableException e)
		{
			e.printStackTrace();
		}
		mMapView = (MapView) v.findViewById(R.id.map);
		mMapView.onCreate(savedInstanceState);

	}

	/**
	 * This method simply place a few dummy location markers on Map View. You
	 * can write your own logic for loading the locations and placing the marker
	 * for each location as per your need.
	 */
	private void setupMarker()
	{
		mMap.clear();
		LatLng ll = new LatLng(45.4667, 9.1833);
		MarkerOptions opt = new MarkerOptions();
		opt.position(ll).title("Milan Fashion Week").snippet("Milan, Italy");
		opt.icon(BitmapDescriptorFactory
				.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));

		mMap.addMarker(opt);
		mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ll, 15));
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreateOptionsMenu(android.view.Menu, android.view.MenuInflater)
	 */
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
	{
		inflater.inflate(R.menu.share_fav, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		if (item.getItemId() == R.id.menu_share)
		{
			Intent i = new Intent(Intent.ACTION_SEND);
			i.setType("text/plain");
			i.putExtra(Intent.EXTRA_TEXT, "This is dummy share text!");
			startActivity(Intent.createChooser(i, getString(R.string.share)));
		}
		return super.onOptionsItemSelected(item);
	}

}
