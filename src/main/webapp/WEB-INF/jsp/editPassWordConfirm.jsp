<%-- 
	editPassWordConfirm.jsp
	新しいパスワードの入力確認ページのViewファイル
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
	<h1>パスワード変更確認画面</h1>
	
	<p>下記の内容で登録してもよろしいですか？</p>
	
	<p>パスワード:
		<% for (int i = 0; i < newUser.getPassWord().length(); i++) { %>
				<%= "*" %>
		<% } %>
		（セキュリティ保護のため伏せ字にしております。）
	</p>
	
	<br>
	
	<button onclick="location.href='EditPassWordServlet?action=input'">入力画面に戻る</button>
	<button onclick="location.href='EditPassWordServlet?action=register'">登録</button>
	
	<br>
	
	<p><a href="UserMenuServlet">ユーザ管理メニューへ戻る</a>（入力内容は破棄されます）</p>
	<p><a href="TaskMenuServlet">タスク管理メニューへ</a>（入力内容は破棄されます）</p>
	<p><a href="MainMenuServlet">メインメニューへ</a>（入力内容は破棄されます）</p>
	
	<%-- フッター  --%>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>