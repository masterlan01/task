package com.master.task.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.master.task.entity.Question;

@Repository("questionDAO")
public class QuestionDAOImpl implements QuestionDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addQuestion(Question question) {
		sessionFactory.getCurrentSession().save(question);
	}

	@SuppressWarnings("unchecked")
	public List<Question> listQuestion() {
		return sessionFactory.getCurrentSession().createQuery("from Question").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Question> findAllQuestions() {
		return sessionFactory.getCurrentSession().createQuery("from Question").list();
	}

	@Override
	public Question findById(int id) {
		return (Question) sessionFactory.getCurrentSession().get(Question.class, id);
	}

}