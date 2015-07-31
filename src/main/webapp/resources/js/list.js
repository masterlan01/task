/**
 *  Скрипт для страницы с вопросами   ( list.jsp )
 */
		var timer = 30;
		var left = timer;
		$(document).ready(function() {
 			
			var timerId = setTimeout(function tick() {
				$("#timer").text('осталось  :  '+(left--)+' (сек.)');
				if(left<0){
					RestPost();
					left =timer;
					$("#timer").text('осталось  :  '+(left--)+' (сек.)');
				}
				  timerId = setTimeout(tick, 1000);
				}, 1000);

		});
		
 
    var prefix = '/task/list';
 
    var RestPost = function() {
        var JSONObject= {
            'answerID': $('input[name=answerGrp]:checked').val()
        };
        
        $.ajax({
            type: 'POST',
            url:  prefix,
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(JSONObject),
            dataType: 'json',
            async: false,
            success: function(result) {
            	
					if(result.question==null)
						window.location.href =  "/task/result";
					
	                $('#textQuestion').html(result.question.text);
	                $('title').html('Вопрос № '+result.numb);
	                
	                var a_list =result.answers; 
	                var new_al = " <table id=\"answerTable\">"; 
	
	                for (var i=0; i< a_list.length; i++)
	                	new_al +="<tr> <td><input type=\"radio\" name=\"answerGrp\" value=\""+a_list[i].id+"\"/>"+a_list[i].text+" </td>	 </tr>";
	                
	                new_al +=" </table>";
	                $('#answerTable').replaceWith(new_al);
					left =timer;
                
            },
            error: function(jqXHR, textStatus, errorThrown) {
            	document.close();
                document.write(jqXHR.responseText);
             }
        });
     }

   