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

.check {
	background-color: #286090;
	border-color: #204d74;
	color: #fff;
}

.check:hover {
	color: #fff !important;
}

.tag {
	/* border:1px solid #ccc;
	background-color:#e6e6e6; */
	
}
</style>
<section class="content-header">
	<h1>
		监控账号管理 <small>监控账号列表</small>
	</h1>
	<ol class="breadcrumb">
		<li><i class="fa fa-dashboard"></i></li>
		<!-- <li><a href="#">QQ群管理 </a></li> -->
		<li class="active">监控账号列表</li>
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
						<table id="example" class="table  table-striped table-hover dataTable no-footer" cellspacing="0" width="100%">
							<thead>
								<tr>
									<th></th>
									<th>监控账号</th>
									<th>群数目</th>
									<th>近一日聊天数</th>
									<th>一周聊天数</th>
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

		var role = $("#role").val()
		var selected = [];
		var selectedId = "";
		var isExsit = "";

		var t = $("#example").DataTable({
			"processing" : true,
			"serverSide" : true,
			//指定默认排序的列
			"order" : [ [ 2, "desc" ] ],
			//汉化
			language : {
				"processing" : "数据加载中",
				"zeroRecords" : "暂无数据",
				"lengthMenu" : "每页 _MENU_	条记录",
				"info" : "第 _PAGE_ 页(总共 _PAGES_ 页)   共 _TOTAL_ 条",
				"search" : "在当前页查询：监控账号  _INPUT_",

			},
			ajax : "/monitor/queryMonitor",

			"lengthMenu":[10,20,50,100],
			pageLength : 20,
			columns : [ {
				"data" : "qmcQqId",
				"render" : function(data, type, row, meta) {
					return '<input type="checkbox" id="' + data + '" value="'+data+'" />';
				}

			}, {
				"data" : "qmcQQ",
				"render":function(data,type,row,meta){
					return '<a href="javaScript:toDetail(\'' + data + '\');">' + data + '</a>';
				}
			}, {
				"data" :"qmcGCount"
			},{
				"data" : "qmcDCount",
				"orderable" : false
			}, {
				"data" : "qmcWCount",
				"orderable" : false
			}],
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
				if ($.inArray(data.qmcQqId, selected) !== -1) {
					$(row).addClass('selected');
				}
				$(row).attr("id", data.qmcQqId);

				//console.log($(row));

			},
			fnDrawCallback : function(row, data) {
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
			$('<button type="button" id="tagBtn" class="btn btn-primary">账号授权</button>').appendTo('.box-header').on('click', function(e) {
				if (!$("#example tr.selected").hasClass("selected")) {
					$('#tipModal').modal();
				} else {
					$('#myModal').modal();
					$("#myModal").on('shown.bs.modal', function() {
						queryDepart();
					});
					$("#myModal").on("hide.bs.modal", function() {
						$select2.val(null).trigger("change");
					});

					//提交标签
					$("#confirm").click(function() {
						var did = $select2.val();
						var qids = selected;
						//var gid =selectedId;
						console.log(did);

						monitorDepart(qids, did);
						$("#myModal").on('hidden.bs.modal', function() {
							pageLoad("${pageContext.request.contextPath}/monitor/toMonitor.do");
							//$("div.modal-backdrop").hide();
						});

					});
				}
			});
		}

		//标签选择数据
		$(".select2").select2({
			data : []
		});

	});

	//跳转至群账号详情
	function toDetail(qmcQQ, qmcGroup, qmcGroupName, qmcTag) {

		var url = "${pageContext.request.contextPath}/monitor/toGroup.do?qmcQQ=" + qmcQQ ;
		console.log(url);
		$('div[class="content-wrapper"]').load(url);

	}

	//查询部门
	function queryDepart() {
		//var qmcGId =selectedId;
		var data = "";
		$.ajax({
			type : "POST",
			url : "/monitor/queryDepart",
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

	

	//关联账号与标签
	function monitorDepart(qids, did) {
		$.ajax({
			type : "POST",
			url : "/monitor/monitorDepart?qmcQId=" + qids + "&qmcDId=" + did,
			cache : false,
			dataType : 'json',

			success : function(data) {
				console.log(data);
				if (data == "1") {
					$("#myModal").on('hidden.bs.modal', function() {
						var table = $("#example").DataTable();
						table.ajax.url("/monitor/queryMonitor").load();

					});
				} else {
					alert(data);
				}
			}

		});

	}
</script>

<!-- 表单Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<div id="alert" class="callout callout-success" style="display:none; margin-bottom: 0;">
					<p>
						<i class="icon fa fa-check"></i>成功添加标签
					</p>
				</div>

				<div id="error" class="callout callout-danger" style="display:none; margin-bottom: 0;">
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
						<label>请选择部门</label> 
						<select class="form-control select2 select2-hidden-accessible" multiple="" style="width: 100% ;" tabindex="-1" aria-hidden="true">

						</select>
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default pull-left"
						data-dismiss="modal">Close</button>
					<button type="button" id="confirm" class="btn btn-primary"
						data-dismiss="modal">确定</button>
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