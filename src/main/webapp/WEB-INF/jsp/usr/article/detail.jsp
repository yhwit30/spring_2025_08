<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="ARTICLE DETAIL"></c:set>
<%@ include file="../common/head.jspf"%>

<script>
	let $id = parseInt('${param.id}');
	//console.log('$id : ' + $id);

	let isAlreadyAddGoodRp = ${isAlreadyAddGoodRp};
	let isAlreadyAddBadRp = ${isAlreadyAddBadRp};
	
</script>

<script>
	function ArticleDetail__doIncreaseHitCount() {
		
		const localStorageKey = 'article__' + $id + '__alreadyViewed';
		console.log(localStorageKey);
		
		if (localStorage.getItem(localStorageKey)){
			return;
		}
		localStorage.setItem(localStorageKey, true);
		

		$.get('../article/hitCount', {
			id : $id,
			ajaxMode : 'Y'
		}, function(data) {
			$('.article-detail__hit-count').html(data.data1);
		}, 'json');
	}

	$(function() {
		// ArticleDetail__doIncreaseHitCount();
		setTimeout(ArticleDetail__doIncreaseHitCount, 2000);
	})
</script>

<script>
	function checkRP() {
		if (isAlreadyAddGoodRp == true) {
			$('#likeButton').toggleClass('btn-outline');
		} else if (isAlreadyAddBadRp == true) {
			$('#dislikeButton').toggleClass('btn-outline');
		} else {
			return;
		}
	}

	function doGoodReaction(articleId) {
		
		if(${!isLogined}){ //로그인 안했어 
			alert('로그인 하고 오세요');
			location.replace('/usr/member/login');
			return;
		}

		$.ajax({
			url : '/usr/reactionPoint/doGoodReaction',
			type : 'POST',
			data : {
				relTypeCode : 'article', 
				relId : articleId
			},
			dataType : 'json',
			success : function(data){
				console.log(data);
				console.log("data1 : " + data.data1);
				console.log("data1Name : " + data.data1Name);
				console.log("data2 : " + data.data2);
				console.log("data2Name : " + data.data2Name);
				
				//좋아요, 싫어요 개수 갱신
				$('.likeCount').text(data.data1);
				$('.dislikeCount').text(data.data2);
				//좋아요 취소
				if(data.resultCode == 'S-1'){ 
					$('#likeButton').toggleClass('btn-outline');
				}
				//좋아요
				else if(data.resultCode == 'S-2'){ 
					$('#likeButton').toggleClass('btn-outline');
				}
				else if(data.resultCode == 'S-3'){
					$('#dislikeButton').toggleClass('btn-outline');
					$('#likeButton').toggleClass('btn-outline');
				}
				else{
					alert(data.msg);
				}
				
			},
			error : function(jqXHR, textStatus, errorThorwn){
				console.log('좋아요 오류 발생 : ' + textStatus);
			}
		});
	}

	function doBadReaction(articleId) {

		if(${!isLogined}){ //로그인 안했어 
			alert('로그인 하고 오세요');
			location.replace('/usr/member/login');
			return;
		}
		
		$.ajax({
			url : '/usr/reactionPoint/doBadReaction',
			type : 'POST',
			data : {
				relTypeCode : 'article', 
				relId : articleId
			},
			dataType : 'json',
			success : function(data){
				console.log(data);
				console.log("data1 : " + data.data1);
				console.log("data1Name : " + data.data1Name);
				console.log("data2 : " + data.data2);
				console.log("data2Name : " + data.data2Name);
				
				//좋아요, 싫어요 개수 갱신
				$('.likeCount').text(data.data1);
				$('.dislikeCount').text(data.data2);
				//싫어요 취소
				if(data.resultCode == 'S-1'){ 
					$('#dislikeButton').toggleClass('btn-outline');
				}
				//싫어요
				else if(data.resultCode == 'S-2'){ 
					$('#dislikeButton').toggleClass('btn-outline');
				}
				else if(data.resultCode == 'S-3'){
					$('#dislikeButton').toggleClass('btn-outline');
					$('#likeButton').toggleClass('btn-outline');
				}
				else{
					alert(data.msg);
				}
				
			},
			error : function(jqXHR, textStatus, errorThorwn){
				console.log('싫어요 오류 발생 : ' + textStatus);
			}
		});
		
	}

	$(function() {
		checkRP();
	});
</script>


<h1>ARTICLE DETAIL</h1>

