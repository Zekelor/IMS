<%@ page language="java" pageEncoding="UTF-8"%>
<!-- Content Header (Page header) -->

<style type="text/css">
h1,h3,h4{
	font-family: 微软雅黑 !important;
}
.select2-container--default .select2-selection--single {
	   border: 1px solid #d2d6de!important;
       border-radius: 0px;
       height: 34px;
    	padding: 6px 12px;
}
.select2-container--default .select2-selection--single .select2-selection__arrow{
	top: 4px!important;
}

</style>
<section class="content-header">
	<h1>用户管理 <small>添加用户</small></h1>
	<ol class="breadcrumb">
		<!-- <li><a href="#"><i class="fa fa-dashboard"></i></a></li>
		<li><a href="#">用户管理</a></li>
		<li class="active">添加用户</li> -->
	</ol>
</section>
<section class="content">
	<div class="row">
		<form id="frmAddUser" action="${pageContext.request.contextPath}/servlet/UserAddServlet" method="post">
			<div class="col-md-6">
				<!-- Form Element sizes -->
				<div class="box box-success">
					<div class="box-header with-border">
						<h3 class="box-title">用户账户信息</h3>
					</div>
					<div class="box-body">
						<div class="input-group">
							<span class="input-group-addon"><span class="fa fa-user"></span></span>
							<input type="text" class="form-control" placeholder="用户名" id="userName" name="userName" autocomplete="off">
						</div>
						<br />
						<div class="input-group pwdck">
							<span class="input-group-addon"><span
								class="fa fa-unlock-alt"></span></span> <input type="password"
								class="form-control" placeholder="密码" id="password" name="password" autocomplete="off">
						</div>
						<br />
						<div class="input-group pwdck">
							<span class="input-group-addon"><span class="fa fa-unlock-alt"></span></span> 
							<input type="password" class="form-control" placeholder="确认密码" id="confirmPassword" name="confirmPassword" autocomplete="off">
						</div>
						<br />
						<div class="input-group">
							<span class="input-group-addon"><span class="fa fa-phone"></span></span>
							<input type="text" class="form-control" placeholder="联系方式" id="mobileNumber" name="mobileNumber" autocomplete="off">
						</div>
						<br/>
						
						<!-- <div class="input-group">
							<span class="input-group-addon"><span class="fa fa-users"></span></span>
							<input type="text" class="form-control" placeholder="QQ群号" id="qqGroupNo" name="qqGroupNo" autocomplete="off">
						</div> -->
						<!-- <div class="input-group">
							<span class="input-group-addon"><span class="fa fa-sitemap"></span></span>
							<input type="text" class="form-control" placeholder="所属角色" id="role" name="role" autocomplete="off">
						</div>
						 -->
						<div class="input-group">
							<span class="input-group-addon"><span class="fa fa-sitemap"></span></span> 
							<select class="form-control selectpicker " id="role" name="role" value="1">
								<option value="1">系统管理员</option>
								<option value="2">支队</option>
								<option value="3">大队</option>
							</select>
						</div>

						<br />
						
						<div class="input-group">
							<span class="input-group-addon"><span class="fa fa-group"></span></span>
							<!-- <input type="text" class="form-control" placeholder="部门" id="department" name="department" autocomplete="off"> -->
							<select class="form-control select2 "  id ="department" name ="department">
								
							</select>
						
						</div>
						<br />
						
					</div>
					<!-- /.box-body -->
					<div class="box-footer">
						<button type="button" class="btn btn-primary" onclick="addUser();">创建</button>
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
<input type="hidden" id="roleType" value="" />

<script>
	$(document).ready(function(){
	queryDepart();
	
	//标签选择数据
	
	});
	
	$(".select2").select2();
	
	//查询部门
	function queryDepart() {
		//var qmcGId =selectedId;
		var data = "";
		$.ajax({
			type : "POST",
			url : "/department/queryAddDepart",
			cache : false,
			dataType : "json",

			success : function(data) {
				$(".select2").select2({
					data : data

				});
				console.log(data)
			}
		});

	}

	function addUser() {
		var isGoingOn = "1";
		var action = $('#frmAddUser').attr('action'); //表单
		var type = $('#frmAddUser').attr('method');//表单方式
		var userName = $('#userName').val();//用户名
		var password = $('#password').val();//密码
		var confirmPassword = $('#confirmPassword').val(); //确认密码
		//var qqGroupNo = $('#qqGroupNo').val();//QQ
		var mobileNumber = $('#mobileNumber').val();//手机号
		var role = $("#role").val();
		var department = $("#department").val();
		console.log("department"+department);

		if (password != confirmPassword) {
			$(".pwdck").addClass("has-error");
			isGoingOn == "0";
			return;
		} else {
			$(".pwdck").removeClass("has-error");
			isGoingOn == "1";
		}

		if (userName != null && password != null && mobileNumber != null && role != null) {
			isGoingOn == "1";
		}
		//console.log(isGoingOn);
		if (isGoingOn == "1") {
			if (userName == "") {
				$('#errorModal').modal();
			} else {
				checkExsit(userName);
			}

		} else {
			$('#errorModal').modal();
		}
	}

	function submit() {
		var action = $('#frmAddUser').attr('action'); //表单
		var type = $('#frmAddUser').attr('method');//表单方式
		var userName = $('#userName').val();//用户名
		var password = $('#password').val();//密码
		var confirmPassword = $('#confirmPassword').val(); //确认密码
		//var qqGroupNo = $('#qqGroupNo').val();//QQ
		var mobileNumber = $('#mobileNumber').val();//手机号
		var role = $("#role").val();
		var department = $("#department").val();
		if (userName == "" && password == "" && mobileNumber == "" && role == "") {
			$('#errorModal').modal();
		} else {
			$.ajax({
				url : action,
				dataType : "html",
				type : type,
				data : {
					userName : userName,
					password : password,
					confirmPassword : confirmPassword,
					//qqGroupNo: qqGroupNo, 
					mobileNumber : mobileNumber,
					role : role,
					department : department
				},
				success : function(data) {
					console.log(data);
					if (data == "1") {
						$('#myModal').modal();

						$("#myModal").on('hide.bs.modal', function() {
							pageLoad("${pageContext.request.contextPath}/servlet/toUserList.do");
							$("div.modal-backdrop").hide();
						});
					} else {
						alert(data);
					}
				},
				error : function(data) {
					$('#errorModal').modal();
				}
			});
		}
	}

	//检查用户名是否存在
	function checkExsit(userName) {
		$.ajax({
			type : "POST",
			url : "/servlet/queryUserNameExsit?userName=" + encodeURIComponent(userName),
			cache : false,
			dataType : "json",

			success : function(data) {
				if (data == "1") {
					submit();

				} else {
					$("#dulModal").modal();
				}
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
			<div class="modal-body">添加用户成功</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal" class="btn btn-primary">确定</button>
			</div>
		</div>
	</div>
</div>

<!-- Modal -->
<div class="modal fade" id="errorModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">提示</h4>
			</div>
			<div class="modal-body">请重新输入</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal" class="btn btn-primary">确定</button>
			</div>
		</div>
	</div>
</div>

<!-- Modal -->
<div class="modal fade" id="dulModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">提示</h4>
			</div>
			<div class="modal-body">用户名已存在</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal" class="btn btn-primary">确定</button>
			</div>
		</div>
	</div>
</div>