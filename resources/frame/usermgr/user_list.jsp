<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- Content Header (Page header) -->
<style type="text/css">
h1, h3 {
	font-family: 微软雅黑 !important;
}
</style>
<section class="content-header">
	<h1>用户管理    <small>用户列表</small></h1>
	<ol class="breadcrumb">
		<!-- <li><a href="#"><i class="fa fa-dashboard"></i></a></li>
		<li><a href="#">用户管理</a></li>
		<li class="active">用户列表</li> -->
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
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<table id="example" class="table  table-striped table-hover dataTable no-footer" cellspacing="0" width="100%">
						<thead>
							<tr>
								<th>用户名</th>
								<th>密码</th>
								<th>编号</th>
								<th>手机号</th>
								<!-- <th>token</th> -->
								<th>部门</th>
								<th>角色</th>
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
	 var loginuser =$("#loginuser").val()
	 console.log(loginuser)
		var t =$("#example").dataTable({
			language:{
				"lengthMenu":"每页 _MENU_	条记录",
				"info":"第 _PAGE_ 页(总共 _PAGES_ 页)",
				"search": "在当前页搜索用户名、部门、手机号 _INPUT_", 
				
			},
			ajax:"/servlet/queryAccount", 
			pageLength:10,
			columns: [
			{
				"data": "qmcUname"
			}, {
				"data": "qmcPwd",
				"searchable":false
			},{
				"data":"qmcNo",
				"searchable":false
			},{
				"data":"qmcTel"
			}/* ,{
				"data":"qmcAccessToken"
			} */,{
				"data":"qmcDepartment"
			},{
				"data":"qmcRole",
				"render":function(data,type,full){
					var roleName=null;
					if(data ==1){
						roleName='管理员';
					}else if(data==2){
						roleName='支队';
					}else if(data==3){
						roleName='大队';
					}else{
					roleName='操作员';
					}
					return roleName;
				},
				"searchable":false
			},{
				"data":"id",
				"render":function(data,type,row,meta){
					var user =row.qmcUname;
					if(user ==loginuser){
						return '<a href=\"javaScript:toEdit('+data+');\">编辑</a>';				
					}else{
						return '<a href=\"javaScript:toEdit('+data+');\">编辑</a> <a href=\"javaScript:toDel('+data+');\" style=\"margin-left:50px;\" >删除</a>';
					}
				},
				"targets":3
				
			}],
			columnDefs:[{
				"targets":[1],
				"visible":false,
				"orderable":false
			},{
				"targets":[2],
				"visible":false
			}],
		});
		
		var role =$("#role").val();
		
		

	});
	
	
	function toEdit(id) {
		
		var url="${pageContext.request.contextPath}/servlet/toEdit.do?id="+id;
		console.log(url);
		$('div[class="content-wrapper"]').load(url);

	}
	
	function toDel(id){
		$('#myModal').modal();
		$("#confirm").click(function(){
		$.ajax({
			type:"POST",
				url:"/servlet/UserDelServlet?id="+ id,
				cache:false,
				dataType:"json",
				data:{},
				success:function(data){
					console.log(data);
					$("#myModal").modal("hide");
					$('div[class="content-wrapper"]').load("${pageContext.request.contextPath}/servlet/toUserList.do");
					$("div.modal-backdrop").hide();
					
				}	
		});
		});		
	}
	
	
	function queryAccount() {
		$.ajax({
			type : "POST",//请求方式
			url : "/servlet/queryAccount",//发送请求地址
			cache : false,
			dataType : "json",
			data : {},
			success : function(data) {
				console.log(data);
			}
		});
	}
</script>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<div id="alert" class="callout callout-success" style="display:none; margin-bottom: 0;">
						<p><i class="icon fa fa-check"></i>删除用户成功</p>
				</div>
			
				<!-- <button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button> -->
				<h4 class="modal-title" id="myModalLabel">提示</h4>
			</div>
		
			<div class="modal-body">确定是否删除此用户?
				
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
				<button id="confirm" type="button" class="btn btn-primary">确定</button>
			</div>
		</div>
	</div>
</div>