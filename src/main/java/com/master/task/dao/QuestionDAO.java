package com.master.task.dao;

import java.util.List;

import com.master.task.entity.Question;

public interface QuestionDAO {

	public void addQuestion(Question question);

	public List<Question> findAllQuestions();

	public Question findById(int id);
}