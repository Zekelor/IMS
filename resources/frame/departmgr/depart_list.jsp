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
	<h1><small>部门列表</small></h1>
	<ol class="breadcrumb">
		<li><i class="fa fa-dashboard"></i></li>
		<li class="active">部门列表</li>
	</ol>
</section>
<c:set var="count" value="1" />
<!-- Main content -->
<section class="content">
	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<div class="box-header">
					<button type="button" id="addBtn" class="btn btn-primary">添加部门</button>
				
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<table id="example" class="table  table-striped table-hover dataTable no-footer" >
						<thead>
							<tr>
								<th>部门名称</th>
								<th>部门备注</th>
								<th>创建人</th>
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
	 
	 var t =$("#example").dataTable({
			"processing": true,
	      	"serverSide": true,
	      	"order"	:[[3,"desc"]],
			language:{
				"processing":"数据加载中",
	      		"zeroRecords":"暂无数据",
				"lengthMenu":"每页 _MENU_	条记录",
				"info":"第 _PAGE_ 页(总共 _PAGES_ 页) 共_TOTAL_条",
				"search": "在当前页搜索部门名称  _INPUT_", 
				
			},
			ajax:"/department/queryDepart",
			pageLength:10,
			columns: [
			{
				"data": "qmcDName"
			},{
				"data":"qmcDRemark"
			},{
				"data":"qmcCreater"
			},{
				"data":"qmcCreateTime"
			},{
				"data":"qmcDId",
				"render":function(data,type,row,meta){
					if(role=="1"){
					  	//return '<a href=\"javaScript:toEdit('+data+');\">编辑</a> <a href=\"javaScript:toDel('+data+');\" style=\"margin-left:20px;\" >删除</a>';
						return '<a href=\"javaScript:toEdit('+data+');\">编辑</a>';
					}
				}
				
			}]
		});
		
	});
	
	
	$("#addBtn").click(function() {

		var url = "${pageContext.request.contextPath}/department/toAdd.do";
		//console.log(url);
		$('div[class="content-wrapper"]').load(url);

	});
	

	function toEdit(id) {
		var url = "${pageContext.request.contextPath}/department/toEdit.do?qmcDId="+id;
		$('div[class="content-wrapper"]').load(url);
	}

	function toDel(id) {
		$('#myModal').modal();
		$("#confirm").click(function() {
		
			$.ajax({
				type : "POST",
				url : "/department/deleteDepart?qmcDId="+id,
				cache : false,
				//dataType : "json",
				//data : {},
				success : function(data) {
					console.log(data);
					
					if (data == "1") {
						$("#myModal").modal("hide");
						pageLoad("${pageContext.request.contextPath}/department/toDepart.do");
						$("div.modal-backdrop").hide();
					}else {
						console.log("删除失败" + data);
					}

				}
			});
		});
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