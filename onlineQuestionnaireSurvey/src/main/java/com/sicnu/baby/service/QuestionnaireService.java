package com.sicnu.baby.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.sicnu.baby.bean.Questionnaire;
import com.sicnu.baby.dto.PageAndQuestionnaire;
import com.sicnu.baby.entity.Page;
import com.sicnu.baby.exception.QuesSurveyDataException;
import com.sicnu.baby.exception.QuesSurveyException;

/**
 * 处理问卷事件的逻辑
 * @author bulala
 *
 */
@Service
public interface QuestionnaireService {
	
	/**
	 * 添加新问卷
	 * @param qJson
	 * @return boolean
	 * @throws QuesSurveyDataException
	 * @throws QuesSurveyException
	 */
	public boolean addQuestionnaire(JSONObject qJson,String username) throws QuesSurveyDataException,QuesSurveyException;
	
	/**
	 * 问卷发起者查看问卷时视图
	 * @param username
	 * @return
	 */
	public PageAndQuestionnaire getQuestionByUsername(String username,Page page);
	
	/**
	 * 管理员查看问卷时视图
	 * @return
	 */
	public PageAndQuestionnaire getAllQuestion(Page page);
	
	/**
	 * 根据Id查找问卷详情
	 * @param quesId
	 * @return Questionnaire
	 */
	public Questionnaire getQuestionnaireById(int quesId);
	
	/**
	 * 删除问卷
	 * @param quesId
	 * @return 成功返回true
	 * @throws QuesSurveyException
	 */
	public boolean deleteById(int quesId,String username);
	
	/**
	 * 当问卷调查结束时向问卷中添加分析结果
	 * @param qesId
	 * @param result
	 * @return 成功返回true
	 * @throws QuesSurveyException
	 */
	public boolean updateResult(int qesId,String result) throws QuesSurveyException;
	
	/**
	 * 获取结果
	 * @param quesId
	 * @return 有结果返回一个字符串,否则null
	 */
	public String getResultById(int quesId);
}
