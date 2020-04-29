$(function() {
	
	// 查询数据
	var tableObj = commonUtil.initTable({
        url : "list",
        search : true,
        detailView : true,
        pagination : true,
        editable:false,
        columns: [
            {checkbox: true ,edit:false,},
            {field: 'id', visible: false, title: 'ID',edit:false,},
            {field: 'name',align: 'center', title: '名称',width:150},
            {field: 'value',align: 'center', title: '值',width:200},
       
        ],
     
    });
 

	  
	// 新增方法
	var addData = function() {
		window.location.href = "saveUI";
	}
	
	// 修改方法
	var updateData = function() {
		var list = tableObj.bootstrapTable("getSelections");
        if (list.length ==0) {
            layer.msg("请选择一条记录进行编辑",{
				offset : 't',
				anim : 6
			});
            return;
        }
        if (list.length > 1) {
             layer.msg("一次只能编辑一条记录",{
				offset : 't',
				anim : 6
			});
            return;
        }
		window.location.href = "saveUI?id="+list[0]["id"];
	}

	// 删除方法
	var deleteData = function() {
		var list = tableObj.bootstrapTable("getSelections");
        if (list.length == 0) {
            layer.msg("请选择至少一条记录进行删除",{
				offset : 't',
				anim : 6
			});
            return;
        }
        var ids = [];
        for(var i=0; i<list.length; i++) {
            ids[i] = list[i]["id"];
        }

		layer.confirm("确定要删除该数据吗?", {
			icon : 3,
			title : '提示'
		}, function(index) {
			$.ajax({
				"type" : "GET",
				"url" : "delete/" + ids.join(","),
				"dataType" : "json",
				"success" : function(resp) {
					if (resp.code == 200) {
						layer.close(index);
						tableObj.bootstrapTable('refresh');
					} else {
						layer.alert(resp.msg, {
							"icon" : 2
						});
					}
				}
			});
		});
	}

	// 绑定按钮事件
	var btns = $("#btns").find("a");
	btns.each(function(index, domEle) {
		var btn = $(domEle);
		var code = btn.data("code");
	     if (code == "dictionary:add") {
			btn.on("click", addData);
		} else if(code == "dictionary:update"){
			btn.on("click", updateData);
		}else if (code == "dictionary:delete") {
			btn.on("click", deleteData);
		}
	});

});