<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="ARTICLE LIST"></c:set>
<%@ include file="../common/head.jspf"%>

<h1>ARTICLE LIST</h1>

<div>게시판 : ${board.code}</div>

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
						<td><a class="hover:underline" href="detail?id=${article.id }">${article.title }</a></td>
						<td>${article.memberId }</td>
					</tr>

				</c:forEach>

			</tbody>
		</table>
</section>

<%@ include file="../common/foot.jspf"%>