<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- Content Header (Page header) -->
<style type="text/css">
h1, h3 {
	font-family: 微软雅黑 !important;
}

.mr {
	margin-right: 20px;
}

.check{
	background-color: #286090;
	border-color: #204d74;
	color:#fff;

}
.check:hover{
	color:#fff!important;
}
.tag{
	/* border:1px solid #ccc;
	background-color:#e6e6e6; */
}
</style>
<section class="content-header">
	<h1>
		QQ群管理 <small>QQ群列表</small>
	</h1>
	<ol class="breadcrumb">
		<li><i class="fa fa-dashboard"></i></li>
		<!-- <li><a href="#">QQ群管理 </a></li> -->
		<li class="active">QQ群列表</li>
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
					<!-- <div class="btn-group">
                      <button type="button" class="btn btn-default ">Left</button>
                      <button type="button" class="btn btn-default ">Middle</button>
                      <button type="button" class="btn btn-default ">Right</button>
                    </div> -->
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<form id="frm-example" action="">
						<table id="example"
							class="table  table-striped table-hover dataTable no-footer" cellspacing="0" width="100%">
							<thead>
								<tr>
									<th><input type="checkbox" id="checkAll" /></th>
									<th>群号</th>
									<th>群名称</th>
									<th>标签</th>
									<th>近一日聊天数</th>
									<th>一周聊天数</th>
									<th>监控账号</th>
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
<input type="hidden" id="role" name="role" value="${role }" />
<input type="hidden" id="loginuser" name="loginuser"
	value="${sessionScope.username}" />

