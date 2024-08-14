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

    </style>
</head>
<body>


        
<%@ include file="../common/head.jspf"%>        
        
        <div class="board-container">
        <div class="header">
            <h1>게시판</h1>
            </div>
           
            <a href="write" class="btn">글쓰기</a>
            
            <table>
                <thead>
                    <tr>
                    <th>id</th>
                    <th>title</th>
                    <th>body</th>

                </tr>
            </thead>
            <tbody>
			<c:forEach var="article" items="${articles}">
			<tr>
				<td class="gitar">${article.id }</td>
				<td><a href="getArticle?id=${article.id }">${article.title }</a></td>
				<td>${article.body }</td>
			</tr>
		</c:forEach>
                <!-- 여기에 더 많은 글을 추가할 수 있습니다 -->
            </tbody>
        </table>
    </div>
</body>
</html>