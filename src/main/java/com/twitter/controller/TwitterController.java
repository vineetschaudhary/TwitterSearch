package com.twitter.controller;

import java.util.Optional;

import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.Min;

import org.assertj.core.util.Preconditions;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
	 * 
	 * @param input
	 *            - Contains values to be searched.
	 * @return SearchOutput - This is returned in JSON format.
	 */
	@RequestMapping(path = "/query", method = RequestMethod.GET)
	public @ResponseBody SearchOutput searchText(@RequestParam(required = true) String query,
			@RequestParam(required=false) Optional<Boolean> exact, @RequestParam(required=false) Optional<Integer> num) {
		if(StringUtils.isEmpty(query) || (num.isPresent() && num.get() < 1)){
			throw new ValidationException();
		}
		SearchOutput output = twitterSearchEngine.getSearchResults(query, exact, num);
		LoggingUtil.logJsonDebug(logger, output);
		return output;
	}
}
