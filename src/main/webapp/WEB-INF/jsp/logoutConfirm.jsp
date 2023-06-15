<%-- 
	logoutConfirm.jsp
	ログアウト完了ページのViewファイル
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログアウト - タスク管理アプリ</title>
</head>
<body>
	<h1>タスク管理アプリ　ログアウト</h1>
	
	<p>ログアウトしますか？</p>
	
	<button onclick="location.href='LogoutServlet?action=logout'">はい</button>
	<button onclick="location.href='MainMenuServlet'">いいえ</button>
	
	<br>
	
	<%-- フッター  --%>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>