package com.master.task;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.master.task.entity.NextQuestion;
import com.master.task.entity.Question;
import com.master.task.entity.UserAnswer;
import com.master.task.services.AnswerService;
import com.master.task.services.QuestionService;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	QuestionService questionService;

	@Autowired
	AnswerService answerService;

	@Autowired
	NextQuestion nextQuestion;

	private ArrayList<Integer> questionList;

	private static Integer qntQuestion = 0; // количество вопросов теста

	@Autowired
	private Environment environment;

	/**
	 * Стартовая страница системы..
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Пришёл запрос на вход. Локаль клиента {}.", locale);
		model.addAttribute("serverTime", DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale).format(new Date()));
		return "home";
	}

	/**
	 * Покажи первый вопрос
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(ModelMap model, HttpServletRequest request) {
		logger.info("Пришёл запрос на продолжение.  Пора наполнять базу вопросов и ответов.");
		// Инициализируем currentQuestion-атрибут сессии
		request.getSession().setAttribute("currentQuestion", 1);
		fillDB();
		try {
			qntQuestion = new Integer(environment.getRequiredProperty("task.question.qnt"));
		} catch (Exception e) {
			qntQuestion = 5;
		}
		qntQuestion = Math.min(qntQuestion, questionService.findAllQuestions().size());

		questionList = new ArrayList<Integer>(questionService.findAllQuestions().size());
		for (Iterator<Question> iterator = questionService.findAllQuestions().iterator(); iterator.hasNext();) {
			Integer integer = ((Question) iterator.next()).getId();
			questionList.add(integer);
		}
		Collections.shuffle(questionList);

		logger.info("Тест будет содержать {} вопросов .", qntQuestion);
		logger.info("Подготовка первого вопроса.");
		model.addAttribute("nextQuestion", nextQuestion.getNextQuestion(getSomeQuestionID(), (Integer) request.getSession().getAttribute("currentQuestion")));
		request.getSession().setAttribute("good", 0);
		request.getSession().setAttribute("bad", 0);
		request.getSession().setAttribute("skip", 0);
		logger.info("Показ первого вопроса.");
		return "list";
	}

	/**
	 * Покажи очередной вопрос
	 */
	// этот метод будет принимать Объект UserAnswer
	// и отдавать NextQuestion клиенту
	@RequestMapping(value = "list", method = RequestMethod.POST)
	@ResponseBody
	public NextQuestion nextQuestion(@RequestBody UserAnswer userAnswer, HttpServletRequest request) {
		logger.info("Получили:  answerID = {} .", userAnswer.getAnswerID());

		// Обрабатываем полученный ответ
		if ((Integer) userAnswer.getAnswerID() == 0) {
			logger.info("Ответ не получен.");
			request.getSession().setAttribute("skip", (Integer) request.getSession().getAttribute("skip") + 1);
		} else {
			if (nextQuestion.isVerityAnswer((Integer) userAnswer.getAnswerID())) {
				logger.info("Получен правильный ответ.");
				request.getSession().setAttribute("good", (Integer) request.getSession().getAttribute("good") + 1);
			} else {
				logger.info("Ответ не верен.");
				request.getSession().setAttribute("bad", (Integer) request.getSession().getAttribute("bad") + 1);
			}
		}

		if (((Integer) request.getSession().getAttribute("currentQuestion")).compareTo(qntQuestion) == 0) {
			logger.info("Тест завершён. Отправляем пустой вопрос .");
			return nextQuestion.getNextQuestion(0, 0);
		} else {
			request.getSession().setAttribute("currentQuestion", ((Integer) request.getSession().getAttribute("currentQuestion")) + 1);
			logger.info("Отправляем очередной вопрос .");
			return nextQuestion.getNextQuestion(getSomeQuestionID(), (Integer) request.getSession().getAttribute("currentQuestion"));
		}

	}

	/**
	 * Покажи результат
	 */
	@RequestMapping(value = "result", method = RequestMethod.GET)
	public String result(ModelMap model, HttpServletRequest request) {
		logger.info("Пришёл запрос на показ результатов.");
		model.addAttribute("allQuestion", request.getSession().getAttribute("currentQuestion"));
		model.addAttribute("goodAnswer", request.getSession().getAttribute("good"));
		model.addAttribute("badAnswer", request.getSession().getAttribute("bad"));
		model.addAttribute("skipAnswer", request.getSession().getAttribute("skip"));
		model.addAttribute("acceptAnswer",
				(Integer) request.getSession().getAttribute("currentQuestion") - (Integer) request.getSession().getAttribute("skip"));
		return "result";
	}

	private void fillDB() {
		logger.info("Наполняем базу вопросов и ответов.");
		answerService.fillAnswers();
		questionService.fillQuestions();
	}

	private int getSomeQuestionID() {
		int someId = questionList.get(0);
		questionList.remove(0);
		logger.info("осталось вопросов {}", questionList);
		return someId;

	}
}
