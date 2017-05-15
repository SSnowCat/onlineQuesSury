package com.sicnu.baby.service.impl;

import javax.annotation.Resource;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.sicnu.baby.bean.User;
import com.sicnu.baby.dao.UserDao;
import com.sicnu.baby.exception.QuesSurveyDataException;
import com.sicnu.baby.exception.QuesSurveyException;
import com.sicnu.baby.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	private UserDao userDao;

	@Resource
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public boolean exists(String username){
		if(userDao.countUserByUsername(username)==1){
			return true;
		}else {			
			return false;
		}
	}

	@Override
	public boolean regUser(JSONObject user,String code) throws QuesSurveyException,QuesSurveyDataException {
		try {
			String username = user.getString("username");
			System.out.println(username);
			String password = user.getString("password");
			System.out.println(password);
			long userPhone = user.getLong("userPhone");
			System.out.println(userPhone);
			String identify = user.getString("identify");
			System.out.println(identify);
			short userType = 0;
			if(identify.toLowerCase().equals(code)){
				if(exists(username)){
					throw new QuesSurveyException("用户已存在,注册失败");
				}
				User user2 = new User(username, password, userType, userPhone);
				System.out.println(user2);
				if(userDao.insert(user2)==1){
					return true;
				}else{
					return false;
				}
			}else {
				throw new QuesSurveyException("验证码错误");
			}
		} catch (JSONException e) {
			throw new QuesSurveyDataException("数据接收异常", e);
		}
	}

	@Override
	public User login(String username, String password){
		return userDao.getUser(username, password);
	}

	@Override
	public boolean updatePassword(String username, String password){	
		if(userDao.updatePassword(username, password)==1){
			return true;
		}
		return false;
	}

	@Override
	public boolean updatePhone(String username, long userPhone){
		if(userDao.updatePhone(username, userPhone)==1){
			return true;
		}
		return false;
	}

	@Override
	public boolean findUserPassword(String username, long userPhone, String password,String rePassword) throws QuesSurveyException {
		if(password.equals(rePassword)){
			User user = userDao.getUserByUserPhone(username, userPhone);
			if(user!=null){
				if(userDao.updatePassword(username, rePassword)==1){
					return true;
				}else{
					throw new QuesSurveyException("更改密码失败");
				}
			}else{
				throw new QuesSurveyException("用户名和手机号不匹配");	
			}	
		}else{
			throw new QuesSurveyException("两次密码不一致");
		}
	}

	@Override
	public User getUser(String username, String password){
		return userDao.getUser(username, password);
	}

}
