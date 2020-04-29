<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>型号管理</title>
<link rel="stylesheet" href="../resources/css/bootstrap.min.css" />
<link rel="stylesheet" href="../resources/css/font-awesome.min.css" />
<link rel="stylesheet" href="../resources/css/ace.min.css" />
<link rel="stylesheet"
	href="../resources/js/layer/skin/default/layer.css" />
</head>
<body>
	<div class="page-content">
		<div class="page-header">
			<h1>型号管理</h1>
		</div>
		<!-- /.page-header -->
		<div class="row">
			<div class="col-xs-12">
				<form id="save-form" class="form-horizontal" role="form"
					action="save" method="post">
					<input type="hidden" name="id" value="${model.id}" />
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="form-field-1">型号名</label>
						<div class="col-sm-9">
							<input type="text" id="name" name="name" value="${model.name }"
								class="col-xs-10 col-sm-8" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="form-field-1">大端序</label>
						<div class="col-sm-9">
							<label> 是：<input name="isbigendian" class="ace"
								type="radio" value="1" ${model.isbigendian == 1 ? 'checked':'' } />
								<span class="lbl"></span>
							</label> <label> 否：<input name="isbigendian" class="ace" type="radio"
								value="0" ${model.isbigendian == 0 ||model.isbigendian == 2 ? 'checked':'' } /> <span
								class="lbl"></span>
							</label>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="form-field-1">备注</label>
						<div class="col-sm-9">
							<input type="text" id="remark" name="remark" value="${model.remark }"
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
	<script src="../resources/js/duan/model/saveUI.js"></script>
</body>
</html>