package com.sicnu.baby.web;

import java.util.List;

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

import com.sicnu.baby.bean.AnswerView;
import com.sicnu.baby.dto.QuestionnaireAndAnswer;
import com.sicnu.baby.dto.QuestionnaireResult;
import com.sicnu.baby.exception.QuesSurveyDataException;
import com.sicnu.baby.exception.QuesSurveyException;
import com.sicnu.baby.service.QuestionnaireAnswerService;


/**
 * 处理与问卷答案相关的事件
 * @author bulala
 *
 */
@Controller
public class QuestionnaireAnswerController {
	private Logger logger =  LoggerFactory.getLogger(this.getClass());
	
	private QuestionnaireAnswerService qService;

	@Resource
	public void setqService(QuestionnaireAnswerService qService) {
		this.qService = qService;
	}
	
	/**
	 * 用户填写问卷的提交
	 * @param answer
	 * @return
	 */
	@RequestMapping(value="/post/user/answer",
			method=RequestMethod.POST,
			produces={"application/json;chaeset=UTF-8"},
			headers={"content-type=application/json;charset=UTF-8"})
	public @ResponseBody QuestionnaireResult<String> addAnswer(@RequestBody JSONObject answer,HttpSession session){
		try{
			if(qService.addAnswer(answer, session.getId())){
				return new QuestionnaireResult<>(true, "提交问卷成功",1);
			}else {
				return new QuestionnaireResult<>(false, "提交答案失败");
			}
		}catch (QuesSurveyDataException e) {
			logger.error(e.getMessage(),e);
			return new QuestionnaireResult<>(false, e.getMessage());
		}catch (QuesSurveyException e) {
			logger.error(e.getMessage(),e);
			return new QuestionnaireResult<>(false, e.getMessage());
		}
	}
	
	/**
	 * 获取用户本次填写过的问卷
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/get/user/answer",
			method=RequestMethod.GET,
			produces={"application/json;charset=UTF-8"})
	public @ResponseBody QuestionnaireResult<List<AnswerView>> getAnswerViewsBySessionId(HttpSession session){
		List<AnswerView> answerViews = qService.getAnswerViewsBySessionId(session.getId());
		if(answerViews!=null){
			return new QuestionnaireResult<>(true, answerViews,1);
		}else{
			logger.error("根据sessionId获取填写过的问卷视图错误");
		    return new QuestionnaireResult<>(false, "数据请求异常");
		}
	}
	
	/**
	 * 获取一张填写过的问卷的答案详情
	 * @param quesId
	 * @return
	 */
	@RequestMapping(value="/get/{ansId}/detail/user/answer",
			method=RequestMethod.GET,
			produces={"application/json;charset=UTF-8"})
	public @ResponseBody QuestionnaireResult<QuestionnaireAndAnswer> getQuestionnaireAnswerById(@PathVariable("ansId") int ansId){
		try {
			QuestionnaireAndAnswer questionnaireAndAnswer = qService.getQuestionnaireAnswerById(ansId);
		    return new QuestionnaireResult<QuestionnaireAndAnswer>(true, questionnaireAndAnswer, 1);
		} catch (QuesSurveyDataException e) {
			logger.error(e.getMessage(),e);
			return new QuestionnaireResult<>(false, e.getMessage());
		}catch (QuesSurveyException e) {
			logger.error(e.getMessage(),e);
			return new QuestionnaireResult<>(false, e.getMessage());
		}
	}
}
