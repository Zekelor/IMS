<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- Content Header (Page header) -->

<style type="text/css">
h1,h3,h4{
	font-family: 微软雅黑 !important;
}
</style>
<section class="content-header">
	<h1>报告管理 <small>编辑报告</small></h1>
	<ol class="breadcrumb">
		<li><i class="fa fa-dashboard"></i></li>
		<li><a href="javascript:;" title="返回报告管理列表" onclick="pageLoad('${pageContext.request.contextPath}/report/toReport.do');">报告管理列表</a></li>
		<li class="active">编辑报告</li>
	</ol>
</section>
<section class="content">
	<div class="row">
		<form id="frmAddReport" action="${pageContext.request.contextPath}/report/editReport" enctype="multipart/form-data" method="post">
			<div class="col-md-6">
				<!-- Form Element sizes -->
				<div class="box box-primary">
					<div class="box-header with-border">
						<h3 class="box-title">编辑报告</h3>
					</div>
					<c:forEach var="report" items="${list}">
					<div class="box-body">
						<div class="form-group">
							<label >报告名称</label> 
							<input type="text" class="form-control" id="reportName" name="reportName" placeholder="请输入报告名称" value="${report.qmcReportName }">
						</div>
						<div class="form-group">
							<label >报告描述</label> 
							<%-- <input type="text" class="form-control" id="reportDesc" name="reportDesc" placeholder="请输入报告描述" value="${report.qmcReportDesc }"> --%>
							<textarea class="form-control" id="reportDesc" name="reportDesc" rows="5" placeholder="请输入报告描述 ..." >${report.qmcReportDesc }
								
							</textarea>
							
						</div>
						<div class="form-group">
							<label for="exampleInputFile">上传附件</label> 
							<input type="file" id="attachment" name="uploadFile">

							<!-- <p class="help-block">Example block-level help text here.</p> -->
						</div>
						<!-- <div class="checkbox">
							<label> <input type="checkbox"> Check me out
							</label>
						</div> -->
					</div>
					<input type="hidden" id="reportRId" name="reportRId" value="${report.qmcRId }"/>
					<input type="hidden" id="reportFilePath" name="reportFilePath" value="${report.qmcReportFilePath }"/>
					</c:forEach>
					<!-- /.box-body -->


					<div class="box-footer">
						<button type="button" class="btn btn-primary" onclick="editReport();">提交</button>
					</div>
				</div>
				<!-- /.box -->
			</div>
			<!-- /.col (left) -->
		</form>
	</div>
	<!-- /.row -->
</section>
<!-- /.content -->

<script>
	$(document).ready(function() {

	});
	
	function editReport(){
			
		var action = $("#frmAddReport").attr("action"); //表单
		var type = $("#frmAddReport").attr("method");//表单方式
		var reportRId =$("#reportRId").val();
		var reportFilePath =$("#reportFilePath").val();
		var reportName =$("#reportName").val();
		var reportDesc =$("#reportDesc").val();
		console.log(reportFilePath);
		var formData =new FormData($("#frmAddReport")[0]);
		formData.append("reportRId",reportRId);
		//formData.append("reportFilePath",reportFilePath);
		formData.append("reportName",reportName);
		formData.append("reportDesc",reportDesc);
		//console.log(formData);
		$.ajax({
			url:action,
			type:type,
			processData:false,
			contentType:false,
			data:formData,
			
			success : function(data) {
				if (data == "1") {
					$('#myModal').modal();
					
					$("#myModal").on('hide.bs.modal', function() {
						pageLoad("${pageContext.request.contextPath}/report/toReport.do");
						$("div.modal-backdrop").hide();
					});
				} else if(data =="3"){
						$('div[class="modal-body"]').html("报告名称已存在");
						$('#myModal').modal();
				} else {
					$('div[class="modal-body"]').html("请上传附件");
					$('#myModal').modal();
				}
			},
			error : function(data) {
				//$('#errorModal').modal();
				$('div[class="modal-body"]').html("请重新填写报告内容");
				$('#myModal').modal();
			}

		});
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
			<div class="modal-body">编辑成功</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal" class="btn btn-primary">确定</button>
			</div>
		</div>
	</div>
</div>
