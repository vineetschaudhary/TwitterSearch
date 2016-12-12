package com.twitter.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.social.OperationNotPermittedException;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.twitter.dto.ErrorInfo;

/**
 * Single point for all exceptions. We can define all user defined or container
 * exceptions here.
 * 
 * <p>
 * @author Veenit Kumar
 * @since 11-12-2016
 * </p>
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * Handler for BindException. 
	 * 
	 * <p>
	 * Actual error is logged as error in the log file.
	 * </p>
	 * <p>
	 * @param ex
	 *            OperationNotPermittedException
	 * @return error information contains status code and user defined message.
	 * </p>
	 */
	@ExceptionHandler(BindException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorInfo handleBindException(BindException ex) {
		String errorMessage = null;
		List<ObjectError> errors = ex.getBindingResult().getAllErrors();
		List<String> messages = errors.stream().map(obj -> obj.getDefaultMessage()).collect(Collectors.toList());
		errorMessage = StringUtils.collectionToDelimitedString(messages, " ,");
		return getError(HttpStatus.BAD_REQUEST.value(), errorMessage);
	}

	/**
	 * Handler for OperationNotPermittedException. 
	 * 
	 * <p>
	 * Actual error is logged as error in the log file.
	 * </p>
	 * <p>
	 * @param ex
	 *            OperationNotPermittedException
	 * @return error information contains status code and user defined message.
	 * </p>
	 */
	@ExceptionHandler(OperationNotPermittedException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorInfo handleOperationNotPermittedException(OperationNotPermittedException ex) {
		log.error(ex.getMessage());
		return getError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
	}

	
	/**
	 * Handler for MethodArgumentTypeMismatchException. If method accepts
	 * Integer value and string passed, this case will be handled in this
	 * method.
	 * <p>
	 * Actual error is logged as error in the log file.
	 * </p>
	 * <p>
	 * @param ex
	 *            MethodArgumentTypeMismatchException
	 * @return error information contains status code and user defined message.
	 * </p>
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorInfo handeMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
		log.error(ex.getMessage());
		return getError(HttpStatus.BAD_REQUEST.value(), String.format("%s field type mismatch.", ex.getParameter().getParameterName()));
	}

	/**
	 * Handler for MissingServletRequestParameterException. If required
	 * parameter is not passed then that case will be handled here.
	 * <p>
	 * Actual error is logged as error in the log file.
	 * </p>
	 * <p>
	 * 
	 * @param ex
	 *            MissingServletRequestParameterException
	 * @return error information contains status code and user defined message.
	 * </p>
	 */
	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorInfo handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
		log.error(ex.getMessage());
		return getError(HttpStatus.BAD_REQUEST.value(), String.format("Required parameter %s is not present.", ex.getParameterName()));
	}

	/**
	 * Handler for Exception. If no handler found for exception thrown then it
	 * will be handled in this handler method.
	 * 
	 * <p>
	 * Actual error is logged as error in the log file.
	 * </p>
	 * <p>
	 * @param ex
	 *            MissingServletRequestParameterException
	 * @return error information contains status code and user defined message.
	 *         </p>
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ErrorInfo handleException(Exception ex) {
		log.error(ex.getMessage());
		return getError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Unexpected error occured.");
	}

	/**
	 * Creates ErrorInfo based on parameters passed.
	 * <p>
	 * @param errorCode errorCode
	 * @param message user defined message.
	 * @return error info object with values.
	 * </p>
	 */
	private ErrorInfo getError(int errorCode, String message) {
		ErrorInfo error = new ErrorInfo();
		error.setStatusCode(errorCode);
		error.setMessage(message);
		return error;
	}

}
