package com.cheer.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cheer.domian.Topics;

public class Grade extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public Grade()
	{
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		
		Map<Integer,Topics> mapTopics=(Map<Integer,Topics>)session.getAttribute("topics");	
		
		//循环遍历mapTopics,比较答案，答对则计数
		Set<Integer> set=mapTopics.keySet();
		int count=0;
		List<Integer> error=new LinkedList<>();
		for(Integer key : mapTopics.keySet())
		{
			
			String answer=mapTopics.get(key).getExercise().getAnswer();
			String checked=mapTopics.get(key).getChecked();
			if(answer.equals(checked))
			{
				count++;
			}
			else
			{
				error.add(key);
			}
		}
		
		int grade=count*5;
		
		session.setAttribute("error", error);
		request.setAttribute("grade", grade);
		
		request.getRequestDispatcher("showGrade.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

}
