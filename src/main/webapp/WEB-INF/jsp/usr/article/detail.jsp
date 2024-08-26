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
		userCanReaction = ${userCanReaction};
		$('.like-button').click(function() {
			if(userCanReaction == -2){
				alert("이용할수 없습니다 로그인 해주세요");
				return false;
			}
			if ($(this).hasClass('clickOn')) {
				$(this).removeClass('clickOn');
			} else {
				$(this).addClass('clickOn');
			}
			params.updown = 1;
			$('.doNotlike-button').removeClass('clickOn');
						
			$.get('../article/doInOutLikeCountRd', {
				id : params.id,
				upAnddown : 1,
				ajaxMode : 'Y'
			}, function(data) {
				console.log(data);
				console.log(data.data1);
				$('.goodReactionPoint').empty().html(data.data1);
			}, 'json');
		});
		
		$('.doNotlike-button').click(function() {
			if(userCanReaction == -2){
				alert("이용할수 없습니다 로그인 해주세요");
				return false;
			}
			
			if ($(this).hasClass('clickOn')) {
				$(this).removeClass('clickOn');
			} else {
				
				$(this).addClass('clickOn');
			}
			params.updown = -1;
			$('.like-button').removeClass('clickOn');
			

			$.get('../article/doInOutLikeCountRd', {
				id : params.id,
				upAnddown : -1,
				ajaxMode : 'Y'
			}, function(data) {
				console.log(data);
				console.log(data.data1);
				$('.badReactionPoint').empty().html(data.data1 * -1);
			}, 'json');
		});
	});
	
	
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
					<th style="text-align: center;">Like</th>
					<td style="text-align: center;" class="goodReactionPoint">${article.goodReactionPoint }</td>
				</tr>
				<tr>
					<th style="text-align: center;">Dislike</th>
					<td style="text-align: center;"class="badReactionPoint">${article.badReactionPoint }</td>
				</tr>
				<tr>
					<th style="text-align: center;">LIKE / Dislike</th>
					<td style="text-align: center;">
						<button class="btn btn-outline btn-success like-button"> LIKE 👍 ${article.goodReactionPoint }</button>
						<button class="btn btn-outline btn-error doNotlike-button"> DISLIKE 👎 ${article.badReactionPoint }</button>
					</td>
				</tr>
				
				<tr>
					<th style="text-align: center;">Views</th>
					<td style="text-align: center;"><span class="article-detail__hit-count">${article.view }</span></td>
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
		</div>
	</div>
</section>
<section>
	<div class="container">d</div>

</section>

<%@ include file="../common/foot.jspf"%>
