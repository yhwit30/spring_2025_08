<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="ARTICLE LIST"></c:set>
<%@ include file="../common/head.jspf"%>

<h1>ARTICLE LIST</h1>

<div>게시판 : ${board != null ? board.code : '전체 게시판'}</div>
<div>게시글 : ${articlesCount }</div>

<section class="mt-8 text-xl px-4">
	<table class="table" cellpadding="5">
		<thead>
			<tr>
				<th>ID</th>
				<th>Registration Date</th>
				<th>Title</th>
				<th>Member ID</th>
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
					<td>${article.memberId }</td>
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
			<c:set var="paginationLen" value="3"></c:set>
			<c:set var="startPage" value="${page - paginationLen >= 1 ? page - paginationLen : 1}"></c:set>
			<c:set var="endPage" value="${page + paginationLen <= pagesCount ? page + paginationLen : pagesCount }"></c:set>

			<c:if test="${startPage > 1 }">
				<a href="?boardId=${boardId }&page=1">1</a>
			</c:if>

			<c:if test="${startPage > 2 }">
				<button>...</button>
			</c:if>

			<c:forEach begin="${startPage }" end="${endPage }" var="i">
				<a class="${param.page== i ? 'text-blue-700' : '' }" href="?boardId=${boardId }&page=${i }">${i}</a>
			</c:forEach>

			<c:if test="${endPage < (pagesCount - 1) }">
				<button>...</button>
			</c:if>
			<c:if test="${endPage < pagesCount}">
				<a href="?boardId=${boardId }&page=${pagesCount}">${pagesCount }</a>
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