$(function() {
	$("#save-btn").on("click",function() {
		if ($("#name").val() == "") {
			layer.msg("串口名不能为空", {
				offset : 't',
				anim : 6
			});
			return;
		} 		 
		if ($("#value").val() == "") {
			layer.msg("值不能为空", {
				offset : 't',
				anim : 6
			});
			return;
		} 		 
		$("#save-form").submit();
	});
	
});