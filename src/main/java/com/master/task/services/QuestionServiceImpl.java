package com.master.task.services;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.master.task.HomeController;
import com.master.task.dao.QuestionDAO;
import com.master.task.entity.Question;

@Service("questionService")
@Transactional
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionDAO dao;
	private HashSet<Integer> questionList;
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Override
	public Question getSomeQuestion() {
		if (questionList == null)
			questionList = new HashSet<Integer>();
		Random rnd = new Random(System.currentTimeMillis());
		while (true) {
			int someId = rnd.nextInt(dao.findAllQuestions().size());
			if (!questionList.contains(someId) & someId > 0) {
				questionList.add(someId);
				logger.info("Для отправки случайным образом выбран вопрос № {}", someId);
				return dao.findById(someId);
			}
		}
	}

	public void saveQuestion(Question Question) {
		dao.addQuestion(Question);
	}

	public void fillQuestions() {
		if (dao.findById(1) == null) {
			saveQuestion(new Question(1, "В каком городе мы живём?"));
			saveQuestion(new Question(2, "Какой улицы нет в нашем городе?"));
			saveQuestion(new Question(3, "Как называется река в нашем городе?"));
			saveQuestion(new Question(4, "В какой стране находится наш город?"));
			saveQuestion(new Question(5, "В какой части света расположена наша страна?"));
			saveQuestion(new Question(6, "Как называется наша планет?"));
			saveQuestion(new Question(7, "Как называется столица нашего государства?"));
		}
	}

	public List<Question> findAllQuestions() {
		return dao.findAllQuestions();
	}

	@Override
	public Question findById(int id) {
		return dao.findById(id);
	}

}
