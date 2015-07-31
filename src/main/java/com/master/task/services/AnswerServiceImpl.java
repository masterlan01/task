package com.master.task.services;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.master.task.dao.AnswerDAO;
import com.master.task.entity.Answer;

@Service("AnswerService")
@Transactional
public class AnswerServiceImpl implements AnswerService {

	@Autowired
	private AnswerDAO dao;

	public void saveAnswer(Answer Answer) {
		dao.addAnswer(Answer);
	}

	public void fillAnswers() {

		if (dao.findById(1) == null) {

			// В каком городе мы живём?

			saveAnswer(new Answer(1, "Чикаго", false));
			saveAnswer(new Answer(1, "Токио", false));
			saveAnswer(new Answer(1, "Париж", false));
			saveAnswer(new Answer(1, "Варшава", false));
			saveAnswer(new Answer(1, "Хацапетовка", false));
			saveAnswer(new Answer(1, "Нью-Васюки", false));
			saveAnswer(new Answer(1, "Глазго", false));
			saveAnswer(new Answer(1, "Осло", false));
			saveAnswer(new Answer(1, "Турин", false));
			saveAnswer(new Answer(1, "Шанхай", false));
			saveAnswer(new Answer(1, "Черкассы", true));

			// Какой улицы нет в нашем городе?

			saveAnswer(new Answer(2, "Богдана Хмельницкого", false));
			saveAnswer(new Answer(2, "Шевченко", false));
			saveAnswer(new Answer(2, "Крещатик", false));
			saveAnswer(new Answer(2, "Пастеровская", false));
			saveAnswer(new Answer(2, "Елисейские поля", true));
			saveAnswer(new Answer(2, "Гоголя", false));
			saveAnswer(new Answer(2, "Байды Вишневецкого", false));
			saveAnswer(new Answer(2, "Ленина", false));
			saveAnswer(new Answer(2, "Проспект химиков", false));
			saveAnswer(new Answer(2, "Ильина", false));
			saveAnswer(new Answer(2, "Смелянская", false));

			// Как называется река в нашем городе?

			saveAnswer(new Answer(3, "Олшанка", false));
			saveAnswer(new Answer(3, "Рось", false));
			saveAnswer(new Answer(3, "Днепр", true));
			saveAnswer(new Answer(3, "Днестр", false));
			saveAnswer(new Answer(3, "Дунай", false));
			saveAnswer(new Answer(3, "Рейн", false));
			saveAnswer(new Answer(3, "Сена", false));
			saveAnswer(new Answer(3, "Темза", false));
			saveAnswer(new Answer(3, "Одер", false));
			saveAnswer(new Answer(3, "Шпрея", false));

			// В какой стране находится наш город?

			saveAnswer(new Answer(4, "Китай", false));
			saveAnswer(new Answer(4, "Гондурас", false));
			saveAnswer(new Answer(4, "Япония", false));
			saveAnswer(new Answer(4, "Украина", true));
			saveAnswer(new Answer(4, "Бразилия", false));
			saveAnswer(new Answer(4, "Австрия", false));
			saveAnswer(new Answer(4, "Франция", false));
			saveAnswer(new Answer(4, "Испания", false));
			saveAnswer(new Answer(4, "Италия", false));
			saveAnswer(new Answer(4, "Люксембург", false));

			// В какой части света расположена наша страна?

			saveAnswer(new Answer(5, "Азия", false));
			saveAnswer(new Answer(5, "Африка", false));
			saveAnswer(new Answer(5, "Южная Амкрика", false));
			saveAnswer(new Answer(5, "Европа", true));
			saveAnswer(new Answer(5, "Северная Америка", false));
			saveAnswer(new Answer(5, "Антактида", false));
			saveAnswer(new Answer(5, "Австралия", false));

			// Как называется наша планет?

			saveAnswer(new Answer(6, "Юпитер", false));
			saveAnswer(new Answer(6, "Венера", false));
			saveAnswer(new Answer(6, "Меркурий", false));
			saveAnswer(new Answer(6, "Земля", true));
			saveAnswer(new Answer(6, "Марс", false));
			saveAnswer(new Answer(6, "Плутон", false));
			saveAnswer(new Answer(6, "Нептун", false));
			saveAnswer(new Answer(6, "Уран", false));
			saveAnswer(new Answer(6, "Сатурн", false));

			// Как называется столица нашего государства?

			saveAnswer(new Answer(7, "Берлин", false));
			saveAnswer(new Answer(7, "Токио", false));
			saveAnswer(new Answer(7, "Париж", false));
			saveAnswer(new Answer(7, "Варшава", false));
			saveAnswer(new Answer(7, "Рим", false));
			saveAnswer(new Answer(7, "Вашингтон", false));
			saveAnswer(new Answer(7, "Вена", false));
			saveAnswer(new Answer(7, "Лондон", false));
			saveAnswer(new Answer(7, "Пекин", false));
			saveAnswer(new Answer(7, "Рига", false));
			saveAnswer(new Answer(7, "Киев", true));
		}
	}

	@Override
	public List<Answer> findAllAnswersByQuestionId(int questionId) {
		return dao.findAllAnswersByQuestionId(questionId);
	}

	@Override
	public Answer findById(int id) {
		return ((AnswerServiceImpl) dao).findById(id);
	}

	@Override
	public List<Answer> getSomeAnswersByQuestionId(int questionID) {
		List<Answer> list = findAllAnswersByQuestionId(questionID);
		Collections.shuffle(list);
		// Инициализируем генератор
		Random rnd = new Random(System.currentTimeMillis());
		int min = (list.size() > 3) ? 3 : list.size(), max = (list.size() < 6) ? list.size() : 6;
		// Получаем случайное число в диапазоне от min до max (включительно)
		int retain = min + rnd.nextInt(max - min + 1);
		while (list.size() > retain) {
			int rm = rnd.nextInt(list.size());
			if (!list.get(rm).isVerity())
				list.remove(rm);
		}

		return list;
	};
}
