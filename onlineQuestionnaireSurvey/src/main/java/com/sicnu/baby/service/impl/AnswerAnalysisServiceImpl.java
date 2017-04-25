package com.sicnu.baby.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sicnu.baby.dao.QuestionnaireAnswerDao;
import com.sicnu.baby.exception.QuesSurveyDataException;
import com.sicnu.baby.service.AnswerAnalysisService;

@Service
public class AnswerAnalysisServiceImpl implements AnswerAnalysisService{

	private  QuestionnaireAnswerDao qAnswerdao;

	@Resource
	public void setqAnswerdao(QuestionnaireAnswerDao qAnswerdao) {
		this.qAnswerdao = qAnswerdao;
	}
	
	@Override
	public Map<String, Integer> getResult(int quesId) throws QuesSurveyDataException{
		List<String> aList = qAnswerdao.getQAnswerByQuesId(quesId);
		Map<String, Integer> countAns = new TreeMap<>();
		try{
			for(int i=0;i<aList.size();i++){//人数
				if(aList.get(i)!=null&&!aList.get(i).equals("")){					
					String[] temp = aList.get(i).split(";");//将每一份答案分解得到如"1=A"形式
					for(int j=0;j<temp.length;j++){//temp.length表示题数
						String[] ansTemp = temp[j].split("=");
						if(countAns.containsKey(ansTemp[1])){
							countAns.put(ansTemp[1], countAns.get(ansTemp[1])+1);
						}else{
							if(ansTemp[1].matches("[A-Z]")){
								countAns.put(ansTemp[1], 1);
							}
						}
					}
				}

			}
		}catch (Exception e) {
			//数据类型错误
			throw new QuesSurveyDataException("ERROR:数据格式错误", e);
		}
		return countAns;
	}

	@Override
	public List<Map<String, Integer>> getStatisticalResults(int quesId) throws QuesSurveyDataException{
		Map<String, Integer> countAns = new TreeMap<>();
		List<String> alList = qAnswerdao.getQAnswerByQuesId(quesId);
		try{
			for(int i=0;i<alList.size();i++){//人数
				if(alList.get(i)!=null&&!alList.get(i).equals("")){					
					String[] temp = alList.get(i).split(";");//将每一份答案分解得到如"1=A"形式
					for(int j=0;j<temp.length;j++){//temp.length表示题数
						String[] ansTemp = temp[j].split("=");
						if(countAns.containsKey((j+1)+"="+ansTemp[1])){
							countAns.put((j+1)+"="+ansTemp[1], countAns.get((j+1)+"="+ansTemp[1])+1);
						}else{
							if(ansTemp[1].matches("[A-Z]")){
								countAns.put((j+1)+"="+ansTemp[1], 1);
							}
						}
					}
				}

			}
		}catch (Exception e) {
			//数据类型错误
			throw new QuesSurveyDataException("ERROR:数据格式错误", e);
		}

		List<Map<String, Integer>> results = new ArrayList<>();
		try{
			for(Map.Entry<String, Integer> entry:countAns.entrySet()){
				results.add(null);
				String[] temp = entry.getKey().split("=");
				int index = Integer.parseInt(temp[0]);
				if(results.get(index-1)==null){
					Map<String, Integer> map = new HashMap<>();
					map.put(temp[1], entry.getValue());
					results.add(index-1,map);
				}else{
					if(results.get(index-1)!=null){
						results.get(index-1).put(temp[1], entry.getValue());
					}
				}
			}
		}catch (Exception e) {
			//结果分析错误
			throw new QuesSurveyDataException("ERROR:数据结果处理错误", e);
		}
		return results;
	}

}
