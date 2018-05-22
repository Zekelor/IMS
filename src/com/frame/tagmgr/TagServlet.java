package com.frame.tagmgr;

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

import com.google.gson.Gson;
import com.mysql.fabric.xmlrpc.base.Array;
import com.yhl.f22.frame.utils.StringUtils;

public class TagServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	TagDAO dao = new TagDAO();

	public TagServlet() {
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
		String username = StringUtils.toString(request.getSession().getAttribute("username"));
		String uid = dao.queryUserId(username);
		System.out.println(req);
		if (req.equals("/servlet/queryTag")) {

			String gid = request.getParameter("qmcGId");
			List<TagBean> tagList = dao.queryTag(gid);
			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
			Map<String, Object> dataMap = new HashMap<String, Object>();
			for (TagBean tag : tagList) {
				dataMap = new HashMap<String, Object>();
				dataMap.put("id", tag.getQmcTId());
				dataMap.put("text", tag.getQmcTagName());
				dataList.add(dataMap);
			}
			Gson gson = new Gson();
			String output = gson.toJson(dataList);
			System.out.println("Output JSON: \n" + output);
			PrintWriter out = response.getWriter();
			out.write(output);
			out.flush();
			out.close();

		} else if (req.equals("/servlet/toAddTag.do")) {
			String tagName = new String(request.getParameter("qmcTagName").getBytes("ISO8859-1"), "UTF-8");

			String flag = dao.addTag(uid, tagName);
			// String flag2 =dao.addTagU(gid,uid);
			if (flag.equals("1")) {
				response.getWriter().print("1");

			} else {
				response.getWriter().print("0");
			}
		} else if (req.equals("/servlet/blindTag")) {
			String gids = request.getParameter("qmcGroupId");
			gids=gids.substring(gids.indexOf(",")+1);
			String tids = request.getParameter("qmcTagId");
			String flag ="";
			
			System.out.println(gids+tids);
			if(!"null".equals(tids)){
				 flag = dao.addTagU(tids, gids, uid);
			}else{
				Boolean flag1 =dao.deleteUTag(gids);
				if(flag1){
					flag ="1";
				}else{
					flag="0";
				}
			}
			if (flag.equals("1")) {
				response.getWriter().print("1");

			} else {
				response.getWriter().print("0");
			}

		} else if(req.equals("/servlet/queryTagExist")){
			String tagName = new String(request.getParameter("qmcTagName").getBytes("ISO8859-1"), "UTF-8");
			Boolean flag =dao.queryTagExist(tagName);
			if(flag){
				response.getWriter().print("1");
			}else{
				
				response.getWriter().print("0");
			}
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
