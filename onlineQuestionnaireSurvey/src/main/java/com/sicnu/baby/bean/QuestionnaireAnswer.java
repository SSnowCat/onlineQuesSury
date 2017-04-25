package com.sicnu.baby.bean;


public class QuestionnaireAnswer {
	
	private Integer id;//答案的ID,主键
	
	private Integer qesId;//问卷ID
	
	private String qesRSet;//相应问卷的答案
	
	private String qesRSetAny;//问卷分析结果
	
	private String sessionId;//临时唯一标识
	
	private Questionnaire questionnaire;

	public QuestionnaireAnswer() {
	}

	public QuestionnaireAnswer(Integer id, Integer qesId, String qesRSet, String qesRSetAny, String sessionId,
			Questionnaire questionnaire) {
		this.id = id;
		this.qesId = qesId;
		this.qesRSet = qesRSet;
		this.qesRSetAny = qesRSetAny;
		this.sessionId = sessionId;
		this.questionnaire = questionnaire;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQesId() {
		return qesId;
	}

	public void setQesId(Integer qesId) {
		this.qesId = qesId;
	}

	public String getQesRSet() {
		return qesRSet;
	}

	public void setQesRSet(String qesRSet) {
		this.qesRSet = qesRSet;
	}

	public String getQesRSetAny() {
		return qesRSetAny;
	}

	public void setQesRSetAny(String qesRSetAny) {
		this.qesRSetAny = qesRSetAny;
	}
	
	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

	
	
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	@Override
	public String toString() {
		return "QuestionnaireAnswer [id=" + id + ", qesId=" + qesId + ", qesRSet=" + qesRSet + ", qesRSetAny="
				+ qesRSetAny + "]";
	}
	
	
}
