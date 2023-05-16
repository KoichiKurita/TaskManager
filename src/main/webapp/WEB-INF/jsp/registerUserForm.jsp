<%-- 
	registerUserForm.jsp
	新規ユーザ登録ページのViewファイル
 --%>
 
 <%-- 
	HTMLのエスケープ文字について
	参考サイト：
	https://magazine.techacademy.jp/magazine/12553
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザ登録 - タスク管理アプリ</title>
</head>
<body>
	<h1>タスク管理アプリ　新規ユーザ登録</h1>
	
	<%-- ユーザID, パスワード入力フォーム --%>
	<form action="RegisterUserServlet" method="post">
		<jsp:include page="userForm.jsp"></jsp:include>
		
		<%-- 入力されたユーザ情報が既に登録済みである場合 --%>
		<c:if test="${errorMessages != null}" >
			<c:forEach var="errorMessage" items="${errorMessages}" >
				<c:if test="${ errorMessage.equals('※既に登録されているユーザです') }" >
					<span style="color: red;"><c:out value="${ errorMessage }" /></span><br>
				</c:if>
			</c:forEach>
		</c:if>
		
		<br>
		
		<input type="submit" value="登録">
	</form>
	
	<br>
	
	<p>ログインは<a href="LoginServlet">こちら</a></p>
	
	<p><a href="TopPageServlet">トップへ戻る</a></p>
	
	<%-- フッター --%>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>