<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시판</title>
     <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 1000px;
            margin: 50px auto;
            background-color: #fff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
        }
        .header {
            text-align: center;
            margin-bottom: 40px;
        }
        .header h1 {
            margin: 0;
            color: #333;
        }
        .login-form {
            width: 300px;
            margin: 0 auto 40px auto;
        }
        .login-form input[type="text"],
        .login-form input[type="password"] {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        .login-form input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .login-form input[type="submit"]:hover {
            background-color: #0056b3;
        }
        .board-container {
            margin-top: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 10px;
            text-align: center;
        }
        th {
            background-color: #f4f4f4;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
                .gitar{
        	width: 5%;
        }
        .btn {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            color: #fff;
            background-color: #28a745;
            text-decoration: none;
            border-radius: 5px;
        }
        .btn:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>

    <div class="container">
        
<%@ include file="../common/head.jspf"%>        
        
        <div class="board-container">
        <div class="header">
            <h1>게시판</h1>
            </div>
            
            <a href="#" class="btn">글쓰기</a>
            <table>
                <thead>
                    <tr>
                    <th>id</th>
                    <th>title</th>
                    <th>body</th>
                    <th>수정</th>
                    <th>삭제</th>
                </tr>
            </thead>
            <tbody>
			<c:forEach var="article" items="${articles}">
			<tr>
				<td class="gitar">${article.id }</td>
				<td><a href="getArticle?id=${article.id }">${article.title }</a></td>
				<td>${article.body }</td>
				<td class="gitar" ><a href="#">수정</a></td>
				<td class="gitar" ><a href="doDelete?id=${article.id }">삭제</a></td>
			</tr>
		</c:forEach>
                <!-- 여기에 더 많은 글을 추가할 수 있습니다 -->
            </tbody>
        </table>
    </div>
</body>
</html>