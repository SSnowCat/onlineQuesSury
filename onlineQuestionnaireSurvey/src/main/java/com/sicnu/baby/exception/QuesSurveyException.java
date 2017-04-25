package com.sicnu.baby.exception;

public class QuesSurveyException extends RuntimeException{

	/**
	 * 问卷调查异常 
	 */
	private static final long serialVersionUID = 1L;

	

	public QuesSurveyException(String message) {
		super(message);
	}

	public QuesSurveyException(String message, Throwable cause) {
		super(message, cause);
	}
	

}
