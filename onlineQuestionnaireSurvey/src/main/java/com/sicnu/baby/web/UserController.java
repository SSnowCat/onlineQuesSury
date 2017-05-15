package com.sicnu.baby.web;



import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sicnu.baby.bean.User;
import com.sicnu.baby.dto.QuestionnaireResult;
import com.sicnu.baby.dto.Url;
import com.sicnu.baby.exception.QuesSurveyDataException;
import com.sicnu.baby.exception.QuesSurveyException;
import com.sicnu.baby.service.UserService;

@Controller
public class UserController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private UserService userService;
	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	/**
	 * 用户登录
	 * @param request
	 * @param response
	 * @param json
	 * @return QuestionnaireResult<User>
	 */
	@RequestMapping(value="/session/user",
			method=RequestMethod.POST,
			produces={"application/json;charset=UTF-8"},
			headers={"content-type=application/json;charset=utf-8"})
	public @ResponseBody QuestionnaireResult<Url> login(HttpSession session,
			HttpServletResponse response,@RequestBody JSONObject json){
		String username = json.optString("username");
		String password = json.optString("password");
		String identify = json.optString("identify");
		String code = session.getAttribute("identify").toString();
		if(!identify.toLowerCase().equals(code)){
			return new QuestionnaireResult<>(false, "验证码错误");
		}

		User user = userService.login(username, password);
		if(user!=null){
			session.setAttribute("user", user);
			return new QuestionnaireResult<Url>(true, new Url("index.html"), 1);
		}else{
			return new QuestionnaireResult<Url>(false, "用户名密码错误");
		}
	}

	/**
	 * 注销登陆
	 * @param session
	 * @return QuestionnaireResult<Url>
	 */
	@RequestMapping(value="/delete/user/private",
			method=RequestMethod.GET,
			produces={"application/json;charset=UTF-8"})
	public @ResponseBody QuestionnaireResult<Url> deleteUser(HttpSession session){
		session.setAttribute("user", null);
		//session.invalidate();
		return new QuestionnaireResult<>(true, new Url("login.html"),1);
	}
	
	/**
	 * 用户登录后获取用户名
	 * @param session
	 * @return QuestionnaireResult<String>
	 */
	@RequestMapping(value="/get/user/private",
			method=RequestMethod.GET,
			produces={"application/json;charset=UTF-8"})
	public @ResponseBody QuestionnaireResult<String> getUser(HttpSession session){
		User user = (User)session.getAttribute("user");
		return new QuestionnaireResult<>(true, user.getUsername(),1);
	}
	/**
	 * 用户注册账号
	 * @param userJson
	 * @return QuestionnaireResult<String>
	 */
	@RequestMapping(value="/put/user",
			method=RequestMethod.POST,
			produces={"application/json;charset=UTF-8"},
			headers={"content-type=application/json;charset=UTF-8"})
	public @ResponseBody QuestionnaireResult<Url> regUser(@RequestBody JSONObject userJson,HttpSession session){
		String code = session.getAttribute("identify").toString();
		try{
			boolean isSuccess = userService.regUser(userJson,code);
			if(isSuccess){
				return new QuestionnaireResult<Url>(true, new Url("../login.html"), 1);
			}else{
				return new QuestionnaireResult<Url>(false, "系统故障,注册失败");
			}
		}catch (QuesSurveyDataException e) {
			logger.error(e.getMessage(),e);
			return new QuestionnaireResult<Url>(false,e.getMessage());
		}catch (QuesSurveyException e) {
			logger.error(e.getMessage(),e);
			return new QuestionnaireResult<Url>(false, e.getMessage());
		}
	}

	/**
	 * 用户修改密码
	 * @param json
	 * @param response
	 * @return QuestionnaireResult<String>
	 */
	@RequestMapping(value="/patch/user/private",
			method=RequestMethod.POST,
			produces={"application/json;charset=UTF-8"},
			headers={"content-type=application/json;charset=UTF-8"})
	public @ResponseBody QuestionnaireResult<Url> updateUserPassword(@RequestBody JSONObject json,HttpSession session){
		User user = (User)session.getAttribute("user");
		String username = user.getUsername();
		String password = json.optString("newPassword");
		String rePassword = json.optString("reNewPassword");
		String identify = json.optString("identify");
		String code = session.getAttribute("identify").toString();
		if(!identify.toLowerCase().equals(code)){
			return new QuestionnaireResult<>(false, "验证码错误");
		}
		if(!password.equals(rePassword)){
			return new QuestionnaireResult<>(false, "两次密码不一致");
		}
		if(userService.updatePassword(username, password)){
			session.invalidate();
			return new QuestionnaireResult<>(true, new Url("../login.html"), 1);
		}else{
			return new QuestionnaireResult<>(false, "修改失败");	
		}
	}

	/**
	 * 用户修改手机号
	 * @param json
	 * @return QuestionnaireResult<String>
	 */
	@RequestMapping(value="/phone/patch/user/private",
			method=RequestMethod.POST,
			produces={"application/json;charset=UTF-8"},
			headers={"content-type=application/json;charset=UTF-8"})
	public @ResponseBody QuestionnaireResult<String> updateUserPhone(@RequestBody JSONObject json,HttpSession session){
		User user = (User)session.getAttribute("user");
		long userPhone = json.optLong("userPhone");
		String identify = json.optString("identify");
		String code = session.getAttribute("identify").toString();
		if(!identify.toLowerCase().equals(code)){
			return new QuestionnaireResult<>(false, "验证码错误");
		}
		if(userService.updatePhone(user.getUsername(), userPhone)){
			return new QuestionnaireResult<String>(true, "success.html", 1);
		}else {
			return new QuestionnaireResult<>(false, "修改电话号码失败");	
		}
	}

	/**
	 * 用户找回密码
	 * @param json
	 * @return QuestionnaireResult<String>
	 */
	@RequestMapping(value="/get/user",
			method=RequestMethod.POST,
			produces={"application/json;charset=UTF-8"},
			headers={"content-type=application/json;charset=UTF-8"})
	public @ResponseBody QuestionnaireResult<Url> findUser(@RequestBody JSONObject json,HttpSession session){
		String username = json.optString("username");
		String password = json.optString("password");
		String rePassword = json.optString("rePassword");
		long userPhone = json.optLong("userPhone");
		String identify = json.optString("identify");
		String code = session.getAttribute("identify").toString();
		if(!identify.toLowerCase().equals(code)){
			return new QuestionnaireResult<>(false, "验证码错误");
		}
		try {
			if(userService.findUserPassword(username, userPhone, password, rePassword)){
				return new QuestionnaireResult<>(true, new Url("../login.html"), 1);
			}
			return new QuestionnaireResult<>(false, "找回密码失败");
		} catch (QuesSurveyException e) {
			logger.error(e.getMessage(),e);
			return new QuestionnaireResult<>(false, e.getMessage());
		}
	}
	
	
}
