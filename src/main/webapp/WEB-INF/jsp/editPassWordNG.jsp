<%-- 
	editPassWordOK.jsp
	パスワード変更成功ページのViewファイル
 --%>
 
 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="model.User" %>
    
<% 
	// newUserをセッションスコープから取得する
	User newUser = (User)session.getAttribute("newUser");

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>パスワード変更 - タスク管理アプリ</title>
</head>
<body>
	<h1>パスワード変更失敗</h1>
	
	<p>パスワードを登録できませんでした・・・</p>
	
	<p>パスワード:
		<% for (int i = 0; i < newUser.getPassWord().length(); i++) { %>
				<%= "*" %>
		<% } %>
		（セキュリティ保護のため伏せ字にしております。）
	</p>
	
	<br>
	
	<p><a href="EditPassWordServlet?action=input">入力画面に戻る</a></p>
	
	<p><a href="UserMenuServlet">ユーザ管理メニューへ戻る</a></p>
	<p><a href="TaskMenuServlet">タスク管理メニューへ</a></p>
	<p><a href="MainMenuServlet">メインメニューへ</a></p>
	
	<%-- フッター  --%>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>