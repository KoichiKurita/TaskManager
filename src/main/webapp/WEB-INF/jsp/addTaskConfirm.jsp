<%-- 
	addTaskConfirm.jsp
	追加するタスクの入力確認ページのViewファイル
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
	<h1>タスク追加確認画面</h1>
	
	<p>下記の内容で登録してもよろしいですか？</p>
	
	<jsp:include page="displayTaskInfo.jsp"></jsp:include>
	
	<br>
	
	<button onclick="location.href='AddTaskServlet?action=input'">入力画面に戻る</button>
	<button onclick="location.href='AddTaskServlet?action=register'">登録</button>
	
	<br>
	
	<p><a href="TaskMenuServlet">タスク管理メニューへ戻る</a>（入力内容は破棄されます）</p>
	<p><a href="UserMenuServlet">ユーザ管理メニューへ</a>（入力内容は破棄されます）</p>
	<p><a href="MainMenuServlet">メインメニューへ</a>（入力内容は破棄されます）</p>
	
	<%-- フッター  --%>
	<jsp:include page="footer.jsp"></jsp:include>
	
</body>
</html>