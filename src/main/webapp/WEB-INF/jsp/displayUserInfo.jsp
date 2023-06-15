<%-- 
	displayUserInfo.jsp
	ユーザ情報を表示する共通ファイル
 --%>

<%--
	JSTLのfnタグライブラリのlengthタグについて
	参考サイト：
	http://struts.wasureppoi.com/jstl/04_length.html
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<% 
	// registerUserをセッションスコープから取得する
	User registerUser = (User)session.getAttribute("registerUser");
	
	// editedUserをセッションスコープから取得する
	User editedUser = (User)session.getAttribute("editedUser");
	
	String userId = "";
	String passWord = "";
	
	User user = null;
	
	if (registerUser != null) {
		user = registerUser;
	} else if (editedUser != null) {
		user = editedUser;
	}
%>

<p>ユーザID：<%= user.getUserId() %></p>
<p>パスワード:
	<% for (int i = 0; i < user.getPassWord().length(); i++) { %>
			<%= "*" %>
	<% } %>
	（セキュリティ保護のため伏せ字にしております。）
</p>