<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
	<title>Результат тестирования</title>	
</head>

<body>
<h1>Вы прошли тест и показали следующие результаты</h1>


       <table  border cellpadding=10  cellspacing=0>
        	
        	<tr><td>Предложенных Вам вопросов  </td><td>${allQuestion}   </td></tr>
        	<tr><td>Количество полученных  ответов </td><td>${acceptAnswer}  </td></tr>
        	<tr><td>Из них правильных </td><td>${goodAnswer}</td></tr>
        	<tr><td>Количество допущеных  ошибок </td><td>${badAnswer} </td></tr>
        	<tr><td>Количество вопросв, оставшихся без ответа</td><td>${skipAnswer}  </td></tr>

        </table>
</br>

    <button type="button" onclick='window.location.href =  "/task";'>Повторить</button>
</body>
</html>
