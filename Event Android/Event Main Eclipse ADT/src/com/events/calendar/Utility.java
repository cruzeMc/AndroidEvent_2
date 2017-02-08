package com.events.calendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import android.annotation.SuppressLint;
import android.content.Context;
import com.events.R;
import com.events.model.Event;

/**
 * The Class Utility is the Utility class for Calendar section. You can write
 * all of your utility method related to Calendar section like loading events
 * from device calendar.
 */
public class Utility
{

	/**
	 * Read calendar events.
	 * 
	 * @param context
	 *            the context
	 * @return the array list
	 */
	public static ArrayList<Event> readCalendarEvent(Context context)
	{

		ArrayList<Event> events = new ArrayList<Event>();

		events.add(new Event("Milan Fashion Week", getDate(System
				.currentTimeMillis() - 2 * 24 * 60 * 60 * 1000), "10 AM",
				"at Milan, Italy", R.drawable.img_event1));
		events.add(new Event("NY Startup Weekend", getDate(System
				.currentTimeMillis() - 2 * 24 * 60 * 60 * 1000), "12 AM",
				"at New York, US", R.drawable.img_event2));
		events.add(new Event("Another Fashion Week", getDate(System
				.currentTimeMillis() - 2 * 24 * 60 * 60 * 1000), "08 PM",
				"at Milan, Italy", R.drawable.img_event1));
		events.add(new Event("Another Startup Weekend", getDate(System
				.currentTimeMillis() - 2 * 24 * 60 * 60 * 1000), "11 PM",
				"at New York, US", R.drawable.img_event2));

		events.add(new Event("Milan Fashion Week", getDate(System
				.currentTimeMillis() - 5 * 24 * 60 * 60 * 1000), "10 AM",
				"at Milan, Italy", R.drawable.img_event1));
		events.add(new Event("NY Startup Weekend", getDate(System
				.currentTimeMillis() - 5 * 24 * 60 * 60 * 1000), "12 AM",
				"at New York, US", R.drawable.img_event2));
		events.add(new Event("Another Fashion Week", getDate(System
				.currentTimeMillis() - 5 * 24 * 60 * 60 * 1000), "08 PM",
				"at Milan, Italy", R.drawable.img_event1));
		events.add(new Event("Another Startup Weekend", getDate(System
				.currentTimeMillis() - 5 * 24 * 60 * 60 * 1000), "11 PM",
				"at New York, US", R.drawable.img_event2));

		events.add(new Event("Milan Fashion Week", getDate(System
				.currentTimeMillis() + 2 * 24 * 60 * 60 * 1000), "10 AM",
				"at Milan, Italy", R.drawable.img_event1));
		events.add(new Event("NY Startup Weekend", getDate(System
				.currentTimeMillis() + 2 * 24 * 60 * 60 * 1000), "12 AM",
				"at New York, US", R.drawable.img_event2));
		events.add(new Event("Another Fashion Week", getDate(System
				.currentTimeMillis() + 2 * 24 * 60 * 60 * 1000), "08 PM",
				"at Milan, Italy", R.drawable.img_event1));
		events.add(new Event("Another Startup Weekend", getDate(System
				.currentTimeMillis() + 2 * 24 * 60 * 60 * 1000), "11 PM",
				"at New York, US", R.drawable.img_event2));

		events.add(new Event("Milan Fashion Week", getDate(System
				.currentTimeMillis() + 6 * 24 * 60 * 60 * 1000), "10 AM",
				"at Milan, Italy", R.drawable.img_event1));
		events.add(new Event("NY Startup Weekend", getDate(System
				.currentTimeMillis() + 6 * 24 * 60 * 60 * 1000), "12 AM",
				"at New York, US", R.drawable.img_event2));
		events.add(new Event("Another Fashion Week", getDate(System
				.currentTimeMillis() + 6 * 24 * 60 * 60 * 1000), "08 PM",
				"at Milan, Italy", R.drawable.img_event1));
		events.add(new Event("Another Startup Weekend", getDate(System
				.currentTimeMillis() + 6 * 24 * 60 * 60 * 1000), "11 PM",
				"at New York, US", R.drawable.img_event2));
		return events;
	}

	/**
	 * Gets the date.
	 * 
	 * @param milliSeconds
	 *            the milli seconds
	 * @return the date
	 */
	@SuppressLint("SimpleDateFormat")
	public static String getDate(long milliSeconds)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milliSeconds);
		return formatter.format(calendar.getTime());
	}
}
