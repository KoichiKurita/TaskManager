<%-- 
	mainMenu.jsp
	メインメニューページのViewファイル
 --%>
 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メインメニュー - タスク管理アプリ</title>
</head>
<body>
	<h1>タスク管理アプリ メインメニュー</h1>
	
	<p><c:out value="${user.userId}" />さん、ログイン中</p>
	
	<ul>
		<li><a href="TaskMenuServlet">タスク管理</a></li>
		<%-- <li><a href="UserMenuServlet">ユーザ管理</a></li> --%>
	</ul>
	
	<button onclick="location.href='LogoutServlet?action=confirm'">ログアウト</button>
	
	<%-- フッター  --%>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>