<%-- 
	addTaskNG.jsp
	タスク追加失敗ページのViewファイル
 --%>
 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク追加 - タスク管理アプリ</title>
</head>
<body>

	<h1>タスク追加失敗</h1>
	
	<p style="color: red;">タスクの登録ができませんでした・・・</p>
	
	<jsp:include page="displayTaskInfo.jsp"></jsp:include>
	
	<p><a href="AddTaskServlet?action=input">入力画面に戻る</a></p>
	
	<p><a href="TaskMenuServlet">タスク管理メニューへ戻る</a></p>
	<%--  <p><a href="UserMenuServlet">ユーザ管理メニューへ</a></p> --%>
	<p><a href="MainMenuServlet">メインメニューへ</a></p>
	
	<%-- フッター  --%>
	<jsp:include page="footer.jsp"></jsp:include>
	
</body>
</html>