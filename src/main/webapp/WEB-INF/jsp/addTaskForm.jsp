<%-- 
	addTaskForm.jsp
	追加するタスクの入力ページのViewファイル
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
	<h1>タスク追加</h1>
	
	<form action="AddTaskServlet" method="post">
		<jsp:include page="taskForm.jsp"></jsp:include>
	</form>
	
	<br>
	
	<p><a href="TaskMenuServlet">タスク管理メニューへ戻る</a></p>
	<%-- <p><a href="UserMenuServlet">ユーザ管理メニューへ</a></p>--%>
	<p><a href="MainMenuServlet">メインメニューへ</a></p>
	
	<%-- フッター  --%>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>