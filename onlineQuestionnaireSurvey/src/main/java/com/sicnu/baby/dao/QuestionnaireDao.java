package com.sicnu.baby.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sicnu.baby.bean.Questionnaire;
import com.sicnu.baby.entity.Page;

public interface QuestionnaireDao {
	/**
	 * 添加新的问卷
	 * @param questionnaire
	 * @return 返回1成功
	 */
	public int insertByQuestionnaire(@Param("questionnaire")Questionnaire questionnaire);
	
	/**
	 * 查找所有的问卷
	 * @return
	 */
	public List<Questionnaire> getAllQuestionByPage(@Param("username")String username,@Param("page")Page page,@Param("resp") String resp);
	/**
	 * 根据Id查找问卷详情
	 * @param quesId
	 * @return
	 */
	public Questionnaire getQuestionnaireById(@Param("quesId")int quesId);
	
	/**
	 * 查询该问卷的结果是否公开
	 * @param quesId
	 * @return
	 */
	public short isPublic(@Param("quesId")int quesId);
	/**
	 * 删除问卷
	 * @param quesId
	 * @return 成功返回1
	 */
	public int deleteById(@Param("quesId")int quesId,@Param("username")String username);
	
	/**
	 * 当问卷调查结束时向问卷中添加分析结果
	 * @param result
	 * @return
	 */
	public int updateResult(@Param("qesId")int qesId,@Param("result")String result);
	
	/**
	 * 获取结果
	 * @param quesId
	 * @return 有结果返回一个字符串,否则null
	 */
	public String getResultById(@Param("quesId")int quesId);
}
