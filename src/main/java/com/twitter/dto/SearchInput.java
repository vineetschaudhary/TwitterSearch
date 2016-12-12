package com.twitter.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Simple POJO class holds incoming request parameter values.
 * 
 * <p>
 * @author Veenit Kumar
 * @since 10-12-2016
 * </p>
 */
public class SearchInput {
	@NotNull(message ="query parameter is required.")
	@NotEmpty(message ="query parameter cannot be empty.")
	private String query;
	private boolean exact;
	@Min(value=1, message="num cannot be less than 1")
	private int num=20;
	
	
	/**
	 * @return the query
	 */
	public String getQuery() {
		return query;
	}


	/**
	 * @param query the query to set
	 */
	public void setQuery(String query) {
		this.query = query;
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
	public void setExact(boolean exact) {
		this.exact = exact;
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
	public void setNum(int num) {
		this.num = num;
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
