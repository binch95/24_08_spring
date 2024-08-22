<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="DETAIL"></c:set>
<%@ include file="../common/head.jspf"%>
<hr />

<script>
	const params = {};
	params.id = parseInt('${param.id}');
</script>

<script>
	function ArticleDetail__doIncreaseHitCount() {
		$.get('../article/doIncreaseHitCountRd', {
			id : params.id,
			ajaxMode : 'Y'
		}, function(data) {
			console.log(data);
			console.log(data.data1);
			$('.article-detail__hit-count').empty().html(data.data1);
		}, 'json');

	};

	$(function() {
		ArticleDetail__doIncreaseHitCount();
		// setTimeout(ArticleDetail__doIncreaseHitCount, 2000);
	});

	$(function() {
		$('.like-button').click(function() {
			if ($(this).hasClass('clickOn')) {
				$(this).removeClass('clickOn');
				params.updown = -1;
			} else if ($('.doNotlike-button').hasClass('clickOn')) {
				params.updown = 2;
				$(this).addClass('clickOn');
			} else {
				params.updown = 1;
				$(this).addClass('clickOn');
			}
			
			$('.doNotlike-button').removeClass('clickOn');
			likeUpDown();
		});
		$('.doNotlike-button').click(function() {
			if ($(this).hasClass('clickOn')) {
				params.updown = 1;
				$(this).removeClass('clickOn');
			} else if ($('.like-button').hasClass('clickOn')) {
				params.updown = -2;
				$(this).addClass('clickOn');
			} else {
				params.updown = -1;
				$(this).addClass('clickOn');
			}
			
			$('.like-button').removeClass('clickOn');
			likeUpDown();
		});
	});
	function likeUpDown() {
		$.get('../article/doInOutLikeCountRd', {
			id : params.id,
			upAnddown : params.updown,
			ajaxMode : 'Y'
		}, function(data) {
			console.log(data);
			console.log(data.data1);
			$('.article_like-count').empty().html(data.data1);
		}, 'json');
	};
</script>


<section class="mt-24 text-xl px-4">
	<div class="mx-auto">
		<table class="table" border="1" cellspacing="0" cellpadding="5" style="width: 100%; border-collapse: collapse;">
			<tbody>
				<tr>
					<th style="text-align: center;">ID</th>
					<td style="text-align: center;">${article.id}</td>
				</tr>
				<tr>
					<th style="text-align: center;">Registration Date</th>
					<td style="text-align: center;">${article.regDate.substring(0,10)}</td>
				</tr>
				<tr>
					<th style="text-align: center;">Modified date</th>
					<td style="text-align: center;">${article.updateDate}</td>
				</tr>
				<tr>
					<th style="text-align: center;">BoardId</th>
					<td style="text-align: center;">${article.boardId}</td>
				</tr>
				<tr>
					<th style="text-align: center;">Writer</th>
					<td style="text-align: center;">${article.extra__writer}</td>
				</tr>
				<tr>
					<th style="text-align: center;">Title</th>
					<td style="text-align: center;">${article.title}</td>
				</tr>
				<tr>
					<th style="text-align: center;">Body</th>
					<td style="text-align: center;">${article.body}</td>
				</tr>
				<tr>
					<th style="text-align: center;">Views</th>
					<td style="text-align: center;"><span class="article-detail__hit-count">${article.view }</span></td>
				</tr>
				<tr>
					<th style="text-align: center;">Like</th>
					<td style="text-align: center;"><span class="article_like-count">${article.like }</span></td>
				</tr>

			</tbody>
		</table>
		<div class="btns">
			<button class="btn" type="button" onclick="history.back()">뒤로가기</button>
			<c:if test="${article.userCanModify }">
				<a class="btn" href="../article/modify?id=${article.id }">수정</a>
			</c:if>
			<c:if test="${article.userCanDelete }">
				<a class="btn" href="../article/doDelete?id=${article.id }">삭제</a>
			</c:if>

			<div class="btn like-button">좋아요</div>
			<div class="btn doNotlike-button">싫어요</div>
		</div>
	</div>
</section>
<section>
<div class="container">

d
</div>

</section>

<%@ include file="../common/foot.jspf"%>
