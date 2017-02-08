package com.events.calendar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.events.R;

/**
 * The Class CalendarAdapter is the Adapter class for Calendar view to display dates in Grid format.
 */
public class CalendarAdapter extends BaseAdapter
{
	
	/** The context. */
	private Context mContext;

	/** The month. */
	private java.util.Calendar month;
	
	/** The calendar instance for previous month. */
	public GregorianCalendar pmonth; // calendar instance for previous month
	
	/** Calendar instance for previous month for getting complete view. */
	public GregorianCalendar pmonthmaxset;
	
	/** The selected date. */
	private GregorianCalendar selectedDate;
	
	/** The first day. */
	int firstDay;
	
	/** The max week number. */
	int maxWeeknumber;
	
	/** The previous month maximum day. */
	int maxP;
	
	/** The Calendar max off days. */
	int calMaxP;
	
	/** The last week day. */
	int lastWeekDay;
	
	/** The left days. */
	int leftDays;
	
	/** The month length. */
	int mnthlength;
	
	/** The current date string. */
	String itemvalue, curentDateString;
	
	/** The DateFormat. */
	DateFormat df;

	/** The items. */
	private ArrayList<String> items;
	
	/** The day string. */
	public static List<String> dayString;
	
	/** The previous view. */
	private View previousView;

	/**
	 * Instantiates a new calendar adapter.
	 *
	 * @param c the c
	 * @param monthCalendar the month calendar
	 */
	public CalendarAdapter(Context c, GregorianCalendar monthCalendar)
	{
		CalendarAdapter.dayString = new ArrayList<String>();
		Locale.setDefault(Locale.US);
		month = monthCalendar;
		selectedDate = (GregorianCalendar) monthCalendar.clone();
		mContext = c;
		month.set(Calendar.DAY_OF_MONTH, 1);
		items = new ArrayList<String>();
		df = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
		curentDateString = df.format(selectedDate.getTime());
		refreshDays();
	}

	/**
	 * Sets the items.
	 *
	 * @param items the new items
	 */
	public void setItems(ArrayList<String> items)
	{
		for (int i = 0; i != items.size(); i++)
		{
			if (items.get(i).length() == 1)
			{
				items.set(i, "0" + items.get(i));
			}
		}
		this.items = items;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount()
	{
		return dayString.size();
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position)
	{
		return dayString.get(position);
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position)
	{
		return 0;
	}

	// create a new view for each item referenced by the Adapter
	/* (non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View v = convertView;
		TextView dayView;
		if (convertView == null)
		{ // if it's not recycled, initialize some
			// attributes
			LayoutInflater vi = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.calendar_item, null);

		}
		dayView = (TextView) v.findViewById(R.id.date);
		// separates daystring into parts.
		String[] separatedTime = dayString.get(position).split("-");

		String gridvalue = separatedTime[0].replaceFirst("^0*", "");
		// checking whether the day is in current month or not.
		if (Integer.parseInt(gridvalue) > 1 && position < firstDay)
		{
			// setting offdays to white color.
			dayView.setTextColor(mContext.getResources().getColor(
					R.color.main_color_gray));
			dayView.setClickable(false);
			dayView.setFocusable(false);
		}
		else if (Integer.parseInt(gridvalue) < 7 && position > 28)
		{
			dayView.setTextColor(mContext.getResources().getColor(
					R.color.main_color_gray));
			dayView.setClickable(false);
			dayView.setFocusable(false);
		}
		else
		{
			// setting curent month's days in blue color.
			dayView.setTextColor(mContext.getResources().getColor(
					R.color.main_color_gray_dk));
		}

		if (dayString.get(position).equals(curentDateString))
		{
			setSelected(v);
			previousView = v;
		}
		else
		{
			v.setBackgroundResource(R.drawable.cal_bg);
		}
		dayView.setText(gridvalue);

		// create date string for comparison
		String date = dayString.get(position);

		if (date.length() == 1)
		{
			date = "0" + date;
		}
		String monthStr = "" + (month.get(Calendar.MONTH) + 1);
		if (monthStr.length() == 1)
		{
			monthStr = "0" + monthStr;
		}

		// show icon if date is not empty and it exists in the items array
		ImageView iw = (ImageView) v.findViewById(R.id.date_icon);
		if (date.length() > 0 && items != null && items.contains(date))
		{
			iw.setVisibility(View.VISIBLE);
		}
		else
		{
			iw.setVisibility(View.INVISIBLE);
		}
		return v;
	}

	/**
	 * Sets the selected date.
	 *
	 * @param view the view
	 * @return the view
	 */
	public View setSelected(View view)
	{
		if (previousView != null)
		{
			previousView.setBackgroundResource(R.drawable.cal_bg);
		}
		previousView = view;
		view.setBackgroundResource(R.drawable.cal_bg_sel);
		return view;
	}

	/**
	 * Refresh days.
	 */
	public void refreshDays()
	{
		// clear items
		items.clear();
		dayString.clear();
		Locale.setDefault(Locale.US);
		pmonth = (GregorianCalendar) month.clone();
		// month start day. ie; sun, mon, etc
		firstDay = month.get(Calendar.DAY_OF_WEEK);
		// finding number of weeks in current month.
		maxWeeknumber = month.getActualMaximum(Calendar.WEEK_OF_MONTH);
		// allocating maximum row number for the gridview.
		mnthlength = maxWeeknumber * 7;
		maxP = getMaxP(); // previous month maximum day 31,30....
		calMaxP = maxP - (firstDay - 1);// calendar offday starting 24,25 ...
		/**
		 * Calendar instance for getting a complete gridview including the three
		 * month's (previous,current,next) dates.
		 */
		pmonthmaxset = (GregorianCalendar) pmonth.clone();
		/**
		 * setting the start date as previous month's required date.
		 */
		pmonthmaxset.set(Calendar.DAY_OF_MONTH, calMaxP + 1);

		/**
		 * filling calendar gridview.
		 */
		for (int n = 0; n < mnthlength; n++)
		{

			itemvalue = df.format(pmonthmaxset.getTime());
			pmonthmaxset.add(Calendar.DATE, 1);
			dayString.add(itemvalue);

		}
	}

	/**
	 * Gets the previous month maximum day.
	 *
	 * @return the max day
	 */
	private int getMaxP()
	{
		int maxP;
		if (month.get(Calendar.MONTH) == month.getActualMinimum(Calendar.MONTH))
		{
			pmonth.set(month.get(Calendar.YEAR) - 1,
					month.getActualMaximum(Calendar.MONTH), 1);
		}
		else
		{
			pmonth.set(Calendar.MONTH, month.get(Calendar.MONTH) - 1);
		}
		maxP = pmonth.getActualMaximum(Calendar.DAY_OF_MONTH);

		return maxP;
	}

}