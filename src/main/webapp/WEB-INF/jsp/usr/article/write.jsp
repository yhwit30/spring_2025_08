<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="ARTICLE WRITE"></c:set>
<%@ include file="../common/head.jspf"%>

<h1>ARTICLE WRITE</h1>


<section class="mt-8 text-xl px-4">
	<div>
		<form action="doWrite" method="post">
			<table class="mx-auto" cellpadding="5" style="width: 100%;">
				<tbody>
					<tr>
						<th>Title</th>
						<td>
							<input type="text" placeholder="새 제목" name="title" autocomplete="off" required="required" />
						</td>
					</tr>
					<tr>
						<th>Body</th>
						<td>
							<input type="text" placeholder="새 내용" name="body" autocomplete="off" required="required" />
						</td>
					</tr>
					<tr>
						<td>
							<input type="submit" value="작성" />
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	<div class="btns mt-10">
		<button type="button" onclick="history.back()" class="">뒤로가기</button>
	</div>



</section>

<%@ include file="../common/foot.jspf"%>