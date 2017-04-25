package com.sicnu.baby.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sicnu.baby.dto.PageAndQuestionnaire;
import com.sicnu.baby.dto.QuestionnaireResult;
import com.sicnu.baby.entity.Page;
import com.sicnu.baby.service.RespondentsService;

/**
 * 被调查者相关逻辑的处理
 * @author bulala
 *
 */
@Controller
public class RespondentsController {
	private RespondentsService rService;
	
	@Resource
	public void setrService(RespondentsService rService) {
		this.rService = rService;
	}

	/**
	 * 初始化分页信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/get/page/questionnaire",
			method=RequestMethod.GET,
			produces={"application/json;chaeset=UTF-8"})
	public @ResponseBody QuestionnaireResult<PageAndQuestionnaire> getAllQuestionnaire(HttpServletRequest request){
		Page page = new Page();
		page.setCurrentPage(1);
		PageAndQuestionnaire pQuestionnaire = rService.getAllQuestionnaire(page);
		if(pQuestionnaire!=null){
			return new QuestionnaireResult<PageAndQuestionnaire>(true, pQuestionnaire, 1);			
		}else{
			return new QuestionnaireResult<>(false, "未查询到任何数据");
		}
	}
	
	/**
	 * 根据页码获取问卷
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/get/page/{index}/questionnaire",
			method=RequestMethod.GET,
			produces={"application/json;chaeset=UTF-8"})
	public @ResponseBody QuestionnaireResult<PageAndQuestionnaire> getQuestionnaireByIndex(HttpServletRequest request,@PathVariable("index") int index){
		Page page = new Page();
		page.setCurrentPage(index);
		PageAndQuestionnaire pQuestionnaire = rService.getAllQuestionnaire(page);
		if(pQuestionnaire!=null){
			return new QuestionnaireResult<PageAndQuestionnaire>(true, pQuestionnaire, 1);			
		}else{
			return new QuestionnaireResult<>(false, "未查询到任何数据");
		}
	}
}
