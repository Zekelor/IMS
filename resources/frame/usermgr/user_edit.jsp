<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
	<h1>用户管理 <small>编辑用户</small></h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> 主页</a></li>
		<li><a href="#">用户管理</a></li>
		<li class="active">编辑用户</li>
	</ol>
</section>
<section class="content">
	<div class="row">
		<form id="frmEditUser" action="${pageContext.request.contextPath}/servlet/UserEditServlet" method="post">
			<div class="col-md-6">
				<!-- Form Element sizes -->
				<div class="box box-success">
					<div class="box-header with-border">
						<!-- <h3 class="box-title">用户账户信息</h3> -->
					</div>
					<c:if test="${role eq '1' }">
					<c:forEach var="user" items="${userList}">
					<div class="box-body">
						<div class="input-group">
							<span class="input-group-addon"><span class="fa fa-user"></span></span>
							<input type="text" class="form-control" placeholder="用户名" id="userName" name="userName" value="${user.qmcUname }" disabled="disabled" autocomplete="off">
						</div>
						<br />
						
						<div class="input-group pwdck">
							<span class="input-group-addon"><span
								class="fa fa-unlock-alt"></span></span> 
								<input type="text" class="form-control" placeholder="密码" id="password" name="password" value="${user.qmcPwd }" autocomplete="off">
						</div>
						<br />
						<div class="input-group pwdck">
							<span class="input-group-addon"><span class="fa fa-unlock-alt"></span></span> 
							<input type="text" class="form-control" placeholder="确认密码" id="confirmPassword" name="confirmPassword"  autocomplete="off">
						</div>
						<br />
						<div class="input-group">
							<span class="input-group-addon"><span class="fa fa-phone"></span></span>
							<input type="text" class="form-control" placeholder="联系方式" id="mobileNumber" name="mobileNumber" value="${user.qmcTel }"autocomplete="off">
						</div>
						<br/>
						
						<%-- <c:if test="${role ne '0' }"> --%>
						<div class="input-group">
							<span class="input-group-addon"><span class="fa fa-sitemap"></span></span> 
							<select class="form-control" id="role" name="role" value="${user.qmcRole }" style="width: 100%;" tabindex="-1" aria-hidden="true">
								<option value="1">系统管理员</option>
								<option value="2">支队</option>
								<option value="3">大队</option>
							</select>
						</div>
						<input type="hidden" id="role" name="role" value="${user.qmcRole }">

						<br />
						
						<div class="input-group">
							<span class="input-group-addon"><span class="fa fa-group"></span></span>
							<%-- <input type="text" class="form-control" disabled placeholder="部门" id="department" name="department" value="${user.qmcDepartment }" autocomplete="off"> --%>
							<select class="form-control select2 " id ="department" name ="department">
								
							</select>
						</div>
						<br />
						<%-- </c:if> --%>
					</div>
					</c:forEach>
					</c:if>
					
					<c:if test="${role ne '1'}">
					<c:forEach var="no_auth" items="${no_authList}">
						<div class="box-body">
						<div class="input-group">
							<span class="input-group-addon"><span class="fa fa-user"></span></span>
							<input type="text" class="form-control" placeholder="用户名" id="userName" name="userName" value="${no_auth.qmcUname }" disabled="disabled" autocomplete="off">
						</div>
						<br />
						
						<div class="input-group pwdck">
							<span class="input-group-addon"><span
								class="fa fa-unlock-alt"></span></span> 
								<input type="text" class="form-control" placeholder="密码" id="password" name="password" value="${no_auth.qmcPwd }" autocomplete="off">
						</div>
						<br />
						<div class="input-group pwdck">
							<span class="input-group-addon"><span class="fa fa-unlock-alt"></span></span> 
							<input type="text" class="form-control" placeholder="确认密码" id="confirmPassword" name="confirmPassword"  autocomplete="off">
						</div>
						<br />
						<div class="input-group">
							<span class="input-group-addon"><span class="fa fa-phone"></span></span>
							<input type="text" class="form-control" placeholder="联系方式" id="mobileNumber" name="mobileNumber" value="${no_auth.qmcTel }"autocomplete="off">
						</div>
						<br/>
						
						<c:if test="${role ne '0' }">
						<div class="input-group" id="roleDiv">
							<span class="input-group-addon"><span class="fa fa-sitemap"></span></span> 
							<select class="form-control" disabled id="role" name="role" value="${no_auth.qmcRole }">
								<option class="op_1" value="1">系统管理员</option>
								<option class="op_2" value="2">支队</option>
								<option class="op_3" value="3">大队</option>
							</select>
						</div>
						<input type="hidden" id="no_auth_role" name="no_auth_role" value="${no_auth.qmcRole }">

						<br />
						
						<div class="input-group" id="depDiv">
							<span class="input-group-addon"><span class="fa fa-group"></span></span>
							<input type="text" class="form-control" disabled placeholder="部门" id="department" name="department" value="${no_auth.qmcDepartment }" autocomplete="off">
						</div>
						<br />
						</c:if>
					</div>
					</c:forEach>
					</c:if>
					
					<!-- /.box-body -->
					<div class="box-footer">
						<button type="button" class="btn btn-primary" onclick="updateUser();">修改</button>
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
<input type="hidden" id="roleG" value="${sessionScope.role }">
	
