$(function() {
	$("#save-btn").on("click", function() {
		if ($("#name").val() == "") {
			layer.msg("串口名不能为空", {
				offset : 't',
				anim : 6
			});
			return;
		}
	
		$("#save-form").submit();
	});
	function loadSelect(url,element) {
		$.ajax({
					url : url,
					type : "get",
					success : function(res) {
						var data = res.obj;
						var nameOpt = "<option value='' selected='selected'>--请选择--</option>";
						for (var i = 0; i < data.length; i++) {
							if (element.attr("value") == data[i].id) {
								nameOpt += "<option value='" + data[i].id
										+ "' selected='selected'>"
										+ data[i].name + "</option>";
							} else {
								nameOpt += "<option value='" + data[i].id
										+ "' >" + data[i].name + "</option>"
							}
						}
						element.html(nameOpt);
					},
					error : function() {
					}
				});
	}
	loadSelect("../model/findAlllist",$('#modelid'));
	//loadSelect("../com/findAlllist",$('#comid'));
});