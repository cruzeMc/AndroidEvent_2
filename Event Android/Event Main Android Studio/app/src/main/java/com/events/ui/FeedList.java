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
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;

import com.events.MapViewActivity;
import com.events.R;
import com.events.custom.CustomFragment;
import com.events.model.Feed;

/**
 * The Class FeedList is the Fragment class that is launched when the user
 * clicks on Feed option in Left navigation drawer . It simply shows a dummy
 * list of Social media Feeds. You can customize this class to display actual
 * Feed listing.
 */
public class FeedList extends CustomFragment {
	/** The feed list. */
	private ArrayList<Feed> fList;

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.programs, null);
		setHasOptionsMenu(true);

		setFeedList(v);
		return v;
	}

	/**
	 * Setup and initialize the feed list view.
	 * 
	 * @param v
	 *            the root view
	 */
	private void setFeedList(View v) {
		loadDummyFeeds();

		ListView list = (ListView) v.findViewById(R.id.list);
		list.setAdapter(new ProgramAdapter());
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3)
			{
			}
		});
	}

	/**
	 * Load a dummy list of feeds. You need to write your own logic to load
	 * actual list of feeds.
	 * 
	 */
	private void loadDummyFeeds() {
		ArrayList<Feed> sl = new ArrayList<Feed>();
		sl.add(new Feed("Dolce & Gabbana runway show", "@DolceG",
				"This fashion show will beautiful, "
						+ "I will present what do you think? #fashion",
				"View other 903 Comments", new int[] { R.drawable.feed11,
						R.drawable.feed12, R.drawable.feed13,
						R.drawable.feed11, R.drawable.feed12,
						R.drawable.feed13, R.drawable.feed11,
						R.drawable.feed12, R.drawable.feed13 },
				R.drawable.feed1));
		sl.add(new Feed("Dior party", "@DiorP",
				"Many excellent people in fashion, I will "
						+ "write on my blog! #milanfashionweek",
				"View other 34 Comments ", new int[] { R.drawable.feed21,
						R.drawable.feed22, R.drawable.feed23,
						R.drawable.feed21, R.drawable.feed22,
						R.drawable.feed23, R.drawable.feed21,
						R.drawable.feed22, R.drawable.feed23 },
				R.drawable.feed2));

		fList = new ArrayList<Feed>(sl);
		fList.addAll(sl);
		fList.addAll(sl);
		fList.addAll(sl);
		fList.addAll(sl);
		fList.addAll(sl);
		fList.addAll(sl);
	}

	/**
	 * The Class FeedAdapter is the adapter class for Feed ListView. The current
	 * implementation simply shows dummy contents and you can customize this
	 * class to display actual contents as per your need.
	 */
	private class ProgramAdapter extends BaseAdapter {

		/** The param. */
		private LayoutParams param = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);

		/* (non-Javadoc)
		 * @see android.widget.Adapter#getCount()
		 */
		@Override
		public int getCount()
		{
			return fList.size();
		}

		/* (non-Javadoc)
		 * @see android.widget.Adapter#getItem(int)
		 */
		@Override
		public Feed getItem(int position)
		{
			return fList.get(position);
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
		public View getView(int position, View convertView, ViewGroup parent)	{
			if (convertView == null)
				convertView = getLayoutInflater(null).inflate(
						R.layout.feed_item, null);

			Feed d = getItem(position);
			TextView lbl = (TextView) convertView.findViewById(R.id.lbl1);
			lbl.setText(d.getTitle());

			lbl = (TextView) convertView.findViewById(R.id.lbl2);
			lbl.setText(d.getName());

			lbl = (TextView) convertView.findViewById(R.id.lbl3);
			lbl.setText(d.getMsg());

			lbl = (TextView) convertView.findViewById(R.id.lbl4);
			lbl.setText(d.getComment());

			ImageView img;
			if (position % 2 == 0)
			{
				img = (ImageView) convertView.findViewById(R.id.img1);
				convertView.findViewById(R.id.img2).setVisibility(View.GONE);
			}
			else
			{
				img = (ImageView) convertView.findViewById(R.id.img2);
				convertView.findViewById(R.id.img1).setVisibility(View.GONE);
			}
			img.setVisibility(View.VISIBLE);
			img.setImageResource(d.getImage());

			LinearLayout vImg = (LinearLayout) convertView
					.findViewById(R.id.vImg);
			vImg.removeAllViews();
			for (int i = 0; i < d.getImages().length; i++)
			{
				img = new ImageView(getActivity());
				img.setPadding(0, 0,
						(int) (getResources().getDisplayMetrics().density * 3),
						0);
				img.setBackgroundColor(getResources().getColor(
						R.color.main_color_orange));
				img.setImageResource(d.getImages()[i]);
				img.setScaleType(ScaleType.FIT_XY);
				vImg.addView(img, param);
			}

			return convertView;
		}

	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreateOptionsMenu(android.view.Menu, android.view.MenuInflater)
	 */
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.map, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item)	{
		if (item.getItemId() == R.id.menu_map)
			startActivity(new Intent(getActivity(), MapViewActivity.class));
		return super.onOptionsItemSelected(item);
	}
}
