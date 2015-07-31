package com.master.task.services;

import java.util.List;

import com.master.task.entity.Answer;

public interface AnswerService {

	void saveAnswer(Answer Answer);

	Answer findById(int id);

	void fillAnswers();

	List<Answer> findAllAnswersByQuestionId(int questionId);

	List<Answer> getSomeAnswersByQuestionId(int questionID);

}
