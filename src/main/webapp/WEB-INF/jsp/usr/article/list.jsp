<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="${board.code } LIST"></c:set>
<%@ include file="../common/head.jspf"%>
<hr />
			<div class="searchForm text-right mr-9 mt-9">
		<form action="/usr/article/list?boardId=${param.boardId }" method="post">
		<select name="searchSelect" >
			<option value="select" selected disabled hidden>Select option</option>
			<option value="title">title</option>
			<option value="body">body</option>
			<option value="nickname">extra__writer</option>
			<option value="name">name</option>
			<option value="id">id</option>
			
		</select>
		<input type="search" autocomplete="off" name="searchKeyword" class="input input-bordered input-primary input-sm w-full max-w-xs" value="${param.searchKeyword }">
		<input type="submit" value="검색">
		</form>
		</div>
<section class="mt-24 text-xl px-4">
	<div class="mx-auto">

		<div>${articlesCount } 개</div>
		<table class="table" border="1" cellspacing="0" cellpadding="5" style="width: 100%; border-collapse: collapse;">

			<thead>
				<tr>
					<th style="text-align: center;">ID</th>
					<th style="text-align: center;">Registration Date</th>
					<th style="text-align: center;">Title</th>
					<th style="text-align: center;">Writer</th>
					<th style="text-align: center;">LikeCount</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="article" items="${articles}">
					<tr class="hover">
						<td style="text-align: center;">${article.id}</td>
						<td style="text-align: center;">${article.regDate.substring(0,10)}</td>
						<td style="text-align: center;"><a class="hover:underline" href="detail?id=${article.id}">${article.title}  (${comments[article.id-1].viewNumber })</a></td>
						<td style="text-align: center;">${article.extra__writer}</td>
						<td style="text-align: center;"> <span class="like-count">${article.goodReactionPoint + article.badReactionPoint * -1}</span></td>
					</tr>
				</c:forEach>

				<c:if test="${empty articles}">
					<tr>
						<td colspan="4" style="text-align: center;">게시글이 없습니다</td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>
</section>

<%@ include file="../common/foot.jspf"%>
