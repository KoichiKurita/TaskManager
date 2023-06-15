<%-- 
	registerUserNG.jsp
	新規ユーザ登録失敗ページのViewファイル
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザ登録 - タスク管理アプリ</title>
</head>
<body>
	<h1>タスク管理アプリ　ユーザ登録失敗</h1>
	
	<p>ユーザ登録できませんでした・・・</p>

	<jsp:include page="displayUserInfo.jsp"></jsp:include>
	
	<p><a href="RegisterUserServlet?action=input">ユーザ登録ページへ戻る</a></p>
	
	<p>ログインは<a href="LoginServlet">こちら</a></p>
	
	<p><a href="TopPageServlet">トップへ戻る</a></p>
	
	<br>
	
	<%-- フッター  --%>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>