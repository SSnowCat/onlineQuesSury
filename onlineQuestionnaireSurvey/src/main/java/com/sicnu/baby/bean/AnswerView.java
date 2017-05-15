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
	private String username;//问卷创建者
		
	public AnswerView() {

	}
	public AnswerView(Integer id, Integer qesId, String qesTitle, String username) {
		super();
		this.id = id;
		this.qesId = qesId;
		this.qesTitle = qesTitle;
		this.username = username;
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

	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "AnswerView [id=" + id + ", qesId=" + qesId + ", qesTitle=" + qesTitle + ", username=" + username + "]";
	}
	
}
