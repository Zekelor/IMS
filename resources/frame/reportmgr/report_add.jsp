<%@ page language="java" pageEncoding="UTF-8"%>
<!-- Content Header (Page header) -->

<style type="text/css">
h1,h3,h4{
	font-family: 微软雅黑 !important;
}
</style>
<section class="content-header">
	<h1>报告管理 <small>添加报告</small></h1>
	<ol class="breadcrumb">
		<li><i class="fa fa-dashboard"></i></li>
		<li><a href="javascript:;" title="返回报告管理列表" onclick="pageLoad('${pageContext.request.contextPath}/report/toReport.do');">报告管理列表</a></li>
		<li class="active">添加报告</li>
	</ol>
</section>
<section class="content">
	<div class="row">
		<form id="frmAddReport" action="${pageContext.request.contextPath}/report/addReport" enctype="multipart/form-data" method="post" onkeydown="if(event.keyCode==13) return false;">
			<div class="col-md-6">
				<!-- Form Element sizes -->
				<div class="box box-primary">
					<div class="box-header with-border">
						<h3 class="box-title">添加报告</h3>
					</div>
					<div class="box-body">
						<div class="form-group">
							<label >报告名称</label> 
							<input type="text" class="form-control" id="reportName" name="reportName" placeholder="请输入报告名称 ...">
						</div>
						<div class="form-group">
							<label >报告描述</label> 
							<!-- <input type="text" class="form-control" id="reportDesc" name="reportDesc" placeholder="请输入报告描述"> -->
							<textarea class="form-control" id="reportDesc" name="reportDesc" rows="5" placeholder="请输入报告描述 ..."></textarea>
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
					<!-- /.box-body -->


					<div class="box-footer">
						<button type="button" class="btn btn-primary" onclick="addReport();">提交</button>
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
	
	
	
	function addReport(){
			
		var action = $("#frmAddReport").attr("action"); //表单
		var type = $("#frmAddReport").attr("method");//表单方式
		var reportName =$("#reportName").val();
		var reportDesc =$("#reportDesc").val();
		var attachment =$("#attachment").val();
		if(reportName ==""||reportName ==null){
			$('div[class="modal-body"]').html("请输入报告名称");
			$('#myModal').modal();
			return;
		}
		console.log(attachment);
		var formData =new FormData($("#frmAddReport")[0]);
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
					/* $('#myModal').modal();
					
					$("#myModal").on('hide.bs.modal', function() {
						pageLoad("${pageContext.request.contextPath}/report/toReport.do");
						$("div.modal-backdrop").hide();
					}); */
					
					$('div[class="modal-body"]').html("添加成功");
					$('#myModal').modal();
					$("#myModal").on('hide.bs.modal', function() {
						pageLoad("${pageContext.request.contextPath}/report/toReport.do");
						$("div.modal-backdrop").hide();
					});
				} else if(data =="3"){
						$('div[class="modal-body"]').html("报告名称已存在");
						$('#myModal').modal();
				} else {
					//console.log("服务器错误："+data);
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
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">提示</h4>
			</div>
			<div class="modal-body">添加成功</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal" class="btn btn-primary">确定</button>
			</div>
		</div>
	</div>
</div>

<!-- Modal -->
<!-- <div class="modal fade" id="errorModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">提示</h4>
			</div>
			<div class="modal-body">请重新填写报告</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal" class="btn btn-primary">确定</button>
			</div>
		</div>
	</div>
</div> -->
