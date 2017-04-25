package com.sicnu.baby.service;

import org.json.JSONObject;

import com.sicnu.baby.bean.User;
import com.sicnu.baby.exception.QuesSurveyDataException;
import com.sicnu.baby.exception.QuesSurveyException;

/**
 * 处理与用户账号相关信息的逻辑
 * @author bulala
 *
 */
public interface UserService {
	/**
	 * 查找用户是否存在
	 * @param username
	 * @return boolean
	 * @throws QuesSurveyDataException
	 */
	public boolean exists(String username);
	
	/**
	 * 用户注册
	 * @param user
	 * @return boolean
	 * @throws QuesSurveyException
	 */
	public boolean regUser(JSONObject user,String code) throws QuesSurveyException,QuesSurveyDataException;
	
	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @return User
	 * @throws QuesSurveyException
	 */
	public User login(String username,String password);
	
	/**
	 * 用户修改密码
	 * @param username
	 * @param password
	 * @return boolean
	 * @throws QuesSurveyException
	 */
	public boolean updatePassword(String username,String password);
    
	/**
	 * 修改用户手机号
	 * @param username
	 * @param userPhone
	 * @return boolean
	 * @throws QuesSurveyException
	 */
	public boolean updatePhone(String username,long userPhone);
    
	/**
	 * 用户找回密码
	 * @param username
	 * @param userPhone
	 * @param password
	 * @return boolean
	 * @throws QuesSurveyException
	 */
	public boolean findUserPassword(String username,long userPhone,String password,String rePassword)throws QuesSurveyException;
    
	/**
	 * 查看用户详情
	 * @param username
	 * @param password
	 * @return User
	 * @throws QuesSurveyException
	 */
	public User getUser(String username,String password);
}
