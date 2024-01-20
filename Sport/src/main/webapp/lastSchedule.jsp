<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<title>지난 경기</title>

<style>
	.list-group-item {
	    display: flex;
	    justify-content: space-between;
	    padding: 10px;
	}
	
	.list-group-item form {
	    width: 100%;
	    display: flex;
	    justify-content: space-between;
	    align-items: center;
	}
	
	.list-group-item input[type="text"] {
	    width: 10%;
	}
</style>

</head>
<body>
<%@ include file="topBar.jsp" %>
<div class="container w-75 mt-5 mx-auto">
	<h2>지난 경기</h2>
	<hr>
	<table class="table">
        <thead>
            <tr>
                <th scope="col"></th>
                <th scope="col">참가자 1</th>
                <th scope="col">경기 번호</th>
                <th scope="col">종목</th>
                <th scope="col">날짜</th>
                <th scope="col">참가자 2</th>
                <th scope="col">우승팀</th>
                <th scope="col"></th>
            </tr>
        </thead>
        <tbody>
            <form name="win" method="post" encType="UTF-8">
	            <c:forEach var="schedule" items="${lastSchedule}" varStatus="status">
	                <tr>
	                    <td>
                            <c:if test="${schedule.winner == null && role == 'admin'}">
                                <input type="submit" formaction="/Sport/Schedule?action=selectWinner&sid=${schedule.sid}&win=1" value="우승">
                            </c:if>
	                    </td>
	                    <td>${schedule.participant1}</td>
	                    <td>${schedule.sid}</td>
	                    <td>${schedule.subject}</td>
	                    <td>${schedule.date}</td>
	                    <td>${schedule.participant2}</td>
	                    <td>${schedule.winner}</td>
	                    <td>
                            <c:if test="${schedule.winner == null && role == 'admin'}">
                                <input type="submit" formaction="/Sport/Schedule?action=selectWinner&sid=${schedule.sid}&win=2" value="우승">
                            </c:if>
	                    </td>
	                </tr>
	            </c:forEach>
	        </form>
        </tbody>
    </table>
</div>
</body>
</html>