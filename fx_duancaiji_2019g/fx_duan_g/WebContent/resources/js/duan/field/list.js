$(function() {
	
	// 查询数据
	var tableObj = commonUtil.initTable({
        url : "list",
        search : true,
        detailView : true,
        pagination : true,
        
        columns: [
            {checkbox: true},
            {field: 'id', visible: false, title: 'ID'},
            {field: 'name',align: 'center', title: '名称',width:350},
            {field: 'defaultunit',align: 'center', title: '默认单位',width:200},
            {field: 'defaultcycle',align: 'center', title: '默认采集周期',width:100},
            {field : 'inventedparametertype', title : '虚拟参数',align: 'center',width:100, formatter : function(value) {return value == 1 ? "<span class='label label-success'>是</span>" : "<span class='label label-danger'>否</span>"; } },
            {field : 'defaultfactor', align: 'center',title : '默认读数因子'},
            {field : 'paramtype', align: 'center',title : '参数类型'},            
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
	     if (code == "field:add") {
			btn.on("click", addData);
		} else if(code == "field:update"){
			btn.on("click", updateData);
		}else if (code == "field:delete") {
			btn.on("click", deleteData);
		}
	});

});