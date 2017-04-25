package com.sicnu.baby.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sicnu.baby.bean.AnswerView;
import com.sicnu.baby.bean.QuestionnaireAnswer;

public interface QuestionnaireAnswerDao {
	/**
	 * 向数据库添加一个被调查者的答案
	 * @param qAnswer
	 * @return 返回1则成功,返回其它失败
	 */
	public int insert(@Param("qAnswer")QuestionnaireAnswer qAnswer);
	/**
	 * 根据sessionId获取问卷含填写过的答案
	 * @param sessionId
	 * @return
	 */
	public List<AnswerView> getAnswerViewBySessionId(@Param("sessionId")String sessionId);
	/**
	 * 根据答案Id获取答案信息,含问卷
	 * @param id
	 * @return
	 */
	public QuestionnaireAnswer getQuestionnaireAnswerById(@Param("id")int ansid);
	/**
	 * 获取ques_id=quesId的问卷所有的答案信息
	 * @param quesId
	 * @return 
	 */
	public List<String> getQAnswerByQuesId(@Param("qesId")int qesId);
	
}
