<%-- 
	editUserConfirm.jsp
	変更するユーザ情報の入力確認ページのViewファイル
 --%>
 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザ変更 - タスク管理アプリ</title>
</head>
<body>
	<h1>ユーザ情報変更確認画面</h1>
	
	<p>下記の内容で登録してもよろしいですか？</p>
	
	<jsp:include page="displayUserInfo.jsp"></jsp:include>
	
	<button onclick="location.href='EditUserServlet?action=input'">入力画面に戻る</button>
	<button onclick="location.href='EditUserServlet?action=register'">登録</button>
	
	<br><br>
	
	<p><a href="UserMenuServlet">ユーザ管理メニューへ戻る</a>（入力内容は破棄されます）</p>
	<p><a href="TaskMenuServlet">タスク管理メニューへ</a>（入力内容は破棄されます）</p>
	<p><a href="MainMenuServlet">メインメニューへ</a>（入力内容は破棄されます）</p>
	
	<%-- フッター  --%>
	<jsp:include page="footer.jsp"></jsp:include>
	
</body>
</html>