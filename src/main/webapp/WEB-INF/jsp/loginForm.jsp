<%-- 
	loginForm.jsp
	ログインページのViewファイル
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン - タスク管理アプリ</title>
</head>
<body>
	<h1>タスク管理アプリ　ログイン</h1>
	
	<%-- ユーザID, パスワード入力フォーム --%>
	<form action="LoginServlet" method="post">
		<jsp:include page="userForm.jsp"></jsp:include>
		
		<%-- 入力されたユーザ情報が誤りがある場合 --%>
		<c:if test="${errorMessages != null}" >
			<c:forEach var="errorMessage" items="${errorMessages}" >
				<c:if test="${ errorMessage.equals('※ユーザIDまたはパスワードが誤っております。') }" >
					<span style="color: red;"><c:out value="${ errorMessage }" /></span><br>
				</c:if>
			</c:forEach>
		</c:if>
		
		<br>
		
		<input type="submit" value="ログイン">
	</form>
	
	<br>
	
	<p>新規ユーザ登録は<a href="RegisterUserServlet?action=input">こちら</a></p>
	
	<p><a href="TopPageServlet">トップへ戻る</a></p>
	
	<%-- フッター  --%>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>