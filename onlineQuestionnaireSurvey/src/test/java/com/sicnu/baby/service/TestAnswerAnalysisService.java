package com.sicnu.baby.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
"classpath:spring/spring-service.xml"})
public class TestAnswerAnalysisService {
	@Resource
	private AnswerAnalysisService answerAnalysisService;

	@Test
	public void testGetResult(){
		Map<String, Integer> results = answerAnalysisService.getResult(3);
		for(Map.Entry<String, Integer> entry:results.entrySet()){
			System.out.println("选"+entry.getKey()+"的有"+entry.getValue()+"人");
		}
	}

	@Test
	public void testGetStatisticalResults(){
		List<Map<String, Integer>> maps = answerAnalysisService.getStatisticalResults(3);
		for(int i=0;i<maps.size();i++){
			if(maps.get(i)!=null){
				for(Map.Entry<String, Integer> entry:maps.get(i).entrySet()){
					System.out.println("第"+(i+1)+"题选"+entry.getKey()+"的有"+entry.getValue()+"人");
				}
			}
		}
	}
}