<script>
$(document).ready(function(){
	var user_role=$("#role").val();
	/* var no_auth_role =$("no_auth_role").val(); */

	/* var rootRole=$("option[value='"+user_role+"']");
		
		rootRole.attr("selected","selected"); */
		
		
	/* var guessRole =$("option[value='"+no_auth_role+"']");
		
		guessRole.attr("selected","selected"); */
		
	queryDepart();	
		
	var roleG =$("#roleG").val();
		if (roleG == "1") {
			$("#role").removeAttr("disabled");
			$("#department").removeAttr("disabled");
		}else{
			$("#roleDiv").hide();
			$("#depDiv").hide();
		}
	});
	
	$(".select2").select2();
	
	//查询部门
	function queryDepart() {
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
	
	function updateUser() {
		var roleG=$("#roleG").val();
		var isGoingOn="1";
		var action = $('#frmEditUser').attr('action'); //表单
		var type = $('#frmEditUser').attr('method');//表单方式
		var userName = $('#userName').val();//用户名
		var password = $('#password').val();//密码
		var confirmPassword = $('#confirmPassword').val(); //确认密码
		//var qqGroupNo = $('#qqGroupNo').val();//QQ
		var mobileNumber = $('#mobileNumber').val();//手机号
		if(roleG =="1"){
			var role =$("#role").val();
		}else{
			var role =$("#no_auth_role").val();
		}
		var department = $("#department").val();
		console.log("department"+department);
		if(password !=confirmPassword){
			$(".pwdck").addClass("has-error");
			isGoingOn=="0";
			return;
		}else{
			$(".pwdck").removeClass("has-error");
			isGoingOn=="1";
		}
		
		if(userName !=null||password !=null||mobileNumber !=null||role !=null){
			isGoingOn=="1";
		}else{
			isGoingOn =="0";
		}
		console.log(isGoingOn);
		if(isGoingOn=="1"){
		
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
				if (data=="1") {
					$('#myModal').modal();
					
					$("#myModal").on('hide.bs.modal',function(){
						if(roleG =="1"){
							pageLoad("${pageContext.request.contextPath}/servlet/toUserList.do");
						}else{
							location.href = "${pageContext.request.contextPath}/jsp/example/data.jsp";
						}
						$("div.modal-backdrop").hide();
					});
				}
				else {
					alert(data);
				}
			},
			error : function(data) {
				alert('服务器错误');
			}
		});
		
		}
		
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
			<div class="modal-body">修改用户成功</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal" class="btn btn-primary">确定</button>
			</div>
		</div>
	</div>
</div>