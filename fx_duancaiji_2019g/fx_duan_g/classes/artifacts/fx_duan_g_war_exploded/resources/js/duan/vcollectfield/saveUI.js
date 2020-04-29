$(function() {
	$("#save-btn").on("click",function() {
		if ($("#name").val() == "") {
			layer.msg("串口名不能为空", {
				offset : 't',
				anim : 6
			});
			return;
		} 		 
		if ($("#baudrate").val() == "") {
			layer.msg("波特率不能为空", {
				offset : 't',
				anim : 6
			});
			return;
		} 		 
		$("#save-form").submit();
	});
});