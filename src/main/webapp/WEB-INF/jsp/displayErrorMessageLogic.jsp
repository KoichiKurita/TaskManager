<%-- 
	displayErrorMessageLogic.jsp
	エラーメッセージを表示する処理
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<c:forEach var="errorMessage" items="${errorMessages}" >
		<p style="color:red;"><c:out value="${errorMessage}" /></p>
</c:forEach>