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
	<h1>日志管理    <small>登录日志列表</small></h1>	
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
				<form id="frm-example" action="">
					<table id="example" class="table  table-striped table-hover dataTable no-footer" cellspacing="0" width="100%">
						<thead>
							<tr>
								<!-- <th>客户端编号</th>
								<th>QQ账号</th>
								<th>登录IP</th>
								<th>最近登录时间</th> -->
								<th>用户</th>
								<th>用户操作</th>
								<th>登录IP</th>
								<th>最近登录时间</th>
							</tr>
						</thead>
						<tbody></tbody>
						
					</table>
					</form>
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
	 	
	 var t =$("#example").dataTable({
			"processing": true,
	      	"serverSide": true,
	      	"order"	:[[3,"desc"]],
			language:{
				"lengthMenu":"每页 _MENU_	条记录",
				"info":"第 _PAGE_ 页(总共 _PAGES_ 页) 共_TOTAL_条",
				"search": "在当前页搜索用户、登录IP  _INPUT_", 
				
			},
			ajax:"/servlet/queryLogTable" ,
			"lengthMenu":[10,20,50,100],
			pageLength : 20,
			columns: [
			{
				"data": "qmcLoginName"
			},{
				"data":"qmcDesc"
			},{
				"data":"qmcLoginIp"
			},{
				"data":"qmcLastDate"
			}]
		});
		
	});
	
	function pageLoad(url) {
		$('div[class="content-wrapper"]').load(url);
	}

</script>