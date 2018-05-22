<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- Content Header (Page header) -->
<style type="text/css">
h1, h3 {
	font-family: 微软雅黑 !important;
}
</style>

<!-- Main content -->
<section class="">

	<div class="">
		<!-- <div class="box-header">
			<h3 class="box-title">用户账户信息列表</h3>
		</div> -->
		<!-- /.box-header -->
		<div class="box-body">
			<form id="frm-example" action="">
				<table id="example" class="table  table-striped table-hover dataTable no-footer" cellspacing="0" width="100%">
					<thead>
						<tr>
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

</section>
<!-- /.content -->
<!-- page script -->

<script>
	$(document).ready(function() {
		var account =$("#account").val();
		var group =$("#group").val();
		var msg =$("#msg").val();
	
		var t = $("#example").DataTable({
			processing : true,
			serverSide : true,
			searching	 : false,
			//指定默认排序的列
			order : [ [ 2, "desc" ] ],
			language : {
				"processing" : "数据加载中",
				"zeroRecords" : "暂无数据",
				"lengthMenu" : "每页 _MENU_	条记录",
				"info" : "第 _PAGE_ 页(总共 _PAGES_ 页)   共 _TOTAL_ 条",
				"search" : "在当前页查询：群号、群名称、标签、监控账号  _INPUT_",
			},
			ajax :{
				type:"POST",
				url:"/servlet/querySearchGroup",
				data:{
					account :account,
					group :group,
					msg :msg
				}
			},
			"lengthMenu":[10,20,50,100],
			pageLength : 20,
			columns: [
			{
				"data": "qmcGroup",
				"render":function(data,type,row,meta){
				 	var groupName =row.qmcGroupName;
				    var account =row.qmcQQ;
				    var groupTag =row.qmcTag;
				    var groupOrg =row.qmcGroupOrg;
				    var accountOrg =row.qmcQQOrg;
				   //return data+'<a style=\"margin-left:20px;\"href="javaScript:toDetail(\'' + accountOrg + '\', \'' + groupOrg + '\', \'' + groupName + '\',\''+ groupTag +'\'); ">查看群详情</a>';
					return data+'<a style=\"margin-left:20px;\" href="${pageContex.request.contextPath}/frame/querymgr/data2.jsp?isDetail=1&qmcGroup='+groupOrg+'&qmcQQ='+accountOrg+'&qmcGroupName='+encodeURIComponent(groupName)+'&qmcTag='+encodeURIComponent(groupTag)+'" target="_blank">查看群详情</a>';
				}
			},{
				"data":"qmcGroupName"
			},{
				"data":"qmcTag"
			},{
				"data":"qmcDayCount",
				"orderable":false
			},{
				"data":"qmcWeekCount",
				"orderable":false
			},{
				"data":"qmcQQ"
			}],

		});

	});
	
	//跳转至群账号详情
	function toDetail(qmcQQ,qmcGroup,qmcGroupName,qmcTag) {
		
		var url="${pageContext.request.contextPath}/servlet/toDetail.do?qmcGroup="+qmcGroup+"&qmcQQ="+qmcQQ+"&qmcGroupName="+encodeURIComponent(qmcGroupName)+"&qmcTag="+encodeURIComponent(qmcTag);
		$('div[class="content-wrapper"]').load(url);
		
	}
	
</script>


<!-- 提示Modal -->
<div class="modal fade" id="tipModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="tipModalLabel">提示</h4>
			</div>
			
			<div class="modal-body">
				查询内容不能为空
			</div>
			<div class="modal-footer">
				<!-- <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button> -->
				<button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
			</div>
		
		</div>
	</div>
</div>
