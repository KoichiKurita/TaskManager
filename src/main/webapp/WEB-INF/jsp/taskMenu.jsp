<%-- 
	taskMenu.jsp
	タスク管理のメニューページのViewファイル
 --%>
 
<%--
	jspファイルにおけるcssファイルの読み込みについて
	参考サイト：
	https://code-examples-ja.hateblo.jp/entry/2015/01/07/JSP%E3%81%A7WEB-INF%E4%BB%A5%E4%B8%8B%E3%81%AEcss%E3%83%95%E3%82%A1%E3%82%A4%E3%83%AB%E3%82%84JavaScript%E3%82%92%E5%8F%96%E3%82%8A%E8%BE%BC%E3%82%80
--%>
 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク管理メニュー - タスク管理アプリ</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

	<h1>タスク管理アプリ タスク管理メニュー</h1>
	
	<p><c:out value="${user.userId}" />さん、ログイン中</p>
	
	<%-- 今日の日付を取得 --%>
	<jsp:useBean id="today" class="java.util.Date" />
	<p>本日は<fmt:formatDate value="${today}" pattern="yyyy年MM月dd日"/>です。</p>
	
	<br>
	
	<h2>タスク一覧</h2>
	
	<table border="1">
		<tr>
			<th>タスク名</th>
			<th>タスク内容</th>
			<th>期限</th>
			<th>残り日数</th>
			<th>優先度</th>
			<th>編集</th>
			<th>削除</th>
		</tr>
		
		<c:forEach var="task" items="${taskList}">
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
					
				<td><button id="edit-btn" class="task-contorol-btn" onclick="location.href='EditTaskServlet?action=input&taskId=${task.taskId}'">編集</button></td>
				<td><button id="delete-btn" class="task-contorol-btn" onclick="location.href='DeleteTaskServlet?action=confirm&taskId=${task.taskId}'">削除</button></td>
			</tr>
		</c:forEach>
	</table>
	
	<br>
	
	<h2>タスクの登録</h2>
	<button id="add-btn" class="task-contorol-btn" onclick="location.href='AddTaskServlet?action=input'">登録</button><br>
	
	<br><br>
	
	<p><a href="MainMenuServlet">メインメニューへ戻る</a></p>
	<p><a href="UserMenuServlet">ユーザ管理メニューへ</a></p>
	
	<button onclick="location.href='LogoutServlet?action=confirm'">ログアウト</button>
	
	<%-- フッター  --%>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>