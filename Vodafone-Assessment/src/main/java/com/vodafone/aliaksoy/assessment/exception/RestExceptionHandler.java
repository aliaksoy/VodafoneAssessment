package com.vodafone.aliaksoy.assessment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.HashMap;
import java.util.Map;

/**
 * @author aaksoy
 *
 */

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Map<String, String> showCustomBadRequestMessage(Exception e) {
		Map<String, String> response = new HashMap<>();
		response.put("message", "invalid input");
		return response;
	}
}
