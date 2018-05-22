<%@ page language="java" pageEncoding="UTF-8"%>
<!-- Content Header (Page header) -->

<style type="text/css">
h1,h3,h4{
	font-family: 微软雅黑 !important;
}
</style>
<section class="content-header">
	<h1>部门管理 <small>添加部门</small></h1>
	<ol class="breadcrumb">
		<li><i class="fa fa-dashboard"></i></li>
		<li><a href="javascript:;" title="返回部门列表" onclick="pageLoad('${pageContext.request.contextPath}/department/toDepart.do');">部门管理列表</a></li>
		<li class="active">添加部门</li>
	</ol>
</section>
<section class="content">
	<div class="row">
		<form id="frmAddDepart" action="${pageContext.request.contextPath}/department/addDepart" method="post" onkeydown="if(event.keyCode==13) return false;">
			<div class="col-md-6">
				<!-- Form Element sizes -->
				<div class="box box-primary">
					<div class="box-header with-border">
						<h3 class="box-title">添加部门</h3>
					</div>
					<div class="box-body">
						<div class="input-group">
							<span class="input-group-addon"><span class="fa fa-group"></span></span>
							<input type="text" class="form-control" placeholder="部门名称" id="departName" name="departName" autocomplete="off">
						</div>
						<br />
						<div class="input-group">
							<span class="input-group-addon"><span class="fa fa-pencil-square-o"></span></span>
							<input type="text" class="form-control" placeholder="部门备注" id="departRemark" name="departRemark" autocomplete="off">
						</div>
						<br />
					</div>
					<!-- /.box-body -->


					<div class="box-footer">
						<button type="button" class="btn btn-primary" onclick="addDepart();">创建</button>
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
	
	function addDepart(){
			
		var action = $("#frmAddDepart").attr("action"); //表单
		var type = $("#frmAddDepart").attr("method");//表单方式
		var departName =$("#departName").val();
		var departRemark=$("#departRemark").val();
		
		if(departName ==""||departName ==null){
			$('div[class="modal-body"]').html("请输入部门名称");
			$('#myModal').modal();
			return;
		}
		
		var formData =new FormData($("#frmAddDepart")[0]);
		formData.append("departName",departName);
		formData.append("departRemark",departRemark);
		
		$.ajax({
			url:action,
			type:type,
			dataType:'json',
			data:{
				"departName":departName,
				"departRemark":departRemark
			},
			
			success : function(data) {
				if (data == "1") {
					
					$('div[class="modal-body"]').html("添加成功");
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

