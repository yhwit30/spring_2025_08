<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="ARTICLE DETAIL"></c:set>
<%@ include file="../common/head.jspf"%>

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