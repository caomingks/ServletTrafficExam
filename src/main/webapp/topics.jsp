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
		//当页面加载时
		//当页面第一次加载的时候，请求当前题，并解析显示
		//相当于windows.onload()
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

		
		
		//局部刷新，上一题下一体
		$("a").click(
				function() {
					
					
					
					var url = this.href;
					
					//获取选中的单选框的值
					var value=$('input:radio[name="select"]:checked').val();
					
					var args = {
						"time" : new Date(),  "select" : value
					};
					
					
					
					$.getJSON(url, args, function(data) {
						
						//对请求返回的数据解析
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
	
							
							//如果下一题或者上一题有答案了，则将其选中
							if(checked!=null)
							{
								var selectValue = '#'+checked;
								$(selectValue).attr("checked", true);
							}
			

						
						}
						else
						{
							
							//最后一题，提示信息
							alert(message);
						}

					});

					
					//取消<a>标签默认行为（跳转到请求的URL）
					return false;
				});
		
		
		
		
	})
</script>



</head>
<body>
	
	<div id="details" align="center">
	</div>
	
	<div align="center">
	<a  href="preQuestion">pre</a>
	<a  href="nextQuestion">next</a>
	</div>
	
	<div align="center">
	<form action="grade">
	<input type="submit" value="jiaojuan"/>
	</form>
	</div>
</body>
</html>