<!-- ai 활용한 테이블 ui -->
<section class="mt-8 px-4">
	<div class="card bg-base-100 shadow-xl">
		<div class="card-body">
			<h2 class="card-title text-2xl font-bold mb-4">${article.title}</h2>

			<div class="overflow-x-auto">
				<table class="table w-full">
					<tbody>
						<tr>
							<th>ID</th>
							<td>${article.id}</td>
						</tr>
						<tr>
							<th>Registration Date</th>
							<td>${article.regDate}</td>
						</tr>
						<c:if test="${article.regDate != article.updateDate}">
							<tr>
								<th>Update Date</th>
								<td>${article.updateDate}</td>
							</tr>
						</c:if>
						<tr>
							<th>Body</th>
							<td class="whitespace-pre-line">${article.body}</td>
						</tr>
						<tr>
							<th>Writer</th>
							<td>${article.extra__writer}</td>
						</tr>
						<tr>
							<th>Views</th>
							<td>
								<span class="article-detail__hit-count">${article.hitCount}</span>
							</td>
						</tr>
						<tr>
							<th>LIKE / DISLIKE</th>
							<td class="flex gap-4">
								<button id="likeButton" class="btn btn-sm btn-outline btn-primary" onclick="doGoodReaction(${param.id})">
									👍 LIKE
									<span class="likeCount ml-1">${article.goodReactionPoint}</span>
								</button>
								<button id="dislikeButton" class="btn btn-sm btn-outline btn-error" onclick="doBadReaction(${param.id})">
									👎 DISLIKE
									<span class="dislikeCount ml-1">${article.badReactionPoint}</span>
								</button>
							</td>
						</tr>
					</tbody>
				</table>
			</div>

			<div class="card-actions justify-end mt-6">
				<button onclick="history.back()" class="btn btn-info">뒤로가기</button>
				<c:if test="${article.userCanModify}">
					<a class="btn btn-warning" href="modify?id=${article.id}">수정</a>
				</c:if>
				<c:if test="${article.userCanDelete}">
					<a class="btn btn-error" href="doDelete?id=${article.id}" onclick="return confirm('정말 삭제하시겠습니까?');">삭제</a>
				</c:if>
			</div>
		</div>
	</div>
</section>

<script>
function replyWrite__submit(form){
	console.log('form.body.value : ', form.body.value);
	
	if(form.body.value.length < 3){
		alert("3글자 이상 입력하시오.");
		form.body.focus();
		return;
	}
	
	form.submit();
}
</script>

<!-- 댓글 작성란 -->
<section>
	<c:if test="${rq.isLogined() }">
		<form action="../reply/doWrite" method="POST" onsubmit="replyWrite__submit(this); return false;">
			<input type="hidden" name="relTypeCode" value="article" />
			<input type="hidden" name="relId" value="${article.id}" />
			<table class="table" border="1" style="width: 100%; border-collapse: collapse;">

				<tbody>
					<tr>
						<th>댓글 입력</th>
						<td>
							<textarea class="input input-bordered input-sm w-full max-w-xs" name="body" autocomplete="off" type="text" placeholder="댓글 입력란"></textarea>
						</td>
						<td>
							<button class="btn btn-outline btn-sm">작성</button>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</c:if>
	<c:if test="${!rq.isLogined() }">
		<div style="text-align: center; margin-top: 20px;">
			댓글 작성을 하려면
			<a href="../member/login" class="btn btn-primary btn-xs">로그인</a>
			이 필요합니다.
		</div>
	</c:if>

</section>
<!-- ai 활용한 댓글 목록 ui -->
<div class="space-y-4 mt-6">
	<c:forEach var="reply" items="${replies}">
		<div class="chat chat-start">
			<div class="chat-image avatar">
				<div class="w-10 rounded-full">
					<img src="https://api.dicebear.com/6.x/thumbs/svg?seed=${reply.extra__writer}" alt="user" />
				</div>
			</div>
			<div class="chat-header">
				${reply.extra__writer}
				<time class="text-xs opacity-50"> · ${reply.regDate}</time>
			</div>
			<div class="chat-bubble">${reply.body}</div>
			<div class="chat-footer opacity-50 text-sm flex gap-4">👍 ${reply.goodReactionPoint} 👎 ${reply.badReactionPoint}</div>
		</div>
	</c:forEach>
	<c:if test="${empty replies }">
		<div style="text-align: center; margin-top: 30px; margin-bottom: 30px;">첫 번째 댓글의 주인공이 되어보세요.</div>
	</c:if>
</div>










<%@ include file="../common/foot.jspf"%>