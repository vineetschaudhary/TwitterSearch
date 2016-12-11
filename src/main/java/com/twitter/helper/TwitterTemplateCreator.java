package com.twitter.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Component;

/**
 * Template to connect to twitter API using Spring classes.
 * 
 * <p>
 * Created by kumar.ve.la on 09.12.2016.
 * </p>
 */
@Component
@PropertySource("classpath:application.properties")
public class TwitterTemplateCreator {
	@Value("${consumerKey}")
	String consumerKey;
	
	@Value("${consumerSecret}")
	String consumerSecret;
	
	@Value("${accessToken}")
	String accessToken;

	@Value("${accessTokenSecret}")
	String accessTokenSecret;
	
	/**
	 * Connection point to twitter API.
	 * 
	 * <p>
	 * It requires 4 parameters which are configured in application.properties
	 * file. These values can be obtained by setting up an app on twitter. These
	 * parameters are used for OAuth authentication to twitter.
	 * </p>
	 * 
	 * @return Template object with basic set of operations for interacting with
	 *         Twitter
	 */
	@Bean
	public Twitter getTwitterTemplate() {
			return new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret);
	}
}
