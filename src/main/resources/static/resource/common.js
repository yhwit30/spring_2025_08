// 테마 토글
$(function() {
	const $toggle = $("#themeToggle");
	const initial = localStorage.getItem("theme") || "light";
	setTheme(initial);
	$toggle.prop("checked", initial !== "light");

	$toggle.on("change", function() {
		setTheme(this.checked ? "dark" : "light");
	});

	function setTheme(theme) {
		document.documentElement.setAttribute("data-theme", theme);
		localStorage.setItem("theme", theme);
	}
});

// 게시글 검색카테고리 유지
$('select[data-value]').each(function(index, el) {
	const $el = $(el);

	defaultValue = $el.attr('data-value').trim();

	if (defaultValue.length > 0) {
		$el.val(defaultValue);
	}
});
