<%-- 
	registerUserOK.jsp
	新規ユーザ登録成功ページのViewファイル
 --%>
 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザ登録 - タスク管理アプリ</title>
</head>
<body>
	<h1>タスク管理アプリ　ユーザ登録成功</h1>
	
	<p><c:out value="${user.userId}" />さんのユーザ情報を登録しました！</p>
	
	<p><a href="MainMenuServlet">メインメニューへ</a></p>
	
	<p><a href="TopPageServlet">トップへ戻る</a>（自動的にログアウトします）</p>
	
	<%-- フッター  --%>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>