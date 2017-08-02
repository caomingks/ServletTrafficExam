package com.cheer.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cheer.domian.Exercise;
import com.cheer.domian.Topics;
import com.google.gson.Gson;

public class NextQuestion extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public NextQuestion()
	{
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		
		//获取上一条答案
		String str=request.getParameter("select");
		
		//当前请求题的index
		Integer index=(Integer)session.getAttribute("index")+1;
		Gson gson=new Gson();
		Map<Integer,Topics> mapTopics=(Map<Integer,Topics>)session.getAttribute("topics");	
		
		
		//边界处理，当达到最后一条时，再次请求给出提示
		if(index>mapTopics.size())
		{
			index=mapTopics.size()+1;
			Topics topicChecked=mapTopics.get(index-1);
			topicChecked.setChecked(str);
			System.out.println("di "+(index-1)+ "tiao "+topicChecked);
			
			String message="{\"message\":\"the last!\"}";
			out.println(message);
		}
		
		else
		{
			
			//获取已经有答案的对象试题
			Topics topicChecked=mapTopics.get(index-1);
			//将答案存入
			topicChecked.setChecked(str);
			System.out.println("第 "+(index-1)+ "条 "+topicChecked);
			
			//获取请求下标对应的题目
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
