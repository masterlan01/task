package com.master.task.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.master.task.entity.Answer;

@Repository
public class AnswerDAOImpl implements AnswerDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addAnswer(Answer answer) {
		sessionFactory.getCurrentSession().save(answer);
	}

	@Override
	public Answer findById(int id) {
		return (Answer) sessionFactory.getCurrentSession().get(Answer.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Answer> findAllAnswersByQuestionId(int questionId) {
		return sessionFactory.getCurrentSession().createQuery("from Answer where question_Id=" + questionId).list();
	}

}