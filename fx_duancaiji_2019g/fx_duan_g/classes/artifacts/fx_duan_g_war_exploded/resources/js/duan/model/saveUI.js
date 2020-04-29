$(function() {
	$("#save-btn").on("click",function() {
		if ($("#name").val() == "") {
			layer.msg("型号名不能为空", {
				offset : 't',
				anim : 6
			});
			return;
		} 		 		 
		$("#save-form").submit();
	});
});