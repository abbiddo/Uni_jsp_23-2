<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <title>스포츠 웹사이트</title>
    <style>
        body {
            padding: 20px;
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary justify-content-between">
        <a class="navbar-brand" href="#">
            <img src="resources/img/Colleage_logo.svg" alt="TUKOREA Logo" height="30" class="mr-2">
            TUKOREA-Enjoy the Sports!
        </a>
		<div class="navbar-nav ml-auto border rounded">
    		<ul class="navbar-nav">
 		       <li class="nav-item">
  		          <a class="nav-link text-white" href="#">회원 정보 : <%= session.getAttribute("id") %></a>
  		      </li>
  		      <c:if test="${role == 'user' }">
	 		       <li class="nav-item">
	 		           <a class="nav-link text-white" href="#">내 포인트 : <%= session.getAttribute("point") %></a>
	  		      </li>
	  		  </c:if>
  		  </ul>
		</div>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item active mr-3">
                    <a class="nav-link text-white" href="/Sport/Schedule?action=recentMatch">홈</a>
                </li>
                <li class="nav-item mr-3">
                    <a class="nav-link text-white" href="/Sport/Schedule?action=listSchedules">경기 일정</a>
                </li>
                <li class="nav-item mr-3">
                    <a class="nav-link text-white" href="/Sport/Schedule?action=lastSchedule">지난 경기 일정</a>
                </li>
                <c:if test="${role == 'user' }">
	                <li class="nav-item mr-3">
	                    <a class="nav-link text-white" href="/Sport/MyVote?action=showMyVotes">내 투표 기록</a>
	                </li>
                </c:if>
                <li class="nav-item mr-3">
                    <a class="nav-link text-white" href="/Sport/MyPage?action=showMyInformation">내 정보</a>
                </li>
                <li class="nav-item mr-3">
                    <a class="nav-link text-white" href="/Sport/Login?action=loginUser">로그아웃</a>
                </li>
            </ul>
        </div>
    </nav>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>