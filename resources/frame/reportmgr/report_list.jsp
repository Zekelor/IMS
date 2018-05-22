<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- Content Header (Page header) -->
<style type="text/css">
h1, h3 {
	font-family: 微软雅黑 !important;
}
h3>span{
	margin-right:10px;
}
</style>

<section class="content-header">
	<h1><small>报告任务列表</small></h1>
	<ol class="breadcrumb">
		<li><i class="fa fa-dashboard"></i></li>
		<li class="active">报告任务列表</li>
	</ol>
</section>
<c:set var="count" value="1" />
<!-- Main content -->
<section class="content">
	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<div class="box-header">
					<button type="button" id="addBtn" class="btn btn-primary">添加报告</button>
				
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<table id="example" class="table  table-striped table-hover dataTable no-footer" >
						<thead>
							<tr>
								<th>报告名称</th>
								<th>报告描述</th>
								<th>上传人</th>
								<th>更新时间</th>
								<th>创建时间</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody></tbody>
						
					</table>
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
<input type="hidden" id="role" name="role" value="${role }"/>
<input type="hidden" id="loginuser" name="loginuser" value="${sessionScope.username}"/>

<script>
	$(document).ready(function() {
	 var loginUser =$("#loginuser").val();
	 var role =$("#role").val();
	 //console.log("role:"+role);
	 //console.log("loginUser:"+loginUser);
	 var t =$("#example").dataTable({
			"processing": true,
	      	"serverSide": true,
	      	"order"	:[[4,"desc"]],
			language:{
				"processing":"数据加载中",
	      		"zeroRecords":"暂无数据",
				"lengthMenu":"每页 _MENU_	条记录",
				"info":"第 _PAGE_ 页(总共 _PAGES_ 页) 共_TOTAL_条",
				"search": "在当前页搜索报告名称、报告描述  _INPUT_", 
				
			},
			ajax:"/report/queryReport",
			pageLength:10,
			columns: [
			{
				"data": "qmcReportName",
				"width":"120"
			},{
				"data":"qmcReportDesc"
			},{
				"data":"qmcCreater",
				"width":"120"
			},{
				"data":"qmcUpdateTime",
				"width":"150"
			},{
				"data":"qmcCreateTime",
				"width":"150"
			},{
				"data":"qmcRId",
				"width":"150",
				"render":function(data,type,row,meta){
					var creater =row.qmcCreater;
					var cRole =row.qmcRole;
					var rid =row.qmcRId;
					var filePath =row.qmcReportFilePath;
					var fileName =filePath.substring(filePath.lastIndexOf("/")+1);
					
					//添加人拥有最高权限
					if(role=="1"){
						if(creater==loginUser){
							return '<a href=\"javaScript:toEdit('+data+');\">编辑</a> <a href=\"javaScript:toDel('+data+');\" style=\"margin-left:20px;\" >删除</a><a href=\"${pageContext.request.contextPath}/report/download?id='+rid+'\" style=\"margin-left:20px;\" target="_self" >下载附件</a>';
						
						}else {
							if(cRole=="1"){
								return '<a href=\"${pageContext.request.contextPath}/report/download?id='+rid+'\" target="_self" >下载附件</a>';
							}else{
								return '<a href=\"javaScript:toDel('+data+');\">删除</a><a href=\"${pageContext.request.contextPath}/report/download?id='+rid+'\" style=\"margin-left:20px;\"  target="_self" >下载附件</a>';
							}
						}
					}else{
						if(creater==loginUser){
							return '<a href=\"javaScript:toEdit('+data+');\">编辑</a> <a href=\"javaScript:toDel('+data+');\" style=\"margin-left:20px;\" >删除</a><a href=\"${pageContext.request.contextPath}/report/download?id='+rid+'\" style=\"margin-left:20px;\" target="_self" >下载附件</a>';
						}else{
							return '<a href=\"${pageContext.request.contextPath}/report/download?id='+rid+'\" target="_self" >下载附件</a>';
						}
					}
				}
			}]
		});
		
	});
	
	
	$("#addBtn").click(function() {

		var url = "${pageContext.request.contextPath}/report/toAdd.do";
		//console.log(url);
		$('div[class="content-wrapper"]').load(url);

	});
	
	function queryRoleType(creater){
		$.ajax({
			type:"POST",
			url:"/report/queryRoleType?userName="+creater,
			cache:false,
			success:function(data){
				return data;			
			}
		});
	}

	function toEdit(id) {
		var url = "${pageContext.request.contextPath}/report/toEdit.do?qmcRId="+id;
		$('div[class="content-wrapper"]').load(url);
	}

	function toDel(id) {
		$('#myModal').modal();
		$("#confirm").click(function() {
		
			$.ajax({
				type : "POST",
				url : "/report/deleteReport?qmcRId="+id,
				cache : false,
				//dataType : "json",
				//data : {},
				success : function(data) {
					console.log(data);
					
					if (data == "1") {
						$("#myModal").modal("hide");
						pageLoad("${pageContext.request.contextPath}/report/toReport.do");
						$("div.modal-backdrop").hide();
					}else {
						console.log("删除失败" + data);
					}

				}
			});
		});
	}
	
	function toDownload(id){
		var url = "${pageContext.request.contextPath}/report/download?id="+id;
		$('div[class="content-wrapper"]').load(url);
	}

	function pageLoad(url) {
		$('div[class="content-wrapper"]').load(url);
	}
</script>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">提示</h4>
			</div>
			<form id="delForm"  action=" " method="post">
			<div class="modal-body">确定是否删除此记录?
				
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary" id="confirm">确定</button>
			</div>
			</form>
		</div>
	</div>
</div>