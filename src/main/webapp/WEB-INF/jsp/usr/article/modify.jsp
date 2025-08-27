<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="ARTICLE MODIFY"></c:set>
<%@ include file="../common/head.jspf"%>

<h1>ARTICLE MODIFY</h1>


<section class="mt-8 text-xl px-4">
	<div>
		<form action="doModify" method="post">
			<input type="hidden" name="id" value="${article.id }" />
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
						<th>Update Date</th>
						<td>${article.updateDate }</td>
					</tr>
					<tr>
						<th>Writer</th>
						<td>${article.extra__writer }</td>
					</tr>
					<tr>
						<th>Title</th>
						<td>
							<input class="input input-primary input-sm" type="text" placeholder="새 제목" value="${article.title }" name="title" required="required"
								autocomplete="off" />
						</td>
					</tr>
					<tr>
						<th>Body</th>
						<td>
							<input class="input input-primary input-sm" type="text" placeholder="새 내용" value="${article.body }" name="body"
								required="required" autocomplete="off" />
						</td>
					</tr>
					<tr>
						<td>
							<button class="btn btn-info">수정</button>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	<div class="btns mt-10">
		<button class="btn btn-info" type="button" onclick="history.back()" >뒤로가기</button>
		<c:if test="${article.userCanDelete }">
			<a class="btn btn-outline" href="doDelete?id=${article.id }" onclick="return confirm('정말 삭제하시겠습니까?');">삭제</a>
		</c:if>
	</div>



</section>

<%@ include file="../common/foot.jspf"%>