<script>
	$(document).ready(function() {

				queryFilterTag();
				var role = $("#role").val()
				var selected = [];
				var selectedId = "";
				var isExsit = "";
				
				var t = $("#example").DataTable(
						{
							"processing" : true,
							"serverSide" : true,
							//指定默认排序的列
							"order" : [ [ 3, "desc" ] ],
							//汉化
							language : {
								"processing" : "数据加载中",
								"zeroRecords" : "暂无数据",
								"lengthMenu" : "每页 _MENU_	条记录",
								"info" : "第 _PAGE_ 页(总共 _PAGES_ 页)   共 _TOTAL_ 条",
								"search" : "在当前页查询：群号、群名称、标签、监控账号  _INPUT_",

							},
							ajax : "/servlet/queryGroup",
							"lengthMenu":[10,20,50,100],
							pageLength : 20,
							columns : [
									{
										"data" : "qmcGId",
										"render" : function(data, type, row, meta) {
											return '<input type="checkbox" id="' + data + '" value="'+data+'" />';
										}

									},
									{
										"data" : "qmcGroup",
										"render" : function(data, type, row, meta) {
											var groupName = row.qmcGroupName;
											var account = row.qmcQQ;
											var groupTag = row.qmcTag;
											return '<a href="javaScript:toDetail(\'' + account + '\', \'' + data + '\', \'' + groupName + '\',\''+ groupTag + '\');">' + data + '</a>';
										}
									}, {
										"data" : "qmcGroupName",
										"checkboxes" : {
											"selectRow" : true
										}
									}, {
										"data" : "qmcTag"
									}, {
										"data" : "qmcDayCount",
										"orderable" : false
									}, {
										"data" : "qmcWeekCount",
										"orderable" : false
									}, {
										"data" : "qmcQQ"
									} ],
							columnDefs : [ {
								"targets" : [ 0 ],
								"visible" : true,
								"orderable" : false,
								"checkboxes" : {
									"selectRow" : true
								}
							} ],

							//为表格中选中的咧增加属性
							rowCallback : function(row, data) {
								if ($.inArray(data.qmcGId, selected) !== -1) {
									$(row).addClass('selected');
								}
								$(row).attr("id", data.qmcGId);

								//console.log($(row));

							},
							fnDrawCallback:function(row,data){
								$("#checkAll").prop('checked', false);
							},
							"select" : {
								'style' : 'multi' //unknown
							}
						});

				//datatable增加选中效果
				$("#example tbody").on('click', 'tr', function() {
					var id = this.id;
					var index = $.inArray(id, selected);

					if (index === -1) {
						selected.push(id);
					} else {
						selected.splice(index, 1);
					}
					//多选
					$(this).toggleClass('selected');
					//单选
					/* if($(this).hasClass('selected')){
						$(this).removeClass("selected");
					}else{
						$("#example tr.selected").removeClass("selected");
						$(this).addClass('selected');
					} */

					selectedId = id;
					if ($(this).hasClass('selected')) {
						$(":checkbox", this).prop('checked', true);
					} else {
						$(":checkbox", this).prop('checked', false);
					}
					console.log($(this).hasClass('selected'));
					console.log(selected);
				});

				//管理员具有标记的权限
				if (role == "1") {
					var $select2 = $(".select2").select2();
					//增加群标记按钮
					$('<button type="button" id="tagBtn" class="btn btn-primary">群标记</button>').appendTo('.box-header').on('click', function(e) {
						if (!$("#example tr.selected").hasClass("selected")) {
							$('#tipModal').modal();
						} else {
							$('#myModal').modal();
							$("#myModal").on('shown.bs.modal', function() {
								queryTag();
							});
							$("#myModal").on("hide.bs.modal", function() {
								$select2.val(null).trigger("change");
							});

							//提交标签
							$("#confirm").click(function() {
								var tid = $select2.val();
								var gids = selected;
								//var gid =selectedId;
								console.log(tid);

								/* $("#error").slideDown(700, function() {
										setTimeout(function() {
											$("#error").slideUp(500);
										}, 500);
									}); */
								blindTag(gids, tid);
								$("#myModal").on('hidden.bs.modal', function() {
									pageLoad("${pageContext.request.contextPath}/servlet/toGroupList.do");
									//$("div.modal-backdrop").hide();
								});

							});

							//新增标签
							$("#addTag").click(function() {
								var tagName = $("#tagName").val();
								console.log(tagName)
								if (tagName != "") {
									queryTagExist(tagName);
								} else {
									//添加动画效果
									$("#error").slideDown(700, function() {
										setTimeout(function() {
											$("#error").slideUp(500);
										}, 500);
									});

								}
							});
						}
					});
				}

				//标签选择数据
				$(".select2").select2({
					data : []
				});
		
		//当前页全选
		$("#checkAll").click(function(event){
			selected.splice(0,selected.length);
			 var $tbr = $('table tbody tr'); 
			 $tbr.find('input').prop('checked',$(this).prop('checked'));  
			
			if ($(this).prop('checked')) {
				$tbr.find('input').parent().parent().addClass('selected');
				$("input[type='checkbox']").each(function(){
				var id = this.id;
				var index = $.inArray(id, selected);
					
				if (index === -1) {
					selected.push(id);
				} else {
					selected.splice(index, 1);
				}
				selectedId = id;
				});
			} else {
				$tbr.find('input').parent().parent().removeClass('selected');
			}

			/* $("input[type='checkbox']").each(function(){
				if($(this).attr('checked')){
					//$(this).attr('checked',false);
					$(this).parent().parent().removeClass("selected");
				}else{
					//$(this).attr('checked',true);
					$(this).parent().parent().addClass("selected");
					var id = this.id;
					var index = $.inArray(id, selected);
					
					if (index === -1) {
						selected.push(id);
					} else {
						selected.splice(index, 1);
					}
					selectedId = id;
				}
			}); */
			console.log(selected);
			event.stopPropagation();
		});

	});

	//跳转至群账号详情
	function toDetail(qmcQQ, qmcGroup, qmcGroupName, qmcTag) {

		var url = "${pageContext.request.contextPath}/servlet/toDetail.do?qmcGroup=" + qmcGroup + "&qmcQQ=" + qmcQQ + "&qmcGroupName=" + encodeURIComponent(qmcGroupName) + "&qmcTag="
				+ encodeURIComponent(qmcTag);
		console.log(url);
		$('div[class="content-wrapper"]').load(url);

	}

	//查询是否存在
	function queryTagExist(tagName) {

		$.ajax({
			type : "POST",
			url : "/servlet/queryTagExist?qmcTagName=" + encodeURIComponent(tagName),
			cache : false,
			dataType : "json",

			success : function(data) {
				if (data == "1") {
					addTag(tagName);

				} else {
					$("#error").slideDown(700, function() {
						setTimeout(function() {
							$("#error").slideUp(500);
						}, 500);
					});
				}
			}
		});

	}

	//列表页面展示标签
	function queryFilterTag() {
		var data = "";
		var selected = [];

		$.ajax({
			type : "POST",
			url : "/servlet/queryTag",
			cache : false,
			dataType : "json",

			success : function(data) {
				//if($("#btG").length > 0){
				if (data.length > 0) {
					$("#tagBtn").after("<div style=\"margin-left:30px;\"class=\"btn-group\" id=\"btG\"></div>");
					//$("#tagBtn").after("<div style=\"margin-left:30px;\"class=\"btn-group\" id=\"btG\"><button type=\"button\" class=\"btn btn-default\">Action</button></div>");
					var html = "";
					if (data.length > 0) {
						html += "<h3 class=\"box-title\" style=\"float:left;margin-top:8px;\">请选择已有标签：</h3>";
					}
					for (var i = 0; i < data.length; i++) {

						html += "<a style=\"margin-right:5px;\" type=\"button\" id=tag_"+data[i].id+" value="+data[i].id+" class=\"tag btn \">" + data[i].text + "</a>";

					}
					$("#btG").html(html);
				}
				//}
				$("a[class^=tag]").click(function() {

					if ($(this).hasClass("check")) {
						$(this).removeClass("check");
					} else {
						$(this).addClass("check");

					}
					//selected.push($(this).attr('value'));

					var id = $(this).attr('value');
					var index = $.inArray(id, selected);
					if (index === -1) {
						selected.push(id);
					} else {
						selected.splice(index, 1);
					}
					console.log(selected)

					var table = $("#example").DataTable();
					if (selected == "") {
						table.ajax.url("/servlet/queryGroup").load();
					} else {
						table.ajax.url("/servlet/queryGroupByTag?tagId=" + selected).load();
					}

				});

			}
		});

	}

	//查询标签
	function queryTag() {
		//var qmcGId =selectedId;
		var data = "";
		$.ajax({
			type : "POST",
			url : "/servlet/queryTag",
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

	//新增标签记录
	function addTag(tagName) {
		var data = "";
		$.ajax({
			type : "POST",
			url : "/servlet/toAddTag.do?qmcTagName=" + encodeURIComponent(tagName),
			cache : false,
			dataType : "json",

			success : function(data) {
				//添加动画效果
				$("#alert").slideDown(700, function() {
					setTimeout(function() {
						$("#alert").slideUp(500);
					}, 500);

					queryTag();
					$("#tagName").val(null);

				});
			}
		});
	}

	//关联账号与标签
	function blindTag(gids, tid) {
		$.ajax({
			type : "POST",
			url : "/servlet/blindTag?qmcGroupId=" + gids + "&qmcTagId=" + tid,
			cache : false,
			dataType : 'json',

			success : function(data) {
				console.log(data);
				if (data == "1") {
					$("#myModal").on('hidden.bs.modal', function() {
						//pageLoad("${pageContext.request.contextPath}/servlet/toGroupList.do");
						//$("div.modal-backdrop").hide();
						var table = $("#example").DataTable();
						table.ajax.url("/servlet/queryGroup").load();

					});
				} else {
					alert(data);
				}
			}

		});

	}
</script>

<!-- 表单Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<div id="alert" class="callout callout-success"
					style="display:none; margin-bottom: 0;">
					<p>
						<i class="icon fa fa-check"></i>成功添加标签
					</p>
				</div>

				<div id="error" class="callout callout-danger"
					style="display:none; margin-bottom: 0;">
					<p>
						<i class="icon fa fa-ban"></i>请重新键入标签
					</p>
				</div>


				<!-- <button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button> -->
				<h4 class="modal-title" id="myModalLabel"></h4>
			</div>
			<form id="tagForm" action="" method="post">
				<div class="modal-body">

					<div class="form-group">
						<label>请选择标签</label> 
						<select class="form-control select2 select2-hidden-accessible" multiple="" style="width: 100% ;" tabindex="-1" aria-hidden="true">

						</select>
					</div>

					<div class="box-footer" style="padding:0;">
						<label>新增标签</label>
						<div class="input-group">
							<input type="text" name="tagName" id="tagName" placeholder="请输入标签名称" class="form-control"> <span class="input-group-btn">
								<button type="button" id="addTag" class="btn btn-primary btn-flat">新增 <i class="fa fa-arrow-circle-right"></i>
								</button>
							</span>
						</div>
					</div>


				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
					<button type="button" id="confirm" class="btn btn-primary" data-dismiss="modal">确定</button>
				</div>
			</form>
		</div>
	</div>
</div>




<!-- 提示Modal -->
<div class="modal fade" id="tipModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="tipModalLabel">提示</h4>
			</div>

			<div class="modal-body">请选中要标记的记录</div>
			<div class="modal-footer">
				<!-- <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button> -->
				<button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
			</div>

		</div>
	</div>
</div>