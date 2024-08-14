<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
     <style>

    </style>
</head>
<body>

<%@ include file="../common/head.jspf"%>        
        <!-- 로그인 폼 -->
        <div class="login-form">
            <h2>회원가입</h2>
            <form action="/usr/member/signMember" method="post">
                <input type="text" name="loginId" placeholder="아이디" required>
                <input type="text" name="loginPw" placeholder="비밀번호" required>
                <input type="text" name="name" placeholder="이름" required>
                <input type="text" name="nickname" placeholder="별명" required>
                <input type="text" name="cellphoneNum" placeholder="전화번호" required>
                <input type="text" name="email" placeholder="이메일" required>
                <input type="submit" value="가입">
            </form>
        </div>

</body>
</html>