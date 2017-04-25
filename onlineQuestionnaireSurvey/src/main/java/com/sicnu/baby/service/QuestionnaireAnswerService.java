package com.sicnu.baby.service;

import java.util.List;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.sicnu.baby.bean.AnswerView;
import com.sicnu.baby.dto.QuestionnaireAndAnswer;
import com.sicnu.baby.exception.QuesSurveyDataException;
import com.sicnu.baby.exception.QuesSurveyException;

/**
 * 用户填写答案的逻辑处理
 * @author bulala
 *
 */
@Service
public interface QuestionnaireAnswerService {
	/**
	 * 提交用户填写的答案
	 * @param answer
	 * @return true成功,false失败
 	 * @throws QuesSurveyException,QuesSurveyDataException
	 */
	public boolean addAnswer(JSONObject answer,String sessionId) throws QuesSurveyException,QuesSurveyDataException;
    
	/**
	 * 用户查看填写过的文件的答案详情
	 * @param quesId
	 * @return QuestionnaireAnswer
	 * @throws QuesSurveyDataException
	 */
	public QuestionnaireAndAnswer getQuestionnaireAnswerById(int ansId) throws QuesSurveyDataException,QuesSurveyException;
	
	/**
	 * 查看本次登陆网站填写过的问卷信息
	 * @param sessionId
	 * @return List<AnswerView>
	 * @throws QuesSurveyDataException
	 */
	public List<AnswerView> getAnswerViewsBySessionId(String sessionId) throws QuesSurveyDataException;
    
}
