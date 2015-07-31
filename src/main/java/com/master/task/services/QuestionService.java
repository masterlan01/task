package com.master.task.services;

import java.util.List;

import com.master.task.entity.Question;

public interface QuestionService {

	Question findById(int id);

	void saveQuestion(Question question);

	List<Question> findAllQuestions();

	void fillQuestions();

	public Question getSomeQuestion();
}
