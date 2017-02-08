package com.events.calendar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.events.EventDetailActivity;
import com.events.R;
import com.events.custom.CustomActivity;
import com.events.custom.CustomFragment;
import com.events.model.Event;

/**
 * The Class CalendarView is Fragment class to hold the Calendar view.
 */
public class CalendarView extends CustomFragment
{

	/** The item month. */
	public GregorianCalendar month, itemmonth;// calendar instances.

	/** The adapter. */
	public CalendarAdapter adapter;// adapter instance

	/** The handler. */
	public Handler handler;// for grabbing some event values for showing the dot
							// marker.
	/** The items. */
	public ArrayList<String> items; // container to store calendar items which
	// needs showing the event marker
	/** The events. */
	private ArrayList<Event> events;

	/** The list. */
	private ListView list;

	/** The events for selected date. */
	private ArrayList<Event> eventSel;

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View v = inflater.inflate(R.layout.calendar, null);
		setHasOptionsMenu(true);

		setupEventList(v);

		initCalendarView(v);

		return v;
	}

	/**
	 * Initialize the calendar view.
	 * 
	 * @param v
	 *            the v
	 */
	private void initCalendarView(View v)
	{
		month = (GregorianCalendar) Calendar.getInstance();
		itemmonth = (GregorianCalendar) month.clone();

		items = new ArrayList<String>();

		adapter = new CalendarAdapter(getActivity(), month);

		GridView gridview = (GridView) v.findViewById(R.id.gridview);
		gridview.setAdapter(adapter);

		handler = new Handler();
		handler.post(calendarUpdater);

		TextView title = (TextView) v.findViewById(R.id.title);
		title.setText(android.text.format.DateFormat.format("MMMM yyyy", month));

		RelativeLayout previous = (RelativeLayout) v
				.findViewById(R.id.previous);
		previous.setOnTouchListener(CustomActivity.TOUCH);
		previous.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				setPreviousMonth();
				refreshCalendar();
			}
		});

		RelativeLayout next = (RelativeLayout) v.findViewById(R.id.next);
		next.setOnTouchListener(CustomActivity.TOUCH);
		next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				setNextMonth();
				refreshCalendar();

			}
		});

		gridview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id)
			{

				eventSel.clear();

				((CalendarAdapter) parent.getAdapter()).setSelected(v);
				String selectedGridDate = CalendarAdapter.dayString
						.get(position);
				String[] separatedTime = selectedGridDate.split("-");
				String gridvalueString = separatedTime[2].replaceFirst("^0*",
						"");// taking last part of date. ie; 2 from 2012-12-02.
				int gridvalue = Integer.parseInt(gridvalueString);
				// navigate to next or previous month on clicking offdays.
				if (gridvalue > 10 && position < 8)
				{
					setPreviousMonth();
					refreshCalendar();
				}
				else if (gridvalue < 7 && position > 28)
				{
					setNextMonth();
					refreshCalendar();
				}
				((CalendarAdapter) parent.getAdapter()).setSelected(v);

				for (Event e : events)
				{
					if (e.getDate().equals(selectedGridDate))
					{
						eventSel.add(e);
					}
				}
				((BaseAdapter) list.getAdapter()).notifyDataSetChanged();

			}

		});
	}

	/**
	 * Set the up event list.
	 * 
	 * @param v
	 *            the root view
	 */
	private void setupEventList(View v)
	{
		eventSel = new ArrayList<Event>();
		list = (ListView) v.findViewById(R.id.list);
		list.setAdapter(new EventAdapter());
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				startActivity(new Intent(getActivity(),
						EventDetailActivity.class));
			}
		});
	}

	/**
	 * Sets the next month.
	 */
	protected void setNextMonth()
	{
		if (month.get(Calendar.MONTH) == month.getActualMaximum(Calendar.MONTH))
		{
			month.set(month.get(Calendar.YEAR) + 1,
					month.getActualMinimum(Calendar.MONTH), 1);
		}
		else
		{
			month.set(Calendar.MONTH, month.get(Calendar.MONTH) + 1);
		}

	}

	/**
	 * Sets the previous month.
	 */
	protected void setPreviousMonth()
	{
		if (month.get(Calendar.MONTH) == month.getActualMinimum(Calendar.MONTH))
		{
			month.set(month.get(Calendar.YEAR) - 1,
					month.getActualMaximum(Calendar.MONTH), 1);
		}
		else
		{
			month.set(Calendar.MONTH, month.get(Calendar.MONTH) - 1);
		}

	}

	/**
	 * Show toast.
	 * 
	 * @param string
	 *            the string message
	 */
	protected void showToast(String string)
	{
		Toast.makeText(getActivity(), string, Toast.LENGTH_SHORT).show();

	}

	/**
	 * Refresh calendar.
	 */
	public void refreshCalendar()
	{
		TextView title = (TextView) getView().findViewById(R.id.title);

		adapter.refreshDays();
		adapter.notifyDataSetChanged();
		handler.post(calendarUpdater); // generate some calendar items

		title.setText(android.text.format.DateFormat.format("MMMM yyyy", month));
	}

	/** The calendar updater to update the Calendar grids and data */
	public Runnable calendarUpdater = new Runnable() {

		@Override
		public void run()
		{
			items.clear();

			DateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
			events = Utility.readCalendarEvent(getActivity());

			for (int i = 0; i < events.size(); i++)
			{
				df.format(itemmonth.getTime());
				itemmonth.add(Calendar.DATE, 1);
				items.add(events.get(i).getDate().toString());
			}
			adapter.setItems(items);
			adapter.notifyDataSetChanged();
		}
	};

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreateOptionsMenu(android.view.Menu, android.view.MenuInflater)
	 */
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
	{
		inflater.inflate(R.menu.comment, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	/**
	 * The Class EventAdapter is the adapter class that is used show list of
	 * Events for a selected date in the ListView.
	 */
	private class EventAdapter extends BaseAdapter
	{

		/* (non-Javadoc)
		 * @see android.widget.Adapter#getCount()
		 */
		@Override
		public int getCount()
		{
			return eventSel.size();
		}

		/* (non-Javadoc)
		 * @see android.widget.Adapter#getItem(int)
		 */
		@Override
		public Event getItem(int position)
		{
			return eventSel.get(position);
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
						R.layout.event_item, null);

			Event d = getItem(position);
			TextView lbl = (TextView) convertView.findViewById(R.id.lbl1);
			lbl.setText(d.getDate());

			lbl = (TextView) convertView.findViewById(R.id.lbl2);
			lbl.setText(d.getTime());

			lbl = (TextView) convertView.findViewById(R.id.lbl3);
			lbl.setText(d.getTitle());

			lbl = (TextView) convertView.findViewById(R.id.lbl4);
			lbl.setText(d.getLocation());

			ImageView img = (ImageView) convertView.findViewById(R.id.img);
			img.setImageResource(d.getImage());

			return convertView;
		}

	}
}
