package com.sicnu.baby.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sicnu.baby.bean.AnswerView;
import com.sicnu.baby.bean.QuestionnaireAnswer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class TestQuestionnaireAnswerDao {
	@Resource
	QuestionnaireAnswerDao questionnaireAnswerDao;
	
	@Test
	public void testInsert(){
		QuestionnaireAnswer qAnswer = new QuestionnaireAnswer();
		qAnswer.setQesId(3);
		qAnswer.setQesRSet("1=B;2=C;3=A;4=A;5=A;6=asad;7=C;8=asad");
		qAnswer.setSessionId("789");
	    questionnaireAnswerDao.insert(qAnswer);	
	}
	
	@Test
	public void testGetQuestionnaireAnswerById(){
		int id = 1;
		QuestionnaireAnswer questionnaireAnswer = questionnaireAnswerDao.getQuestionnaireAnswerById(id);
		System.out.println(questionnaireAnswer);
	}
	
	@Test
	public void testGetQAnswerByQuesId(){
		int qesId = 3;
		List<String> aList = questionnaireAnswerDao.getQAnswerByQuesId(qesId);
		for(int i=0;i<aList.size();i++){
			System.out.println(aList.get(i));
		}
	}
	
	@Test
	public void testGetAnswerViewBySessionId(){
		List<AnswerView> answers = questionnaireAnswerDao.getAnswerViewBySessionId("789");
		for(int i=0;i<answers.size();i++){
			System.out.println(answers.get(i));
		}
	}
}
