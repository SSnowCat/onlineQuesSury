package com.sicnu.baby.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sicnu.baby.bean.Questionnaire;
import com.sicnu.baby.dao.QuestionnaireDao;
import com.sicnu.baby.dto.PageAndQuestionnaire;
import com.sicnu.baby.entity.Page;
import com.sicnu.baby.service.RespondentsService;
@Service
public class RespondentsServiceImpl implements RespondentsService{

	private QuestionnaireDao qDao;
	
	@Resource
	public void setqDao(QuestionnaireDao qDao) {
		this.qDao = qDao;
	}


	@Override
	public PageAndQuestionnaire getAllQuestionnaire(Page page){
		List<Questionnaire> qList = qDao.getAllQuestionByPage(null, page, "resp");
		if(qList.size()==0){
			return null;
		}
		PageAndQuestionnaire pageAndQuestionnaire = new PageAndQuestionnaire(page, qList);
		return pageAndQuestionnaire;
	}

}
