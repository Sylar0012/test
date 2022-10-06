<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<ul>
	<li>
	<a href="/imgtest/imgSaveForm">이미지 업로드</a>
	</li>
</ul>
<br/>
<br/>
<br/>
<div>
<h2>이미지 출력</h2>
<br/>
<h3>${img.title}</h3>
<img src="/img/${img.imgName}">
<p>${img.content}</p>
<p>${img.createdAt}</p>
</div>
</body>
</html>