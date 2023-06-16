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

<% 
	String userId = "";
	String passWord = "";
	String passWordConfirm = "";
	
	
	// ユーザIDをリクエストスコープから取得する
	String requestUserId = (String)request.getAttribute("requestUserId");
	
	// パスワードをリクエストスコープから取得する
	String requestPassWord = (String)request.getAttribute("requestPassWord");
	
	// 確認用パスワードをリクエストスコープから取得する
	String requestPassWordConfirm = (String)request.getAttribute("requestPassWordConfirm");
	
	// ユーザIDをセッションスコープから取得する
	String sessionUserId = (String)session.getAttribute("sessionUserId");
		
	// パスワードをセッションスコープから取得する
	String sessionPassWord = (String)session.getAttribute("sessionPassWord");
		
	// 確認用パスワードをセッションスコープから取得する
	String sessionPassWordConfirm = (String)session.getAttribute("sessionPassWordConfirm");
	
	
	if (requestUserId != null) {
		userId = requestUserId;
	} else if (sessionUserId != null) {
		userId = sessionUserId;
	}
	
	if (requestPassWord != null) {
		passWord = requestPassWord;
	} else if (sessionPassWord != null) {
		passWord = sessionPassWord;
	} 
	
	if (requestPassWordConfirm != null) {
		passWordConfirm = requestPassWordConfirm;
	} else if (sessionPassWordConfirm != null) {
		passWordConfirm = sessionPassWordConfirm;
	}
%>

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
		<%-- <jsp:include page="userForm.jsp"></jsp:include> --%>
		
		<label for="user-ID">
			ユーザーID<span style="color:red;">（必須）</span>：<br>
			<input type="text" name="user-ID" id="user-ID" value="<%= userId %>" required><br>
		</label>

		<c:choose>
			<%-- 入力画面に初めてアクセスしたとき --%>
			<c:when test="${errorMessageMap == null}" >
				<span style="color: red;">※半角英数8文字以上50文字以下</span><br>
				<span style="color: red;">※空白文字は利用不可</span><br>
			</c:when>
	
			<%-- ユーザIDが登録できず、入力画面に戻ってきたとき --%>
			<c:otherwise>
				<%-- エラーメッセージがある場合は表示する --%>
				<c:forEach var="errorMessage" items="${errorMessageMap.get('userId')}" >
					<span style="color: red;"><c:out value="${ errorMessage }" /></span><br>
				</c:forEach>
			</c:otherwise>
		</c:choose>

		<br>
		
		<label for="pass-word">
			パスワード<span style="color:red;">（必須）</span>：<br>
			<input type="password" name="pass-word" id="pass-word" value="<%= passWord %>" required><br>
		</label>
		
		<%-- ユーザIDが登録できず、入力画面に戻ってきたとき --%>
		<c:if test="${errorMessageMap != null}" >
			<%-- エラーメッセージがある場合は表示する --%>
			<c:forEach var="errorMessage" items="${errorMessageMap.get('passWord')}" >
				<span style="color: red;"><c:out value="${ errorMessage }" /></span><br>
			</c:forEach>
		</c:if>
		
		<br>
		
		<label for="pass-word-confirm">
			パスワード（確認）<span style="color:red;">（必須）</span>：<br>
			<input type="password" name="pass-word-confirm" id="pass-word-confirm" value="<%= passWordConfirm %>" required><br>
		</label>

		<c:choose>
			<%-- 入力画面に初めてアクセスしたとき --%>
			<c:when test="${errorMessageMap == null}" >
				<span style="color: red;">※半角英数記号8文字以上20文字以下</span><br>
				<span style="color: red;">※半角、英数、記号はそれぞれ最低1文字ずつ使用してください</span><br>
				<span style="color: red;">※使用可能な記号は !&quot;#$%&amp;&#39;()=-~^|&yen;`@{}[]+;*:&lt;&gt;,.?/_ です。</span><br>
				<span style="color: red;">※空白文字は利用不可</span><br>
			</c:when>
			
			<%-- ユーザIDが登録できず、入力画面に戻ってきたとき --%>
			<c:otherwise>
				<%-- エラーメッセージがある場合は表示する --%>
				<c:forEach var="errorMessage" items="${errorMessageMap.get('passWordConfirm')}" >			
					<span style="color: red;"><c:out value="${ errorMessage }" /></span><br>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		
		<%-- 入力されたユーザ情報が既に登録済みである場合、エラーメッセージを表示する --%>
		<c:if test="${errorMessageMap != null}" >
			<c:forEach var="errorMessage" items="${errorMessageMap.get('existUser')}" >
				<span style="color: red;"><c:out value="${ errorMessage }" /></span><br>
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