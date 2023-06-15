<%-- 
	userForm.jsp
	ユーザID, パスワード入力フォーム
 --%>
 
 <%-- 
	HTMLのエスケープ文字について
	参考サイト：
	https://magazine.techacademy.jp/magazine/12553
 --%>
 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% 
	// loginUserをリクエストスコープから取得する
	User loginUser = (User)request.getAttribute("loginUser");

	// registerUserをセッションスコープから取得する
	User registerUser = (User)session.getAttribute("registerUser");
	
	// editedUserをセッションスコープから取得する
	User editedUser = (User)session.getAttribute("editedUser");
	
	// userをセッションスコープから取得する
	User user = (User)session.getAttribute("user");
	
	String userId = "";
	String passWord = "";
	
	if (registerUser != null) {
		userId = registerUser.getUserId();
		passWord = registerUser.getPassWord();
	} else if (loginUser != null){
		userId = loginUser.getUserId();
		passWord = loginUser.getPassWord();
	} else if (editedUser != null) {
		userId = editedUser.getUserId();
		passWord = editedUser.getPassWord();
	} else if (user != null) {
		userId = user.getUserId();
		passWord = user.getPassWord();
	}
%>

<label for="user-ID">
	ユーザーID<span style="color:red;">（必須）</span>：<input type="text" name="user-ID" id="user-ID" value="<%= userId %>" required><br>
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
	パスワード<span style="color:red;">（必須）</span>：<input type="password" name="pass-word" id="pass-word" value="<%= passWord %>" required><br>
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
		<c:forEach var="errorMessage" items="${errorMessageMap.get('passWord')}" >			
			<span style="color: red;"><c:out value="${ errorMessage }" /></span><br>
		</c:forEach>
	</c:otherwise>
</c:choose>

<br>

