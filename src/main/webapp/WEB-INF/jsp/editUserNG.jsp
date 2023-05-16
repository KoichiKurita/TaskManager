<%-- 
	editUserNG.jsp
	ユーザ変更失敗ページのViewファイル
 --%>
 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザ変更 - タスク管理アプリ</title>
</head>
<body>
	
	<p style="color: red;">ユーザの変更ができませんでした・・・</p>
	
	<jsp:include page="displayUserInfo.jsp"></jsp:include>
	
	<p><a href="EditUserServlet?action=input">入力画面に戻る</a></p>
	
	<p><a href="UserMenuServlet">ユーザ管理メニューへ戻る</a></p>
	<p><a href="TaskMenuServlet">タスク管理メニューへ</a></p>
	<p><a href="MainMenuServlet">メインメニューへ</a></p>
	
	<%-- フッター  --%>
	<jsp:include page="footer.jsp"></jsp:include>
	
</body>
</html>