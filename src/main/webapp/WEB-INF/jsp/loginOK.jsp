<%-- 
	loginOK.jsp
	ログイン成功ページのViewファイル
 --%>
 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン - タスク管理アプリ</title>
</head>
<body>
	<h1>タスク管理アプリ　ログイン成功</h1>
	
	<p>ようこそ、<c:out value="${user.userId}" />さん！</p>
	
	<p><a href="MainMenuServlet">メインメニューへ</a></p>
	
	<%-- フッター  --%>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>