<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.extlight.com" prefix="permission" %>
<!DOCTYPE html>
<html lang="zh">
<head>
	<TITLE> ZTREE DEMO - beforeClick / onClick</TITLE>

	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="..\resources\css\treecss\css\demo.css">
	<link rel="stylesheet" href="..\resources\css\treecss\css\zTreeStyle\zTreeStyle.css">



	<link rel="stylesheet" href="../resources/css/bootstrap.min.css" />
	<link rel="stylesheet" href="../resources/css/font-awesome.min.css" />
	<link rel="stylesheet" href="../resources/js/layer/skin/default/layer.css" />
	<link rel="stylesheet" href="../resources/js/bootstrap-table/bootstrap-table.css" />
	<link rel="stylesheet" href="../resources/js/zTree/css/zTreeStyle/metro.css" />
	<SCRIPT type="text/javascript">
        var ss=0;
		var treelist=[];
 // var zNodes =treelist;
        var setting = {
            check:{
                enable:true
            },
            data: {
                key: {
                    title:"t"
                },
                simpleData: {
                    enable: true
                }
            },
          /*  callback: {
                onClick: onClick
            }*/
        };


        var log, className = "dark";

        function onClick(event, treeId, treeNode, clickFlag) {
            console.log(treeNode);
            console.info(!treeNode.isParent);
            console.info(treeNode.name);
            var ItemName =treeNode.name;
            var serverIp =$("#hidserverIp").val();
            var userNameId =$("#hiduserNameId").val();
            var passwordId =$("#hidpasswordId").val();
            /*setInterval(function () {*/
                $.ajax({
                    async: false,
                    url: "http://localhost:8090/getValue",
                    data:{"ItemName":ItemName,"serverIp":serverIp,"userNameId":userNameId,"passwordId":passwordId},
                    type: "post",
                    success:function(data){
                        showLog(data);
                    }

                })
                /*},9000)*/
        }
        function showLog(str) {
            if (!log) log = $("#log");
            log.append("<li class='"+className+"'>"+str+"</li>");
            if(log.children("li").length > 4) {
                log.get(0).removeChild(log.children("li")[0]);
            }
        }
        function getTime() {
            var now= new Date(),
                h=now.getHours(),
                m=now.getMinutes(),
                s=now.getSeconds();
            return (h+":"+m+":"+s);
        }
        function forBranch(list) {
            for (var i = 0; i < list.length; i++) {
                //第一层
				treelist.push({id: i, pId: "$", name: list[i].name});
               if((list[i].leaves).length > 0){
                    for(var j = 0; j < list[i].leaves.length; j++){
                        treelist.push({id: list[i].leaves[j].itemId, pId: i, name: list[i].leaves[j].itemId});
                    }
				}
                if ((list[i].branches).length > 0) {
                    for (var j = 0; j < list[i].branches.length; j++) {
                        //第二层
                        treelist.push({id: i+"-"+j, pId: i, name: list[i].name+list[i].branches[j].name});
                        if((list[i].branches[j].leaves).length > 0){
                            for(var a = 0; a < list[i].branches[j].leaves.length; a++){
                                treelist.push({id: list[i].branches[j].leaves[a].itemId, pId: i+"-"+j, name: list[i].branches[j].leaves[a].itemId});
                            }
                        }
                        if(list[i].branches[j].branches.length>0){
                    	    for(var m = 0; m < list[i].branches[j].branches.length; m++){
                                //第三层
                                treelist.push({id:i+"-"+j+"-"+m , pId: i+"-"+j, name: list[i].name+list[i].branches[j].name+list[i].branches[j].branches[m].name});

								if((list[i].branches[j].branches[m].leaves).length > 0){
								    for(var b=0;b<(list[i].branches[j].branches[m].leaves).length;b++){
                                        treelist.push({id:list[i].branches[j].branches[m].leaves[b].itemId, pId: i+"-"+j+"-"+m, name: list[i].branches[j].branches[m].leaves[b].itemId});

                                    }


                                }


							}

                        }
                    }

                }

            }
        }





            /*if(list.length>0){
                for(var i=0;i<list.length;i++){
                    if(ss==0){
                        treelist.push({id:list[i].name, pId:"$", name:list[i].name});

                    }else{
                        treelist.push({id:list[i].name, pId:list[i].name, name:list[i].name});
                    }

                    if(((list[i].leaves).length)>0){
                        for(var j=0;j<(list[i].leaves).length;j++){
                            treelist.push({id:list[i].name, pId:i, name:list[i].leaves[j].itemId});
                        }
                    }

                    if(((list[i].branches).length)>0){
                        ss=ss+1;
                        forBranch(list[i].branches,ss);

                    }

                }

            }
        }*/

       /* $(document).ready(function(){
            $.fn.zTree.init($("#treeDemo"), setting, treelist);
        });*/

       function queryOpcTree() {
           ss = 0;
           var serverIp = $("#serverIp").val();
           var userNameId = $("#userNameId").val();
           var passwordId = $("#passwordId").val();

           if (serverIp == "") {
               alert("服务器地址不能为空!");
               return;
           }
           if (userNameId == "") {
               alert("用户名不能为空!");
               return;
           }
           if (passwordId == "") {
               alert("密码不能为空!");
               return;
           }

           treelist = [];

           $.fn.zTree.init($("#treeDemo"), setting, treelist);
           $.ajax({
               type: "post",
               url: "http://localhost:8090/initTree",
               data: {"serverIp": serverIp, "userNameId": userNameId, "passwordId": passwordId},
               dataType: "json",
               success: function (data) {
                   $("#hidserverIp").val(data.serverIp);
                   $("#hiduserNameId").val(data.userNameId);
                   $("#hidpasswordId").val(data.passwordId);
                   var list = JSON.parse(data.jsonStr).branches;
                   forBranch(list);

                   $.fn.zTree.init($("#treeDemo"), setting, treelist);
               },
			   error: function(XMLHttpRequest, textStatus, errorThrown) {
                   alert(XMLHttpRequest.status);
                   alert(XMLHttpRequest.readyState);
                   alert(textStatus);
               },
           });
       }

	</SCRIPT>
