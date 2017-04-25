package com.sicnu.baby.dto;
/**
 * 后台返回前台的数据封装类
 * @author bulala
 *
 * @param <T>
 */
public class QuestionnaireResult<T> {
    /**
     * 是否成功
     */
	private boolean success;

	/**
	 * 数据
	 */
	private T data;
    
	/**
	 * 错误信息
	 */
	private String error;
	
	public boolean isSuccess() {
		return success;
	}



	public void setSuccess(boolean success) {
		this.success = success;
	}



	public T getData() {
		return data;
	}



	public void setData(T data) {
		this.data = data;
	}



	public String getError() {
		return error;
	}



	public void setError(String error) {
		this.error = error;
	}



	public QuestionnaireResult(boolean success, T data,int flag) {
		this.success = success;
		this.data = data;
	}



	public QuestionnaireResult(boolean success, String error) {
		this.success = success;
		this.error = error;
	}



	public QuestionnaireResult(boolean success, T data, String error) {
		super();
		this.success = success;
		this.data = data;
		this.error = error;
	}
}
