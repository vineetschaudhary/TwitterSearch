package com.twitter.dto;

/**
 * Simple POJO class holds incoming request parameter values.
 * 
 * <p>
 * @author Veenit Kumar
 * @since 10-12-2016
 * </p>
 */
public class SearchInput {
	private String query;
	private boolean exact;
	private int num = 20;
	
	/**
	 * @param query
	 */
	public SearchInput(String query) {
		this.query = query;
	}
	/**
	 * @return the query
	 */
	public String getQuery() {
		return query;
	}
	
	/**
	 * @return the exact
	 */
	public boolean isExact() {
		return exact;
	}
	/**
	 * @param exact the exact to set
	 */
	public SearchInput exact(boolean exact) {
		this.exact = exact;
		return this;
	}
	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
	}
	/**
	 * @param num the num to set
	 */
	public SearchInput num(int num) {
		this.num = num;
		return this;
	}
	
	@Override
	public String toString() {
		return "SearchInput{" +
				"query='" + query + '\'' +
				", exact=" + exact +
				", num=" + num +
				'}';
	}

	
	
}
