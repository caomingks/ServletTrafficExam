<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>考试开始</title>

<script type="text/javascript" src="doc/jquery-3.2.1.js"></script>

<script type="text/javascript">
	$(function() {

		
		var url = "currentQuestion";
		$.getJSON(url, function(data) {
			var no = data.no;
			var title = data.exercise.title;
			var selectA = data.exercise.selectA;
			var selectB = data.exercise.selectB;
			var selectC = data.exercise.selectC;
			var selectD = data.exercise.selectD;

			$("#details").empty()
			.append(no + "\." + title + "<br>")
			.append("<input  type='radio'  name='select' value='A'>"+selectA+"<br>")
			.append("<input  type='radio'  name='select' value='B'>"+selectB+"<br>")
			.append("<input  type='radio'  name='select' value='C'>"+selectC+"<br>")
			.append("<input  type='radio'  name='select' value='D'>"+selectD+"<br>");
		});

		
		
		
		$("a").click(
				function() {
					
					
					
					var url = this.href;
					var value=$('input:radio[name="select"]:checked').val();
					
					var args = {
						"time" : new Date(),  "select" : value
					};
					
					
					
					$.getJSON(url, args, function(data) {
						
						var message=data.message;
						
	
						
						
						if(undefined==message)
						{
							var no = data.no;
							var title = data.exercise.title;
							var selectA = data.exercise.selectA;
							var selectB = data.exercise.selectB;
							var selectC = data.exercise.selectC;
							var selectD = data.exercise.selectD;
							var checked=data.checked;
							
							$("#details").empty()
							.append(no + "\." + title + "<br>")
							.append("<input id='A' type='radio'  name='select' value='A'>"+selectA+"<br>")
							.append("<input id='B' type='radio'  name='select' value='B'>"+selectB+"<br>")
							.append("<input id='C' type='radio'  name='select' value='C'>"+selectC+"<br>")
							.append("<input id='D' type='radio'  name='select' value='D'>"+selectD+"<br>");
						}
						else
						{
							alert(message);
						}
						
						
	
						

					});

					return false;
				});
		
		
	})
</script>

</head>
<body>
	
	<div id="details">
	</div>
	
	
	<a  href="preQuestion">pre</a>
	<a  href="nextQuestion">next</a>
	<form action="grade">
	<input type="submit" value="jiaojuan"/>
	</form>
	
</body>
</html>