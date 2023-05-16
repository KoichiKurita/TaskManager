<%-- 
	deleteTaskNG.jsp
	タスク削除失敗ページのViewファイル
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

	<h1>タスク削除失敗</h1>
	
	<p style="color: red;">タスクの削除ができませんでした・・・</p>
	
	<jsp:include page="displayTaskInfo.jsp"></jsp:include>
	
	<p><a href="DeleteTaskServlet?action=confirm&taskId=${task.taskId}">確認画面に戻る</a></p>
	
	<p><a href="TaskMenuServlet">タスク管理メニューへ戻る</a></p>
	<%-- <p><a href="UserMenuServlet">ユーザ管理メニューへ</a></p> --%>
	<p><a href="MainMenuServlet">メインメニューへ</a></p>
	
	<%-- フッター  --%>
	<jsp:include page="footer.jsp"></jsp:include>
	
</body>
</html>