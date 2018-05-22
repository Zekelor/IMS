package com.frame.querymgr;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.frame.common.DataVO;
import com.frame.qqgroup.GroupBean;
import com.google.gson.Gson;
import com.yhl.f22.frame.utils.StringUtils;

public class QueryServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private QueryDAO dao =new QueryDAO();

	public QueryServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		String req = request.getServletPath();
		System.out.println(req);
		if (req.equals("/servlet/toSearch.do")) {
			request.getRequestDispatcher("/frame/querymgr/query_index.jsp").forward(request, response);
		}else if(req.equals("/servlet/queryResult")){
			
			String account=request.getParameter("account");
			String group =request.getParameter("group");
			String msg = new String(request.getParameter("msg").getBytes("ISO8859-1"), "UTF-8");
			String [] searchValue ={account,group,msg};
			//查询账号数量
			int msgCount =dao.queryMsgCount(searchValue);
			request.setAttribute("msgCount", msgCount);
			int groupCount =0;
			
			if(!StringUtils.isEmpty(account)||!StringUtils.isEmpty(group)){
				//查询消息数量
				String [] searchValue2={account,group};
				groupCount =dao.queryGroupCount(searchValue2);
				request.setAttribute("groupCount", groupCount);
			}
			int totalCount =msgCount +groupCount;
			
			request.setAttribute("totalCount", totalCount);
			request.setAttribute("account", account);
			request.setAttribute("group", group);
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/frame/querymgr/query_result.jsp").forward(request, response);
		} else if (req.equals("/servlet/toQueryGroupList.do")){
			String account =request.getParameter("account");
			String group =request.getParameter("group");
			
			request.setAttribute("account", account);
			request.setAttribute("group", group);
			request.getRequestDispatcher("/frame/querymgr/queryGroup_list.jsp").forward(request, response);
			
		} else if(req.equals("/servlet/querySearchGroup")){
			String account =new String(request.getParameter("account").getBytes("ISO8859-1"),"UTF-8");
			String group =new String(request.getParameter("group").getBytes("ISO8859-1"),"UTF-8");
			//For Page
			int pageSize =10;
			int startRecord =0;
			String size = request.getParameter("length");
			if (!"".equals(size) && size != null) {
				pageSize = Integer.parseInt(size);
			}
			String currentRecord = request.getParameter("start");
			if (!"".equals(currentRecord) && currentRecord != null) {
				startRecord = Integer.parseInt(currentRecord);
			}
			// For sortable
			String sortOrder = request.getParameter("order[0][column]");
			String[] columnsName = {"A.qmc_group","A.qmc_group_name","A.tn","A.daycount","A.weekcount","A.qmc_qq"};
			String sortDir = request.getParameter("order[0][dir]");
			System.out.println("sortOrder: " + sortOrder);
			System.out.println("sortDir: " + sortDir);
			
			//For search
			String[] searchValue ={account,group};
			int count = 0;
			List<GroupBean> results = new ArrayList<GroupBean>();
			count = dao.queryGroupCount(searchValue);
			
			results =dao.queryGroupList(pageSize,startRecord,columnsName[Integer.parseInt(sortOrder)], sortDir, searchValue);
			//Map<String,Object> dataMap =new HashMap<String, Object>();
			for(GroupBean g:results){
				g.setQmcGroupOrg(g.getQmcGroup());
				g.setQmcQQOrg(g.getQmcQQ());
				
				String groupKey =StringUtils.toString(g.getQmcGroup());
				if(!StringUtils.isEmpty(group)){
					String groupReplace =groupKey.replace(group, "<span class=\"highlight\">"+group+"</span>");
					g.setQmcGroup(groupReplace);
					
				}
				String accountKey =StringUtils.toString(g.getQmcQQ());
				if(!StringUtils.isEmpty(account)){
					String accountReplace =accountKey.replace(account, "<span class=\"highlight\">"+account+"</span>");
					g.setQmcQQ(accountReplace);
				}
				
			}
			
			DataVO<GroupBean> result = new DataVO<GroupBean>();
			result.setDraw(Integer.parseInt(request.getParameter("draw") == null ? "0" : request.getParameter("draw")) + 1);
			result.setData(results);
			result.setRecordsTotal(count);
			result.setRecordsFiltered(count);
			Gson gson = new Gson();
			String output = gson.toJson(result);
			System.out.println("Output JSON: \n" + output);
			PrintWriter out = response.getWriter();
			out.write(output);
			out.flush();
			out.close();
			
		} else if (req.equals("/servlet/toQueryMsgList.do")){
			String account =request.getParameter("account");
			String group =request.getParameter("group");
			String msg = new String(request.getParameter("msg").getBytes("ISO8859-1"), "UTF-8");

			request.setAttribute("account", account);
			request.setAttribute("group", group);
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/frame/querymgr/queryMsg_list.jsp").forward(request, response);
		} else if(req.equals("/servlet/querySearchMsg")){
			String account =request.getParameter("account");
			String group =request.getParameter("group");
			String msg = request.getParameter("msg");
			//For Page
			int pageSize =10;
			int startRecord =0;
			String size = request.getParameter("length");
			if (!"".equals(size) && size != null) {
				pageSize = Integer.parseInt(size);
			}
			String currentRecord = request.getParameter("start");
			if (!"".equals(currentRecord) && currentRecord != null) {
				startRecord = Integer.parseInt(currentRecord);
			}
			// For sortable
			String sortOrder = request.getParameter("order[0][column]");
			String[] columnsName = {"m.qmc_sender","m.qmc_sender_nick","m.qmc_msg","m.qmc_msg_date","m.qmc_group","qg.qmc_group_name","m.qmc_from_qq"};
			String sortDir = request.getParameter("order[0][dir]");
			System.out.println("sortOrder: " + sortOrder);
			System.out.println("sortDir: " + sortDir);
			
			//For search
			String[] searchValue ={account,group,msg};
			int count = 0;
			List<GroupBean> results = new ArrayList<GroupBean>();
			count = dao.queryMsgCount(searchValue);
			
			results =dao.queryMsgList(pageSize,startRecord,columnsName[Integer.parseInt(sortOrder)], sortDir, searchValue);
			for(GroupBean g:results){
				g.setQmcGroupOrg(g.getQmcGroup());
				g.setQmcQQOrg(g.getQmcFromQq());
				//发送账号
				String accountKey =StringUtils.toString(g.getQmcSender());
				if(!StringUtils.isEmpty(account)){
					String accountReplace =accountKey.replace(account, "<span class=\"highlight\">"+account+"</span>");
					g.setQmcSender(accountReplace);
				}
				//监控账号
				String qqKey =StringUtils.toString(g.getQmcFromQq());
				if(!StringUtils.isEmpty(account)){
					String qqReplace =qqKey.replace(account, "<span class=\"highlight\">"+account+"</span>");
					g.setQmcFromQq(qqReplace);
				}
				//所属群
				String groupKey =StringUtils.toString(g.getQmcGroup());
				if(!StringUtils.isEmpty(group)){
					String groupReplace =groupKey.replace(group, "<span class=\"highlight\">"+group+"</span>");
					g.setQmcGroup(groupReplace);
				}
				//消息内容
				String msgKey =StringUtils.toString(g.getQmcMsg());
				if(!StringUtils.isEmpty(msg)){
					String msgReplace =msgKey.replace(msg, "<span class=\"highlight\">"+msg+"</span>");
					g.setQmcMsg(msgReplace);
				}
				
			}
			
			DataVO<GroupBean> result = new DataVO<GroupBean>();
			result.setDraw(Integer.parseInt(request.getParameter("draw") == null ? "0" : request.getParameter("draw")) + 1);
			result.setData(results);
			result.setRecordsTotal(count);
			result.setRecordsFiltered(count);
			Gson gson = new Gson();
			String output = gson.toJson(result);
			System.out.println("Output JSON: \n" + output);
			PrintWriter out = response.getWriter();
			out.write(output);
			out.flush();
			out.close();
			
		}else if(req.equals("/servlet/toData2.do")){
			String group=request.getParameter("qmcGroup");
			String groupName = new String(request.getParameter("qmcGroupName").getBytes("ISO8859-1"), "UTF-8");
			String account =request.getParameter("qmcQQ");
			String tag =new String(request.getParameter("qmcTag").getBytes("ISO8859-1"), "UTF-8");
			System.out.println("list-groupname:"+group);
			request.setAttribute("qmcGroup", group);
			request.setAttribute("qmcGroupName", groupName);
			request.setAttribute("qmcTag", tag);
			request.setAttribute("qmcQQ", account);
			request.getRequestDispatcher("/frame/querymgr/data2.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

	
	public void init() throws ServletException {
		// Put your code here
	}

}
