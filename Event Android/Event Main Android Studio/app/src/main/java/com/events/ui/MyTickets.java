package com.events.ui;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.events.R;
import com.events.custom.CustomFragment;
import com.events.model.Data;

/**
 * The Class MyTickets is the Fragment class that is launched when the user
 * clicks on My Tickets tab in MyProgram section and It simply shows a dummy
 * list of user's tickets . You can customize this class to display actual
 * ticket listing.
 */
public class MyTickets extends CustomFragment
{

	/** The ticket list. */
	private ArrayList<Data> tList;

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View v = inflater.inflate(R.layout.my_tickets, null);
		setHasOptionsMenu(true);

		setTicketList(v);
		return v;
	}

	/**
	 * Setup and initialize the ticket list view.
	 * 
	 * @param v
	 *            the root view
	 */
	private void setTicketList(View v)
	{
		loadDummyTickets();

		ListView list = (ListView) v.findViewById(R.id.list);
		list.setAdapter(new TicketAdapter());
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3)
			{
			}
		});
	}

	/**
	 * Load a dummy list of tickets. You need to write your own logic to load
	 * actual list of tickets.
	 * 
	 */
	private void loadDummyTickets()
	{
		ArrayList<Data> sl = new ArrayList<Data>();
		sl.add(new Data("Gautier Runway Show", "04/28/2014",
				"6:00PM - 10:00PM", 0));
		sl.add(new Data("2014 Music Awards", "04/29/2014", "8:00PM - 11:00PM",
				1));
		sl.add(new Data("Startup Weekend", "05/02/2014", "7:00PM - 8:00PM", 1));
		sl.add(new Data("Sydney night", "05/20/2014", "9:00PM - 11:00PM", 0));

		tList = new ArrayList<Data>(sl);
		tList.addAll(sl);
		tList.addAll(sl);
		tList.addAll(sl);
		tList.addAll(sl);
		tList.addAll(sl);
		tList.addAll(sl);
	}

	/**
	 * The Class TicketAdapter is the adapter class for ticket ListView. The current
	 * implementation simply shows dummy contents and you can customize this
	 * class to display actual contents as per your need.
	 */
	private class TicketAdapter extends BaseAdapter
	{

		/* (non-Javadoc)
		 * @see android.widget.Adapter#getCount()
		 */
		@Override
		public int getCount()
		{
			return tList.size();
		}

		/* (non-Javadoc)
		 * @see android.widget.Adapter#getItem(int)
		 */
		@Override
		public Data getItem(int position)
		{
			return tList.get(position);
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
						R.layout.ticket_item, null);

			Data d = getItem(position);
			TextView lbl = (TextView) convertView.findViewById(R.id.lbl1);
			lbl.setText(d.getTitle1());

			lbl = (TextView) convertView.findViewById(R.id.lbl2);
			lbl.setText(d.getTitle2());

			lbl = (TextView) convertView.findViewById(R.id.lbl3);
			lbl.setText(d.getDesc());

			convertView.findViewById(R.id.free).setVisibility(
					d.getImage1() == 0 ? View.GONE : View.VISIBLE);

			return convertView;
		}

	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreateOptionsMenu(android.view.Menu, android.view.MenuInflater)
	 */
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
	{
		inflater.inflate(R.menu.comment, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

}
