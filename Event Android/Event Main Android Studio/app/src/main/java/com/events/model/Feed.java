package com.events.model;

/**
 * The Class Feed is a simple Java Bean that is used to hold the data of a
 * particular Feed item like title, date etc.
 */
public class Feed
{

	/** The title. */
	private String title;

	/** The name. */
	private String name;

	/** The msg. */
	private String msg;

	/** The comment. */
	private String comment;

	/** The images. */
	private int[] images;

	/** The image. */
	private int image;

	/**
	 * Instantiates a new feed.
	 * 
	 * @param title
	 *            the title
	 * @param name
	 *            the name
	 * @param msg
	 *            the msg
	 * @param comment
	 *            the comment
	 * @param images
	 *            the images
	 * @param image
	 *            the image
	 */
	public Feed(String title, String name, String msg, String comment,
			int[] images, int image)
	{
		this.title = title;
		this.name = name;
		this.msg = msg;
		this.comment = comment;
		this.image = image;
		this.images = images;
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
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the new name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Gets the msg.
	 * 
	 * @return the msg
	 */
	public String getMsg()
	{
		return msg;
	}

	/**
	 * Sets the msg.
	 * 
	 * @param msg
	 *            the new msg
	 */
	public void setMsg(String msg)
	{
		this.msg = msg;
	}

	/**
	 * Gets the comment.
	 * 
	 * @return the comment
	 */
	public String getComment()
	{
		return comment;
	}

	/**
	 * Sets the comment.
	 * 
	 * @param comment
	 *            the new comment
	 */
	public void setComment(String comment)
	{
		this.comment = comment;
	}

	/**
	 * Gets the images.
	 * 
	 * @return the images
	 */
	public int[] getImages()
	{
		return images;
	}

	/**
	 * Sets the images.
	 * 
	 * @param images
	 *            the new images
	 */
	public void setImages(int[] images)
	{
		this.images = images;
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
