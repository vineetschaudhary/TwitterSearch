package com.twitter.exception;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
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
	public @ResponseBody ErrorInfo handeMethodArgumentTypeMismatchException(HttpServletResponse response, MethodArgumentTypeMismatchException ex) {
		log.error(ex.getMessage());
		response.setStatus(HttpStatus.BAD_REQUEST.value());
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
	public @ResponseBody ErrorInfo handleMissingServletRequestParameterException(HttpServletResponse response,
			MissingServletRequestParameterException ex) {
		log.error(ex.getMessage());
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		return getError(HttpStatus.BAD_REQUEST.value(), String.format("Required parameter %s is not present.", ex.getParameterName()));
	}

	/**
	 * Handler for ValidationException. It's called when argument value is not valid.
	 * <p>
	 * Actual error is logged as error in the log file.
	 * </p>
	 * <p>
	 * 
	 * @param ex
	 *            ValidationException
	 * @return error information contains status code and user defined message.
	 * </p>
	 */
	@ExceptionHandler(ValidationException.class)
	public @ResponseBody ErrorInfo handleValidationException(HttpServletResponse response,
			ValidationException ex) {
		log.error(ex.getMessage());
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		return getError(HttpStatus.BAD_REQUEST.value(), "Invalid method parameters.");
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
	public @ResponseBody ErrorInfo handleException(HttpServletResponse response, Exception ex) {
		log.error(ex.getMessage());
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
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
