<%-- 
	editUserOK.jsp
	ユーザ変更成功ページのViewファイル
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
	<h1>タスク管理アプリ　ユーザ変更成功</h1>
	
	<p>ユーザ情報を変更しました</p>
	
	<jsp:include page="displayUserInfo.jsp"></jsp:include>
	
		<p><a href="UserMenuServlet">ユーザ管理メニューへ戻る</a></p>
	<p><a href="TaskMenuServlet">タスク管理メニューへ</a></p>
	<p><a href="MainMenuServlet">メインメニューへ</a></p>
	
	<%-- フッター  --%>
	<jsp:include page="footer.jsp"></jsp:include>
	
</body>
</html>