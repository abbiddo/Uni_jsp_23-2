<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이 페이지</title>
</head>
<body>
<%@ include file="topBar.jsp" %>
<div class="container w-75 mt-5 mx-auto">
	<div style="display: flex; justify-content: space-between;">
	        <h2>내 정보</h2>
	</div>
	<hr>
	<c:forEach items="${a}">
    </c:forEach>
    <table class="table">
        <tbody>
            <tr>
                <th scope="row">ID:</th>
                <td>${user.id}</td>
            </tr>
            <tr>
                <th scope="row">이름:</th>
                <td>${user.name}</td>
            </tr>
            <c:if test="${role == 'user' }">
	            <tr>
	                <th scope="row">포인트:</th>
	                <td>${user.point}</td>
	            </tr>
	        </c:if>
            <tr>
                <th scope="row">전공:</th>
                <td>${user.major}</td>
            </tr>
            <tr>
                <th scope="row">학번:</th>
                <td>${user.stNumber}</td>
            </tr>
            <tr>
                <th scope="row">역할:</th>
                <td>${user.role}</td>
            </tr>
        </tbody>
    </table>
</div>
</body>
</html>