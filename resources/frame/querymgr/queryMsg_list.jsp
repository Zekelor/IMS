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

<!-- Main content -->
<section class="">
	<!-- <div class="row">
		<div class="col-xs-12"> -->
			<div class="">
				<div class="box-body">
					<table id="example" class="table  table-striped table-hover dataTable no-footer"  >
						<thead>
							<tr>
								<th>发送QQ号</th>
								<th>发送昵称</th>
								<th>QQ消息</th>
								<th>聊天时间</th>
								<th>所属QQ群</th>
								<th>所属QQ群名称</th>
								<th>所属监控QQ</th>
							</tr>
						</thead>
						<tbody></tbody>
						
					</table>
				</div>
				<!-- /.box-body -->
			</div>
			<!-- /.box -->
	<!-- 	</div>
		/.col
	</div>
	/.row -->
</section>
<!-- /.content -->
<!-- page script -->


<script>
	$(document).ready(function() {
	 	var account =$("#account").val();
		var group =$("#group").val();
		var msg =$("#msg").val();
	 
		var t =$("#example").dataTable({
			processing: true,
	      	serverSide: true,
	      	searching:false,
	      	order	:[[3,"desc"]],
			language:{
				"processing":"数据加载中",
	      		"zeroRecords":"暂无数据",
				"lengthMenu":"每页 _MENU_	条记录",
				"info":"第 _PAGE_ 页(总共 _PAGES_ 页) 共_TOTAL_条",
				"search": "在当前页搜索聊天内容  _INPUT_", 
				
			},
			ajax :{
				type:"POST",
				url:"/servlet/querySearchMsg",
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
				"data": "qmcSender",
				"width":"100px"
			},{
				"data":"qmcSenderNick",
				"width":"100px"
			},{
				"data":"qmcMsg"
			},{
				"data":"qmcMsgDate",
				"width":"130px"
			},{
				"data":"qmcGroup",
				"width":"150px",
				"render":function(data,type,row,meta){
				 	var groupName =row.qmcGroupName;
				    var account =row.qmcQQ;
				    var groupTag =row.qmcTag;
				    var groupOrg =row.qmcGroupOrg;
				    var accountOrg =row.qmcQQOrg;
					return data+'<a style=\"margin-right:20px; float:right;\" href="${pageContex.request.contextPath}/frame/querymgr/data2.jsp?isDetail=1&qmcGroup='+groupOrg+'&qmcQQ='+accountOrg+'&qmcGroupName='+encodeURIComponent(groupName)+'&qmcTag='+encodeURIComponent(groupTag)+'" target="_blank">查看群详情</a>';
				}
			},{
				"data":"qmcGroupName",
				"width":"100px"
			},{
				"data":"qmcFromQq",
				"width":"100px"
			}]
		});
		
	});
	
	function pageLoad(url) {
		$('div[class="content-wrapper"]').load(url);
	}
	
</script>
