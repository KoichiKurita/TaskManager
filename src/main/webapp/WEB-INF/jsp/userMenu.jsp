<%-- 
	userMenu.jsp
	ユーザ管理のメニューページのViewファイル
 --%>
 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザ管理メニュー - タスク管理アプリ</title>
</head>
<body>
	<h1>タスク管理アプリ ユーザ管理メニュー</h1>
	
	<p><c:out value="${user.userId}" />さん、ログイン中</p>
	
	<br>
	
	<h2>パスワードの変更</h2>
	<button onclick="location.href='EditPassWordServlet?action=input'">パスワード変更</button>
	
	<br><br>
	
	<%-- 
	<h2>ユーザ情報削除</h2>
	<button onclick="location.href='DeleteUserServlet'">削除</button>
	
	<br><br>
	--%>
	
	<p><a href="MainMenuServlet">メインメニューへ戻る</a></p>
	<p><a href="TaskMenuServlet">タスク管理メニューへ</a></p>
	
	<button onclick="location.href='LogoutServlet?action=confirm'">ログアウト</button>
	
	<%-- フッター  --%>
	<jsp:include page="footer.jsp"></jsp:include>
	
</body>
</html>