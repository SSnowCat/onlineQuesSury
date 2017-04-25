package com.sicnu.baby.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.sicnu.baby.bean.Questionnaire;
import com.sicnu.baby.dao.QuestionnaireDao;
import com.sicnu.baby.dto.Analysis;
import com.sicnu.baby.dto.ConAnalysis;
import com.sicnu.baby.exception.QuesSurveyDataException;
import com.sicnu.baby.service.AnswerAnalysisService;
import com.sicnu.baby.service.ChartService;

@Service
public class ChartServiceImpl implements ChartService{
	
	private AnswerAnalysisService analysisService;
	
	private QuestionnaireDao questionnaireDao;
	
	@Resource
	public void setAnalysisService(AnswerAnalysisService analysisService) {
		this.analysisService = analysisService;
	}
	
	@Resource
	public void setQuestionnaireDao(QuestionnaireDao questionnaireDao) {
		this.questionnaireDao = questionnaireDao;
	}
		
	@Override
	public Analysis getAnalysis(int quesId) throws QuesSurveyDataException{
		Questionnaire questionnaire = questionnaireDao.getQuestionnaireById(quesId);
		if(questionnaire.getQesResult()!=null&&questionnaire.getQesResult().trim()!=""){
			Map<String, Integer> comAnalysis = new HashMap<>();
			List<ConAnalysis> conAnalysisList = new ArrayList<>();
			try {
				JSONObject json = new JSONObject(questionnaire.getQesResult());
				JSONObject map =  json.optJSONObject("comAnalysis");
				JSONArray list = json.optJSONArray("conAnalysis");
				@SuppressWarnings("unchecked")
				Iterator<String> iterator = map.keys();
				while(iterator.hasNext()){
					String key = iterator.next();
					int value = map.getInt(key);
					comAnalysis.put(key, value);
				}
				for(int i = 0;i<list.length();i++){
					JSONObject jObject = list.getJSONObject(i);
			        ConAnalysis conAnalysis = new ConAnalysis(jObject.getInt("id"), jObject.getString("option"), jObject.getInt("number"));
			        conAnalysisList.add(conAnalysis);
				}
			} catch (JSONException e) {
				throw new QuesSurveyDataException("数据异常");
			}
			
			
			Analysis analysis = new Analysis();
			analysis.setComAnalysis(comAnalysis);
			analysis.setConAnalysis(conAnalysisList);
			return analysis;
		}else {			
			Map<String, Integer> comAnalysis = analysisService.getResult(quesId);
			Analysis analysis = new Analysis();
			analysis.setComAnalysis(comAnalysis);
			
			List<ConAnalysis> conAnalysisList = new ArrayList<>();
			List<Map<String, Integer>> maps = analysisService.getStatisticalResults(quesId);
			for(int i=0;i<maps.size();i++){
				if(maps.get(i)!=null){
					for(Map.Entry<String, Integer> entry: maps.get(i).entrySet()){
						ConAnalysis conAnalysis = new ConAnalysis(i+1, entry.getKey(),entry.getValue());
						conAnalysisList.add(conAnalysis);
					}
				}
			}
			analysis.setConAnalysis(conAnalysisList);
			if((new Date()).getTime()>=questionnaire.getEndTime().getTime()){
				if(questionnaireDao.updateResult(quesId,analysis.toString())==1){
					return analysis;					
				}else{
					return null;
				}
			}else{
				return analysis;
			}
		}
	}

}
