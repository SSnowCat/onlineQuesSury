package com.sicnu.baby.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.sicnu.baby.bean.AnswerView;
import com.sicnu.baby.bean.QuestionnaireAnswer;
import com.sicnu.baby.dao.QuestionnaireAnswerDao;
import com.sicnu.baby.dto.QuestionnaireAndAnswer;
import com.sicnu.baby.entity.Answer;
import com.sicnu.baby.exception.QuesSurveyDataException;
import com.sicnu.baby.exception.QuesSurveyException;
import com.sicnu.baby.service.QuestionnaireAnswerService;

/**
 * QuestionnaireAnswerService方法的具体实现
 * @author bulala
 *
 */
@Service
public class QuestionnaireAnswerServiceImpl implements QuestionnaireAnswerService{

	private QuestionnaireAnswerDao qAnswerDao;
	@Resource
	public void setqAnswerDao(QuestionnaireAnswerDao qAnswerDao) {
		this.qAnswerDao = qAnswerDao;
	}
	
	@Override
	public boolean addAnswer(JSONObject answer,String sessionId) throws QuesSurveyException,QuesSurveyDataException {
	    try {
	    	int qesId = answer.getInt("qesId");
			String answerStr = answer.getString("answer");
			QuestionnaireAnswer qAnswer = new QuestionnaireAnswer(null, qesId, answerStr, null, sessionId, null);
			if(qAnswerDao.insert(qAnswer)==1){
				return true;
			}else{
				return false;
			}
		} catch (JSONException e) {
			throw new QuesSurveyDataException("数据解析错误", e);
		} 
	}

	@Override
	public QuestionnaireAndAnswer getQuestionnaireAnswerById(int ansId) throws QuesSurveyDataException,QuesSurveyException {
		QuestionnaireAnswer qAnswer = qAnswerDao.getQuestionnaireAnswerById(ansId);
		if(qAnswer!=null){
			List<Answer> answers = getAnswers(qAnswer.getQesRSet());
			QuestionnaireAndAnswer qAndAnswer = new QuestionnaireAndAnswer(qAnswer.getId(), answers, qAnswer.getQuestionnaire());
			return qAndAnswer; 
		}else{
		     throw new QuesSurveyException("数据为空");	
		}
	}

	@Override
	public List<AnswerView> getAnswerViewsBySessionId(String sessionId) throws QuesSurveyDataException {
		
		return qAnswerDao.getAnswerViewBySessionId(sessionId);
	}
	
	private List<Answer> getAnswers(String answerString) throws QuesSurveyDataException{
		List<Answer> answers = new ArrayList<>();
		try{
			String[] temp = answerString.split(";");//将一份答案分解得到如"1=A"形式
			for(int j=0;j<temp.length;j++){//temp.length表示题数
				String[] ansTemp = temp[j].split("=");
				Answer answer2 = new Answer(Integer.parseInt(ansTemp[0]), ansTemp[1]);
				answers.add(answer2);
			}
		}catch (Exception e) {
			//数据类型错误
			throw new QuesSurveyDataException("ERROR:数据格式错误", e);
		}
		return answers;			
	}

}
