<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<style>
        body {
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
            flex-direction: column; /* 세로 방향으로 정렬 */
        }
        form {
            text-align: center;
            width: 300px; /* 폼의 너비를 조절 */
        }
        label {
            display: block;
            margin-bottom: 5px; /* 각 label 아래의 간격 조절 */
        }
        input {
            width: 100%;
            padding: 5px;
            box-sizing: border-box; /* 입력란의 크기를 조절하여 외곽선을 포함하도록 함 */
            margin-bottom: 10px; /* 각 input 아래의 간격 조절 */
        }
    </style>

<title>경기 일정 추가</title>
</head>
<body>
	<h2>경기 일정 추가</h2>
    <form action="/Sport/Schedule?action=addSchedule" method="post">
        <label">종목</label>
        <input type="text" name="subject" required>

        <label>날짜</label>
        <input type="text" name="date" required>
        
        <label>참가자1</label>
        <input type="text" name="participant1" required>

        <label>참가자2</label>
        <input type="text" name="participant2" required>

        <input type="submit" value="등록하기">
    </form>
</body>
</html>