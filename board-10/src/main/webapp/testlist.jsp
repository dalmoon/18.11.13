<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1" cellpadding="0" cellspacing="0" width="700">
	<c:forEach items="${list}" var="member">
		<tr>
			<td>${member.email}</td>
			<td>${member.nickname}</td>
			<td>${member.password}</td>
			<td>${member.regDate}</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>