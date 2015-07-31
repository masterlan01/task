<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
	<title>Продолжение</title>
</head>
<body>
<h1>
	Породолжаем ...  
</h1>

<P>  Цель: получить простейшую систему тестирования знаний.

Это должна быть база данных вопросов-ответов и веб-отображение, в котором пользователь последовательно видит вопросы с вариантами ответов, может отвечать на них (например, пять случайным образом выбранных из БД; количество вариантов ответов на вопрос не фиксировано; правильный ответ один) и в конце получает за это оценку.

Базу данных предлагаем использовать MySQL + Hibernate как ORM.

Не обязательно, но желательно сделать лимит времени ответа на вопрос (таймер на 30 секунд, по окончанию которого будет автоматический переход к следующему вопросу).

Страницы должны работать через AJAX, то есть не перезагружаться (рекомендуем использовать jQuery).

Как минимум, должно быть использовано следующее: Java (Spring, Hibernate, JSP), HTML/JavaScript, AJAX (jQuery).

В качестве среды разработки рекомендуем Eclipse или IntelliJIdea  - Tomcat 

 </P>
</body>
</html>