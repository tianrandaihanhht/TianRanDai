$(function() {

	var startup = function() {
		$.ajax({
			"type" : "GET",
			"url" : "startup",
			"dataType" : "json",
			"success" : function(resp) {
				if (resp.code == 200) {
					layer.alert(resp.msg, {
						"icon" : 1
					});	 
				} else {
					layer.alert(resp.msg, {
						"icon" : 2
					});
				}
			}
		});

	}
	// 绑定按钮事件
	var btns = $("#btns").find("a");
	btns.each(function(index, domEle) {
		var btn = $(domEle);
		var code = btn.data("code");
		if (code == "collectstartup:startup") {
			btn.on("click", startup);
		}
	});
})