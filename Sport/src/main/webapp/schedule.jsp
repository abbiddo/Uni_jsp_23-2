<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<title>경기 일정</title>

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
    
<script>
	var upperLimit = ${sessionScope.point};
	function validateAndSubmit(index, sid, choice) {
		var score;
		if (choice == 1)
			score = document.getElementsByName("score1")[index].value;
		else
			score = document.getElementsByName("score2")[index].value;

	    // 숫자 확인
	    if (!isNumeric(score)) {
	        alert("숫자만 입력하세요...");
	        return false;
	    }

	    score = parseInt(score, 10);
	    
	    // 범위 확인
	    if (score < 0 || score > upperLimit) {
	        alert("보유한 포인트 내로 입력하세요 (0에서 " + upperLimit+ "사이).");
	        return false;
	    }

	    // 폼 업데이트
	    var form = document.vote;
	    form.method = "post";
	    form.action = "/Sport/Schedule?action=votePoint1&sid=" + sid + "&choice="+choice + "&score="+score;
	    form.submit();
	}
	
	function isNumeric(value) {
	    return /^\d+$/.test(value);
	}
</script>
    
    
</head>
<body>
<%@ include file="topBar.jsp" %>
<div class="container w-75 mt-5 mx-auto">
	<div style="display: flex; justify-content: space-between;">		
		<h2>경기 일정</h2>
        
        <c:if test = "${role == 'admin' }">
			<form action="addSchedule.jsp" method="post">
				<input type="submit" value="경기 일정 추가">
			</form>
		</c:if>
	</div>
	
	<hr>
	<table class="table">
        <thead>
            <tr>
                <th scope="col">투표 1</th>
                <th scope="col">참가자 1</th>
                <th scope="col">경기 번호</th>
                <th scope="col">종목</th>
                <th scope="col">날짜</th>
                <th scope="col">참가자 2</th>
                <th scope="col">투표 2</th>
            </tr>
        </thead>
        <tbody>
        	<form name="vote" method="post" encType="UTF-8">
	            <c:forEach var="schedule" items="${schedulelist}" varStatus="status">
	                <tr>
	                    <td>
                            <c:if test="${role == 'user' }">
                                <input type="button" onclick="validateAndSubmit(${status.index }, ${schedule.sid }, 1)" value="투표">
                                <input type="text" name="score1" style="width: 60px;">
                            </c:if>
	                    </td>
	                    <td>${schedule.participant1}</td>
	                    <td>${schedule.sid }
	                    <td>${schedule.subject}</td>
	                    <td>${schedule.date}</td>
	                    <td>${schedule.participant2}</td>
	                    <td>                    
	                        <c:if test="${role == 'user' }">
	                            <input type="text" name="score2" style="width: 60px;">
	                            <input type="button" onclick="validateAndSubmit(${status.index }, ${schedule.sid }, 2)" value="투표">
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