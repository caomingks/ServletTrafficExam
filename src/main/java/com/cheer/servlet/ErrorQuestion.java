package com.cheer.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cheer.domian.Topics;
import com.google.gson.Gson;

public class ErrorQuestion extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public ErrorQuestion()
	{

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		
		List<Integer> errors=(List<Integer>)session.getAttribute("error");
		Map<Integer,Topics> mapTopics=(Map<Integer,Topics>)session.getAttribute("topics");	
		List<Topics> errorsTopics=new LinkedList<>();
		
		for(int i=0;i<errors.size();i++)
		{
			Topics error=mapTopics.get(errors.get(i));
			errorsTopics.add(error);
		}
		
		Gson gson=new Gson();
		String listGson=gson.toJson(errorsTopics);
		System.out.println(listGson);
		out.println(listGson);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		doGet(request, response);
	}

}
