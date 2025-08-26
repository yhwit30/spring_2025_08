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

		$.get('../article/hitCount', {
			id : $id,
			ajaxMode : 'Y'
		}, function(data) {
			$('.article-detail__hit-count').html(data.data1);
		}, 'json');
	}

	$(function() {
		<!--
		ArticleDetail__doIncreaseHitCount();
		-->
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
			},
			error : function(jqXHR, textStatus, errorThorwn){
				console.log('좋아요 오류 발생 : ' + textStatus);
			}
		});
		
	}

	function doBadReaction(articleId) {

		
	}

	$(function() {
		checkRP();
	});
</script>


<h1>ARTICLE DETAIL</h1>


<section class="mt-8 text-xl px-4">
	<table class="table" cellpadding="5" style="width: 100%;">
		<tbody>
			<tr>
				<th>ID</th>
				<td>${article.id }</td>
			</tr>
			<tr>
				<th>Registration Date</th>
				<td>${article.regDate }</td>
			</tr>
			<c:if test="${article.regDate != article.updateDate }">
				<tr>
					<th>Update Date</th>
					<td>${article.updateDate }</td>
				</tr>
			</c:if>
			<tr>
				<th>Title</th>
				<td>${article.title }</td>
			</tr>
			<tr>
				<th>Body</th>
				<td>${article.body }</td>
			</tr>
			<tr>
				<th>Writer</th>
				<td>${article.extra__writer }</td>
			</tr>
			<tr>
				<th>Views</th>
				<td>${article.hitCount }</td>
			</tr>
			<tr>
				<th>Views(Ajax)</th>
				<td>
					<span class="article-detail__hit-count">${article.hitCount }</span>
				</td>
			</tr>
			<tr>
				<th>LIKE / DISLIKE</th>
				<td>
					<!-- <a href="/usr/reactionPoint/doGoodReaction?relTypeCode=article&relId=${article.id }&replaceUri=${rq.getCurrentUri()}" class="btn btn-sm btn-primary">LIKE ${article.goodReactionPoint }</a> -->
					<button id="likeButton" class="btn btn-sm btn-primary btn-outline" onclick="doGoodReaction(${param.id})">LIKE ${article.goodReactionPoint }</button>
					<!-- <a href="/usr/reactionPoint/doBadReaction?relTypeCode=article&relId=${article.id }&replaceUri=${rq.getCurrentUri()}"  class="btn btn-sm btn-error">DISLIKE ${article.badReactionPoint }</a> -->
					<button id="dislikeButton" class="btn btn-sm btn-error btn-outline" onclick="doBadReaction(${param.id})">DISLIKE ${article.badReactionPoint }</button>
				</td>
			</tr>
		</tbody>
	</table>

	<div class="btns mt-10">
		<button onclick="history.back()" class="btn btn-info">뒤로가기</button>
		<c:if test="${article.userCanModify }">
			<a class="btn btn-info" href="modify?id=${article.id }">수정</a>
		</c:if>
		<c:if test="${article.userCanDelete }">
			<a class="btn btn-info" href="doDelete?id=${article.id }">삭제</a>
		</c:if>
	</div>



</section>

<%@ include file="../common/foot.jspf"%>