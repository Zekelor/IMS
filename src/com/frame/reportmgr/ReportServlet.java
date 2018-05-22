package com.frame.reportmgr;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.frame.common.DataVO;
import com.frame.role.RoleService;
import com.google.gson.Gson;
import com.yhl.f22.frame.utils.StringUtils;

public class ReportServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ReportDAO dao =new ReportDAO();
	private RoleService roleService =new RoleService();

	private static final String UPLOAD_DIRECTORY = "upload";
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 30MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50;// 50MB

	public ReportServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		String req = request.getServletPath();
		String username =StringUtils.toString(request.getSession().getAttribute("username"));
		String role=roleService.queryRoleType(username);
		System.out.println(req);
		if (req.equals("/report/toReport.do")) {
			request.setAttribute("role", role);
			request.getRequestDispatcher("/frame/reportmgr/report_list.jsp").forward(request, response);
		} else if (req.equals("/report/queryReport")) {
			// 分页
			int pageSize = 10;
			int startRecord = 0;
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
			String[] columnsName = { "qr.qmc_rid", "qr.qmc_report_name", "qr.qmc_report_desc", "qr.qmc_creater", "qr.qmc_create_time", "qr.qmc_update_time"};

			String sortDir = request.getParameter("order[0][dir]");
			System.out.println("sortOrder: " + sortOrder);
			System.out.println("sortDir: " + sortDir);

			// For search
			String searchValue = new String(request.getParameter("search[value]").getBytes("iso-8859-1"), "utf-8");
			int count = 0;
			List<ReportBean> results = new ArrayList<ReportBean>();
			count = dao.count(pageSize, startRecord, searchValue);

			results = dao.queryDataList(pageSize, startRecord, columnsName[Integer.parseInt(sortOrder)], sortDir, searchValue);
			DataVO<ReportBean> result = new DataVO<ReportBean>();
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
		}else if("/report/toAdd.do".equals(req)){
			request.getRequestDispatcher("/frame/reportmgr/report_add.jsp").forward(request, response);
		}else if("/report/addReport".equals(req)){
			String reportName =null;
			String reportDesc =null;
			String reportFilePath=null;
			if(!ServletFileUpload.isMultipartContent(request)){
				PrintWriter writer =response.getWriter();
			    writer.println("Error: 表单必须包含 enctype=multipart/form-data");
				writer.flush();
				return;
			}
			// 配置上传参数
	        DiskFileItemFactory factory = new DiskFileItemFactory();
	        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
	        factory.setSizeThreshold(MEMORY_THRESHOLD);
	        // 设置临时存储目录
	        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
	 
	        ServletFileUpload upload = new ServletFileUpload(factory);
	         
	        // 设置最大文件上传值
	        upload.setFileSizeMax(MAX_FILE_SIZE);
	        
	        // 设置最大请求值 (包含文件和表单数据)
	        upload.setSizeMax(MAX_REQUEST_SIZE);
	        
	        // 中文处理
	        upload.setHeaderEncoding("UTF-8"); 
	        
	        // 构造临时路径来存储上传的文件
	        // 这个路径相对当前应用的目录
	        //String dstPath ="/frame/"+ System.currentTimeMillis()  ;
	        //frame路径
	        //String uploadPath = getServletContext().getRealPath("/frame") + File.separator + UPLOAD_DIRECTORY+ File.separator+ "report" +File.separator+System.currentTimeMillis();
	        //webapp路径
	        String path =getServletContext().getRealPath("/frame");
	        String uploadPath =path.substring(0,path.length()-11)+ File.separator + UPLOAD_DIRECTORY+ File.separator+ "report" +File.separator+System.currentTimeMillis();
 	       
	        // 如果目录不存在则创建
	        File uploadDir = new File(uploadPath);
	        if (!uploadDir.exists()) {
	            uploadDir.mkdirs();
	        }

			try {
				List<FileItem> formItems = upload.parseRequest(request);
				if (formItems != null && formItems.size() > 0) {
					// 迭代表单数据
					for (FileItem item : formItems) {
						// 处理不在表单中的字段
						if (!item.isFormField()) {
							String fileName = new File(item.getName()).getName();
							if(StringUtils.isEmpty(fileName)){
								response.getWriter().print("2");
								return;
							}
							String filePath = uploadPath + File.separator + fileName;
							
							File storeFile = new File(filePath);
							// 在控制台输出文件的上传路径
							reportFilePath =filePath;
							System.out.println(filePath);
							// 保存文件到硬盘
							item.write(storeFile);
							request.setAttribute("message", "文件上传成功!");
						}else{
							String name =item.getFieldName();
							String value =item.getString("UTF-8");
							//System.out.println("表单字段:"+name+":"+value);
							if(!StringUtils.isEmpty(name)){
								if("reportName".equals(name)){
									reportName=value;
									continue;
								}
								if("reportDesc".equals(name)){
									reportDesc=value;
									continue;
								}
							}
							
						}
					}
					//判断添加时此报告名称是否存在
					Boolean isExist =dao.queryReportNameExist(reportName);
					if(isExist){
						//项目frame路径下载
						/*String toSub ="frame";
						int index =reportFilePath.indexOf(toSub);
						if(index !=-1){
							reportFilePath =(reportFilePath.substring(index-1)).replace("\\", "/");
							
						}*/
						//项目webapp路径下载
						String toSub ="webapps";
						int index =reportFilePath.indexOf(toSub);
						if(index !=-1){
							reportFilePath =(reportFilePath.substring(index-1)).replace("\\", "/");
						}
						
						
						System.out.println("reportName:" + reportName);
						System.out.println("reportDesc:" + reportDesc);
						System.out.println("filepath:" + reportFilePath);

						ReportBean bean = new ReportBean();
						bean.setQmcReportName(reportName);
						bean.setQmcReportDesc(reportDesc);
						bean.setQmcReportFilePath(reportFilePath);
						bean.setQmcCreater(username);
						bean.setQmcUpdateTime(String.valueOf((System.currentTimeMillis() / 1000)));
						bean.setQmcCreateTime(String.valueOf((System.currentTimeMillis() / 1000)));
						String flag = dao.save(bean);
						if (flag.equals("1")) {
							response.getWriter().print("1");

						} else {
							response.getWriter().print("0");
						}
					}else{
						
						response.getWriter().print("3");//表示数据库中已存在这条记录
					}
				}
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		} else if("/report/toEdit.do".equals(req)){
			String id =request.getParameter("qmcRId");
			List<ReportBean> list =dao.queryDataById(id);
			request.setAttribute("list", list);
			request.getRequestDispatcher("/frame/reportmgr/report_edit.jsp").forward(request, response);
		} else if("/report/editReport".equals(req)){
			String reportRId  =null;
			String reportName =null;
			String reportDesc =null;
			String reportFilePath=null;
			String dstFilePath =null;
			if(!ServletFileUpload.isMultipartContent(request)){
				PrintWriter writer =response.getWriter();
			    writer.println("Error: 表单必须包含 enctype=multipart/form-data");
				writer.flush();
				return;
			}
			// 配置上传参数
	        DiskFileItemFactory factory = new DiskFileItemFactory();
	        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
	        factory.setSizeThreshold(MEMORY_THRESHOLD);
	        // 设置临时存储目录
	        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
	 
	        ServletFileUpload upload = new ServletFileUpload(factory);
	         
	        // 设置最大文件上传值
	        upload.setFileSizeMax(MAX_FILE_SIZE);
	        
	        // 设置最大请求值 (包含文件和表单数据)
	        upload.setSizeMax(MAX_REQUEST_SIZE);
	        
	        // 中文处理
	        upload.setHeaderEncoding("UTF-8"); 
	        
	        // 构造临时路径来存储上传的文件
	        // 这个路径相对当前应用的目录
	        //String dstPath ="/frame/"+ System.currentTimeMillis()  ;
	        //frame路径
	        //String uploadPath = getServletContext().getRealPath("/frame") + File.separator + UPLOAD_DIRECTORY+ File.separator+ "report" +File.separator+System.currentTimeMillis();
	        //webapp路径
	        String path =getServletContext().getRealPath("/frame");
	        String uploadPath =path.substring(0,path.length()-11)+ File.separator + UPLOAD_DIRECTORY+ File.separator+ "report" +File.separator+System.currentTimeMillis();
	       
	        // 如果目录不存在则创建
	        File uploadDir = new File(uploadPath);
	        if (!uploadDir.exists()) {
	            uploadDir.mkdirs();
	        }

			try {
				List<FileItem> formItems = upload.parseRequest(request);
				if (formItems != null && formItems.size() > 0) {
					// 迭代表单数据
					for (FileItem item : formItems) {
						// 处理不在表单中的字段
						if (!item.isFormField()) {
							String fileName = new File(item.getName()).getName();
							if(StringUtils.isEmpty(fileName)){
								continue;
							}
							String filePath = uploadPath + File.separator + fileName  ;
							
							File storeFile = new File(filePath);
							// 在控制台输出文件的上传路径
							dstFilePath =filePath;
							System.out.println(filePath);
							// 保存文件到硬盘
							item.write(storeFile);
							request.setAttribute("message", "文件上传成功!");
						}else{
							String name =item.getFieldName().trim();
							String value =item.getString("UTF-8").trim();
							//System.out.println("表单字段:"+name+":"+value);
							if(!StringUtils.isEmpty(name)){
								if("reportRId".equals(name)){
									reportRId =value;
									continue;
								}
								if("reportName".equals(name)){
									reportName=value;
									continue;
								}
								if("reportDesc".equals(name)){
									reportDesc=value;
									continue;
								}
								if("reportFilePath".equals(name)){
									if(!StringUtils.isEmpty(dstFilePath)){
										reportFilePath =dstFilePath;
										System.out.println("dstFilePath:"+dstFilePath);
									}else{
										reportFilePath =value;
									}
									continue;
								}
							}
							
						}
					}
					//判断添加时此报告名称是否存在
					Boolean isExist =dao.queryReportNameExist(reportName,reportRId);
					if(isExist){
						//frame路径
						//String toSub ="frame";
						//项目webapp路径下载
						String toSub ="webapps";
						int index =reportFilePath.indexOf(toSub);
						if(index !=-1){
							reportFilePath =(reportFilePath.substring(index-1)).replace("\\", "/");
							
						}
						System.out.println("reportRId:"+reportRId);
						System.out.println("reportName:" + reportName);
						System.out.println("reportDesc:" + reportDesc);
						System.out.println("filepath:" + reportFilePath);

						ReportBean bean = new ReportBean();
						bean.setQmcRId(Integer.parseInt(reportRId));
						bean.setQmcReportName(reportName);
						bean.setQmcReportDesc(reportDesc);
						bean.setQmcReportFilePath(reportFilePath);
						//bean.setQmcCreater(username);
						bean.setQmcUpdateTime(String.valueOf((System.currentTimeMillis() / 1000)));
						//bean.setQmcCreateTime(String.valueOf((System.currentTimeMillis() / 1000)));
						String flag = dao.update(bean);
						if (flag.equals("1")) {
							response.getWriter().print("1");

						} else {
							response.getWriter().print("0");
						}
					}else{
						
						response.getWriter().print("3");//表示数据库中已存在这条记录
					}
				}
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}else if("/report/deleteReport".equals(req)){
			String id =request.getParameter("qmcRId");
			Boolean flag =dao.delete(id);
			
			if(flag){
				response.getWriter().print("1");
			}else{
				response.getWriter().print("0");
			}
			
		}else if("/report/download".equals(req)){
			String id =request.getParameter("id");
			
			String dstPath = dao.queryReportById(id);
			String fileName =dstPath.substring(dstPath.lastIndexOf("/")+1);
			
			/*int index =dstPath.indexOf("frame");
			if(index !=-1){
				dstPath =(dstPath.substring(index)).replace("\\", "/");
				
			}*/
			
			int index =dstPath.indexOf("webapps");
			/*if(index !=-1){
				dstPath =(dstPath.substring(index)).replace("\\", "/");
				
			}*/
			
			
			//String dirName =abPath.substring(abPath.indexOf(""));
			//String fileName =new String(request.getParameter("fileName").getBytes("ISO8859-1"),"UTF-8");
			//String dirName = "/frame";
			//String sep = System.getProperty("file.separator");
			//String path = request.getSession(true).getServletContext().getRealPath("/" + dirName) + sep + fileName;
	        //String uploadPath = getServletContext().getRealPath("/frame") + File.separator + UPLOAD_DIRECTORY+ "\\"+System.currentTimeMillis()  ;
			
			//从项目路径frame下载文件
			//String path =(getServletContext().getRealPath("/"+dstPath));
			//从webapp路径下载文件
			String queryPath =getServletContext().getRealPath("/webapps");
			String path =(queryPath.substring(0,queryPath.length()-13)+File.separator+(dstPath.substring(dstPath.indexOf("/")+9)).replace("/", File.separator));
			System.out.println("\\\\\\文件下载路径:"+path);
			
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			OutputStream os = null;
			InputStream is = null;
			try {
				File downloadFile = new File(path);
				if (downloadFile.exists()) {
					is = new FileInputStream(downloadFile);
					bis = new BufferedInputStream(is);
					os = response.getOutputStream();
					bos = new BufferedOutputStream(os);
					response.setContentType("text/comma-separated-values");
					//response.setContentType(getServletContext().getMimeType(fileName));
					response.setHeader("Content-Disposition","attachment;filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
					response.setContentLength((int) downloadFile.length());
					int bytesRead = 0;
					byte[] buffer = new byte[4096];
					while ((bytesRead = bis.read(buffer, 0, 4096)) != -1) {
						bos.write(buffer, 0, bytesRead);
					}
					bos.flush();//清空缓冲区
					is.close();
					bis.close();
					os.close();
					bos.close();
				} else {
					//PrintWriter out = response.getWriter();
					//out.println("<script type=\"text/javascript\">");
					//out.println("alert(\"文件不存在\");");
					//out.println("pageLoad(\"${pageContext.request.contextPath}/report/toReport.do\");");
					//out.println("</script>");
					//request.getRequestDispatcher("/jsp/example/data.jsp").forward(request, response);
					request.getRequestDispatcher("/frame/404.jsp").forward(request, response);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
			
			
		}
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
