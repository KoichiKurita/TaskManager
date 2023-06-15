<%-- 
	editPassWordOK.jsp
	パスワード変更成功ページのViewファイル
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>パスワード変更 - タスク管理アプリ</title>
</head>
<body>
	<h1>パスワード変更完了</h1>
	
	<p><c:out value="${user.userId}" />さんのパスワードを変更しました！</p>
	
	<p>次回のログインから新しいパスワードを使用してください。</p>
		
	<p><a href="UserMenuServlet">ユーザ管理メニューへ戻る</a></p>
	<p><a href="TaskMenuServlet">タスク管理メニューへ</a></p>
	<p><a href="MainMenuServlet">メインメニューへ</a></p>
	
	<%-- フッター  --%>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>