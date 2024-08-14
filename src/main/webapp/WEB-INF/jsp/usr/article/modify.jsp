<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>게시글 수정</title>
</head>
<%@ include file="../common/head.jspf"%>
<body>
    <h2>게시글 수정</h2>
    <form action="/usr/article/doModify" method="post">
    	글번호 : <input type="text" name="id" value="${article.id }" readonly /><br />
    	
        제목: <input type="text" name="title" value="${article.title }" /><br />
        내용:<br />
        <textarea name="body" rows="10" cols="50" >${article.body }</textarea><br />
        <input class="btn" type="submit" value="수정" />
    </form>
</body>
</html>