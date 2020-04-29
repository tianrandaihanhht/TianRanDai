$(function() {
	$("#save-btn").on(
			"click",
			function() {
				if ($("#name").val() == "") {
					layer.msg("仪表名不能为空", {
						offset : 't',
						anim : 6
					});
					return;
				}
				var meterwethfields = {
					id : $("#id").val(),
					name : $("#name").val(),
					address : $("#address").val(),
					macaddress : $("#macaddress").val(),
					modelid : $("#modelid").val(),
					protocolid : $("#protocolid").val(),
					parserid : $("#parserid").val(),
					comid : $("#comid").val(),
					enable : $("#qiyong:checked").val() == true ? 1 : 0,
					fields : JSON.stringify($('#table').bootstrapTable(
							"getSelections")),
				};

				$.ajax({
					url : "addfields",
					type : "POST",
					data : meterwethfields,
					success : function(response, status, xhr) {
						console.log(xhr);
						layer.alert(response.msg, {
							"icon" : 1
						});
						var json = $.parseJSON(response);
						window.location.reload();
					}			
				});
			
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
	function modelSelect() {
		loadSelect("../protocol/getProtocolListByModelId/"
				+ $('#modelid').val(), $('#protocolid'));
		protocolSelect();
		$('#parserid').val('');
		$("#table").bootstrapTable('destroy');
		var tableObj = commonUtil

				.initTable({
					url : "../modelfield/getFieldListWithChecked/"
							+ $('#id').val(),
					search : true,
					detailView : true,
					pagination : true,
					editable : true,
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
								title : 'ID',
								edit : false,
							},
							{
								field : 'name',
								align : 'center',
								title : '名称',
								width : 350,
								edit : false,
							},

							{
								field : 'data1',
								align : 'center',
								title : '寄存器起始地址/标识符',
								width : 200
							},
							{
								field : 'data2',
								align : 'center',
								title : '寄存器结束地址/标识符',
								width : 200
							},
							{
								field : 'defaultunit',
								align : 'center',
								title : '默认单位',
								width : 200
							},
//							{
//								field : 'defaultcycle',
//								align : 'center',
//								title : '默认采集周期',
//								width : 100
//							},
							{
								field : 'inventedparametertype',
								title : '瞬時/累計',
								align : 'center',
								width : 100,
								formatter : function(value) {
									return value == 1 ? "<span class='label label-success'>累計</span>"
											: "<span class='label label-danger'>瞬時</span>";
								},
								edit : false,
							}, {
								field : 'defaultfactor',
								align : 'center',
								title : '默认读数因子'
							}, {
								field : 'paramtype',
								align : 'center',
								title : '参数类型',
								edit : false,
							}, ],
					queryparm : {
						value1 : $('#modelid').val()
					}
				});

	}
	function protocolSelect() {
		loadSelect("../parser/getParserListByProtocolId/"
				+ $('#protocolid').val(), $('#parserid'));
	}

	loadSelect("../model/findAlllist", $('#modelid'));
	loadSelect("../com/findAlllist", $('#comid'));
	modelSelect();
	protocolSelect();

	// setTimeout(function(){modelSelect();},0)
	// setTimeout(function(){protocolSelect(); },0)

	$('#modelid').on("change", modelSelect);
	$('#protocolid').on("change", protocolSelect);
	if ($('#id').val() == '') {
		$('#id').val('null')
	}


});
