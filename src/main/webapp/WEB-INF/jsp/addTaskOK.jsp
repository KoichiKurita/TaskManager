<%-- 
	addTaskOK.jsp
	タスク追加完了ページのViewファイル
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

	<h1>タスク追加完了</h1>
	
	<p>タスクを登録しました</p>
	
	<jsp:include page="displayTaskInfo.jsp"></jsp:include>
	
	<p><a href="TaskMenuServlet">タスク管理メニューへ戻る</a></p>
	<p><a href="UserMenuServlet">ユーザ管理メニューへ</a></p>
	<p><a href="MainMenuServlet">メインメニューへ</a></p>
	
	<%-- フッター  --%>
	<jsp:include page="footer.jsp"></jsp:include>
	
</body>
</html>