package com.sicnu.baby.dto;

import java.util.List;
import java.util.Map;



public class Analysis {
	
	private Map<String, Integer> comAnalysis;//综合分析结果
	
	private List<ConAnalysis> conAnalysis;//详细分析结果

	public Map<String, Integer> getComAnalysis() {
		return comAnalysis;
	}

	public void setComAnalysis(Map<String, Integer> comAnalysis) {
		this.comAnalysis = comAnalysis;
	}

	public List<ConAnalysis> getConAnalysis() {
		return conAnalysis;
	}

	public void setConAnalysis(List<ConAnalysis> conAnalysis) {
		this.conAnalysis = conAnalysis;
	}

	@Override
	public String toString() {
		StringBuilder comAnaString = new StringBuilder();
		comAnaString.append("{");
		int index=0;
		for(Map.Entry<String, Integer> entry:comAnalysis.entrySet()){
			if(index==0){				
				comAnaString.append("\"");
				index += 1;
			}else{
				comAnaString.append(",\"");
			}
		    comAnaString.append(entry.getKey());
		    comAnaString.append("\":");
		    comAnaString.append(entry.getValue());
		}
		comAnaString.append("}");
		
		StringBuilder conAnaString = new StringBuilder();
		conAnaString.append("[");
		for(int i=0;i<conAnalysis.size();i++){
			conAnaString.append("{");
			conAnaString.append("\"id");
			conAnaString.append("\":");
			conAnaString.append(conAnalysis.get(i).getId());
			conAnaString.append(",");
			
			conAnaString.append("\"option");
			conAnaString.append("\":");
			conAnaString.append("\"");
			conAnaString.append(conAnalysis.get(i).getOption());
			conAnaString.append("\"");
			conAnaString.append(",");
			
			conAnaString.append("\"number");
			conAnaString.append("\":");
			conAnaString.append(conAnalysis.get(i).getNumber());
			
			if(i==conAnalysis.size()-1){
			   conAnaString.append("}");
			}else {
				conAnaString.append("},");
			}
		}
		conAnaString.append("]");
		return "{"+"\""+"comAnalysis"+"\":"+comAnaString.toString()+","+"\"conAnalysis\":"+conAnaString.toString()+"}";
	}
	
	
}
