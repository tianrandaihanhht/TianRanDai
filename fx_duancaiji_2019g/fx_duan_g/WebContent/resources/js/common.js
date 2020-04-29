var commonUtil = {
	p:null,
	initTable : function(obj) {
		commonUtil.p=obj.queryparm;
		var id = obj.id || "table";
		var table = $('#' + id).bootstrapTable({
			treeShowField : obj.treeShowField,
			url : obj.url,
			xhrFields : {
				withCredentials : true
			},
			data_locale: 'zh-CN',
			editable:obj.editable,
			  cache: false,
			  sidePagination:'server',
			crossDomain : true,
			// striped: true,
			search : obj.search,
			queryParams: obj.queryParams!=undefined?obj.queryParams:commonUtil.searchParam,
			contentType:"application/x-www-form-urlencoded; charset=UTF-8",
			 singleSelect: false,
 

			   silent: true,

			   queryParamsType: "limit",
			height : obj.height,
			showRefresh : obj.showRefresh!=undefined?obj.showRefresh:true,
			showColumns : obj.showColumns!=undefined?obj.showColumns:true,
			minimumCountColumns : 2,
			clickToSelect : false,
			detailView : obj.detailView,
			detailFormatter : commonUtil.detailFormatter,
			pagination : obj.pagination,
			paginationLoop : false,
			sidePagination : 'server',
			silentSort : false,
			smartDisplay : false,
			escape : true,
			searchOnEnterKey : true,
			idField : 'id',
			maintainSelected : true,
			pageSize: obj.pageSize,
			pageList: obj.pageList,
			responseHandler : function(resp) {
				if (resp.code != 200) {
					layer.msg(resp.msg);
					return;
				}
				return {
					"total" : resp.obj.total, // 总页数
					"rows" : resp.obj.list // 数据
				};
			},
			columns : obj.columns,
			onClickCell : obj.onClickCell,
			onExpandRow : obj.onExpandRow,
			onSearch:function(text)
			  {
			  alert(text);
			  },
			  onLoadSuccess:obj.onLoadSuccess,
		});
		return table;
	},
	// 数据表格展开内容
     detailFormatter: function(index, row) {
        var html = [];
        $.each(row, function(key, value) {
            html.push('<p><b>' + key + ':</b> ' + value + '</p>');
        });
        return html.join('');
    },
	searchParam:function (params) {
		var pams = {
				offset: params.offset,
		        limit: params.limit,
		        sort: params.sort,
		        order: params.order,
		        search:$(".fixed-table-toolbar .search input").val(),
                value1:$("#value1").val(),
            	value2:$("#value2").val()
		};
		if(commonUtil.p!=undefined&&commonUtil.p.value1!=undefined) pams.value1=commonUtil.p.value1
		return pams;
	}
	
}

$.ajaxSetup({
    dataType: "json",
    cache: false,
    xhrFields: { withCredentials: true },//设置后，请求会携带cookie
    crossDomain: true,
    complete: function(xhr) {
        if (xhr.responseJSON) {
            if (xhr.responseJSON.code != 200) {
                layer.msg(xhr.responseJSON.msg);
            }
        } else {
        	console.log(xhr.status != 200)
            layer.msg(xhr.responseText);
        }
    }
});