</head>

<body>
<div>

    <h1>查询信息</h1>
    <div class="form-search">
        服务器IP：<input class="input-medium search-query" id="serverIp" type="text" />
		<input class="input-medium search-query" id="hidserverIp" hidden="true" type="text" />
        用户名：<input class="input-medium search-query" id="userNameId" type="text" />
		<input class="input-medium search-query" id="hiduserNameId" hidden="true" type="text" />
        密码：<input  type="password"  class="input-medium search-query" id="passwordId" type="text" />
		<input class="input-medium search-query" id="hidpasswordId" hidden="true" type="text" />
        <button  id="submitId" onclick="queryOpcTree()" >查找</button>
    </div>

</div>


<h1>详细信息</h1>


<div class="content_wrap" id="content_wrap" style="padding-left: 15px">
	<div class="zTreeDemoBackground left" >
		<ul id="treeDemo" class="ztree"></ul>
	</div>

<button type="button" id="addConfig">addConfig</button>

	<div class="right">
		<ul class="info">
			<li class="title">
				<ul class="list1">
					<li><p><button id="deleOpcByItemNames" >删除</button><br/>
						<ul id="log01" class="log">
						<div class="row">
							<div class="col-xs-12">
								<div class="row">
									<div class="col-xs-12">
										<div style="position: absolute;top:15px" id="btns">
											<permission:each items="${sessionScope.loginUser.permissionList }" type="opc" var="permission">
												<a href="#" class="btn ${permission.color } btn-sm" data-code="${permission.code }">
													<i class="${permission.icon }"></i>${permission.name}
												</a>
											</permission:each>
										</div>
										<div id="toolbar">
											<form class="form-inline">
												<div class="form-group">
													<label class="sr-only" >lableId</label>
													<div class="input-group">
														<div class="input-group-addon">lableId</div>
														<input type="text" class="form-control" name="searchTexts" id="lableId" placeholder="请输入标签值。。">
													</div>
												</div>

												<div class="form-group">
													<label class="sr-only" >serverIp</label>
													<div class="input-group">
														<div class="input-group-addon">serverIp</div>
														<input type="text" class="form-control" name="searchTexts" id="searchText" placeholder="请输入服务器IP">
													</div>
												</div>
												<button type="button" class="btn btn-primary queryButton" id="query">查询</button>
											</form>
										</div>
										<table id="table"></table>
									</div>
								</div>
							</div>
						</div>



						</ul></p>


					</li>
				</ul>
			</li>
		</ul>
	</div>
</div>
<%@ include file="../../common.jsp" %>
<script src="../resources/js/zTree/js/jquery.ztree.all-3.5.min.js"></script>
<script src="../resources/js/duan/opc/list.js"></script>
</body>
</html>