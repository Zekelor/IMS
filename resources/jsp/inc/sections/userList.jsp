<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>数据列表 <small>用户列表</small></h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> 主页</a></li>
		<li><a href="#">数据列表</a></li>
		<li class="active">用户列表</li>
	</ol>
</section>
<c:set var="count" value="1" />
<!-- Main content -->
<section class="content">
	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<div class="box-header">
					<h3 class="box-title">用户账户信息列表</h3>
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<div class="row">
						<div class="col-sm-6">
							<div class="dataTables_length" id="example1_length">
								<label>每页显示 
									<select id="pageSize" name="pageSize" aria-controls="example1" class="form-control input-sm" onchange="flipPage(1);">
										<option value="20">20</option>
										<option value="50">50</option>
										<option value="100">100</option>
									</select> 条
								</label>
							</div>
						</div>
						<!--
						<div class="col-sm-6">
							<div id="example1_filter" class="dataTables_filter">
								<label>搜索:<input type="search" class="form-control input-sm" placeholder="" aria-controls="example1"></label>
							</div>
						</div>
						-->
					</div>
					<div class="row">
						<div class="col-sm-12">
						<c:if test="${userList ne null and fn:length(userList) gt 0}">
							<table id="example1" class="table table-bordered table-striped table-hover dataTable" role="grid" aria-describedby="example1_info">
								<thead>
									<tr>
										<th class="sorting" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-sort="ascending" aria-label="编号: activate to sort column descending">编号</th>
										<th class="sorting" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-sort="ascending" aria-label="用户名: activate to sort column descending">用户名</th>
										<th class="sorting" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-sort="ascending" aria-label="密码: activate to sort column descending">密码</th>
										<th class="sorting" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-sort="ascending" aria-label="QQ群号: activate to sort column descending">QQ群号</th>
										<th class="sorting" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-sort="ascending" aria-label="手机号码: activate to sort column descending">手机号码</th>
										<th class="sorting" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-sort="ascending" aria-label="盐: activate to sort column descending">盐</th>
										<th class="sorting" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-sort="ascending" aria-label="操作: activate to sort column descending">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="user" items="${userList}">
									<tr>
										<td>${user.qmcUid}</td>
										<td>${user.qmcUname}</td>
										<td>******</td>
										<td>${user.qmcNo}</td>
										<td>${user.qmcTel}</td>
										<td>${user.qmcAccessToken}</td>
										<td>&nbsp;</td>
									</tr>
									</c:forEach>
								</tbody>
							</table>
						</c:if>
						</div>
					</div>
					<div class="row">
						<!--
						<div class="col-sm-5">
							<div class="dataTables_info" id="example1_info" role="status" aria-live="polite">显示 ${pageNumber * pageSize - pageSize + 1} 到 ${pageNumber * pageSize - pageSize + pageSize} 条, 共 ${total} 条</div>
						</div>
						-->
						<div class="col-sm-12">
							<div class="dataTables_paginate paging_simple_numbers" id="example1_paginate">
								<ul class="pagination">
									<c:if test="${pageCount ne null and pageCount > 0}">
										<!--
										<li class="paginate_button previous${pageNumber eq 1 ? ' disabled' : ''}" id="example1_previous"><a href="javascript:;" aria-controls="example1" data-dt-idx="0" tabindex="0">上一页</a></li>
										-->
										<c:forEach var="pn" step="1" begin="1" end="${pageCount}">
											<li class="paginate_button${pageNumber eq pn ? ' active' : ''}"><a href="javascript:;" onclick="flipPage(${pn});" aria-controls="example1" data-dt-idx="${pn}" tabindex="0">${pn}</a></li>
										</c:forEach>
										<!--
										<li class="paginate_button next${pageNumber eq pageCount ? ' disabled' : ''}" id="example1_next"><a href="javascript:;" aria-controls="example1" data-dt-idx="${pageCount + 1}" tabindex="0">下一页</a></li>
										-->
									</c:if>
								</ul>
							</div>
						</div>
					</div>
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
<script>
	$(function() {
		/*
		$("#example1").DataTable({
			"paging" : true, 
			"lengthChange" : false,
			"searching" : false,
			"ordering" : true,
			"info" : true,
			"autoWidth" : false, 
			"language" : {
				"paginate" : {
					"first": "第一页", 
					"previous": "上一页", 
					"next" : "下一页", 
					"last": "末页"
				}
			}
		});
		*/
		$('#example1').DataTable({
			"paginate": false, 
			"language": {
				"info": "显示第 _START_ 到第 _END_ 条, 共 _TOTAL_ 条", 
				"search": "在当前页过滤 _INPUT_", 
				"zeroRecords": "没有匹配的数据", 
				"infoFiltered": " - 从 _MAX_ 条数据中过滤", 
				"infoEmpty": "没有符合条件的数据"
			}
		});
	});
	
	$('#pageSize').val('${pageSize}');
	
	function flipPage(pageNumber) {
		$('div[class="content-wrapper"]').load('${pageContext.request.contextPath}/user/userList.do', {
			pageSize: $('#pageSize').val(), 
			pageNumber: pageNumber
		}, function () {});
	}
</script>