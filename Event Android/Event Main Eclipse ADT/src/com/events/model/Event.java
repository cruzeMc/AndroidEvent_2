package com.events.model;

/**
 * The Class Event is a simple Java Bean that is used to hold data related to an
 * Event like title, date etc.
 */
public class Event
{

	/** The title. */
	private String title;

	/** The date. */
	private String date;

	/** The time. */
	private String time;

	/** The location. */
	private String location;

	/** The image. */
	private int image;

	/**
	 * Instantiates a new event.
	 * 
	 * @param title
	 *            the title
	 * @param date
	 *            the date
	 * @param time
	 *            the time
	 * @param location
	 *            the location
	 * @param image
	 *            the image
	 */
	public Event(String title, String date, String time, String location,
			int image)
	{
		this.date = date;
		this.title = title;
		this.time = time;
		this.location = location;
		this.image = image;
	}

	/**
	 * Gets the title.
	 * 
	 * @return the title
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * Sets the title.
	 * 
	 * @param title
	 *            the new title
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * Gets the date.
	 * 
	 * @return the date
	 */
	public String getDate()
	{
		return date;
	}

	/**
	 * Sets the date.
	 * 
	 * @param date
	 *            the new date
	 */
	public void setDate(String date)
	{
		this.date = date;
	}

	/**
	 * Gets the time.
	 * 
	 * @return the time
	 */
	public String getTime()
	{
		return time;
	}

	/**
	 * Sets the time.
	 * 
	 * @param time
	 *            the new time
	 */
	public void setTime(String time)
	{
		this.time = time;
	}

	/**
	 * Gets the location.
	 * 
	 * @return the location
	 */
	public String getLocation()
	{
		return location;
	}

	/**
	 * Sets the location.
	 * 
	 * @param location
	 *            the new location
	 */
	public void setLocation(String location)
	{
		this.location = location;
	}

	/**
	 * Gets the image.
	 * 
	 * @return the image
	 */
	public int getImage()
	{
		return image;
	}

	/**
	 * Sets the image.
	 * 
	 * @param image
	 *            the new image
	 */
	public void setImage(int image)
	{
		this.image = image;
	}

}
