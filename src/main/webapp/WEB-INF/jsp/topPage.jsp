<%-- 
	topPage.jsp
	トップページのViewファイル
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク管理アプリ</title>
</head>
<body>
	<h1>タスク管理アプリ</h1>
	
	<p>あなたは通算${count}人目の利用者です。</p>
	
	<p>メニュー</p>
	
	<ul>
		<li><a href="LoginServlet">ログイン</a>
		<li><a href="RegisterUserServlet?action=input">新規ユーザー登録</a></li>
	</ul>
	
	<%-- フッター  --%>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>