package com.sicnu.baby.bean;

/**
 * 查看答案导航作用
 * @author bulala
 *
 */
public class AnswerView {
	private Integer id;//答案Id
	private Integer qesId;//问卷Id
	private String qesTitle;//问卷的标题
		
	public AnswerView() {

	}

	public AnswerView(Integer id, Integer qesId, String qesTitle) {
		this.id = id;
		this.qesId = qesId;
		this.qesTitle = qesTitle;
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

	public String getQesTitle() {
		return qesTitle;
	}

	public void setQesTitle(String qesTitle) {
		this.qesTitle = qesTitle;
	}

	@Override
	public String toString() {
		return "AnswerView [id=" + id + ", qesId=" + qesId + ", qesTitle=" + qesTitle + "]";
	}
	
	
}
