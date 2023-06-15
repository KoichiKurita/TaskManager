<%-- 
	logout.jsp
	ログアウトページのViewファイル
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
	
	<p>ログアウトしました</p>
	
	<a href="TopPageServlet">トップへ戻る</a>
	
	<%-- フッター  --%>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>