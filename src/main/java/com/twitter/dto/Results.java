package com.twitter.dto;

/**
 * Contains search results of twitter.
 * 
 * @author Veenit Kumar
 * @since 10-12-2016
 *
 */
public class Results {
	private String id;
	private String text;
	private String user;
	
	/**
	 * @param id
	 * @param text
	 * @param user
	 */
	public Results(String id, String text, String user) {
		super();
		this.id = id;
		this.text = text;
		this.user = user;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Results [id=" + id + ", text=" + text + ", user=" + user + "]";
	}
	
	
}
