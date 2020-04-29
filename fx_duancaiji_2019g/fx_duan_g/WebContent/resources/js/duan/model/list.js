$(function() {

	// 查询数据
	var tableObj = commonUtil
			.initTable({
				url : "list",
				search : true,
				detailView : true,
				pagination : true,

				columns : [
						{
							checkbox : true
						},
						{
							field : 'id',
							visible : false,
							title : 'ID'
						},
						{
							field : 'name',
							align : 'center',
							title : '名称',
							width : 150
						},

						{
							field : 'isBigEndian',
							title : '是否大端序',
							align : 'center',
							width : 100,
							formatter : function(value) {
								return value == 1 ? "<span class='label label-success'>是</span>"
										: "<span class='label label-danger'>否</span>";
							}
						},

						{
							field : 'isDefault',
							title : '是否默认',
							align : 'center',
							width : 100,
							formatter : function(value) {
								return value == 1 ? "<span class='label label-success'>是</span>"
										: "<span class='label label-danger'>否</span>";
							}
						}, {
							field : 'remark',
							align : 'center',
							title : '备注'
						}, ],

			});

	var ztreeObj = null;
	// 设置协议方法
	var setProtocol = function() {
		var list = tableObj.bootstrapTable("getSelections");

		if (list.length == 0) {
			layer.msg("请选择一条记录进行编辑", {
				offset : 't',
				anim : 6
			});
			return;
		}
		if (list.length > 1) {
			layer.msg("一次只能编辑一条记录", {
				offset : 't',
				anim : 6
			});
			return;
		}
		var modelId = list[0].id;
		$.ajax({
			"type" : "GET",
			"url" : "../protocol/getProtocolListWithChecked/" + modelId,
			"dataType" : "json",
			"success" : function(resp) {
				if (resp.code == 200) {
					// 生成树
					ztreeObj = $.fn.zTree.init($("#protocolTree"),
							getZTreeSetting(), resp.obj);

					layer.open({
						title : "【" + list[0].name + '】关联协议',
						type : 1,
						content : $("#protocolUI"),
						area : [ '400px', '350px' ],
						btn : [ '保存', '取消' ],
						yes : function(index, layero) {

							var nodes = ztreeObj.getCheckedNodes(true);

							var protocolIds = [];
							if (nodes.length > 0) {
								for (var i = 0; i < nodes.length; i++) {
									protocolIds.push(nodes[i].id);
								}
							}
							// 发送请求
							$.ajax({
								"type" : "POST",
								"url" : "setProtocol",
								"data" : {
									modelId : modelId,
									protocolIds : protocolIds.join(",")
								},
								"dataType" : "json",
								"success" : function(resp) {
									if (resp.code == 200) {
										layer.msg("设置成功!")
										layer.close(index);
										tableObj.bootstrapTable('refresh');
									} else {
										layer.alert(resp.msg, {
											"icon" : 2
										});
									}
								}
							});
						}
					});
				} else {
					layer.alert(resp.msg, {
						"icon" : 2
					});
					return;
				}
			}
		});

	}
	var setField = function() {
		var list = tableObj.bootstrapTable("getSelections");
		if (list.length == 0) {
			layer.msg("请选择一条记录进行编辑", {
				offset : 't',
				anim : 6
			});
			return;
		}
		if (list.length > 1) {
			layer.msg("一次只能编辑一条记录", {
				offset : 't',
				anim : 6
			});
			return;
		}
		var modelId = list[0].id;
		var setFieldtableObj = commonUtil
				.initTable({
					id : "fieldtable",
					url : "../field/getFieldListWithChecked/" + modelId,
					search : false,
					detailView : true,
					pagination : true,
					pageSize : 200,
					pageList : [ 200 ],
					columns : [
							{
								field : 'checked',
								checkbox : true
							},
							{
								field : 'id',
								visible : false,
								title : 'ID'
							},
							{
								field : 'name',
								align : 'center',
								title : '名称',
								width : 350
							},
							{
								field : 'defaultunit',
								align : 'center',
								title : '默认单位',
								width : 200
							},
							{
								field : 'defaultcycle',
								align : 'center',
								title : '默认采集周期',
								width : 100
							},
							{
								field : 'inventedparametertype',
								title : '虚拟参数',
								align : 'center',
								width : 100,
								formatter : function(value) {
									return value == 1 ? "<span class='label label-success'>是</span>"
											: "<span class='label label-danger'>否</span>";
								}
							}, {
								field : 'defaultfactor',
								align : 'center',
								title : '默认读数因子'
							}, {
								field : 'paramtype',
								align : 'center',
								title : '参数类型'
							}, ],
					onLoadSuccess : function(data) {
						layer.open({
							title : "【" + list[0].name + '】关联参数',
							type : 1,
							content : $("#fieldUI"),
							area : [ '950px', '650px' ],
							btn : [ '保存', '取消' ],
							yes : function(index, layero) {
								var str = "慎重修改，请确定删除参数在其他地方未被使用！！！";
								layer.confirm(str, {
									btn : [ '确定', '取消' ],
									title : "提示"
								}, function() {
									var a = $("#fieldtable").bootstrapTable(
											'getSelections');

									var fieldIds = [];
									if (a.length > 0) {
										for (var i = 0; i < a.length; i++) {
											fieldIds.push(a[i].id);
										}
									}
									// var b=JSON.stringify( a );
									var url = "saveField";
									$.ajax({
										dataType : "json",
										traditional : true,// 这使json格式的字符不会被转码
										data : {
											"fieldIds" : fieldIds.join(","),
											"modelId" : modelId
										},
										type : "post",
										url : url,
										success : function(data) {
											alert("成功！");

										},
										error : function(data) {
											alert(data.responseText);

										},
										complete : function(XMLHttpRequest,
												textStatus) {
											layer.closeAll();
											setFieldtableObj
													.bootstrapTable('destroy');
										},
									});
								});

							},
							btn2 : function() {// 取消按钮
								// do something;
								layer.closeAll();
								setFieldtableObj.bootstrapTable('destroy');
							}

							,
							cancel : function() {
								layer.closeAll();
								// 右上角关闭事件的逻辑
								setFieldtableObj.bootstrapTable('destroy');
							}

						});

					}

				});
	}

	// ztree设置
	function getZTreeSetting() {
		return {
			check : {
				enable : true,
				chkboxType : {
					"Y" : "p",
					"N" : "s"
				}
			},
			view : {
				dblClickExpand : false,
				showLine : true,
				selectedMulti : false
			},
			data : {
				simpleData : {
					enable : true,
					idKey : "id",
					pIdKey : "pid",
					rootPId : ""
				}
			},
			callback : {
				beforeClick : function(treeId, treeNode) {
					var zTree = $.fn.zTree.getZTreeObj("protocolTree");
					if (treeNode.isParent) {
						zTree.expandNode(treeNode);
					}
					return false;
				}
			}
		};
	}

	// 新增方法
	var addData = function() {
		window.location.href = "saveUI";
	}

	// 修改方法
	var updateData = function() {
		var list = tableObj.bootstrapTable("getSelections");
		if (list.length == 0) {
			layer.msg("请选择一条记录进行编辑", {
				offset : 't',
				anim : 6
			});
			return;
		}
		if (list.length > 1) {
			layer.msg("一次只能编辑一条记录", {
				offset : 't',
				anim : 6
			});
			return;
		}
		window.location.href = "saveUI?id=" + list[0]["id"];
	}

	// 删除方法
	var deleteData = function() {
		var list = tableObj.bootstrapTable("getSelections");
		if (list.length == 0) {
			layer.msg("请选择至少一条记录进行删除", {
				offset : 't',
				anim : 6
			});
			return;
		}
		var ids = [];
		for (var i = 0; i < list.length; i++) {
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
		if (code == "model:setField") {
			btn.on("click", setField);
		} else if (code == "model:setProtocol") {
			btn.on("click", setProtocol);
		} else if (code == "model:add") {
			btn.on("click", addData);
		} else if (code == "model:update") {
			btn.on("click", updateData);
		} else if (code == "model:delete") {
			btn.on("click", deleteData);
		}
	});

});