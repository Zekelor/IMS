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
	<h1>群账号详情   <small>QQ消息列表</small></h1>
	<ol class="breadcrumb">
		<li><i class="fa fa-dashboard"></i></li>
		<li><a href="javascript:;" title="返回群列表页面" onclick="pageLoad('${pageContext.request.contextPath}/servlet/toGroupList.do');">QQ群列表</a></li>
		<li class="active">QQ群消息列表</li>
	</ol>
</section>
<c:set var="count" value="1" />
<!-- Main content -->
<section class="content">
	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<div class="box-header">
				<c:if test="${isDetail ne 1 }">
					<h3 class="box-title">
					监控账号：<span style="color:#efa900;">${qmcQQ}</span>
					QQ群号：<span style="color:#efa900;">${qmcGroup}</span> 
					QQ群名称：<span style="color:#efa900;">${qmcGroupName}</span>
					
					</h3>
				
				</c:if>
					
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<table id="example" class="table  table-striped table-hover dataTable no-footer" cellspacing="0" width="100%">
						<thead>
							<tr>
								<th>发送QQ号</th>
								<th>发送昵称</th>
								<th>QQ消息</th>
								<th>聊天时间</th>
								<!-- <th>token</th> -->
								<!-- 
								<th>最新聊天时间</th> -->
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
<input type="hidden" id="qmcGroup" name="qmcGroup" value="${qmcGroup }">
<input type ="hidden" id ="qmcTag" name ="qmcTag" value="${qmcTag }">

<script>
	$(document).ready(function() {
	 	var groupName =$("#qmcGroup").val();
	 	console.log(groupName);
	 	var qmcTag =$("#qmcTag").val();
	 	console.log(qmcTag);
	 	if(qmcTag!=""){
	 		$(".box-title").append("群标签：<span style=\"color:#efa900;\">"+qmcTag+"</span>");
	 	}
	 
		var t =$("#example").dataTable({
			"processing": true,
	      	"serverSide": true,
	      	"order"	:[[3,"desc"]],
			language:{
				"processing":"数据加载中",
	      		"zeroRecords":"暂无数据",
				"lengthMenu":"每页 _MENU_	条记录",
				"info":"第 _PAGE_ 页(总共 _PAGES_ 页) 共_TOTAL_条",
				"search": "在当前页搜索聊天内容  _INPUT_", 
				
			},
			ajax:"/servlet/queryGroupDetail?groupName="+groupName ,
			"lengthMenu":[10,20,50,100],
			pageLength : 20,
			columns: [
			{
				"data": "qmcSender",
				"width":"100px"
			},{
				"data":"qmcSenderNick",
				"width":"100px"
			},{
				"data":"qmcMsg"
			},{
				"data":"qmcMsgDate",
				"width":"120px"
			}]
		});
		
	});
	
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
			<div class="modal-body">确定是否删除此用户?
				
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
				<button type="submit" class="btn btn-primary">确定</button>
			</div>
			</form>
		</div>
	</div>
</div>