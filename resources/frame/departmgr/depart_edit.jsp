<%@ page language="java" pageEncoding="UTF-8"%>
<!-- Content Header (Page header) -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<style type="text/css">
h1,h3,h4{
	font-family: 微软雅黑 !important;
}
</style>
<section class="content-header">
	<h1>部门管理 <small>编辑部门</small></h1>
	<ol class="breadcrumb">
		<li><i class="fa fa-dashboard"></i></li>
		<li><a href="javascript:;" title="返回部门列表" onclick="pageLoad('${pageContext.request.contextPath}/department/toDepart.do');">部门管理列表</a></li>
		<li class="active">编辑部门</li>
	</ol>
</section>
<section class="content">
	<div class="row">
		<form id="frmEditDepart" action="${pageContext.request.contextPath}/department/editDepart" method="post" onkeydown="if(event.keyCode==13) return false;">
			<div class="col-md-6">
				<!-- Form Element sizes -->
				<div class="box box-primary">
					<div class="box-header with-border">
						<h3 class="box-title">编辑部门</h3>
					</div>
					<c:forEach var="depart" items="${list}">
					
					<div class="box-body">
						<div class="input-group">
							<span class="input-group-addon"><span class="fa fa-group"></span></span>
							<input type="text" class="form-control" placeholder="部门名称" id="departName" name="departName" value="${depart.qmcDName}" autocomplete="off">
						</div>
						<br />
						<div class="input-group">
							<span class="input-group-addon"><span class="fa fa-pencil-square-o"></span></span>
							<input type="text" class="form-control" placeholder="部门备注" id="departRemark" name="departRemark" value="${depart.qmcDRemark}" autocomplete="off">
						</div>
						<br />
					</div>
					<!-- /.box-body -->
					
					<input type="hidden" id="qmcDId" value="${qmcDId}"/>
					</c:forEach>


					<div class="box-footer">
						<button type="button" class="btn btn-primary" onclick="editDepart();">提交</button>
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
	
	function editDepart(){
			
		var action = $("#frmEditDepart").attr("action"); //表单
		var type = $("#frmEditDepart").attr("method");//表单方式
		var qmcDId=$("#qmcDId").val();
		var departName =$("#departName").val();
		var departRemark=$("#departRemark").val();
		
		if(departName ==""||departName ==null){
			$('div[class="modal-body"]').html("请输入部门名称");
			$('#myModal').modal();
			return;
		}
		
		var formData =new FormData($("#frmEditDepart")[0]);
		formData.append("departName",departName);
		formData.append("departRemark",departRemark);
		
		$.ajax({
			url:action,
			type:type,
			dataType:'json',
			data:{
				"qmcDId":qmcDId,
				"departName":departName,
				"departRemark":departRemark
			},
			
			success : function(data) {
				if (data == "1") {
					
					$('div[class="modal-body"]').html("编辑成功");
					$('#myModal').modal();
					$("#myModal").on('hide.bs.modal', function() {
						pageLoad("${pageContext.request.contextPath}/department/toDepart.do");
						$("div.modal-backdrop").hide();
					});
				} else if(data =="3"){
						$('div[class="modal-body"]').html("部门已存在");
						$('#myModal').modal();
				}
			},
			error : function(data) {
				$('div[class="modal-body"]').html("请重新填写");
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

