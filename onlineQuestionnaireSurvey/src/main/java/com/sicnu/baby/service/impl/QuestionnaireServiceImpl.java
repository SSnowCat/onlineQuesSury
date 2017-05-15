package com.sicnu.baby.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.sicnu.baby.bean.Questionnaire;
import com.sicnu.baby.dao.QuestionnaireDao;
import com.sicnu.baby.dto.PageAndQuestionnaire;
import com.sicnu.baby.entity.Page;
import com.sicnu.baby.exception.QuesSurveyDataException;
import com.sicnu.baby.exception.QuesSurveyException;
import com.sicnu.baby.service.QuestionnaireService;

/**
 * QuestionnaireService接口的实现
 * @author bulala
 *
 */
@Service
public class QuestionnaireServiceImpl implements QuestionnaireService{

	private QuestionnaireDao qDao;
	@Resource
	public void setqDao(QuestionnaireDao qDao) {
		this.qDao = qDao;
	}
	@Override
	public boolean addQuestionnaire(JSONObject qJson,String username)
			throws QuesSurveyDataException, QuesSurveyException {
		try {
			String qesTitle = qJson.getString("qesTitle");
			short isPublic = (short)qJson.getInt("isPublic");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
			Date startTime = sdf.parse(qJson.getString("startTime"));
			Date endTime = sdf.parse(qJson.getString("endTime"));
			String qesInformation = qJson.getString("qesInformation");
			String qesProfix = qJson.getString("qesProfix");
			Questionnaire questionnaire = new Questionnaire(null, qesInformation, username, qesProfix, qesTitle, null, isPublic, null, startTime, endTime);
			if(qDao.insertByQuestionnaire(questionnaire)==1){
				return true;
			}else {
				return false;
			}
		} catch (JSONException e) {
			throw new QuesSurveyDataException("json数据转换错误", e);
		} catch (ParseException e) {
			throw new QuesSurveyDataException("时间格式不符合yyyy-MM-dd HH:mm:ss", e);
		}
	}
	@Override
	public PageAndQuestionnaire getQuestionByUsername(String username,Page page) {
		PageAndQuestionnaire pQuestionnaire = new PageAndQuestionnaire();
		List<Questionnaire> questionnaire = qDao.getAllQuestionByPage(username,page,null);
		if(questionnaire!=null){
			pQuestionnaire.setPage(page);
			pQuestionnaire.setQuestionnaire(questionnaire);
			return pQuestionnaire;
		}else{
		    return null;	
		}
	}
	@Override
	public PageAndQuestionnaire getAllQuestion(Page page) {
		PageAndQuestionnaire pQuestionnaire = new PageAndQuestionnaire();
		List<Questionnaire> questionnaire = qDao.getAllQuestionByPage(null,page,null);
		if(questionnaire!=null){
			pQuestionnaire.setPage(page);
			pQuestionnaire.setQuestionnaire(questionnaire);
			return pQuestionnaire;
		}else{
		    return null;	
		}
	}
	@Override
	public Questionnaire getQuestionnaireById(int quesId) {
		
		return qDao.getQuestionnaireById(quesId);
	}
	@Override
	public boolean deleteById(int quesId,String username){
		if(qDao.deleteById(quesId,username)==1){
			return true;
		}
		return false;
	}
	@Override
	public boolean updateResult(int qesId,String result) throws QuesSurveyException {
		if(qDao.updateResult(qesId,result)==1){
			return true;
		}
		return false;
	}
	@Override
	public String getResultById(int quesId) {
		return qDao.getResultById(quesId);
	}
}
