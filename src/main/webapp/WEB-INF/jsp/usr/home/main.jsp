<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="MAIN"></c:set>
<%@ include file="../common/head.jspf"%>

<hr />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home - 게시판</title>
    <style>
        .grid-container {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 20px;
        }
        .section {
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        .section h2 {
            margin-top: 0;
        }
        .post {
            display: flex;
            align-items: center;
            margin-bottom: 15px;
        }
        .post img {
            width: 60px;
            height: 60px;
            border-radius: 5px;
            margin-right: 15px;
        }
        .post-content {
            display: flex;
            flex-direction: column;
        }
        .post-title {
            font-size: 16px;
            margin: 0;
        }
        .post-info {
            font-size: 12px;
            color: #777;
        }
        .view-more {
            display: block;
            text-align: center;
            margin-top: 20px;
            color: #007BFF;
            text-decoration: none;
        }
        .view-more:hover {
            text-decoration: underline;
        }
        /* 반응형 디자인 */
        @media (max-width: 768px) {
            .grid-container {
                grid-template-columns: 1fr;
            }
        }
    </style>
</head>
<body>


        <div class="grid-container">
            
            <!-- 최근 게시물 섹션 -->
            <div class="section">
                <h2>최근 게시물</h2>
				<c:forEach var="article" items="${articles}">
                	<div class="post">
                    	<img src="https://via.placeholder.com/60" alt="Post Thumbnail">
                    	<div class="post-content">
                       		 <p class="post-title">${article.title }</p>
                       		 <p class="post-info">${member.name }</p>
               		</div>
                </div>
				</c:forEach>

                <a href="../article/list" class="view-more">더보기</a>
            </div>

            <!-- 인기 게시물 섹션 -->
            <div class="section">
                <h2>인기 게시물</h2>

                <div class="post">
                    <img src="https://via.placeholder.com/60" alt="Post Thumbnail">
                    <div class="post-content">
                        <p class="post-title">인기 게시물 제목 1</p>
                        <p class="post-info">조회수: 150 | 좋아요: 30</p>
                    </div>
                </div>

                <a href="../article/list" class="view-more">더보기</a>
            </div>

        </div>


</body>