<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<title>boardList</title>
</head>
<body>
<%@ include file="topBar.jsp" %>
<div class="container w-75 mt-5 mx-auto">
    <div style="display: flex; justify-content: space-between;">
        <h2>내 투표 기록</h2>
    </div>
    <hr>
    <table class="table">
        <thead>
            <tr>
                <th scope="col">경기 번호</th>
                <th scope="col">포인트</th>
                <th scope="col">투표한 팀</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${votelist}" var="vlist">
                <tr>
                    <td>${vlist.sid}</td>
                    <td>${vlist.point}</td>
                    <td>${vlist.voteParticipant}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>




