<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="MEMBER LOGIN"></c:set>
<%@ include file="../common/head.jspf"%>


<section class="mt-8 text-xl px-4">
	<div>
		<form action="doLogin" method="post">
			<table border="1" class="table" cellpadding="5">
				<tbody>
					<tr>
						<th>아이디</th>
						<td>
							<input class="input input-primary input-sm" name="loginId" type="text" autocomplete="off" placeholder="아이디 입력" />
						</td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td>
							<input class="input input-primary input-sm" name="loginPw" type="text" autocomplete="off" placeholder="비밀번호 입력" />
						</td>
					</tr>
					<tr>
						<td>
							<button class="btn btn-primary">로그인</button>
						</td>
					</tr>
				</tbody>

			</table>
		</form>
		<div>
			<button class="btn btn-info" type="button" onclick="history.back();">뒤로가기</button>
		</div>
	</div>
</section>


<%@ include file="../common/foot.jspf"%>