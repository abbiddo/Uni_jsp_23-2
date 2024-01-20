<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>MainPage</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>

        .match-info {
            background-color: #f8f9fa;
            padding: 15px;
            margin-top: 20px;
            border-radius: 5px;
        }

        h2 {
            color: #007bff;
        }

        .custom-container {
            border: 2px solid #007bff;
            border-radius: 10px;
            padding: 20px;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <%@ include file="topBar.jsp" %>
    <div class="container custom-container">
        <div class="text-center mt-5">
            	<c:if test = "${match.sid != null }">
                    <h2><strong>경기 날짜 : </strong>${match.date} <strong></h2>
                    <br><br>
                    <h3><strong>경기 종목 : </strong>${match.subject} <strong></h3>
                    <br><br>
                    <div class="row">
                        <div class="col-md-6">
                            <h3><strong>경기 참가자1 : </strong>${match.participant1 }</h3>
                        </div>
                        <div class="col-md-6">
                            <h3><strong>경기 참가자2 : </strong>${ match.participant2 }</h3>
                        </div>
                        <br><br><br><br><br>
                        <div class="col-md-12 text-center">
                            <p><img src="resources/img/main.png" alt="Sports" class="img-fluid" style="width: 20%;"></p>
                        </div>
                        <div class="col-md-12 text-right mt-3">
                        	<form action="/Sport/Schedule?action=listSchedules" method="post">
								<input type="submit" class="btn btn-primary" value="투표하러 가기">
							</form>
                        </div>
                    </div>
                </c:if>
				<c:if test = "${match.sid == null }">
                    <p>오늘은 경기가 없습니다.</p>
                </c:if>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
