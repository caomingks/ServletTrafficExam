package com.cheer.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cheer.domian.Exercise;
import com.cheer.domian.Topics;
import com.google.gson.Gson;

public class CurrentQuestion extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public CurrentQuestion()
	{
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out=response.getWriter();
		
		HttpSession session=request.getSession();
		//INDEX
//		Integer index=(Integer)session.getAttribute("index");
//		System.out.println("index"+index);
//		Gson gson=new Gson();
//		if(null==index)
//		{
//			index=1;
//		}
		Integer index=1;
		Gson gson=new Gson();
		Map<Integer,Topics> mapTopics=(Map<Integer,Topics>)session.getAttribute("topics");
		
		Topics topic=mapTopics.get(index);
		System.out.println(topic);
		
		
		String exJson=gson.toJson(topic);
		session.setAttribute("index", index);
		out.println(exJson);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		doGet(request, response);
	}

}
