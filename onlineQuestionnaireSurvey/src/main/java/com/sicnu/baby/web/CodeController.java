package com.sicnu.baby.web;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sicnu.baby.util.VerifyCodeUtils;

@Controller
public class CodeController {
	
	private  Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "/code/{time}",method = RequestMethod.GET)
	public void getCode(HttpSession session,HttpServletResponse response){
		
		//禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        
      //生成随机字串  
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);  
        //存入会话session   
        session.setAttribute("identify", verifyCode.toLowerCase());
        System.out.println(session.getAttribute("identify"));
        //生成图片  
        int w = 200, h = 80;  
        try {
			VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}  
	}
}
