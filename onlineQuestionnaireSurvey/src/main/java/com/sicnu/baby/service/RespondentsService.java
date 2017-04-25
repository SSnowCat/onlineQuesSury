package com.sicnu.baby.service;

import com.sicnu.baby.dto.PageAndQuestionnaire;
import com.sicnu.baby.entity.Page;
import com.sicnu.baby.exception.QuesSurveyException;

public interface RespondentsService {
	/**
	 * 用于被调查者查看可填写的调查表
	 * @return
	 * @throws QuesSurveyException
	 */
	public PageAndQuestionnaire getAllQuestionnaire(Page page);
	
}
