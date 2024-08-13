<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>게시글 작성</title>
</head>

<body>
    <h2>게시글 작성</h2>
    <form action="/usr/article/doWrite" method="post">
        제목: <input type="text" name="title" /><br />
        내용:<br />
        <textarea name="body" rows="10" cols="50"></textarea><br />
        <input type="submit" value="작성" />
    </form>
</body>
</html>