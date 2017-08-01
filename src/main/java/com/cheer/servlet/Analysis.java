package com.cheer.servlet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cheer.dao.ExerciseDao;
import com.cheer.dao.impl.ExerciseDaoImpl;
import com.cheer.domian.Exercise;
import com.cheer.util.DbHelper;

public class Analysis extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public Analysis()
	{
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(request.getAttribute("message").toString());

		String filePath = request.getAttribute("filePath").toString();
		Reader reader = null;
		BufferedReader br = null;
		
		Connection conn = DbHelper.getInstance().getConnection();
		
		ExerciseDao ed=new ExerciseDaoImpl(conn);
		
		try
		{
			reader = new FileReader(filePath);
			br = new BufferedReader(reader);

			String line = null;

			for (int i = 1; i <= 60; i++)
			{
				Exercise exe = new Exercise();
				exe.setNo(i);

				while ((line = br.readLine()) != null)
				{
					if (!"".equals(line))
					{
						if (line.contains("、"))
						{
							
							// System.out.println(strs[1]);
							if (line.startsWith("A"))
							{
								exe.setSelectA(line);
							} else if (line.startsWith("B"))
							{
								exe.setSelectB(line);
							} else if (line.startsWith("C"))
							{
								exe.setSelectC(line);
							} else if (line.startsWith("D"))
							{
								exe.setSelectD(line);
							} else
							{
								String[] strs = line.split("、", 2);
								exe.setTitle(strs[1]);
							}
						}
						else
						{
							String[] strs = line.split("：", 2);
							// System.out.println(strs[1]);
							exe.setAnswer(strs[1]);
						}
					} 
					else
					{
						break;
					}
				}

				System.out.println(exe);
				
				ed.save(exe);
			}
		}

		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}

		catch (IOException e)
		{
			e.printStackTrace();
		}

		finally
		{

			if (null != br)
			{
				try
				{
					br.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}

		DbHelper.getInstance().closeConnection(conn);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		doGet(request, response);
	}

}
