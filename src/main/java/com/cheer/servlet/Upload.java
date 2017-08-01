package com.cheer.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class Upload extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	private static final String UPLOAD_DIR="F:"+File.separator+"upload";
	
	
	public Upload()
	{

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		Boolean ismultipart=ServletFileUpload.isMultipartContent(request);
		
		//判断请求是否是多媒体文件
		
		if(!ismultipart)
		{
			out.println("表单必须包含 enctype=multipart/form-data");
			out.flush();
			out.close();
		}
		
		DiskFileItemFactory fileFactory=new DiskFileItemFactory();
		
		// 创建文件上传处理器
		ServletFileUpload uploadcpu = new ServletFileUpload(fileFactory);	
		
		File uploadDir=new File(UPLOAD_DIR);
		
		if(!uploadDir.exists())
		{
			uploadDir.mkdir();
		}
		try
		{
			
			List<FileItem> formItems = uploadcpu.parseRequest(request);
			
			
			if(formItems != null && formItems.size()>0)
			{
				// 迭代表单数据
				for(FileItem item : formItems)
				{
					// 处理不在表单中的字段
					if(!item.isFormField())
					{
						String fileName = new File(item.getName()).getName();
						String filePath=UPLOAD_DIR + File.separator + fileName;
                        File uploadFile = new File(filePath);
                        
                        // 在控制台输出文件的上传路径
                        System.out.println(filePath);
                        
                        // 保存文件到硬盘
                        item.write(uploadFile);
                        
                        request.setAttribute("filePath",filePath);
                        request.setAttribute("message","文件上传成功!");							
					}						
				}					
			}	
		} 
		
		catch (Exception e)
		{
			 request.setAttribute("message","错误信息: " + e.getMessage());
		}
		
		
		request.getRequestDispatcher("/analysis").forward(request, response);		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		doGet(request, response);
	}

}
