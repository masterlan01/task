<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
	<title>Вопрос № ${nextQuestion.numb}</title>	
</head>
 
 <script 	src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script> 
  <script 	src="${pageContext.request.contextPath}/resources/js/list.js"></script> 
 

<body>
<h1  id="textQuestion">
 ${nextQuestion.question.text}  
</h1>
<h2 id="timer">осталось  :  30 (сек.)</h2>

       <table id="answerTable">
        <c:forEach var="answer" items="${nextQuestion.answers}">
        	<tr>
        	<td><input type="radio" name="answerGrp" value="${answer.id}"/>${answer.text} </td>
   			 </tr>
		</c:forEach>
        </table>

</br>
    <button type="button" onclick="RestPost()">Далее</button>
</body>
</html>
