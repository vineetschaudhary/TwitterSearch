package com.twitter.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.twitter.dto.SearchInput;
import com.twitter.dto.SearchOutput;
import com.twitter.helper.TwitterSearchEngine;
import com.twitter.logging.LoggingUtil;

/**
 * Handles twitter search based on query parameters. It takes JSON as input and
 * returns JSON result. Calling program can search twitter text with exact or
 * like query i.e. search the exact query text passed or text contained within
 * the twitter text.
 * 
 * @author veenit Kumar
 * @since 10-12-2016
 *
 */
@RestController
public class TwitterController {
	private static final Logger logger = LoggerFactory.getLogger(TwitterController.class);

	@Autowired
	private TwitterSearchEngine twitterSearchEngine;

	/**
	 * Entry point for Twitter search API, It then calls
	 * {@link TwitterSearchEngine} class to process the given input and returns
	 * {@link SearchOutput}.
	 * <p>
	 * @param input - holds query parameters.
     * @return SearchOutput - This is returned in JSON format.
	 * </p>
     */
	@RequestMapping(path = "/query", method = RequestMethod.GET)
	public @ResponseBody SearchOutput searchText(@Valid SearchInput input) {
		SearchOutput output = twitterSearchEngine.processQuery(input);
		LoggingUtil.logJsonDebug(logger, output);
		return output;
	}
}
