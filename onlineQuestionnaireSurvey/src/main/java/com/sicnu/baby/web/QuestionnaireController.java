package com.sicnu.baby.web;


import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sicnu.baby.bean.Questionnaire;
import com.sicnu.baby.bean.User;
import com.sicnu.baby.dto.PageAndQuestionnaire;
import com.sicnu.baby.dto.QuestionnaireResult;
import com.sicnu.baby.dto.Url;
import com.sicnu.baby.entity.Page;
import com.sicnu.baby.exception.QuesSurveyDataException;
import com.sicnu.baby.exception.QuesSurveyException;
import com.sicnu.baby.service.QuestionnaireService;


/**
 * 处理问卷相关事件
 * @author bulala
 *
 */
@Controller
public class QuestionnaireController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private QuestionnaireService questionnaireService;
    
	@Resource
	public void setQuestionnaireService(QuestionnaireService questionnaireService) {
		this.questionnaireService = questionnaireService;
	}
	
	/**
	 * 添加一份新的问卷
	 * @param json
	 * @return
	 */
	@RequestMapping(value="/put/questionnaire/private",
			method=RequestMethod.POST,
			produces={"application/json;charset=UTF-8"},
			headers={"content-type=application/json;charset=UTF-8"})
	public @ResponseBody QuestionnaireResult<Url> addQuestionnaire(@RequestBody JSONObject json,HttpSession session){
		try{
		   User user = (User)session.getAttribute("user");
		   if(questionnaireService.addQuestionnaire(json,user.getUsername())){
			   return new QuestionnaireResult<>(true,new Url("questionnaire.html"),1);
		   }
		   return new QuestionnaireResult<>(false, "添加失败");
		}catch (QuesSurveyDataException e) {
			logger.error(e.getMessage(), e);
			return new QuestionnaireResult<>(false, e.getMessage());
		}catch (QuesSurveyException e) {
			logger.error(e.getMessage(),e);
			return new QuestionnaireResult<>(false, e.getMessage());
		}
	}
	/**
	 * 用户删除一份问卷
	 * @param quesId
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/delete/{quesId}/questionnaire/private",
			method=RequestMethod.POST,
			produces={"application/json;charset=UTF-8"})
	public @ResponseBody QuestionnaireResult<String> deleteQuestionnaireByUser(@PathVariable("quesId") int quesId,HttpSession session){
		User user = (User)session.getAttribute("user");
		if(user.getUserType()==0){
			if(questionnaireService.deleteById(quesId,user.getUsername())){
				return new QuestionnaireResult<>(true, null);
			}else{
				return new QuestionnaireResult<>(false, "删除失败");
			}			
		}else{
			if(questionnaireService.deleteById(quesId,null)){
				return new QuestionnaireResult<>(true, null);
			}else{
				return new QuestionnaireResult<>(false, "删除失败");
			}
		}
	}
	
	/**
	 * 用于查看问卷
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/get/page/questionnaire/private",
			method=RequestMethod.GET,
			produces={"application/json;charset=UTF-8"})
	public @ResponseBody QuestionnaireResult<PageAndQuestionnaire> getQuestionnaire(HttpSession session){
		User user = (User)session.getAttribute("user");
		Page page = new Page();
		page.setPageNumber(8);
		page.setCurrentPage(1);
		if(user.getUserType()==0){
			PageAndQuestionnaire questionnaires = questionnaireService.getQuestionByUsername(user.getUsername(),page);
			if(questionnaires!=null){
				return new QuestionnaireResult<>(true, questionnaires, 1);
			}else{
				return new QuestionnaireResult<>(false, "未查询到任何数据");
			}			
		}else{
			PageAndQuestionnaire pQuestionnaire = questionnaireService.getAllQuestion(page);
			if(pQuestionnaire!=null){
				return new QuestionnaireResult<PageAndQuestionnaire>(true, pQuestionnaire, 1);			
			}else{
				return new QuestionnaireResult<>(false, "未查询到任何数据");
			}
		}
	}
	
	/**
	 * 获取一条问卷的详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/get/detail/{quesId}/questionnaire",
			method=RequestMethod.GET,
			produces={"application/json;charset=UTF-8"})
	public @ResponseBody QuestionnaireResult<Questionnaire> getQuestionnaireDetail(@PathVariable("quesId")  int quesId){
		Questionnaire questionnaire = questionnaireService.getQuestionnaireById(quesId);
		if(questionnaire!=null){
			questionnaire.setQesResult(null);
			return new QuestionnaireResult<>(true, questionnaire,1);
		}else{
			return new QuestionnaireResult<>(false, "未查询到任何数据");
		}
	}
	
	/**
	 * 根据页码获取问卷
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/get/page/{index}/questionnaire/private",
			method=RequestMethod.GET,
			produces={"application/json;charset=UTF-8"})
	public @ResponseBody QuestionnaireResult<PageAndQuestionnaire> getQuestionnaireByIndex(HttpSession session,@PathVariable("index") int index){
		User user = (User)session.getAttribute("user");
		Page page = new Page();
		page.setCurrentPage(index);
		if(user.getUserType()==1){
			PageAndQuestionnaire pQuestionnaire = questionnaireService.getAllQuestion(page);
			if(pQuestionnaire!=null){
				return new QuestionnaireResult<PageAndQuestionnaire>(true, pQuestionnaire, 1);			
			}else{
				return new QuestionnaireResult<>(false, "未查询到任何数据");
			}			
		}else{
			PageAndQuestionnaire pQuestionnaire = questionnaireService.getQuestionByUsername(user.getUsername(),page);
			if(pQuestionnaire!=null){
				return new QuestionnaireResult<PageAndQuestionnaire>(true, pQuestionnaire, 1);			
			}else{
				return new QuestionnaireResult<>(false, "未查询到任何数据");
			}
		}
	}
	
}
