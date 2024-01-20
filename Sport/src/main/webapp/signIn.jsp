<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>로그인</title>
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
        input {
            width: 100%;
            padding: 5px;
            box-sizing: border-box; /* 입력란의 크기를 조절하여 외곽선을 포함하도록 함 */
            margin-bottom: 10px; /* 각 input 아래의 간격 조절 */
        }
        .button-container {
            display: flex;
            justify-content: space-between;
        }
    </style>
</head>
<body>
    <form action="/Sport/Login?action=login" method="post">
        <h2>아이디 <input type="text" name="id"></h2>
        <h2>비밀번호 <input type="password" name="password"></h2>
        <div class="button-container">
            <input type="submit" value="로그인">
            <input type="submit" formaction="signUp.jsp" value="회원가입">
        </div>
    </form>
</body>
</html>
