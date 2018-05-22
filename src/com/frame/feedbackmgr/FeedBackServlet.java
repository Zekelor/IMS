package com.frame.feedbackmgr;

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

public class FeedBackServlet extends HttpServlet {

	
	private static final long serialVersionUID = 1L;
	private FeedBackDAO dao =new FeedBackDAO();
	private RoleService roleService =new RoleService();
	
	private static final String UPLOAD_DIRECTORY = "upload";
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 30MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50;// 50MB

	
	public FeedBackServlet() {
		super();
	}

	
	public void destroy() {
		super.destroy(); 
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
		if (req.equals("/feedback/toFeedBack.do")) {
			request.setAttribute("role", role);
			request.getRequestDispatcher("/frame/feedbackmgr/feedback_list.jsp").forward(request, response);
		} else if ("/feedback/queryFeedBack".equals(req)) {
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
			String[] columnsName = { "qf.qmc_fid", "qf.qmc_feedback_title", "qf.qmc_feedback_content", "qf.qmc_creater","qf.qmc_create_time", "qf.qmc_update_time" };

			String sortDir = request.getParameter("order[0][dir]");
			System.out.println("sortOrder: " + sortOrder);
			System.out.println("sortDir: " + sortDir);
			
			// For search
			String searchValue = new String(request.getParameter("search[value]").getBytes("iso-8859-1"), "utf-8");
			int count = 0;
			List<FeedBackBean> results =new ArrayList<FeedBackBean>();
			count = dao.count(pageSize, startRecord, searchValue);
			
			results = dao.queryDataList(pageSize, startRecord, columnsName[Integer.parseInt(sortOrder)], sortDir, searchValue);
			DataVO<FeedBackBean> result =new DataVO<FeedBackBean>();
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
		}else if("/feedback/toAdd.do".equals(req)){
			request.getRequestDispatcher("/frame/feedbackmgr/feedback_add.jsp").forward(request, response);

		}else if("/feedback/addFeedBack".equals(req)){
			String fdTitle =null;
			String fdContent=null;
			String fdFilePath=null;
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
	        //String uploadPath = getServletContext().getRealPath("/frame") + File.separator + UPLOAD_DIRECTORY+ File.separator+ "feedback" + File.separator +System.currentTimeMillis()  ;
	        String path =getServletContext().getRealPath("/frame");
	        String uploadPath =path.substring(0,path.length()-11)+ File.separator + UPLOAD_DIRECTORY+ File.separator+ "feedback" +File.separator+System.currentTimeMillis();
 	        
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
								//response.getWriter().print("2");
								continue;
							}
							String filePath = uploadPath + File.separator + fileName;
							
							File storeFile = new File(filePath);
							// 在控制台输出文件的上传路径
							fdFilePath =filePath;
							System.out.println(filePath);
							// 保存文件到硬盘
							item.write(storeFile);
							request.setAttribute("message", "文件上传成功!");
						}else{
							String name =item.getFieldName();
							String value =item.getString("UTF-8");
							//System.out.println("表单字段:"+name+":"+value);
							if(!StringUtils.isEmpty(name)){
								if("fdTitle".equals(name)){
									fdTitle=value;
									continue;
								}
								if("fdContent".equals(name)){
									fdContent=value;
									continue;
								}
							}
							
						}
					}
					//判断添加时此报告名称是否存在
					Boolean isExist =dao.queryFdTitleExist(fdTitle);
					if(isExist){
						System.out.println("fdTitle:" + fdTitle);
						System.out.println("fdContent:" + fdContent);
						
						if(!StringUtils.isEmpty(fdFilePath)){
							//frame路径下载
							//String toSub ="frame";
							//webapp路径下载
							String toSub ="webapps";
							int index =fdFilePath.indexOf(toSub);
							if(index !=-1){
								fdFilePath =(fdFilePath.substring(index-1)).replace("\\", "/");
								
							}
							System.out.println("filepath:" + fdFilePath);
						}
						
						FeedBackBean bean = new FeedBackBean();
						bean.setQmcFdTitle(fdTitle);
						bean.setQmcFdContent(fdContent);
						if(!StringUtils.isEmpty(fdFilePath)){
							bean.setQmcFdFilePath(fdFilePath);
						}
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
			
		} else if("/feedback/toEdit.do".equals(req)){
			String id =request.getParameter("qmcFId");
			List<FeedBackBean> list =dao.queryDataById(id);
			request.setAttribute("list", list);
			request.getRequestDispatcher("/frame/feedbackmgr/feedback_edit.jsp").forward(request, response);
		} else if("/feedback/editFeedBack".equals(req)){
			String fdFId =null;
			String fdTitle =null;
			String fdContent=null;
			String fdFilePath=null;
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
	        //String uploadPath = getServletContext().getRealPath("/frame") + File.separator + UPLOAD_DIRECTORY+ File.separator+ "feedback" + File.separator +System.currentTimeMillis()  ;
	        //webapp路径
	        String path =getServletContext().getRealPath("/frame");
	        String uploadPath =path.substring(0,path.length()-11)+ File.separator + UPLOAD_DIRECTORY+ File.separator+ "feedback" +File.separator+System.currentTimeMillis();
 	        
	        
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
							String filePath = uploadPath + File.separator + fileName;
							
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
								if ("fdFId".equals(name)) {
									fdFId = value;
									continue;
								}

								if ("fdTitle".equals(name)) {
									fdTitle = value;
									continue;
								}
								if ("fdContent".equals(name)) {
									fdContent = value;
									continue;
								}
								if ("fdFilePath".equals(name)) {
									if (!StringUtils.isEmpty(dstFilePath)) {
										fdFilePath = dstFilePath;
										System.out.println("dstFilePath:" + dstFilePath);
									} else {
										fdFilePath = value;
									}
									continue;
								}
							}
							
						}
					}
					//判断添加时此报告名称是否存在
					Boolean isExist =dao.queryFdTitleExist(fdTitle,fdFId);
					if(isExist){
						//frame路径
						//String toSub ="frame";
						//webapp路径
						String toSub ="webapps";
						int index =fdFilePath.indexOf(toSub);
						if(index !=-1){
							fdFilePath =(fdFilePath.substring(index-1)).replace("\\", "/");
							
						}
						System.out.println("fdFId:"+fdFId);
						System.out.println("fdTitle:" + fdTitle);
						System.out.println("fdContent:" + fdContent);
						System.out.println("filepath:" + fdFilePath);

						FeedBackBean bean = new FeedBackBean();
						bean.setQmcFId(Integer.parseInt(fdFId));
						bean.setQmcFdTitle(fdTitle);
						bean.setQmcFdContent(fdContent);
						bean.setQmcFdFilePath(fdFilePath);
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
		} else if("/feedback/deleteFeedBack".equals(req)){
			String id =request.getParameter("qmcFId");
			Boolean flag =dao.delete(id);
			
			if(flag){
				response.getWriter().print("1");
			}else{
				response.getWriter().print("0");
			}
			
		}else if("/feedback/download".equals(req)){
			String id =request.getParameter("id");
			
			String dstPath = dao.queryFeedBackById(id);
			String fileName =dstPath.substring(dstPath.lastIndexOf("/")+1);
			
			//frame路径
			//int index =dstPath.indexOf("frame");
			//webapp路径
			
			int index =dstPath.indexOf("webapps");
			if(index !=-1){
				dstPath =(dstPath.substring(index)).replace("\\", "/");
				
			}
			//从项目路径frame下载文件
			//String path =(getServletContext().getRealPath("/"+dstPath));
			//从webapp路径下载文件
			String queryPath =getServletContext().getRealPath("/webapps");
			String path =(queryPath.substring(0,queryPath.length()-13)+File.separator+(dstPath.substring(dstPath.indexOf("/")+1)).replace("/", File.separator));
			System.out.println("\\\\\\反馈路径"+path);
			
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
					//response.setContentType("text/comma-separated-values");
					response.setContentType(getServletContext().getMimeType(fileName));
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
