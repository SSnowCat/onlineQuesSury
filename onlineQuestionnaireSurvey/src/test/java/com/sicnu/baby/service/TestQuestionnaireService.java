package com.sicnu.baby.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sicnu.baby.bean.Questionnaire;
import com.sicnu.baby.dto.PageAndQuestionnaire;
import com.sicnu.baby.entity.Page;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
"classpath:spring/spring-service.xml"})
public class TestQuestionnaireService {
	@Resource
	private QuestionnaireService qService;
	@Test
	public void testGetAllQuestion(){
		Page page = new Page();
		//先设置每页数量再设置当前页
		page.setPageNumber(1);
		page.setCurrentPage(1);
		System.out.println(page.getCurrentPage());
		
		PageAndQuestionnaire pQuestionnaire = qService.getAllQuestion(page);
		System.out.println("总页数："+pQuestionnaire.getPage().getTotalPage());
		System.out.println("当前页："+pQuestionnaire.getPage().getCurrentPage());
		System.out.println("limit第1个参数："+pQuestionnaire.getPage().getDbIndex());
		System.out.println("limit第2个参数："+pQuestionnaire.getPage().getDbNumber());
		List<Questionnaire> qList = pQuestionnaire.getQuestionnaire();
		for(int i=0;i<qList.size();i++){
			System.out.println(qList.get(i));
		}
	}
}
