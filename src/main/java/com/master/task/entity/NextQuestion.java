package com.master.task.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.master.task.entity.Answer;
import com.master.task.entity.Question;
import com.master.task.services.AnswerService;
import com.master.task.services.QuestionService;

@Component
public class NextQuestion {

	@Autowired
	QuestionService questionService;

	@Autowired
	AnswerService answerService;

	private Question question;
	private List<Answer> answers;
	private Integer numb;

	public int getNumb() {
		return numb;
	}

	public void setNumb(int numb) {
		this.numb = numb;
	}

	public NextQuestion getNextQuestion(int questionID, Integer numb) {
		setNumb(numb);
		if (questionID == 0) {
			setQuestion(null);
			setAnswers(null);
		} else {
			setQuestion(questionService.findById(questionID));
			setAnswers(answerService.getSomeAnswersByQuestionId(getQuestion().getId()));
		}
		return this;
	}

	public NextQuestion() {
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public boolean isVerityAnswer(int responseAnswerID) {
		for (Answer answer : answers) {
			if (answer.isVerity() && answer.getId() == responseAnswerID)
				return true;
		}
		return false;

	}

}