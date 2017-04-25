package com.sicnu.baby.service;

import java.util.List;
import java.util.Map;

import com.sicnu.baby.exception.QuesSurveyDataException;

public interface AnswerAnalysisService {
	
	/**
	 * 解析答案并统计
	 * Map<String, Integer>中第一个为选项，第二个为选项个数
	 * @param quesId
	 * @return Map<String, Integer>
	 */
	public Map<String, Integer> getResult(int quesId)throws QuesSurveyDataException;
	
	/**
	 *  第一题选A的,第一题选B的人数,第一题选C的人数,第一题选D的人数
	 *  第二题.......
	 *  @param quesId
	 *  @return List<Map<String, Integer>>
	 */
	public  List<Map<String, Integer>> getStatisticalResults(int quesId) throws QuesSurveyDataException;
	
}
