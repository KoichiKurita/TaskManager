<%-- 
	displayTaskInfo.jsp
	タスク情報を表示する共通ファイル
 --%>
 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<table border="1">
	<tr>
		<th>タスク名</th>
		<th>タスク内容</th>
		<th>期限</th>
		<th>残り日数</th>
		<th>優先度</th>
	</tr>
	
	<tr>
		<td><c:out value="${task.title}" /></td>
		<td><c:out value="${task.contents}" /></td>
		<td align="center"><fmt:formatDate value="${task.deadline}" pattern="yyyy年MM月dd日"/></td>
		
		<c:choose>
			<c:when test="${5 <= task.daysLeft && task.daysLeft <= 10 }">
				<td align="center" style="color: white; background-color: orange;"><c:out value="${task.daysLeft}" />日</td>
			</c:when>
			<c:when test="${0 <= task.daysLeft && task.daysLeft <= 5 }">
				<td align="center" style="color: white; background-color: red;"><c:out value="${task.daysLeft}" />日</td>
			</c:when>
			<c:when test="${task.daysLeft < 0}">
				<td align="center" style="color: white; background-color: blue;"><c:out value="${task.daysLeft}" />日</td>
			</c:when>
			<c:otherwise>
				<td align="center"><c:out value="${task.daysLeft}" />日</td>
			</c:otherwise>
		</c:choose>
			
		<c:choose>
			<c:when test="${task.priority == 'high'}">
				<td align="center" style="color: white; background-color: red;">高</td>
			</c:when>
			<c:when test="${task.priority == 'middle'}">
				<td align="center" style="color: white; background-color: orange;">中</td>
			</c:when>
			<c:otherwise>
				<td align="center" style="color: white; background-color: blue;">低</td>
			</c:otherwise>
		</c:choose>
	</tr>
</table>