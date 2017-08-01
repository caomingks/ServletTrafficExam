package com.cheer.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cheer.domian.Exercise;
import com.cheer.domian.Topics;
import com.google.gson.Gson;

public class PreQuestion extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public PreQuestion()
	{
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		
		
		String str=request.getParameter("select");
		
		Integer index=(Integer)session.getAttribute("index")-1;
		Gson gson=new Gson();
		Map<Integer,Topics> mapTopics=(Map<Integer,Topics>)session.getAttribute("topics");	
		
		if(index<1)
		{
			index=0;
			Topics topicChecked=mapTopics.get(index+1);
			topicChecked.setChecked(str);
			System.out.println("di "+(index+1)+ "tiao"+topicChecked);
			
			String message="{\"message\":\"the first!\"}";
			out.println(message);
		}else
		{
			
			Topics topicChecked=mapTopics.get(index+1);
			topicChecked.setChecked(str);
			System.out.println("di "+(index+1)+ "tiao"+topicChecked);
			
			Topics topic=mapTopics.get(index);
			String exJson=gson.toJson(topic);
			String exJsonChecked=gson.toJson(topicChecked);
			
			session.setAttribute("index", index);
			
			out.println(exJson);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		doGet(request, response);
	}

}
