package com.twitter.helper;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.SearchParameters;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Component;

import com.twitter.dto.Results;
import com.twitter.dto.SearchInput;
import com.twitter.dto.SearchOutput;
import com.twitter.logging.LoggingUtil;

/**
 * It handles the search operation for twitter search API. Uses Spring Social
 * API to connect and query twitter.
 *
 * <p>
 * @author Veenit Kumar
 * @since 10-12-2016
 * </p>
 */
@Component
public class TwitterSearchEngine {
	private Logger logger = LoggerFactory.getLogger(TwitterSearchEngine.class);
	
	@Autowired
	private Twitter twitterTemplate;
	/**
	 * Calls getResults method and prepare the SearchOutput.
	 *
	 * <p>
	 * @param query - Query text to be searched.
	 * @param exact - Search the exact text or like text.
	 * @param num   - Number of records to be returned.
	 * @return SearchOutput - This is returned in JSON format.
	 * </p>
	 */
	public SearchOutput getSearchResults(String query, boolean exact, int num) {
		SearchInput input = new SearchInput(query).exact(exact).num(num);
		LoggingUtil.logDebug(logger, "Input Parameters::" + input);
		List<Results> results = getResults(input);
		LoggingUtil.logDebug(logger, "Filtered results::" + results);
		return SearchOutput.Builder.build().withExact(input.isExact()).withQuery(input.getQuery())
				.withCount(results.size()).withResult(results).get();
	}

	/**
	 * If search is exact search the it adds the query text in double quotes as
	 * this is query syntax for twitter. If the text is within double quotes,
	 * for example "I am a developer", then twitter api will look for the exact
	 * sentence rather then each individual words.
	 * 
	 * @param input
	 *            - contains query parameter values.
	 * @return updated string value.
	 */
	private String query(SearchInput input) {
		return input.isExact() ? "\"" + input.getQuery() + "\"" : input.getQuery();
	}

	/**
	 * Calls twitter API and returns search results. It will create the
	 * SearchParameters and send it to the search operations to get the desired results, It will add
	 * query with the number of results we need.
	 * 
	 * @param input
	 *            - contains query parameter values.
	 * @return search results
	 */
	private List<Results> getResults(SearchInput input) {
		SearchParameters parameters = new SearchParameters(query(input)).count(input.getNum());
		SearchResults results = twitterTemplate.searchOperations().search(parameters);
		LoggingUtil.logDebug(logger, "Result from twitter api::" + results);
		return filterResults(results);
	}

	/**
	 * Filters data based on the input parameters.
	 * <p>
	 * 1. Filters number of result to be returned based on value passed in
	 * SearchInput.num. It's default value is set to 20 so by default 20 results
	 * are returned.<br>
	 *
	 * </p>
	 *
	 * <p>
	 * @param results
	 *            - twitter search result.
	 * @return filtered result.
	 * </p>
	 */
	private List<Results> filterResults(SearchResults results) {
		return results.getTweets().stream()
				.map(t -> new Results(t.getIdStr(), t.getText(), t.getFromUser())).collect(Collectors.toList());
	}

}
