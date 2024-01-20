<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원가입 양식</title>
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
</head>
<body>
	<c:if test = "${error != null }"> ${error }</c:if>
    <h2>회원가입 양식</h2>
    <form action="/Sport/Login?action=addUser" method="post">
        <label for="id">아이디</label>
        <input type="text" id="id" name="id" required>

        <label for="password">비밀번호</label>
        <input type="password" id="password" name="password" required>
        
        <label for="password">비밀번호 확인</label>
        <input type="password" id="re_password" name="re_password" required>

        <label for="name">이름</label>
        <input type="name" id="name" name="name" required>

        <label for="stnumber">학번</label>
        <input type="stnumber" id="stnumber" name="stnumber" required>

        <label for="major">전공</label>
        <input type="text" id="major" name="major">
        
        <label for="adminNum">Admin 코드</label>
        <input type="password" id="adminNum" name="adminNum" >

        <input type="submit" value="가입하기">
    </form>
</body>
</html>
