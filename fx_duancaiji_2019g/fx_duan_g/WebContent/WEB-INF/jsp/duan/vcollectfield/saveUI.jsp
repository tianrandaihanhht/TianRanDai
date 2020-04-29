<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>采集管理</title>
<link rel="stylesheet" href="../resources/css/bootstrap.min.css" />
<link rel="stylesheet" href="../resources/css/font-awesome.min.css" />
<link rel="stylesheet" href="../resources/css/ace.min.css" />
<link rel="stylesheet"
	href="../resources/js/layer/skin/default/layer.css" />
</head>
<body>
	<div class="page-content">
		<div class="page-header">
			<h1>采集管理</h1>
		</div>
		<!-- /.page-header -->
		<div class="row">
			<div class="col-xs-12">
				<form id="save-form" class="form-horizontal" role="form"
					action="save" method="post">
					<input type="hidden" name="id" value="${com.id}" />
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="form-field-1">采集名</label>
						<div class="col-sm-9">
							<input type="text" id="name" name="name" value="${com.name }"
								class="col-xs-10 col-sm-8" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="form-field-1">波特率</label>
						<div class="col-sm-9">
							<select id="baudrate" name="baudrate" value="${com.baudrate }"
								class="col-xs-10 col-sm-8">
								<option value="300">300 Baud</option>
								<option value="600">600 Baud</option>
								<option value="1200">1200 Baud</option>
								<option value="2400">2400 Baud</option>
								<option value="4800">4800 Baud</option>
								<option value="9600">9600 Baud</option>
								<option value="14400">14400 Baud</option>
								<option value="19200">300 Baud</option>
								<option value="38400">38400 Baud</option>
								<option value="56000">56000 Baud</option>
								<option value="57600">57600 Baud</option>
								<option value="115200">115200 Baud</option>
								<option value="128000">128000 Baud</option>
								<option value="256000">256000 Baud</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="form-field-1">校验位</label>
						<div class="col-sm-9">
							<select value="${com.parity }" class="col-xs-10 col-sm-8">
								<option value="ODD">ODD</option>
								<option value="ODD">ODD</option>
								<option value="NONE">NONE</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="form-field-1">数据位</label>
						<div class="col-sm-9">
							<select value="${com.databits }" id="parity" name="parity"
								class="col-xs-10 col-sm-8">
								<option value="8">8 Data bits</option>
								<option value="7">7 Data bits</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="form-field-1">停止位</label>
						<div class="col-sm-9">

							<select value="${com.stopbit }" id="stopbit" name="stopbit"
								class="col-xs-10 col-sm-8">
								<option value="1">1 Stop Bit</option>
								<option value="2">2 Stop Bit</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right"
							for="form-field-1">备注</label>
						<div class="col-sm-9">
							<input type="text" id="remark" name="remark"
								value="${com.remark }" class="col-xs-10 col-sm-8" />
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
	<script src="../resources/js/duan/vcollectfield/saveUI.js"></script>
</body>
</html>