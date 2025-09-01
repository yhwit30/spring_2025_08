<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="ARTICLE LIST"></c:set>
<%@ include file="../common/head.jspf"%>

<h1>ARTICLE LIST</h1>

<div>게시판 : ${board != null ? board.code : '전체 게시판'}</div>
<div>게시글 : ${articlesCount }</div>

<section class="mt-8 text-xl px-4">

	<div class="flex justify-end">
		<div class="searchBox">
			<form action="">
				<input type="hidden" name="boardId" value="${boardId }" />

				<div class="flex">
					<select class="select select-sm select-bordered max-w-xs" name="searchKeywordTypeCode" data-value=${param.searchKeywordTypeCode }>
						<option value="title">제목</option>
						<option value="body">내용</option>
						<option value="title,body">제목 + 내용</option>
						<option value="nickname">작성자</option>

					</select>

					<label class="ml-3 input input-bordered input-sm flex items-center gap-2">
						<input type="text" placeholder="Search" name="searchKeyword" value="${param.searchKeyword }" />
						<button type="submit">
							<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16" fill="currentColor" class="h-4 w-4 opacity-70">
    <path fill-rule="evenodd" d="M9.965 11.026a5 5 0 1 1 1.06-1.06l2.755 2.754a.75.75 0 1 1-1.06 1.06l-2.755-2.754ZM10.5 7a3.5 3.5 0 1 1-7 0 3.5 3.5 0 0 1 7 0Z" clip-rule="evenodd" />
  </svg>
						</button>
					</label>
				</div>
			</form>
		</div>
	</div>

	<table class="table" cellpadding="5">
		<thead>
			<tr>
				<th>ID</th>
				<th>Registration Date</th>
				<th>Title</th>
				<th>Writer</th>
				<th>Views</th>
				<th>likeRP</th>
				<th>dislikeRP</th>
				<th>replyCount</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach var="article" items="${articles }">
				<tr class="hover:bg-base-300">
					<td>${article.id }</td>
					<td>${article.regDate }</td>
					<td>
						<a class="hover:underline" href="detail?id=${article.id }">${article.title }</a>
					</td>
					<td>${article.extra__writer }</td>
					<td>${article.hitCount }</td>
					<td>${article.goodReactionPoint }</td>
					<td>${article.badReactionPoint }</td>
					<td>${article.replyCount }</td>
				</tr>

			</c:forEach>

			<c:if test="${empty articles }">
				<tr>
					<td colspan="4" style="text-align: center;">게시글이 없습니다.</td>
				</tr>
			</c:if>

		</tbody>
	</table>

	<!-- 동적 페이징 -->
	<div class="flex justify-center mt-4">
		<div class="page-group">
			<c:set var="paginationLen" value="3" />
			<c:set var="startPage" value="${page - paginationLen >= 1 ? page - paginationLen : 1}" />
			<c:set var="endPage" value="${page + paginationLen <= pagesCount ? page + paginationLen : pagesCount }" />

			<c:set var="baseUri" value="?boardId=${boardId }" />
			<c:set var="baseUri" value="${baseUri }&searchKeywordTypeCode=${searchKeywordTypeCode }" />
			<c:set var="baseUri" value="${baseUri }&searchKeyword=${searchKeyword }" />


			<c:if test="${startPage > 1 }">
				<a href="${baseUri }&page=1">1</a>
			</c:if>

			<c:if test="${startPage > 2 }">
				<button>...</button>
			</c:if>

			<c:forEach begin="${startPage }" end="${endPage }" var="i">
				<a class="${param.page== i ? 'text-blue-700' : '' }" href="${baseUri }&page=${i }">${i}</a>
			</c:forEach>

			<c:if test="${endPage < (pagesCount - 1) }">
				<button>...</button>
			</c:if>
			<c:if test="${endPage < pagesCount}">
				<a href="${baseUri }&page=${pagesCount}">${pagesCount }</a>
			</c:if>

		</div>
	</div>

	<!-- 정적 페이징
	<div class="flex justify-center mt-4">
		<div class="page-group">
			<c:forEach begin="1" end="${pagesCount }" var="i">
				<a class="${param.page== i ? 'text-blue-700' : '' }" href="?boardId=${boardId }&page=${i }">${i}</a>
			</c:forEach>
		</div>
	</div> -->


</section>

<%@ include file="../common/foot.jspf"%>