<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>参数管理</title>
<link rel="stylesheet" href="../resources/css/bootstrap.min.css" />
<link rel="stylesheet" href="../resources/css/font-awesome.min.css" />
<link rel="stylesheet" href="../resources/css/ace.min.css" />
<link rel="stylesheet"
	href="../resources/js/layer/skin/default/layer.css" />
</head>
<body>
	<div class="page-content">
		<div class="page-header">
			<h1>参数管理</h1>
		</div>
		<!-- /.page-header -->
		<div class="row">
			<div class="col-xs-12">
				<form id="save-form" class="form-horizontal" role="form"
					action="save" method="post">
					<input type="hidden" name="id" value="${com.id}" />
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="form-field-1">参数名</label>
						<div class="col-sm-9">
							<input type="text" id="name" name="name" value="${field.name }"
								class="col-xs-10 col-sm-8" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="form-field-1">默认单位</label>
						<div class="col-sm-9">
							 
							<select id="defaultunit" name="defaultunit" value="${field.defaultunit}"
								class="col-xs-10 col-sm-8">
								<option value="A">A</option>
								<option value="kW">kW</option>
								<option value="kWh">kWh</option>
								<option value="Kvarh">Kvarh</option>
								<option value="V">V</option>
								<option value="kvar">kvar</option>
								<option value="%">%</option>
								<option value=".">.</option>
								<option value="元">元</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="form-field-1">默认采集周期</label>
						<div class="col-sm-9">
							<input type="text" id="defaultcycle" name="defaultcycle"
								value="${field.defaultcycle }" class="col-xs-10 col-sm-8" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="form-field-1">默认读数因子</label>
						<div class="col-sm-9">
							<input type="text" id="defaultfactor" name="defaultfactor"
								value="${field.defaultfactor }" class="col-xs-10 col-sm-8" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="form-field-1">参数类型</label>
						<div class="col-sm-9">
							<input type="text" id="paramtype" name="paramtype"
								value="${field.paramtype }" class="col-xs-10 col-sm-8" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="form-field-1">虚拟参数类型</label>
						<div class="col-sm-9">
							<input type="text" id="inventedparametertype"
								name="inventedparametertype"
								value="${field.inventedparametertype }"
								class="col-xs-10 col-sm-8" />
						</div>
					</div>
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
	<script src="../resources/js/jquery-1.11.3.js"></script>
	<script src="../resources/js/layer/layer.js"></script>
	<script src="../resources/js/duan/field/saveUI.js"></script>
</body>
</html>