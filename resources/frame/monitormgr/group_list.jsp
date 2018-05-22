<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- Content Header (Page header) -->
<style type="text/css">
h1, h3 {
	font-family: 微软雅黑 !important;
}

.mr {
	margin-right: 20px;
}

.check {
	background-color: #286090;
	border-color: #204d74;
	color: #fff;
}

.check:hover {
	color: #fff !important;
}

.tag {
	/* border:1px solid #ccc;
	background-color:#e6e6e6; */
	
}
</style>
<section class="content-header">
	<h1>
		QQ群列表 <small></small>
	</h1>
	<ol class="breadcrumb">
		<li><i class="fa fa-dashboard"></i></li>
		<li><a href="javascript:;" onclick="pageLoad('${pageContext.request.contextPath}/monitor/toMonitor.do');">监控账号列表 </a></li>
		<li class="active">QQ群列表</li>
	</ol>
</section>
<c:set var="count" value="1" />
<!-- Main content -->
<section class="content">
	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<div class="box-header">
					<!-- <h3 class="box-title">用户账户信息列表</h3> -->
					<!-- <div class="btn-group">
                      <button type="button" class="btn btn-default ">Left</button>
                      <button type="button" class="btn btn-default ">Middle</button>
                      <button type="button" class="btn btn-default ">Right</button>
                    </div> -->
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<form id="frm-example" action="">
						<table id="example" class="table  table-striped table-hover dataTable no-footer" cellspacing="0" width="100%">
							<thead>
								<tr>
									<th></th>
									<th>群号</th>
									<th>群名称</th>
									<th>标签</th>
									<th>近一日聊天数</th>
									<th>一周聊天数</th>
									<th>监控账号</th>
								</tr>
							</thead>
							<tbody></tbody>

						</table>
					</form>
				</div>
				<!-- /.box-body -->
			</div>
			<!-- /.box -->
		</div>
		<!-- /.col -->
	</div>
	<!-- /.row -->
</section>
<!-- /.content -->
<!-- page script -->
<input type="hidden" id="role" name="role" value="${role }" />
<input type="hidden" id="loginuser" name="loginuser" value="${sessionScope.username}" />
<input type="hidden" id="qmcQQ" value="${qmcQQ}"/>

<script>
	$(document).ready(function() {
		var qmcQQ =$("#qmcQQ").val();
		console.log(qmcQQ)
		var t = $("#example").DataTable({
			"processing" : true,
			"serverSide" : true,
			//指定默认排序的列
			"order" : [ [ 3, "desc" ] ],
			//汉化
			language : {
				"processing" : "数据加载中",
				"zeroRecords" : "暂无数据",
				"lengthMenu" : "每页 _MENU_	条记录",
				"info" : "第 _PAGE_ 页(总共 _PAGES_ 页)   共 _TOTAL_ 条",
				"search" : "在当前页查询：群号、群名称、标签、监控账号  _INPUT_",

			},
			ajax :{
				type:"POST",
				url:"/monitor/queryGroup",
				data:{
					qmcQQ:qmcQQ
				}
			},
			"lengthMenu":[10,20,50,100],
			pageLength : 20,
			columns : [ {
				"data" : "qmcGId",
				"render" : function(data, type, row, meta) {
					return '<input type="checkbox" id="' + data + '" value="'+data+'" />';
				}

			}, {
				"data" : "qmcGroup",
				"render" : function(data, type, row, meta) {
					var groupName = row.qmcGroupName;
					var account = row.qmcQQ;
					var groupTag = row.qmcTag;
					return '<a href="javaScript:toDetail(\'' + account + '\', \'' + data + '\', \'' + groupName + '\',\'' + groupTag + '\');">' + data + '</a>';
				}
			}, {
				"data" : "qmcGroupName",
				"checkboxes" : {
					"selectRow" : true
				}
			}, {
				"data" : "qmcTag"
			}, {
				"data" : "qmcDayCount",
				"orderable" : false
			}, {
				"data" : "qmcWeekCount",
				"orderable" : false
			}, {
				"data" : "qmcQQ"
			} ],
			columnDefs : [ {
				"targets" : [ 0 ],
				"visible" : true,
				"orderable" : false,
				"checkboxes" : {
					"selectRow" : true
				}
			} ]
		});

	});

	//跳转至群账号详情
	function toDetail(qmcQQ, qmcGroup, qmcGroupName, qmcTag) {

		var url = "${pageContext.request.contextPath}/monitor/toDetail.do?qmcGroup=" + qmcGroup + "&qmcQQ=" + qmcQQ + "&qmcGroupName=" + encodeURIComponent(qmcGroupName) + "&qmcTag="+ encodeURIComponent(qmcTag);
		console.log(url);
		$('div[class="content-wrapper"]').load(url);

	}
</script>

<!-- 表单Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<div id="alert" class="callout callout-success"
					style="display:none; margin-bottom: 0;">
					<p>
						<i class="icon fa fa-check"></i>成功添加标签
					</p>
				</div>

				<div id="error" class="callout callout-danger"
					style="display:none; margin-bottom: 0;">
					<p>
						<i class="icon fa fa-ban"></i>请重新键入标签
					</p>
				</div>


				<!-- <button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button> -->
				<h4 class="modal-title" id="myModalLabel"></h4>
			</div>
			<form id="tagForm" action="" method="post">
				<div class="modal-body">

					<div class="form-group">
						<label>请选择标签</label> <select
							class="form-control select2 select2-hidden-accessible"
							multiple="" style="width: 100% ;" tabindex="-1"
							aria-hidden="true">

						</select>
					</div>

					<div class="box-footer" style="padding:0;">
						<label>新增标签</label>
						<div class="input-group">
							<input type="text" name="tagName" id="tagName"
								placeholder="请输入标签名称" class="form-control"> <span
								class="input-group-btn">
								<button type="button" id="addTag"
									class="btn btn-primary btn-flat">
									新增 <i class="fa fa-arrow-circle-right"></i>
								</button>
							</span>
						</div>
					</div>


				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default pull-left"
						data-dismiss="modal">Close</button>
					<button type="button" id="confirm" class="btn btn-primary"
						data-dismiss="modal">确定</button>
				</div>
			</form>
		</div>
	</div>
</div>




<!-- 提示Modal -->
<div class="modal fade" id="tipModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="tipModalLabel">提示</h4>
			</div>

			<div class="modal-body">请选中要标记的记录</div>
			<div class="modal-footer">
				<!-- <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button> -->
				<button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
			</div>

		</div>
	</div>
</div>