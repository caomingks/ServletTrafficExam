<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>上传</title>


	
</head>
<body>
	<div align="center">
		<form action="upload" method="post" enctype="multipart/form-data">
		    上传<input type="file" name="uploadFile" style="border-style: solid;" />
			<br><br />
			<input type="submit" value="上传" />
		</form>
		<br><br>
		
		<a href="selectTopics">选题测试</a>

	</div>
</body>
</html>