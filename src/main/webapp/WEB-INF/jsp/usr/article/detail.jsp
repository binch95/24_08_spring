<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="DETAIL"></c:set>
<%@ include file="../common/head.jspf"%>
<hr />

<style>
.table.comment {
  width: 100%;
  border-collapse: collapse;
}

/* 작성자 열: 좁게 설정 */
.table.comment .author-column {
  width: 10%; /* 작성자 열의 너비를 15%로 설정 */
  text-align: center;
}

/* 내용 열: 넓게 설정 */
.table.comment .content-column {
  width: 55%; /* 내용 열의 너비를 55%로 설정 */
  text-align: left;
}

/* 수정 열: 최소한의 너비로 설정 */
.table.comment .edit-column,
.table.comment .delete-column {
  width: 5%; /* 수정 및 삭제 열의 너비를 15%로 설정 */
  text-align: center;
}

/* 테이블의 기본 스타일 */
.table.comment th,
.table.comment td {
  padding: 5px;
}

/* 테이블 헤더의 스타일 */
.table.comment th {
  background-color: #f2f2f2;
}
.pointer{
cursor: pointer; 
}
</style>

<script>
	const params = {};
	params.id = parseInt('${param.id}');
	editCommentId = -1;
</script>

<script>

	function Comment__article_Edit(commentId) {
		$('.comment__list' + commentId).addClass('hidden');
		$('.edit__ing' + commentId).removeClass('hidden');
	}
	
	function Comment__article_DoEdit(commentId) {
	    var commentBody = document.getElementsByName('commentEdit' + commentId)[0].value

	    $('.comment__list' + commentId).removeClass('hidden');
		$('.edit__ing' + commentId).addClass('hidden');
		$.get('../comment/doUpdateArticleComment', {
			id : params.id,
			commentId : commentId,
			commentBody : commentBody,
			ajaxMode : 'Y'
		}, function(data){
			console.log(data);
			console.log(data.data1);
			$('.body-column' + commentId).empty().html(data.data1);
		}, 'json');
	};

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
		userCanReaction = $
		{
			userCanReaction
		}
		;
		$('.like-button').click(function() {
			if (userCanReaction == -2) {
				alert("이용할수 없습니다 로그인 해주세요");
				location.href = '../member/login';
				return false;
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

				if ($('.like-button').hasClass('clickOn')) {
					$('.like-button').removeClass('clickOn');
				}
				if (!data.fail) {
					$('.like-button').addClass('clickOn');
				}
				$('.goodReactionPoint').empty().html(data.data1);
			}, 'json');
		});

		$('.doNotlike-button').click(function() {
			if (userCanReaction == -2) {
				alert("이용할수 없습니다 로그인 해주세요");
				location.href = '../member/login'
				return false;
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
				console.log(data.data1Name);
				if ($('.doNotlike-button').hasClass('clickOn')) {
					$('.doNotlike-button').removeClass('clickOn');
				}
				if (!data.fail) {
					$('.doNotlike-button').addClass('clickOn');
				}
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
					<td style="text-align: center;" class="badReactionPoint">${article.badReactionPoint }</td>
				</tr>
				<tr>
					<th style="text-align: center;">LIKE / Dislike</th>
					<td style="text-align: center;">
						<button class="btn btn-outline btn-success like-button">LIKE 👍 ${article.goodReactionPoint }</button>
						<button class="btn btn-outline btn-error doNotlike-button">DISLIKE 👎 ${article.badReactionPoint }</button>
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
	<div class="container">
		<h2>댓글 ${comments.size() }개</h2>
		<table class="table comment" border="1" cellspacing="0" cellpadding="5"
			style="width: 100%; border-collapse: collapse;">
			<thead>
				<tr class="border-solid">
					<th class="author-column">작성자</th>
					<th class="content-column">내용</th>
					<th class="edit-column">Edit</th>
					<th class="delete-column">Delete</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="comment" items="${comments}">
			

					<tr class="comment__list${comment.id }">
						<td class="author-column">${comment.nickname }</td>
						<td class="body-column${comment.id }">${comment.body }</td>
						<c:if test="${loginedMemberId == comment.memberId}">
						<td class="edit-column"><nav class="pointer" onclick='Comment__article_Edit(${comment.id })'>수정</nav></td>
						<td class="delete-column"><a href="../comment/dodelete?id=${comment.id }">삭제</a></td>
						</c:if>
						</tr>

					<tr class="edit__ing${comment.id } hidden">
					<from id="commentUpdate${comment.id}" action="../comment/doUpdateArticleComment" method="POST">
					<td class="author-column">${comment.nickname }</td>
				<td> <input	class="input input-bordered input-primary input-sm w-full max-w-4xl" name="commentEdit${comment.id }" autocomplete="off"
				type="text" value="${comment.body }" /></td>
			<td><button class="h-8 rounded border-black border-solid" onclick='Comment__article_DoEdit(${comment.id })'>댓글 수정</button></td>
			</from>
</tr>
				
				</c:forEach>
			</tbody>
		</table>
		<form action="../comment/docomment" method="POST">
			<input type="hidden" name="id" value="${article.id }"> <input
				class="input input-bordered input-primary input-sm w-full max-w-5xl" name="commentInput" autocomplete="off"
				type="text" placeholder="댓글을 입력하세요..." />
			<button class="h-8 rounded border-black border-solid">댓글 작성</button>
		</form>
	</div>

</section>

<%@ include file="../common/foot.jspf"%>
