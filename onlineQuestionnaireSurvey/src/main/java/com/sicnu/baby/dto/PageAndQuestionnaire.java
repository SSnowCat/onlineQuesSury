package com.sicnu.baby.dto;

import java.util.List;

import com.sicnu.baby.bean.Questionnaire;
import com.sicnu.baby.entity.Page;
/**
 * 用于分页时与前台数据交流
 * @author bulala
 *
 */
public class PageAndQuestionnaire {
	private Page page;
	
	private List<Questionnaire> questionnaire;
	
	public PageAndQuestionnaire() {
		super();
	}
	
	public PageAndQuestionnaire(Page page, List<Questionnaire> questionnaire) {
		super();
		this.page = page;
		this.questionnaire = questionnaire;
	}



	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List<Questionnaire> getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(List<Questionnaire> questionnaire) {
		this.questionnaire = questionnaire;
	}	
	
}
