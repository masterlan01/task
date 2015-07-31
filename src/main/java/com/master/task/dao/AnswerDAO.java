package com.master.task.dao;

import java.util.List;

import com.master.task.entity.Answer;

public interface AnswerDAO {

	public void addAnswer(Answer answer);

	public List<Answer> findAllAnswersByQuestionId(int questionId);

	public Answer findById(int id);
}