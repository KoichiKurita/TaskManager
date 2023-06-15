<%-- 
	deleteTaskConfirm.jsp
	削除するタスクの確認ページのViewファイル
 --%>
 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク削除 - タスク管理アプリ</title>
</head>
<body>
	<h1>タスク削除確認画面</h1>
	
	<p>下記のタスクを削除してもよろしいですか？</p>
	
	<jsp:include page="displayTaskInfo.jsp"></jsp:include>
	
	<br>
	
	<button onclick="location.href='DeleteTaskServlet?action=delete'">削除</button>
	
	<br>
	
	<p><a href="TaskMenuServlet">タスク管理メニューへ戻る</a></p>
	<p><a href="UserMenuServlet">ユーザ管理メニューへ</a></p>
	<p><a href="MainMenuServlet">メインメニューへ</a></p>
	
	<%-- フッター  --%>
	<jsp:include page="footer.jsp"></jsp:include>
	
</body>
</html>