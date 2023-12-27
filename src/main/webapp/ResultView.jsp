<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel='StyleSheet' href='style.css' type='text/css'>
<title>Student Loan Application</title>
</head>
<body>
<header>Student Loan Application</header>

<p class="subtitle">Client Protocol: <% out.print(request.getProtocol()); %></p>
<p class="subtitle">Client Method: <% out.print(request.getMethod()); %></p>
<p class="subtitle">Query String: <% out.print(request.getQueryString()); %></p>
<p class="subtitle">Entered Principal: ${param.principal}</p>
<p class="subtitle">Entered Interest: ${param.interest}</p>
<p class="subtitle">Entered Period: ${param.period}</p>
<hr>


<c:choose>
 <c:when test= "${sessionScope.count > 1}"> <p class='subtitle'>Welcome back, Calculation count: ${count}
 </p></c:when>
 <c:otherwise> <p class='subtitle'>Welcome. This is first time calculation for you. </p></c:otherwise>
 
</c:choose>
<form method="get" action="UI.html" class="resultForm">

<h2> <% out.println(request.getAttribute("payment")); %><br><br>
Calculation Used: </h2>
<ul>
<li>Principal: <% out.println(request.getAttribute("principal")); %></li>

<li>Interest: <% out.println(request.getAttribute("interest")); %></li>

<li>Period: <% out.println(request.getAttribute("period")); %></li>
</ul>
<input type="submit" value="Re-calculate" />
</form>
</body>
</html>