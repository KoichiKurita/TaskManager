<%-- 
	registerUserConfirm.jsp
	登録するユーザ情報の入力確認ページのViewファイル
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
	<h1>ユーザ登録確認画面</h1>
	
	<p>下記の内容で登録してもよろしいですか？</p>
	
	<jsp:include page="displayUserInfo.jsp"></jsp:include>
	
	<button onclick="location.href='RegisterUserServlet?action=input'">入力画面に戻る</button>
	<button onclick="location.href='RegisterUserServlet?action=register'">登録</button>
	
	<br><br>
	
	<p><a href="TopPageServlet">トップへ戻る</a>（入力内容は破棄されます）</p>
	
	<%-- フッター  --%>
	<jsp:include page="footer.jsp"></jsp:include>
	
</body>
</html>