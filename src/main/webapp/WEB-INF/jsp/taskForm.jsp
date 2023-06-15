<%-- 
	taskForm.jsp
	タスク入力フォーム
 --%>
 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="model.Task" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="priorityData.jsp" %>

<%
	// セッショントスコープからタスクを取得
	Task task = (Task)session.getAttribute("task");
	
	// 日付のフォーマット文字列
	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

	// タスクの期限 形式はyyyy-MM-dd
	String strDeadline = null;
	
	// タスクが存在する場合、タスクの期限を<input type="date">の形式(yyyy-MM-dd)に変換
	if (task != null) {
		strDeadline = f.format(task.getDeadline());
	} else {
		// タスクが存在しない場合、入力時点の日付を設定
		strDeadline = f.format(new Date());
	}
%>

<label for="title">タスク名：<br>
	<input type="text" name="title" id="title" value="${task.title}"><br>
</label>

<%-- エラーメッセージがある場合は表示する --%>
<c:forEach var="errorMessage" items="${errorMessages}" >
	<c:if test="${ errorMessage.equals('※タスク名は50文字以下で入力してください') }" >
		<span style="color: red;"><c:out value="${ errorMessage }" /></span>
	</c:if>
</c:forEach>

<br>

<label for="contents">タスク内容：<br>		
	<textarea name="contents" id="contents" cols="40" rows="10" style="resize: none;"><c:out value="${task.contents}" /></textarea><br>
</label>

<br>

<label for="deadline">
	期限：<br>
	<input type="date" name="deadline" id="deadline" value="<%= strDeadline %>" min="2000-01-01" max="9999-12-31" required>
</label>

<br><br>

<label for="priority">
	優先度：<br>
	<select name="priority" id="priority">
		<% if (task == null) { %>
			<option value="" selected>選択してください</option>
		<% } else { %>
			<option value="">選択してください</option>
		<% } %>
		
		<% for (int i = 0; i < prioritiesArray.length; i++) { %>
			<% if (task != null && prioritiesArray[i].equals(task.getPriority())) { %>
				<option value="<%= prioritiesArray[i] %>" selected><%= prioritiesHashMap.get(prioritiesArray[i]) %></option>
			<% } else { %>
				<option value="<%= prioritiesArray[i] %>"><%= prioritiesHashMap.get(prioritiesArray[i]) %></option>
			<% } %>
		<% } %>
	</select>
</label>

<br><br>

<input type="submit" value="登録確認">