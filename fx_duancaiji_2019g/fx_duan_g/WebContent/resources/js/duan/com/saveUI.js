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

	function loadSelect(url, element) {
		$
				.ajax({
					url : url,
					type : "get",
					async : false,
					success : function(res) {
						var data = res.obj;
						var nameOpt = "<option value='null' selected='selected'>--请选择--</option>";
						for (var i = 0; i < data.length; i++) {
							if (element.attr("value") == data[i]) {
								nameOpt += "<option value='" + data[i]
										+ "' selected='selected'>"
										+ data[i]+ "</option>";
							} else {
								nameOpt += "<option value='" + data[i]
										+ "' >" + data[i]+ "</option>"
							}
						}
						element.html(nameOpt);
					},
					error : function() {
					}
				});
	}
	
	loadSelect("../com/findLocalComList/", $('#name'));
	
});