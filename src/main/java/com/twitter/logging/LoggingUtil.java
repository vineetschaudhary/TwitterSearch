package com.twitter.logging;

import org.slf4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Writes the data to the log file.
 * 
 * @author Veenit Kumar
 * @since 11-12-2016
 */
public class LoggingUtil {
	/**
	 * Logs simple string messages.
	 * 
	 * @param logger
	 * @param message - message to be logged.
	 */
	public static void logDebug(Logger logger, String message){
		if(logger.isDebugEnabled()) logger.debug(message);
	}
	
	/**
	 * Logs object in the form of json.
	 * 
	 * @param logger 
	 * @param objectToWrite - object to be written.
	 */
	public static void logJsonDebug(Logger logger, Object objectToWrite) {
		if(logger.isDebugEnabled()) {
			try{
				ObjectMapper mapper = new ObjectMapper();
				logger.debug(mapper.writeValueAsString(objectToWrite));
			}catch(Exception ex){
				logger.error("Exception while converting object to json while logging. Class name" + objectToWrite.getClass());
			}
		}
	}
	
}
