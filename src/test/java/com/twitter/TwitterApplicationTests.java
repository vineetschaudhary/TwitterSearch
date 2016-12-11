package com.twitter;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.twitter.controller.TwitterController;

/**
 * MockMvc test cases for Twitter search api.
 * <p>
 * @author Veenit Kumar
 * @Since 10-11-2016
 * </p>
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TwitterController.class })
@ComponentScan("com.twitter")
@EnableWebMvc
@WebAppConfiguration
public class TwitterApplicationTests {
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	/**
	 * Initialises of the fields before test cast running.
	 * 
	 * @throws Exception - exception to be thrown
	 */
	@Before
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	/**
	 * Should return status code 200.
	 * 
	 * @throws Exception exception thrown
	 */
	@Test
	public void testControllerStatusOk() throws Exception {
		mockMvc.perform(get("/query")
				.param("query","mars mission")
				.param("exact", "false")
				.param("num", "1")).andExpect(status().isOk());
	}
	
	/**
	 * Should return status code in the 400 series.
	 * 
	 * @throws Exception exception thrown
	 */
	@Test
	public void testStringNumParameter() throws Exception {
		mockMvc.perform(get("/query")
				.param("query","mars mission")
				.param("exact", "false")
				.param("num", "hello")).andExpect(status().is4xxClientError());
		
	}
	
	/**
	 * Should return status code in the 400 series.
	 * 
	 * @throws Exception exception thrown
	 */
	@Test
	public void testNegativeNumParameter() throws Exception {
		mockMvc.perform(get("/query")
				.param("query","mars mission")
				.param("exact", "false")
				.param("num", "-1")).andExpect(status().is4xxClientError());
		
	}
	
	/**
	 * Should return status code in the 400 series.
	 * 
	 * @throws Exception exception thrown
	 */
	@Test
	public void testNoQueryParameter() throws Exception {
		mockMvc.perform(get("/query")
				.param("exact", "false")
				.param("num", "-1")).andExpect(status().is4xxClientError());
		
	}
	
	/**
	 * Should return status 200.
	 * 
	 * @throws Exception exception thrown
	 */
	@Test
	public void testOnlyQueryParameter() throws Exception {
		mockMvc.perform(get("/query")
				.param("query","mars mission")).andExpect(status().isOk());
		
	}
	
	/**
	 * Should return result with size 1.
	 * 
	 * @throws Exception exception thrown
	 */
	@Test
	public void testResultSize() throws Exception {
		mockMvc.perform(get("/query")
				.param("query","mars mission")
				.param("exact", "false")
				.param("num", "1"))
				.andExpect(jsonPath("$.result", hasSize(1)));
		
	}
	
	/**
	 * Should return result containing mission.
	 * 
	 * @throws Exception exception thrown
	 */
	@Ignore
	public void testResultData() throws Exception {
		mockMvc.perform(get("/query")
				.param("query","mission")
				.param("exact", "false")
				.param("num", "1"))
				.andExpect(jsonPath("$.result[0].text", containsString("mission")));
		
	}
}
