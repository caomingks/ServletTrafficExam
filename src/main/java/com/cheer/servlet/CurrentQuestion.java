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
		
		//创建下标，初始化为1，存入session，用于上一题，下一题，下标计数
		Integer index=1;
		Gson gson=new Gson();
		
		//从session中取出已选择的试题
		Map<Integer,Topics> mapTopics=(Map<Integer,Topics>)session.getAttribute("topics");
		
		//通过Index取出对应试题
		Topics topic=mapTopics.get(index);
		System.out.println(topic);
		
		//取出的试题转为Json格式字符串
		String exJson=gson.toJson(topic);
		//将当前使用的index存入session
		session.setAttribute("index", index);
		//回应Json格式字符串，交给Ajax回调函数function(data)处理
		out.println(exJson);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		doGet(request, response);
	}

}
