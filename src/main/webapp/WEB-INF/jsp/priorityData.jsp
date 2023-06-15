<%-- 
	priorityData.jsp
	優先度の配列とHashMapデータ
 --%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.LinkedHashMap" %>
<%
	// 優先度の配列
	String[] prioritiesArray = {"high", "middle", "low"};
	
	// 優先度の英語名と日本語名を対応付けるHashMap
	LinkedHashMap prioritiesHashMap = new LinkedHashMap<String, String>();
	prioritiesHashMap.put("high", "高");
	prioritiesHashMap.put("middle", "中");
	prioritiesHashMap.put("low", "低");
%>