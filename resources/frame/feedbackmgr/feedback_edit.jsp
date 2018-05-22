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
	<h1>反馈管理 <small>编辑反馈</small></h1>
	<ol class="breadcrumb">
		<li><i class="fa fa-dashboard"></i></li>
		<li><a href="javascript:;" title="返回反馈列表" onclick="pageLoad('${pageContext.request.contextPath}/feedback/toFeedBack.do');">反馈列表</a></li>
		<li class="active">编辑反馈</li>
	</ol>
</section>
<section class="content">
	<div class="row">
		<form id="frmEditFeedBack" action="${pageContext.request.contextPath}/feedback/editFeedBack" enctype="multipart/form-data" method="post">
			<div class="col-md-6">
				<!-- Form Element sizes -->
				<div class="box box-primary">
					<div class="box-header with-border">
						<h3 class="box-title">编辑反馈</h3>
					</div>
					<c:forEach var="feedback" items="${list}">
					<div class="box-body">
						<div class="form-group">
							<label >反馈名称</label> 
							<input type="text" class="form-control" id="fdTitle" name="fdTitle" placeholder="请输入反馈名称" value="${feedback.qmcFdTitle }">
						</div>
						<div class="form-group">
							<label >反馈描述</label> 
							<textarea class="form-control" id="fdContent" name="fdContent" rows="3" placeholder="请输入反馈内容 ..." >${feedback.qmcFdContent }
								
							</textarea>
							
						</div>
						<div class="form-group">
							<label for="exampleInputFile">上传附件</label> 
							<input type="file" id="attachment" name="uploadFile">

						</div>
					</div>
					<input type="hidden" id="fdFId" name="fdFId" value="${feedback.qmcFId }"/>
					<input type="hidden" id="fdFilePath" name="fdFilePath" value="${feedback.qmcFdFilePath }"/>
					</c:forEach>
					<!-- /.box-body -->


					<div class="box-footer">
						<button type="button" class="btn btn-primary" onclick="editFeedBack();">提交</button>
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
	
	function editFeedBack(){
			
		var action = $("#frmEditFeedBack").attr("action"); //表单
		var type = $("#frmEditFeedBack").attr("method");//表单方式
		var fdFId =$("#fdFId").val();
		//var fdFilePath =$("#fdFilePath").val();
		var fdTitle =$("#fdTitle").val();
		var fdContent =$("#fdContent").val();
		console.log(fdFilePath)
		var formData =new FormData($("#frmEditFeedBack")[0]);
		formData.append("fdFId",fdFId);
		//formData.append("fdFilePath",fdFilePath);
		formData.append("fdTitle",fdTitle);
		formData.append("fdContent",fdContent);
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
						pageLoad("${pageContext.request.contextPath}/feedback/toFeedBack.do");
						$("div.modal-backdrop").hide();
					});
				} else if(data =="3"){
						$('div[class="modal-body"]').html("反馈名称已存在");
						$('#myModal').modal();
				} else {
					$('div[class="modal-body"]').html("请上传附件");
					$('#myModal').modal();
				}
			},
			error : function(data) {
				//$('#errorModal').modal();
				$('div[class="modal-body"]').html("请重新填写反馈内容");
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
