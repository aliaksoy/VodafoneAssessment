package com.vodafone.aliaksoy.assessment.model;

import io.swagger.annotations.ApiModel;

/**
 * @author aaksoy
 *
 */

@ApiModel(value = "ResultModel", description = "ResultModel Model")
public class ResultModel {

	private String message;

	public ResultModel(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ResultModel [message=" + message + "]";
	}

}
