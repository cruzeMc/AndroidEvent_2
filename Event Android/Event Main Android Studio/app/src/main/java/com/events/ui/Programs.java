package com.events.ui;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.events.EventDetailActivity;
import com.events.MainActivity;
import com.events.R;
import com.events.custom.CustomFragment;
import com.events.model.Data;

/**
 * The Class Programs is the Fragment class that is launched when the user
 * clicks on Programs or on MyPrograms option in Left navigation drawer and this
 * is also used as a default fragment for MainActivity. It simply shows a dummy
 * list of Events/Programs. . You can customize this class to display actual
 * Feed listing.
 */
public class Programs extends CustomFragment
{

	/** The Programs list. */
	private ArrayList<Data> pList;

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View v = inflater.inflate(R.layout.programs, null);
		setHasOptionsMenu(true);

		setProgramList(v);
		return v;
	}

	/**
	 * Setup and initialize the Program list view.
	 * 
	 * @param v
	 *            the root view
	 */
	private void setProgramList(View v)
	{
		loadDummyPrograms();

		ListView list = (ListView) v.findViewById(R.id.list);
		list.setAdapter(new ProgramAdapter());
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3)
			{
				startActivity(new Intent(getActivity(),
						EventDetailActivity.class));
			}
		});
	}

	/**
	 * Load a dummy list of Programs. You need to write your own logic to load
	 * actual list of Programs.
	 * 
	 */
	private void loadDummyPrograms()
	{
		ArrayList<Data> sl = new ArrayList<Data>();
		sl.add(new Data("Gautier Runway Show",
				"The first runway show for the revered...", "Today 12:00AM",
				R.drawable.img1));
		sl.add(new Data("2014 Music Awards",
				"Last big event for the best singers, all in...",
				"Tomorrow 1:00 PM", R.drawable.img2));
		sl.add(new Data("Startup Weekend",
				"Silicon Vally Beat: New York Tech, Mobile...",
				"Tomorrow 7:00 PM", R.drawable.img3));
		sl.add(new Data("Sydney night",
				"A dance night for party lovers. Come join us",
				"Tomorrow 8:00 PM", R.drawable.img4));

		pList = new ArrayList<Data>(sl);
		pList.addAll(sl);
		pList.addAll(sl);
		pList.addAll(sl);
		pList.addAll(sl);
		pList.addAll(sl);
		pList.addAll(sl);
	}

	/**
	 * The Class ProgramAdapter is the adapter class for Feed ListView. The current
	 * implementation simply shows dummy contents and you can customize this
	 * class to display actual contents as per your need.
	 */
	private class ProgramAdapter extends BaseAdapter {

		/* (non-Javadoc)
		 * @see android.widget.Adapter#getCount()
		 */
		@Override
		public int getCount()
		{
			return pList.size();
		}

		/* (non-Javadoc)
		 * @see android.widget.Adapter#getItem(int)
		 */
		@Override
		public Data getItem(int position)
		{
			return pList.get(position);
		}

		/* (non-Javadoc)
		 * @see android.widget.Adapter#getItemId(int)
		 */
		@Override
		public long getItemId(int position)
		{
			return position;
		}

		/* (non-Javadoc)
		 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
		 */
		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			if (convertView == null)
				convertView = getLayoutInflater(null).inflate(
						R.layout.program_item, null);

			Data d = getItem(position);
			TextView lbl = (TextView) convertView.findViewById(R.id.lbl1);
			lbl.setText(d.getTitle1());

			lbl = (TextView) convertView.findViewById(R.id.lbl2);
			lbl.setText(d.getTitle2());

			lbl = (TextView) convertView.findViewById(R.id.lbl3);
			lbl.setText(d.getDesc());

			ImageView img = (ImageView) convertView.findViewById(R.id.categoryImageView);
			img.setImageResource(d.getImage1());

			return convertView;
		}

	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreateOptionsMenu(android.view.Menu, android.view.MenuInflater)
	 */
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
	{
		inflater.inflate(getArguments() != null ? R.menu.comment : R.menu.add,
				menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		if (item.getItemId() == R.id.menu_fav)
		{
			((MainActivity) getActivity()).launchFragment(21);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
