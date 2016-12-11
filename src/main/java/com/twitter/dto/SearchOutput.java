package com.twitter.dto;

import java.util.List;
/**
 * Output for Twitter search API. 
 * 
 * @author Veenit Kumar
 * @since 10-12-2016	
 */
public class SearchOutput {
	private String query;
	private boolean exact;
	private int count;
	private List<Results> result;

	private SearchOutput() {
	}
	
	public static class Builder {
		private final SearchOutput output;

		private Builder(SearchOutput output) {
			this.output = output;
		}

		public static Builder build() {
			return new Builder(new SearchOutput());
		}
		
		public Builder withQuery(String query){
			output.query = query;
			return this;
		}
		
		public Builder withExact(boolean exact){
			output.exact = exact;
			return this;
		}
		
		public Builder withCount(int count){
			output.count = count;
			return this;
		}
		
		public Builder withResult(List<Results> userDetail){
			output.result = userDetail;
			return this;
		}
		
		public SearchOutput get(){
			return this.output;
		}
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
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @return the result
	 */
	public List<Results> getResult() {
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SearchOutput [query=" + query + ", exact=" + exact + ", count=" + count + ", result=" + result + "]";
	}

}
