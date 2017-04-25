package com.sicnu.baby.bean;

import java.util.Date;

public class Questionnaire {
	
	private Integer qesId;//主键
	
	private String qesInformation;//问卷主要内容
	
	private String username;//问卷的创造者
	
	private String qesProfix;//问卷的说明
	
	private String qesTitle;//问卷的名字
	
	private String qesResult;//问卷调查的分析结果
	
	private short isPublic;//标记问卷是否公开
	
	private Date createTime;//创建时间
	
	private Date startTime;//问卷开始时间
	
	private Date endTime;//问卷结束时间

	public Questionnaire() {
	}

	public Questionnaire(Integer qesId, String qesInformation, String username, String qesProfix, String qesTitle,
			String qesResult, short isPublic, Date createTime, Date startTime, Date endTime) {
		this.qesId = qesId;
		this.qesInformation = qesInformation;
		this.username = username;
		this.qesProfix = qesProfix;
		this.qesTitle = qesTitle;
		this.qesResult = qesResult;
		this.isPublic = isPublic;
		this.createTime = createTime;
		this.startTime = startTime;
		this.endTime = endTime;
	}



	public Integer getQesId() {
		return qesId;
	}



	public void setQesId(Integer qesId) {
		this.qesId = qesId;
	}



	public String getQesInformation() {
		return qesInformation;
	}



	public void setQesInformation(String qesInformation) {
		this.qesInformation = qesInformation;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getQesProfix() {
		return qesProfix;
	}



	public void setQesProfix(String qesProfix) {
		this.qesProfix = qesProfix;
	}



	public String getQesTitle() {
		return qesTitle;
	}



	public void setQesTitle(String qesTitle) {
		this.qesTitle = qesTitle;
	}



	public String getQesResult() {
		return qesResult;
	}



	public void setQesResult(String qesResult) {
		this.qesResult = qesResult;
	}



	public short getIsPublic() {
		return isPublic;
	}



	public void setIsPublic(short isPublic) {
		this.isPublic = isPublic;
	}



	public Date getCreateTime() {
		return createTime;
	}



	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}



	public Date getStartTime() {
		return startTime;
	}



	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}



	public Date getEndTime() {
		return endTime;
	}



	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}



	@Override
	public String toString() {
		return "Questionnaire [qesId=" + qesId + ", qesInformation=" + qesInformation + ", username=" + username
				+ ", qesProfix=" + qesProfix + ", qesTitle=" + qesTitle + ", qesResult=" + qesResult + ", isPublic="
				+ isPublic + ", createTime=" + createTime + ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}	
	
}
