package com.sicnu.baby.web;


import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sicnu.baby.bean.User;
import com.sicnu.baby.dao.QuestionnaireDao;
import com.sicnu.baby.dto.Analysis;
import com.sicnu.baby.dto.QuestionnaireResult;
import com.sicnu.baby.exception.QuesSurveyDataException;
import com.sicnu.baby.service.ChartService;

/**
 * 
 * @author bulala
 *
 */
@Controller
public class ChartController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private ChartService chartService;

	private QuestionnaireDao qDao;

	@Resource
	public void setChartService(ChartService chartService) {
		this.chartService = chartService;
	}
	@Resource
	public void setqDao(QuestionnaireDao qDao) {
		this.qDao = qDao;
	}

	@RequestMapping(value="/{quesId}/chart",
			method=RequestMethod.GET ,
			produces={"application/json;charset=UTF-8"})
	public @ResponseBody QuestionnaireResult<Analysis> getAnalysis(@PathVariable("quesId") int quesId,HttpSession session){
		User user = (User)session.getAttribute("user");
		QuestionnaireResult<Analysis> qResult;
		if(user!=null){
			try {
				Analysis analysis = chartService.getAnalysis(quesId);
				qResult = new QuestionnaireResult<Analysis>(true, analysis, 1);
				return qResult;
			}catch (QuesSurveyDataException e) {
				logger.error(e.getMessage(), e);
				qResult = new QuestionnaireResult<>(false, e.getMessage());
				return qResult;
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				qResult = new QuestionnaireResult<>(false, e.getMessage());
				return qResult;
			}			
		}else{
			if(qDao.isPublic(quesId)==0){
				return new QuestionnaireResult<>(false, "该问卷结果不对外公开");
			}
			try {
				Analysis analysis = chartService.getAnalysis(quesId);
				qResult = new QuestionnaireResult<Analysis>(true, analysis, 1);
				return qResult;
			}catch (QuesSurveyDataException e) {
				logger.error(e.getMessage(), e);
				qResult = new QuestionnaireResult<>(false, e.getMessage());
				return qResult;
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				qResult = new QuestionnaireResult<>(false, e.getMessage());
				return qResult;
			}
		}
	}
}
