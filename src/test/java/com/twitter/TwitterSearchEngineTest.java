package com.twitter;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.twitter.dto.Results;
import com.twitter.dto.SearchInput;
import com.twitter.dto.SearchOutput;
import com.twitter.helper.TwitterSearchEngine;

/**
 * JUnit test for TwitterSearchEngineTest class.
 * 
 * @author Veenit Kumar
 * @since 12-12-2016
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=AppConfig.class)
public class TwitterSearchEngineTest {

	@Autowired
	private TwitterSearchEngine twitterSearchEngine;
	
	@Test
	public void testProcessQueryNum(){
		SearchInput input = getSearchInput("mars mission", false, 10);
		SearchOutput output = twitterSearchEngine.processQuery(input);
		Assert.notNull(output);
		Assert.notEmpty(output.getResult());
		Assert.isTrue(output.getResult().size()==10);
	}

	@Test
	public void testProcessQueryWithoutNumParameter(){
		SearchInput input = new SearchInput();
		input.setExact(false);
		input.setQuery("mars mission");
		SearchOutput output = twitterSearchEngine.processQuery(input);
		Assert.notNull(output);
		Assert.notEmpty(output.getResult());
		Assert.isTrue(output.getResult().size()==20);
	}

	
	@Test
	public void testProcessQueryText(){
		SearchInput input = getSearchInput("mars mission", true, 10);
		SearchOutput output = twitterSearchEngine.processQuery(input);
		Assert.notNull(output);
		Assert.notEmpty(output.getResult());
		List<String> messages = output.getResult().stream().filter(r -> r.getText().toLowerCase().contains("mars mission")).map(Results::getText).collect(Collectors.toList()); 
		Assert.isTrue(messages.size()==10);
	}
	
	private SearchInput getSearchInput(String query, boolean exact, int num){
		SearchInput input = new SearchInput();
		input.setExact(exact);
		input.setNum(num);
		input.setQuery(query);
		return input;
	}
}
