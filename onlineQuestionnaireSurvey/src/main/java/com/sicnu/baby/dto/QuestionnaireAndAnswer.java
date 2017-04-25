package com.sicnu.baby.dto;

import java.util.List;

import com.sicnu.baby.bean.Questionnaire;
import com.sicnu.baby.entity.Answer;

/**
 * 问卷内容与答案
 * @author bulala
 *
 */
public class QuestionnaireAndAnswer {
	private int id;//答案的id
	private List<Answer> answers;//每一个题的答案
	private Questionnaire qesInformation;//问卷
	
	public QuestionnaireAndAnswer() {
		super();
	}

	public QuestionnaireAndAnswer(int id, List<Answer> answers, Questionnaire qesInformation) {
		super();
		this.id = id;
		this.answers = answers;
		this.qesInformation = qesInformation;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	public Questionnaire getQesInformation() {
		return qesInformation;
	}
	public void setQesInformation(Questionnaire qesInformation) {
		this.qesInformation = qesInformation;
	}
	
	
}
