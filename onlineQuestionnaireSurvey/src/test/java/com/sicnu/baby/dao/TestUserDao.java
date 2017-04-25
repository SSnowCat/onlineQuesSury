package com.sicnu.baby.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sicnu.baby.bean.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class TestUserDao {
	@Resource
	private UserDao userDao;
	
	@Test
	public void testInsert(){
		String username="2014110440";
		String password="qaz123456";
		short userType = 0;
		long userPhone=18080485343L;
		
		User user=new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setUserType(userType);
		user.setUserPhone(userPhone);
		System.out.println(userDao.insert(user));
	}
	
	@Test
	public void testUpdatePassword(){
		String username = "201411044";
		String password = "qaz123";
		userDao.updatePassword(username, password);
	}
	
	@Test
	public void testUpdatePhone(){
		String username = "2014110440";
		long userPhone=17780615345L;
		userDao.updatePhone(username, userPhone);
	}
	
	@Test
	public void testCountUserByUsername(){
		String username = "2014110440";
		System.out.println(userDao.countUserByUsername(username));
	}
	
	@Test
	public void testGetUser(){
		String username = "2014110440";
		String password = "qaz123";
		System.out.println(userDao.getUser(username, password));
	}
	
	@Test
	public void testGetUserByUserPhone(){
		String username = "2014110440";
		long userPhone = 17780615345L;
		System.out.println(userDao.getUserByUserPhone(username, userPhone));
	}
}
