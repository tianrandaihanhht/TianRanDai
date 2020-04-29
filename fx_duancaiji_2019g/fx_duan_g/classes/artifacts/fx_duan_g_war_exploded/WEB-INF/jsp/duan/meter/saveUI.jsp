<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>仪表管理</title>
 

<link rel="stylesheet" href="../resources/css/bootstrap.min.css" />
<link rel="stylesheet" href="../resources/css/font-awesome.min.css" />
 
<link rel="stylesheet" href="../resources/js/layer/skin/default/layer.css" />
<link rel="stylesheet" href="../resources/js/bootstrap-table/bootstrap-table.css" />
<link rel="stylesheet" href="../resources/js/zTree/css/zTreeStyle/metro.css" />

 

</head>
<body>
	<div class="page-content">
		<div class="page-header">
			<h1>仪表管理</h1>
		</div>
		<!-- /.page-header -->
		<div class="row">
			<div class="col-xs-12">
				<form id="save-form" class="form-horizontal" role="form"
					action="addfields" method="post">
					<input type="hidden" id="id" name="id" value="${meter.id}" />
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="form-field-1">仪表名</label>
						<div class="col-sm-9">
							<input type="text" id="name" name="name" value="${meter.name }"
								class="col-xs-10 col-sm-8" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="form-field-1">仪表地址</label>
						<div class="col-sm-9">
							<input type="text" id="address" name="address"
								value="${meter.address }" class="col-xs-10 col-sm-8" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="form-field-1">MAC地址</label>
						<div class="col-sm-9">
							<input type="text" id="macaddress" name="macaddress"
								value="${meter.macaddress }" class="col-xs-10 col-sm-8" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="form-field-1">型号</label>
						<div class="col-sm-9">
							<select id="modelid" name="modelid" value="${meter.modelid}"
								class="col-xs-10 col-sm-8">
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="form-field-1">采集协议</label>
						<div class="col-sm-9">
							<select id="protocolid" name="protocolid"
								value="${meter.protocolid}" class="col-xs-10 col-sm-8">
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="form-field-1">解析协议</label>
						<div class="col-sm-9">
							<select id="parserid" name="parserid" value="${meter.parserid}"
								class="col-xs-10 col-sm-8">
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="form-field-1">串口</label>
						<div class="col-sm-9">
							<select id="comid" name="comid" value="${meter.comid}"
								class="col-xs-10 col-sm-8">
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="form-field-1">启用</label>
						<div class="col-sm-9">
							<label> 是：<input id="qiyong" name="enable" class="ace" type="radio"
								value="1" ${meter.enable == 1 ? 'checked':'' } /> <span
								class="lbl"></span>
							</label> <label> 否：<input id="buqiyong" name="enable" class="ace" type="radio"
								value="0" ${meter.enable == 0 ? 'checked':'' } /> <span
								class="lbl"></span>
							</label>
						</div>
					</div>
					<table id="table"></table>
					<div class="clearfix form-actions">
						<div class="col-md-offset-3 col-md-9">
							<button class="btn btn-info" type="button" id="save-btn">
								<i class="icon-ok bigger-110"></i> 保存
							</button>
							&nbsp; &nbsp; &nbsp;
							<button class="btn" type="button" onclick="history.go(-1)">
								<i class="icon-undo bigger-110"></i> 返回
							</button>
						</div>
					</div>
				</form>
				

			</div>
		</div>
	</div>
	<%@ include file="../../common.jsp" %>
    <script src="../resources/js/bootstrap-table/tree-column/bootstrap-table-tree-column.min.js"></script>
	<script src="../resources/js/bootstrap-table/bootstrap-table.min2.js"></script>
	<script src="../resources/js/bootstrap-table/bootstrap-select.js"></script>
	<script src="../resources/js/bootstrap-table/bootstrap-table-edit.min.js"></script>  
 
	<script src="../resources/js/duan/meter/saveUI.js"></script>
	
</body>
</html>