<%-- 
	editUserForm.jsp
	変更するユーザの入力ページのViewファイル
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザ変更 - タスク管理アプリ</title>
</head>
<body>

	<h1>ユーザ情報変更</h1>
	
	<%-- ユーザID, パスワード入力フォーム --%>
	<form action="EditUserServlet" method="post">
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
	
	<p><a href="UserMenuServlet">ユーザ管理メニューへ戻る</a></p>
	<p><a href="TaskMenuServlet">タスク管理メニューへ</a></p>
	<p><a href="MainMenuServlet">メインメニューへ</a></p>
	
	<%-- フッター  --%>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>