package com.events.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.events.R;
import com.events.custom.CustomFragment;

/**
 * The Class More is the Fragment class that is launched when the user clicks on
 * More option in Left navigation drawer and it simply shows a few options for
 * like Help, Privacy, Account, About etc. You can customize this to display
 * actual contents.
 */
public class More extends CustomFragment
{

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View v = inflater.inflate(R.layout.more, null);

		setTouchNClick(v.findViewById(R.id.help));
		setTouchNClick(v.findViewById(R.id.privacy));
		setTouchNClick(v.findViewById(R.id.terms));
		setTouchNClick(v.findViewById(R.id.account));
		setTouchNClick(v.findViewById(R.id.about));
		return v;
	}

}
