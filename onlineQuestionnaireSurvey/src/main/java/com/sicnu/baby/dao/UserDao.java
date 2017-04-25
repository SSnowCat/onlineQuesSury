package com.sicnu.baby.dao;

import org.apache.ibatis.annotations.Param;

import com.sicnu.baby.bean.User;

public interface UserDao {
	/**
	 * 用户注册向数据库添加新用户
	 * @param user
	 * @return 成功返回1
	 */
	public int insert(@Param("user")User user);
	/**
	 * 用户修改密码
	 * @param password
	 * @return 成功返回1
	 */
	public int updatePassword(@Param("username") String username, @Param("password")String password);
	
	/**
	 * 用户修改关联手机号
	 * @param userPhone
	 * @return 成功返回1
	 */
	public int updatePhone(@Param("username")String username,@Param("userPhone")long userPhone);
	
	/**
	 * 查询数据库中是否已存在该用户
	 * @param username
	 * @return 成功返回1
	 */
	public int countUserByUsername(@Param("username")String username);
	
	/**
	 * 根据用户名和密码查询用户，用于登陆
	 * @param username
	 * @param password
	 * @return 成功返回User实例,否则NULL
	 */
	public User getUser(@Param("username")String username,@Param("password")String password);
	
	/**
	 * 根据用户名和手机号查询用户,用于找回密码
	 * @param username
	 * @param userPhone
	 * @return 成功返回User实例,否则null
	 */
	public User getUserByUserPhone(@Param("username")String username,@Param("userPhone")long userPhone);
}
