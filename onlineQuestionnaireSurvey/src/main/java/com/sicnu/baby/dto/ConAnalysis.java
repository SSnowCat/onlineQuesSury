package com.sicnu.baby.dto;

public class ConAnalysis {
	
	private int id;//题的id
	
	private String option;//选择的选项
	
	private int number;//选择此选项的人数
	
	public ConAnalysis() {
	}

	public ConAnalysis(int id, String option, int number) {
		this.id = id;
		this.option = option;
		this.number = number;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
}
