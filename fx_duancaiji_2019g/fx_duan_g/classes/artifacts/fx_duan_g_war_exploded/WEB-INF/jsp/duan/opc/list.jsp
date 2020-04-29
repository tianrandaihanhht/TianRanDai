<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.extlight.com" prefix="permission" %>
<!DOCTYPE html>
<html lang="zh">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>opc列表</title>
	<link rel="stylesheet" href="../resources/css/bootstrap.min.css" />
	<link rel="stylesheet" href="../resources/css/font-awesome.min.css" />
	<link rel="stylesheet" href="../resources/js/layer/skin/default/layer.css" />
	<link rel="stylesheet" href="../resources/js/bootstrap-table/bootstrap-table.css" />
	<link rel="stylesheet" href="../resources/js/zTree/css/zTreeStyle/metro.css" />

</head>
<body>
<div style="margin-left: auto;margin-right: auto;width: 95%">



	<div class="col-xs-12">
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"
				   >服务器地址</label>
			<div class="col-sm-9">
				<input type="text" id="value1" name="value1"
					   value="" class="col-xs-10 col-sm-8" />
			</div>
		</div>
	</div>

	<div class="col-xs-12">
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"
			>条件二</label>
			<div class="col-sm-9">
				<input type="text" id="value2" name="value2"
					   value="" class="col-xs-10 col-sm-8" />
			</div>
		</div>
	</div>
	<div class="row">
		<div>
			<table id="table"></table>
		</div>
	</div>
</div>	
<%@ include file="../../common.jsp" %>
<script src="../resources/js/bootstrap-table/tree-column/bootstrap-table-tree-column.min.js"></script>
 <!-- <script src="../resources/js/bootstrap-table/bootstrap-table.min.js"></script>
<script src="../resources/js/bootstrap-table/bootstrap-select.js"></script>
<script src="../resources/js/bootstrap-table/bootstrap-table-edit.min.js"></script> -->
<script src="../resources/js/duan/opc/list.js"></script>
</body>
</html>