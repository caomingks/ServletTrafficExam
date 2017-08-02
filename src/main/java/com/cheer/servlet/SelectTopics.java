package com.cheer.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cheer.dao.ExerciseDao;
import com.cheer.dao.impl.ExerciseDaoImpl;
import com.cheer.domian.Exercise;
import com.cheer.domian.Topics;
import com.cheer.util.DbHelper;
import com.google.gson.Gson;

public class SelectTopics extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public SelectTopics()
	{

	}

	//从数据库随机选择要考的题目，取出，并放入Session
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		Connection conn = DbHelper.getInstance().getConnection();
		ExerciseDao ed=new ExerciseDaoImpl(conn);
		
		//创建选择的题目要存放的容器，map.
		Map<Integer,Topics> mapTopics = new LinkedHashMap<>();
		//Gson gson=new Gson();

		int n = 60;
		Random rand = new Random();
		
		boolean[] bool = new boolean[n+1];
		
		int randInt = 0;

		//随机从60挑中生成20个不重复的id,根据id从数据库取出数据，赋值给Topics，存入map,存入session
		for (int i = 1; i <=20; i++)
		{
			Topics topics=new Topics();
			do
			{
				randInt = rand.nextInt(n)+1;
			} 
			
			while (bool[randInt]);
			
			bool[randInt] = true;
			
			Exercise ex=ed.find(randInt);
			topics.setExercise(ex);
			topics.setNo(i);
			
			System.out.println(topics);
			
			mapTopics.put(i, topics);
		}
		
		DbHelper.getInstance().closeConnection(conn);
//		String jsonMap=gson.toJson(map);
//		System.out.println(jsonMap);
		System.out.println(mapTopics.size());
		request.getSession().setAttribute("topics", mapTopics);
		
		//随机生成试题后，从定向考试页面
		response.sendRedirect("topics.jsp");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		doGet(request, response);
	}

}
