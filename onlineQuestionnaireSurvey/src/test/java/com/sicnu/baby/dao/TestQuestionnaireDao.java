package com.sicnu.baby.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sicnu.baby.bean.Questionnaire;
import com.sicnu.baby.entity.Page;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
"classpath:spring/spring-service.xml"})
public class TestQuestionnaireDao{
	@Resource
	private QuestionnaireDao questionnaireDao;

	@Test
	public void tsetInsertByQuestionnaire() throws ParseException {
		String start = "2017-03-30 20:02:29";
		String end = "2017-03-30 20:02:29";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startTime = sdf.parse(start);
		Date endTime = sdf.parse(end);
		short isPublic = 0;
		Questionnaire questionnaire = new Questionnaire(null, "test", "2014110444", "test", "test", null, isPublic, null, startTime, endTime);
		System.out.println(questionnaireDao.insertByQuestionnaire(questionnaire));
	}

	@Test
	public void testGetQuestionByUsername() {
		Page page = new Page();
		page.setCurrentPage(1);
		List<Questionnaire> qList = questionnaireDao.getAllQuestionByPage("2014110445",page,null);
		if(qList.size()==0){
			System.out.println("null");
		}
		for(int i = 0;i<qList.size();i++){
			System.out.println(qList.get(i));
		}
	}

	@Test
	public void testGetAllQuestion() {
		Page page = new Page();
		page.setCurrentPage(1);
		List<Questionnaire> qList = questionnaireDao.getAllQuestionByPage(null,page,null);
		for(int i = 0;i<qList.size();i++){
			System.out.println(qList.get(i));
		}
	}
	
	@Test
	public void testGetAllQuestion2() {
		Page page = new Page();
		page.setCurrentPage(1);
		List<Questionnaire> qList = questionnaireDao.getAllQuestionByPage(null,page,"resp");
		for(int i = 0;i<qList.size();i++){
			System.out.println(qList.get(i));
		}
	}

	@Test
	public void testGetQuestionnaireById() {
		Questionnaire questionnaire = questionnaireDao.getQuestionnaireById(4);
		System.out.println(questionnaire);
	}

	@Test
	public void testDeleteById() {
		System.out.println(questionnaireDao.deleteById(6,null));
	}
	
	@Test
	public void testDeleteByUsername() {
		System.out.println(questionnaireDao.deleteById(7,"2014110445"));
	}
	@Test
	public void testUpdateResult() {
		System.out.println(questionnaireDao.updateResult(4, "test"));
	}
	
	@Test
	public void testGetResultById() {
	    System.out.println(questionnaireDao.getResultById(5));
	}
	@Test
	public void isPublic(){
		short a = questionnaireDao.isPublic(3);
		System.out.println(a);
	}
}
