$(function() {

    var tableObj = commonUtil.initTable({
        striped: true,
        url : "list",
        search : false,
        detailView : true,
        pagination : true,
        showRefresh: false,
        showColumns:false,
        editable:false,
        queryParams:function(params){
            var pams = {
                offset: params.offset,
                limit: params.limit,
                sort: params.sort,
                order: params.order,
                search:$("#searchText").val()

            };
         return pams;

        }, // 表格数据来源
        columns: [ {
            checkbox: true,
            visible: true                  //是否显示复选框
        },{
                field: 'id',
                title: 'id',
                visible: false
            },{
            field: 'itemId',
            title: '标签名'
        }, {
            field: 'serverIp',
            title: '服务器Ip'
        },{
            field: 'userName',
            title: 'userName'
        }  ]
    });

    var queryData = function() {
        $("table").bootstrapTable('destroy');
        commonUtil.initTable({
            striped: true,
            url : "list",
            search : false,
            detailView : true,
            pagination : true,
            showRefresh: false,
            showColumns:false,
            editable:false,
            queryParams:function(params){
                var pams = {
                    offset: params.offset,
                    limit: params.limit,
                    sort: params.sort,
                    order: params.order,
                    lableId:$("#lableId").val(),
                    serverIp:$("#searchText").val()

                };
                return pams;

            }, // 表格数据来源
            columns: [{
                checkbox: true,
                visible: true   //是否显示复选框
            }, {
                field: 'id',
                title: 'id',
                visible: false
            },

                {
                field: 'itemId',
                title: '标签名'
            }, {
                field: 'serverIp',
                title: '服务器Ip'
            },{
                field: 'userName',
                title: 'userName'
            } ]
        });

    }
    var addData=function(){

        var treeObj=$.fn.zTree.getZTreeObj("treeDemo"),
            nodes=treeObj.getCheckedNodes(true),
            v="";
            for(var i=0;i<nodes.length;i++) {

                if(!nodes[i].isParent){
                    v += nodes[i].name + ",";
                }; //获取选中节点的值


            }
        //var ItemName =treeNode.name;
        var serverIp =$("#hidserverIp").val();
        var userNameId =$("#hiduserNameId").val();
        var passwordId =$("#hidpasswordId").val();

        $.ajax({
            async: true,
            url: "addData",
            data:{"ItemName":v,"serverIp":serverIp,"userNameId":userNameId,"passwordId":passwordId},
            type: "post",
            success:function(data){
                if(data==0){
                    alert("已存在该标签！")
                }else{
                    alert("配置成功！");
                    queryData();
                }

            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {

                if(XMLHttpRequest.status==200){
                    queryData();
                }
            },

        })

    }

    var deleOpcByItemNames=function(){

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
                        layer.msg("删除成功");
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
    $("#query").on("click", queryData);

    $("#addConfig").on("click", addData);
    $("#deleOpcByItemNames").on("click", deleOpcByItemNames);






});