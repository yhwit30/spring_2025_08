<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="API TEST"></c:set>

<%@ include file="../common/head.jspf"%>

<div>콘솔에 api 데이터 확인 페이지</div>

<script>
	const API_KEY = ''; // Encoding된 키

	async function getAirData() {
		const url = 'https://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty'
				+ '?serviceKey='
				+ API_KEY
				+ '&returnType=json&numOfRows=5&pageNo=1&sidoName=대전';

		try {
			const response = await
			fetch(url);
			if (!response.ok) {
				throw new Error(`HTTP 오류! 상태 코드: ${response.status}`);
			}
			const data = await
			response.json();
			console.log("대기오염 정보1:", data);
			console.log("대기오염 정보2:", data.response.body);
			console.log("대기오염 정보3:", data.response.body.items[0]);
			console.log("대기오염 정보4:", data.response.body.items[0].dataTime);
			console.log("대기오염 정보5:", data.response.body.items[0].stationName);
		} catch (e) {
			console.error("API 호출 실패:", e);
		}
	}
	getAirData();
</script>

<%@ include file="../common/foot.jspf"%>