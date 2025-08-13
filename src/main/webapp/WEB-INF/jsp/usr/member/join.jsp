<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="MEMBER JOIN"></c:set>
<%@ include file="../common/head.jspf"%>


<section class="mt-8 text-xl px-4">
	<div>
		<form action="doJoin" method="post">
			<table border="1" class="mx-auto border border-gray-800" cellpadding="5">
				<tbody>
					<tr>
						<th>아이디</th>
						<td>
							<input name="loginId" type="text" autocomplete="off" placeholder="아이디 입력" />
						</td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td>
							<input name="loginPw" type="text" autocomplete="off" placeholder="비밀번호 입력" />
						</td>
					</tr>
					<tr>
						<th>이름</th>
						<td>
							<input name="name" type="text" autocomplete="off" placeholder="이름 입력" />
						</td>
					</tr>
					<tr>
						<th>닉네임</th>
						<td>
							<input name="nickname" type="text" autocomplete="off" placeholder="닉네임 입력" />
						</td>
					</tr>
					<tr>
						<th>전화번호</th>
						<td>
							<input name="cellphoneNum" type="text" autocomplete="off" placeholder="전화번호 입력" />
						</td>
					</tr>
					<tr>
						<th>이메일</th>
						<td>
							<input name="email" type="text" autocomplete="off" placeholder="이메일 입력" />
						</td>
					</tr>
					
					<tr>
						<td>
							<input type="submit" value="회원가입" />
						</td>
					</tr>
				</tbody>

			</table>
		</form>
		<div>
			<button type="button" onclick="history.back();">뒤로가기</button>
		</div>
	</div>
</section>


<%@ include file="../common/foot.jspf"%>