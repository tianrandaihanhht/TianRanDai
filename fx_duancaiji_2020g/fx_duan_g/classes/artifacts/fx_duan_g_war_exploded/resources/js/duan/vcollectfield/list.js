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
            {field: 'comName',align: 'center', title: '串口名称',width:50},
            {field : 'meterName', align: 'center',title : '仪表名称',width:150},
            {field : 'meterAddress', align: 'center',title : '仪表地址'},
            {field : 'modelName',align: 'center', title : '型号'},
            {field: 'cycle',align: 'center', title: '采集周期',width:50},
            {field : 'fieldName',align: 'center', title : '参数名',width:150},
            {field : 'inventedParameterType',align: 'center', title : '虚拟参数类型'},
            {field : 'protocolName',align: 'center', title : '协议名'},
            {field : 'parseName',align: 'center', title : '数据解析算法'},
            {field : 'isBigEndian',align: 'center', title : '大端序'},
            {field : 'data1',align: 'center', title : '数据域1'},
            {field : 'data1',align: 'center', title : '数据域2'},        
            {field : 'unit',align: 'center', title : '单位'},
            {field: 'factor',align: 'center', title: '读数因子',width:50},
            {field : 'enable', title : '状态',align: 'center',width:50, formatter : function(value) {return value == 1 ? "<span class='label label-success'>开启</span>" : "<span class='label label-danger'>禁用</span>"; } },
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
	     if (code == "collectField:add") {
			btn.on("click", addData);
		} else if(code == "collectField:update"){
			btn.on("click", updateData);
		}else if (code == "collectField:delete") {
			btn.on("click", deleteData);
		}
	});

});