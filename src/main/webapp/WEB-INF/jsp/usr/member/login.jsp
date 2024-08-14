<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인</title>
     <style>

    </style>
</head>
<body>

<%@ include file="../common/head.jspf"%>        
        <!-- 로그인 폼 -->
        <div class="login-form">
            <h2>로그인</h2>
            <form action="/usr/member/doLogin" method="post">
                <input type="text" autocomplete="off" name="loginId" placeholder="아이디" required>
                <input type="password" autocomplete="off" name="loginPw" placeholder="비밀번호" required>
                <input type="submit" value="로그인">
            </form>
        </div>

</body>
</html>