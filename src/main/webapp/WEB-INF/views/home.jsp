<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
	<title>Простейшая система тестирования</title>
</head>
<body>
<h1>
	Простейшая система тестирования знаний.
</h1>
<p> 
Вам будет предложена серия вопросов с вариантами ответов на каждый из них.</br>
Для ответа на каждый вопрос Вам даётся 30 сукунд.</br>
По завершению теста Вам будет представлена таблица с результатами его прохождения.
</p>
</br>
 <button type="button" onclick='window.location.href =  "/task/list";'>  Начать тест</button>
</body>
</html>
