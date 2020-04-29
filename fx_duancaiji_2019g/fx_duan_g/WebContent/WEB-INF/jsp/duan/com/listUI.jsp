<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.extlight.com" prefix="permission" %>
<!DOCTYPE html>
<html lang="zh">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>串口列表</title>
	<link rel="stylesheet" href="../resources/css/bootstrap.min.css" />
	<link rel="stylesheet" href="../resources/css/font-awesome.min.css" />
	<link rel="stylesheet" href="../resources/js/layer/skin/default/layer.css" />
	<link rel="stylesheet" href="../resources/js/bootstrap-table/bootstrap-table.css" />
	<link rel="stylesheet" href="../resources/js/zTree/css/zTreeStyle/metro.css" />
</head>
<body>
	<div style="margin-left: auto;margin-right: auto;width: 95%">
	<div class="row">
		<div class="col-xs-12">
			<div class="row">
				<div class="col-xs-12">
					<div style="position: absolute;top:15px" id="btns">
						<permission:each items="${sessionScope.loginUser.permissionList }" type="com" var="permission">
							<a href="#" class="btn ${permission.color } btn-sm" data-code="${permission.code }">
								<i class="${permission.icon }"></i>${permission.name}
							</a>
						</permission:each>
					</div>
					<table id="table"></table>
				</div>
			</div>
		</div>
	</div>	 
</div>	
<%@ include file="../../common.jsp" %>
<script src="../resources/js/bootstrap-table/tree-column/bootstrap-table-tree-column.min.js"></script>
 <!-- <script src="../resources/js/bootstrap-table/bootstrap-table.min.js"></script>
<script src="../resources/js/bootstrap-table/bootstrap-select.js"></script>
<script src="../resources/js/bootstrap-table/bootstrap-table-edit.min.js"></script> -->
<script src="../resources/js/duan/com/list.js"></script>
</body>
</html>