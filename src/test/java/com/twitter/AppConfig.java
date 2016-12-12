package com.twitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.social.twitter.api.Twitter;

import com.twitter.helper.TwitterSearchEngine;
import com.twitter.helper.TwitterTemplateCreator;

/**
 * Application configuration for junit testing.
 * 
 * @author Veenit Kumar
 *
 */
@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {
	@Autowired
	private TwitterTemplateCreator twitterTemplateCreator;
	
	@Bean
	public TwitterSearchEngine getTwitterSearchEngine(){
		return new TwitterSearchEngine();
	}
	
	@Bean
	public TwitterTemplateCreator getTwitterTemplateCreator(){
		return new TwitterTemplateCreator();
	}
	
	@Bean
	public Twitter getTwitterTemplate(){
		return twitterTemplateCreator.getTwitterTemplate();
	}

}
