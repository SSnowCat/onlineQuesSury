package com.sicnu.baby.service;

import com.sicnu.baby.dto.Analysis;
import com.sicnu.baby.exception.QuesSurveyDataException;

public interface ChartService {
	/**
	 * 获取分析结果
	 * @param qesId
	 * @return Analysis
	 */
	public Analysis getAnalysis(int quesId) throws QuesSurveyDataException;
}
