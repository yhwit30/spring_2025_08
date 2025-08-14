<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="ARTICLE WRITE"></c:set>
<%@ include file="../common/head.jspf"%>

<h1>ARTICLE WRITE</h1>


<section class="mt-8 text-xl px-4">
	<div>
		<form action="doWrite" method="post">
			<table class="table" cellpadding="5" style="width: 100%;">
				<tbody>
					<tr>
						<th>게시판</th>
						<td>
							<select name="boardId">
								<option value="" selected disabled>게시판을 선택하시오</option>
								<option value="1">공지사항</option>
								<option value="2">자유게시판</option>
								<option value="3">질의응답</option>
							</select>
						</td>
					</tr>


					<tr>
						<th>제목</th>
						<td>
							<input class="input input-primary input-sm" type="text" placeholder="새 제목" name="title" autocomplete="off"
								required="required" />
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td>
							<input class="input input-primary input-sm" type="text" placeholder="새 내용" name="body" autocomplete="off"
								required="required" />
						</td>
					</tr>
					<tr>
						<td>
							<button class="btn btn-primary">작성</button>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	<div class="btns mt-10">
		<button class="btn btn-info" type="button" onclick="history.back()"">뒤로가기</button>
	</div>



</section>

<%@ include file="../common/foot.jspf"%>