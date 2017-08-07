<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="doc/jquery-3.2.1.js"></script>

<script type="text/javascript">
	$(function() {
		$("a").click(
				function() {

					var url = this.href;

					var args = {
						"time" : new Date()
					};

					$.getJSON(url, args, function(data) {

						$.each(data, function(index, item) {
							//alert(item.no);
							//alert(item.exercise.title);
							
							var no = item.no;
							var title = item.exercise.title;
							var selectA = item.exercise.selectA;
							var selectB = item.exercise.selectB;
							var selectC = item.exercise.selectC;
							var selectD = item.exercise.selectD;
							var answer = item.exercise.answer;
							var checked = item.checked==undefined ? item.checked : "未选择";

							$("#error").append(
									"<h2><div>" + no + ":" + title + "</div></h2>")
									.append("<h2><div>" + selectA + "</div></h2>")
									.append("<h2><div>" + selectB + "</div></h2>")
									.append("<h2><div>" + selectC + "</div></h2>")
									.append("<h2><div>" + selectD + "</div></h2>")
									.append(
											"<h2><div>" + "正确答案：" + answer
													+ "</div></h2>").append(
											"<h2><div>" + "你的选择：" + checked
													+ "</div></h2>");
						});


					});
					return false;
				});

		
	});
</script>
</head>
<body>

    <div align="center">
	<h1>成绩单</h1>
	</div>
	
	<br>
	<br>
	<div align="center">
	<table>
		<tr>
			<th>总题</th>
			<th>选对</th>
			<th>选错</th>
			<th>得分</th>
		</tr>

		<tr>
			<td>${topics.size()}</td>
			<td>${topics.size()-error.size()}</td>
			<td>${error.size()}</td>
			<td>${grade}</td>
		</tr>

	</table>
	</div>
	<br>
	<br>

<div align="center">
	<a href="errorQuestion">查看错题</a>
</div>

<div align="center">
<form action="endExam">
	<input id="exit" type="submit" value="离开考场"/>
</form>
</div>	
	

	<br>
	<br>
	<div id="error"></div>
</body>
</html>