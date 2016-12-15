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
	 * Calls processResults method and prepare the SearchOutput.
	 *
	 * <p>
	 * @param input - Query parameters
	 * @return SearchOutput - This is returned in JSON format.
	 * </p>
	 */
	public SearchOutput processQuery(SearchInput input) {
		LoggingUtil.logDebug(logger, "Input Parameters::" + input.toString());
		List<Results> results = processResults(input);
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
		return input.isExact() ? "\"" + input.getQuery().replaceAll("\"", "") + "\"" : input.getQuery().replaceAll("\"", "");
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
	private List<Results> processResults(SearchInput input) {
		SearchParameters parameters = new SearchParameters(query(input)).count(input.getNum());
		LoggingUtil.logDebug(logger, "query after changes::" + parameters.getQuery());
		SearchResults results = twitterTemplate.searchOperations().search(parameters);
		LoggingUtil.logDebug(logger, "Result from twitter api::" + results);
		return mapResults(results);
	}

	/**
	 * Maps SearchResults data to the new list containing Results.
	 *
	 * <p>
	 * @param results
	 *            - twitter search result.
	 * @return filtered result.
	 * </p>
	 */
	private List<Results> mapResults(SearchResults results) {
		return results.getTweets().stream()
				.map(t -> new Results(t.getIdStr(), t.getText(), t.getFromUser())).collect(Collectors.toList());
	}

}
