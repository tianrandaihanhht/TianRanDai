<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.extlight.com" prefix="permission" %>
<!DOCTYPE html>
<html lang="zh">
<head>
	<TITLE> ZTREE DEMO - beforeClick / onClick</TITLE>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="..\resources\css\treecss\css\demo.css">
	<link rel="stylesheet" href="..\resources\css\treecss\css\zTreeStyle\zTreeStyle.css">
	<script type="text/javascript" src="..\resources\js\treejs\js\jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="..\resources\js\treejs\js\jquery.ztree.core.js"></script>
	<SCRIPT type="text/javascript">

		var treelist=[];

        $.ajax({
            type: "post",
            url: "http://localhost:8090/initTree",
            dataType: "json",
            async:false,
            success: function(data){
                var list = data.branches;
                forBranch(list);
                console.info(treelist);

            }
        });


        var zNodes =treelist;
        var setting = {
            data: {
                key: {
                    title:"t"
                },
                simpleData: {
                    enable: true
                }
            },
            callback: {
                // beforeClick: beforeClick,
                onClick: onClick
            }
        };

        var log, className = "dark";

        var setting = {
            data: {
                key: {
                    title:"t"
                },
                simpleData: {
                    enable: true
                }
            },
            callback: {
                // beforeClick: beforeClick,
                onClick: onClick
            }
        };

        var log, className = "dark";
      /*  function beforeClick(treeId, treeNode, clickFlag) {
            className = (className === "dark" ? "":"dark");
            showLog("[ "+getTime()+" beforeClick ]&nbsp;&nbsp;" + treeNode.name );
            return (treeNode.click != false);
        }*/
        function onClick(event, treeId, treeNode, clickFlag) {
            //showLog("[ "+getTime()+" onClick ]&nbsp;&nbsp;clickFlag = " + clickFlag + " (" + (clickFlag===1 ? "普通选中": (clickFlag===0 ? "<b>取消选中</b>" : "<b>追加选中</b>")) + ")");
        //请求参数 $("#log1");中放参数类型， $("#log2")中放参数值

            /*setInterval(function () {*/
                $.ajax({
                    async: false,
                    url: "http://localhost:8090/getValue",
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
        function forBranch(list){
            var ss=0;
            if(list.length>0){
                for(var i=0;i<list.length;i++){
                  /* var bst=list[i].branchStack;
                    if(bst.length>0){
                        for(var i=0;i<bst.length;i++){
                            console.info("rrrrrr"+bst[i]);
                        }
                    }*/

                    if(ss=0){
                        treelist.push({id:list[i].name, pId:"$", name:list[i].name});

                    }else{
                        treelist.push({id:list[i].name, pId:list[i].name, name:list[i].name});
                    }

                    if(((list[i].leaves).length)>0){
                        for(var j=0;j<(list[i].leaves).length;j++){
                            treelist.push({id:list[i].leaves[j].itemId, pId:list[i].name, name:list[i].leaves[j].itemId});

                        }

                    }

                    if(((list[i].branches).length)>0){
                        forBranch(list[i].branches)
                    }

                    ss+=1;
                }
            }


        }

        $(document).ready(function(){
            $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        });

        /*$("#submitId").on("click",function() {
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

            $.ajax({
                "type": "post",
                "url": "login",
                "data":{"serverIp":serverIp,"userNameId":userNameId,"passwordId":passwordId},
                "success" : function(resp) {
                    if (resp.code == 200) {
                        window.location.href = resp.obj;
                    } else {
                        alert(resp.msg);
                        return;
                    }
                }
            });
        });
*/



	</SCRIPT>
</head>

<body>
<%--<div>

    <h1>查询信息</h1>
    <form class="form-search">
        服务器IP：<input class="input-medium search-query" id="serverIp" type="text" />
        用户名：<input class="input-medium search-query" id="userNameId" type="text" />
        密码：<input class="input-medium search-query" id="passwordId" type="text" />
        <button class="btn" contenteditable="true" id="submitId" type="submit">查找</button>
    </form>

</div>--%>


<h1>详细信息</h1>


<div class="content_wrap" id="content_wrap" style="padding-left: 15px">
	<div class="zTreeDemoBackground left" >
		<ul id="treeDemo" class="ztree"></ul>
	</div>
	<div class="right">
		<ul class="info">
			<li class="title">
				<ul class="list">
					<li><p><span class="highlight_red">参数值</span><br/>
						<ul id="log" class="log"></ul></p>
					</li>
				</ul>
			</li>
		</ul>
	</div>
</div>
</body>
</html>