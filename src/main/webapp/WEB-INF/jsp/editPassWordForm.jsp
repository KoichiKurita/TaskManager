<%-- 
	editPassWordForm.jsp
	パスワード変更の入力フォームのViewファイル
 --%>
 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% 
	String currentPassWord = "";
	String newPassWord = "";
	String newPassWordConfirm = "";
	
	// 現在のパスワードをリクエストスコープから取得する
	String requestCurrentPassWord = (String)request.getAttribute("requestCurrentPassWord");
	
	// 新しいパスワードをリクエストスコープから取得する
	String requestNewPassWord = (String)request.getAttribute("requestNewPassWord");
	
	// 確認用パスワードをリクエストスコープから取得する
	String requestNewPassWordConfirm = (String)request.getAttribute("requestNewPassWordConfirm");
	
	// 現在のパスワードをセッションスコープから取得する
	String sessionCurrentPassWord = (String)session.getAttribute("sessionCurrentPassWord");
		
	// 新しいパスワードをセッションスコープから取得する
	String sessiontNewPassWord = (String)session.getAttribute("sessionNewPassWord");
	
	// 確認用パスワードをセッションスコープから取得する
	String sessionNewPassWordConfirm = (String)session.getAttribute("sessionNewPassWordConfirm");
	
	
	if (requestCurrentPassWord != null) {
		currentPassWord = requestCurrentPassWord;
	} else if (sessionCurrentPassWord != null) {
		currentPassWord = sessionCurrentPassWord;
	}
	
	if (requestNewPassWord != null) {
		newPassWord = requestNewPassWord;
	} else if (sessiontNewPassWord != null) {
		newPassWord = sessiontNewPassWord;
	} 
	
	if (requestNewPassWordConfirm != null) {
		newPassWordConfirm = requestNewPassWordConfirm;
	} else if (sessionNewPassWordConfirm != null) {
		newPassWordConfirm = sessionNewPassWordConfirm;
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>パスワード変更 - タスク管理アプリ</title>
</head>
<body>
	<h1>パスワード変更</h1>
	
	<%-- パスワード入力フォーム --%>
	<form action="EditPassWordServlet" method="post">
		
		<label for="current-pass-word">
			現在のパスワード<span style="color:red;">（必須）</span>：<br>
			<input type="password" name="current-pass-word" id="current-pass-word" value="<%= currentPassWord %>" required><br>
		</label>
	
		<%-- 現在のパスワードに誤りがあり、入力画面に戻ってきたとき --%>
		<c:if test="${errorMessageMap != null}" >
			<%-- エラーメッセージがある場合は表示する --%>
			<c:forEach var="errorMessage" items="${errorMessageMap.get('currentPassWord')}" >
				<span style="color: red;"><c:out value="${ errorMessage }" /></span><br>
			</c:forEach>
		</c:if>

		<br>
		
		<label for="new-pass-word">
			新しいパスワード<span style="color:red;">（必須）</span>：<br>
			<input type="password" name="new-pass-word" id="new-pass-word" value="<%= newPassWord %>" required><br>
		</label>
		
		<%-- 新しいパスワードに誤りがあり、入力画面に戻ってきたとき --%>
		<c:if test="${errorMessageMap != null}" >
			<%-- エラーメッセージがある場合は表示する --%>
			<c:forEach var="errorMessage" items="${errorMessageMap.get('newPassWord')}" >
				<span style="color: red;"><c:out value="${ errorMessage }" /></span><br>
			</c:forEach>
		</c:if>
		
		<br>
		
		<label for="new-pass-word-confirm">
			新しいパスワード（確認）<span style="color:red;">（必須）</span>：<br>
			<input type="password" name="new-pass-word-confirm" id="new-pass-word-confirm" value="<%= newPassWordConfirm %>" required><br>
		</label>

		<c:choose>
			<%-- 入力画面に初めてアクセスしたとき --%>
			<c:when test="${errorMessageMap == null}" >
				<span style="color: red;">※半角英数記号8文字以上20文字以下</span><br>
				<span style="color: red;">※半角、英数、記号はそれぞれ最低1文字ずつ使用してください</span><br>
				<span style="color: red;">※使用可能な記号は !&quot;#$%&amp;&#39;()=-~^|&yen;`@{}[]+;*:&lt;&gt;,.?/_ です。</span><br>
				<span style="color: red;">※空白文字は利用不可</span><br>
			</c:when>
			
			<%-- 確認用パスワードに誤りがあり、入力画面に戻ってきたとき --%>
			<c:otherwise>
				<%-- エラーメッセージがある場合は表示する --%>
				<c:forEach var="errorMessage" items="${errorMessageMap.get('newPassWordConfirm')}" >			
					<span style="color: red;"><c:out value="${ errorMessage }" /></span><br>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		
		<br>
		
		<input type="submit" value="確認">
	</form>
	
	<br>
	
	<p><a href="UserMenuServlet">ユーザ管理メニューへ戻る</a></p>
	<p><a href="TaskMenuServlet">タスク管理メニューへ</a></p>
	<p><a href="MainMenuServlet">メインメニューへ</a></p>
	
	<%-- フッター  --%>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>